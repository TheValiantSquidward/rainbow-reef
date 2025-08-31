package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ByIdMap;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FishDigGoal;
import net.thevaliantsquidward.rainbowreef.entity.ai.goals.FollowVariantLeaderGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.LocateNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.MoveToNemGoal;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.NemHoster;
import net.thevaliantsquidward.rainbowreef.entity.base.NemHoster.RestInNemGoal;
import net.thevaliantsquidward.rainbowreef.registry.ReefItems;
import net.thevaliantsquidward.rainbowreef.registry.tags.RRTags;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.function.IntFunction;

public class Clownfish extends NemHoster {

    public Clownfish(EntityType<? extends NemHoster> entityType, Level level) {
        super(entityType, level, 200, 4, 600, 200);
        this.moveControl = new SmoothSwimmingMoveControl(this, 1000, 60, 0.02F, 0.1F, true);
        this.lookControl = new SmoothSwimmingLookControl(this, 4);
    }

    public static AttributeSupplier setAttributes() {
        return Animal.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.7F)
                .build();
    }

    @Override
    protected void registerGoals() {
        // Anemone seeker goal plan:
        // priority of 0, but only works if the clown has a home nem and is over 10 blocks from it
        // Pathfinds back to home nem and makes it hide for 3 - 5 secs
        this.goalSelector.addGoal(0, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.25D));
        this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, Player.class, 8.0F, 1.6D, 1.4D, EntitySelector.NO_SPECTATORS::test));
        this.goalSelector.addGoal(3, new RestInNemGoal(this, 3, 600, 200));
        this.goalSelector.addGoal(4, new MoveToNemGoal(this, 1, 4));
        this.goalSelector.addGoal(5, new LocateNemGoal(this, 200));
        this.goalSelector.addGoal(6, new FishDigGoal(this, 10, RRTags.CLOWNFISH_DIET));
        this.goalSelector.addGoal(7, new RandomSwimmingGoal(this, 1, 40));
        this.goalSelector.addGoal(8, new FollowVariantLeaderGoal(this));
    }

    @Override
    public int getMaxSchoolSize() {
        return 5;
    }

    @Override
    @NotNull
    public ItemStack getBucketItemStack() {
        return new ItemStack(ReefItems.CLOWNFISH_BUCKET.get());
    }

    public enum ClownfishVariant implements StringRepresentable {
        OCELLARIS(0, "ocellaris"),
        BLACK_AND_WHITE(1, "black_and_white"),
        PINK_SKUNK(2, "pink_skunk"),
        MAROON(3, "maroon"),
        CLARKII(4, "clarkii"),
        TOMATO(5, "tomato"),
        MADAGASCAR(6, "madagascar"),
        ALLARD(7, "allard"),
        RED_SADDLEBACK(8, "red_saddleback"),

        // rare (could probably separate in a better way but this works for now)
        BLIZZARD(9, "blizzard"),
        BLUESTRAIN(10, "bluestrain"),
        OMAN(11, "oman"),
        MOCHA(12, "mocha"),
        WHITESNOUT(13, "whitesnout"),
        GOLD_NUGGET(14, "gold_nugget"),
        SNOWSTORM(15, "snowstorm"),
        ORANGE_SKUNK(16, "orange_skunk"),
        DOMINO(17, "domino"),
        YELLOW_CLARKII(18, "yellow_clarkii"),
        NAKED(19, "naked");

        private final int variant;
        private final String name;

        ClownfishVariant(int variant, String name) {
            this.variant = variant;
            this.name = name;
        }

        public int getVariant() {
            return this.variant;
        }

        @Override
        public @NotNull String getSerializedName() {
            return this.name;
        }

        private static final IntFunction<ClownfishVariant> VARIANT_ID = ByIdMap.sparse(ClownfishVariant::getVariant, ClownfishVariant.values(), OCELLARIS);

        public static ClownfishVariant variantId(int id) {
            return VARIANT_ID.apply(id);
        }
    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty, MobSpawnType spawnType, @Nullable SpawnGroupData spawnData, @Nullable CompoundTag compoundTag) {
        ClownfishVariant commonVariant = ClownfishVariant.variantId(level.getRandom().nextInt(8));
        ClownfishVariant rareVariant = ClownfishVariant.variantId(9 + level.getRandom().nextInt(10));

        if (this.getRandom().nextFloat() <= 0.005F) {
            this.setVariant(rareVariant.getVariant());
        } else {
            this.setVariant(commonVariant.getVariant());
        }

        this.findAndSetNems();
        return super.finalizeSpawn(level, difficulty, spawnType, spawnData, compoundTag);
    }
}
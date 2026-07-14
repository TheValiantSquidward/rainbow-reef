package net.thevaliantsquidward.rainbowreef.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlockEntities;
import org.jetbrains.annotations.Nullable;

public class BurrowBlock extends Block implements EntityBlock {

    private final boolean ground;
    private final TagKey<Block> substrate;

    public BurrowBlock(boolean ground, TagKey<Block> substrate, Properties properties) {
        super(properties);
        this.ground = ground;
        this.substrate = substrate;
    }

    public boolean isGround() {
        return this.ground;
    }

    public TagKey<Block> substrate() {
        return this.substrate;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BurrowBlockEntity(pos, state);
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (!level.isClientSide && type == ReefBlockEntities.BURROW.get()) {
            return (BlockEntityTicker<T>) (BlockEntityTicker<BurrowBlockEntity>) BurrowBlockEntity::serverTick;
        }
        return null;
    }

    @Override
    public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof BurrowBlockEntity burrow
                && !player.isCreative() && hasSilkTouch(level, player.getMainHandItem())) {
            burrow.setKeepOccupantsOnBreak(true);
        }
        return super.playerWillDestroy(level, pos, state, player);
    }

    @Override
    public void onRemove(BlockState state, Level level, BlockPos pos, BlockState newState, boolean movedByPiston) {
        if (!state.is(newState.getBlock()) && level.getBlockEntity(pos) instanceof BurrowBlockEntity burrow
                && !burrow.keepOccupantsOnBreak()) {
            burrow.evacuate();
        }
        super.onRemove(state, level, pos, newState, movedByPiston);
    }

    private static boolean hasSilkTouch(Level level, ItemStack tool) {
        Holder<Enchantment> silkTouch = level.registryAccess().registryOrThrow(Registries.ENCHANTMENT).getHolderOrThrow(Enchantments.SILK_TOUCH);
        return EnchantmentHelper.getItemEnchantmentLevel(silkTouch, tool) > 0;
    }
}

package com.valiantenvoy.rainbow_reef.registry;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Consumer;

public class ReefEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPE = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, RainbowReef.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<Angelfish>> ANGELFISH = registerEntity("angelfish", Angelfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.6F, 0.6F).eyeHeight(0.3F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<ArrowCrab>> ARROW_CRAB = registerEntity("arrow_crab", ArrowCrab::new, MobCategory.CREATURE, builder -> builder.sized(0.6F, 0.6F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Basslet>> BASSLET = registerEntity("basslet", Basslet::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Billfish>> BILLFISH = registerEntity("billfish", Billfish::new, MobCategory.WATER_CREATURE, builder -> builder.sized(0.9F, 0.9F).eyeHeight(0.45F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Boxfish>> BOXFISH = registerEntity("boxfish", Boxfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Butterflyfish>> BUTTERFLYFISH = registerEntity("butterflyfish", Butterflyfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Clownfish>> CLOWNFISH = registerEntity("clownfish", Clownfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Crab>> CRAB = registerEntity("crab", Crab::new, MobCategory.CREATURE, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Damselfish>> DAMSELFISH = registerEntity("damselfish", Damselfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<DwarfAngelfish>> DWARF_ANGELFISH = registerEntity("dwarf_angelfish", DwarfAngelfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Frogfish>> FROGFISH = registerEntity("frogfish", Frogfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Fusilier>> FUSILIER = registerEntity("fusilier", Fusilier::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Goby>> GOBY = registerEntity("goby", Goby::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Hogfish>> HOGFISH = registerEntity("hogfish", Hogfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Jellyfish>> JELLYFISH = registerEntity("jellyfish", Jellyfish::new, MobCategory.WATER_CREATURE, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<LargeShark>> LARGE_SHARK = registerEntity("large_shark", LargeShark::new, MobCategory.WATER_CREATURE, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Lionfish>> LIONFISH = registerEntity("lionfish", Lionfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<MahiMahi>> MAHI_MAHI = registerEntity("mahi_mahi", MahiMahi::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.8F, 0.9F).eyeHeight(0.45F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<MaoriWrasse>> MAORI_WRASSE = registerEntity("maori_wrasse", MaoriWrasse::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<MoorishIdol>> MOORISH_IDOL = registerEntity("moorish_idol", MoorishIdol::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Parrotfish>> PARROTFISH = registerEntity("parrotfish", Parrotfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Pipefish>> PIPEFISH = registerEntity("pipefish", Pipefish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Rabbitfish>> RABBITFISH = registerEntity("rabbitfish", Rabbitfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Ray>> RAY = registerEntity("ray", Ray::new, MobCategory.WATER_CREATURE, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Seahorse>> SEAHORSE = registerEntity("seahorse", Seahorse::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.75F).eyeHeight(0.375F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Shark>> SHARK = registerEntity("shark", Shark::new, MobCategory.WATER_CREATURE, builder -> builder.sized(1.25F, 0.9F).eyeHeight(0.45F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<SmallShark>> SMALL_SHARK = registerEntity("small_shark", SmallShark::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Tang>> TANG = registerEntity("tang", Tang::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Triggerfish>> TRIGGERFISH = registerEntity("triggerfish", Triggerfish::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));
    public static final DeferredHolder<EntityType<?>, EntityType<Wrasse>> WRASSE = registerEntity("wrasse", Wrasse::new, MobCategory.WATER_AMBIENT, builder -> builder.sized(0.5F, 0.5F).eyeHeight(0.25F).clientTrackingRange(10));

    public static <E extends Entity> DeferredHolder<EntityType<?>, EntityType<E>> registerEntity(String name, EntityType.EntityFactory<E> factory, MobCategory entityClassification, Consumer<EntityType.Builder<E>> builderConsumer) {
        if (entityClassification != MobCategory.MISC) {
            ReefMobVariants.register(name);
        }
        return ENTITY_TYPE.register(name, () -> {
            var builder = EntityType.Builder.of(factory, entityClassification);
            builderConsumer.accept(builder);
            return builder.build(RainbowReef.MOD_ID + ":" + name);
        });
    }
}

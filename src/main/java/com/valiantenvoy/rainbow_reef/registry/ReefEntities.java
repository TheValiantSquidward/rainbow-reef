package com.valiantenvoy.rainbow_reef.registry;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.*;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ReefEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, RainbowReef.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<Goby>> GOBY = ENTITY_TYPES.register(
            "goby", () ->
            EntityType.Builder.of(Goby::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("goby").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Jellyfish>> JELLYFISH = ENTITY_TYPES.register(
            "jellyfish", () ->
            EntityType.Builder.of(Jellyfish::new, MobCategory.WATER_CREATURE)
                    .sized(1.1F, 1.1F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("jellyfish").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<MoorishIdol>> MOORISH_IDOL = ENTITY_TYPES.register(
            "moorish_idol", () ->
            EntityType.Builder.of(MoorishIdol::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("moorish_idol").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Tang>> TANG = ENTITY_TYPES.register(
            "tang", () ->
            EntityType.Builder.of(Tang::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("tang").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Seahorse>> SEAHORSE = ENTITY_TYPES.register(
            "seahorse", () ->
            EntityType.Builder.of(Seahorse::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("seahorse").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<Wrasse>> WRASSE = ENTITY_TYPES.register(
            "wrasse", () ->
            EntityType.Builder.of(Wrasse::new, MobCategory.WATER_AMBIENT)
                    .sized(0.3F, 0.3F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("wrasse").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<Triggerfish>> TRIGGERFISH = ENTITY_TYPES.register(
            "triggerfish", () ->
            EntityType.Builder.of(Triggerfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.4F, 0.4F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("triggerfish").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<Damselfish>> DAMSELFISH = ENTITY_TYPES.register(
            "damselfish", () ->
            EntityType.Builder.of(Damselfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.3F, 0.3F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("damselfish").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<Rabbitfish>> RABBITFISH = ENTITY_TYPES.register(
            "rabbitfish", () ->
            EntityType.Builder.of(Rabbitfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.3F, 0.3F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("rabbitfish").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<Boxfish>> BOXFISH = ENTITY_TYPES.register(
            "boxfish", () ->
            EntityType.Builder.of(Boxfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("boxfish").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<SmallShark>> SMALL_SHARK = ENTITY_TYPES.register(
            "small_shark", () ->
            EntityType.Builder.of(SmallShark::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("small_shark").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<Shark>> SHARK = ENTITY_TYPES.register(
            "shark", () ->
            EntityType.Builder.of(Shark::new, MobCategory.WATER_CREATURE)
                    .sized(1.25F, 1.0F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("shark").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<LargeShark>> LARGE_SHARK = ENTITY_TYPES.register(
            "large_shark", () ->
            EntityType.Builder.of(LargeShark::new, MobCategory.WATER_CREATURE)
                    .sized(1.3F, 1.2F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("large_shark").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<Clownfish>> CLOWNFISH = ENTITY_TYPES.register(
            "clownfish", () ->
            EntityType.Builder.of(Clownfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.3F, 0.3F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("clownfish").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<Butterflyfish>> BUTTERFLYFISH = ENTITY_TYPES.register(
            "butterflyfish", () ->
            EntityType.Builder.of(Butterflyfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("butterflyfish").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<DwarfAngelfish>> DWARF_ANGELFISH = ENTITY_TYPES.register(
            "dwarf_angelfish", () ->
            EntityType.Builder.of(DwarfAngelfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.3F, 0.3F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("dwarf_angelfish").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<Hogfish>> HOGFISH = ENTITY_TYPES.register(
            "hogfish", () ->
            EntityType.Builder.of(Hogfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("hogfish").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Crab>> CRAB = ENTITY_TYPES.register(
            "crab", () ->
            EntityType.Builder.of(Crab::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("crab").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<ArrowCrab>> ARROW_CRAB = ENTITY_TYPES.register(
            "arrow_crab", () ->
            EntityType.Builder.of(ArrowCrab::new, MobCategory.CREATURE)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("arrow_crab").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Basslet>> BASSLET = ENTITY_TYPES.register(
            "basslet", () ->
            EntityType.Builder.of(Basslet::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5f, 0.5f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("basslet").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Pipefish>> PIPEFISH = ENTITY_TYPES.register(
            "pipefish", () ->
            EntityType.Builder.of(Pipefish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.35F, 0.35F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("pipefish").toString())
    );

    public static final DeferredHolder<EntityType<?>, EntityType<Parrotfish>> PARROTFISH = ENTITY_TYPES.register(
            "parrotfish", () ->
            EntityType.Builder.of(Parrotfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6f, 0.6f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("parrotfish").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Ray>> RAY = ENTITY_TYPES.register(
            "ray", () ->
            EntityType.Builder.of(Ray::new, MobCategory.WATER_AMBIENT)
                    .sized(1.0F, 0.6F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("ray").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Angelfish>> ANGELFISH = ENTITY_TYPES.register(
            "angelfish", () ->
            EntityType.Builder.of(Angelfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6f, 0.6f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("angelfish").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<Lionfish>> LIONFISH = ENTITY_TYPES.register(
            "lionfish", () ->
            EntityType.Builder.of(Lionfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6f, 0.6f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("lionfish").toString()));


    public static final DeferredHolder<EntityType<?>, EntityType<MahiMahi>> MAHI_MAHI = ENTITY_TYPES.register(
            "mahi_mahi", () ->
            EntityType.Builder.of(MahiMahi::new, MobCategory.WATER_AMBIENT)
                    .sized(0.9F, 1.1F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("mahi_mahi").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Billfish>> BILLFISH = ENTITY_TYPES.register(
            "billfish", () ->
            EntityType.Builder.of(Billfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.9F, 1.1F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("billfish").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<MaoriWrasse>> MAORI_WRASSE = ENTITY_TYPES.register(
            "maori_wrasse", () ->
            EntityType.Builder.of(MaoriWrasse::new, MobCategory.WATER_AMBIENT)
                    .sized(1.2f, 1.2f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("maori_wrasse").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Frogfish>> FROGFISH = ENTITY_TYPES.register(
            "frogfish", () ->
            EntityType.Builder.of(Frogfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.6f, 0.6f)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("frogfish").toString()));

    public static final DeferredHolder<EntityType<?>, EntityType<Fusilier>> FUSILIER = ENTITY_TYPES.register(
            "fusilier", () ->
            EntityType.Builder.of(Fusilier::new, MobCategory.WATER_AMBIENT)
                    .sized(0.35F, 0.35F)
                    .clientTrackingRange(10)
                    .build(RainbowReef.location("fusilier").toString())
    );


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

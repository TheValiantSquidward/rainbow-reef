package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.*;

public class ReefEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RainbowReef.MOD_ID);

    public static final RegistryObject<EntityType<Goby>> GOBY =
            ENTITY_TYPES.register("goby",
                    () -> EntityType.Builder.of(Goby::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "goby").toString()));

    public static final RegistryObject<EntityType<Jellyfish>> JELLYFISH =
            ENTITY_TYPES.register("jellyfish",
                    () -> EntityType.Builder.of(Jellyfish::new, MobCategory.WATER_AMBIENT)
                            .sized(0.9f, 0.9f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "jellyfish").toString()));

    public static final RegistryObject<EntityType<MoorishIdol>> MOORISH_IDOL =
            ENTITY_TYPES.register("moorish_idol",
                    () -> EntityType.Builder.of(MoorishIdol::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "moorish_idol").toString()));

    public static final RegistryObject<EntityType<Tang>> TANG =
            ENTITY_TYPES.register("tang",
                    () -> EntityType.Builder.of(Tang::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "tang").toString()));

    public static final RegistryObject<EntityType<Seahorse>> SEAHORSE = ENTITY_TYPES.register(
            "seahorse", () ->
            EntityType.Builder.of(Seahorse::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "seahorse").toString())
    );

    public static final RegistryObject<EntityType<Wrasse>> WRASSE = ENTITY_TYPES.register(
            "wrasse", () ->
            EntityType.Builder.of(Wrasse::new, MobCategory.WATER_AMBIENT)
                    .sized(0.3F, 0.3F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "wrasse").toString())
    );

    public static final RegistryObject<EntityType<Triggerfish>> TRIGGERFISH = ENTITY_TYPES.register(
            "triggerfish", () ->
            EntityType.Builder.of(Triggerfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.4F, 0.4F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "triggerfish").toString())
    );

    public static final RegistryObject<EntityType<Damselfish>> DAMSELFISH = ENTITY_TYPES.register(
            "damselfish", () ->
            EntityType.Builder.of(Damselfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.3F, 0.3F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "damselfish").toString())
    );

    public static final RegistryObject<EntityType<Rabbitfish>> RABBITFISH = ENTITY_TYPES.register(
            "rabbitfish", () ->
            EntityType.Builder.of(Rabbitfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.3F, 0.3F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "rabbitfish").toString())
    );

    public static final RegistryObject<EntityType<Boxfish>> BOXFISH = ENTITY_TYPES.register(
            "boxfish", () ->
            EntityType.Builder.of(Boxfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "boxfish").toString())
    );

    public static final RegistryObject<EntityType<SmallShark>> SMALL_SHARK = ENTITY_TYPES.register(
            "small_shark", () ->
            EntityType.Builder.of(SmallShark::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "small_shark").toString())
    );

    public static final RegistryObject<EntityType<Shark>> SHARK = ENTITY_TYPES.register(
            "shark", () ->
            EntityType.Builder.of(Shark::new, MobCategory.WATER_CREATURE)
                    .sized(1.25F, 1.0F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "shark").toString())
    );

    public static final RegistryObject<EntityType<LargeShark>> LARGE_SHARK = ENTITY_TYPES.register(
            "large_shark", () ->
                    EntityType.Builder.of(LargeShark::new, MobCategory.WATER_CREATURE)
                            .sized(1.3F, 1.2F)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "large_shark").toString())
    );

    public static final RegistryObject<EntityType<Clownfish>> CLOWNFISH = ENTITY_TYPES.register(
            "clownfish", () ->
            EntityType.Builder.of(Clownfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.3F, 0.3F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "clownfish").toString())
    );

    public static final RegistryObject<EntityType<Butterflyfish>> BUTTERFLYFISH = ENTITY_TYPES.register(
            "butterflyfish", () ->
            EntityType.Builder.of(Butterflyfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.5F, 0.5F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "butterflyfish").toString())
    );

    public static final RegistryObject<EntityType<DwarfAngelfish>> DWARF_ANGELFISH = ENTITY_TYPES.register(
            "dwarf_angelfish", () ->
            EntityType.Builder.of(DwarfAngelfish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.3F, 0.3F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "dwarf_angelfish").toString())
    );

    public static final RegistryObject<EntityType<Hogfish>> HOGFISH =
            ENTITY_TYPES.register("hogfish",
                    () -> EntityType.Builder.of(Hogfish::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "hogfish").toString()));

    public static final RegistryObject<EntityType<Crab>> CRAB =
            ENTITY_TYPES.register("crab",
                    () -> EntityType.Builder.of(Crab::new, MobCategory.CREATURE)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "crab").toString()));

    public static final RegistryObject<EntityType<ArrowCrab>> ARROW_CRAB =
            ENTITY_TYPES.register("arrow_crab",
                    () -> EntityType.Builder.of(ArrowCrab::new, MobCategory.CREATURE)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "arrow_crab").toString()));

    public static final RegistryObject<EntityType<Basslet>> BASSLET =
            ENTITY_TYPES.register("basslet",
                    () -> EntityType.Builder.of(Basslet::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "basslet").toString()));

    public static final RegistryObject<EntityType<Pipefish>> PIPEFISH = ENTITY_TYPES.register(
            "pipefish", () ->
            EntityType.Builder.of(Pipefish::new, MobCategory.WATER_AMBIENT)
                    .sized(0.35F, 0.35F)
                    .clientTrackingRange(10)
                    .build(new ResourceLocation(RainbowReef.MOD_ID, "pipefish").toString())
    );

    public static final RegistryObject<EntityType<Parrotfish>> PARROTFISH =
            ENTITY_TYPES.register("parrotfish",
                    () -> EntityType.Builder.of(Parrotfish::new, MobCategory.WATER_AMBIENT)
                            .sized(0.6f, 0.6f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "parrotfish").toString()));

    public static final RegistryObject<EntityType<Ray>> RAY =
            ENTITY_TYPES.register("ray",
                    () -> EntityType.Builder.of(Ray::new, MobCategory.WATER_AMBIENT)
                            .sized(1.0F, 0.6F)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "ray").toString()));

    public static final RegistryObject<EntityType<Angelfish>> ANGELFISH =
            ENTITY_TYPES.register("angelfish",
                    () -> EntityType.Builder.of(Angelfish::new, MobCategory.WATER_AMBIENT)
                            .sized(0.6f, 0.6f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "angelfish").toString()));


    public static final RegistryObject<EntityType<Lionfish>> LIONFISH =
            ENTITY_TYPES.register("lionfish",
                    () -> EntityType.Builder.of(Lionfish::new, MobCategory.WATER_AMBIENT)
                            .sized(0.6f, 0.6f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "lionfish").toString()));


    public static final RegistryObject<EntityType<MahiMahi>> MAHI_MAHI =
            ENTITY_TYPES.register("mahi_mahi",
                    () -> EntityType.Builder.of(MahiMahi::new, MobCategory.WATER_AMBIENT)
                            .sized(1.2f, 1.2f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "mahi_mahi").toString()));

    public static final RegistryObject<EntityType<Billfish>> BILLFISH =
            ENTITY_TYPES.register("billfish",
                    () -> EntityType.Builder.of(Billfish::new, MobCategory.WATER_AMBIENT)
                            .sized(1.2f, 1.2f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "billfish").toString()));

    public static final RegistryObject<EntityType<MaoriWrasse>> MAORI_WRASSE =
            ENTITY_TYPES.register("maori_wrasse",
                    () -> EntityType.Builder.of(MaoriWrasse::new, MobCategory.WATER_AMBIENT)
                            .sized(1.2f, 1.2f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "maori_wrasse").toString()));

    public static final RegistryObject<EntityType<Frogfish>> FROGFISH =
            ENTITY_TYPES.register("frogfish",
                    () -> EntityType.Builder.of(Frogfish::new, MobCategory.WATER_AMBIENT)
                            .sized(0.6f, 0.6f)
                            .clientTrackingRange(10)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "frogfish").toString()));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

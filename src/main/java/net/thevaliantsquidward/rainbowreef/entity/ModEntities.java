package net.thevaliantsquidward.rainbowreef.entity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.custom.*;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RainbowReef.MOD_ID);

    public static final RegistryObject<EntityType<GobyEntity>> GOBY =
            ENTITY_TYPES.register("goby",
                    () -> EntityType.Builder.of(GobyEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "goby").toString()));

    public static final RegistryObject<EntityType<TangEntity>> TANG =
            ENTITY_TYPES.register("tang",
                    () -> EntityType.Builder.of(TangEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "tang").toString()));

    public static final RegistryObject<EntityType<SeahorseEntity>> SEAHORSE =
            ENTITY_TYPES.register("seahorse",
                    () -> EntityType.Builder.of(SeahorseEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "seahorse").toString()));


    public static final RegistryObject<EntityType<BoxfishEntity>> BOXFISH =
            ENTITY_TYPES.register("boxfish",
                    () -> EntityType.Builder.of(BoxfishEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "boxfish").toString()));

    public static final RegistryObject<EntityType<SmallSharkEntity>> SMALL_SHARK =
            ENTITY_TYPES.register("small_shark",
                    () -> EntityType.Builder.of(SmallSharkEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.7f, 0.7f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "small_shark").toString()));

    public static final RegistryObject<EntityType<ClownfishEntity>> CLOWNFISH =
            ENTITY_TYPES.register("clownfish",
                    () -> EntityType.Builder.of(ClownfishEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "clownfish").toString()));

    public static final RegistryObject<EntityType<ButterfishEntity>> BUTTERFISH =
            ENTITY_TYPES.register("butterflyfish",
                    () -> EntityType.Builder.of(ButterfishEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "butterflyfish").toString()));

    public static final RegistryObject<EntityType<DwarfAngelfishEntity>> DWARFANGEL =
            ENTITY_TYPES.register("dwarf_angelfish",
                    () -> EntityType.Builder.of(DwarfAngelfishEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "dwarf_angelfish").toString()));

    public static final RegistryObject<EntityType<HogfishEntity>> HOGFISH =
            ENTITY_TYPES.register("hogfish",
                    () -> EntityType.Builder.of(HogfishEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "hogfish").toString()));

    public static final RegistryObject<EntityType<BassletEntity>> BASSLET =
            ENTITY_TYPES.register("basslet",
                    () -> EntityType.Builder.of(BassletEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "basslet").toString()));

    public static final RegistryObject<EntityType<PipefishEntity>> PIPEFISH =
            ENTITY_TYPES.register("pipefish",
                    () -> EntityType.Builder.of(PipefishEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.5f, 0.5f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "pipefish").toString()));

    public static final RegistryObject<EntityType<ParrotfishEntity>> PARROTFISH =
            ENTITY_TYPES.register("parrotfish",
                    () -> EntityType.Builder.of(ParrotfishEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(0.6f, 0.6f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "parrotfish").toString()));

    public static final RegistryObject<EntityType<RayEntity>> RAY =
            ENTITY_TYPES.register("ray",
                    () -> EntityType.Builder.of(RayEntity::new, MobCategory.WATER_AMBIENT)
                            .sized(1f, 0.6f)
                            .build(new ResourceLocation(RainbowReef.MOD_ID, "ray").toString()));



    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}

package com.valiantenvoy.rainbow_reef.registry;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.entity.variant.ReefMobVariant;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.neoforged.neoforge.registries.DataPackRegistryEvent;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ReefMobVariants {

    public static final Set<ResourceKey<Registry<ReefMobVariant>>> REGISTRIES = new HashSet<>();

    public static ResourceKey<Registry<ReefMobVariant>> register(String name) {
        ResourceKey<Registry<ReefMobVariant>> key = registryFor(name);
        REGISTRIES.add(key);
        return key;
    }

    public static void registerVariantRegistries(DataPackRegistryEvent.NewRegistry event) {
        REGISTRIES.forEach(registry -> event.dataPackRegistry(registry, ReefMobVariant.DIRECT_CODEC, ReefMobVariant.DIRECT_CODEC));
    }

    public static ResourceKey<Registry<ReefMobVariant>> registryFor(String mobName) {
        return ResourceKey.createRegistryKey(RainbowReef.location("mob_variant/" + mobName));
    }

    public static ResourceKey<Registry<ReefMobVariant>> registryFor(EntityType<?> type) {
        return registryFor(EntityType.getKey(type).getPath());
    }

    public static Optional<ResourceKey<Registry<ReefMobVariant>>> findRegistry(EntityType<?> type) {
        ResourceLocation id = EntityType.getKey(type);

        if (!id.getNamespace().equals(RainbowReef.MOD_ID)) {
            return Optional.empty();
        }

        ResourceKey<Registry<ReefMobVariant>> registry = registryFor(id.getPath());
        return REGISTRIES.contains(registry) ? Optional.of(registry) : Optional.empty();
    }

    public static ResourceKey<ReefMobVariant> defaultFor(EntityType<?> type) {
        String name = EntityType.getKey(type).getPath();
        return createKey(registryFor(name), name);
    }

    public static ResourceKey<ReefMobVariant> createKey(ResourceKey<Registry<ReefMobVariant>> registry, String name) {
        return ResourceKey.create(registry, RainbowReef.location(name));
    }
}

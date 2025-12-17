package net.thevaliantsquidward.rainbowreef.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.registries.ForgeRegistries;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ReefDatapackBuiltinEntriesProvider extends DatapackBuiltinEntriesProvider {

    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ReefBiomeModifiers::bootstrap);

    public ReefDatapackBuiltinEntriesProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries,BUILDER, Set.of(RainbowReef.MOD_ID));
    }
}

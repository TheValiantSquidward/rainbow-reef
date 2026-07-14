package net.thevaliantsquidward.rainbowreef.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.thevaliantsquidward.rainbowreef.registry.ReefBlocks;

import java.util.List;
import java.util.Set;

public class ReefBlockLootProvider extends BlockLootSubProvider {

    public ReefBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.add(ReefBlocks.MUD_BURROW.get(), block -> this.burrowDrop(block, Blocks.MUD));
        this.add(ReefBlocks.SAND_BURROW.get(), block -> this.burrowDrop(block, Blocks.SAND));
        this.add(ReefBlocks.STONE_BURROW.get(), block -> this.burrowDrop(block, Blocks.STONE));
        this.add(ReefBlocks.CORALSTONE_BURROW.get(), block -> this.burrowDrop(block, ReefBlocks.CORALSTONE.get()));
    }

    private LootTable.Builder burrowDrop(Block burrow, Block base) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(burrow)
                        .when(this.hasSilkTouch())
                        .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY))
                        .otherwise(this.applyExplosionCondition(base, LootItem.lootTableItem(base)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return List.of(ReefBlocks.MUD_BURROW.get(), ReefBlocks.SAND_BURROW.get(), ReefBlocks.STONE_BURROW.get(), ReefBlocks.CORALSTONE_BURROW.get());
    }
}

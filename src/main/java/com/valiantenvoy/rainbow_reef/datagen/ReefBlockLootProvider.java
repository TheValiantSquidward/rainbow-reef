package com.valiantenvoy.rainbow_reef.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.LootTable.Builder;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.CopyComponentsFunction;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.HashSet;
import java.util.Set;

import static com.valiantenvoy.rainbow_reef.registry.ReefBlocks.*;

public class ReefBlockLootProvider extends BlockLootSubProvider {

    public ReefBlockLootProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    private final Set<Block> knownBlocks = new HashSet<>();

    @Override
    protected void add(Block block, Builder builder) {
        super.add(block, builder);
        this.knownBlocks.add(block);
    }

    @Override
    protected void generate() {
        this.add(MUD_BURROW.get(), block -> this.burrowDrop(block, Blocks.MUD));
        this.add(SAND_BURROW.get(), block -> this.burrowDrop(block, Blocks.SAND));
        this.add(STONE_BURROW.get(), block -> this.burrowDrop(block, Blocks.STONE));
        this.add(CORALSTONE_BURROW.get(), block -> this.burrowDrop(block, CORALSTONE.get()));

        this.dropSelf(CORALSTONE.get());
        this.dropSelf(CORALSTONE_STAIRS.get());
        this.add(CORALSTONE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(CORALSTONE_WALL.get());

        this.dropSelf(CORALSTONE_BRICKS.get());
        this.dropSelf(CORALSTONE_BRICK_STAIRS.get());
        this.add(CORALSTONE_BRICK_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(CORALSTONE_BRICK_WALL.get());

        this.dropSelf(POLISHED_CORALSTONE.get());
        this.dropSelf(POLISHED_CORALSTONE_STAIRS.get());
        this.add(POLISHED_CORALSTONE_SLAB.get(), this::createSlabItemTable);
        this.dropSelf(POLISHED_CORALSTONE_WALL.get());

        this.dropSelf(CHISELED_CORALSTONE.get());
    }

    private Builder burrowDrop(Block burrow, Block base) {
        return LootTable.lootTable().withPool(LootPool.lootPool()
                .setRolls(ConstantValue.exactly(1.0F))
                .add(LootItem.lootTableItem(burrow)
                        .when(this.hasSilkTouch())
                        .apply(CopyComponentsFunction.copyComponents(CopyComponentsFunction.Source.BLOCK_ENTITY))
                        .otherwise(this.applyExplosionCondition(base, LootItem.lootTableItem(base)))));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return this.knownBlocks;
    }
}

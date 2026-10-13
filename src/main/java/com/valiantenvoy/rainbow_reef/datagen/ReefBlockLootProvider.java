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

        this.dropSelf(FINE_SAND.get());
        this.dropSelf(PUFFER_LANTERN.get());

        this.dropSelf(WHITE_STAINED_SAND.get());
        this.dropSelf(LIGHT_GRAY_STAINED_SAND.get());
        this.dropSelf(GRAY_STAINED_SAND.get());
        this.dropSelf(BLACK_STAINED_SAND.get());
        this.dropSelf(BROWN_STAINED_SAND.get());
        this.dropSelf(RED_STAINED_SAND.get());
        this.dropSelf(ORANGE_STAINED_SAND.get());
        this.dropSelf(YELLOW_STAINED_SAND.get());
        this.dropSelf(LIME_STAINED_SAND.get());
        this.dropSelf(GREEN_STAINED_SAND.get());
        this.dropSelf(CYAN_STAINED_SAND.get());
        this.dropSelf(LIGHT_BLUE_STAINED_SAND.get());
        this.dropSelf(BLUE_STAINED_SAND.get());
        this.dropSelf(PURPLE_STAINED_SAND.get());
        this.dropSelf(MAGENTA_STAINED_SAND.get());
        this.dropSelf(PINK_STAINED_SAND.get());

        this.dropSelf(WHITE_STAINED_FINE_SAND.get());
        this.dropSelf(LIGHT_GRAY_STAINED_FINE_SAND.get());
        this.dropSelf(GRAY_STAINED_FINE_SAND.get());
        this.dropSelf(BLACK_STAINED_FINE_SAND.get());
        this.dropSelf(BROWN_STAINED_FINE_SAND.get());
        this.dropSelf(RED_STAINED_FINE_SAND.get());
        this.dropSelf(ORANGE_STAINED_FINE_SAND.get());
        this.dropSelf(YELLOW_STAINED_FINE_SAND.get());
        this.dropSelf(LIME_STAINED_FINE_SAND.get());
        this.dropSelf(GREEN_STAINED_FINE_SAND.get());
        this.dropSelf(CYAN_STAINED_FINE_SAND.get());
        this.dropSelf(LIGHT_BLUE_STAINED_FINE_SAND.get());
        this.dropSelf(BLUE_STAINED_FINE_SAND.get());
        this.dropSelf(PURPLE_STAINED_FINE_SAND.get());
        this.dropSelf(MAGENTA_STAINED_FINE_SAND.get());
        this.dropSelf(PINK_STAINED_FINE_SAND.get());

        this.dropSelf(WHITE_PUFFER_LANTERN.get());
        this.dropSelf(LIGHT_GRAY_PUFFER_LANTERN.get());
        this.dropSelf(GRAY_PUFFER_LANTERN.get());
        this.dropSelf(BLACK_PUFFER_LANTERN.get());
        this.dropSelf(BROWN_PUFFER_LANTERN.get());
        this.dropSelf(RED_PUFFER_LANTERN.get());
        this.dropSelf(ORANGE_PUFFER_LANTERN.get());
        this.dropSelf(YELLOW_PUFFER_LANTERN.get());
        this.dropSelf(LIME_PUFFER_LANTERN.get());
        this.dropSelf(GREEN_PUFFER_LANTERN.get());
        this.dropSelf(CYAN_PUFFER_LANTERN.get());
        this.dropSelf(LIGHT_BLUE_PUFFER_LANTERN.get());
        this.dropSelf(BLUE_PUFFER_LANTERN.get());
        this.dropSelf(PURPLE_PUFFER_LANTERN.get());
        this.dropSelf(MAGENTA_PUFFER_LANTERN.get());
        this.dropSelf(PINK_PUFFER_LANTERN.get());

        this.dropSelf(PINK_SEA_ANEMONE.get());
        this.dropSelf(BROWN_SEA_ANEMONE.get());
        this.dropSelf(PURPLE_SEA_ANEMONE.get());
        this.dropSelf(MAGENTA_SEA_ANEMONE.get());
        this.dropSelf(RED_SEA_ANEMONE.get());

        this.add(SHELF_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_SHELF_CORAL_BLOCK));
        this.add(BARREL_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_BARREL_CORAL_BLOCK));
        this.add(HAND_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_HAND_CORAL_BLOCK));
        this.add(CHIMNEY_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_CHIMNEY_CORAL_BLOCK));
        this.add(TOWER_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_TOWER_CORAL_BLOCK));
        this.add(ROSE_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_ROSE_CORAL_BLOCK));
        this.add(FLOWER_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_FLOWER_CORAL_BLOCK));
        this.add(BUSH_CORAL_BLOCK.get(), block -> this.createSingleItemTableWithSilkTouch(block, DEAD_BUSH_CORAL_BLOCK));

        this.dropWhenSilkTouch(SHELF_CORAL.get());
        this.dropWhenSilkTouch(BARREL_CORAL.get());
        this.dropWhenSilkTouch(HAND_CORAL.get());
        this.dropWhenSilkTouch(CHIMNEY_CORAL.get());
        this.dropWhenSilkTouch(TOWER_CORAL.get());
        this.dropWhenSilkTouch(ROSE_CORAL.get());
        this.dropWhenSilkTouch(FLOWER_CORAL.get());
        this.dropWhenSilkTouch(BUSH_CORAL.get());

        this.dropWhenSilkTouch(DEAD_SHELF_CORAL.get());
        this.dropWhenSilkTouch(DEAD_BARREL_CORAL.get());
        this.dropWhenSilkTouch(DEAD_HAND_CORAL.get());
        this.dropWhenSilkTouch(DEAD_CHIMNEY_CORAL.get());
        this.dropWhenSilkTouch(DEAD_TOWER_CORAL.get());
        this.dropWhenSilkTouch(DEAD_ROSE_CORAL.get());
        this.dropWhenSilkTouch(DEAD_FLOWER_CORAL.get());
        this.dropWhenSilkTouch(DEAD_BUSH_CORAL.get());

        this.dropWhenSilkTouch(SHELF_CORAL_FAN.get());
        this.dropWhenSilkTouch(BARREL_CORAL_FAN.get());
        this.dropWhenSilkTouch(HAND_CORAL_FAN.get());
        this.dropWhenSilkTouch(CHIMNEY_CORAL_FAN.get());
        this.dropWhenSilkTouch(TOWER_CORAL_FAN.get());
        this.dropWhenSilkTouch(ROSE_CORAL_FAN.get());
        this.dropWhenSilkTouch(FLOWER_CORAL_FAN.get());
        this.dropWhenSilkTouch(BUSH_CORAL_FAN.get());

        this.dropWhenSilkTouch(DEAD_SHELF_CORAL_FAN.get());
        this.dropWhenSilkTouch(DEAD_BARREL_CORAL_FAN.get());
        this.dropWhenSilkTouch(DEAD_HAND_CORAL_FAN.get());
        this.dropWhenSilkTouch(DEAD_CHIMNEY_CORAL_FAN.get());
        this.dropWhenSilkTouch(DEAD_TOWER_CORAL_FAN.get());
        this.dropWhenSilkTouch(DEAD_ROSE_CORAL_FAN.get());
        this.dropWhenSilkTouch(DEAD_FLOWER_CORAL_FAN.get());
        this.dropWhenSilkTouch(DEAD_BUSH_CORAL_FAN.get());

        this.dropWhenSilkTouch(FINE_GLASS.get());

        this.dropSelf(BUBBLER.get());

        this.dropSelf(JELLY_BLOCK.get());
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

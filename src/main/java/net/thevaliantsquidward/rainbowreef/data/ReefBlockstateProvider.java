package net.thevaliantsquidward.rainbowreef.data;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

import static net.thevaliantsquidward.rainbowreef.registry.ReefBlocks.*;

public class ReefBlockstateProvider extends BlockStateProvider {

    public ReefBlockstateProvider(GatherDataEvent event) {
        super(event.getGenerator().getPackOutput(), RainbowReef.MOD_ID, event.getExistingFileHelper());
    }

    @Override
    protected void registerStatesAndModels() {
        // shelf coral
        this.cubeAllBlock(DEAD_SHELF_CORAL_BLOCK);
        this.cubeAllBlock(SHELF_CORAL_BLOCK);
        this.simpleCross(DEAD_SHELF_CORAL);
        this.simpleCross(SHELF_CORAL);
        this.coralFan(DEAD_SHELF_CORAL_FAN, DEAD_SHELF_CORAL_WALL_FAN);
        this.coralFan(SHELF_CORAL_FAN, SHELF_CORAL_WALL_FAN);

        // barrel coral
        this.cubeAllBlock(DEAD_BARREL_CORAL_BLOCK);
        this.cubeAllBlock(BARREL_CORAL_BLOCK);
        this.simpleCross(DEAD_BARREL_CORAL);
        this.simpleCross(BARREL_CORAL);
        this.coralFan(DEAD_BARREL_CORAL_FAN, DEAD_BARREL_CORAL_WALL_FAN);
        this.coralFan(BARREL_CORAL_FAN, BARREL_CORAL_WALL_FAN);

        // hand coral
        this.cubeAllBlock(DEAD_HAND_CORAL_BLOCK);
        this.cubeAllBlock(HAND_CORAL_BLOCK);
        this.simpleCross(DEAD_HAND_CORAL);
        this.simpleCross(HAND_CORAL);
        this.coralFan(DEAD_HAND_CORAL_FAN, DEAD_HAND_CORAL_WALL_FAN);
        this.coralFan(HAND_CORAL_FAN, HAND_CORAL_WALL_FAN);

        // chimney coral
        this.cubeAllBlock(DEAD_CHIMNEY_CORAL_BLOCK);
        this.cubeAllBlock(CHIMNEY_CORAL_BLOCK);
        this.simpleCross(DEAD_CHIMNEY_CORAL);
        this.simpleCross(CHIMNEY_CORAL);
        this.coralFan(DEAD_CHIMNEY_CORAL_FAN, DEAD_CHIMNEY_CORAL_WALL_FAN);
        this.coralFan(CHIMNEY_CORAL_FAN, CHIMNEY_CORAL_WALL_FAN);

        // tower coral
        this.cubeAllBlock(DEAD_TOWER_CORAL_BLOCK);
        this.cubeAllBlock(TOWER_CORAL_BLOCK);
        this.simpleCross(DEAD_TOWER_CORAL);
        this.simpleCross(TOWER_CORAL);
        this.coralFan(DEAD_TOWER_CORAL_FAN, DEAD_TOWER_CORAL_WALL_FAN);
        this.coralFan(TOWER_CORAL_FAN, TOWER_CORAL_WALL_FAN);

        // rose coral
        this.cubeAllBlock(DEAD_ROSE_CORAL_BLOCK);
        this.cubeAllBlock(ROSE_CORAL_BLOCK);
        this.simpleCross(DEAD_ROSE_CORAL);
        this.simpleCross(ROSE_CORAL);
        this.coralFan(DEAD_ROSE_CORAL_FAN, DEAD_ROSE_CORAL_WALL_FAN);
        this.coralFan(ROSE_CORAL_FAN, ROSE_CORAL_WALL_FAN);

        // flower coral
        this.cubeAllBlock(DEAD_FLOWER_CORAL_BLOCK);
        this.cubeAllBlock(FLOWER_CORAL_BLOCK);
        this.simpleCross(DEAD_FLOWER_CORAL);
        this.simpleCross(FLOWER_CORAL);
        this.coralFan(DEAD_FLOWER_CORAL_FAN, DEAD_FLOWER_CORAL_WALL_FAN);
        this.coralFan(FLOWER_CORAL_FAN, FLOWER_CORAL_WALL_FAN);

        // ring coral
        this.cubeAllBlock(DEAD_RING_CORAL_BLOCK);
        this.cubeAllBlock(RING_CORAL_BLOCK);
        this.simpleCross(DEAD_RING_CORAL);
        this.simpleCross(RING_CORAL);
        this.coralFan(DEAD_RING_CORAL_FAN, DEAD_RING_CORAL_WALL_FAN);
        this.coralFan(RING_CORAL_FAN, RING_CORAL_WALL_FAN);

        // bush coral
        this.cubeAllBlock(DEAD_BUSH_CORAL_BLOCK);
        this.cubeAllBlock(BUSH_CORAL_BLOCK);
        this.simpleCross(DEAD_BUSH_CORAL);
        this.simpleCross(BUSH_CORAL);
        this.coralFan(DEAD_BUSH_CORAL_FAN, DEAD_BUSH_CORAL_WALL_FAN);
        this.coralFan(BUSH_CORAL_FAN, BUSH_CORAL_WALL_FAN);
    }

    // item
    private void itemModel(RegistryObject<Block> block) {
        this.itemModels().withExistingParent(getItemName(block.get()), this.blockTexture(block.get()));
    }

    private void generatedItem(ItemLike item, TextureFolder folder) {
        String name = getItemName(item);
        this.itemModels().withExistingParent(name, "item/generated").texture("layer0", this.modLoc(folder.format(name)));
    }

    private void cubeAllBlock(RegistryObject<Block> block) {
        this.simpleBlock(block.get());
        this.itemModel(block);
    }

    // block
    private void stairs(RegistryObject<Block> stairs, ResourceLocation texture) {
        this.stairsBlock((StairBlock) stairs.get(), texture);
        this.itemModel(stairs);
    }

    private void slab(RegistryObject<Block> slab, ResourceLocation texture) {
        this.slabBlock((SlabBlock) slab.get(), texture, texture);
        this.itemModel(slab);
    }

    private void wall(RegistryObject<Block> wall, ResourceLocation texture) {
        this.wallBlock((WallBlock) wall.get(), texture);
        this.itemModels().wallInventory(getItemName(wall.get()), texture);
    }

    private void simpleCross(RegistryObject<Block> block) {
        this.simpleBlock(block.get(), this.models().cross(getItemName(block.get()), this.blockTexture(block.get())).renderType("cutout"));
        this.generatedItem(block.get(), TextureFolder.BLOCK);
    }

    private void coralFan(RegistryObject<Block> coralFan, ResourceLocation texture) {
        ModelFile model = this.models().withExistingParent(getBlockName(coralFan.get()), "block/coral_fan").texture("fan", texture).renderType("cutout");
        this.simpleBlock(coralFan.get(), model);
    }

    private void coralWallFan(RegistryObject<Block> coralFan, ResourceLocation texture) {
        ModelFile model = this.models().withExistingParent(getBlockName(coralFan.get()), "block/coral_wall_fan").texture("fan", texture).renderType("cutout");
        this.horizontalBlock(coralFan.get(), model);
    }

    private void coralFan(RegistryObject<Block> coralFan, RegistryObject<Block> coralWallFan) {
        this.coralFan(coralFan, this.blockTexture(coralFan.get()));
        this.coralWallFan(coralWallFan, this.blockTexture(coralFan.get()));
        this.generatedItem(coralFan.get(), TextureFolder.BLOCK);
    }

    // utils
    private static String getItemName(ItemLike item) {
        return ForgeRegistries.ITEMS.getKey(item.asItem()).getPath();
    }

    private static String getBlockName(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block).getPath();
    }

    private enum TextureFolder {
        ITEM, BLOCK;
        public String format(String itemName) {
            return this.name().toLowerCase() + '/' + itemName;
        }
    }
}

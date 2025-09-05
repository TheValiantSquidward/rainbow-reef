package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.blocks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

public class ReefBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RainbowReef.MOD_ID);
    public static List<RegistryObject<? extends Block>> AUTO_TRANSLATE = new ArrayList<>();

    public static final RegistryObject<Block> CORALSTONE = registerBlock("coralstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEAD_BUBBLE_CORAL_BLOCK).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> JELLY_BLOCK = registerBlock("jelly_block",
            () -> new JellyBlock(BlockBehaviour.Properties.copy(Blocks.SLIME_BLOCK).friction(0.5F).noOcclusion()));

    public static final RegistryObject<Block> CORALSTONE_BRICKS = registerBlock("coralstone_bricks",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEAD_BUBBLE_CORAL_BLOCK).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> POLISHED_CORALSTONE = registerBlock("polished_coralstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEAD_BUBBLE_CORAL_BLOCK).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> CHISELED_CORALSTONE = registerBlock("chiseled_coralstone",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DEAD_BUBBLE_CORAL_BLOCK).strength(3.0F, 3.0F).requiresCorrectToolForDrops()));

    public static final RegistryObject<Block> BUBBLER = registerBlock("bubbler",
            () -> new BubblerBlock(BlockBehaviour.Properties.copy(Blocks.SAND).mapColor(MapColor.SAND).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND)));

    public static final RegistryObject<Block> RED_SAND_BUBBLER = registerBlock("red_sand_bubbler",
            () -> new RedSandBubblerBlock(BlockBehaviour.Properties.copy(Blocks.RED_SAND).mapColor(MapColor.TERRACOTTA_ORANGE).instrument(NoteBlockInstrument.SNARE).strength(0.5F).sound(SoundType.SAND)));

    // shelf coral
    public static final RegistryObject<Block> DEAD_SHELF_CORAL_BLOCK = registerBlock("dead_shelf_coral_block", () -> new Block(ReefBlockProperties.DEAD_CORAL_BLOCK));
    public static final RegistryObject<Block> DEAD_SHELF_CORAL = registerBlock("dead_shelf_coral", () -> new BaseCoralPlantBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_SHELF_CORAL = registerBlock("dead_tall_shelf_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_SHELF_CORAL_WALL_FAN = registerBlockWithoutItem("dead_shelf_coral_wall_fan", () -> new BaseCoralWallFanBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_SHELF_CORAL_FAN = registerBlockWithoutItem("dead_shelf_coral_fan", () -> new BaseCoralFanBlock(ReefBlockProperties.DEAD_CORAL));

    public static final RegistryObject<Block> SHELF_CORAL_BLOCK = registerBlock("shelf_coral_block", () -> new CoralBlock(DEAD_SHELF_CORAL_BLOCK.get(), ReefBlockProperties.coralBlock(MapColor.COLOR_LIGHT_GREEN)));
    public static final RegistryObject<Block> SHELF_CORAL = registerBlock("shelf_coral", () -> new CoralPlantBlock(DEAD_SHELF_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_GREEN)));
    public static final RegistryObject<Block> TALL_SHELF_CORAL = registerBlock("tall_shelf_coral", () -> new TallCoralBlock(DEAD_TALL_SHELF_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_GREEN)));
    public static final RegistryObject<Block> SHELF_CORAL_WALL_FAN = registerBlockWithoutItem("shelf_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_SHELF_CORAL_WALL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_GREEN)));
    public static final RegistryObject<Block> SHELF_CORAL_FAN = registerBlockWithoutItem("shelf_coral_fan", () -> new CoralFanBlock(DEAD_SHELF_CORAL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_GREEN)));

    // barrel coral
    public static final RegistryObject<Block> DEAD_BARREL_CORAL_BLOCK = registerBlock("dead_barrel_coral_block", () -> new Block(ReefBlockProperties.DEAD_CORAL_BLOCK));
    public static final RegistryObject<Block> DEAD_BARREL_CORAL = registerBlock("dead_barrel_coral", () -> new BaseCoralPlantBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_BARREL_CORAL = registerBlock("dead_tall_barrel_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_BARREL_CORAL_WALL_FAN = registerBlockWithoutItem("dead_barrel_coral_wall_fan", () -> new BaseCoralWallFanBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_BARREL_CORAL_FAN = registerBlockWithoutItem("dead_barrel_coral_fan", () -> new BaseCoralFanBlock(ReefBlockProperties.DEAD_CORAL));

    public static final RegistryObject<Block> BARREL_CORAL_BLOCK = registerBlock("barrel_coral_block", () -> new CoralBlock(DEAD_BARREL_CORAL_BLOCK.get(), ReefBlockProperties.coralBlock(MapColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<Block> BARREL_CORAL = registerBlock("barrel_coral", () -> new CoralPlantBlock(DEAD_BARREL_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<Block> TALL_BARREL_CORAL = registerBlock("tall_barrel_coral", () -> new TallCoralBlock(DEAD_TALL_BARREL_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<Block> BARREL_CORAL_WALL_FAN = registerBlockWithoutItem("barrel_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_BARREL_CORAL_WALL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<Block> BARREL_CORAL_FAN = registerBlockWithoutItem("barrel_coral_fan", () -> new CoralFanBlock(DEAD_BARREL_CORAL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_BLUE)));

    // hand coral
    public static final RegistryObject<Block> DEAD_HAND_CORAL_BLOCK = registerBlock("dead_hand_coral_block", () -> new Block(ReefBlockProperties.DEAD_CORAL_BLOCK));
    public static final RegistryObject<Block> DEAD_HAND_CORAL = registerBlock("dead_hand_coral", () -> new BaseCoralPlantBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_HAND_CORAL = registerBlock("dead_tall_hand_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_HAND_CORAL_WALL_FAN = registerBlockWithoutItem("dead_hand_coral_wall_fan", () -> new BaseCoralWallFanBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_HAND_CORAL_FAN = registerBlockWithoutItem("dead_hand_coral_fan", () -> new BaseCoralFanBlock(ReefBlockProperties.DEAD_CORAL));

    public static final RegistryObject<Block> HAND_CORAL_BLOCK = registerBlock("hand_coral_block", () -> new CoralBlock(DEAD_HAND_CORAL_BLOCK.get(), ReefBlockProperties.coralBlock(MapColor.COLOR_ORANGE)));
    public static final RegistryObject<Block> HAND_CORAL = registerBlock("hand_coral", () -> new CoralPlantBlock(DEAD_HAND_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_ORANGE)));
    public static final RegistryObject<Block> TALL_HAND_CORAL = registerBlock("tall_hand_coral", () -> new TallCoralBlock(DEAD_TALL_HAND_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_ORANGE)));
    public static final RegistryObject<Block> HAND_CORAL_WALL_FAN = registerBlockWithoutItem("hand_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_HAND_CORAL_WALL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_ORANGE)));
    public static final RegistryObject<Block> HAND_CORAL_FAN = registerBlockWithoutItem("hand_coral_fan", () -> new CoralFanBlock(DEAD_HAND_CORAL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_ORANGE)));

    // chimney coral
    public static final RegistryObject<Block> DEAD_CHIMNEY_CORAL_BLOCK = registerBlock("dead_chimney_coral_block", () -> new Block(ReefBlockProperties.DEAD_CORAL_BLOCK));
    public static final RegistryObject<Block> DEAD_CHIMNEY_CORAL = registerBlock("dead_chimney_coral", () -> new BaseCoralPlantBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_CHIMNEY_CORAL = registerBlock("dead_tall_chimney_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_CHIMNEY_CORAL_WALL_FAN = registerBlockWithoutItem("dead_chimney_coral_wall_fan", () -> new BaseCoralWallFanBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_CHIMNEY_CORAL_FAN = registerBlockWithoutItem("dead_chimney_coral_fan", () -> new BaseCoralFanBlock(ReefBlockProperties.DEAD_CORAL));

    public static final RegistryObject<Block> CHIMNEY_CORAL_BLOCK = registerBlock("chimney_coral_block", () -> new CoralBlock(DEAD_CHIMNEY_CORAL_BLOCK.get(), ReefBlockProperties.coralBlock(MapColor.COLOR_BLACK)));
    public static final RegistryObject<Block> CHIMNEY_CORAL = registerBlock("chimney_coral", () -> new CoralPlantBlock(DEAD_CHIMNEY_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_BLACK)));
    public static final RegistryObject<Block> TALL_CHIMNEY_CORAL = registerBlock("tall_chimney_coral", () -> new TallCoralBlock(DEAD_TALL_CHIMNEY_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_BLACK)));
    public static final RegistryObject<Block> CHIMNEY_CORAL_WALL_FAN = registerBlockWithoutItem("chimney_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_CHIMNEY_CORAL_WALL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_BLACK)));
    public static final RegistryObject<Block> CHIMNEY_CORAL_FAN = registerBlockWithoutItem("chimney_coral_fan", () -> new CoralFanBlock(DEAD_CHIMNEY_CORAL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_BLACK)));

    // tower coral
    public static final RegistryObject<Block> DEAD_TOWER_CORAL_BLOCK = registerBlock("dead_tower_coral_block", () -> new Block(ReefBlockProperties.DEAD_CORAL_BLOCK));
    public static final RegistryObject<Block> DEAD_TOWER_CORAL = registerBlock("dead_tower_coral", () -> new BaseCoralPlantBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_TOWER_CORAL = registerBlock("dead_tall_tower_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TOWER_CORAL_WALL_FAN = registerBlockWithoutItem("dead_tower_coral_wall_fan", () -> new BaseCoralWallFanBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TOWER_CORAL_FAN = registerBlockWithoutItem("dead_tower_coral_fan", () -> new BaseCoralFanBlock(ReefBlockProperties.DEAD_CORAL));

    public static final RegistryObject<Block> TOWER_CORAL_BLOCK = registerBlock("tower_coral_block", () -> new CoralBlock(DEAD_TOWER_CORAL_BLOCK.get(), ReefBlockProperties.coralBlock(MapColor.COLOR_PURPLE)));
    public static final RegistryObject<Block> TOWER_CORAL = registerBlock("tower_coral", () -> new CoralPlantBlock(DEAD_TOWER_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_PURPLE)));
    public static final RegistryObject<Block> TALL_TOWER_CORAL = registerBlock("tall_tower_coral", () -> new TallCoralBlock(DEAD_TALL_TOWER_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_PURPLE)));
    public static final RegistryObject<Block> TOWER_CORAL_WALL_FAN = registerBlockWithoutItem("tower_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_TOWER_CORAL_WALL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_PURPLE)));
    public static final RegistryObject<Block> TOWER_CORAL_FAN = registerBlockWithoutItem("tower_coral_fan", () -> new CoralFanBlock(DEAD_TOWER_CORAL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_PURPLE)));

    // rose coral
    public static final RegistryObject<Block> DEAD_ROSE_CORAL_BLOCK = registerBlock("dead_rose_coral_block", () -> new Block(ReefBlockProperties.DEAD_CORAL_BLOCK));
    public static final RegistryObject<Block> DEAD_ROSE_CORAL = registerBlock("dead_rose_coral", () -> new BaseCoralPlantBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_ROSE_CORAL = registerBlock("dead_tall_rose_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_ROSE_CORAL_WALL_FAN = registerBlockWithoutItem("dead_rose_coral_wall_fan", () -> new BaseCoralWallFanBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_ROSE_CORAL_FAN = registerBlockWithoutItem("dead_rose_coral_fan", () -> new BaseCoralFanBlock(ReefBlockProperties.DEAD_CORAL));

    public static final RegistryObject<Block> ROSE_CORAL_BLOCK = registerBlock("rose_coral_block", () -> new CoralBlock(DEAD_ROSE_CORAL_BLOCK.get(), ReefBlockProperties.coralBlock(MapColor.COLOR_RED)));
    public static final RegistryObject<Block> ROSE_CORAL = registerBlock("rose_coral", () -> new CoralPlantBlock(DEAD_ROSE_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_RED)));
    public static final RegistryObject<Block> TALL_ROSE_CORAL = registerBlock("tall_rose_coral", () -> new TallCoralBlock(DEAD_TALL_ROSE_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_RED)));
    public static final RegistryObject<Block> ROSE_CORAL_WALL_FAN = registerBlockWithoutItem("rose_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_ROSE_CORAL_WALL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_RED)));
    public static final RegistryObject<Block> ROSE_CORAL_FAN = registerBlockWithoutItem("rose_coral_fan", () -> new CoralFanBlock(DEAD_ROSE_CORAL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_RED)));

    // flower coral
    public static final RegistryObject<Block> DEAD_FLOWER_CORAL_BLOCK = registerBlock("dead_flower_coral_block", () -> new Block(ReefBlockProperties.DEAD_CORAL_BLOCK));
    public static final RegistryObject<Block> DEAD_FLOWER_CORAL = registerBlock("dead_flower_coral", () -> new BaseCoralPlantBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_FLOWER_CORAL = registerBlock("dead_tall_flower_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_FLOWER_CORAL_WALL_FAN = registerBlockWithoutItem("dead_flower_coral_wall_fan", () -> new BaseCoralWallFanBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_FLOWER_CORAL_FAN = registerBlockWithoutItem("dead_flower_coral_fan", () -> new BaseCoralFanBlock(ReefBlockProperties.DEAD_CORAL));

    public static final RegistryObject<Block> FLOWER_CORAL_BLOCK = registerBlock("flower_coral_block", () -> new CoralBlock(DEAD_FLOWER_CORAL_BLOCK.get(), ReefBlockProperties.coralBlock(MapColor.COLOR_YELLOW)));
    public static final RegistryObject<Block> FLOWER_CORAL = registerBlock("flower_coral", () -> new CoralPlantBlock(DEAD_FLOWER_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_YELLOW)));
    public static final RegistryObject<Block> TALL_FLOWER_CORAL = registerBlock("tall_flower_coral", () -> new TallCoralBlock(DEAD_TALL_FLOWER_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_YELLOW)));
    public static final RegistryObject<Block> FLOWER_CORAL_WALL_FAN = registerBlockWithoutItem("flower_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_FLOWER_CORAL_WALL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_YELLOW)));
    public static final RegistryObject<Block> FLOWER_CORAL_FAN = registerBlockWithoutItem("flower_coral_fan", () -> new CoralFanBlock(DEAD_FLOWER_CORAL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_YELLOW)));

    // ring coral
    public static final RegistryObject<Block> DEAD_RING_CORAL_BLOCK = registerBlock("dead_ring_coral_block", () -> new Block(ReefBlockProperties.DEAD_CORAL_BLOCK));
    public static final RegistryObject<Block> DEAD_RING_CORAL = registerBlock("dead_ring_coral", () -> new BaseCoralPlantBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_RING_CORAL = registerBlock("dead_tall_ring_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_RING_CORAL_WALL_FAN = registerBlockWithoutItem("dead_ring_coral_wall_fan", () -> new BaseCoralWallFanBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_RING_CORAL_FAN = registerBlockWithoutItem("dead_ring_coral_fan", () -> new BaseCoralFanBlock(ReefBlockProperties.DEAD_CORAL));

    public static final RegistryObject<Block> RING_CORAL_BLOCK = registerBlock("ring_coral_block", () -> new CoralBlock(DEAD_RING_CORAL_BLOCK.get(), ReefBlockProperties.coralBlock(MapColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<Block> RING_CORAL = registerBlock("ring_coral", () -> new CoralPlantBlock(DEAD_RING_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<Block> TALL_RING_CORAL = registerBlock("tall_ring_coral", () -> new TallCoralBlock(DEAD_TALL_RING_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<Block> RING_CORAL_WALL_FAN = registerBlockWithoutItem("ring_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_RING_CORAL_WALL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_BLUE)));
    public static final RegistryObject<Block> RING_CORAL_FAN = registerBlockWithoutItem("ring_coral_fan", () -> new CoralFanBlock(DEAD_RING_CORAL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_LIGHT_BLUE)));

    // bush coral
    public static final RegistryObject<Block> DEAD_BUSH_CORAL_BLOCK = registerBlock("dead_bush_coral_block", () -> new Block(ReefBlockProperties.DEAD_CORAL_BLOCK));
    public static final RegistryObject<Block> DEAD_BUSH_CORAL = registerBlock("dead_bush_coral", () -> new BaseCoralPlantBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_BUSH_CORAL = registerBlock("dead_tall_bush_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_BUSH_CORAL_WALL_FAN = registerBlockWithoutItem("dead_bush_coral_wall_fan", () -> new BaseCoralWallFanBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_BUSH_CORAL_FAN = registerBlockWithoutItem("dead_bush_coral_fan", () -> new BaseCoralFanBlock(ReefBlockProperties.DEAD_CORAL));

    public static final RegistryObject<Block> BUSH_CORAL_BLOCK = registerBlock("bush_coral_block", () -> new CoralBlock(DEAD_BUSH_CORAL_BLOCK.get(), ReefBlockProperties.coralBlock(MapColor.COLOR_PURPLE)));
    public static final RegistryObject<Block> BUSH_CORAL = registerBlock("bush_coral", () -> new CoralPlantBlock(DEAD_BUSH_CORAL.get(), ReefBlockProperties.coral(MapColor.COLOR_PURPLE)));
    public static final RegistryObject<Block> TALL_BUSH_CORAL = registerBlock("tall_bush_coral", () -> new TallCoralBlock(DEAD_TALL_BUSH_CORAL.get(), BlockBehaviour.Properties.copy(BUSH_CORAL.get())));
    public static final RegistryObject<Block> BUSH_CORAL_WALL_FAN = registerBlockWithoutItem("bush_coral_wall_fan", () -> new CoralWallFanBlock(DEAD_BUSH_CORAL_WALL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_PURPLE)));
    public static final RegistryObject<Block> BUSH_CORAL_FAN = registerBlockWithoutItem("bush_coral_fan", () -> new CoralFanBlock(DEAD_BUSH_CORAL_FAN.get(), ReefBlockProperties.coral(MapColor.COLOR_PURPLE)));

    // tall coral
    public static final RegistryObject<Block> DEAD_TALL_TUBE_CORAL = registerBlock("dead_tall_tube_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_HORN_CORAL = registerBlock("dead_tall_horn_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_BUBBLE_CORAL = registerBlock("dead_tall_bubble_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_FIRE_CORAL = registerBlock("dead_tall_fire_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));
    public static final RegistryObject<Block> DEAD_TALL_BRAIN_CORAL = registerBlock("dead_tall_brain_coral", () -> new DeadTallCoralBlock(ReefBlockProperties.DEAD_CORAL));

    public static final RegistryObject<Block> TALL_TUBE_CORAL = registerBlock("tall_tube_coral", () -> new TallCoralBlock(DEAD_TALL_TUBE_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.TUBE_CORAL)));
    public static final RegistryObject<Block> TALL_HORN_CORAL = registerBlock("tall_horn_coral", () -> new TallCoralBlock(DEAD_TALL_HORN_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.HORN_CORAL)));
    public static final RegistryObject<Block> TALL_BUBBLE_CORAL = registerBlock("tall_bubble_coral", () -> new TallCoralBlock(DEAD_TALL_BUBBLE_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.BUBBLE_CORAL)));
    public static final RegistryObject<Block> TALL_FIRE_CORAL = registerBlock("tall_fire_coral", () -> new TallCoralBlock(DEAD_TALL_FIRE_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.FIRE_CORAL)));
    public static final RegistryObject<Block> TALL_BRAIN_CORAL = registerBlock("tall_brain_coral", () -> new TallCoralBlock(DEAD_TALL_BRAIN_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL)));

    public static final RegistryObject<Block> FAKE_BUBBLES = registerBlock("fake_bubbles",
            () -> new FakeBubbleBlock(BlockBehaviour.Properties.copy(Blocks.BUBBLE_COLUMN).mapColor(MapColor.WATER).replaceable().noCollission().noLootTable().pushReaction(PushReaction.DESTROY).liquid().sound(SoundType.EMPTY)));

    public static final RegistryObject<Block> FAKE_BUBBLES_RED_SAND = registerBlock("fake_bubbles_red_sand",
            () -> new FakeBubbleBlockRedSand(BlockBehaviour.Properties.copy(Blocks.BUBBLE_COLUMN).mapColor(MapColor.WATER).replaceable().noCollission().noLootTable().pushReaction(PushReaction.DESTROY).liquid().sound(SoundType.EMPTY)));

    public static final RegistryObject<Block> ANGELFISH_CAKE = registerBlock("angelfish_cake", () -> new AngelfishCakeBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));

    public static final RegistryObject<Block> BLUE_PUFFER_LANTERN = registerBlock("blue_puffer_lantern",
            () -> new BasePufferLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)
                    .strength(0.5F, 0.0F)
                    .sound(SoundType.LANTERN)
                    .lightLevel((state) -> 15)
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D)
                            ));

    public static final RegistryObject<Block> GREEN_PUFFER_LANTERN = registerBlock("green_puffer_lantern",
            () -> new BasePufferLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).strength(0.5F, 0.0F).sound(SoundType.LANTERN).lightLevel((p_152677_) -> 15).noOcclusion().pushReaction(PushReaction.DESTROY),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D)
            ));

    public static final RegistryObject<Block> ORANGE_PUFFER_LANTERN = registerBlock("orange_puffer_lantern",
            () -> new BasePufferLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).strength(0.5F, 0.0F).sound(SoundType.LANTERN).lightLevel((p_152677_) -> 15).noOcclusion().pushReaction(PushReaction.DESTROY),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D)
                    ));

    public static final RegistryObject<Block> YELLOW_SEA_ANEMONE = registerBlock("yellow_sea_anemone",
            () -> new AnemoneBlock(0));

    public static final RegistryObject<Block> ORANGE_SEA_ANEMONE = registerBlock("orange_sea_anemone",
            () -> new AnemoneBlock(1));

    public static final RegistryObject<Block> GREEN_SEA_ANEMONE = registerBlock("green_sea_anemone",
            () -> new AnemoneBlock(2));


    public static final RegistryObject<Block> CERULEAN_STARFISH = registerBlock("cerulean_starfish", () ->
            new StarfishBlock(BlockBehaviour.Properties.of().instabreak().noCollission().sound(SoundType.CORAL_BLOCK)) {
                @Override
                public MultifaceSpreader getSpreader() {
                    return null;
                }
            });

    public static final RegistryObject<Block> UMBER_STARFISH = registerBlock("umber_starfish", () ->
            new StarfishBlock(BlockBehaviour.Properties.of().instabreak().noCollission().sound(SoundType.CORAL_BLOCK)) {
                @Override
                public MultifaceSpreader getSpreader() {
                    return null;
                }
            });

    public static final RegistryObject<Block> TANGERINE_STARFISH = registerBlock("tangerine_starfish", () ->
            new StarfishBlock(BlockBehaviour.Properties.of().instabreak().noCollission().sound(SoundType.CORAL_BLOCK)) {
                @Override
                public MultifaceSpreader getSpreader() {
                    return null;
                }
            });

    public static final RegistryObject<Block> CARMINE_STARFISH = registerBlock("carmine_starfish", () ->
            new StarfishBlock(BlockBehaviour.Properties.of().instabreak().noCollission().sound(SoundType.CORAL_BLOCK)) {
                @Override
                public MultifaceSpreader getSpreader() {
                    return null;
                }
            });

    public static final RegistryObject<Block> FUCHSIA_STARFISH = registerBlock("fuchsia_starfish", () ->
            new StarfishBlock(BlockBehaviour.Properties.of().instabreak().noCollission().sound(SoundType.CORAL_BLOCK)) {
                @Override
                public MultifaceSpreader getSpreader() {
                    return null;
                }
            });

    public static final RegistryObject<Block> SAFFRON_STARFISH = registerBlock("saffron_starfish", () ->
            new StarfishBlock(BlockBehaviour.Properties.of().instabreak().noCollission().sound(SoundType.CORAL_BLOCK)) {
                @Override
                public MultifaceSpreader getSpreader() {
                    return null;
                }
            });

    public static final RegistryObject<Block> CHARTREUSE_STARFISH = registerBlock("chartreuse_starfish", () ->
            new StarfishBlock(BlockBehaviour.Properties.of().instabreak().noCollission().sound(SoundType.CORAL_BLOCK)) {
                @Override
                public MultifaceSpreader getSpreader() {
                    return null;
                }
            });
    public static final RegistryObject<Block> VIOLET_STARFISH = registerBlock("violet_starfish", () ->
            new StarfishBlock(BlockBehaviour.Properties.of().instabreak().noCollission().sound(SoundType.CORAL_BLOCK)) {
                @Override
                public MultifaceSpreader getSpreader() {
                    return null;
                }
            });

    private static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        ReefItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        AUTO_TRANSLATE.add(block);
        return block;
    }

    private static <B extends Block> RegistryObject<B> registerBlockNoLang(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        ReefItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

    private static <B extends Block> RegistryObject<B> registerBlockWithoutItem(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        AUTO_TRANSLATE.add(block);
        return block;
    }

    private static <B extends Block> RegistryObject<B> registerBlockWithoutItemNoLang(String name, Supplier<? extends B> supplier) {
        return BLOCKS.register(name, supplier);
    }

    private static <B extends Block> Supplier<B> registerBlockWithItem(String name, Supplier<B> block, Function<Supplier<B>, Item> item) {
        Supplier<B> entry = registerBlockWithoutItem(name, block);
        ReefItems.ITEMS.register(name, () -> item.apply(entry));
        return entry;
    }
}

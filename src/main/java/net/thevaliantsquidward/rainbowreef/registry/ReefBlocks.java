package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
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

import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.item.Items.registerBlock;

public class ReefBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS,
            RainbowReef.MOD_ID);


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

    public static final RegistryObject<Block> DEAD_SHELF_CORAL_BLOCK = registerBlock("dead_shelf_coral_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SHELF_CORAL_BLOCK = registerBlock("shelf_coral_block", () ->
            new CoralBlock(DEAD_SHELF_CORAL_BLOCK.get(), BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK)));

    public static final RegistryObject<Block> DEAD_SHELF_CORAL = registerBlock("dead_shelf_coral", () ->
            new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> SHELF_CORAL = registerBlock("shelf_coral", () ->
            new CoralPlantBlock(DEAD_SHELF_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.LILY_PAD).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> DEAD_BARREL_CORAL = registerBlock("dead_barrel_coral", () ->
            new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> BARREL_CORAL = registerBlock("barrel_coral", () ->
            new CoralPlantBlock(DEAD_BARREL_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.LILY_PAD).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> DEAD_HAND_CORAL = registerBlock("dead_hand_coral", () ->
            new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> HAND_CORAL = registerBlock("hand_coral", () ->
            new CoralPlantBlock(DEAD_HAND_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.LILY_PAD).noCollission().instabreak().sound(SoundType.WET_GRASS)));
    public static final RegistryObject<Block> DEAD_CHIMNEY_CORAL = registerBlock("dead_chimney_coral", () ->
            new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> CHIMNEY_CORAL = registerBlock("chimney_coral", () ->
            new CoralPlantBlock(DEAD_CHIMNEY_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.LILY_PAD).noCollission().instabreak().sound(SoundType.WET_GRASS)));
    public static final RegistryObject<Block> DEAD_TOWER_CORAL = registerBlock("dead_tower_coral", () ->
            new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> TOWER_CORAL = registerBlock("tower_coral", () ->
            new CoralPlantBlock(DEAD_TOWER_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.LILY_PAD).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> DEAD_ROSE_CORAL = registerBlock("dead_rose_coral", () ->
            new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> ROSE_CORAL = registerBlock("rose_coral", () ->
            new CoralPlantBlock(DEAD_ROSE_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.LILY_PAD).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> DEAD_FLOWER_CORAL = registerBlock("dead_flower_coral", () ->
            new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> FLOWER_CORAL = registerBlock("flower_coral", () ->
            new CoralPlantBlock(DEAD_FLOWER_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.LILY_PAD).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> DEAD_RING_CORAL = registerBlock("dead_ring_coral", () ->
            new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> RING_CORAL = registerBlock("ring_coral", () ->
            new CoralPlantBlock(DEAD_RING_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.LILY_PAD).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> DEAD_BUSH_CORAL = registerBlock("dead_bush_coral", () ->
            new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> BUSH_CORAL = registerBlock("bush_coral", () ->
            new CoralPlantBlock(DEAD_BUSH_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.LILY_PAD).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> DEAD_BARREL_CORAL_BLOCK = registerBlock("dead_barrel_coral_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> BARREL_CORAL_BLOCK = registerBlock("barrel_coral_block", () ->
            new CoralBlock(DEAD_BARREL_CORAL_BLOCK.get(), BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK)));

    public static final RegistryObject<Block> DEAD_HAND_CORAL_BLOCK = registerBlock("dead_hand_coral_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> HAND_CORAL_BLOCK = registerBlock("hand_coral_block", () ->
            new CoralBlock(DEAD_HAND_CORAL_BLOCK.get(), BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK)));
    public static final RegistryObject<Block> DEAD_TOWER_CORAL_BLOCK = registerBlock("dead_tower_coral_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> TOWER_CORAL_BLOCK = registerBlock("tower_coral_block", () ->
            new CoralBlock(DEAD_TOWER_CORAL_BLOCK.get(), BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK)));
    public static final RegistryObject<Block> DEAD_CHIMNEY_CORAL_BLOCK = registerBlock("dead_chimney_coral_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> CHIMNEY_CORAL_BLOCK = registerBlock("chimney_coral_block", () ->
            new CoralBlock(DEAD_CHIMNEY_CORAL_BLOCK.get(), BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK)));

    public static final RegistryObject<Block> DEAD_ROSE_CORAL_BLOCK = registerBlock("dead_rose_coral_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> ROSE_CORAL_BLOCK = registerBlock("rose_coral_block", () ->
            new CoralBlock(DEAD_ROSE_CORAL_BLOCK.get(), BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK)));

    public static final RegistryObject<Block> DEAD_FLOWER_CORAL_BLOCK = registerBlock("dead_flower_coral_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> FLOWER_CORAL_BLOCK = registerBlock("flower_coral_block", () ->
            new CoralBlock(DEAD_FLOWER_CORAL_BLOCK.get(), BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK)));

    public static final RegistryObject<Block> DEAD_RING_CORAL_BLOCK = registerBlock("dead_ring_coral_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> RING_CORAL_BLOCK = registerBlock("ring_coral_block", () ->
            new CoralBlock(DEAD_RING_CORAL_BLOCK.get(), BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK)));

    public static final RegistryObject<Block> DEAD_BUSH_CORAL_BLOCK = registerBlock("dead_bush_coral_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> BUSH_CORAL_BLOCK = registerBlock("bush_coral_block", () ->
            new CoralBlock(DEAD_BUSH_CORAL_BLOCK.get(), BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK)));

    public static final RegistryObject<Block> DEAD_BARREL_CORAL_FAN = BLOCKS.register("dead_barrel_coral_fan", () ->
          new BaseCoralFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_FAN).requiresCorrectToolForDrops().noCollission().instabreak()));

  public static final RegistryObject<Block> DEAD_BARREL_CORAL_WALL_FAN = BLOCKS.register("dead_barrel_coral_wall_fan", () ->
          new BaseCoralWallFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(DEAD_BARREL_CORAL_FAN.get())));

  public static final RegistryObject<Block> BARREL_CORAL_FAN = BLOCKS.register("barrel_coral_fan", () ->
          new CoralFanBlock(DEAD_BARREL_CORAL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS)));

  public static final RegistryObject<Block> BARREL_CORAL_WALL_FAN = BLOCKS.register("barrel_coral_wall_fan", () ->
          new CoralWallFanBlock(DEAD_BARREL_CORAL_WALL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(BARREL_CORAL_FAN.get())));

    public static final RegistryObject<Block> DEAD_CHIMNEY_CORAL_FAN = BLOCKS.register("dead_chimney_coral_fan", () ->
            new BaseCoralFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_FAN).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> DEAD_CHIMNEY_CORAL_WALL_FAN = BLOCKS.register("dead_chimney_coral_wall_fan", () ->
            new BaseCoralWallFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(DEAD_CHIMNEY_CORAL_FAN.get())));

    public static final RegistryObject<Block> CHIMNEY_CORAL_FAN = BLOCKS.register("chimney_coral_fan", () ->
            new CoralFanBlock(DEAD_CHIMNEY_CORAL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> CHIMNEY_CORAL_WALL_FAN = BLOCKS.register("chimney_coral_wall_fan", () ->
            new CoralWallFanBlock(DEAD_CHIMNEY_CORAL_WALL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(CHIMNEY_CORAL_FAN.get())));

    public static final RegistryObject<Block> DEAD_SHELF_CORAL_FAN = BLOCKS.register("dead_shelf_coral_fan", () ->
            new BaseCoralFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_FAN).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> DEAD_SHELF_CORAL_WALL_FAN = BLOCKS.register("dead_shelf_coral_wall_fan", () ->
            new BaseCoralWallFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(DEAD_SHELF_CORAL_FAN.get())));

    public static final RegistryObject<Block> SHELF_CORAL_FAN = BLOCKS.register("shelf_coral_fan", () ->
            new CoralFanBlock(DEAD_SHELF_CORAL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> SHELF_CORAL_WALL_FAN = BLOCKS.register("shelf_coral_wall_fan", () ->
            new CoralWallFanBlock(DEAD_SHELF_CORAL_WALL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(SHELF_CORAL_FAN.get())));

    public static final RegistryObject<Block> DEAD_HAND_CORAL_FAN = BLOCKS.register("dead_hand_coral_fan", () ->
            new BaseCoralFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_FAN).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> DEAD_HAND_CORAL_WALL_FAN = BLOCKS.register("dead_hand_coral_wall_fan", () ->
            new BaseCoralWallFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(DEAD_HAND_CORAL_FAN.get())));

    public static final RegistryObject<Block> HAND_CORAL_FAN = BLOCKS.register("hand_coral_fan", () ->
            new CoralFanBlock(DEAD_HAND_CORAL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> HAND_CORAL_WALL_FAN = BLOCKS.register("hand_coral_wall_fan", () ->
            new CoralWallFanBlock(DEAD_HAND_CORAL_WALL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(HAND_CORAL_FAN.get())));

    public static final RegistryObject<Block> DEAD_TOWER_CORAL_FAN = BLOCKS.register("dead_tower_coral_fan", () ->
            new BaseCoralFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_FAN).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> DEAD_TOWER_CORAL_WALL_FAN = BLOCKS.register("dead_tower_coral_wall_fan", () ->
            new BaseCoralWallFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(DEAD_TOWER_CORAL_FAN.get())));

    public static final RegistryObject<Block> TOWER_CORAL_FAN = BLOCKS.register("tower_coral_fan", () ->
            new CoralFanBlock(DEAD_TOWER_CORAL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> TOWER_CORAL_WALL_FAN = BLOCKS.register("tower_coral_wall_fan", () ->
            new CoralWallFanBlock(DEAD_TOWER_CORAL_WALL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(TOWER_CORAL_FAN.get())));

    public static final RegistryObject<Block> DEAD_ROSE_CORAL_FAN = BLOCKS.register("dead_rose_coral_fan", () ->
            new BaseCoralFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_FAN).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> DEAD_ROSE_CORAL_WALL_FAN = BLOCKS.register("dead_rose_coral_wall_fan", () ->
            new BaseCoralWallFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(DEAD_ROSE_CORAL_FAN.get())));

    public static final RegistryObject<Block> ROSE_CORAL_FAN = BLOCKS.register("rose_coral_fan", () ->
            new CoralFanBlock(DEAD_ROSE_CORAL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> ROSE_CORAL_WALL_FAN = BLOCKS.register("rose_coral_wall_fan", () ->
            new CoralWallFanBlock(DEAD_ROSE_CORAL_WALL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(ROSE_CORAL_FAN.get())));

    public static final RegistryObject<Block> DEAD_FLOWER_CORAL_FAN = BLOCKS.register("dead_flower_coral_fan", () ->
            new BaseCoralFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_FAN).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> DEAD_FLOWER_CORAL_WALL_FAN = BLOCKS.register("dead_flower_coral_wall_fan", () ->
            new BaseCoralWallFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(DEAD_FLOWER_CORAL_FAN.get())));

    public static final RegistryObject<Block> FLOWER_CORAL_FAN = BLOCKS.register("flower_coral_fan", () ->
            new CoralFanBlock(DEAD_FLOWER_CORAL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> FLOWER_CORAL_WALL_FAN = BLOCKS.register("flower_coral_wall_fan", () ->
            new CoralWallFanBlock(DEAD_FLOWER_CORAL_WALL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(FLOWER_CORAL_FAN.get())));

    public static final RegistryObject<Block> DEAD_RING_CORAL_FAN = BLOCKS.register("dead_ring_coral_fan", () ->
            new BaseCoralFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_FAN).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> DEAD_RING_CORAL_WALL_FAN = BLOCKS.register("dead_ring_coral_wall_fan", () ->
            new BaseCoralWallFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(DEAD_RING_CORAL_FAN.get())));

    public static final RegistryObject<Block> RING_CORAL_FAN = BLOCKS.register("ring_coral_fan", () ->
            new CoralFanBlock(DEAD_RING_CORAL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> RING_CORAL_WALL_FAN = BLOCKS.register("ring_coral_wall_fan", () ->
            new CoralWallFanBlock(DEAD_RING_CORAL_WALL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(RING_CORAL_FAN.get())));

    public static final RegistryObject<Block> DEAD_BUSH_CORAL_FAN = BLOCKS.register("dead_bush_coral_fan", () ->
            new BaseCoralFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_FAN).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> DEAD_BUSH_CORAL_WALL_FAN = BLOCKS.register("dead_bush_coral_wall_fan", () ->
            new BaseCoralWallFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(DEAD_BUSH_CORAL_FAN.get())));

    public static final RegistryObject<Block> BUSH_CORAL_FAN = BLOCKS.register("bush_coral_fan", () ->
            new CoralFanBlock(DEAD_BUSH_CORAL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> BUSH_CORAL_WALL_FAN = BLOCKS.register("bush_coral_wall_fan", () ->
            new CoralWallFanBlock(DEAD_BUSH_CORAL_WALL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(BUSH_CORAL_FAN.get())));


    public static final RegistryObject<Block> FAKE_BUBBLES = registerBlock("fake_bubbles",
            () -> new FakeBubbleBlock(BlockBehaviour.Properties.copy(Blocks.BUBBLE_COLUMN).mapColor(MapColor.WATER).replaceable().noCollission().noLootTable().pushReaction(PushReaction.DESTROY).liquid().sound(SoundType.EMPTY)));

    public static final RegistryObject<Block> FAKE_BUBBLES_RED_SAND = registerBlock("fake_bubbles_red_sand",
            () -> new FakeBubbleBlockRedSand(BlockBehaviour.Properties.copy(Blocks.BUBBLE_COLUMN).mapColor(MapColor.WATER).replaceable().noCollission().noLootTable().pushReaction(PushReaction.DESTROY).liquid().sound(SoundType.EMPTY)));

    public static final RegistryObject<Block> ANGELFISH_CAKE = registerBlock("angelfish_cake", () -> new AngelfishCakeBlock(BlockBehaviour.Properties.copy(Blocks.CAKE)));

    public static final RegistryObject<Block> BLUE_PUFFER_LANTERN = registerBlock("blue_puffer_lantern",
            () -> new BasePufferLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)
                    .strength(0.5F, 0.0F)
                    .sound(SoundType.LANTERN)
                    .lightLevel((p_152677_) -> {return 15;})
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D)
                            ));

    public static final RegistryObject<Block> GREEN_PUFFER_LANTERN = registerBlock("green_puffer_lantern",
            () -> new BasePufferLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).strength(0.5F, 0.0F).sound(SoundType.LANTERN).lightLevel((p_152677_) -> {
                return 15;
            }).noOcclusion().pushReaction(PushReaction.DESTROY),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D),
                    Block.box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D)
            ));

    public static final RegistryObject<Block> ORANGE_PUFFER_LANTERN = registerBlock("orange_puffer_lantern",
            () -> new BasePufferLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).strength(0.5F, 0.0F).sound(SoundType.LANTERN).lightLevel((p_152677_) -> {
                return 15;
            }).noOcclusion().pushReaction(PushReaction.DESTROY),
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


    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        ReefItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
    private static <T extends Block> Supplier<T> create(String key, Supplier<T> block, Function<Supplier<T>, Item> item) {
        Supplier<T> entry = create(key, block);
        ReefItems.ITEMS.register(key, () -> item.apply(entry));
        return entry;
    }

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> Supplier<T> create(String key, Supplier<T> block, CreativeModeTab tab) {
        return create(key, block, entry -> new BlockItem(entry.get(), new Item.Properties()));
    }



    private static <T extends Block> Supplier<T> create(String key, Supplier<T> block) {
        return BLOCKS.register(key, block);
    }
}

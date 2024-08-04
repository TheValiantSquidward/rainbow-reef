package net.thevaliantsquidward.rainbowreef.block;

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
import net.thevaliantsquidward.rainbowreef.block.custom.*;
import net.thevaliantsquidward.rainbowreef.item.ModItems;

import java.util.function.Function;
import java.util.function.Supplier;

import static net.minecraft.world.item.Items.registerBlock;

public class ModBlocks {
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

    public static final RegistryObject<Block> DEAD_SHELF_CORAL_BLOCK = registerBlock("dead_shelf_coral_block", () ->
            new Block(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F)));

    public static final RegistryObject<Block> SHELF_CORAL_BLOCK = registerBlock("shelf_coral_block", () ->
            new CoralBlock(DEAD_SHELF_CORAL_BLOCK.get(), BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK)));

    public static final RegistryObject<Block> DEAD_SHELF_CORAL = registerBlock("dead_shelf_coral", () ->
            new BaseCoralPlantBlock(BlockBehaviour.Properties.copy(Blocks.STONE).requiresCorrectToolForDrops().noCollission().instabreak()));

    public static final RegistryObject<Block> SHELF_CORAL = registerBlock("shelf_coral", () ->
            new CoralPlantBlock(DEAD_SHELF_CORAL.get(), BlockBehaviour.Properties.copy(Blocks.LILY_PAD).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> DEAD_SHELF_CORAL_FAN = BLOCKS.register("dead_shelf_coral_fan", () ->
            new BaseCoralFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_FAN).requiresCorrectToolForDrops().noCollission().instabreak()));

 public static final RegistryObject<Block> DEAD_SHELF_CORAL_WALL_FAN = BLOCKS.register("dead_shelf_coral_wall_fan", () ->
         new BaseCoralWallFanBlock(BlockBehaviour.Properties.copy(Blocks.DEAD_BRAIN_CORAL_WALL_FAN).requiresCorrectToolForDrops().noCollission().instabreak().dropsLike(DEAD_SHELF_CORAL_FAN.get())));

    public static final RegistryObject<Block> SHELF_CORAL_FAN = BLOCKS.register("shelf_coral_fan", () ->
            new CoralFanBlock(DEAD_SHELF_CORAL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS)));

    public static final RegistryObject<Block> SHELF_CORAL_WALL_FAN = BLOCKS.register("shelf_coral_wall_fan", () ->
            new CoralWallFanBlock(DEAD_SHELF_CORAL_WALL_FAN.get(), BlockBehaviour.Properties.copy(Blocks.BRAIN_CORAL_WALL_FAN).noCollission().instabreak().sound(SoundType.WET_GRASS).dropsLike(SHELF_CORAL_FAN.get())));


    public static final RegistryObject<Block> FAKE_BUBBLES = registerBlock("fake_bubbles",
            () -> new FakeBubbleBlock(BlockBehaviour.Properties.copy(Blocks.BUBBLE_COLUMN).mapColor(MapColor.WATER).replaceable().noCollission().noLootTable().pushReaction(PushReaction.DESTROY).liquid().sound(SoundType.EMPTY)));


    public static final RegistryObject<Block> BLUE_PUFFER_LANTERN = registerBlock("blue_puffer_lantern",
            () -> new BasePufferLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN)
                    .strength(1.0F, 0.0F)
                    .sound(SoundType.WOOL)
                    .lightLevel((p_152677_) -> {return 15;})
                    .noOcclusion()
                    .pushReaction(PushReaction.DESTROY),
                    Block.box(3.0D, 2.0D, 3.0D, 13.0D, 14.0D, 13.0D)
                            ));

    public static final RegistryObject<Block> GREEN_PUFFER_LANTERN = registerBlock("green_puffer_lantern",
            () -> new BasePufferLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).strength(1.0F, 0.0F).sound(SoundType.WOOL).lightLevel((p_152677_) -> {
                return 15;
            }).noOcclusion().pushReaction(PushReaction.DESTROY),
                    Block.box(3.0D, 2.0D, 3.0D, 13.0D, 14.0D, 13.0D)
            ));

    public static final RegistryObject<Block> ORANGE_PUFFER_LANTERN = registerBlock("orange_puffer_lantern",
            () -> new BasePufferLanternBlock(BlockBehaviour.Properties.copy(Blocks.LANTERN).strength(1.0F, 0.0F).sound(SoundType.WOOL).lightLevel((p_152677_) -> {
                return 15;
            }).noOcclusion().pushReaction(PushReaction.DESTROY),
                    Block.box(3.0D, 2.0D, 3.0D, 13.0D, 14.0D, 13.0D)
                    ));



    public static <B extends Block> RegistryObject<B> registerBlock(String name, Supplier<? extends B> supplier) {
        RegistryObject<B> block = BLOCKS.register(name, supplier);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
    private static <T extends Block> Supplier<T> create(String key, Supplier<T> block, Function<Supplier<T>, Item> item) {
        Supplier<T> entry = create(key, block);
        ModItems.ITEMS.register(key, () -> item.apply(entry));
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

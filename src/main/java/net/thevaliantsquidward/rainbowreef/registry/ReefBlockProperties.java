package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

public class ReefBlockProperties {

    public static final BlockBehaviour.Properties DEAD_CORAL_BLOCK = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().strength(1.5F, 6.0F);
    public static final BlockBehaviour.Properties DEAD_CORAL = BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_GRAY).forceSolidOn().instrument(NoteBlockInstrument.BASEDRUM).requiresCorrectToolForDrops().noCollission().instabreak();

    public static BlockBehaviour.Properties coral(MapColor color) {
        return BlockBehaviour.Properties.of().mapColor(color).noCollission().instabreak().sound(SoundType.WET_GRASS).pushReaction(PushReaction.DESTROY);
    }

    public static BlockBehaviour.Properties coralBlock(MapColor color) {
        return BlockBehaviour.Properties.of().mapColor(color).requiresCorrectToolForDrops().strength(1.5F, 6.0F).sound(SoundType.CORAL_BLOCK);
    }
}

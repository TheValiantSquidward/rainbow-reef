package com.valiantenvoy.rainbow_reef.utils;

import com.valiantenvoy.rainbow_reef.network.ParticlePacket;
import com.valiantenvoy.rainbow_reef.registry.ReefParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.ChunkPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.neoforge.network.PacketDistributor;

public class ReefParticleUtils {

    public static void spawnSingleChestBubbles(Level level, BlockPos pos, double x, double y, double z) {
        spawnChestBubbles((ServerLevel) level, pos, 8, x, y, z, 0.5D, 0.5D);
    }

    public static void spawnDoubleChestBubbles(Level level, BlockPos pos, BlockState state, double x, double y, double z) {
        double xRange = 0.5;
        double zRange = 0.5;
        if (state.getValue(ChestBlock.FACING).getAxis() == Direction.Axis.X) {
            zRange += 1;
        } else {
            xRange += 1;
        }
        spawnChestBubbles((ServerLevel) level, pos, 16, x, y, z, xRange, zRange);
    }

    public static void spawnChestBubbles(ServerLevel level, BlockPos pos, int numParticles, double x, double y, double z, double xRange, double zRange) {
        ParticlePacket particlePacket = new ParticlePacket();
        for (int i = 0; i < numParticles; i++) {
            double xPos = x + (level.getRandom().nextDouble() - 0.5D) * xRange;
            double yPos = y + level.getRandom().nextDouble();
            double zPos = z + (level.getRandom().nextDouble() - 0.5D) * zRange;
            double speed = 0.2D + level.getRandom().nextDouble() * 0.6D;
            particlePacket.queueParticle(ReefParticleTypes.BURROW_BUBBLE.get(), xPos, yPos, zPos, 0, speed, 0);
        }
        PacketDistributor.sendToPlayersTrackingChunk(level, new ChunkPos(pos), particlePacket);
        level.playSound(null, x, y, z, SoundEvents.BUBBLE_COLUMN_UPWARDS_AMBIENT, SoundSource.BLOCKS, 0.6F + level.getRandom().nextFloat() * 0.4F, 0.9F + level.getRandom().nextFloat() * 0.15F);
    }
}

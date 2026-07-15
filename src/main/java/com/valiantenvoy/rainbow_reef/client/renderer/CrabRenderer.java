package com.valiantenvoy.rainbow_reef.client.renderer;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import com.valiantenvoy.rainbow_reef.client.models.entity.CrabModel;
import com.valiantenvoy.rainbow_reef.entity.Crab;
import com.valiantenvoy.rainbow_reef.registry.ReefModelLayers;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class CrabRenderer extends MobRenderer<Crab, CrabModel> {

    private static final ResourceLocation VAMPIRE = RainbowReef.location("textures/entity/crab/vampire.png");
    private static final ResourceLocation HALLOWEEN = RainbowReef.location("textures/entity/crab/halloween.png");
    private static final ResourceLocation GHOST = RainbowReef.location("textures/entity/crab/ghost.png");
    private static final ResourceLocation SALLY = RainbowReef.location("textures/entity/crab/sally.png");
    private static final ResourceLocation EMERALD = RainbowReef.location("textures/entity/crab/emerald.png");
    private static final ResourceLocation BLUE = RainbowReef.location("textures/entity/crab/bluecrab.png");
    private static final ResourceLocation PURPLE = RainbowReef.location("textures/entity/crab/purplecrab.png");
    private static final ResourceLocation CANDY = RainbowReef.location("textures/entity/crab/candycrab.png");
    private static final ResourceLocation REDGHOST = RainbowReef.location("textures/entity/crab/redghost.png");

    public CrabRenderer(EntityRendererProvider.Context context) {
        super(context, new CrabModel(context.bakeLayer(ReefModelLayers.CRAB)), 0.3F);
    }

    public @NotNull ResourceLocation getTextureLocation(Crab entity) {
        return switch (entity.getVariant()) {
            case 1 -> HALLOWEEN;
            case 2 -> GHOST;
            case 3 -> SALLY;
            case 4 -> EMERALD;
            case 5 -> BLUE;
            case 6 -> PURPLE;
            case 7 -> CANDY;
            case 8 -> REDGHOST;
            default -> VAMPIRE;
        };
    }
}
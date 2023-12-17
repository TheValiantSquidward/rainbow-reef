package net.thevaliantsquidward.rainbowreef.entity.client.renders;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.entity.client.models.DwarfAngelfishModel;
import net.thevaliantsquidward.rainbowreef.entity.custom.DwarfAngelfishEntity;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class DwarfAngelfishRenderer extends GeoEntityRenderer<DwarfAngelfishEntity> {
    private static final ResourceLocation BICOLOR = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/bicolor.png");
    private static final ResourceLocation CORAL_BEAUTY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/coral_beauty.png");
    private static final ResourceLocation CANDY_CANE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/cand_cane.png");
    private static final ResourceLocation FLAME = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/flame.png");
    private static final ResourceLocation SPOTTED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/spotted.png");


    public DwarfAngelfishRenderer(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new DwarfAngelfishModel());
    }

    protected void scale(DwarfAngelfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(DwarfAngelfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> CORAL_BEAUTY;
            case 2 -> CANDY_CANE;
            case 3 -> FLAME;
            case 4 -> SPOTTED;
            default -> BICOLOR;
        };
    }
}
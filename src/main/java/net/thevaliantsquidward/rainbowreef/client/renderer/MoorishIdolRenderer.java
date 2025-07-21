package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.ClownfishModel;
import net.thevaliantsquidward.rainbowreef.client.models.entity.EelModel;
import net.thevaliantsquidward.rainbowreef.client.models.entity.MoorishIdolModel;
import net.thevaliantsquidward.rainbowreef.entity.EelEntity;
import net.thevaliantsquidward.rainbowreef.entity.MoorishIdolEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class MoorishIdolRenderer extends MobRenderer<MoorishIdolEntity, MoorishIdolModel<MoorishIdolEntity>> {
    private static final ResourceLocation DEFAULT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/moorishidol/defaultmoorishidol.png");
    private static final ResourceLocation SILVER = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/moorishidol/silvermoorishidol.png");



    public MoorishIdolRenderer(EntityRendererProvider.Context context) {
        super(context, new MoorishIdolModel<>(context.bakeLayer(ReefModelLayers.IDOL_LAYER)), 0.4F);
    }

    protected void scale(MoorishIdolEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(MoorishIdolEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> SILVER;
            default -> DEFAULT;
        };
    }
}
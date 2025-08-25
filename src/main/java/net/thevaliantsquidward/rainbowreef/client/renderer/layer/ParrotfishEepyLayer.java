package net.thevaliantsquidward.rainbowreef.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.ParrotfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Parrotfish;

public class ParrotfishEepyLayer extends RenderLayer<Parrotfish, ParrotfishModel<Parrotfish>> {

    public ParrotfishEepyLayer(RenderLayerParent<Parrotfish, ParrotfishModel<Parrotfish>> renderer) {
        super(renderer);
    }

    public ResourceLocation eepyTextures(Parrotfish entity) {
        return new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/parroteepy/parrotfish_sleepy_" + entity.getVariant() + ".png");
    }

    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, Parrotfish entity, float pLimbSwing, float pLimbSwingAmount, float pPartialTicks, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        long roundedTime = entity.level().getDayTime() % 24000;
        boolean night = roundedTime >= 13000 && roundedTime <= 22000;
        if (!entity.isInvisible() && night && entity.isInWaterOrBubble()) {
            ResourceLocation resourceLocation = eepyTextures(entity);
            renderColoredCutoutModel(this.getParentModel(), resourceLocation, pPoseStack, pBuffer, pPackedLight, entity, 1.0F, 1.0F, 1.0F);
        }
    }
}
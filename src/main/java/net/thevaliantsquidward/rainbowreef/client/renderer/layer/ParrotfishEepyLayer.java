package net.thevaliantsquidward.rainbowreef.client.renderer.layer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.ParrotfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Parrotfish;

@OnlyIn(Dist.CLIENT)
public class ParrotfishEepyLayer extends RenderLayer<Parrotfish, ParrotfishModel> {

    public ParrotfishEepyLayer(RenderLayerParent<Parrotfish, ParrotfishModel> renderer) {
        super(renderer);
    }

    public ResourceLocation eepyTextures(Parrotfish entity) {
        Parrotfish.ParrotfishVariant parrotfishVariant = Parrotfish.ParrotfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/parrotfish/sleepy_" + parrotfishVariant.getSerializedName() + ".png");
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int packedLight, Parrotfish entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        long roundedTime = entity.level().getDayTime() % 24000;
        boolean night = roundedTime >= 13000 && roundedTime <= 22000;
        if (!entity.isInvisible() && night && entity.isInWaterOrBubble()) {
            ResourceLocation resourceLocation = eepyTextures(entity);
            renderColoredCutoutModel(this.getParentModel(), resourceLocation, poseStack, buffer, packedLight, entity, 1.0F, 1.0F, 1.0F);
        }
    }
}
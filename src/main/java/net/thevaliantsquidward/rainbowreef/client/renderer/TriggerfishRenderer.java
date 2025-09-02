package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.TriggerfishModel;
import net.thevaliantsquidward.rainbowreef.entity.Triggerfish;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class TriggerfishRenderer extends MobRenderer<Triggerfish, TriggerfishModel> {

    public TriggerfishRenderer(EntityRendererProvider.Context context) {
        super(context, new TriggerfishModel(context.bakeLayer(ReefModelLayers.TRIGGERFISH)), 0.3F);
    }

    public ResourceLocation getTextureLocation(Triggerfish entity) {
        Triggerfish.TriggerfishVariant triggerfishVariant = Triggerfish.TriggerfishVariant.getVariantId(entity.getVariant());
        return new ResourceLocation(RainbowReef.MOD_ID,"textures/entity/triggerfish/" + triggerfishVariant.getSerializedName() + ".png");
    }
}

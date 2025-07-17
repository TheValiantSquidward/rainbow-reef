package net.thevaliantsquidward.rainbowreef.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.ArrowCrabModel;
import net.thevaliantsquidward.rainbowreef.client.models.entity.BoxfishModel;
import net.thevaliantsquidward.rainbowreef.entity.BoxfishEntity;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

@OnlyIn(Dist.CLIENT)
public class BoxfishRenderer extends MobRenderer<BoxfishEntity, BoxfishModel<BoxfishEntity>> {
    private static final ResourceLocation TEXTURE_GOLD = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/yellowboxfish.png");
    private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/whitespottedboxfish.png");
    private static final ResourceLocation TEXTURE_STRIPE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/whitebarredboxfish.png");
    private static final ResourceLocation TEXTURE_WHITE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/hornnosedboxfish.png");
    private static final ResourceLocation TEXTURE_BLUETAIL = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/bluetail.png");
    private static final ResourceLocation TEXTURE_LONGHORN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/longhorn.png");
    private static final ResourceLocation TEXTURE_WHITLEYS = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/whitleys.png");
    private static final ResourceLocation TEXTURE_SPOTTED = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/boxfish/spotted.png");


    public BoxfishRenderer(EntityRendererProvider.Context context) {
        super(context, new BoxfishModel<>(context.bakeLayer(ReefModelLayers.BOXFISH_LAYER)), 0.4F);
    }

    protected void scale(BoxfishEntity entitylivingbaseIn, PoseStack matrixStackIn, float partialTickTime) {
    }


    public ResourceLocation getTextureLocation(BoxfishEntity entity) {
        return switch (entity.getVariant()) {
            case 1 -> TEXTURE_PURPLE;
            case 2 -> TEXTURE_STRIPE;
            case 3 -> TEXTURE_WHITE;
            case 4 -> TEXTURE_BLUETAIL;
            case 5 -> TEXTURE_LONGHORN;
            case 6 -> TEXTURE_WHITLEYS;
            case 7 -> TEXTURE_SPOTTED;
            default -> TEXTURE_GOLD;
        };
    }
}
package net.thevaliantsquidward.rainbowreef.client.renderer;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;
import net.thevaliantsquidward.rainbowreef.client.models.entity.TangModel;
import net.thevaliantsquidward.rainbowreef.entity.Tang;
import net.thevaliantsquidward.rainbowreef.registry.ReefModelLayers;

@OnlyIn(Dist.CLIENT)
public class TangRenderer extends MobRenderer<Tang, TangModel<Tang>> {

    private static final ResourceLocation TEXTURE_BLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/blue.png");
    private static final ResourceLocation TEXTURE_POWDERBLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/powderblue.png");
    private static final ResourceLocation TEXTURE_YELLOW = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/yellow.png");
    private static final ResourceLocation TEXTURE_UNICORN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/unicorn.png");
    private static final ResourceLocation TEXTURE_CONVICT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/convict.png");
    private static final ResourceLocation TEXTURE_CLOWN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/clown.png");
    private static final ResourceLocation TEXTURE_ACHILLES = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/achilles.png");
    private static final ResourceLocation TEXTURE_YELLOW_BELLY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/yellowbelly.png");
    private static final ResourceLocation TEXTURE_MUDDY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/muddy.png");
    private static final ResourceLocation TEXTURE_PEARLY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/pearly.png");
    private static final ResourceLocation TEXTURE_PURPLE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/purple.png");
    private static final ResourceLocation TEXTURE_BLACK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/black.png");
    private static final ResourceLocation TEXTURE_GEM = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/gem.png");
    private static final ResourceLocation TEXTURE_REGAL_BLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/regalblue.png");
    private static final ResourceLocation TEXTURE_PENGUIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/penguin.png");
    private static final ResourceLocation TEXTURE_GREEN_SPOT = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/greenspot.png");
    private static final ResourceLocation TEXTURE_RUSTY = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/rusty.png");
    private static final ResourceLocation TEXTURE_CHOCOLATE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/chocolate.png");
    private static final ResourceLocation TEXTURE_SAILFIN = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/sailfin.png");
    private static final ResourceLocation TEXTURE_ATLANTIC = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/atlantic.png");
    private static final ResourceLocation TEXTURE_EYESTRIPE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/eyestripe.png");
    private static final ResourceLocation TEXTURE_WHITECHEEK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/whitecheek.png");
    private static final ResourceLocation TEXTURE_SCOPAS = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/scopas.png");
    private static final ResourceLocation TEXTURE_GOTH = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/goth.png");
    private static final ResourceLocation TEXTURE_POWDERHYBRID = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/powderbluehybrid.png");
    private static final ResourceLocation TEXTURE_PASTELBLUE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/pastelblue.png");
    private static final ResourceLocation TEXTURE_YELLOWSTRIKE = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/yellowstrike.png");
    private static final ResourceLocation TEXTURE_BLACKSURGEON = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/blacksurgeon.png");
    private static final ResourceLocation TEXTURE_ORANGEBAND = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/orangeband.png");
    private static final ResourceLocation TEXTURE_BLONDELIPSTICK = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/blondelipstick.png");
    private static final ResourceLocation TEXTURE_WHITETAILBRISTLETOOTH = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/whitetailbristletooth.png");
    private static final ResourceLocation TEXTURE_ZEBRA = new ResourceLocation(RainbowReef.MOD_ID, "textures/entity/tang/zebra.png");

    public TangRenderer(EntityRendererProvider.Context context) {
        super(context, new TangModel<>(context.bakeLayer(ReefModelLayers.TANG_LAYER)), 0.3F);
    }

    public ResourceLocation getTextureLocation(Tang entity) {
        return switch (entity.getVariant()) {
            case 1 ->  TEXTURE_POWDERBLUE;
            case 2 ->  TEXTURE_YELLOW;
            case 3 ->  TEXTURE_UNICORN;
            case 4 ->  TEXTURE_CONVICT;
            case 5 ->  TEXTURE_CLOWN;
            case 6 ->  TEXTURE_ACHILLES;
            case 7 ->  TEXTURE_PURPLE;
            case 8 ->  TEXTURE_BLACK;
            case 9 ->  TEXTURE_REGAL_BLUE;
            case 10 -> TEXTURE_GEM;
            case 11 -> TEXTURE_PENGUIN;
            case 12 -> TEXTURE_GREEN_SPOT;
            case 13 -> TEXTURE_RUSTY;
            case 14 -> TEXTURE_PEARLY;
            case 15 -> TEXTURE_YELLOW_BELLY;
            case 16 -> TEXTURE_MUDDY;
            case 17 -> TEXTURE_CHOCOLATE;
            case 18 -> TEXTURE_SAILFIN;
            case 19 -> TEXTURE_ATLANTIC;
            case 20 -> TEXTURE_EYESTRIPE;
            case 21 -> TEXTURE_WHITECHEEK;
            case 22 -> TEXTURE_SCOPAS;
            case 23 -> TEXTURE_GOTH;
            case 24 -> TEXTURE_POWDERHYBRID;
            case 25 -> TEXTURE_PASTELBLUE;
            case 26 -> TEXTURE_YELLOWSTRIKE;
            case 27 -> TEXTURE_BLACKSURGEON;
            case 28 -> TEXTURE_ORANGEBAND;
            case 29 -> TEXTURE_BLONDELIPSTICK;
            case 30 -> TEXTURE_WHITETAILBRISTLETOOTH;
            case 31 -> TEXTURE_ZEBRA;
            default -> TEXTURE_BLUE;
        };
    }
}

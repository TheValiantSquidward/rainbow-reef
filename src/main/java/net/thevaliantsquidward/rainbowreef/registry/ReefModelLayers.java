package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

@OnlyIn(Dist.CLIENT)
public class ReefModelLayers {

    public static final ModelLayerLocation ANGELFISH_LAYER = main("angelfish");
    public static final ModelLayerLocation CRAB_LAYER = main("crab");
    public static final ModelLayerLocation CLOWNFISH_LAYER = main("clownfish");
    public static final ModelLayerLocation PARROTFISH_LAYER = main("parrotfish");
    public static final ModelLayerLocation PIPEFISH_LAYER = main("pipefish");
    public static final ModelLayerLocation RAY_LAYER = main("ray");
    public static final ModelLayerLocation SEAHORSE_LAYER = main("seahorse");
    public static final ModelLayerLocation SMALL_SHARK_LAYER = main("small_shark");
    public static final ModelLayerLocation TANG_LAYER = main("tang");
    public static final ModelLayerLocation BUTTERFISH_LAYER = main("butterfish");
    public static final ModelLayerLocation ARROWCRAB_LAYER = main("arrowcrab");
    public static final ModelLayerLocation DWARF_ANGELFISH_LAYER = main("dwarf_angelfish");
    public static final ModelLayerLocation BOXFISH_LAYER = main("boxfish");
    public static final ModelLayerLocation BASSLET_LAYER = main("basslet");
    public static final ModelLayerLocation IDOL_LAYER = main("moorish_idol");
    public static final ModelLayerLocation JELLYFISH_LAYER = main("jellyfish");
    public static final ModelLayerLocation HOGFISH_LAYER = main("hogfish");
    public static final ModelLayerLocation GOBY_LAYER = main("goby");
    public static final ModelLayerLocation LIONFISH_LAYER = main("lionfish");
    public static final ModelLayerLocation MAHI_LAYER = main("mahi_mahi");
    public static final ModelLayerLocation BILLFISH_LAYER = main("billfish");
    public static final ModelLayerLocation MAORI_WRASSE_LAYER = main("maori_wrasse");
    public static final ModelLayerLocation FROGFISH_LAYER = main("frogfish_layer");

    private static ModelLayerLocation register(String id, String name) {
        return new ModelLayerLocation(new ResourceLocation(RainbowReef.MOD_ID, id), name);
    }

    private static ModelLayerLocation main(String id) {
        return register(id, "main");
    }
}

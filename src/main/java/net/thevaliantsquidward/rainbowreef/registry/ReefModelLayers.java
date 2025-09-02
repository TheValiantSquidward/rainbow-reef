package net.thevaliantsquidward.rainbowreef.registry;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.RainbowReef;

@OnlyIn(Dist.CLIENT)
public class ReefModelLayers {

    public static final ModelLayerLocation ANGELFISH = main("angelfish");
    public static final ModelLayerLocation ARROW_CRAB = main("arrow_crab");
    public static final ModelLayerLocation BASSLET = main("basslet");
    public static final ModelLayerLocation BILLFISH = main("billfish");
    public static final ModelLayerLocation BOXFISH = main("boxfish");
    public static final ModelLayerLocation BUTTERFLYFISH = main("butterflyfish");
    public static final ModelLayerLocation CLOWNFISH = main("clownfish");
    public static final ModelLayerLocation CRAB = main("crab");
    public static final ModelLayerLocation DAMSELFISH = main("damselfish");
    public static final ModelLayerLocation DWARF_ANGELFISH = main("dwarf_angelfish");
    public static final ModelLayerLocation FROGFISH = main("frogfish");
    public static final ModelLayerLocation GOBY = main("goby");
    public static final ModelLayerLocation HOGFISH = main("hogfish");
    public static final ModelLayerLocation JELLYFISH = main("jellyfish");
    public static final ModelLayerLocation LARGE_SHARK = main("large_shark");
    public static final ModelLayerLocation LIONFISH = main("lionfish");
    public static final ModelLayerLocation MAHI_MAHI = main("mahi_mahi");
    public static final ModelLayerLocation MAORI_WRASSE = main("maori_wrasse");
    public static final ModelLayerLocation MOORISH_IDOL = main("moorish_idol");
    public static final ModelLayerLocation PARROTFISH = main("parrotfish");
    public static final ModelLayerLocation PIPEFISH = main("pipefish");
    public static final ModelLayerLocation RABBITFISH = main("rabbitfish");
    public static final ModelLayerLocation RAY = main("ray");
    public static final ModelLayerLocation SEAHORSE = main("seahorse");
    public static final ModelLayerLocation SHARK = main("shark");
    public static final ModelLayerLocation SMALL_SHARK = main("small_shark");
    public static final ModelLayerLocation TANG = main("tang");
    public static final ModelLayerLocation TRIGGERFISH = main("triggerfish");
    public static final ModelLayerLocation WRASSE = main("wrasse");

    private static ModelLayerLocation register(String id, String name) {
        return new ModelLayerLocation(new ResourceLocation(RainbowReef.MOD_ID, id), name);
    }

    private static ModelLayerLocation main(String id) {
        return register(id, "main");
    }
}

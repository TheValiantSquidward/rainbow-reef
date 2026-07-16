package com.valiantenvoy.rainbow_reef.registry;

import com.valiantenvoy.rainbow_reef.RainbowReef;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class ReefModelLayers {

    public static final ModelLayerLocation ANGELFISH = register("angelfish");
    public static final ModelLayerLocation ARROW_CRAB = register("arrow_crab");
    public static final ModelLayerLocation BASSLET = register("basslet");
    public static final ModelLayerLocation BILLFISH = register("billfish");
    public static final ModelLayerLocation BOXFISH = register("boxfish");
    public static final ModelLayerLocation BUTTERFLYFISH = register("butterflyfish");
    public static final ModelLayerLocation CLOWNFISH = register("clownfish");
    public static final ModelLayerLocation CRAB = register("crab");
    public static final ModelLayerLocation DAMSELFISH = register("damselfish");
    public static final ModelLayerLocation DWARF_ANGELFISH = register("dwarf_angelfish");
    public static final ModelLayerLocation FROGFISH = register("frogfish");
    public static final ModelLayerLocation FUSILIER = register("fusilier");
    public static final ModelLayerLocation GOBY = register("goby");
    public static final ModelLayerLocation HOGFISH = register("hogfish");
    public static final ModelLayerLocation JELLYFISH = register("jellyfish");
    public static final ModelLayerLocation LARGE_SHARK = register("large_shark");
    public static final ModelLayerLocation LIONFISH = register("lionfish");
    public static final ModelLayerLocation MAHI_MAHI = register("mahi_mahi");
    public static final ModelLayerLocation MAORI_WRASSE = register("maori_wrasse");
    public static final ModelLayerLocation MOORISH_IDOL = register("moorish_idol");
    public static final ModelLayerLocation PARROTFISH = register("parrotfish");
    public static final ModelLayerLocation PIPEFISH = register("pipefish");
    public static final ModelLayerLocation RABBITFISH = register("rabbitfish");
    public static final ModelLayerLocation RAY = register("ray");
    public static final ModelLayerLocation SEAHORSE = register("seahorse");
    public static final ModelLayerLocation SHARK = register("shark");
    public static final ModelLayerLocation SMALL_SHARK = register("small_shark");
    public static final ModelLayerLocation TANG = register("tang");
    public static final ModelLayerLocation TRIGGERFISH = register("triggerfish");
    public static final ModelLayerLocation WRASSE = register("wrasse");

    private static ModelLayerLocation register(String id) {
        return new ModelLayerLocation(RainbowReef.location(id), "main");
    }
}

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
    public static final ModelLayerLocation PARROTFISH_LAYER = main("parrotfish");
    public static final ModelLayerLocation PIPEFISH_LAYER = main("pipefish");
    public static final ModelLayerLocation SEAHORSE_LAYER = main("seahorse");
    public static final ModelLayerLocation SMALL_SHARK_LAYER = main("small_shark");
    public static final ModelLayerLocation TANG_LAYER = main("tang");

    private static ModelLayerLocation register(String id, String name) {
        return new ModelLayerLocation(new ResourceLocation(RainbowReef.MOD_ID, id), name);
    }

    private static ModelLayerLocation main(String id) {
        return register(id, "main");
    }
}

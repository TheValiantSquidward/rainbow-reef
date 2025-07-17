package net.thevaliantsquidward.rainbowreef.client.models.entity;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.client.animations.ClownfishAnimations;
import net.thevaliantsquidward.rainbowreef.client.animations.HogfishAnimations;
import net.thevaliantsquidward.rainbowreef.client.models.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.entity.HogfishEntity;

@OnlyIn(Dist.CLIENT)
public class HogfishModel<T extends HogfishEntity> extends ReefModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "hogfish"), "main");
	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart TopFin;
	private final ModelPart BottomFin;
	private final ModelPart l_fin;
	private final ModelPart r_fin;
	private final ModelPart l_bottom_fin;
	private final ModelPart r_bottom_fin;
	private final ModelPart tail;

	public HogfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.TopFin = this.body.getChild("TopFin");
		this.BottomFin = this.body.getChild("BottomFin");
		this.l_fin = this.body.getChild("l_fin");
		this.r_fin = this.body.getChild("r_fin");
		this.l_bottom_fin = this.body.getChild("l_bottom_fin");
		this.r_bottom_fin = this.body.getChild("r_bottom_fin");
		this.tail = this.body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 1.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -4.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(21, 8).addBox(-0.5F, -2.0F, -5.0F, 1.0F, 3.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-1.0F, -3.0F, -3.0F, 2.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition TopFin = body.addOrReplaceChild("TopFin", CubeListBuilder.create().texOffs(19, 14).addBox(0.0F, -3.0454F, -1.001F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition BottomFin = body.addOrReplaceChild("BottomFin", CubeListBuilder.create().texOffs(0, 14).addBox(0.0F, -2.0F, -3.0F, 0.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 2.0F));

		PartDefinition l_fin = body.addOrReplaceChild("l_fin", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, -2.0F));

		PartDefinition l_fin_r1 = l_fin.addOrReplaceChild("l_fin_r1", CubeListBuilder.create().texOffs(28, 0).addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.3927F, 0.0F));

		PartDefinition r_fin = body.addOrReplaceChild("r_fin", CubeListBuilder.create(), PartPose.offset(-1.0F, 1.0F, -2.0F));

		PartDefinition r_fin_r1 = r_fin.addOrReplaceChild("r_fin_r1", CubeListBuilder.create().texOffs(28, 0).mirror().addBox(0.0F, -1.0F, 0.0F, 0.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, -0.3927F, 0.0F));

		PartDefinition l_bottom_fin = body.addOrReplaceChild("l_bottom_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -1.0F));

		PartDefinition l_bottom_fin_r1 = l_bottom_fin.addOrReplaceChild("l_bottom_fin_r1", CubeListBuilder.create().texOffs(21, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, -0.3927F));

		PartDefinition r_bottom_fin = body.addOrReplaceChild("r_bottom_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 2.0F, -1.0F));

		PartDefinition r_bottom_fin_r1 = r_bottom_fin.addOrReplaceChild("r_bottom_fin_r1", CubeListBuilder.create().texOffs(21, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.3927F, 0.0F, 0.3927F));

		PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(17, 26).addBox(0.0F, -4.0F, 0.0F, 0.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}
	@Override
	public void setupAnim(HogfishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.core.xRot = (headPitch * (Mth.DEG_TO_RAD));

		this.animate(entity.swimAnimationState, HogfishAnimations.SWIM, ageInTicks, (float) (0.5 + limbSwingAmount * 4.0f));
		this.animate(entity.landAnimationState, ClownfishAnimations.FLOP, ageInTicks, 1);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
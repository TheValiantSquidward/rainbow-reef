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
import net.thevaliantsquidward.rainbowreef.client.animations.BoxfishAnimations;
import net.thevaliantsquidward.rainbowreef.client.animations.ClownfishAnimations;
import net.thevaliantsquidward.rainbowreef.client.models.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.entity.BoxfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.ClownfishEntity;

@OnlyIn(Dist.CLIENT)
public class BoxfishModel<T extends BoxfishEntity> extends ReefModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "boxfish"), "main");
	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart l_fin;
	private final ModelPart r_fin;
	private final ModelPart tail;
	private final ModelPart body;
	private final ModelPart TopFin;
	private final ModelPart BottomFin;

	public BoxfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.l_fin = this.core.getChild("l_fin");
		this.r_fin = this.core.getChild("r_fin");
		this.tail = this.core.getChild("tail");
		this.body = this.core.getChild("body");
		this.TopFin = this.body.getChild("TopFin");
		this.BottomFin = this.body.getChild("BottomFin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 22.0F, 0.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -2.0F));

		PartDefinition l_fin = core.addOrReplaceChild("l_fin", CubeListBuilder.create().texOffs(16, 21).addBox(-1.0F, -1.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 2.0F, 0.0F));

		PartDefinition r_fin = core.addOrReplaceChild("r_fin", CubeListBuilder.create().texOffs(16, 21).mirror().addBox(-2.0F, -1.0F, 0.0F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-2.0F, 2.0F, 0.0F));

		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -2.5F, 0.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 5.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 13).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(23, 0).addBox(-0.5F, 0.0F, -5.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(15, 3).addBox(-2.0F, -3.0F, -6.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-2.0F, -3.0F, -7.0F, 4.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(23, 9).addBox(2.0F, 1.0F, 3.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(23, 9).addBox(-2.0F, 1.0F, 3.0F, 0.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

		PartDefinition TopFin = body.addOrReplaceChild("TopFin", CubeListBuilder.create().texOffs(13, 22).addBox(0.0F, -3.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 2.0F));

		PartDefinition BottomFin = body.addOrReplaceChild("BottomFin", CubeListBuilder.create().texOffs(21, 13).addBox(0.0261F, -1.0231F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 2.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(BoxfishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.core.xRot = (headPitch * (Mth.DEG_TO_RAD));

		this.animate(entity.swimAnimationState, BoxfishAnimations.SWIM, ageInTicks, (float) (0.5 + limbSwingAmount * 4.0f));
		this.animate(entity.landAnimationState, BoxfishAnimations.FLOP, ageInTicks, 1);
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
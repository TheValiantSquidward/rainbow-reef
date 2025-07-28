package net.thevaliantsquidward.rainbowreef.client.models.entity;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.thevaliantsquidward.rainbowreef.client.animations.DwarfAngelfishAnimations;
import net.thevaliantsquidward.rainbowreef.client.models.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.entity.ButterfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.DwarfAngelfishEntity;
import net.thevaliantsquidward.rainbowreef.entity.LionfishEntity;

public class LionfishModel<T extends LionfishEntity> extends ReefModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "lionfish"), "main");
	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart tail;
	private final ModelPart body;
	private final ModelPart topfin;
	private final ModelPart bottomfin;
	private final ModelPart leftpelvicfin;
	private final ModelPart rightpelvicfin;
	private final ModelPart leftfin;
	private final ModelPart rightfin;
	private final ModelPart head;
	private final ModelPart leftantenna;
	private final ModelPart rightantenna;

	public LionfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.tail = this.core.getChild("tail");
		this.body = this.core.getChild("body");
		this.topfin = this.body.getChild("topfin");
		this.bottomfin = this.body.getChild("bottomfin");
		this.leftpelvicfin = this.body.getChild("leftpelvicfin");
		this.rightpelvicfin = this.body.getChild("rightpelvicfin");
		this.leftfin = this.body.getChild("leftfin");
		this.rightfin = this.body.getChild("rightfin");
		this.head = this.core.getChild("head");
		this.leftantenna = this.head.getChild("leftantenna");
		this.rightantenna = this.leftantenna.getChild("rightantenna");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 1.0F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, -7.0F));

		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(29, 1).addBox(0.0F, -4.0F, 0.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 12.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 23).addBox(-2.0F, -5.0F, 0.0F, 4.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.0F));

		PartDefinition topfin = body.addOrReplaceChild("topfin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -7.0F, -7.0F, 0.0F, 9.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 4.0F));

		PartDefinition bottomfin = body.addOrReplaceChild("bottomfin", CubeListBuilder.create().texOffs(28, 11).addBox(0.0F, -2.0F, -2.0F, 0.0F, 5.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 7.0F));

		PartDefinition leftpelvicfin = body.addOrReplaceChild("leftpelvicfin", CubeListBuilder.create().texOffs(34, 39).addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 3.0F, 0.3927F, 0.0F, -0.3927F));

		PartDefinition rightpelvicfin = body.addOrReplaceChild("rightpelvicfin", CubeListBuilder.create().texOffs(34, 39).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 3.0F, 3.0F, 0.3927F, 0.0F, 0.3927F));

		PartDefinition leftfin = body.addOrReplaceChild("leftfin", CubeListBuilder.create().texOffs(26, 32).addBox(0.0F, -3.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 1.0F, 0.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition rightfin = body.addOrReplaceChild("rightfin", CubeListBuilder.create().texOffs(26, 32).mirror().addBox(-8.0F, -3.0F, 0.0F, 8.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 1.0F, 0.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition head = core.addOrReplaceChild("head", CubeListBuilder.create().texOffs(26, 23).addBox(-2.0F, -3.0F, -4.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F))
		.texOffs(6, 46).addBox(1.0F, 1.0F, -5.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(6, 46).mirror().addBox(-1.0F, 1.0F, -5.0F, 0.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 1.0F, 3.0F));

		PartDefinition leftantenna = head.addOrReplaceChild("leftantenna", CubeListBuilder.create().texOffs(26, 39).addBox(0.0F, -4.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -3.0F, -3.0F));

		PartDefinition rightantenna = leftantenna.addOrReplaceChild("rightantenna", CubeListBuilder.create().texOffs(26, 39).mirror().addBox(0.0F, -4.0F, -3.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-4.0F, 0.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(LionfishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netheadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.core.xRot = (headPitch * (Mth.DEG_TO_RAD));

		this.animate(entity.swimAnimationState, DwarfAngelfishAnimations.SWIM, ageInTicks, limbSwingAmount * 4.0f);
		this.animate(entity.landAnimationState, DwarfAngelfishAnimations.FLOP, ageInTicks, 1);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

}
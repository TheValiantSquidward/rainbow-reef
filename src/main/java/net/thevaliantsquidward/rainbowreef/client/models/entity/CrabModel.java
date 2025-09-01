package net.thevaliantsquidward.rainbowreef.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.client.animations.CrabAnimations;
import net.thevaliantsquidward.rainbowreef.entity.Crab;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class CrabModel extends HierarchicalModel<Crab> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart legs;
	private final ModelPart l_legs;
	private final ModelPart l_leg_1;
	private final ModelPart l_leg_2;
	private final ModelPart l_leg_3;
	private final ModelPart r_legs;
	private final ModelPart r_leg_1;
	private final ModelPart r_leg_2;
	private final ModelPart r_leg_3;
	private final ModelPart body;
	private final ModelPart mandibles;
	private final ModelPart spikes;
	private final ModelPart l_pincer;
	private final ModelPart r_pincer;

	public CrabModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.legs = this.core.getChild("legs");
		this.l_legs = this.legs.getChild("l_legs");
		this.l_leg_1 = this.l_legs.getChild("l_leg_1");
		this.l_leg_2 = this.l_legs.getChild("l_leg_2");
		this.l_leg_3 = this.l_legs.getChild("l_leg_3");
		this.r_legs = this.legs.getChild("r_legs");
		this.r_leg_1 = this.r_legs.getChild("r_leg_1");
		this.r_leg_2 = this.r_legs.getChild("r_leg_2");
		this.r_leg_3 = this.r_legs.getChild("r_leg_3");
		this.body = this.core.getChild("body");
		this.mandibles = this.body.getChild("mandibles");
		this.spikes = this.body.getChild("spikes");
		this.l_pincer = this.body.getChild("l_pincer");
		this.r_pincer = this.body.getChild("r_pincer");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(-0.5F, 21.0F, -0.5F));
		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -0.5F));
		PartDefinition legs = core.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.5F, 3.0F, 1.0F));
		PartDefinition l_legs = legs.addOrReplaceChild("l_legs", CubeListBuilder.create(), PartPose.offset(3.0F, -2.0F, -1.0F));
		PartDefinition l_leg_1 = l_legs.addOrReplaceChild("l_leg_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition l_leg_1_r1 = l_leg_1.addOrReplaceChild("l_leg_1_r1", CubeListBuilder.create().texOffs(12, 19).mirror().addBox(0.0F, -1.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6109F, -0.2618F));
		PartDefinition l_leg_2 = l_legs.addOrReplaceChild("l_leg_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));
		PartDefinition l_leg_2_r1 = l_leg_2.addOrReplaceChild("l_leg_2_r1", CubeListBuilder.create().texOffs(12, 19).mirror().addBox(0.0F, -1.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));
		PartDefinition l_leg_3 = l_legs.addOrReplaceChild("l_leg_3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 2.0F));
		PartDefinition l_leg_3_r1 = l_leg_3.addOrReplaceChild("l_leg_3_r1", CubeListBuilder.create().texOffs(12, 19).mirror().addBox(0.0F, -1.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6109F, -0.2618F));
		PartDefinition r_legs = legs.addOrReplaceChild("r_legs", CubeListBuilder.create(), PartPose.offset(-4.0F, -2.0F, -1.0F));
		PartDefinition r_leg_1 = r_legs.addOrReplaceChild("r_leg_1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition r_leg_1_r1 = r_leg_1.addOrReplaceChild("r_leg_1_r1", CubeListBuilder.create().texOffs(12, 19).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.6109F, 0.2618F));
		PartDefinition r_leg_2 = r_legs.addOrReplaceChild("r_leg_2", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));
		PartDefinition r_leg_2_r1 = r_leg_2.addOrReplaceChild("r_leg_2_r1", CubeListBuilder.create().texOffs(12, 19).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));
		PartDefinition r_leg_3 = r_legs.addOrReplaceChild("r_leg_3", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 2.0F));
		PartDefinition r_leg_3_r1 = r_leg_3.addOrReplaceChild("r_leg_3_r1", CubeListBuilder.create().texOffs(12, 19).addBox(-3.0F, -1.0F, 0.0F, 3.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.6109F, 0.2618F));
		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, -0.75F, -1.2667F));
		PartDefinition body_r1 = body.addOrReplaceChild("body_r1", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -5.0F, -1.15F, 5.0F, 4.0F, 0.0F, new CubeDeformation(0.0F)).texOffs(0, 0).addBox(-4.0F, -2.0F, -1.0F, 7.0F, 4.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 0.75F, 0.2667F, -0.0873F, 0.0F, 0.0F));
		PartDefinition mandibles = body.addOrReplaceChild("mandibles", CubeListBuilder.create(), PartPose.offset(0.5F, 0.75F, 0.2667F));
		PartDefinition mandibles_r1 = mandibles.addOrReplaceChild("mandibles_r1", CubeListBuilder.create().texOffs(10, 9).addBox(-2.0F, -0.75F, -1.15F, 3.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition spikes = body.addOrReplaceChild("spikes", CubeListBuilder.create().texOffs(17, 11).addBox(-4.0F, -4.0F, -5.0F, 5.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.5F, 3.75F, 2.2667F));
		PartDefinition spikes_r1 = spikes.addOrReplaceChild("spikes_r1", CubeListBuilder.create().texOffs(5, 23).addBox(2.0F, -3.0F, -1.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(5, 25).addBox(-0.5F, -4.0F, -1.0F, 0.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)).texOffs(5, 23).addBox(-3.0F, -3.0F, -1.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -3.0F, -2.0F, -0.0873F, 0.0F, 0.0F));
		PartDefinition l_pincer = body.addOrReplaceChild("l_pincer", CubeListBuilder.create(), PartPose.offsetAndRotation(3.5F, 1.75F, -0.7333F, 0.2182F, 0.0F, 0.0F));
		PartDefinition l_pincer_r1 = l_pincer.addOrReplaceChild("l_pincer_r1", CubeListBuilder.create().texOffs(0, 9).mirror().addBox(-3.0F, -1.0F, -2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.5236F, -0.2618F));
		PartDefinition r_pincer = body.addOrReplaceChild("r_pincer", CubeListBuilder.create(), PartPose.offset(-3.5F, 1.75F, -0.7333F));
		PartDefinition r_pincer_r1 = r_pincer.addOrReplaceChild("r_pincer_r1", CubeListBuilder.create().texOffs(0, 22).addBox(-1.0F, -2.0F, -2.0F, 4.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(12, 13).addBox(-1.0F, -1.0F, -2.0F, 4.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.5236F, 0.2618F));
		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Crab entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		animateWalk(CrabAnimations.WALK, limbSwing, limbSwingAmount, 2, 4);
		this.animate(entity.idleAnimationState, CrabAnimations.IDLE, ageInTicks);
		this.animate(entity.danceAnimationState, CrabAnimations.DANCE, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.root;
	}
}
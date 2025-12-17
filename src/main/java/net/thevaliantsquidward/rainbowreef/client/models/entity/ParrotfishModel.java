package net.thevaliantsquidward.rainbowreef.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.client.animations.ParrotfishAnimations;
import net.thevaliantsquidward.rainbowreef.entity.Parrotfish;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class ParrotfishModel extends HierarchicalModel<Parrotfish> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart TopFin;
	private final ModelPart BottomFin;
	private final ModelPart l_fin;
	private final ModelPart r_fin;
	private final ModelPart r_bottom_fin;
	private final ModelPart l_bottom_fin;
	private final ModelPart tail;

	public ParrotfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.TopFin = this.body.getChild("TopFin");
		this.BottomFin = this.body.getChild("BottomFin");
		this.l_fin = this.body.getChild("l_fin");
		this.r_fin = this.body.getChild("r_fin");
		this.r_bottom_fin = this.body.getChild("r_bottom_fin");
		this.l_bottom_fin = this.body.getChild("l_bottom_fin");
		this.tail = this.core.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 1.0F));
		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -5.0F));
		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -4.0F, -1.0F, 4.0F, 8.0F, 11.0F, new CubeDeformation(0.0F)).texOffs(19, 0).addBox(-2.0F, -5.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
		PartDefinition TopFin = body.addOrReplaceChild("TopFin", CubeListBuilder.create().texOffs(0, 10).addBox(0.0F, -2.0F, -1.0F, 0.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 4.0F));
		PartDefinition BottomFin = body.addOrReplaceChild("BottomFin", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -1.0F, -1.0F, 0.0F, 3.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 4.0F));
		PartDefinition l_fin = body.addOrReplaceChild("l_fin", CubeListBuilder.create(), PartPose.offset(2.0F, 1.0F, 2.0F));
		PartDefinition l_fin_r1 = l_fin.addOrReplaceChild("l_fin_r1", CubeListBuilder.create().texOffs(0, 3).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.9822F, -0.1454F, 0.2618F, 0.6109F, 0.0F));
		PartDefinition r_fin = body.addOrReplaceChild("r_fin", CubeListBuilder.create(), PartPose.offset(-2.0F, 1.0F, 2.0F));
		PartDefinition r_fin_r1 = r_fin.addOrReplaceChild("r_fin_r1", CubeListBuilder.create().texOffs(0, 3).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.9822F, -0.1454F, 0.2618F, -0.6109F, 0.0F));
		PartDefinition r_bottom_fin = body.addOrReplaceChild("r_bottom_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 4.0F));
		PartDefinition r_bottom_fin_r1 = r_bottom_fin.addOrReplaceChild("r_bottom_fin_r1", CubeListBuilder.create().texOffs(22, 25).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.6109F, 0.0F, 0.6109F));
		PartDefinition l_bottom_fin = body.addOrReplaceChild("l_bottom_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, 3.0F));
		PartDefinition l_bottom_fin_r1 = l_bottom_fin.addOrReplaceChild("l_bottom_fin_r1", CubeListBuilder.create().texOffs(22, 25).addBox(0.0F, 0.0F, -1.0F, 0.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.6109F, 0.0F, -0.6109F));
		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(19, 11).addBox(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 10.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Parrotfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		float prevOnLandProgress = entity.prevOnLandProgress;
		float onLandProgress = entity.onLandProgress;
		float partialTicks = ageInTicks - entity.tickCount;
		float landProgress = prevOnLandProgress + (onLandProgress - prevOnLandProgress) * partialTicks;

		this.animate(entity.swimIdleAnimationState, ParrotfishAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 1.25F);
		this.animate(entity.flopAnimationState, ParrotfishAnimations.FLOP, ageInTicks);
		this.animate(entity.eepyAnimationState, ParrotfishAnimations.SLEEP, ageInTicks);

		this.root.xRot = headPitch * (Mth.DEG_TO_RAD);
		this.root.zRot += landProgress * ((float) Math.toRadians(90) / 5F);
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
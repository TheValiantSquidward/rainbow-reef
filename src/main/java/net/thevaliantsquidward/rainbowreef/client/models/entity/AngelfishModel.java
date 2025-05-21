package net.thevaliantsquidward.rainbowreef.client.models.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.thevaliantsquidward.rainbowreef.client.animations.AngelfishAnimations;
import net.thevaliantsquidward.rainbowreef.client.models.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.entity.AngelfishEntity;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class AngelfishModel<T extends AngelfishEntity> extends ReefModel<T> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart FinTop;
	private final ModelPart FinBottom;
	private final ModelPart head;
	private final ModelPart leftBottomFin;
	private final ModelPart rightBottomFin;
	private final ModelPart rightFin;
	private final ModelPart leftFin;
	private final ModelPart tail;

	public AngelfishModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.FinTop = this.body.getChild("FinTop");
		this.FinBottom = this.body.getChild("FinBottom");
		this.head = this.body.getChild("head");
		this.leftBottomFin = this.body.getChild("leftBottomFin");
		this.rightBottomFin = this.body.getChild("rightBottomFin");
		this.rightFin = this.body.getChild("rightFin");
		this.leftFin = this.body.getChild("leftFin");
		this.tail = this.core.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();
		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 1.0F));
		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -1.0F));
		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(19, 19).addBox(-1.5F, -4.0F, -5.0F, 3.0F, 8.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, 1.0F));
		PartDefinition FinTop = body.addOrReplaceChild("FinTop", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -3.0F, -1.0F, 0.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -2.0F));
		PartDefinition FinBottom = body.addOrReplaceChild("FinBottom", CubeListBuilder.create().texOffs(0, 9).addBox(0.0F, -2.0F, -2.0F, 0.0F, 5.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -1.0F));
		PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(12, 0).addBox(-1.0F, -3.5F, -2.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -5.0F));
		PartDefinition leftBottomFin = body.addOrReplaceChild("leftBottomFin", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, -3.0F));
		PartDefinition leftBottomFin_r1 = leftBottomFin.addOrReplaceChild("leftBottomFin_r1", CubeListBuilder.create().texOffs(16, 7).addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, -0.6109F));
		PartDefinition rightBottomFin = body.addOrReplaceChild("rightBottomFin", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, -3.0F));
		PartDefinition rightBottomFin_r1 = rightBottomFin.addOrReplaceChild("rightBottomFin_r1", CubeListBuilder.create().texOffs(16, 7).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.6109F));
		PartDefinition rightFin = body.addOrReplaceChild("rightFin", CubeListBuilder.create(), PartPose.offset(-1.5F, 2.0F, -4.0F));
		PartDefinition rightFin_r1 = rightFin.addOrReplaceChild("rightFin_r1", CubeListBuilder.create().texOffs(6, 0).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, -0.6109F, 0.0F));
		PartDefinition leftFin = body.addOrReplaceChild("leftFin", CubeListBuilder.create(), PartPose.offset(1.5F, 2.0F, -4.0F));
		PartDefinition leftFin_r1 = leftFin.addOrReplaceChild("leftFin_r1", CubeListBuilder.create().texOffs(6, 0).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.6109F, 0.0F));
		PartDefinition tail = core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -0.5F, 5.0F));
		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(AngelfishEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animate(entity.swimAnimationState, AngelfishAnimations.SWIM, ageInTicks, limbSwingAmount * 4.0F);
		this.animate(entity.flopAnimationState, AngelfishAnimations.FLOP, ageInTicks, 1.0F);

		this.root.xRot = headPitch * (Mth.DEG_TO_RAD);
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
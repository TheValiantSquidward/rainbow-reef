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
import net.thevaliantsquidward.rainbowreef.client.animations.MoorishIdolAnimations;
import net.thevaliantsquidward.rainbowreef.entity.MahiMahi;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class MahiMahiModel extends HierarchicalModel<MahiMahi> {

	private final ModelPart Root;
	private final ModelPart Core;
	private final ModelPart Body;
	private final ModelPart Fin;
	private final ModelPart LeftPelvicFin;
	private final ModelPart RightPelvicFin;
	private final ModelPart LeftFin;
	private final ModelPart RightFin;
	private final ModelPart Tail;
	private final ModelPart TailFin;
	private final ModelPart TopTailFin;
	private final ModelPart BottomTailFin;

	public MahiMahiModel(ModelPart root) {
		this.Root = root.getChild("Root");
		this.Core = this.Root.getChild("Core");
		this.Body = this.Core.getChild("Body");
		this.Fin = this.Body.getChild("Fin");
		this.LeftPelvicFin = this.Body.getChild("LeftPelvicFin");
		this.RightPelvicFin = this.Body.getChild("RightPelvicFin");
		this.LeftFin = this.Body.getChild("LeftFin");
		this.RightFin = this.Body.getChild("RightFin");
		this.Tail = this.Core.getChild("Tail");
		this.TailFin = this.Tail.getChild("TailFin");
		this.TopTailFin = this.Tail.getChild("TopTailFin");
		this.BottomTailFin = this.Tail.getChild("BottomTailFin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Root = partdefinition.addOrReplaceChild("Root", CubeListBuilder.create(), PartPose.offset(0.0F, 19.0F, 0.0F));

		PartDefinition Core = Root.addOrReplaceChild("Core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -12.0F));

		PartDefinition Body = Core.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -5.0F, -7.0F, 4.0F, 10.0F, 14.0F, new CubeDeformation(0.0F))
		.texOffs(37, 16).addBox(-2.0F, -5.0F, -8.0F, 4.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));

		PartDefinition Fin = Body.addOrReplaceChild("Fin", CubeListBuilder.create().texOffs(0, 24).addBox(0.0F, -7.0F, 0.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, -7.0F));

		PartDefinition LeftPelvicFin = Body.addOrReplaceChild("LeftPelvicFin", CubeListBuilder.create().texOffs(16, 45).addBox(0.0F, 0.0F, 0.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -1.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition RightPelvicFin = Body.addOrReplaceChild("RightPelvicFin", CubeListBuilder.create().texOffs(16, 45).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 5.0F, -1.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition LeftFin = Body.addOrReplaceChild("LeftFin", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 4.0F, -4.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition RightFin = Body.addOrReplaceChild("RightFin", CubeListBuilder.create().texOffs(0, 45).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 4.0F, -4.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition Tail = Core.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(28, 24).addBox(-1.0F, -3.0F, 0.0F, 2.0F, 6.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 14.0F));

		PartDefinition TailFin = Tail.addOrReplaceChild("TailFin", CubeListBuilder.create().texOffs(28, 41).addBox(0.0F, -6.0F, 0.0F, 0.0F, 12.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 11.0F));

		PartDefinition TopTailFin = Tail.addOrReplaceChild("TopTailFin", CubeListBuilder.create().texOffs(36, 0).addBox(0.0F, -4.0F, 0.0F, 0.0F, 4.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

		PartDefinition BottomTailFin = Tail.addOrReplaceChild("BottomTailFin", CubeListBuilder.create().texOffs(42, 41).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 1.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	public void setupAnim(MahiMahi entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.Root.xRot = (headPitch * (Mth.DEG_TO_RAD));
		this.animate(entity.swimIdleAnimationState, MoorishIdolAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 2.0F);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.Root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.Root;
	}

}
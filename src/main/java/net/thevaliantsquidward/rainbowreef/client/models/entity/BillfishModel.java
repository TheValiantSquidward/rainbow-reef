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
import net.thevaliantsquidward.rainbowreef.entity.Billfish;

@OnlyIn(Dist.CLIENT)
public class BillfishModel extends HierarchicalModel<Billfish> {

	private final ModelPart Root;
	private final ModelPart Core;
	private final ModelPart Tail;
	private final ModelPart TailFin;
	private final ModelPart Body;
	private final ModelPart SailFin;
	private final ModelPart BottomFin;
	private final ModelPart Jaw;
	private final ModelPart LeftFin;
	private final ModelPart RightFin;
	private final ModelPart LeftPelvicFin;
	private final ModelPart RightPelvicFin;

	public BillfishModel(ModelPart root) {
		this.Root = root.getChild("Root");
		this.Core = this.Root.getChild("Core");
		this.Tail = this.Core.getChild("Tail");
		this.TailFin = this.Tail.getChild("TailFin");
		this.Body = this.Core.getChild("Body");
		this.SailFin = this.Body.getChild("SailFin");
		this.BottomFin = this.Body.getChild("BottomFin");
		this.Jaw = this.Body.getChild("Jaw");
		this.LeftFin = this.Body.getChild("LeftFin");
		this.RightFin = this.Body.getChild("RightFin");
		this.LeftPelvicFin = this.Body.getChild("LeftPelvicFin");
		this.RightPelvicFin = this.Body.getChild("RightPelvicFin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Root = partdefinition.addOrReplaceChild("Root", CubeListBuilder.create(), PartPose.offset(-1.0F, 20.0F, 1.0F));

		PartDefinition Core = Root.addOrReplaceChild("Core", CubeListBuilder.create(), PartPose.offset(1.0F, 0.0F, -9.0F));

		PartDefinition Tail = Core.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(66, 0).addBox(-0.5F, -6.0F, 5.0F, 0.0F, 12.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(46, 38).addBox(-2.0F, -3.0F, 0.0F, 3.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, 17.0F));

		PartDefinition TailFin = Tail.addOrReplaceChild("TailFin", CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, -13.0F, 0.0F, 0.0F, 26.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 14.0F));

		PartDefinition Body = Core.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 38).addBox(-2.5F, -6.0F, -9.0F, 5.0F, 10.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(46, 58).addBox(-0.5F, -1.0F, -25.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 8.0F));

		PartDefinition SailFin = Body.addOrReplaceChild("SailFin", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -11.0F, -1.0F, 0.0F, 14.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -8.0F));

		PartDefinition BottomFin = Body.addOrReplaceChild("BottomFin", CubeListBuilder.create().texOffs(66, 19).addBox(0.0F, 0.0F, 0.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, 2.0F));

		PartDefinition Jaw = Body.addOrReplaceChild("Jaw", CubeListBuilder.create().texOffs(66, 30).addBox(-0.5F, 0.0F, -4.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -9.0F));

		PartDefinition LeftFin = Body.addOrReplaceChild("LeftFin", CubeListBuilder.create().texOffs(18, 66).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 3.0F, -2.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition RightFin = Body.addOrReplaceChild("RightFin", CubeListBuilder.create().texOffs(18, 66).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 3.0F, -2.0F, 0.0F, -0.3927F, 0.0F));

		PartDefinition LeftPelvicFin = Body.addOrReplaceChild("LeftPelvicFin", CubeListBuilder.create().texOffs(0, 66).addBox(0.0F, 0.0F, -1.0F, 0.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -2.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition RightPelvicFin = Body.addOrReplaceChild("RightPelvicFin", CubeListBuilder.create().texOffs(0, 66).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, -2.0F, 0.0F, 0.0F, 0.3927F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.Root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(Billfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.Core.xRot = (headPitch * (Mth.DEG_TO_RAD));

		this.animate(entity.swimAnimationState, MoorishIdolAnimations.SWIM, ageInTicks, (float) (0.5 + limbSwingAmount * 4.0f));
	}

	@Override
	public ModelPart root() {
		return this.Root;
	}
}
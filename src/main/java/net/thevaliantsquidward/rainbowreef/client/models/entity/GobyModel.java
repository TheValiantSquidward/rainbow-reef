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
import net.thevaliantsquidward.rainbowreef.client.animations.GobyAnimations;
import net.thevaliantsquidward.rainbowreef.entity.Goby;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class GobyModel extends HierarchicalModel<Goby> {

	private final ModelPart root;
	private final ModelPart core;
	private final ModelPart body;
	private final ModelPart l_fin;
	private final ModelPart l_fin2;
	private final ModelPart l_bottom_fin;
	private final ModelPart r_bottom_fin;
	private final ModelPart tail;

	public GobyModel(ModelPart root) {
		this.root = root.getChild("root");
		this.core = this.root.getChild("core");
		this.body = this.core.getChild("body");
		this.l_fin = this.body.getChild("l_fin");
		this.l_fin2 = this.body.getChild("l_fin2");
		this.l_bottom_fin = this.body.getChild("l_bottom_fin");
		this.r_bottom_fin = this.body.getChild("r_bottom_fin");
		this.tail = this.core.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 23.0F, -0.5F));

		PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(5, 9).addBox(-1.0F, -1.0F, -3.5F, 2.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(14, 22).addBox(-1.0F, -2.0F, -3.5F, 2.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 15).addBox(0.0F, -7.0F, -2.5F, 0.0F, 10.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(4, 10).addBox(1.0F, -2.0F, -2.5F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(4, 10).mirror().addBox(-1.0F, -2.0F, -2.5F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition l_fin = body.addOrReplaceChild("l_fin", CubeListBuilder.create(), PartPose.offset(1.0F, 1.0F, -1.5F));

        l_fin.addOrReplaceChild("l_fin_r1", CubeListBuilder.create().texOffs(5, -1).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0F, -0.9599F));

        PartDefinition l_fin2 = body.addOrReplaceChild("l_fin2", CubeListBuilder.create(), PartPose.offset(-1.0F, 1.0F, -1.5F));

        l_fin2.addOrReplaceChild("l_fin2_r1", CubeListBuilder.create().texOffs(5, -1).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.0F, 0.9599F));

        PartDefinition l_bottom_fin = body.addOrReplaceChild("l_bottom_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.5F));

        l_bottom_fin.addOrReplaceChild("l_bottom_fin_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, -0.2618F));

        PartDefinition r_bottom_fin = body.addOrReplaceChild("r_bottom_fin", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 0.5F));

        r_bottom_fin.addOrReplaceChild("r_bottom_fin_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.2618F, 0.0F, 0.2618F));

        core.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(11, -3).addBox(0.0F, -3.0F, 0.0F, 0.0F, 6.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 3.5F));

        return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Goby entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.root.xRot = (headPitch * (Mth.DEG_TO_RAD));
		this.animate(entity.swimAnimationState, GobyAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount * 2.0F);
		this.animate(entity.flopAnimationState, GobyAnimations.FLOP, ageInTicks);
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
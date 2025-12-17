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
import net.thevaliantsquidward.rainbowreef.client.animations.ButterfishAnimations;
import net.thevaliantsquidward.rainbowreef.entity.Butterflyfish;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
@SuppressWarnings("FieldCanBeLocal, unused")
public class ButterflyfishModel extends HierarchicalModel<Butterflyfish> {

    private final ModelPart root;
    private final ModelPart core;
    private final ModelPart body;
    private final ModelPart bodies;
    private final ModelPart snouts;
    private final ModelPart long_snouts;
    private final ModelPart lower_fin_tip;
    private final ModelPart top_fin_tip;
    private final ModelPart left_pelvic_fin;
    private final ModelPart right_pelvic_fin;
    private final ModelPart left_fin;
    private final ModelPart right_fin;
    private final ModelPart tail;

	public ButterflyfishModel(ModelPart root) {
        this.root = root.getChild("root");
        this.core = this.root.getChild("core");
        this.body = this.core.getChild("body");
        this.bodies = this.body.getChild("bodies");
        this.snouts = this.body.getChild("snouts");
        this.long_snouts = this.snouts.getChild("long_snouts");
        this.lower_fin_tip = this.body.getChild("lower_fin_tip");
        this.top_fin_tip = this.body.getChild("top_fin_tip");
        this.left_pelvic_fin = this.body.getChild("left_pelvic_fin");
        this.right_pelvic_fin = this.body.getChild("right_pelvic_fin");
        this.left_fin = this.body.getChild("left_fin");
        this.right_fin = this.body.getChild("right_fin");
        this.tail = this.body.getChild("tail");
	}

	public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.5F, 21.5F, 0.5F));

        PartDefinition core = root.addOrReplaceChild("core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -1.5F));

        PartDefinition body = core.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -8.5F, -6.5F, 0.0F, 14.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 1.5F));

        PartDefinition bodies = body.addOrReplaceChild("bodies", CubeListBuilder.create().texOffs(0, 26).addBox(0.0F, -3.5F, -3.5F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(0.0F, -3.5F, -4.5F, 2.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(24, 13).addBox(0.0F, -4.5F, -3.5F, 2.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

        PartDefinition snouts = body.addOrReplaceChild("snouts", CubeListBuilder.create().texOffs(30, 37).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-0.5F, -3.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 33).addBox(-0.5F, -1.5F, -3.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 2.0F, -3.5F));

        PartDefinition long_snouts = snouts.addOrReplaceChild("long_snouts", CubeListBuilder.create().texOffs(8, 38).addBox(-0.5F, -3.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 38).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition lower_fin_tip = body.addOrReplaceChild("lower_fin_tip", CubeListBuilder.create().texOffs(16, 26).addBox(-0.5F, -1.0F, 0.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5F, 5.5F));

        PartDefinition top_fin_tip = body.addOrReplaceChild("top_fin_tip", CubeListBuilder.create().texOffs(16, 33).addBox(-0.5F, -5.0F, 0.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.5F, 3.5F));

        PartDefinition left_pelvic_fin = body.addOrReplaceChild("left_pelvic_fin", CubeListBuilder.create().texOffs(36, 26).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -1.5F, -0.3927F, 0.3927F, 0.0F));

        PartDefinition right_pelvic_fin = body.addOrReplaceChild("right_pelvic_fin", CubeListBuilder.create().texOffs(36, 26).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 2.5F, -1.5F, -0.3927F, -0.3927F, 0.0F));

        PartDefinition left_fin = body.addOrReplaceChild("left_fin", CubeListBuilder.create().texOffs(26, 37).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.5F, -1.5F, -0.7854F, 0.3927F, 0.0F));

        PartDefinition right_fin = body.addOrReplaceChild("right_fin", CubeListBuilder.create().texOffs(26, 37).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 1.5F, -1.5F, -0.7854F, -0.3927F, 0.0F));

        PartDefinition tail = body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(28, 26).addBox(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, 2.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Butterflyfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.root.xRot = (headPitch * (Mth.DEG_TO_RAD));
		this.animate(entity.swimIdleAnimationState, ButterfishAnimations.SWIM, ageInTicks, 0.5F + limbSwingAmount);
		this.animate(entity.flopAnimationState, ButterfishAnimations.FLOP, ageInTicks);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		this.root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public @NotNull ModelPart root() {
		return this.root;
	}
}
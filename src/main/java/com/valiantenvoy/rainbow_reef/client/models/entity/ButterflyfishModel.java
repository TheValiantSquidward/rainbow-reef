package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.ButterfishAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Butterflyfish;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

@SuppressWarnings("FieldCanBeLocal, unused")
public class ButterflyfishModel extends ReefModel<Butterflyfish> {

    private final ModelPart Root;
    private final ModelPart Core;
    private final ModelPart Body;
    private final ModelPart Bodies;
    private final ModelPart Snouts;
    private final ModelPart LongBodySnouts;
    private final ModelPart LowerFinTip;
    private final ModelPart TopFinTip;
    private final ModelPart LeftPelvicFin;
    private final ModelPart RightPelvicFin;
    private final ModelPart LeftFin;
    private final ModelPart RightFin;
    private final ModelPart Tail;

	public ButterflyfishModel(ModelPart root) {
        this.Root = root.getChild("Root");
        this.Core = this.Root.getChild("Core");
        this.Body = this.Core.getChild("Body");
        this.Bodies = this.Body.getChild("Bodies");
        this.Snouts = this.Body.getChild("Snouts");
        this.LongBodySnouts = this.Snouts.getChild("LongBodySnouts");
        this.LowerFinTip = this.Body.getChild("LowerFinTip");
        this.TopFinTip = this.Body.getChild("TopFinTip");
        this.LeftPelvicFin = this.Body.getChild("LeftPelvicFin");
        this.RightPelvicFin = this.Body.getChild("RightPelvicFin");
        this.LeftFin = this.Body.getChild("LeftFin");
        this.RightFin = this.Body.getChild("RightFin");
        this.Tail = this.Body.getChild("Tail");
	}

    @Override
    public ModelPart root() {
        return this.Root;
    }

    @Override
    protected void setupAnimations(Butterflyfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
        this.Root.xRot = headPitch * Mth.DEG_TO_RAD;
        this.animateWalk(ButterfishAnimations.SWIM, limbSwing, limbSwingAmount, 1.5F, 2.5F);
        this.animateIdleSmooth(entity.swimIdleAnimationState, ButterfishAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
        this.animate(entity.flopAnimationState, ButterfishAnimations.FLOP, ageInTicks);
    }

	public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Root = partdefinition.addOrReplaceChild("Root", CubeListBuilder.create(), PartPose.offset(0.5F, 21.5F, 0.5F));

        PartDefinition Core = Root.addOrReplaceChild("Core", CubeListBuilder.create(), PartPose.offset(0.0F, 0.5F, -1.5F));

        PartDefinition Body = Core.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -8.5F, -6.5F, 0.0F, 14.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 1.5F));

        PartDefinition Bodies = Body.addOrReplaceChild("Bodies", CubeListBuilder.create().texOffs(0, 26).addBox(0.0F, -3.5F, -3.5F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(0.0F, -3.5F, -4.5F, 2.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(24, 13).addBox(0.0F, -4.5F, -3.5F, 2.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

        PartDefinition Snouts = Body.addOrReplaceChild("Snouts", CubeListBuilder.create().texOffs(30, 37).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-0.5F, -3.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 33).addBox(-0.5F, -1.5F, -3.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 2.0F, -3.5F));

        PartDefinition LongBodySnouts = Snouts.addOrReplaceChild("LongBodySnouts", CubeListBuilder.create().texOffs(8, 38).addBox(-0.5F, -3.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 38).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

        PartDefinition LowerFinTip = Body.addOrReplaceChild("LowerFinTip", CubeListBuilder.create().texOffs(16, 26).addBox(-0.5F, -1.0F, 0.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5F, 5.5F));

        PartDefinition TopFinTip = Body.addOrReplaceChild("TopFinTip", CubeListBuilder.create().texOffs(16, 33).addBox(-0.5F, -5.0F, 0.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.5F, 3.5F));

        PartDefinition LeftPelvicFin = Body.addOrReplaceChild("LeftPelvicFin", CubeListBuilder.create().texOffs(36, 26).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -1.5F, -0.3927F, 0.3927F, 0.0F));

        PartDefinition RightPelvicFin = Body.addOrReplaceChild("RightPelvicFin", CubeListBuilder.create().texOffs(36, 26).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 2.5F, -1.5F, -0.3927F, -0.3927F, 0.0F));

        PartDefinition LeftFin = Body.addOrReplaceChild("LeftFin", CubeListBuilder.create().texOffs(26, 37).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.5F, -1.5F, -0.7854F, 0.3927F, 0.0F));

        PartDefinition RightFin = Body.addOrReplaceChild("RightFin", CubeListBuilder.create().texOffs(26, 37).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 1.5F, -1.5F, -0.7854F, -0.3927F, 0.0F));

        PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(28, 26).addBox(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, 2.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
	}
}

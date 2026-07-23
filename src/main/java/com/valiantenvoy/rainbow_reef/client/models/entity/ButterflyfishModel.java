package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.ButterflyfishAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Butterflyfish;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class ButterflyfishModel extends ReefModel<Butterflyfish> {

    private final ModelPart root;
    private final ModelPart swim_control;

	public ButterflyfishModel(ModelPart root) {
        this.root = root.getChild("root");
        this.swim_control = this.root.getChild("swim_control");
	}

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    protected void setupAnimations(Butterflyfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
        this.animateWalkSmooth(entity.swimAnimationState, ButterflyfishAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
        this.animateIdleSmooth(entity.swimIdleAnimationState, ButterflyfishAnimations.SWIM, ageInTicks, partialTicks, limbSwingAmount, 2.5F, 0.75F);
        this.animateSmooth(entity.flopAnimationState, ButterflyfishAnimations.FLOP, ageInTicks, partialTicks);
        this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
    }

	public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.5F, 21.5F, 0.5F));

        PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(-0.5F, -0.5F, -0.5F));

        PartDefinition body_main = swim_control.addOrReplaceChild("body_main", CubeListBuilder.create(), PartPose.offset(0.5F, 1.0F, -1.0F));

        PartDefinition body = body_main.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -8.5F, -6.5F, 0.0F, 14.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 1.5F));

        body.addOrReplaceChild("body_types", CubeListBuilder.create().texOffs(0, 26).addBox(0.0F, -3.5F, -3.5F, 2.0F, 6.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 0).addBox(0.0F, -3.5F, -4.5F, 2.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(24, 13).addBox(0.0F, -4.5F, -3.5F, 2.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.5F, 0.0F, 0.0F));

        PartDefinition snout = body.addOrReplaceChild("snout", CubeListBuilder.create().texOffs(30, 37).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(-0.5F, -3.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 33).addBox(-0.5F, -1.5F, -3.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 2.0F, -3.5F));

        snout.addOrReplaceChild("snout_long", CubeListBuilder.create().texOffs(8, 38).addBox(-0.5F, -3.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 38).addBox(-0.5F, -1.5F, -1.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

        body.addOrReplaceChild("fin_tip_lower", CubeListBuilder.create().texOffs(16, 26).addBox(-0.5F, -1.0F, 0.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.5F, 5.5F));

        body.addOrReplaceChild("fin_tip_upper", CubeListBuilder.create().texOffs(16, 33).addBox(-0.5F, -5.0F, 0.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.5F, 3.5F));

        body.addOrReplaceChild("fin_pelvic_left", CubeListBuilder.create().texOffs(36, 26).addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, -1.5F, -0.3927F, 0.3927F, 0.0F));

        body.addOrReplaceChild("fin_pelvic_right", CubeListBuilder.create().texOffs(36, 26).mirror().addBox(0.0F, 0.0F, 0.0F, 0.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 2.5F, -1.5F, -0.3927F, -0.3927F, 0.0F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(26, 37).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 1.5F, -1.5F, -0.7854F, 0.3927F, 0.0F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(26, 37).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.5F, 1.5F, -1.5F, -0.7854F, -0.3927F, 0.0F));

        body.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(28, 26).addBox(0.0F, -3.5F, 0.0F, 0.0F, 7.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.0F, 2.5F));

        return LayerDefinition.create(meshdefinition, 64, 64);
	}
}

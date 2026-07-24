package com.valiantenvoy.rainbow_reef.client.models.entity;

import com.valiantenvoy.rainbow_reef.client.models.entity.animations.BillfishAnimations;
import com.valiantenvoy.rainbow_reef.client.models.entity.base.ReefModel;
import com.valiantenvoy.rainbow_reef.entity.Billfish;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;

public class BillfishModel extends ReefModel<Billfish> {

    private final ModelPart root;
    private final ModelPart swim_control;
    private final ModelPart tail1;
    private final ModelPart tail2;

	public BillfishModel(ModelPart root) {
        this.root = root.getChild("root");
        this.swim_control = this.root.getChild("swim_control");
        ModelPart body = this.swim_control.getChild("body");
        this.tail1 = body.getChild("tail1");
        this.tail2 = this.tail1.getChild("tail2");
	}

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    protected void setupAnimations(Billfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float partialTicks, float netHeadYaw, float headPitch) {
        this.animateWalkSmooth(entity.swimAnimationState, BillfishAnimations.SWIM, limbSwing, limbSwingAmount, partialTicks);
        this.animateIdleSmooth(entity.swimIdleAnimationState, BillfishAnimations.IDLE, ageInTicks, partialTicks, limbSwingAmount);
        this.animateSmooth(entity.flopAnimationState, BillfishAnimations.FLOP, ageInTicks, partialTicks);
        this.applyPitchAndRoll(entity, this.swim_control, partialTicks);
    }

	public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(-1.0F, 20.0F, 1.0F));

        PartDefinition swim_control = root.addOrReplaceChild("swim_control", CubeListBuilder.create(), PartPose.offset(1.0F, -1.0F, -1.0F));

        PartDefinition body = swim_control.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 34).addBox(-2.5F, -6.0F, -9.0F, 5.0F, 10.0F, 18.0F, new CubeDeformation(0.0F))
                .texOffs(28, 34).addBox(-0.5F, -1.0F, -25.0F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
                .texOffs(38, 10).addBox(0.0F, 4.0F, 2.0F, 0.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));

        body.addOrReplaceChild("fin_sail", CubeListBuilder.create().texOffs(0, -24).addBox(0.0F, -11.0F, -1.0F, 0.0F, 14.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -8.0F));

        body.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(33, 43).addBox(-0.5F, 0.0F, -4.0F, 1.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -9.0F));

        body.addOrReplaceChild("fin_left", CubeListBuilder.create().texOffs(38, 6).addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.5F, 3.0F, -2.0F, 0.0F, 0.3927F, 0.0F));

        body.addOrReplaceChild("fin_right", CubeListBuilder.create().texOffs(38, 6).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.5F, 3.0F, -2.0F, 0.0F, -0.3927F, 0.0F));

        body.addOrReplaceChild("fin_pelvic_left", CubeListBuilder.create().texOffs(0, 5).addBox(0.0F, 0.0F, -1.0F, 0.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, -2.0F, 0.0F, 0.0F, -0.3927F));

        body.addOrReplaceChild("fin_pelvic_right", CubeListBuilder.create().texOffs(0, 5).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 11.0F, 9.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 4.0F, -2.0F, 0.0F, 0.0F, 0.3927F));

        PartDefinition tail1 = body.addOrReplaceChild("tail1", CubeListBuilder.create().texOffs(18, 7).addBox(0.0F, -6.0F, 5.0F, 0.0F, 12.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(18, 14).addBox(-1.5F, -3.0F, 0.0F, 3.0F, 6.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 9.0F));

        tail1.addOrReplaceChild("tail2", CubeListBuilder.create().texOffs(0, 16).addBox(0.0F, -13.0F, 0.0F, 0.0F, 26.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 14.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
	}
}
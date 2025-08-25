package net.thevaliantsquidward.rainbowreef.client.models.entity;// Made with Blockbench 4.12.5
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.thevaliantsquidward.rainbowreef.client.animations.MaoriWrasseAnimations;
import net.thevaliantsquidward.rainbowreef.client.animations.MoorishIdolAnimations;
import net.thevaliantsquidward.rainbowreef.client.models.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.entity.MaoriWrasse;

public class MaoriWrasseModel<T extends MaoriWrasse> extends ReefModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "maori_wrasse"), "main");
	private final ModelPart Root;
	private final ModelPart Core;
	private final ModelPart Tail;
	private final ModelPart Body;
	private final ModelPart TopFin;
	private final ModelPart BottomFin;
	private final ModelPart LeftPelvicFin;
	private final ModelPart RightPelvicFin;
	private final ModelPart LeftFin;
	private final ModelPart RightFin;

	public MaoriWrasseModel(ModelPart root) {
		this.Root = root.getChild("Root");
		this.Core = this.Root.getChild("Core");
		this.Tail = this.Core.getChild("Tail");
		this.Body = this.Core.getChild("Body");
		this.TopFin = this.Body.getChild("TopFin");
		this.BottomFin = this.Body.getChild("BottomFin");
		this.LeftPelvicFin = this.Body.getChild("LeftPelvicFin");
		this.RightPelvicFin = this.Body.getChild("RightPelvicFin");
		this.LeftFin = this.Body.getChild("LeftFin");
		this.RightFin = this.Body.getChild("RightFin");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Root = partdefinition.addOrReplaceChild("Root", CubeListBuilder.create(), PartPose.offset(0.0F, 17.0F, 1.0F));

		PartDefinition Core = Root.addOrReplaceChild("Core", CubeListBuilder.create(), PartPose.offset(0.0F, 4.0F, -11.0F));

		PartDefinition Tail = Core.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(0, 33).addBox(0.0F, -8.0F, 0.0F, 0.0F, 15.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 19.0F));

		PartDefinition Body = Core.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -8.0F, -8.0F, 6.0F, 15.0F, 18.0F, new CubeDeformation(0.0F))
		.texOffs(24, 54).addBox(-2.0F, -8.0F, -9.0F, 4.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
		.texOffs(48, 21).addBox(-3.0F, -2.0F, -11.0F, 6.0F, 9.0F, 3.0F, new CubeDeformation(0.0F))
		.texOffs(53, 34).addBox(-3.5F, 2.0F, -12.0F, 7.0F, 3.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 9.0F));

		PartDefinition TopFin = Body.addOrReplaceChild("TopFin", CubeListBuilder.create().texOffs(24, 33).addBox(0.0F, -5.0F, 0.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));

		PartDefinition BottomFin = Body.addOrReplaceChild("BottomFin", CubeListBuilder.create().texOffs(48, 0).addBox(0.0F, -3.0F, 0.0F, 0.0F, 7.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, 0.0F));

		PartDefinition LeftPelvicFin = Body.addOrReplaceChild("LeftPelvicFin", CubeListBuilder.create().texOffs(52, 39).addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 7.0F, -2.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition RightPelvicFin = Body.addOrReplaceChild("RightPelvicFin", CubeListBuilder.create().texOffs(52, 39).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 7.0F, -2.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition LeftFin = Body.addOrReplaceChild("LeftFin", CubeListBuilder.create().texOffs(52, 51).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 1.0F, -1.0F, 0.0F, 0.3927F, 0.0F));

		PartDefinition RightFin = Body.addOrReplaceChild("RightFin", CubeListBuilder.create().texOffs(52, 51).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-3.0F, 1.0F, -1.0F, 0.0F, -0.3927F, 0.0F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(MaoriWrasse entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.Core.xRot = (headPitch * (Mth.DEG_TO_RAD));
		this.animate(entity.flopAnimationState, MoorishIdolAnimations.FLOP, ageInTicks, 1);
		this.animate(entity.swimAnimationState, MaoriWrasseAnimations.SWIM, ageInTicks, (float) (0.5 + limbSwingAmount * 4.0f));
	}

	@Override
	public ModelPart root() {
		return Root;
	}
}
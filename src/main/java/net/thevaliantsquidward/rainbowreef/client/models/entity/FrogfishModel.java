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
import net.thevaliantsquidward.rainbowreef.client.models.entity.base.ReefModel;
import net.thevaliantsquidward.rainbowreef.entity.Frogfish;

public class FrogfishModel<T extends Frogfish> extends ReefModel<T> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "frogfish"), "main");
	private final ModelPart Root;
	private final ModelPart Core;
	private final ModelPart Body;
	private final ModelPart Fin;
	private final ModelPart Lure;
	private final ModelPart LeftBottomFin;
	private final ModelPart RightBottomFin;
	private final ModelPart LeftFin;
	private final ModelPart RightFin;
	private final ModelPart Mouth;
	private final ModelPart Tail;

	public FrogfishModel(ModelPart root) {
		this.Root = root.getChild("Root");
		this.Core = this.Root.getChild("Core");
		this.Body = this.Core.getChild("Body");
		this.Fin = this.Body.getChild("Fin");
		this.Lure = this.Body.getChild("Lure");
		this.LeftBottomFin = this.Body.getChild("LeftBottomFin");
		this.RightBottomFin = this.Body.getChild("RightBottomFin");
		this.LeftFin = this.Body.getChild("LeftFin");
		this.RightFin = this.Body.getChild("RightFin");
		this.Mouth = this.Body.getChild("Mouth");
		this.Tail = this.Core.getChild("Tail");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition Root = partdefinition.addOrReplaceChild("Root", CubeListBuilder.create(), PartPose.offset(0.0F, 21.0F, 0.0F));

		PartDefinition Core = Root.addOrReplaceChild("Core", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -2.0F));

		PartDefinition Body = Core.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(22, 21).addBox(0.0F, 2.0F, 1.0F, 0.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-2.0F, -3.0F, -3.0F, 4.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
		.texOffs(0, 11).addBox(-2.0F, -4.0F, -3.0F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 2.0F));

		PartDefinition Fin = Body.addOrReplaceChild("Fin", CubeListBuilder.create().texOffs(0, 17).addBox(0.0F, -4.0F, -1.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 1.0F));

		PartDefinition Lure = Body.addOrReplaceChild("Lure", CubeListBuilder.create().texOffs(20, -1).addBox(0.0F, -3.0F, -3.0F, 0.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -3.0F));

		PartDefinition LeftBottomFin = Body.addOrReplaceChild("LeftBottomFin", CubeListBuilder.create().texOffs(22, 17).addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 2.0F, -1.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition RightBottomFin = Body.addOrReplaceChild("RightBottomFin", CubeListBuilder.create().texOffs(22, 17).mirror().addBox(0.0F, 0.0F, -1.0F, 0.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.0F, 2.0F, -1.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition LeftFin = Body.addOrReplaceChild("LeftFin", CubeListBuilder.create().texOffs(10, 17).addBox(0.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 2.0F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition RightFin = Body.addOrReplaceChild("RightFin", CubeListBuilder.create().texOffs(10, 17).mirror().addBox(-2.0F, -1.0F, -2.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0F, 2.0F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition Mouth = Body.addOrReplaceChild("Mouth", CubeListBuilder.create().texOffs(10, 23).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -3.0F, 0.3927F, 0.0F, 0.0F));

		PartDefinition Tail = Core.addOrReplaceChild("Tail", CubeListBuilder.create().texOffs(20, 9).addBox(0.0F, -2.0F, 0.0F, 0.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 5.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Frogfish entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return this.Root;
	}
}
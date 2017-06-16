package io.github.cappycot.kemonocraft.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * ModelGrayWolf - Cappycot<br>
 * Created using Tabula 4.1.1
 */
public class ModelGrayWolf extends ModelBiped {
	public ModelRenderer scarfThingy;
	public ModelRenderer skirtUpper;
	public ModelRenderer animeTiddyR;
	public ModelRenderer animeTiddyL;
	public ModelRenderer tieTop;
	public ModelRenderer tieBottom;
	public ModelRenderer tailUpper;
	public ModelRenderer armCuff3;
	public ModelRenderer armCuff4;
	public ModelRenderer armCuff2;
	public ModelRenderer armCuff1;
	public ModelRenderer legFluffL;
	public ModelRenderer legFluffR;
	public ModelRenderer armCuff1_1;
	public ModelRenderer armCuff2_1;
	public ModelRenderer armCuff3_1;
	public ModelRenderer armCuff4_1;
	public ModelRenderer tailMiddle;
	public ModelRenderer tailTip;
	public ModelRenderer leftEar;
	public ModelRenderer rightEar;
	public ModelRenderer hairBack;
	public ModelRenderer leftEarBack;
	public ModelRenderer leftEarTip;
	public ModelRenderer rightEarBack;
	public ModelRenderer rightEarTip;
	public ModelRenderer scarfThingy2;

	public ModelGrayWolf() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.bipedHeadwear = new ModelRenderer(this, 32, 0);
		this.bipedHeadwear.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedHeadwear.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.5F);
		this.bipedBody = new ModelRenderer(this, 16, 16);
		this.bipedBody.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedBody.addBox(-4.0F, 0.0F, -2.0F, 8, 12, 4, 0.0F);
		this.bipedRightArm = new ModelRenderer(this, 40, 16);
		this.bipedRightArm.setRotationPoint(-5.0F, 2.0F, 0.0F);
		this.bipedRightArm.addBox(-3.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(bipedRightArm, 0.0F, 0.0F, 0.10000000149011613F);
		this.bipedLeftLeg = new ModelRenderer(this, 0, 16);
		this.bipedLeftLeg.mirror = true;
		this.bipedLeftLeg.setRotationPoint(1.9F, 12.0F, 0.0F);
		this.bipedLeftLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.scarfThingy2 = new ModelRenderer(this, 26, 43);
		this.scarfThingy2.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.scarfThingy2.addBox(-5.0F, -0.5F, -2.5F, 10, 2, 5, 0.0F);
		this.skirtUpper = new ModelRenderer(this, 0, 59);
		this.skirtUpper.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.skirtUpper.addBox(-4.0F, 11.4F, -2.0F, 8, 1, 4, 0.4F);
		this.animeTiddyR = new ModelRenderer(this, 0, 41);
		this.animeTiddyR.setRotationPoint(-2.1F, 3.0F, -1.4F);
		this.animeTiddyR.addBox(-2.0F, -1.5F, -1.5F, 4, 3, 3, 0.0F);
		this.setRotateAngle(animeTiddyR, -0.6108652381980153F, -0.13962634015954636F, 0.0F);
		this.armCuff2_1 = new ModelRenderer(this, 48, 32);
		this.armCuff2_1.setRotationPoint(1.2F, 6.0F, 0.2F);
		this.armCuff2_1.addBox(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
		this.rightEarTip = new ModelRenderer(this, 58, 16);
		this.rightEarTip.setRotationPoint(0.0F, -2.0F, 0.1F);
		this.rightEarTip.addBox(-1.0F, -2.0F, -0.5F, 2, 3, 1, 0.0F);
		this.bipedEars = new ModelRenderer(this, 24, 0);
		this.bipedEars.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedEars.addBox(-3.0F, -6.0F, -1.0F, 6, 6, 1, 0.0F);
		this.animeTiddyL = new ModelRenderer(this, 0, 41);
		this.animeTiddyL.mirror = true;
		this.animeTiddyL.setRotationPoint(2.1F, 3.0F, -1.4F);
		this.animeTiddyL.addBox(-2.0F, -1.5F, -1.5F, 4, 3, 3, 0.0F);
		this.setRotateAngle(animeTiddyL, -0.6108652381980153F, 0.13962634015954636F, 0.0F);
		this.rightEarBack = new ModelRenderer(this, 52, 16);
		this.rightEarBack.setRotationPoint(0.0F, -0.5F, 1.0F);
		this.rightEarBack.addBox(-1.0F, -1.5F, -0.5F, 2, 3, 1, 0.0F);
		this.tieTop = new ModelRenderer(this, 14, 41);
		this.tieTop.setRotationPoint(0.0F, 2.4F, -2.0F);
		this.tieTop.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(tieTop, -0.6108652381980153F, 0.0F, 0.0F);
		this.tailUpper = new ModelRenderer(this, 40, 51);
		this.tailUpper.setRotationPoint(0.0F, 10.6F, 2.0F);
		this.tailUpper.addBox(-1.5F, -0.5F, -1.0F, 3, 3, 3, 0.0F);
		this.tieBottom = new ModelRenderer(this, 14, 41);
		this.tieBottom.setRotationPoint(0.0F, 3.9F, -1.9F);
		this.tieBottom.addBox(-0.5F, -1.5F, -0.5F, 1, 3, 1, 0.0F);
		this.setRotateAngle(tieBottom, 0.9599310885968813F, 0.0F, 0.0F);
		this.tailMiddle = new ModelRenderer(this, 25, 53);
		this.tailMiddle.setRotationPoint(0.0F, 1.0F, 1.0F);
		this.tailMiddle.addBox(-2.0F, -2.0F, 0.0F, 4, 4, 7, 0.0F);
		this.bipedRightLeg = new ModelRenderer(this, 0, 16);
		this.bipedRightLeg.setRotationPoint(-1.9F, 12.0F, 0.0F);
		this.bipedRightLeg.addBox(-2.0F, 0.0F, -2.0F, 4, 12, 4, 0.0F);
		this.legFluffL = new ModelRenderer(this, 33, 34);
		this.legFluffL.mirror = true;
		this.legFluffL.setRotationPoint(0.0F, 9.0F, 0.0F);
		this.legFluffL.addBox(-2.5F, -1.0F, -2.5F, 5, 2, 5, 0.0F);
		this.bipedHead = new ModelRenderer(this, 0, 0);
		this.bipedHead.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.bipedHead.addBox(-4.0F, -8.0F, -4.0F, 8, 8, 8, 0.0F);
		this.bipedLeftArm = new ModelRenderer(this, 40, 16);
		this.bipedLeftArm.mirror = true;
		this.bipedLeftArm.setRotationPoint(5.0F, 2.0F, -0.0F);
		this.bipedLeftArm.addBox(-1.0F, -2.0F, -2.0F, 4, 12, 4, 0.0F);
		this.setRotateAngle(bipedLeftArm, 0.0F, 0.0F, -0.10000000149011613F);
		this.leftEarBack = new ModelRenderer(this, 52, 16);
		this.leftEarBack.mirror = true;
		this.leftEarBack.setRotationPoint(0.0F, -0.5F, 1.0F);
		this.leftEarBack.addBox(-1.0F, -1.5F, -0.5F, 2, 3, 1, 0.0F);
		this.hairBack = new ModelRenderer(this, 0, 32);
		this.hairBack.setRotationPoint(0.0F, 0.0F, 3.0F);
		this.hairBack.addBox(-4.0F, -1.0F, 0.0F, 8, 8, 1, -0.1F);
		this.legFluffR = new ModelRenderer(this, 33, 34);
		this.legFluffR.setRotationPoint(0.0F, 9.0F, 0.0F);
		this.legFluffR.addBox(-2.5F, -1.0F, -2.5F, 5, 2, 5, 0.0F);
		this.leftEarTip = new ModelRenderer(this, 58, 16);
		this.leftEarTip.mirror = true;
		this.leftEarTip.setRotationPoint(0.0F, -2.0F, 0.1F);
		this.leftEarTip.addBox(-1.0F, -2.0F, -0.5F, 2, 3, 1, 0.0F);
		this.tailTip = new ModelRenderer(this, 47, 58);
		this.tailTip.setRotationPoint(0.0F, -0.1F, 7.0F);
		this.tailTip.addBox(-1.5F, -1.5F, -1.0F, 3, 3, 3, 0.0F);
		this.armCuff3 = new ModelRenderer(this, 48, 32);
		this.armCuff3.setRotationPoint(-1.2F, 6.0F, -0.2F);
		this.armCuff3.addBox(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
		this.armCuff1_1 = new ModelRenderer(this, 48, 32);
		this.armCuff1_1.setRotationPoint(0.8F, 6.0F, 0.2F);
		this.armCuff1_1.addBox(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
		this.armCuff3_1 = new ModelRenderer(this, 48, 32);
		this.armCuff3_1.setRotationPoint(1.2F, 6.0F, -0.2F);
		this.armCuff3_1.addBox(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
		this.armCuff4_1 = new ModelRenderer(this, 48, 32);
		this.armCuff4_1.setRotationPoint(0.8F, 6.0F, -0.2F);
		this.armCuff4_1.addBox(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
		this.scarfThingy = new ModelRenderer(this, 2, 49);
		this.scarfThingy.setRotationPoint(0.0F, 0.0F, 0.0F);
		this.scarfThingy.addBox(-4.0F, -0.5F, -3.0F, 8, 2, 6, 0.1F);
		this.setRotateAngle(scarfThingy, 0.08726646259971647F, 0.0F, 0.0F);
		this.armCuff1 = new ModelRenderer(this, 48, 32);
		this.armCuff1.setRotationPoint(-0.8F, 6.0F, 0.2F);
		this.armCuff1.addBox(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
		this.rightEar = new ModelRenderer(this, 56, 0);
		this.rightEar.setRotationPoint(-2.3F, -8.0F, -1.0F);
		this.rightEar.addBox(-1.5F, -2.0F, -0.5F, 3, 3, 1, 0.0F);
		this.armCuff4 = new ModelRenderer(this, 48, 32);
		this.armCuff4.setRotationPoint(-0.8F, 6.0F, -0.2F);
		this.armCuff4.addBox(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
		this.leftEar = new ModelRenderer(this, 56, 0);
		this.leftEar.mirror = true;
		this.leftEar.setRotationPoint(2.3F, -8.0F, -1.0F);
		this.leftEar.addBox(-1.5F, -2.0F, -0.5F, 3, 3, 1, 0.0F);
		this.armCuff2 = new ModelRenderer(this, 48, 32);
		this.armCuff2.setRotationPoint(-1.2F, 6.0F, 0.2F);
		this.armCuff2.addBox(-2.0F, -1.0F, -2.0F, 4, 2, 4, 0.0F);
		this.scarfThingy.addChild(this.scarfThingy2);
		this.bipedBody.addChild(this.animeTiddyR);
		this.bipedLeftArm.addChild(this.armCuff2_1);
		this.rightEar.addChild(this.rightEarTip);
		this.bipedBody.addChild(this.animeTiddyL);
		this.rightEar.addChild(this.rightEarBack);
		this.bipedBody.addChild(this.tieTop);
		this.bipedBody.addChild(this.tailUpper);
		this.bipedBody.addChild(this.tieBottom);
		this.tailUpper.addChild(this.tailMiddle);
		this.bipedLeftLeg.addChild(this.legFluffL);
		this.leftEar.addChild(this.leftEarBack);
		this.bipedHead.addChild(this.hairBack);
		this.bipedRightLeg.addChild(this.legFluffR);
		this.leftEar.addChild(this.leftEarTip);
		this.tailMiddle.addChild(this.tailTip);
		this.bipedRightArm.addChild(this.armCuff3);
		this.bipedLeftArm.addChild(this.armCuff1_1);
		this.bipedLeftArm.addChild(this.armCuff3_1);
		this.bipedLeftArm.addChild(this.armCuff4_1);
		this.bipedRightArm.addChild(this.armCuff1);
		this.bipedHead.addChild(this.rightEar);
		this.bipedRightArm.addChild(this.armCuff4);
		this.bipedHead.addChild(this.leftEar);
		this.bipedRightArm.addChild(this.armCuff2);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.bipedLeftLeg.render(f5);
		this.bipedRightArm.render(f5);
		this.skirtUpper.render(f5);
		this.bipedEars.render(f5);
		this.bipedLeftArm.render(f5);
		this.bipedHead.render(f5);
		this.bipedRightLeg.render(f5);
		this.bipedBody.render(f5);
		this.bipedHeadwear.render(f5);
		this.scarfThingy.render(f5);
	}

	@Override
	public void setRotationAngles(float time, float spd, float f2, float yaw, float pitch, float f5, Entity entity) {
		super.setRotationAngles(time, spd, f2, yaw, pitch, f5, entity);
		float sneaky = this.isSneak ? 0.5F : 0;
		this.setRotateAngle(this.hairBack, -this.bipedHead.rotateAngleX + sneaky, 0, 0);
		this.setRotateAngle(this.skirtUpper, this.bipedBody.rotateAngleX, this.bipedBody.rotateAngleY,
				this.bipedBody.rotateAngleZ);
		if (spd > 1)
			spd = 1.0F;
		float tailRaise = (float) Math.toRadians(-45F + 45F * spd * (1 - sneaky));
		// Make period of tailYaw the same as the period of legs. I dun like
		// trig.
		float tailYaw = (float) MathHelper.sin(time * 0.6662F) * 0.25F * spd;
		this.setRotateAngle(this.tailUpper, tailRaise, tailYaw / 2, 0);
		this.setRotateAngle(this.tailMiddle, 0, tailYaw / 2, 0);
		this.setRotateAngle(this.tailTip, 0, tailYaw / 4, 0);
	}

	/**
	 * This is a helper function from Tabula to set the rotation of model parts
	 */
	public void setRotateAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}
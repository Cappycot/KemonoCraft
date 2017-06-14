package io.github.cappycot.kemonocraft.client;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

/**
 * ModelLuckyBeast - Cappycot<br>
 * Created using Tabula 4.1.1
 * 
 */
public class ModelLuckyBeast extends ModelBase {
	public ModelRenderer baseBody;
	public ModelRenderer rightFoot;
	public ModelRenderer leftFoot;
	public ModelRenderer mainBody;
	public ModelRenderer tailBase;
	public ModelRenderer upperBody;
	public ModelRenderer beltLeft;
	public ModelRenderer beltRight;
	public ModelRenderer beltFront;
	public ModelRenderer beltBack;
	public ModelRenderer coreChip;
	public ModelRenderer leftEar;
	public ModelRenderer rightEar;
	public ModelRenderer coreLight;
	public ModelRenderer tailMid;
	public ModelRenderer tailTip;

	public ModelLuckyBeast() {
		this.textureWidth = 32;
		this.textureHeight = 32;
		this.coreLight = new ModelRenderer(this, 25, 3);
		this.coreLight.setRotationPoint(0.0F, 0.0F, -0.1F);
		this.coreLight.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 1, 0.0F);
		this.leftEar = new ModelRenderer(this, 17, 0);
		this.leftEar.mirror = true;
		this.leftEar.setRotationPoint(1.3F, -3.5F, 0.0F);
		this.leftEar.addBox(-1.0F, -2.0F, -1.0F, 2, 3, 2, 0.0F);
		this.mainBody = new ModelRenderer(this, 0, 10);
		this.mainBody.setRotationPoint(0.0F, -2.5F, 0.0F);
		this.mainBody.addBox(-3.5F, -2.5F, -3.0F, 7, 4, 6, 0.0F);
		this.tailMid = new ModelRenderer(this, 8, 26);
		this.tailMid.setRotationPoint(0.0F, 0.0F, 2.0F);
		this.tailMid.addBox(-1.0F, -1.2F, -1.0F, 2, 2, 4, 0.0F);
		this.tailBase = new ModelRenderer(this, 0, 28);
		this.tailBase.setRotationPoint(0.0F, -0.5F, 2.0F);
		this.tailBase.addBox(-0.5F, -0.5F, -0.5F, 1, 1, 3, 0.0F);
		this.setRotateAngle(tailBase, 0.0F, 0.0F, 0.0041887902047863905F);
		this.coreChip = new ModelRenderer(this, 25, 0);
		this.coreChip.setRotationPoint(0.0F, 0.2F, -2.6F);
		this.coreChip.addBox(-1.0F, -1.0F, -0.5F, 2, 2, 1, 0.0F);
		this.rightFoot = new ModelRenderer(this, 20, 10);
		this.rightFoot.setRotationPoint(-1.5F, 23.0F, 1.0F);
		this.rightFoot.addBox(-1.0F, 0.0F, -4.0F, 2, 1, 4, 0.0F);
		this.upperBody = new ModelRenderer(this, 0, 0);
		this.upperBody.setRotationPoint(0.0F, -3.0F, 0.0F);
		this.upperBody.addBox(-3.0F, -3.0F, -2.5F, 6, 5, 5, 0.0F);
		this.rightEar = new ModelRenderer(this, 17, 0);
		this.rightEar.setRotationPoint(-1.3F, -3.5F, 0.0F);
		this.rightEar.addBox(-1.0F, -2.0F, -1.0F, 2, 3, 2, 0.0F);
		this.beltFront = new ModelRenderer(this, 16, 26);
		this.beltFront.setRotationPoint(0.0F, -1.6F, -2.6F);
		this.beltFront.addBox(-3.5F, -0.5F, -0.5F, 7, 1, 1, 0.0F);
		this.leftFoot = new ModelRenderer(this, 20, 10);
		this.leftFoot.mirror = true;
		this.leftFoot.setRotationPoint(1.5F, 23.0F, 1.0F);
		this.leftFoot.addBox(-1.0F, 0.0F, -4.0F, 2, 1, 4, 0.0F);
		this.beltLeft = new ModelRenderer(this, 18, 21);
		this.beltLeft.mirror = true;
		this.beltLeft.setRotationPoint(3.1F, -1.6F, 0.0F);
		this.beltLeft.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 6, 0.0F);
		this.baseBody = new ModelRenderer(this, 0, 20);
		this.baseBody.setRotationPoint(0.0F, 23.0F, 0.0F);
		this.baseBody.addBox(-3.0F, -1.0F, -2.5F, 6, 1, 5, 0.0F);
		this.beltRight = new ModelRenderer(this, 18, 21);
		this.beltRight.setRotationPoint(-3.1F, -1.6F, 0.0F);
		this.beltRight.addBox(-0.5F, -0.5F, -3.0F, 1, 1, 6, 0.0F);
		this.beltBack = new ModelRenderer(this, 16, 26);
		this.beltBack.setRotationPoint(0.0F, -1.6F, 2.6F);
		this.beltBack.addBox(-3.5F, -0.5F, -0.5F, 7, 1, 1, 0.0F);
		this.tailTip = new ModelRenderer(this, 20, 28);
		this.tailTip.setRotationPoint(0.0F, -0.2F, 2.5F);
		this.tailTip.addBox(-0.5F, -0.5F, -1.5F, 1, 1, 3, 0.0F);
		this.coreChip.addChild(this.coreLight);
		this.upperBody.addChild(this.leftEar);
		this.baseBody.addChild(this.mainBody);
		this.tailBase.addChild(this.tailMid);
		this.baseBody.addChild(this.tailBase);
		this.mainBody.addChild(this.coreChip);
		this.mainBody.addChild(this.upperBody);
		this.upperBody.addChild(this.rightEar);
		this.mainBody.addChild(this.beltFront);
		this.mainBody.addChild(this.beltLeft);
		this.mainBody.addChild(this.beltRight);
		this.mainBody.addChild(this.beltBack);
		this.tailMid.addChild(this.tailTip);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
		this.rightFoot.render(f5);
		this.leftFoot.render(f5);
		this.baseBody.render(f5);
	}

	private static float maxPitch = 20;

	@Override
	public void setRotationAngles(float time, float spd, float f2, float yaw, float pitch, float f5, Entity entity) {
		float upperPitch;
		if (Math.abs(pitch) > maxPitch * 2) {
			pitch += Math.signum(pitch) == -1 ? maxPitch : -maxPitch;
			upperPitch = maxPitch * Math.signum(pitch);
		} else {
			pitch /= 2;
			upperPitch = pitch;
		}
		if (spd > 1F)
			spd = 1F;
		// Method gives degrees but angles require radians.
		pitch = (float) Math.toRadians(pitch);
		upperPitch = (float) Math.toRadians(upperPitch);
		yaw = (float) Math.toRadians(yaw);
		float footTime = (float) MathHelper.sin(time * 2) * 0.05F * spd;
		float tail = (float) MathHelper.sin(time) * 0.25F * spd;
		setRotateAngle(this.upperBody, upperPitch, 0, 0);
		setRotateAngle(this.baseBody, pitch, yaw, 0);
		setRotateAngle(this.leftEar, upperPitch, 0, 0);
		setRotateAngle(this.rightEar, upperPitch, 0, 0);
		setRotateAngle(this.tailBase, -pitch, tail, 0);
		setRotateAngle(this.tailMid, 0, tail, 0);
		setRotateAngle(this.tailTip, 0, tail, 0);
		leftFoot.offsetZ = footTime;
		rightFoot.offsetZ = -footTime;
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
package io.github.cappycot.kemonocraft.client;

import io.github.cappycot.kemonocraft.Resource;
import io.github.cappycot.kemonocraft.entity.EntityLuckyBeast;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderLuckyBeast extends RenderLiving {

	private static final ResourceLocation mobTexture = new ResourceLocation(Resource.entityTexture("luckybeast"));

	public RenderLuckyBeast(ModelBase p_i1262_1_, float p_i1262_2_) {
		super(p_i1262_1_, p_i1262_2_);
	}

	protected ResourceLocation getEntityTexture(EntityLuckyBeast entity) {
		return mobTexture;
	}

	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return this.getEntityTexture((EntityLuckyBeast) entity);
	}

}
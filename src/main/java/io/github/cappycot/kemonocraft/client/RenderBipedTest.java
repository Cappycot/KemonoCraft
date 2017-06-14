package io.github.cappycot.kemonocraft.client;

import io.github.cappycot.kemonocraft.Resource;
import io.github.cappycot.kemonocraft.entity.EntityLuckyBeast;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.renderer.entity.RenderBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;

public class RenderBipedTest extends RenderBiped {
	
	private static final ResourceLocation mobTexture = new ResourceLocation(Resource.entityTexture("graywolf"));

	public RenderBipedTest(ModelBiped p_i1257_1_, float p_i1257_2_) {
		super(p_i1257_1_, p_i1257_2_);
	}

	public RenderBipedTest(ModelBiped p_i1258_1_, float p_i1258_2_, float p_i1258_3_) {
		super(p_i1258_1_, p_i1258_2_, p_i1258_3_);
	}
	
	@Override
	protected ResourceLocation getEntityTexture(Entity entity) {
		return mobTexture;
	}

}

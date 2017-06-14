package io.github.cappycot.kemonocraft.proxy;

import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import io.github.cappycot.kemonocraft.client.ModelGrayWolf;
import io.github.cappycot.kemonocraft.client.ModelLuckyBeast;
import io.github.cappycot.kemonocraft.client.RenderBipedTest;
import io.github.cappycot.kemonocraft.client.RenderLuckyBeast;
import io.github.cappycot.kemonocraft.entity.EntityBipedTest;
import io.github.cappycot.kemonocraft.entity.EntityLuckyBeast;

public class ClientProxy extends CommonProxy {

	@Override
	public void preInit(FMLPreInitializationEvent event) {
		super.preInit(event);
		// TODO: Move renderers
		RenderingRegistry.registerEntityRenderingHandler(EntityLuckyBeast.class,
				new RenderLuckyBeast(new ModelLuckyBeast(), 0));
		RenderingRegistry.registerEntityRenderingHandler(EntityBipedTest.class,
				new RenderBipedTest(new ModelGrayWolf(), 0));
	}

	@Override
	public void init(FMLInitializationEvent event) {
		super.init(event);
	}

	@Override
	public void postInit(FMLPostInitializationEvent event) {
		super.postInit(event);
	}

}

package io.github.cappycot.kemonocraft;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import io.github.cappycot.kemonocraft.proxy.CommonProxy;

@Mod(modid = KemonoCraft.MOD_ID, name = KemonoCraft.MOD_NAME, version = KemonoCraft.VERSION, dependencies = "required-after:Forge@[10.13.4.1614,)")
public class KemonoCraft {

	public static final String MOD_ID = "kemonocraft";
	public static final String MOD_NAME = "KemonoCraft";
	public static final String VERSION = "0.0.1";

	@SidedProxy(clientSide = "io.github.cappycot.kemonocraft.proxy.ClientProxy", serverSide = "io.github.cappycot.kemonocraft.proxy.ServerProxy")
	public static CommonProxy proxy;

	@Instance(value = MOD_ID)
	public static KemonoCraft instance = new KemonoCraft();

	@EventHandler
	public void preInit(final FMLPreInitializationEvent event) {
		proxy.preInit(event);
	}

	@EventHandler
	public void init(final FMLInitializationEvent event) {
		proxy.init(event);
	}

	@EventHandler
	public void postInit(final FMLPostInitializationEvent event) {
		proxy.postInit(event);
	}
}

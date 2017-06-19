package io.github.cappycot.kemonocraft.proxy;

import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import io.github.cappycot.kemonocraft.KemonoCraft;
import io.github.cappycot.kemonocraft.block.SandstarOre;
import io.github.cappycot.kemonocraft.entity.EntityBipedTest;
import io.github.cappycot.kemonocraft.entity.EntityLuckyBeast;
import io.github.cappycot.kemonocraft.item.KemonoRecord;
import io.github.cappycot.kemonocraft.worldgen.JapariCafeGenerator;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityList;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class CommonProxy {

	public static CreativeTabs kemonoTab;
	public static Item japariCoin;
	public static KemonoRecord recordOozora;
	public static KemonoRecord recordParade;
	public static KemonoRecord recordYoukoso;
	public static KemonoRecord recordYoukosoMetal;
	public static SandstarOre sandstarOre;

	public void preInit(FMLPreInitializationEvent event) {
		// TODO: Move blocks to some block manager class
		sandstarOre = new SandstarOre("sandstarOre");
		GameRegistry.registerBlock(sandstarOre, "sandstarOre");
		// TODO: Move item to some item manager class
		kemonoTab = new CreativeTabs(KemonoCraft.MOD_ID) {
			@Override
			public Item getTabIconItem() {
				return japariCoin;
			}
		};
		japariCoin = new Item().setUnlocalizedName("japariCoin").setCreativeTab(kemonoTab)
				.setTextureName(KemonoCraft.MOD_ID + ":japariCoin");
		recordOozora = (KemonoRecord) new KemonoRecord("Oozora").setUnlocalizedName("recordOozora")
				.setCreativeTab(kemonoTab);
		recordParade = (KemonoRecord) new KemonoRecord("Parade").setUnlocalizedName("recordParade")
				.setCreativeTab(kemonoTab);
		recordYoukoso = (KemonoRecord) new KemonoRecord("Youkoso").setUnlocalizedName("recordYoukoso")
				.setCreativeTab(kemonoTab);
		recordYoukosoMetal = (KemonoRecord) new KemonoRecord("YoukosoMetal").setUnlocalizedName("recordYoukosoMetal")
				.setCreativeTab(kemonoTab);
		GameRegistry.registerItem(japariCoin, "japariCoin");
		GameRegistry.registerItem(recordOozora, "recordOozora");
		GameRegistry.registerItem(recordParade, "recordParade");
		GameRegistry.registerItem(recordYoukoso, "recordYoukoso");
		GameRegistry.registerItem(recordYoukosoMetal, "recordYoukosoMetal");
		// TODO: Move recipe registers to different class
		GameRegistry.addRecipe(new ItemStack(japariCoin, 8),
				new Object[] { "GGG", "GEG", "GGG", 'E', Items.emerald, 'G', Items.gold_ingot });
		// TODO: Move entity code test
		int id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityLuckyBeast.class, "Lucky Beast", id);
		EntityRegistry.registerModEntity(EntityLuckyBeast.class, "Lucky Beast", id, KemonoCraft.instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(id), new EntityList.EntityEggInfo(id, 0x00AACC, 0x001010));
		id = EntityRegistry.findGlobalUniqueEntityId();
		EntityRegistry.registerGlobalEntityID(EntityBipedTest.class, "bipedTest", id);
		EntityRegistry.registerModEntity(EntityBipedTest.class, "bipedTest", id, KemonoCraft.instance, 64, 1, true);
		EntityList.entityEggs.put(Integer.valueOf(id), new EntityList.EntityEggInfo(id, 0x00AACD, 0x001010));
	}

	public void init(FMLInitializationEvent event) {
		GameRegistry.registerWorldGenerator(new JapariCafeGenerator(), 100);
	}

	public void postInit(FMLPostInitializationEvent event) {

	}

}

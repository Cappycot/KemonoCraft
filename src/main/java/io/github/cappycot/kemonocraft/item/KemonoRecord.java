package io.github.cappycot.kemonocraft.item;

import java.util.HashMap;
import java.util.List;

import io.github.cappycot.kemonocraft.KemonoCraft;
import net.minecraft.block.BlockJukebox;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemRecord;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;

public class KemonoRecord extends ItemRecord {

	private static final HashMap<String, KemonoRecord> records = new HashMap<String, KemonoRecord>();

	public final String name;

	public KemonoRecord(String song) {
		super(song);
		this.maxStackSize = 1;
		this.name = song;
		records.put(song, this);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister) {
		itemIcon = iconRegister.registerIcon(KemonoCraft.MOD_ID + ":record" + recordName);
	}

	@Override
	public boolean onItemUse(ItemStack itemStack, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_,
			float p_77648_8_, float p_77648_9_, float p_77648_10_) {
		if (world.getBlock(x, y, z) == Blocks.jukebox && world.getBlockMetadata(x, y, z) == 0) {
			if (world.isRemote)
				return true;
			else {
				((BlockJukebox) Blocks.jukebox).func_149926_b(world, x, y, z, itemStack);
				world.playAuxSFXAtEntity((EntityPlayer) null, 1005, x, y, z, Item.getIdFromItem(this));
				--itemStack.stackSize;
				return true;
			}
		} else
			return false;
	}

	/**
	 * That feeling when you're too lazy to change up the variable names.
	 */
	@Override
	public void addInformation(ItemStack p_77624_1_, EntityPlayer p_77624_2_, List p_77624_3_, boolean p_77624_4_) {
		p_77624_3_.add(this.getRecordNameLocal());
		p_77624_3_.add(StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc2"));
	}

	@Override
	public EnumRarity getRarity(ItemStack itemStack) {
		return EnumRarity.rare;
	}

	@Override
	public String getRecordNameLocal() {
		return StatCollector.translateToLocal(this.getUnlocalizedName() + ".desc");
	}

	@Override
	public ResourceLocation getRecordResource(String name) {
		return new ResourceLocation(KemonoCraft.MOD_ID + ":" + name);
	}

	public static KemonoRecord getRecord(String song) {
		return records.get(song);
	}
}

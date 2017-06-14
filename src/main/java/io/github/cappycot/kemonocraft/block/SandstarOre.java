package io.github.cappycot.kemonocraft.block;

import io.github.cappycot.kemonocraft.Resource;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class SandstarOre extends Block {

	public SandstarOre(String name) {
		super(Material.rock);
		this.setBlockName(name);
		this.setBlockTextureName(Resource.blockTexture(name));
		this.setCreativeTab(CreativeTabs.tabBlock);
		this.setHardness(3.0F);
		this.setHarvestLevel("pickaxe", 0);
		this.setLightLevel(0.5F);
		this.setResistance(5.0F);
	}
}

package io.github.cappycot.kemonocraft.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

public class EntityBipedTest extends EntityTameable {

	public EntityBipedTest(World p_i1604_1_) {
		super(p_i1604_1_);
		this.setSize(0.6F, 1.8F);
		this.tasks.addTask(0, new EntityAISwimming(this));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(1, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
	}
	
	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return null;
	}
	
	@Override
	protected boolean isAIEnabled() {
		return true;
	}

}

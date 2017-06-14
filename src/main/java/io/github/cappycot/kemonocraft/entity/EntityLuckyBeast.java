package io.github.cappycot.kemonocraft.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIOpenDoor;
import net.minecraft.entity.ai.EntityAIPanic;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityLuckyBeast extends EntityTameable {

	private boolean regen = true;

	public EntityLuckyBeast(World p_i1738_1_) {
		super(p_i1738_1_);
		this.setSize(0.4375F, 0.75F);
		this.getNavigator().setAvoidsWater(true);
		this.getNavigator().setBreakDoors(true); // Run into doors apparently...
		this.getNavigator().setCanSwim(false);
		this.tasks.addTask(0, new EntityAIOpenDoor(this, true));
		this.tasks.addTask(1, new EntityAIPanic(this, 1.5D));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
		this.tasks.addTask(2, new EntityAIWatchClosest(this, EntityLuckyBeast.class, 8.0F));
		this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityLiving.class, 8.0F));
		this.tasks.addTask(4, new EntityAIWander(this, 1D));
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.25D);
	}

	@Override
	public EntityAgeable createChild(EntityAgeable p_90011_1_) {
		return null;
	}

	@Override
	public float getEyeHeight() {
		return 0.625F;
	}

	@Override
	protected boolean isAIEnabled() {
		return true;
	}

	@Override
	public void onDeath(DamageSource dmg) {
		// Prevent health regen when dropping dead.
		this.regen = false;
		super.onDeath(dmg);
	}

	@Override
	public void onItemPickup(Entity thing, int num) {
		super.onItemPickup(thing, num);
	}

	@Override
	public void onLivingUpdate() {
		if (!this.worldObj.isRemote) {
			if (this.isInWater()) {
				// Poor thing can't swim. Episode 12 spoilers tbh.
				this.attackEntityFrom(DamageSource.drown, 1.0F);
			} else if (this.ticksExisted % 100 == 0 && this.getHealth() < this.getMaxHealth() && regen) {
				this.setHealth(this.getHealth() + 1);
			}
		}
		super.onLivingUpdate();
	}
}
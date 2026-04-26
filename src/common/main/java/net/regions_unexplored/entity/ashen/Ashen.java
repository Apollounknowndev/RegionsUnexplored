package net.regions_unexplored.entity.ashen;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Ashen extends Zombie {
	public Ashen(EntityType<? extends Zombie> type, Level level) {
		super(type, level);
	}
	
	@Override
	protected boolean isSunSensitive() {
		return false;
	}
	
	@Override
	protected SoundEvent getAmbientSound() {
		return SoundEvents.HUSK_AMBIENT;
	}
	
	@Override
	protected SoundEvent getHurtSound(DamageSource source) {
		return SoundEvents.HUSK_HURT;
	}
	
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.HUSK_DEATH;
	}
	
	@Override
	protected SoundEvent getStepSound() {
		return SoundEvents.HUSK_STEP;
	}
	
	@Override
	protected boolean convertsInWater() {
		return false;
	}
	
	@Override
	protected ItemStack getSkull() {
		return ItemStack.EMPTY;
	}
}

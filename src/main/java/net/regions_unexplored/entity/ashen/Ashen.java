package net.regions_unexplored.entity.ashen;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.monster.zombie.Zombie;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

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
	public boolean fireImmune() {
		return true;
	}
	
	@Override
	protected void populateDefaultEquipmentSlots(final RandomSource random, final DifficultyInstance difficulty) {
		super.populateDefaultEquipmentSlots(random, difficulty);
		this.setBaby(false);
	}
	
	@Override
	public SpawnGroupData finalizeSpawn(
		ServerLevelAccessor level, DifficultyInstance difficulty, EntitySpawnReason spawnReason, SpawnGroupData groupData
	) {
		groupData = super.finalizeSpawn(level, difficulty, spawnReason, groupData);
		this.setBaby(false);
		return groupData;
	}
}

package net.regions_unexplored.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.regions_unexplored.registry.RUBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(WorldCarver.class)
public abstract class WorldCarverMixin {
	@WrapOperation(
		method = "carveBlock",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z",
			ordinal = 0
		)
	)
	private boolean fixGrassQuery(BlockState state, Object block, Operation<Boolean> operation) {
		return operation.call(state, block) || operation.call(state, RUBlocks.PEAT_GRASS_BLOCK.get()) || operation.call(state, RUBlocks.SILT_GRASS_BLOCK.get());
	}
	
	@WrapOperation(
		method = "carveBlock",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/level/block/state/BlockState;is(Ljava/lang/Object;)Z",
			ordinal = 2
		)
	)
	private boolean fixDirtQuery(BlockState state, Object block, Operation<Boolean> operation) {
		return operation.call(state, block) || operation.call(state, RUBlocks.PEAT_DIRT.get()) || operation.call(state, RUBlocks.SILT_DIRT.get());
	}
}

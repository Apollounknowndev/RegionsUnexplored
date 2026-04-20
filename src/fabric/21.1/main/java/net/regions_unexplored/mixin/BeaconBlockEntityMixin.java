package net.regions_unexplored.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BeaconBlockEntity;
import net.regions_unexplored.block.type.misc.PrismaglassBlock;
import net.regions_unexplored.client.color.RuColors;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BeaconBlockEntity.class)
public abstract class BeaconBlockEntityMixin {
	@WrapOperation(
		method = "tick",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/item/DyeColor;getTextureDiffuseColor()I"
		)
	)
	private static int applyPrismaglassColor(DyeColor color, Operation<Integer> operation, Level level, BlockPos pos, @Local(ordinal = 0) Block block) {
		if (block instanceof PrismaglassBlock) {
			return RuColors.getRainbowColor(pos.getX(), pos.getZ() + level.getGameTime() / 8f);
		}
		return operation.call(color);
	}
}
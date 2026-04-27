package net.regions_unexplored.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.InteractionHand;
import net.regions_unexplored.registry.RUItems;
import org.checkerframework.checker.units.qual.A;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {
	@WrapOperation(
		method = {
			"aiStep",
			"canStartSprinting"
		},
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/client/player/LocalPlayer;isUsingItem()Z"
		)
	)
	private boolean ignoreUsingItem(LocalPlayer player, Operation<Boolean> operation) {
		return operation.call(player) && !player.getItemInHand(player.getUsedItemHand()).is(RUItems.IRIDESCENT_RING.get());
	}
}

package net.regions_unexplored.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.regions_unexplored.registry.tag.RUBiomeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.stream.Stream;

@Mixin(HolderLookup.class)
public interface HolderLookupMixin {
	@WrapOperation(
		method = "listElementIds",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/HolderLookup;listElements()Ljava/util/stream/Stream;"
		)
	)
	private <T> Stream<Holder.Reference<T>> hideRemovedBiomes(HolderLookup<T> $this, Operation<Stream<Holder.Reference<T>>> operation) {
		return operation.call($this).filter(holder -> !holder.key().isFor(Registries.BIOME) || !holder.is((TagKey<T>) RUBiomeTags.REMOVED));
	}
}

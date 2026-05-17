package net.regions_unexplored.mixin.removals;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.screens.CreateBuffetWorldScreen;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.regions_unexplored.registry.tag.RUBiomeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.stream.Stream;

@Mixin(CreateBuffetWorldScreen.BiomeList.class)
public class BiomeListMixin {
	@WrapOperation(
		method = "<init>",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/core/Registry;holders()Ljava/util/stream/Stream;"
		)
	)
	private Stream<Holder.Reference<Biome>> hideRemovedBiomes(Registry<Biome> registry, Operation<Stream<Holder.Reference<Biome>>> operation) {
		return operation.call(registry).filter(holder -> !holder.is(RUBiomeTags.REMOVED));
	}
}

package net.regions_unexplored.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.regions_unexplored.registry.RUBlocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(TreeConfiguration.class)
public abstract class TreeConfigurationMixin {
    @WrapOperation(
        method = "<clinit>",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/level/levelgen/feature/stateproviders/RuleBasedStateProvider;ifTrueThenProvide(Lnet/minecraft/world/level/levelgen/blockpredicates/BlockPredicate;Lnet/minecraft/world/level/block/Block;)Lnet/minecraft/world/level/levelgen/feature/stateproviders/RuleBasedStateProvider;"
        )
    )
    private static RuleBasedStateProvider addRUDirt(BlockPredicate predicate, Block block, Operation<RuleBasedStateProvider> operation) {
        return RuleBasedStateProvider.builder()
            .ifTrueThenProvide(BlockPredicate.matchesBlocks(RUBlocks.PEAT_GRASS_BLOCK.get(), RUBlocks.PEAT_DIRT.get()), RUBlocks.PEAT_DIRT.get())
            .ifTrueThenProvide(BlockPredicate.matchesBlocks(RUBlocks.SILT_GRASS_BLOCK.get(), RUBlocks.SILT_DIRT.get()), RUBlocks.SILT_DIRT.get())
            .ifTrueThenProvide(predicate, block)
            .build();
    }
}

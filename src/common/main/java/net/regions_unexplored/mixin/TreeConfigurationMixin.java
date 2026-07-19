package net.regions_unexplored.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.regions_unexplored.registry.data.RUBlockIds;
import net.regions_unexplored.registry.tag.RUBlockTags;
import net.regions_unexplored.worldgen.stateprovider.KeyHackStateProvider;
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
            .ifTrueThenProvide(BlockPredicate.matchesTag(RUBlockTags.PEAT_SUBSTRATE), new KeyHackStateProvider(RUBlockIds.PEAT_DIRT))
            .ifTrueThenProvide(BlockPredicate.matchesTag(RUBlockTags.SILT_SUBSTRATE), new KeyHackStateProvider(RUBlockIds.SILT_DIRT))
            .ifTrueThenProvide(predicate, block)
            .build();
    }
}

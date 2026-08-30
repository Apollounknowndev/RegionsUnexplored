package net.regions_unexplored.mixin;

import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.FeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.rootplacers.RootPlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.data.RUBlockIds;
import net.regions_unexplored.registry.tag.RUBlockTags;
import net.regions_unexplored.worldgen.stateprovider.KeyHackStateProvider;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.Optional;

@Mixin(TreeConfiguration.class)
public abstract class TreeConfigurationMixin {
    @Shadow @Mutable @Final
    public BlockStateProvider belowTrunkProvider;
    
    @Inject(
        method = "<init>",
        at = @At("RETURN")
    )
    private void addRUDirt(BlockStateProvider trunkProvider, TrunkPlacer trunkPlacer, BlockStateProvider foliageProvider, FoliagePlacer foliagePlacer, Optional<RootPlacer> rootPlacer, FeatureSize minimumSize, List<TreeDecorator> decorators, boolean ignoreVines, BlockStateProvider belowTrunkProvider, CallbackInfo ci) {
        this.belowTrunkProvider = RuleBasedStateProvider.builder(belowTrunkProvider)
            .ifTrueThenProvide(BlockPredicate.matchesTag(RUBlockTags.PEAT_SUBSTRATE), new KeyHackStateProvider(RUBlockIds.PEAT_DIRT))
            .ifTrueThenProvide(BlockPredicate.matchesTag(RUBlockTags.SILT_SUBSTRATE), new KeyHackStateProvider(RUBlockIds.SILT_DIRT))
            .build();
    }
}

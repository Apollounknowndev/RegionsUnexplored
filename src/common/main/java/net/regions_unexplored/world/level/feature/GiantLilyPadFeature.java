package net.regions_unexplored.world.level.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.regions_unexplored.block.type.aquatic.GiantLilyPadBlock;

public class GiantLilyPadFeature extends Feature<NoneFeatureConfiguration> {
    public GiantLilyPadFeature(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> context) {
        return GiantLilyPadBlock.tryPlace(context.level(), context.origin(), context.random());
    }
}
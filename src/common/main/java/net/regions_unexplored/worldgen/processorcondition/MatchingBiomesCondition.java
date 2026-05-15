package net.regions_unexplored.worldgen.processorcondition;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import dev.worldgen.lithostitched.api.worldgen.processorcondition.ProcessorCondition;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.QuartPos;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings;

public record MatchingBiomesCondition(HolderSet<Biome> biomes) implements ProcessorCondition {
	public static final MapCodec<MatchingBiomesCondition> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
		Biome.LIST_CODEC.fieldOf("biomes").forGetter(MatchingBiomesCondition::biomes)
	).apply(i, MatchingBiomesCondition::new));
	
	@Override
	public boolean test(WorldGenLevel level, Data data, StructurePlaceSettings placeSettings, RandomSource random) {
		if (!(level.getChunkSource() instanceof ServerChunkCache source)) return false;
		BlockPos pos = data.pos();
		Holder<Biome> biome = source.getGenerator().getBiomeSource().getNoiseBiome(
			QuartPos.fromBlock(pos.getX()), QuartPos.fromBlock(pos.getY()), QuartPos.fromBlock(pos.getZ()),
			source.randomState().sampler()
		);
		return this.biomes.contains(biome);
	}
	
	@Override
	public MapCodec<? extends ProcessorCondition> codec() {
		return CODEC;
	}
}

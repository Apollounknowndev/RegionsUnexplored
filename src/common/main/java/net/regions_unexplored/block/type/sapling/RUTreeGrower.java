package net.regions_unexplored.block.type.sapling;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;

import static java.util.Optional.empty;

public class RUTreeGrower {
	private static final Map<String, RUTreeGrower> GROWERS = new Object2ObjectArrayMap<>();
	private static final List<BlockPos> OFFSETS_3X3_SQUARE = List.of(
		new BlockPos(-1, 0, -1),
		new BlockPos(-1, 0, 0),
		new BlockPos(-1, 0, 1),
		new BlockPos(0, 0, -1),
		new BlockPos(0, 0, 0),
		new BlockPos(0, 0, 1),
		new BlockPos(1, 0, -1),
		new BlockPos(1, 0, 0),
		new BlockPos(1, 0, 1)
	);
	private static final List<BlockPos> OFFSETS_3X3_PLUS = List.of(
		new BlockPos(-1, 0, 0),
		new BlockPos(0, 0, -1),
		new BlockPos(0, 0, 0),
		new BlockPos(0, 0, 1),
		new BlockPos(1, 0, 0)
	);
	private static final List<BlockPos> OFFSETS_2X2 = List.of(
		new BlockPos(0, 0, 0),
		new BlockPos(1, 0, 0),
		new BlockPos(1, 0, 1),
		new BlockPos(0, 0, 1)
	);
	private static final List<BlockPos> OFFSETS_1X1 = List.of(BlockPos.ZERO);
	public static final Codec<RUTreeGrower> CODEC = Codec.stringResolver(g -> g.name, GROWERS::get);
	
	private final String name;
	private final float secondaryChance;
	private final Optional<Entry> tree1x1;
	private final Optional<Entry> tree2x2;
	private final Optional<Entry> tree3x3Plus;
	private final Optional<Entry> tree3x3Square;
	
	public RUTreeGrower(String name, float secondaryChance, Optional<Entry> tree1x1, Optional<Entry> tree2x2, Optional<Entry> tree3x3Plus, Optional<Entry> tree3x3Square) {
		this.name = name;
		this.secondaryChance = secondaryChance;
		this.tree1x1 = tree1x1;
		this.tree2x2 = tree2x2;
		this.tree3x3Plus = tree3x3Plus;
		this.tree3x3Square = tree3x3Square;
	}
	
	public static RUTreeGrower create1x1(String name, ResourceKey<ConfiguredFeature<?, ?>> key) {
		return new RUTreeGrower(name, 0f, Entry.of(key), empty(), empty(), empty());
	}
	
	public static RUTreeGrower create1x1(String name, float secondaryChance, ResourceKey<ConfiguredFeature<?, ?>> primary, ResourceKey<ConfiguredFeature<?, ?>> secondary) {
		return new RUTreeGrower(name, secondaryChance, Entry.of(primary, secondary), empty(), empty(), empty());
	}
	
	public static RUTreeGrower create2x2(String name, ResourceKey<ConfiguredFeature<?, ?>> key) {
		return new RUTreeGrower(name, 0f, empty(), Entry.of(key), empty(), empty());
	}
	
	public static RUTreeGrower create3x3Plus(String name, ResourceKey<ConfiguredFeature<?, ?>> key) {
		return new RUTreeGrower(name, 0f, empty(), empty(), Entry.of(key), empty());
	}
	
	public static RUTreeGrower create3x3Square(String name, ResourceKey<ConfiguredFeature<?, ?>> key) {
		return new RUTreeGrower(name, 0f, empty(), empty(), empty(), Entry.of(key));
	}
	
	private @Nullable ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(Optional<Entry> optional, RandomSource random) {
		if (optional.isEmpty()) return null;
		Entry entry = optional.get();
		if (entry.secondary().isPresent() && random.nextFloat() < this.secondaryChance) return entry.secondary().get();
		return entry.primary();
	}
	
	public boolean growTree(ServerLevel level, ChunkGenerator generator, BlockPos pos, BlockState state, RandomSource random) {
		Registry<ConfiguredFeature<?, ?>> registry = level.registryAccess().lookupOrThrow(Registries.CONFIGURED_FEATURE);
		Result placementResult;
		
		placementResult = tryGrow(OFFSETS_3X3_SQUARE, this.tree3x3Square, level, registry, generator, pos, state, random);
		if (placementResult != Result.CONTINUE) return placementResult == Result.SUCCESS;
		
		placementResult = tryGrow(OFFSETS_3X3_PLUS, this.tree3x3Plus, level, registry, generator, pos, state, random);
		if (placementResult != Result.CONTINUE) return placementResult == Result.SUCCESS;
		
		placementResult = tryGrow(OFFSETS_2X2, this.tree2x2, level, registry, generator, pos, state, random);
		if (placementResult != Result.CONTINUE) return placementResult == Result.SUCCESS;
		
		placementResult = tryGrow(OFFSETS_1X1, this.tree1x1, level, registry, generator, pos, state, random);
		if (placementResult != Result.CONTINUE) return placementResult == Result.SUCCESS;
		
		return false;
	}
	
	private Result tryGrow(List<BlockPos> offsets, Optional<Entry> entry, ServerLevel level, Registry<ConfiguredFeature<?, ?>> registry, ChunkGenerator generator, BlockPos origin, BlockState state, RandomSource random) {
		var key = this.getConfiguredFeature(entry, random);
		if (key == null) return Result.CONTINUE;
		var feature = registry.getOptional(key).orElse(null);
		if (feature == null) return Result.CONTINUE;
		
		for (int offsetX = -1; offsetX <= 1; offsetX++) {
			for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
				BlockPos pos = origin.offset(offsetX, 0, offsetZ);
				
				boolean validSapling = true;
				for (BlockPos offset : offsets) {
					if (!level.getBlockState(pos.offset(offset)).is(state.getBlock())) {
						validSapling = false;
						break;
					}
				}
				if (!validSapling) continue;
				
				for (BlockPos offset : offsets) {
					level.setBlock(pos.offset(offset), Blocks.AIR.defaultBlockState(), 260);
				}
				if (feature.place(level, generator, random, pos)) {
					return Result.SUCCESS;
				}
				
				for (BlockPos offset : offsets) {
					level.setBlock(pos.offset(offset), state, 260);
				}
				return Result.FAILURE;
			}
		}
		return Result.CONTINUE;
	}
	
	public record Entry(ResourceKey<ConfiguredFeature<?, ?>> primary, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondary) {
		public static Optional<Entry> of(ResourceKey<ConfiguredFeature<?, ?>> key) {
			return Optional.of(new Entry(key, empty()));
		}
		
		public static Optional<Entry> of(ResourceKey<ConfiguredFeature<?, ?>> primary, ResourceKey<ConfiguredFeature<?, ?>> secondary) {
			return Optional.of(new Entry(primary, Optional.of(secondary)));
		}
	}
	
	private enum Result {
		SUCCESS,
		FAILURE,
		CONTINUE;
	}
}

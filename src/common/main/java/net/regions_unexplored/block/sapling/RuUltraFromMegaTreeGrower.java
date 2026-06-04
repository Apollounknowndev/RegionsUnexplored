package net.regions_unexplored.block.sapling;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.Object2ObjectArrayMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.regions_unexplored.module.version.VersionHelper;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public final class RuUltraFromMegaTreeGrower {
    private static final Map<String, RuUltraFromMegaTreeGrower> GROWERS = new Object2ObjectArrayMap<>();

    // TODO: Fix this codec
    public static final Codec<RuUltraFromMegaTreeGrower> CODEC = Codec.stringResolver((grower) -> {
        return grower.name;
    }, GROWERS::get);

    private final String name;
    private final float secondaryChance;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> ultraTree;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryUltraTree;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers;
    private final Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers;

    public RuUltraFromMegaTreeGrower(String key, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers) {
        this(key, 0.0F, Optional.empty(), Optional.empty(), megaTree, Optional.empty(), tree, Optional.empty(), flowers, Optional.empty());
    }

    public RuUltraFromMegaTreeGrower(String key, Optional<ResourceKey<ConfiguredFeature<?, ?>>> ultraTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers) {
        this(key, 0.0F, ultraTree, Optional.empty(), megaTree, Optional.empty(), tree, Optional.empty(), flowers, Optional.empty());
    }

    public RuUltraFromMegaTreeGrower(String key, float secondaryChance, Optional<ResourceKey<ConfiguredFeature<?, ?>>> ultraTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryUltraTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> megaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryMegaTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> tree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryTree, Optional<ResourceKey<ConfiguredFeature<?, ?>>> flowers, Optional<ResourceKey<ConfiguredFeature<?, ?>>> secondaryFlowers) {
        this.name = key;
        this.secondaryChance = secondaryChance;
        this.ultraTree = ultraTree;
        this.secondaryUltraTree = secondaryUltraTree;
        this.megaTree = megaTree;
        this.secondaryMegaTree = secondaryMegaTree;
        this.tree = tree;
        this.secondaryTree = secondaryTree;
        this.flowers = flowers;
        this.secondaryFlowers = secondaryFlowers;
        GROWERS.put(key, this);
    }

    @Nullable
    private ResourceKey<ConfiguredFeature<?, ?>> getConfiguredUltraFeature(RandomSource random) {
        return this.secondaryUltraTree.isPresent() && random.nextFloat() < this.secondaryChance ? this.secondaryUltraTree.get() : this.ultraTree.orElse((ResourceKey<ConfiguredFeature<?, ?>>)null);
    }

    @Nullable
    private ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
        return this.secondaryMegaTree.isPresent() && random.nextFloat() < this.secondaryChance ? this.secondaryMegaTree.get() : this.megaTree.orElse((ResourceKey<ConfiguredFeature<?, ?>>)null);
    }

    @Nullable
    private ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean b) {
        if (random.nextFloat() < this.secondaryChance) {
            if (b && this.secondaryFlowers.isPresent()) {
                return this.secondaryFlowers.get();
            }

            if (this.secondaryTree.isPresent()) {
                return this.secondaryTree.get();
            }
        }

        return b && this.flowers.isPresent() ? this.flowers.get() : this.tree.orElse((ResourceKey<ConfiguredFeature<?, ?>>)null);
    }

    public boolean growTree(ServerLevel level, ChunkGenerator generator, BlockPos pos, BlockState state, RandomSource random) {
        ResourceKey<ConfiguredFeature<?, ?>> ultraKey = this.getConfiguredUltraFeature(random);
        if (ultraKey != null) {
            ConfiguredFeature<?, ?> feature = VersionHelper.registry(level.registryAccess(), Registries.CONFIGURED_FEATURE).getOptional(ultraKey).orElse(null);
            if (feature != null) {
                for(int i = 1; i >= -1; --i) {
                    for(int j = 1; j >= -1; --j) {
                        if (isThreeBlockSapling(state, level, pos, i, j)) {
                            BlockState blockstate = Blocks.AIR.defaultBlockState();
                            level.setBlock(pos.offset(i, 0, j), blockstate, 4);
                            level.setBlock(pos.offset(i, 0, j+1), blockstate, 4);
                            level.setBlock(pos.offset(i, 0, j-1), blockstate, 4);
                            level.setBlock(pos.offset(i+1, 0, j), blockstate, 4);
                            level.setBlock(pos.offset(i-1, 0, j), blockstate, 4);
                            level.setBlock(pos.offset(i+1, 0, j+1), blockstate, 4);
                            level.setBlock(pos.offset(i+1, 0, j-1), blockstate, 4);
                            level.setBlock(pos.offset(i-1, 0, j+1), blockstate, 4);
                            level.setBlock(pos.offset(i-1, 0, j-1), blockstate, 4);
                            if (feature.place(level, generator, random, pos.offset(i, 0, j))) {
                                return true;
                            }

                            level.setBlock(pos.offset(i, 0, j), state, 4);
                            level.setBlock(pos.offset(i, 0, j+1), state, 4);
                            level.setBlock(pos.offset(i, 0, j-1), state, 4);
                            level.setBlock(pos.offset(i+1, 0, j), state, 4);
                            level.setBlock(pos.offset(i-1, 0, j), state, 4);
                            level.setBlock(pos.offset(i+1, 0, j+1), state, 4);
                            level.setBlock(pos.offset(i+1, 0, j-1), state, 4);
                            level.setBlock(pos.offset(i-1, 0, j+1), state, 4);
                            level.setBlock(pos.offset(i-1, 0, j-1), state, 4);
                            return false;
                        }
                    }
                }
            }
        }
        
        ResourceKey<ConfiguredFeature<?, ?>> megaKey = this.getConfiguredMegaFeature(random);
        if (megaKey != null) {
            ConfiguredFeature<?, ?> feature = VersionHelper.registry(level.registryAccess(), Registries.CONFIGURED_FEATURE).getOptional(megaKey).orElse(null);
            if (feature != null) {
                for(int i = 0; i >= -1; --i) {
                    for(int j = 0; j >= -1; --j) {
                        if (isTwoBlockSapling(state, level, pos, i, j)) {
                            BlockState blockstate = Blocks.AIR.defaultBlockState();
                            level.setBlock(pos.offset(i, 0, j), blockstate, 4);
                            level.setBlock(pos.offset(i + 1, 0, j), blockstate, 4);
                            level.setBlock(pos.offset(i, 0, j + 1), blockstate, 4);
                            level.setBlock(pos.offset(i + 1, 0, j + 1), blockstate, 4);
                            if (feature.place(level, generator, random, pos.offset(i, 0, j))) {
                                return true;
                            }

                            level.setBlock(pos.offset(i, 0, j), state, 4);
                            level.setBlock(pos.offset(i + 1, 0, j), state, 4);
                            level.setBlock(pos.offset(i, 0, j + 1), state, 4);
                            level.setBlock(pos.offset(i + 1, 0, j + 1), state, 4);
                            return false;
                        }
                    }
                }
            }
        }

        ResourceKey<ConfiguredFeature<?, ?>> key = this.getConfiguredFeature(random, this.hasFlowers(level, pos));
        if (key == null) {
            return false;
        } else {
            ConfiguredFeature<?, ?> feature = VersionHelper.registry(level.registryAccess(), Registries.CONFIGURED_FEATURE).getOptional(key).orElse(null);
            if (feature == null) {
                return false;
            } else {
                BlockState blockstate1 = level.getFluidState(pos).createLegacyBlock();
                level.setBlock(pos, blockstate1, 4);
                if (feature.place(level, generator, random, pos)) {
                    if (level.getBlockState(pos) == blockstate1) {
                        level.sendBlockUpdated(pos, state, blockstate1, 2);
                    }

                    return true;
                } else {
                    level.setBlock(pos, state, 4);
                    return false;
                }
            }
        }
    }

    public static boolean isThreeBlockSapling(BlockState state, BlockGetter getter, BlockPos pos, int X, int Z) {
        Block block = state.getBlock();
        return  getter.getBlockState(pos.offset(X, 0, Z)).is(block) &&
                getter.getBlockState(pos.offset(X, 0, Z+1)).is(block) &&
                getter.getBlockState(pos.offset(X, 0, Z-1)).is(block) &&
                getter.getBlockState(pos.offset(X+1, 0, Z)).is(block) &&
                getter.getBlockState(pos.offset(X-1, 0, Z)).is(block) &&
                getter.getBlockState(pos.offset(X+1, 0, Z+1)).is(block) &&
                getter.getBlockState(pos.offset(X+1, 0, Z-1)).is(block) &&
                getter.getBlockState(pos.offset(X-1, 0, Z+1)).is(block) &&
                getter.getBlockState(pos.offset(X-1, 0, Z-1)).is(block);
    }

    private static boolean isTwoBlockSapling(BlockState state, BlockGetter getter, BlockPos pos, int X, int Z) {
        Block block = state.getBlock();
        return getter.getBlockState(pos.offset(X, 0, Z)).is(block)
                && getter.getBlockState(pos.offset(X + 1, 0, Z)).is(block)
                && getter.getBlockState(pos.offset(X, 0, Z + 1)).is(block)
                && getter.getBlockState(pos.offset(X + 1, 0, Z + 1)).is(block);
    }

    private boolean hasFlowers(LevelAccessor level, BlockPos pos) {
        for(BlockPos blockpos : BlockPos.MutableBlockPos.betweenClosed(pos.below().north(2).west(2), pos.above().south(2).east(2))) {
            if (level.getBlockState(blockpos).is(BlockTags.FLOWERS)) {
                return true;
            }
        }

        return false;
    }
}

package net.regions_unexplored.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public interface BlockFactory<T extends Block> extends Function<BlockBehaviour.Properties, T> {
}

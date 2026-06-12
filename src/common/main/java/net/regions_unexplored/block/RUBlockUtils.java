package net.regions_unexplored.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.minecraft.world.phys.shapes.*;
import net.regions_unexplored.module.platform.Registrar;
import net.regions_unexplored.item.RUItemUtils;
import org.jetbrains.annotations.Nullable;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class RUBlockUtils {
    // TODO: Consolidate unnecessary factory methods here
    public static Block block(BlockBehaviour.Properties properties, float destroyTime, float explosionResistance, MapColor colour, SoundType sound, boolean fireproof, BlockFactory<?> factory) {
        applyProperties(properties, destroyTime, explosionResistance, sound, fireproof, colour);
        return factory.apply(properties);
    }

    public static Block log(BlockBehaviour.Properties properties, BlockFactory<?> blockFactory, MapColor plankColour, MapColor logColour, SoundType sound, boolean fireproof) {
        applyProperties(properties, 2f, 2f, sound, fireproof, null).mapColor(state -> state.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? plankColour : logColour);
        return blockFactory.apply(properties);
    }

    public static RotatedPillarBlock wood(BlockBehaviour.Properties properties, MapColor colour, SoundType sound, boolean fireproof) {
        applyProperties(properties, 2, 3, sound, fireproof, colour);
        return new RotatedPillarBlock(properties);
    }

    public static Block planks(BlockBehaviour.Properties properties, MapColor colour, SoundType sound, boolean fireproof) {
        applyProperties(properties, 2, 3, sound, fireproof, colour);
        return new Block(properties);
    }

    public static StairBlock stairs(BlockBehaviour.Properties properties, MapColor colour, SoundType sound, boolean fireproof) {
        applyProperties(properties, 2, 3, sound, fireproof, colour);
        return new StairBlock(Blocks.OAK_PLANKS.defaultBlockState(), properties);
    }

    public static SlabBlock slab(BlockBehaviour.Properties properties, MapColor colour, SoundType sound, boolean fireproof) {
        applyProperties(properties, 2, 3, sound, fireproof, colour);
        return new SlabBlock(properties);
    }

    public static DoorBlock door(BlockBehaviour.Properties properties, MapColor colour, SoundType sound, BlockSetType blockSetType, boolean fireproof) {
        applyProperties(properties, 3, 3, sound, fireproof, colour).noOcclusion();
        return new DoorBlock(blockSetType, properties);
    }

    public static TrapDoorBlock trapdoor(BlockBehaviour.Properties properties, MapColor colour, SoundType sound, BlockSetType blockSetType, boolean fireproof) {
        applyProperties(properties, 3, 3, sound, fireproof, colour).noOcclusion();
        return new TrapDoorBlock(blockSetType, properties);
    }

    public static FenceBlock fence(BlockBehaviour.Properties properties, MapColor colour, SoundType sound, boolean fireproof) {
        applyProperties(properties, 2, 3, sound, fireproof, colour);
        return new FenceBlock(properties);
    }

    public static FenceGateBlock fenceGate(BlockBehaviour.Properties properties, MapColor colour, WoodType type, SoundType sound, boolean fireproof) {
        applyProperties(properties, 2, 3, sound, fireproof, colour);
        return new FenceGateBlock(type, properties);
    }

    public static PressurePlateBlock pressurePlate(BlockBehaviour.Properties properties, MapColor colour, SoundType sound, BlockSetType blockSetType, boolean fireproof) {
        applyProperties(properties, 0.5f, 0.5f, sound, fireproof, colour).noCollision();
        return new PressurePlateBlock(blockSetType, properties);
    }

    public static ButtonBlock button(BlockBehaviour.Properties properties, SoundType sound, BlockSetType blockSetType) {
        applyProperties(properties, 0.5f, 0.5f, sound, true, null).noCollision();
        return new ButtonBlock(blockSetType, 30, properties);
    }

    public static StandingSignBlock sign(BlockBehaviour.Properties properties, SoundType sound, WoodType woodType, boolean fireproof) {
        applyProperties(properties, 1, 1, sound, fireproof, null).noCollision();
        return new StandingSignBlock(woodType, properties);
    }

    public static WallSignBlock wallSign(BlockBehaviour.Properties properties, SoundType sound, Block standingSign, WoodType woodType, boolean fireproof) {
        applyProperties(properties, 1, 1, sound, fireproof, null).noCollision().dropsLike(standingSign);
        return new WallSignBlock(woodType, properties);
    }

    public static CeilingHangingSignBlock hangingSign(BlockBehaviour.Properties properties, MapColor color, SoundType sound, WoodType woodType, boolean fireproof) {
        applyProperties(properties, 1, 1, sound, fireproof, color).noCollision().forceSolidOn();
        return new CeilingHangingSignBlock(woodType, properties);
    }

    public static WallHangingSignBlock wallHangingSign(BlockBehaviour.Properties properties, MapColor color, SoundType sound, Block hangingSign, WoodType woodType, boolean fireproof) {
        applyProperties(properties, 1, 1, sound, fireproof, color).noCollision().dropsLike(hangingSign).forceSolidOn();
        return new WallHangingSignBlock(woodType, properties);
    }

    private static BlockBehaviour.Properties applyProperties(BlockBehaviour.Properties properties, float destroyTime, float explosionResistance, SoundType sound, boolean fireproof, MapColor color) {
        properties.instrument(NoteBlockInstrument.BASS).strength(destroyTime, explosionResistance).sound(sound);
        if (!fireproof) properties.ignitedByLava();
        if (color != null) properties.mapColor(color);
        return properties;
    }

    public static <T extends Block> Supplier<T> register(String name, BlockFactory<T> factory) {
        return register(name, factory, RUItemUtils::registerBlock, null);
    }

    public static <T extends Block> Supplier<T> register(String name, BlockFactory<T> factory, T copiedBlock) {
        return register(name, factory, RUItemUtils::registerBlock, () -> copiedBlock);
    }

    public static <T extends Block> Supplier<T> register(String name, BlockFactory<T> factory, Supplier<T> copiedBlock) {
        return register(name, factory, RUItemUtils::registerBlock, copiedBlock);
    }

    public static <T extends Block> Supplier<T> registerNoItem(String name, BlockFactory<T> factory) {
        return register(name, factory, (a, b) -> {}, null);
    }

    public static <T extends Block> Supplier<T> registerNoItem(String name, BlockFactory<T> factory, T copiedBlock) {
        return register(name, factory, (a, b) -> {}, () -> copiedBlock);
    }

    public static <T extends Block> Supplier<T> registerNoItem(String name, BlockFactory<T> factory, Supplier<T> copiedBlock) {
        return register(name, factory, (a, b) -> {}, copiedBlock);
    }

    public static <T extends Block> Supplier<T> register(String name, BlockFactory<T> factory, BiConsumer<String, Supplier<T>> itemCreator, @Nullable Supplier<T> copiedBlock) {
        Supplier<T> block = Registrar.register(BuiltInRegistries.BLOCK, name, () -> factory.apply(createProperties(copiedBlock)));
        itemCreator.accept(name, block);
        return block;
    }

    private static <T extends Block> BlockBehaviour.Properties createProperties(@Nullable Supplier<T> copiedBlock) {
        return copiedBlock != null ? BlockBehaviour.Properties.ofFullCopy(copiedBlock.get()) : BlockBehaviour.Properties.of();
    }

    public static Block leaves(BlockBehaviour.Properties properties, MapColor colour, boolean fireproof, BlockFactory<?> factory) {
        properties.mapColor(colour).strength(0.2F).randomTicks().sound(SoundType.GRASS).noOcclusion().isValidSpawn(RUBlockUtils::ocelotOrParrot).isSuffocating(RUBlockUtils::never).isViewBlocking(RUBlockUtils::never).pushReaction(PushReaction.DESTROY).isRedstoneConductor(RUBlockUtils::never);
        if (!fireproof) properties.ignitedByLava();
        return factory.apply(properties);
    }


    //Boolean states
    public static Boolean always(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
        return true;
    }
    
    public static boolean always(BlockState state, BlockGetter getter, BlockPos pos) {
        return true;
    }
    
    public static Boolean never(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
        return false;
    }
    
    public static boolean never(BlockState state, BlockGetter getter, BlockPos pos) {
        return false;
    }
    
    public static Boolean ocelotOrParrot(BlockState state, BlockGetter getter, BlockPos pos, EntityType<?> type) {
        return (type == EntityType.OCELOT || type == EntityType.PARROT);
    }
    
    // Shape builders
    
    public static VoxelShape column(final double sizeXZ, final double minY, final double maxY) {
        return column(sizeXZ, sizeXZ, minY, maxY);
    }
    
    public static VoxelShape column(final double sizeX, final double sizeZ, final double minY, final double maxY) {
        double halfX = sizeX / 2.0;
        double halfZ = sizeZ / 2.0;
        return Block.box(8.0 - halfX, minY, 8.0 - halfZ, 8.0 + halfX, maxY, 8.0 + halfZ);
    }
}

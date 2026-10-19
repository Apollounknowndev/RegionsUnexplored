package net.regions_unexplored.block.set;

import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.grower.TreeGrower;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.regions_unexplored.block.BlockFactory;
import net.regions_unexplored.block.type.leaves.HangingVinesBlock;
import net.regions_unexplored.block.type.leaves.NoParticleLeavesBlock;
import net.regions_unexplored.block.type.sapling.CactusSaplingBlock;
import net.regions_unexplored.block.type.sapling.CobaltSaplingBlock;
import net.regions_unexplored.block.type.sapling.RUSaplingBlock;
import net.regions_unexplored.block.type.sapling.RUTreeGrower;
import net.regions_unexplored.block.type.wood.BeardBlock;
import net.regions_unexplored.block.type.wood.BranchBlock;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.block.sapling.RUTreeGrowers;
import net.regions_unexplored.block.RUBlockUtils;
import net.regions_unexplored.block.type.leaves.RUTintedParticlesLeavesBlock;
import net.regions_unexplored.block.type.shrub.ShrubBlock;

import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static net.regions_unexplored.block.RUBlockUtils.postProcessed;

public class NaturalSet {
    private static final UnaryOperator<BlockBehaviour.Properties> BRANCH_PROPERTIES = p -> p.noOcclusion().sound(SoundType.MANGROVE_ROOTS).strength(1.0F, 1.5F).dynamicShape();
    private static final UnaryOperator<BlockBehaviour.Properties> SHRUB_PROPERTIES = p -> p.pushReaction(PushReaction.DESTROY).noCollision().instabreak().sound(SoundType.AZALEA).offsetType(BlockBehaviour.OffsetType.XZ);
    public final String name;
    public final boolean fireproof;
    protected Supplier<Block> branch;
    protected Supplier<Block> shrub;
    protected Supplier<Block> leaves;
    protected Supplier<Block> vines;
    protected Supplier<Block> sapling;
    protected Supplier<Block> pottedSapling;

    public NaturalSet(String name, boolean fireproof) {
        this.name = name;
        this.fireproof = fireproof;
        RUBlocks.NATURAL_SETS.add(this);
    }

    public static NaturalSet saguaroCactus() {
        NaturalSet set = NaturalSet.create("saguaro_cactus");
        set.sapling = RUBlockUtils.register("saguaro_cactus_flower", p -> new CactusSaplingBlock(RUTreeGrowers.SAGUARO_CACTUS, p), Blocks.OAK_SAPLING);
        set.pottedSapling = RUBlockUtils.registerNoItem("potted_saguaro_cactus_flower", p -> new FlowerPotBlock(set.sapling.get(), p), Blocks.POTTED_OAK_SAPLING);
        return set;
    }

    public static NaturalSet ashen() {
        NaturalSet set = NaturalSet.create("ashen").withLeaves(MapColor.COLOR_LIGHT_GRAY, RUTintedParticlesLeavesBlock.small(RUTintedParticlesLeavesBlock.TintGetter.constant(0x767470))).withSapling(RUTreeGrowers.ASHEN);
        set.shrub = RUBlockUtils.register("ashen_shrub", p -> new ShrubBlock(SHRUB_PROPERTIES.apply(postProcessed(p)).sound(SoundType.ROOTED_DIRT).emissiveRendering((bs, br, bp) -> true)));
        return set;
    }

    public static NaturalSet cobalt() {
        NaturalSet set = NaturalSet.create("cobalt");
        set.leaves = RUBlockUtils.register("cobalt_webbing", p -> RUBlockUtils.leaves(p, MapColor.COLOR_BLUE, true, NoParticleLeavesBlock::new));
        set.withSapling(p -> new CobaltSaplingBlock(RUTreeGrowers.COBALT, p.sound(SoundType.NETHER_SPROUTS)));
        return set;
    }

    public static NaturalSet create(String name) {
        return new NaturalSet(name, false);
    }

    public static NaturalSet create(String name, boolean fireproof) {
        return new NaturalSet(name, fireproof);
    }

    public NaturalSet withShrub() {
        return withShrub(ShrubBlock::new);
    }
    
    public <T extends Block> NaturalSet withShrub(BlockFactory<T> factory) {
        this.shrub = RUBlockUtils.register(this.name + "_shrub", p -> factory.apply(SHRUB_PROPERTIES.apply(p)));
        return this;
    }

    public NaturalSet withBranch() {
        this.branch = RUBlockUtils.register(this.name + "_branch", p -> new BranchBlock(BRANCH_PROPERTIES.apply(p)));
        return this;
    }

    public NaturalSet withBeard() {
        this.branch = RUBlockUtils.register(this.name + "_beard", p -> new BeardBlock(BRANCH_PROPERTIES.apply(p)));
        return this;
    }

    public NaturalSet withLeaves() {
        return withLeaves(MapColor.PLANT, RUTintedParticlesLeavesBlock.standard());
    }

    public NaturalSet withLeaves(BlockFactory<?> factory) {
        return withLeaves(MapColor.PLANT, factory);
    }

    public NaturalSet withLeaves(MapColor color, BlockFactory<?> factory) {
        this.leaves = RUBlockUtils.register(this.name + "_leaves", p -> RUBlockUtils.leaves(p, color, this.fireproof, factory));
        return this;
    }
    
    public NaturalSet withVines(MapColor color) {
        this.vines = RUBlockUtils.register(this.name + "_vines", p -> new HangingVinesBlock(
            p.ignitedByLava().mapColor(color).noCollision().sound(SoundType.MOSS_CARPET).pushReaction(PushReaction.DESTROY))
        );
        return this;
    }

    public NaturalSet withSapling(TreeGrower grower) {
        return withSapling(p -> new SaplingBlock(grower, p));
    }
    
    public NaturalSet withSapling(RUTreeGrower grower) {
        return withSapling(p -> new RUSaplingBlock(grower, p));
    }

    public NaturalSet withSapling(BlockFactory<Block> factory) {
        this.sapling = RUBlockUtils.register(this.name + "_sapling", factory, Blocks.OAK_SAPLING);
        this.pottedSapling = RUBlockUtils.registerNoItem("potted_" + this.name + "_sapling", p -> new FlowerPotBlock(this.getSapling(), p), Blocks.POTTED_OAK_SAPLING);
        return this;
    }


    public Block getBranch() {
        return branch != null ? branch.get() : null;
    }

    public Block getShrub() {
        return shrub != null ? shrub.get() : null;
    }

    public Block getLeaves() {
        return leaves != null ? leaves.get() : null;
    }
    
    public Block getVines() {
        return vines != null ? vines.get() : null;
    }

    public Block getSapling() {
        return sapling != null ? sapling.get() : null;
    }

    public Block getPottedSapling() {
        return pottedSapling != null ? pottedSapling.get() : null;
    }
}

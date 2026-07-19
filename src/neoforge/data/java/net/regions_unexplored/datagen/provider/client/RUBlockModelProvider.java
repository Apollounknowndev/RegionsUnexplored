package net.regions_unexplored.datagen.provider.client;

import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.BlockModelGenerators.PlantType;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.util.random.Weighted;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.block.properties.RUBlockProperties;
import net.regions_unexplored.block.set.NaturalSet;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.block.type.leaves.HangingVinesBlock;
import net.regions_unexplored.registry.RUBlocks;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static net.minecraft.client.data.models.BlockModelGenerators.*;

public class RUBlockModelProvider {
	private final BlockModelGenerators blockModels;
	private final ItemModelGenerators itemModels;
	private final BiConsumer<Identifier, ModelInstance> modelOutput;
	
	public RUBlockModelProvider(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
		this.blockModels = blockModels;
		this.itemModels = itemModels;
		this.modelOutput = blockModels.modelOutput;
	}
	
	protected void run() {
		// Templates
		registerTemplate("branch");
		registerTemplate("hanging_vines");
		registerTemplate("flower_pot");
		registerTemplate("cube_bottom");
		registerTemplate("leaves_top_bottom");
		registerTemplate("speleothem");
		registerTemplate("cube_all_tinted");
		
		// Blocks
		fullCrossAndPotted(RUBlocks.POTTED_ALPHA_DANDELION, RUBlocks.ALPHA_DANDELION);
		fullCrossAndPotted(RUBlocks.POTTED_ALPHA_ROSE, RUBlocks.ALPHA_ROSE);
		fullCrossAndPotted(RUBlocks.POTTED_ASTER, RUBlocks.ASTER);
		fullCrossAndPotted(RUBlocks.POTTED_BARREL_CACTUS, RUBlocks.BARREL_CACTUS);
		fullCrossAndPotted(RUBlocks.POTTED_BLEEDING_HEART, RUBlocks.BLEEDING_HEART);
		fullCrossAndPotted(RUBlocks.POTTED_BLUE_BIOSHROOM, RUBlocks.BLUE_BIOSHROOM);
		fullCrossAndPotted(RUBlocks.POTTED_BLUE_LUPINE, RUBlocks.BLUE_LUPINE);
		fullCrossAndPotted(RUBlocks.POTTED_CAVE_HYSSOP, RUBlocks.CAVE_HYSSOP);
		fullCrossAndPotted(RUBlocks.POTTED_COBALT_EARLIGHT, RUBlocks.COBALT_EARLIGHT);
		fullCrossAndPotted(RUBlocks.POTTED_DAISY, RUBlocks.DAISY, true);
		fullCrossAndPotted(RUBlocks.POTTED_DORCEL, RUBlocks.DORCEL);
		fullPotted(RUBlocks.POTTED_DUSKTRAP, RUBlocks.DUSKTRAP, true);
		fullCrossAndPotted(RUBlocks.POTTED_FELICIA_DAISY, RUBlocks.FELICIA_DAISY, true);
		fullCrossAndPotted(RUBlocks.POTTED_FIREWEED, RUBlocks.FIREWEED, true);
		fullCross(RUBlocks.FROZEN_GRASS);
		fullCrossAndPotted(RUBlocks.POTTED_GLISTERING_BLOOM, RUBlocks.GLISTERING_BLOOM, true);
		fullCross(RUBlocks.GLISTERING_SPROUT);
		fullTintedCross(RUBlocks.GRASS_SPROUTS);
		fullCrossAndPotted(RUBlocks.POTTED_GREEN_BIOSHROOM, RUBlocks.GREEN_BIOSHROOM);
		fullCrossAndPotted(RUBlocks.POTTED_HIBISCUS, RUBlocks.HIBISCUS);
		fullCrossAndPotted(RUBlocks.POTTED_HYSSOP, RUBlocks.HYSSOP, true);
		fullCrossAndPotted(RUBlocks.POTTED_MALLOW, RUBlocks.MALLOW);
		fullCrossAndPotted(RUBlocks.POTTED_PINK_BIOSHROOM, RUBlocks.PINK_BIOSHROOM);
		fullCrossAndPotted(RUBlocks.POTTED_PINK_LUPINE, RUBlocks.PINK_LUPINE);
		fullCrossAndPotted(RUBlocks.POTTED_POPPY_BUSH, RUBlocks.POPPY_BUSH);
		fullCrossAndPotted(RUBlocks.POTTED_PURPLE_LUPINE, RUBlocks.PURPLE_LUPINE);
		fullCrossAndPotted(RUBlocks.POTTED_RED_LUPINE, RUBlocks.RED_LUPINE);
		fullCrossAndPotted(RUBlocks.POTTED_SALMON_POPPY, RUBlocks.SALMON_POPPY);
		fullCrossAndPotted(RUBlocks.POTTED_SALMON_POPPY_BUSH, RUBlocks.SALMON_POPPY_BUSH);
		fullCrossAndPotted(RUBlocks.POTTED_TSUBAKI, RUBlocks.TSUBAKI, true);
		fullCrossAndPotted(RUBlocks.POTTED_WARATAH, RUBlocks.WARATAH);
		fullCrossAndPotted(RUBlocks.POTTED_WHITE_TRILLIUM, RUBlocks.WHITE_TRILLIUM);
		fullCrossAndPotted(RUBlocks.POTTED_WILTING_TRILLIUM, RUBlocks.WILTING_TRILLIUM);
		fullCrossAndPotted(RUBlocks.POTTED_YELLOW_BIOSHROOM, RUBlocks.YELLOW_BIOSHROOM);
		fullCrossAndPotted(RUBlocks.POTTED_YELLOW_LUPINE, RUBlocks.YELLOW_LUPINE);
		fullDoubleCross(RUBlocks.CORPSE_FLOWER);
		fullPotted(RUBlocks.POTTED_CORPSE_FLOWER, RUBlocks.CORPSE_FLOWER, true);
		fullDoubleCross(RUBlocks.DAY_LILY);
		fullPotted(RUBlocks.POTTED_DAY_LILY, RUBlocks.DAY_LILY, false);
		fullDoubleCross(RUBlocks.MEADOW_SAGE, true);
		fullPotted(RUBlocks.POTTED_MEADOW_SAGE, RUBlocks.MEADOW_SAGE, false);
		fullDoubleCross(RUBlocks.GLISTER_SPIRE);
		fullPotted(RUBlocks.POTTED_GLISTER_SPIRE, RUBlocks.GLISTER_SPIRE, true);
		fullTintedCross(RUBlocks.BLADED_GRASS);
		fullDoubleCross(RUBlocks.BLADED_TALL_GRASS, "tinted_cross", false);
		fullDoubleCross(RUBlocks.WINDSWEPT_GRASS, "tinted_cross", false);
		
		fullCross(RUBlocks.SHORT_DEAD_GRASS);
		fullCross(RUBlocks.TALL_DEAD_GRASS);
		fullCross(RUBlocks.SANDY_GRASS);
		fullDoubleCross(RUBlocks.TALL_SANDY_GRASS);
		fullCross(RUBlocks.RED_SANDY_GRASS);
		fullDoubleCross(RUBlocks.TALL_RED_SANDY_GRASS);
		
		fullDoubleCross(RUBlocks.TALL_COBALT_EARLIGHT);
		fullDoubleCross(RUBlocks.TALL_BLUE_BIOSHROOM);
		fullDoubleCross(RUBlocks.TALL_GREEN_BIOSHROOM);
		fullDoubleCross(RUBlocks.TALL_PINK_BIOSHROOM);
		fullDoubleCross(RUBlocks.TALL_YELLOW_BIOSHROOM);
		
		String ashenDirtName = name(RUBlocks.ASHEN_DIRT.get());
		var ashenDirt = cuboidModel(ashenDirtName, "cube_all", "all", ashenDirtName);
		Identifier ashenDirtId = ashenDirt.createTemplate(nameId(RUBlocks.ASHEN_DIRT.get()), "block/", this.modelOutput);
		var ashenDirtSmouldering = cuboidModel(ashenDirtName + "_smouldering", "cube_mirrored_all", "all", ashenDirtName + "_smouldering");
		
		this.blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(RUBlocks.ASHEN_DIRT.get()).with(
			PropertyDispatch.initial(RUBlockProperties.SMOULDERING)
				.select(false, BlockModelGenerators.createRotatedVariants(plainModel(ashenDirtId)))
				.select(true, BlockModelGenerators.createRotatedVariants(plainModel(ashenDirtSmouldering.createTemplate(ashenDirtId.withSuffix("_smouldering"), "", this.modelOutput))))
		));
		itemBlock(RUBlocks.ASHEN_DIRT.get(), ashenDirtId);
		
		var ashenGrass = cuboidCross(RUBlocks.ASHEN_GRASS.get(), "cross");
		var ashenGrassSmouldering = cuboidModel(name(RUBlocks.ASHEN_GRASS.get()) + "_smouldering", "cross", "cross", name(RUBlocks.ASHEN_GRASS.get()) + "_smouldering");
		blockBoolean(RUBlocks.ASHEN_GRASS.get(), RUBlockProperties.SMOULDERING, ashenGrassSmouldering, ashenGrass, "_smouldering");
		itemGenerated(RUBlocks.ASHEN_GRASS.get(), false);
		
		fullCube(RUBlocks.ARGILLITE.get(), TexturedModel.CUBE_MIRRORED);
		
		fullSpeleothem(RUBlocks.ICICLE.get());
		fullSpeleothem(RUBlocks.REDSTONE_SPIKE.get());
		
		itemGenerated(RUBlocks.PRISMARITE_CLUSTER.get(), true);
		itemGenerated(RUBlocks.LARGE_PRISMARITE_CLUSTER.get(), true);
		
		String prismaglass = name(RUBlocks.PRISMAGLASS.get());
		blockSingle(RUBlocks.PRISMAGLASS.get(), cuboidModel(prismaglass, template("cube_all_tinted"), b -> b.texture("all", texturize(nameId(Blocks.WHITE_STAINED_GLASS), false))));
		itemBlock(RUBlocks.PRISMAGLASS.get(), cuboidModel(prismaglass + "_item", "cube_all", b -> b.texture("all", texturize(nameId(RUBlocks.PRISMAGLASS.get()), true))).createTemplate(nameId(RUBlocks.PRISMAGLASS.get()).withSuffix("_item"), "item/", this.modelOutput));
		
		fullCubeAll(RUBlocks.CHALK.get());
		fullSlab(RUBlocks.CHALK_SLAB.get(), RUBlocks.CHALK.get());
		fullStairs(RUBlocks.CHALK_STAIRS.get(), RUBlocks.CHALK.get());
		
		fullCubeAll(RUBlocks.POLISHED_CHALK.get());
		fullSlab(RUBlocks.POLISHED_CHALK_SLAB.get(), RUBlocks.POLISHED_CHALK.get());
		fullStairs(RUBlocks.POLISHED_CHALK_STAIRS.get(), RUBlocks.POLISHED_CHALK.get());
		
		fullCubeAll(RUBlocks.CHALK_BRICKS.get());
		fullSlab(RUBlocks.CHALK_BRICK_SLAB.get(), RUBlocks.CHALK_BRICKS.get());
		fullStairs(RUBlocks.CHALK_BRICK_STAIRS.get(), RUBlocks.CHALK_BRICKS.get());
		
		fullCubeAllRotated(RUBlocks.PEAT_DIRT.get());
		fullCubeAll(RUBlocks.PEAT_COARSE_DIRT.get());
		fullCubeAll(RUBlocks.PEAT_MUD.get());
		
		fullCubeAllRotated(RUBlocks.SILT_DIRT.get());
		fullCubeAll(RUBlocks.SILT_COARSE_DIRT.get());
		fullCubeAll(RUBlocks.SILT_MUD.get());
		
		fullBioshroom(RUBlocks.BLUE_BIOSHROOM_BLOCK, RUBlocks.GLOWING_BLUE_BIOSHROOM_BLOCK);
		fullBioshroom(RUBlocks.GREEN_BIOSHROOM_BLOCK, RUBlocks.GLOWING_GREEN_BIOSHROOM_BLOCK);
		fullBioshroom(RUBlocks.PINK_BIOSHROOM_BLOCK, RUBlocks.GLOWING_PINK_BIOSHROOM_BLOCK);
		fullCubeAll(RUBlocks.GLOWING_YELLOW_BIOSHROOM_BLOCK.get());
		
		fullGrowingPlant(RUBlocks.SPANISH_MOSS.get(), RUBlocks.SPANISH_MOSS_PLANT.get());
		itemGenerated(RUBlocks.DROPLEAF.get(), true);
		
		fullCube(RUBlocks.ASH.get(), TexturedModel.CUBE_MIRRORED);
		fullCube(RUBlocks.VOLCANIC_ASH.get(), TexturedModel.CUBE_MIRRORED);
		fullCubeAll(RUBlocks.GLISTERING_WART.get());
		fullCube(RUBlocks.MOSSY_STONE.get(), TexturedModel.CUBE_MIRRORED);
		
		fullCubeTopBottom(RUBlocks.OVERGROWN_BONE_BLOCK.get(), nameId(RUBlocks.GLISTERING_WART.get()), nameId(Blocks.BONE_BLOCK).withSuffix("_top"));
		fullCubeTopBottom(RUBlocks.BRIMSPROUT_NYLIUM.get(), nameId(RUBlocks.BRIMSPROUT_NYLIUM.get()).withSuffix("_top"), nameId(Blocks.NETHERRACK));
		fullCubeTopBottom(RUBlocks.COBALT_NYLIUM.get(), nameId(RUBlocks.COBALT_NYLIUM.get()).withSuffix("_top"), nameId(Blocks.BLACKSTONE));
		fullCubeTopBottom(RUBlocks.MYCOTOXIC_NYLIUM.get(), nameId(RUBlocks.MYCOTOXIC_NYLIUM.get()).withSuffix("_top"), nameId(Blocks.NETHERRACK));
		fullCubeTopBottom(RUBlocks.GLISTERING_NYLIUM.get(), nameId(RUBlocks.GLISTERING_WART.get()), nameId(Blocks.NETHERRACK));
		fullCubeTopBottom(RUBlocks.ASH_VENT.get(), nameId(RUBlocks.ASH_VENT.get()).withSuffix("_top"), nameId(Blocks.BASALT).withSuffix("_top"));
		fullCubeTopBottom(RUBlocks.VIRIDESCENT_NYLIUM.get(), nameId(RUBlocks.VIRIDESCENT_NYLIUM.get()).withSuffix("_top"), nameId(Blocks.STONE));
		fullCubeTopBottom(RUBlocks.DEEPSLATE_VIRIDESCENT_NYLIUM.get(), nameId(RUBlocks.VIRIDESCENT_NYLIUM.get()).withSuffix("_top"), nameId(Blocks.DEEPSLATE).withSuffix("_top"));
		
		var alphaLog = RUBlocks.ALPHA_WOOD_SET.getLog();
		fullCubeTopBottom(alphaLog, nameId(alphaLog).withSuffix("_top"), nameId(alphaLog).withSuffix("_top"));
		
		var palmLeaves = RUBlocks.PALM_NATURAL_SET.getLeaves();
		Identifier palmLeavesModel = cuboidModel(name(palmLeaves), template("leaves_top_bottom"), b -> b
			.texture("side", texturize(name(palmLeaves) + "_side"))
			.texture("top", texturize(name(palmLeaves) + "_top"))
		).createTemplate(nameId(palmLeaves), "block/", this.modelOutput);
		this.blockModels.blockStateOutput.accept(createSimpleBlock(palmLeaves, plainVariant(palmLeavesModel)));
		itemBlock(palmLeaves, palmLeavesModel);
		
		
		fullBranch(RUBlocks.ACACIA_NATURAL_SET, Blocks.ACACIA_LOG);
		fullBranch(RUBlocks.BIRCH_NATURAL_SET, Blocks.BIRCH_LOG);
		fullBranch(RUBlocks.CHERRY_NATURAL_SET, Blocks.CHERRY_LOG);
		fullBranch(RUBlocks.DARK_OAK_NATURAL_SET, Blocks.DARK_OAK_LOG);
		fullBranch(RUBlocks.JUNGLE_NATURAL_SET, Blocks.JUNGLE_LOG);
		fullBranch(RUBlocks.MANGROVE_NATURAL_SET, Blocks.MANGROVE_LOG);
		fullBranch(RUBlocks.OAK_NATURAL_SET, Blocks.OAK_LOG);
		fullBranch(RUBlocks.SPRUCE_NATURAL_SET, Blocks.SPRUCE_LOG);
		fullBranch(RUBlocks.BAOBAB_NATURAL_SET, RUBlocks.BAOBAB_WOOD_SET.getLog());
		fullBranch(RUBlocks.BLACKWOOD_NATURAL_SET, RUBlocks.BLACKWOOD_WOOD_SET.getLog());
		fullBranch(RUBlocks.CYPRESS_NATURAL_SET, RUBlocks.CYPRESS_WOOD_SET.getLog());
		fullBranch(RUBlocks.DEAD_NATURAL_SET, RUBlocks.DEAD_WOOD_SET.getLog());
		fullBranch(RUBlocks.KAPOK_NATURAL_SET, RUBlocks.KAPOK_WOOD_SET.getLog());
		fullBranch(RUBlocks.LARCH_NATURAL_SET, RUBlocks.LARCH_WOOD_SET.getLog());
		fullBranch(RUBlocks.MAGNOLIA_NATURAL_SET, RUBlocks.MAGNOLIA_WOOD_SET.getLog());
		fullBranch(RUBlocks.MAPLE_NATURAL_SET, RUBlocks.MAPLE_WOOD_SET.getLog());
		fullBranch(RUBlocks.PINE_NATURAL_SET, RUBlocks.PINE_WOOD_SET.getLog());
		fullBranch(RUBlocks.REDWOOD_NATURAL_SET, RUBlocks.REDWOOD_WOOD_SET.getLog());
		fullBranch(RUBlocks.SILVER_BIRCH_NATURAL_SET, RUBlocks.SILVER_BIRCH_WOOD_SET.getLog());
		fullBranch(RUBlocks.SOCOTRA_NATURAL_SET, RUBlocks.SOCOTRA_WOOD_SET.getLog());
		fullBranch(RUBlocks.WILLOW_NATURAL_SET, RUBlocks.WILLOW_WOOD_SET.getLog());
		fullBranch(RUBlocks.WISTERIA_NATURAL_SET, RUBlocks.WISTERIA_WOOD_SET.getLog());
		
		for (Map.Entry<DyeColor, Block> entry : RUBlocks.SNOWBELLES.getMap().entrySet()) {
			fullCrossAndPotted(() -> RUBlocks.POTTED_SNOWBELLES.getByColor(entry.getKey()), entry::getValue);
		}
		
		for (Block plank : RUBlocks.PAINTED_PLANKS.getAll()) {
			fullCubeAll(plank);
		}
		for (Map.Entry<DyeColor, SlabBlock> entry : RUBlocks.PAINTED_SLABS.getMap().entrySet()) {
			fullSlab(entry.getValue(), RUBlocks.PAINTED_PLANKS.getByColor(entry.getKey()));
		}
		for (Map.Entry<DyeColor, StairBlock> entry : RUBlocks.PAINTED_STAIRS.getMap().entrySet()) {
			fullStairs(entry.getValue(), RUBlocks.PAINTED_PLANKS.getByColor(entry.getKey()));
		}
		
		for (WoodSet set : RUBlocks.WOOD_SETS) {
			String name = set.name;
			if (!name.equals("alpha") && !name.equals("bamboo") && !name.equals("small_oak")) {
				if (!name.equals("eucalyptus")) {
					if (set.getLog() != null) fullColumn(set.getLog(), nameId(set.getLog()), true);
					if (set.getWood() != null) fullColumn(set.getWood(), nameId(set.getLog()), false);
				}
				if (set.getStrippedLog() != null) fullColumn(set.getStrippedLog(), nameId(set.getStrippedLog()), true);
				if (set.getStrippedWood() != null) fullColumn(set.getStrippedWood(), nameId(set.getStrippedLog()), false);
			}
			if (set.getPlanks() != null) {
				Block planks = set.getPlanks();
				fullCubeAll(planks);
				fullSlab(set.getSlab(), planks);
				fullStairs(set.getStairs(), planks);
				if (name.equals("alpha")) continue;
				fullFence(set.getFence(), planks);
				fullFenceGate(set.getFenceGate(), planks);
				fullDoor(set.getDoor());
				fullTrapdoor(set.getTrapdoor());
				fullPressurePlate(set.getPressurePlate(), planks);
				fullButton(set.getButton(), planks);
				fullSign(set);
			}
		}
		for (NaturalSet set : RUBlocks.NATURAL_SETS) {
			String name = set.name;
			List<String> specialLeaves = List.of("wisteria", "apple_oak", "flowering", "joshua", "palm");
			
			if (set.getLeaves() != null && specialLeaves.stream().noneMatch(name::contains)) {
				fullLeaves(set.getLeaves(), name(set.getLeaves()));
			}
			
			if (set.getShrub() != null) {
				fullDoubleCross(set::getShrub);
			}
			
			if (set.getPottedSapling() != null && set.getSapling() != null) {
				fullCrossAndPotted(set::getPottedSapling, set::getSapling);
			}
		}
		for (NaturalSet set : RUBlocks.WISTERIA_NATURAL_SETS) {
			fullLeaves(set.getLeaves(), "wisteria_leaves");
			fullHangingVines(set.getVines(), "wisteria_vines");
		}
	}
    
    // FULL
	
	private void fullSpeleothem(Block block) {
		PropertyDispatch.C2<MultiVariant, Direction, DripstoneThickness> generator = PropertyDispatch.initial(
			BlockStateProperties.VERTICAL_DIRECTION, BlockStateProperties.DRIPSTONE_THICKNESS
		);
		
		for (DripstoneThickness speleothemThickness : DripstoneThickness.values()) {
			generator.select(Direction.UP, speleothemThickness, this.createSpeleothemVariant(Direction.UP, speleothemThickness, block));
		}
		
		for (DripstoneThickness speleothemThickness : DripstoneThickness.values()) {
			generator.select(Direction.DOWN, speleothemThickness, this.createSpeleothemVariant(Direction.DOWN, speleothemThickness, block));
		}
		
		this.blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(generator));
		
		this.itemGenerated(block, nameId(block).withSuffix("_up_tip"), false);
	}
	
	private MultiVariant createSpeleothemVariant(final Direction direction, final DripstoneThickness speleothemThickness, final Block block) {
		String suffix = "_" + direction.getSerializedName() + "_" + speleothemThickness.getSerializedName();
		TextureMapping texture = TextureMapping.cross(TextureMapping.getBlockTexture(block, suffix));
		return plainVariant(ModelTemplates.POINTED_DRIPSTONE.createWithSuffix(block, suffix, texture, this.modelOutput));
	}
	
	private void fullColumn(Block block, Identifier id, boolean useTopTexture) {
		ModelBuilder builder = cuboidModel(name(block), "block/cube_column", b -> b
			.texture("side", texturize(id, false))
			.texture("end", texturize(id.withSuffix(useTopTexture ? "_top" : ""), false))
		);
		
		blockAxisAligned(block, builder);
		itemBlock(block, nameId(block));
	}
	
	private void fullBranch(NaturalSet set, Block log) {
		Block branch = set.getBranch();
		ModelBuilder model = cuboidModel(name(branch), template("branch"), b -> b
			.texture("log", texturize(nameId(log), false))
			.texture("branch", texturize(set.name + "_branch"))
		);
		
		blockHorizontalFacing(branch, model);
		itemGenerated(branch, nameId(branch), true);
	}
	
	private void fullDoubleCross(Supplier<Block> supplier) {
		fullDoubleCross(supplier, false);
	}
	
	private void fullDoubleCross(Supplier<Block> supplier, boolean itemTexture) {
		fullDoubleCross(supplier, "cross", itemTexture);
	}
	
	private void fullDoubleCross(Supplier<Block> supplier, String parent, boolean itemTexture) {
		parent = "minecraft:block/" + parent;
		Block block = supplier.get();
		
		String name = name(block);
		ModelBuilder lower = cuboidModel(name + "_lower", parent, "cross", name + "_bottom");
		ModelBuilder upper = cuboidModel(name + "_upper", parent, "cross", name + "_top");
		
		blockDoubleTall(block, lower, upper);
		itemGenerated(block, RegionsUnexplored.id(name(block) + (itemTexture ? "" : "_top")), itemTexture);
	}
	
	private void fullGrowingPlant(Block bodyBlock, Block headBlock) {
		ModelBuilder body = cuboidModel(name(bodyBlock), "cross", "cross", name(bodyBlock));
		ModelBuilder head = cuboidModel(name(headBlock), "cross", "cross", name(headBlock));
		
		blockSingle(bodyBlock, body);
		blockSingle(headBlock, head);
		itemGenerated(bodyBlock, false);
	}
	
	private void fullHangingVines(Block block, String baseName) {
		ModelBuilder base = cuboidModel(name(block), template("hanging_vines"), "cross", baseName);
		ModelBuilder tip = cuboidModel(name(block) + "_tip", template("hanging_vines"), "cross", baseName + "_tip");
        
        blockBoolean(block, HangingVinesBlock.TIP, tip, base, "_tip");
		itemGenerated(block, RegionsUnexplored.id("wisteria_vines"), false);
	}
	
	private void fullCubeAllRotated(Block block) {
		Variant normal = BlockModelGenerators.plainModel(TexturedModel.CUBE.create(block, this.modelOutput));
		Variant mirrored = BlockModelGenerators.plainModel(TexturedModel.CUBE_MIRRORED.create(block, this.modelOutput));
		this.blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, createRotatedVariants(normal, mirrored)));
		itemBlock(block);
	}
	
	private void fullCubeAll(Block block) {
		this.fullCube(block, TexturedModel.CUBE);
	}
	
	private void fullCube(Block block, TexturedModel.Provider provider) {
		if (provider.equals(TexturedModel.CUBE_MIRRORED)) {
		
		} else {
			this.blockModels.createTrivialBlock(block, provider);
		}
	}
	
	private void fullCubeTopBottom(Block block, Identifier top, Identifier bottom) {
		ModelBuilder model = cuboidModel(name(block), "cube_bottom_top", b -> b
			.texture("top", texturize(top, false))
			.texture("side", texturize(nameId(block).withSuffix("_side"), false))
			.texture("bottom", texturize(bottom, false))
		);
		blockSingle(block, model);
		itemBlock(block, nameId(block));
	}
    
    private void fullLeaves(Block block, String texture) {
        ModelBuilder model = cuboidModel(name(block), "minecraft:block/leaves", "all", texture);
        blockSingle(block, model);
        itemBlock(block, nameId(block));
    }
	
	private void fullSlab(SlabBlock slab, Block full) {
		TextureMapping mapping = TEXTURED_MODELS.getOrDefault(full, TexturedModel.CUBE.get(full)).getMapping();
		
		Identifier bottom = ModelTemplates.SLAB_BOTTOM.create(slab, mapping, this.modelOutput);
		MultiVariant top = plainVariant(ModelTemplates.SLAB_TOP.create(slab, mapping, this.modelOutput));
		MultiVariant doubleSlab = plainVariant(ModelTemplates.CUBE_ALL.create(nameId(slab).withPrefix("block/").withSuffix("_double"), mapping, this.modelOutput));
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createSlab(slab, plainVariant(bottom), top, doubleSlab));
		this.blockModels.registerSimpleItemModel(slab, bottom);
	}
	
	private void fullStairs(StairBlock stairs, Block full) {
		TextureMapping mapping = TEXTURED_MODELS.getOrDefault(full, TexturedModel.CUBE.get(full)).getMapping();
		
		MultiVariant inner = plainVariant(ModelTemplates.STAIRS_INNER.create(stairs, mapping, this.modelOutput));
		Identifier straight = ModelTemplates.STAIRS_STRAIGHT.create(stairs, mapping, this.modelOutput);
		MultiVariant outer = plainVariant(ModelTemplates.STAIRS_OUTER.create(stairs, mapping, this.modelOutput));
		
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createStairs(stairs, inner, plainVariant(straight), outer));
		this.blockModels.registerSimpleItemModel(stairs, straight);
	}
	
	private void fullFence(FenceBlock fence, Block full) {
		TextureMapping mapping = TEXTURED_MODELS.getOrDefault(full, TexturedModel.CUBE.get(full)).getMapping();
		
		MultiVariant post = plainVariant(ModelTemplates.FENCE_POST.create(fence, mapping, this.modelOutput));
		MultiVariant side = plainVariant(ModelTemplates.FENCE_SIDE.create(fence, mapping, this.modelOutput));
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createFence(fence, post, side));
		Identifier inventory = ModelTemplates.FENCE_INVENTORY.create(fence, mapping, this.modelOutput);
		this.blockModels.registerSimpleItemModel(fence, inventory);
	}
	
	private void fullFenceGate(FenceGateBlock fenceGate, Block full) {
		TextureMapping mapping = TEXTURED_MODELS.getOrDefault(full, TexturedModel.CUBE.get(full)).getMapping();
		
		MultiVariant open = plainVariant(
			ModelTemplates.FENCE_GATE_OPEN.create(fenceGate, mapping, this.modelOutput)
		);
		Identifier closed = ModelTemplates.FENCE_GATE_CLOSED.create(fenceGate, mapping, this.modelOutput);
		MultiVariant openWall = plainVariant(
			ModelTemplates.FENCE_GATE_WALL_OPEN.create(fenceGate, mapping, this.modelOutput)
		);
		MultiVariant closedWall = plainVariant(
			ModelTemplates.FENCE_GATE_WALL_CLOSED.create(fenceGate, mapping, this.modelOutput)
		);
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createFenceGate(fenceGate, open, plainVariant(closed), openWall, closedWall, true));
		this.blockModels.registerSimpleItemModel(fenceGate, closed);
	}
	
	private void fullDoor(DoorBlock door) {
		TextureMapping mapping = TextureMapping.door(door);
		MultiVariant doorBottomLeft = plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT.create(door, mapping, this.modelOutput));
		MultiVariant doorBottomLeftOpen = plainVariant(ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.create(door, mapping, this.modelOutput));
		MultiVariant doorBottomRight = plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT.create(door, mapping, this.modelOutput));
		MultiVariant doorBottomRightOpen = plainVariant(ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.create(door, mapping, this.modelOutput));
		MultiVariant doorTopLeft = plainVariant(ModelTemplates.DOOR_TOP_LEFT.create(door, mapping, this.modelOutput));
		MultiVariant doorTopLeftOpen = plainVariant(ModelTemplates.DOOR_TOP_LEFT_OPEN.create(door, mapping, this.modelOutput));
		MultiVariant doorTopRight = plainVariant(ModelTemplates.DOOR_TOP_RIGHT.create(door, mapping, this.modelOutput));
		MultiVariant doorTopRightOpen = plainVariant(ModelTemplates.DOOR_TOP_RIGHT_OPEN.create(door, mapping, this.modelOutput));
		this.blockModels.registerSimpleFlatItemModel(door.asItem());
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createDoor(
			door, doorBottomLeft, doorBottomLeftOpen, doorBottomRight, doorBottomRightOpen, doorTopLeft, doorTopLeftOpen, doorTopRight, doorTopRightOpen
		));
	}
	
	private void fullTrapdoor(TrapDoorBlock trapdoor) {
		TextureMapping mapping = TextureMapping.defaultTexture(trapdoor);
		MultiVariant top = plainVariant(ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.create(trapdoor, mapping, this.modelOutput));
		Identifier bottom = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.create(trapdoor, mapping, this.modelOutput);
		MultiVariant open = plainVariant(ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.create(trapdoor, mapping, this.modelOutput));
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createOrientableTrapdoor(trapdoor, top, plainVariant(bottom), open));
		this.blockModels.registerSimpleItemModel(trapdoor, bottom);
	}
	
	private void fullPressurePlate(PressurePlateBlock pressurePlate, Block full) {
		TextureMapping mapping = TEXTURED_MODELS.getOrDefault(full, TexturedModel.CUBE.get(full)).getMapping();
		
		MultiVariant off = plainVariant(
			ModelTemplates.PRESSURE_PLATE_UP.create(pressurePlate, mapping, this.modelOutput)
		);
		MultiVariant on = plainVariant(
			ModelTemplates.PRESSURE_PLATE_DOWN.create(pressurePlate, mapping, this.modelOutput)
		);
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createPressurePlate(pressurePlate, off, on));
	}
	
	private void fullButton(ButtonBlock button, Block full) {
		TextureMapping mapping = TEXTURED_MODELS.getOrDefault(full, TexturedModel.CUBE.get(full)).getMapping();
		
		MultiVariant normal = plainVariant(ModelTemplates.BUTTON.create(button, mapping, this.modelOutput));
		MultiVariant pressed = plainVariant(
			ModelTemplates.BUTTON_PRESSED.create(button, mapping, this.modelOutput)
		);
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createButton(button, normal, pressed));
		Identifier inventory = ModelTemplates.BUTTON_INVENTORY.create(button, mapping, this.modelOutput);
		this.blockModels.registerSimpleItemModel(button, inventory);
	}
	
	private void fullSign(WoodSet set) {
		TextureMapping mapping = TEXTURED_MODELS.getOrDefault(set.getPlanks(), TexturedModel.CUBE.get(set.getPlanks())).getMapping();
		Block sign = set.getSign();
		Block wallSign = set.getWallSign();
		MultiVariant model = plainVariant(ModelTemplates.PARTICLE_ONLY.create(sign, mapping, this.modelOutput));
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(sign, model));
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallSign, model));
		this.blockModels.registerSimpleFlatItemModel(sign.asItem());
		
		Block hangingSign = set.getHangingSign();
		Block wallHangingSign = set.getWallHangingSign();
		MultiVariant hangingModel = this.blockModels.createParticleOnlyBlockModel(hangingSign, set.getPlanks());
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(hangingSign, hangingModel));
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(wallHangingSign, hangingModel));
		this.blockModels.registerSimpleFlatItemModel(hangingSign.asItem());
	}
	
	private void fullCross(Supplier<Block> supplier) {
		this.blockModels.createCrossBlockWithDefaultItem(supplier.get(), PlantType.NOT_TINTED);
	}
	
	private void fullTintedCross(Supplier<Block> supplier) {
		this.blockModels.createCrossBlockWithDefaultItem(supplier.get(), PlantType.TINTED);
	}
	
	private void fullPotted(Supplier<Block> potted, Supplier<Block> plant, boolean pottedTexture) {
		ModelBuilder model = cuboidModel(name(potted.get()), template("flower_pot"), "plant", (pottedTexture ? "potted_" : "") + name(plant.get()));
		blockSingle(potted.get(), model);
	}
	
	private void fullCrossAndPotted(Supplier<Block> potted, Supplier<Block> plant) {
		this.blockModels.createPlantWithDefaultItem(plant.get(), potted.get(), PlantType.NOT_TINTED);
	}
	
	private void fullCrossAndPotted(Supplier<Block> potted, Supplier<Block> standAlone, boolean pottedTexture) {
		this.blockModels.registerSimpleItemModel(standAlone.get().asItem(), PlantType.NOT_TINTED.createItemModel(this.blockModels, standAlone.get()));
		
		this.blockModels.createCrossBlock(standAlone.get(), PlantType.NOT_TINTED);
		TextureMapping textures = new TextureMapping().put(TextureSlot.PLANT, new Material(nameId(standAlone.get()).withPrefix(pottedTexture ? "potted_" : "")));
		MultiVariant model = plainVariant(PlantType.NOT_TINTED.getCrossPot().create(potted.get(), textures, this.modelOutput));
		this.blockModels.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(potted.get(), model));
	}
	
	private void fullBioshroom(Supplier<Block> base, Supplier<Block> glowing) {
		ModelBuilder model = cuboidModel(name(base.get()), template("cube_bottom"), b -> b
			.texture("side", texturize(name(base.get())))
			.texture("bottom", texturize(name(glowing.get())))
		);
		blockSingle(base.get(), model);
		itemBlock(base.get(), nameId(base.get()));
		
		fullCubeAll(glowing.get());
	}
   
    // BLOCK
    
    private void blockSingle(Block block, ModelBuilder builder) {
		Identifier model = builder.createTemplate(nameId(block), "block/", this.modelOutput);
		this.blockModels.blockStateOutput.accept(createSimpleBlock(block, plainVariant(model)));
    }
	
    private void blockBoolean(Block block, Property<Boolean> property, ModelBuilder on, ModelBuilder off, String onSuffix) {
	    Identifier id = nameId(block);
	    
	    this.blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(
		    PropertyDispatch.initial(property)
			    .select(true, plainVariant(on.createTemplate(id.withSuffix(onSuffix), "block/", this.modelOutput)))
			    .select(false, plainVariant(off.createTemplate(id, "block/", this.modelOutput)))
	    ));
    }
	
	private void blockDoubleTall(Block block, ModelBuilder lower, ModelBuilder upper) {
		Identifier id = nameId(block);
		
		this.blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block).with(
			PropertyDispatch.initial(BlockStateProperties.DOUBLE_BLOCK_HALF)
				.select(DoubleBlockHalf.LOWER, plainVariant(lower.createTemplate(id.withSuffix("_lower"), "block/", this.modelOutput)))
				.select(DoubleBlockHalf.UPPER, plainVariant(upper.createTemplate(id.withSuffix("_upper"), "block/", this.modelOutput)))
		));
	}
	
	private void blockHorizontalFacing(Block block, ModelBuilder builder) {
		Identifier model = builder.createTemplate(nameId(block), "block/", this.modelOutput);
		this.blockModels.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, BlockModelGenerators.createRotatedVariants(new Variant(model))));
	}
	
	private void blockAxisAligned(Block block, ModelBuilder builder) {
		Identifier model = builder.createTemplate(nameId(block), "block/", this.modelOutput);
		this.blockModels.blockStateOutput.accept(createAxisAlignedPillarBlock(block, plainVariant(model)));
	}
	
	// ITEM
	
	private void itemBlock(Block block) {
		this.blockModels.registerSimpleItemModel(block, ModelLocationUtils.getModelLocation(block));
	}
	
	private void itemBlock(Block block, Identifier model) {
		this.blockModels.registerSimpleItemModel(block, model.withPrefix("block/"));
	}
	
	private void itemGenerated(Block block, boolean itemPrefix) {
        itemGenerated(block, nameId(block), itemPrefix);
	}
    
    private void itemGenerated(Block block, Identifier textureId, boolean itemPrefix) {
		this.itemModels.itemModelOutput.accept(block.asItem(), ItemModelUtils.plainModel(
			ModelTemplates.FLAT_ITEM.create(BuiltInRegistries.BLOCK.getKey(block).withPrefix("item/"), new TextureMapping().put(TextureSlot.LAYER0, new Material(texturize(textureId, itemPrefix))), this.itemModels.modelOutput)
		));
    }
    
    // CUBOID
	
	private ModelBuilder cuboidSpeleothem(Block block, Direction direction, DripstoneThickness thickness) {
		String name = "%s_%s_%s".formatted(name(block), direction.getSerializedName(), thickness.getSerializedName());
		return cuboidModel(name, template("speleothem"), "cross", name);
	}
    
    private ModelBuilder cuboidCross(Block block, String parent) {
        return cuboidModel(block, parent, "cross");
    }
    
    private ModelBuilder cuboidModel(Block block, String parent, String textureKey) {
	    return cuboidModel(name(block), parent, textureKey, name(block));
    }
	
	private ModelBuilder cuboidModel(String name, String parent, String textureKey, String texture) {
		return cuboidModel(name, parent, b -> b.texture(textureKey, texturize(texture)));
	}
    
    private ModelBuilder cuboidModel(String name, String parent, UnaryOperator<ModelBuilder> operator) {
		return operator.apply(ModelBuilder.builder().parent(parent));
    }
	
	// TEXTURES
	
	private Identifier nameId(Block block) {
		return block.builtInRegistryHolder().key().identifier();
	}
	
	private String name(Block block) {
		return nameId(block).getPath();
	}
	
	private Identifier texturize(String texture) {
		return texturize(RegionsUnexplored.id(texture), false);
	}
	
	private Identifier texturize(Identifier id, boolean itemPrefix) {
		id = itemPrefix ? id.withPrefix("item/") : id.withPrefix("block/");
		//this.existingFileHelper.trackGenerated(id, ModelProvider.TEXTURE);
		return id;
	}
	
	// MISC
	
	private void registerTemplate(String name) {
		Identifier id = Identifier.parse(template(name));
		//models().getBuilder(id.toString());
		//models().generatedModels.remove(id);
	}
	
	private String template(String name) {
		return RegionsUnexplored.id("block/template/" + name).toString();
	}
	
	// OTHER NONSENSE
	
	private static MultiVariant plainVariant(final Identifier model) {
		return variants(new Variant(model));
	}
	
	private static MultiVariant createRotatedVariants(
		final Variant normal, final Variant mirrored
	) {
		return variants(normal, mirrored, normal.with(Y_ROT_180), mirrored.with(Y_ROT_180));
	}
	
	private static MultiVariant variants(final Variant... variant) {
		return new MultiVariant(WeightedList.of(Arrays.stream(variant).map(v -> new Weighted<>(v, 1)).toList()));
	}
}

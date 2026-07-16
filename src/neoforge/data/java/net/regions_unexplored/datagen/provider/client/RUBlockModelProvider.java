package net.regions_unexplored.datagen.provider.client;

import net.minecraft.core.Direction;
import net.minecraft.core.Direction.Axis;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.properties.*;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.block.properties.RUBlockProperties;
import net.regions_unexplored.block.set.NaturalSet;
import net.regions_unexplored.block.set.WoodSet;
import net.regions_unexplored.block.type.leaves.HangingVinesBlock;
import net.regions_unexplored.registry.RUBlocks;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

import static net.regions_unexplored.RegionsUnexplored.*;

public class RUBlockModelProvider extends BlockStateProvider {
	private final ExistingFileHelper existingFileHelper;
	
	public RUBlockModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, MOD_ID, existingFileHelper);
		this.existingFileHelper = existingFileHelper;
	}
	
	@Override
	protected void registerStatesAndModels() {
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
		fullCross(RUBlocks.GRASS_SPROUTS, "tinted_cross");
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
		fullCross(RUBlocks.BLADED_GRASS, "tinted_cross");
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
		var ashenDirtSmouldering = cuboidModel(ashenDirtName + "_smouldering", "cube_mirrored_all", "all", ashenDirtName + "_smouldering");
		this.getVariantBuilder(RUBlocks.ASHEN_DIRT.get())
			.partialState().with(RUBlockProperties.SMOULDERING, true).addModels(ConfiguredModel.allYRotations(ashenDirtSmouldering, 0, false))
			.partialState().with(RUBlockProperties.SMOULDERING, false).addModels(ConfiguredModel.allYRotations(ashenDirt, 0, false))
		;
		blockBoolean(RUBlocks.ASHEN_DIRT.get(), RUBlockProperties.SMOULDERING, ashenDirtSmouldering, ashenDirt);
		itemBlock(RUBlocks.ASHEN_DIRT.get(), ashenDirt);
		
		var ashenGrass = cuboidCross(RUBlocks.ASHEN_GRASS.get(), "cross");
		var ashenGrassSmouldering = cuboidModelCutout(name(RUBlocks.ASHEN_GRASS.get()) + "_smouldering", "cross", "cross", name(RUBlocks.ASHEN_GRASS.get()) + "_smouldering");
		blockBoolean(RUBlocks.ASHEN_GRASS.get(), RUBlockProperties.SMOULDERING, ashenGrassSmouldering, ashenGrass);
		itemGenerated(RUBlocks.ASHEN_GRASS.get(), false);
		
		fullCubeAllMirrored(RUBlocks.ARGILLITE.get());
		
		fullSpeleothem(RUBlocks.ICICLE.get());
		fullSpeleothem(RUBlocks.REDSTONE_SPIKE.get());
		
		itemGenerated(RUBlocks.PRISMARITE_CLUSTER.get(), true);
		itemGenerated(RUBlocks.LARGE_PRISMARITE_CLUSTER.get(), true);
		
		blockSingle(RUBlocks.PRISMAGLASS.get(), cuboidModel(name(RUBlocks.PRISMAGLASS.get()), template("cube_all_tinted"), b -> b.texture("all", texturize(nameId(Blocks.WHITE_STAINED_GLASS), false))));
		itemBlock(RUBlocks.PRISMAGLASS.get(), cuboidModel(name(RUBlocks.PRISMAGLASS.get()) + "_item", "cube_all", b -> b.texture("all", texturize(nameId(RUBlocks.PRISMAGLASS.get()), true))));
		
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
		
		fullCubeAllMirrored(RUBlocks.ASH.get());
		fullCubeAllMirrored(RUBlocks.VOLCANIC_ASH.get());
		fullCubeAll(RUBlocks.GLISTERING_WART.get());
		fullCubeAllMirrored(RUBlocks.MOSSY_STONE.get());
		
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
		ModelFile palmLeavesModel = cuboidModel(name(palmLeaves), template("leaves_top_bottom"), b -> b
			.texture("side", texturize(name(palmLeaves) + "_side"))
			.texture("top", texturize(name(palmLeaves) + "_top"))
		);
		blockSingle(palmLeaves, palmLeavesModel);
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
				fullSimple(set.getLeaves(), "leaves", "all", name(set.getLeaves()));
			}
			
			if (set.getShrub() != null) {
				fullDoubleCross(set::getShrub);
			}
			
			if (set.getPottedSapling() != null && set.getSapling() != null) {
				fullCrossAndPotted(set::getPottedSapling, set::getSapling);
			}
		}
		for (NaturalSet set : RUBlocks.WISTERIA_NATURAL_SETS) {
			fullSimple(set.getLeaves(), "leaves", "all", "wisteria_leaves");
			fullHangingVines(set.getVines(), "wisteria_vines");
		}
	}
    
    // FULL
	
	private void fullSpeleothem(Block block) {
		this.getVariantBuilder(block).forAllStates(state -> new ConfiguredModel[]{
			new ConfiguredModel(cuboidSpeleothem(block, state.getValue(BlockStateProperties.VERTICAL_DIRECTION), state.getValue(BlockStateProperties.DRIPSTONE_THICKNESS)))
		});
		itemModels().singleTexture(
			name(block),
			mcLoc("item/generated"),
			"layer0", texturize(nameId(block).withSuffix("_up_tip"), false)
		);
	}
	
	private void fullColumn(Block block, Identifier id, boolean useTopTexture) {
		ModelFile model = cuboidModel(name(block), "block/cube_column", b -> b
			.texture("side", texturize(id, false))
			.texture("end", texturize(id.withSuffix(useTopTexture ? "_top" : ""), false))
		);
		
		blockAxisAligned(block, model);
		itemBlock(block, model);
	}
	
	private void fullBranch(NaturalSet set, Block log) {
		Block branch = set.getBranch();
		ModelFile model = cuboidModel(name(branch), template("branch"), b -> b
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
		Block block = supplier.get();
		
		String name = name(block);
		ModelFile lower = cuboidModelCutout(name + "_lower", parent, "cross", name + "_bottom");
		ModelFile upper = cuboidModelCutout(name + "_upper", parent, "cross", name + "_top");
		
		blockDoubleTall(block, lower, upper);
		itemGenerated(block, RegionsUnexplored.id(name(block) + (itemTexture ? "" : "_top")), itemTexture);
	}
	
	private void fullGrowingPlant(Block bodyBlock, Block headBlock) {
		ModelFile body = cuboidModelCutout(name(bodyBlock), "cross", "cross", name(bodyBlock));
		ModelFile head = cuboidModelCutout(name(headBlock), "cross", "cross", name(headBlock));
		
		blockSingle(bodyBlock, body);
		blockSingle(headBlock, head);
		itemGenerated(bodyBlock, false);
	}
	
	private void fullHangingVines(Block block, String baseName) {
		ModelFile base = cuboidModelCutout(name(block), template("hanging_vines"), "cross", baseName);
		ModelFile tip = cuboidModelCutout(name(block) + "_tip", template("hanging_vines"), "cross", baseName + "_tip");
        
        blockBoolean(block, HangingVinesBlock.TIP, tip, base);
		itemGenerated(block, RegionsUnexplored.id("wisteria_vines"), false);
	}
	
	private void fullCubeAllRotated(Block block) {
		ModelFile model = cuboidModel(name(block), "cube_all", "all", name(block));
		blockRotated(block, model);
		itemBlock(block, model);
	}
	
	private void fullCubeAllMirrored(Block block) {
		String name = name(block);
		ModelFile model = cuboidModel(name, "cube_all", "all", name);
		ModelFile mirroredModel = cuboidModel(name + "_mirrored", "cube_mirrored_all", "all", name);
		
		simpleBlock(block, new ConfiguredModel(model), new ConfiguredModel(mirroredModel));
		itemBlock(block, model);
	}
	
	private void fullCubeAll(Block block) {
		fullSimple(block, "cube_all", "all", name(block));
	}
	
	private void fullCubeTopBottom(Block block, Identifier top, Identifier bottom) {
		ModelFile model = cuboidModel(name(block), "cube_bottom_top", b -> b
			.texture("top", texturize(top, false))
			.texture("side", texturize(nameId(block).withSuffix("_side"), false))
			.texture("bottom", texturize(bottom, false))
		);
		blockSingle(block, model);
		itemBlock(block, model);
	}
    
    private void fullSimple(Block block, String parent, String textureKey, String texture) {
        ModelFile model = cuboidModel(name(block), parent, textureKey, texture);
        blockSingle(block, model);
        itemBlock(block, model);
    }
	
	private void fullSlab(SlabBlock slab, Block full) {
		ModelFile topModel = cuboidModel(name(slab) + "_top", "block/slab_top", b -> b
			.texture("bottom", texturize(nameId(full), false))
			.texture("side", texturize(nameId(full), false))
			.texture("top", texturize(nameId(full), false))
		);
		ModelFile doubleModel = cuboidModel(name(slab) + "_double", "block/cube_all", "all", name(full));
		ModelFile bottomModel = cuboidModel(name(slab) + "_bottom", "block/slab", b -> b
			.texture("bottom", texturize(nameId(full), false))
			.texture("side", texturize(nameId(full), false))
			.texture("top", texturize(nameId(full), false))
		);
		blockSlab(slab, topModel, doubleModel, bottomModel);
		itemBlock(slab, bottomModel);
	}
	
	private void fullStairs(StairBlock stairs, Block full) {
		Identifier texture = texturize(nameId(full), false);
		stairsBlock(stairs, texture);
		simpleBlockItem(stairs, itemModels().stairs("block/" + name(stairs), texture, texture, texture));
	}
	
	private void fullFence(FenceBlock fence, Block full) {
		Identifier texture = texturize(nameId(full), false);
		fenceBlock(fence, texture);
		simpleBlockItem(fence, itemModels().fenceInventory("block/" + name(fence), texture));
	}
	
	private void fullFenceGate(FenceGateBlock fenceGate, Block full) {
		Identifier texture = texturize(nameId(full), false);
		fenceGateBlock(fenceGate, texture);
		simpleBlockItem(fenceGate, itemModels().fenceGate("block/" + name(fenceGate), texture));
	}
	
	private void fullDoor(DoorBlock door) {
		Identifier bottomTexture = texturize(nameId(door).withSuffix("_bottom"), false);
		Identifier topTexture = texturize(nameId(door).withSuffix("_top"), false);
		doorBlock(door, bottomTexture, topTexture);
		itemGenerated(door, true);
	}
	
	private void fullTrapdoor(TrapDoorBlock trapdoor) {
		Identifier texture = texturize(nameId(trapdoor), false);
		trapdoorBlock(trapdoor, texture, true);
		itemModels().withExistingParent(texturize(nameId(trapdoor), true).toString(), texture.withSuffix("_bottom"));
	}
	
	private void fullPressurePlate(PressurePlateBlock pressurePlate, Block full) {
		Identifier texture = texturize(nameId(full), false);
		pressurePlateBlock(pressurePlate, texture);
		
		itemModels().withExistingParent(
			texturize(nameId(pressurePlate), true).toString(),
			texturize(nameId(pressurePlate), false)
		);
	}
	
	private void fullButton(ButtonBlock button, Block full) {
		Identifier texture = texturize(nameId(full), false);
		buttonBlock(button, texture);
		simpleBlockItem(button, itemModels().buttonInventory("block/" + name(button) + "_inventory", texture));
	}
	
	private void fullSign(WoodSet set) {
		Identifier texture = texturize(nameId(set.getPlanks()), false);
		signBlock(set.getSign(), set.getWallSign(), texture);
		hangingSignBlock(set.getHangingSign(), set.getWallHangingSign(), texture);
		
		itemGenerated(set.getSign(), true);
		itemGenerated(set.getHangingSign(), true);
	}
	
	private void fullCross(Supplier<Block> supplier) {
		fullCross(supplier, "cross");
	}
	
	private void fullCross(Supplier<Block> supplier, String parent) {
		Block block = supplier.get();
		blockSingle(block, cuboidCross(block, parent));
		itemGenerated(block, false);
	}
	
	private void fullPotted(Supplier<Block> potted, Supplier<Block> plant, boolean pottedTexture) {
		ModelFile model = cuboidModel(name(potted.get()), template("flower_pot"), "plant", (pottedTexture ? "potted_" : "") + name(plant.get()));
		blockSingle(potted.get(), model);
	}
	
	private void fullCrossAndPotted(Supplier<Block> potted, Supplier<Block> plant) {
		fullCrossAndPotted(potted, plant, false);
	}
	
	private void fullCrossAndPotted(Supplier<Block> potted, Supplier<Block> plant, boolean pottedTexture) {
		fullCross(plant);
		fullPotted(potted, plant, pottedTexture);
	}
	
	private void fullBioshroom(Supplier<Block> base, Supplier<Block> glowing) {
		ModelFile model = cuboidModel(name(base.get()), template("cube_bottom"), b -> b
			.texture("side", texturize(name(base.get())))
			.texture("bottom", texturize(name(glowing.get())))
		);
		blockSingle(base.get(), model);
		itemBlock(base.get(), model);
		
		fullCubeAll(glowing.get());
	}
   
    // BLOCK
    
    private void blockSingle(Block block, ModelFile model) {
        simpleBlock(block, model);
    }
	
	private void blockRotated(Block block, ModelFile model) {
		simpleBlock(block, ConfiguredModel.allYRotations(model, 0, false));
	}
    
    private void blockBoolean(Block block, Property<Boolean> property, ModelFile on, ModelFile off) {
        this.getVariantBuilder(block)
            .partialState().with(property, true).addModels(new ConfiguredModel(on))
            .partialState().with(property, false).addModels(new ConfiguredModel(off))
        ;
    }
	
	private void blockDoubleTall(Block block, ModelFile lower, ModelFile upper) {
		this.getVariantBuilder(block)
			.partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.LOWER).addModels(new ConfiguredModel(lower))
			.partialState().with(BlockStateProperties.DOUBLE_BLOCK_HALF, DoubleBlockHalf.UPPER).addModels(new ConfiguredModel(upper))
		;
	}
	
	private void blockHorizontalFacing(Block block, ModelFile model) {
		this.getVariantBuilder(block)
			.partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH).addModels(new ConfiguredModel(model, 0, 0, false))
			.partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST).addModels(new ConfiguredModel(model, 0, 90, false))
			.partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH).addModels(new ConfiguredModel(model, 0, 180, false))
			.partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST).addModels(new ConfiguredModel(model, 0, 270, false))
		;
	}
	
	private void blockAxisAligned(Block block, ModelFile model) {
		this.getVariantBuilder(block)
			.partialState().with(BlockStateProperties.AXIS, Axis.X).addModels(new ConfiguredModel(model, 90, 90, false))
			.partialState().with(BlockStateProperties.AXIS, Axis.Y).addModels(new ConfiguredModel(model, 0, 0, false))
			.partialState().with(BlockStateProperties.AXIS, Axis.Z).addModels(new ConfiguredModel(model, 90, 0, false))
		;
	}
	
	private void blockSlab(Block block, ModelFile topModel, ModelFile doubleModel, ModelFile bottomModel) {
		this.getVariantBuilder(block)
			.partialState().with(BlockStateProperties.SLAB_TYPE, SlabType.TOP).addModels(new ConfiguredModel(topModel))
			.partialState().with(BlockStateProperties.SLAB_TYPE, SlabType.DOUBLE).addModels(new ConfiguredModel(doubleModel))
			.partialState().with(BlockStateProperties.SLAB_TYPE, SlabType.BOTTOM).addModels(new ConfiguredModel(bottomModel))
		;
	}
	
	// ITEM
	
	private void itemBlock(Block parent, ModelFile model) {
		simpleBlockItem(parent, model);
	}
	
	private void itemGenerated(Block block, boolean itemPrefix) {
        itemGenerated(block, nameId(block), itemPrefix);
	}
    
    private void itemGenerated(Block block, Identifier textureId, boolean itemPrefix) {
        itemModels().singleTexture(
            name(block),
            mcLoc("item/generated"),
            "layer0", texturize(textureId, itemPrefix)
        );
    }
    
    // CUBOID
	
	private BlockModelBuilder cuboidSpeleothem(Block block, Direction direction, DripstoneThickness thickness) {
		String name = "%s_%s_%s".formatted(name(block), direction.getSerializedName(), thickness.getSerializedName());
		return cuboidModel(name, template("speleothem"), "cross", name).renderType("cutout");
	}
    
    private BlockModelBuilder cuboidCross(Block block, String parent) {
        return cuboidModelCutout(block, parent, "cross");
    }
    
    private BlockModelBuilder cuboidModelCutout(Block block, String parent, String textureKey) {
        return cuboidModel(block, parent, textureKey).renderType("cutout");
    }
    
    private BlockModelBuilder cuboidModelCutout(String name, String parent, String textureKey, String texture) {
        return cuboidModel(name, parent, textureKey, texture).renderType("cutout");
    }
    
    private BlockModelBuilder cuboidModel(Block block, String parent, String textureKey) {
	    return cuboidModel(name(block), parent, textureKey, name(block));
    }
	
	private BlockModelBuilder cuboidModel(String name, String parent, String textureKey, String texture) {
		return cuboidModel(name, parent, b -> b.texture(textureKey, texturize(texture)));
	}
    
    private BlockModelBuilder cuboidModel(String name, String parent, UnaryOperator<BlockModelBuilder> operator) {
		return operator.apply(models().withExistingParent(name, Identifier.parse(parent)));
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
		this.existingFileHelper.trackGenerated(id, ModelProvider.TEXTURE);
		return id;
	}
	
	// MISC
	
	private void registerTemplate(String name) {
		Identifier id = Identifier.parse(template(name));
		models().getBuilder(id.toString());
		models().generatedModels.remove(id);
	}
	
	private String template(String name) {
		return RegionsUnexplored.id("block/template/" + name).toString();
	}
}

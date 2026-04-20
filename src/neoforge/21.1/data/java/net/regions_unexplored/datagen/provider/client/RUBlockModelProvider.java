package net.regions_unexplored.datagen.provider.client;

import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.block.state.properties.Property;
import net.neoforged.neoforge.client.model.generators.*;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.block.set.NaturalSet;
import net.regions_unexplored.block.type.leaves.HangingVinesBlock;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.world.level.block.plant.tall.ShrubBlock;

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
		
		fullBranch(RUBlocks.WISTERIA_NATURAL_SET, RUBlocks.MAUVE_WOOD_SET.getLog());
		
		for (NaturalSet set : RUBlocks.NATURAL_SETS) {
			if (set.getShrub() != null) fullShrub(set.getShrub());
		}
		for (NaturalSet set : RUBlocks.WISTERIA_NATURAL_SETS) {
			fullSimple(set.getLeaves(), "leaves", "all", "wisteria_leaves");
			fullHangingVines(set.getVines(), "wisteria_vines");
			fullCross(set.getSapling());
			fullPotted(set.getPottedSapling(), set.getSapling());
		}
		
		fullCross(RUBlocks.SALMON_POPPY.get());
	}
    
    // FULL
	
	private void fullBranch(NaturalSet set, Block log) {
		Block branch = set.getBranch();
		ModelFile model = cuboidModel(name(branch), template("branch"), b -> b
			.texture("log", texturize(nameId(log), false))
			.texture("branch", texturize(set.name + "_branch"))
		);
		
		blockHorizontalFacing(branch, model);
		itemGenerated(branch, nameId(branch), true);
	}
	
	private void fullShrub(Block block) {
		String name = name(block);
		ModelFile lower = cuboidModelCutout(name + "_lower", "cross", "cross", name + "_bottom");
		ModelFile upper = cuboidModelCutout(name + "_upper", "cross", "cross", name + "_top");
		
		blockDoubleTall(block, lower, upper);
		itemGenerated(block, RegionsUnexplored.id(name(block) + "_top"), false);
	}
	
	private void fullHangingVines(Block block, String baseName) {
		ModelFile base = cuboidModelCutout(name(block), template("hanging_vines"), "cross", baseName);
		ModelFile tip = cuboidModelCutout(name(block) + "_tip", template("hanging_vines"), "cross", baseName + "_tip");
        
        blockBoolean(block, HangingVinesBlock.TIP, tip, base);
		itemGenerated(block, RegionsUnexplored.id("wisteria_vines"), false);
	}
	
	private void fullSimple(Block block, String parent, String textureKey) {
		fullSimple(block, parent, textureKey, name(block));
	}
    
    private void fullSimple(Block block, String parent, String textureKey, String texture) {
        ModelFile model = cuboidModel(name(block), parent, textureKey, texture);
        blockSingle(block, model);
        itemBlock(block, model);
    }
	
	private void fullCross(Block block) {
        blockSingle(block, cuboidCross(block));
		itemGenerated(block);
	}
	
	private void fullPotted(Block block, Block plant) {
		ModelFile model = cuboidModel(name(block), "flower_pot_cross", b -> b.texture("plant", texturize(name(plant))));
		blockSingle(block, model);
	}
   
    // BLOCK
    
    private void blockSingle(Block block, ModelFile model) {
        simpleBlock(block, model);
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
	
	// ITEM
	
	private void itemBlock(Block parent, ModelFile model) {
		simpleBlockItem(parent, model);
	}
	
	private void itemGenerated(Block block) {
        itemGenerated(block, nameId(block), false);
	}
    
    private void itemGenerated(Block block, Identifier textureId, boolean itemPrefix) {
        itemModels().singleTexture(
            name(block),
            mcLoc("item/generated"),
            "layer0", texturize(textureId, itemPrefix)
        );
    }
    
    // CUBOID
    
    private BlockModelBuilder cuboidCross(Block block) {
        return cuboidModelCutout(block, "cross", "cross");
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

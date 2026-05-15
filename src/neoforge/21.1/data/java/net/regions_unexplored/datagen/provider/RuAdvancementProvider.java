package net.regions_unexplored.datagen.provider;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.DisplayInfo;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.AllOfCondition;
import net.minecraft.world.level.storage.loot.predicates.LocationCheck;
import net.neoforged.neoforge.common.data.AdvancementProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.registry.RUItems;
import net.regions_unexplored.registry.tag.RUBiomeTags;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;
import java.util.function.UnaryOperator;

public class RuAdvancementProvider extends AdvancementProvider {

    public RuAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper) {
        super(output, registries, existingFileHelper, List.of(new AdvancementBuilder()));
    }

    private static class AdvancementBuilder implements AdvancementProvider.AdvancementGenerator {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> saver, ExistingFileHelper existingFileHelper) {
            HolderGetter<Biome> biomeGetter = registries.lookupOrThrow(Registries.BIOME);

            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            RUBlocks.EUCALYPTUS_NATURAL_SET.getSapling(),
                            Component.translatable("advancements.regions_unexplored.title"),
                            Component.translatable("advancements.regions_unexplored.description"),
                            RegionsUnexplored.id("textures/gui/advancements/backgrounds/argillite.png"),
                            AdvancementType.TASK,
                            false,
                            false,
                            false
                    )
                    .addCriterion("load_in_world", PlayerTrigger.TriggerInstance.located(LocationPredicate.Builder.inDimension(Level.OVERWORLD)))
                    .save(saver, RegionsUnexplored.stringId("parent"));

            //SURFACE
            
            Advancement.Builder advancementSurface = biomeAdvancement(root, RUBlocks.HIBISCUS.get(), "pioneer", AdvancementType.TASK);
            Advancement.Builder advancementAll = biomeAdvancement(root, Items.LEATHER_BOOTS, "regions_explored", AdvancementType.CHALLENGE);
            Advancement.Builder advancementNether = biomeAdvancement(root, RUBlocks.BRIMWOOD_NATURAL_SET.getSapling(), "eternal_expedition", AdvancementType.TASK);
            Advancement.Builder advancementCaves = biomeAdvancement(root, RUBlocks.DROPLEAF.get(), "spelunker", AdvancementType.TASK);
            
            for (ResourceKey<Biome> biome : RUBiomes.ALL_BIOMES) {
                if (RUBiomes.REMOVED_BIOMES.contains(biome)) continue;
                
                addBiome(advancementAll, biomeGetter, biome);
                if (RUBiomes.NETHER_BIOMES.contains(biome)) {
                    addBiome(advancementNether, biomeGetter, biome);
                } else {
                    if (RUBiomes.CAVE_BIOMES.contains(biome)) {
                        addBiome(advancementCaves, biomeGetter, biome);
                    } else {
                        addBiome(advancementSurface, biomeGetter, biome);
                    }
                }
            }
            
            var pioneer = save(saver, advancementSurface, "pioneer");
            var regionsExplored = save(saver, advancementAll, "regions_explored");
            var eternalExpedition = save(saver, advancementNether, "eternal_expedition");
            var spelunker = save(saver, advancementCaves, "spelunker");


            AdvancementHolder EVERY_BIT_OF_THE_RAINBOW = Advancement.Builder.advancement()
                    .parent(pioneer)
                    .display(
                            RUBlocks.SNOWBELLES.getWhite().get(),
                            Component.translatable("advancements.regions_unexplored.every_bit_of_the_rainbow.title"),
                            Component.translatable("advancements.regions_unexplored.every_bit_of_the_rainbow.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("white_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getWhite().get().asItem()))
                    .addCriterion("black_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getBlack().get().asItem()))
                    .addCriterion("blue_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getBlue().get().asItem()))
                    .addCriterion("green_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getGreen().get().asItem()))
                    .addCriterion("pink_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getPink().get().asItem()))
                    .addCriterion("brown_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getBrown().get().asItem()))
                    .addCriterion("cyan_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getCyan().get().asItem()))
                    .addCriterion("gray_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getGray().get().asItem()))
                    .addCriterion("magenta_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getMagenta().get().asItem()))
                    .addCriterion("red_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getRed().get().asItem()))
                    .addCriterion("orange_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getOrange().get().asItem()))
                    .addCriterion("yellow_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getYellow().get().asItem()))
                    .addCriterion("purple_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getPurple().get().asItem()))
                    .addCriterion("lime_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getLime().get().asItem()))
                    .addCriterion("light_gray_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getLightGray().get().asItem()))
                    .addCriterion("light_blue_snowbelle", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SNOWBELLES.getLightBlue().get().asItem()))
                    .save(saver, RegionsUnexplored.stringId("every_bit_of_the_rainbow"));


            AdvancementHolder FROM_THE_TOPS = Advancement.Builder.advancement()
                    .parent(pioneer)
                    .display(
                            RUBlocks.KAPOK_VINES.get(),
                            Component.translatable("advancements.regions_unexplored.from_the_tree_tops.title"),
                            Component.translatable("advancements.regions_unexplored.from_the_tree_tops.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("kapok_vines", EnterBlockTrigger.TriggerInstance.entersBlock(RUBlocks.KAPOK_VINES_PLANT.get()))
                    .save(saver, RegionsUnexplored.stringId("from_the_tree_tops"));


            AdvancementHolder LIGHT_AS_A_FROG = Advancement.Builder.advancement()
                    .parent(pioneer)
                    .display(
                            RUBlocks.FLOWERING_LILY_PAD.get(),
                            Component.translatable("advancements.regions_unexplored.light_as_a_frog.title"),
                            Component.translatable("advancements.regions_unexplored.light_as_a_frog.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("lily_pad", EnterBlockTrigger.TriggerInstance.entersBlock(RUBlocks.GIANT_LILY_PAD.get()))
                    .save(saver, RegionsUnexplored.stringId("light_as_a_frog"));

            //NETHER

            AdvancementHolder DOWNER = Advancement.Builder.advancement()
                    .parent(eternalExpedition)
                    .display(
                            RUBlocks.DORCEL.get(),
                            Component.translatable("advancements.regions_unexplored.downer.title"),
                            Component.translatable("advancements.regions_unexplored.downer.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("dorcel", EnterBlockTrigger.TriggerInstance.entersBlock(RUBlocks.DORCEL.get()))
                    .save(saver, RegionsUnexplored.stringId("downer"));

            AdvancementHolder LIGHT_SNACK = Advancement.Builder.advancement()
                    .parent(eternalExpedition)
                    .display(
                            RUBlocks.HANGING_EARLIGHT.get(),
                            Component.translatable("advancements.regions_unexplored.light_snack.title"),
                            Component.translatable("advancements.regions_unexplored.light_snack.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("earlight", ConsumeItemTrigger.TriggerInstance.usedItem(RUItems.HANGING_EARLIGHT_FRUIT.get()))
                    .save(saver, RegionsUnexplored.stringId("light_snack"));

            //CAVE

            AdvancementHolder BLIND_AS_A_BAT = Advancement.Builder.advancement()
                    .parent(spelunker)
                    .display(
                            RUBlocks.DUSKMELON.get(),
                            Component.translatable("advancements.regions_unexplored.blind_as_a_bat.title"),
                            Component.translatable("advancements.regions_unexplored.blind_as_a_bat.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("duskmelon", ConsumeItemTrigger.TriggerInstance.usedItem(RUItems.DUSKMELON_SLICE.get()))
                    .save(saver, RegionsUnexplored.stringId("blind_as_a_bat"));

            AdvancementHolder THIS_BLEEDS_RED = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            RUBlocks.SOCOTRA_WOOD_SET.getLog(),
                            Component.translatable("advancements.regions_unexplored.this_tree_bleeds_red.title"),
                            Component.translatable("advancements.regions_unexplored.this_tree_bleeds_red.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("socotra_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SOCOTRA_WOOD_SET.getLog()))
                    .save(saver, RegionsUnexplored.stringId("this_tree_bleeds_red"));

            AdvancementHolder GOT_WOOD = Advancement.Builder.advancement()
                    .parent(THIS_BLEEDS_RED)
                    .display(
                            RUBlocks.REDWOOD_WOOD_SET.getLog(),
                            Component.translatable("advancements.regions_unexplored.got_wood.title"),
                            Component.translatable("advancements.regions_unexplored.got_wood.description"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .addCriterion("bamboo_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.BAMBOO_LOG.get().asItem()))
                    .addCriterion("small_oak_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SMALL_OAK_LOG.get().asItem()))
                    .addCriterion("ashen_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.ASHEN_WOOD_SET.getLog().asItem()))
                    .addCriterion("silver_birch_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SILVER_BIRCH_WOOD_SET.getLog().asItem()))
                    .addCriterion("alpha_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.ALPHA_WOOD_SET.getLog().asItem()))
                    .addCriterion("baobab_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.BAOBAB_WOOD_SET.getLog().asItem()))
                    .addCriterion("blackwood_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.BLACKWOOD_WOOD_SET.getLog().asItem()))
                    .addCriterion("brimwood_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.BRIMWOOD_WOOD_SET.getLog().asItem()))
                    .addCriterion("cobalt_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.COBALT_WOOD_SET.getLog().asItem()))
                    .addCriterion("cypress_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.CYPRESS_WOOD_SET.getLog().asItem()))
                    .addCriterion("dead_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.DEAD_WOOD_SET.getLog().asItem()))
                    .addCriterion("eucalyptus_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.EUCALYPTUS_WOOD_SET.getLog().asItem()))
                    .addCriterion("joshua_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.JOSHUA_WOOD_SET.getLog().asItem()))
                    .addCriterion("kapok_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.KAPOK_WOOD_SET.getLog().asItem()))
                    .addCriterion("larch_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.LARCH_WOOD_SET.getLog().asItem()))
                    .addCriterion("magnolia_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.MAGNOLIA_WOOD_SET.getLog().asItem()))
                    .addCriterion("maple_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.MAPLE_WOOD_SET.getLog().asItem()))
                    .addCriterion("mauve_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.MAUVE_WOOD_SET.getLog().asItem()))
                    .addCriterion("palm_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.PALM_WOOD_SET.getLog().asItem()))
                    .addCriterion("pine_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.PINE_WOOD_SET.getLog().asItem()))
                    .addCriterion("redwood_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.REDWOOD_WOOD_SET.getLog().asItem()))
                    .addCriterion("socotra_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.SOCOTRA_WOOD_SET.getLog().asItem()))
                    .addCriterion("willow_log", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.WILLOW_WOOD_SET.getLog().asItem()))
                    .addCriterion("blue_bioshroom_stem", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.BLUE_BIOSHROOM_WOOD_SET.getLog().asItem()))
                    .addCriterion("green_bioshroom_stem", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.GREEN_BIOSHROOM_WOOD_SET.getLog().asItem()))
                    .addCriterion("pink_bioshroom_stem", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.PINK_BIOSHROOM_WOOD_SET.getLog().asItem()))
                    .addCriterion("yellow_bioshroom_stem", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.YELLOW_BIOSHROOM_WOOD_SET.getLog().asItem()))

                    .save(saver, RegionsUnexplored.stringId("got_wood"));

            AdvancementHolder MYCOLOGIST = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            RUBlocks.BLUE_BIOSHROOM.get(),
                            Component.translatable("advancements.regions_unexplored.mycologist.title"),
                            Component.translatable("advancements.regions_unexplored.mycologist.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("blue_bioshroom", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.BLUE_BIOSHROOM.get().asItem()))
                    .addCriterion("pink_bioshroom", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.PINK_BIOSHROOM.get().asItem()))
                    .addCriterion("yellow_bioshroom", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.YELLOW_BIOSHROOM.get().asItem()))
                    .addCriterion("green_bioshroom", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.GREEN_BIOSHROOM.get().asItem()))
                    .save(saver, RegionsUnexplored.stringId("mycologist"));

            AdvancementHolder ANCIENT_SPECIMENS = Advancement.Builder.advancement()
                    .parent(MYCOLOGIST)
                    .display(
                            RUBlocks.BLUE_BIOSHROOM_WOOD_SET.getLog(),
                            Component.translatable("advancements.regions_unexplored.ancient_specimens.title"),
                            Component.translatable("advancements.regions_unexplored.ancient_specimens.description"),
                            null,
                            AdvancementType.CHALLENGE,
                            true,
                            true,
                            false
                    )
                    .addCriterion("blue_bioshroom_stem", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.BLUE_BIOSHROOM_WOOD_SET.getLog().asItem()))
                    .addCriterion("pink_bioshroom_stem", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.PINK_BIOSHROOM_WOOD_SET.getLog().asItem()))
                    .addCriterion("yellow_bioshroom_stem", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.YELLOW_BIOSHROOM_WOOD_SET.getLog().asItem()))
                    .addCriterion("green_bioshroom_stem", InventoryChangeTrigger.TriggerInstance.hasItems(RUBlocks.GREEN_BIOSHROOM_WOOD_SET.getLog().asItem()))
                    .save(saver, RegionsUnexplored.stringId("ancient_specimens"));
            
            CompoundTag beaconNbt = new CompoundTag();
            beaconNbt.putInt("levels", 0);
            
            AdvancementHolder RGBEACON = Advancement.Builder.advancement()
                .parent(Identifier.withDefaultNamespace("nether/create_beacon"))
                .display(display(RUBlocks.PRISMAGLASS.get(), "rgbeacon", AdvancementType.GOAL))
                .addCriterion("place_prismaglass_on_beacon", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(AllOfCondition.allOf(
                    LocationCheck.checkLocation(blockPredicate(RUBlocks.PRISMAGLASS.get(), b -> b)),
                    LocationCheck.checkLocation(blockPredicate(Blocks.BEACON, b -> b), BlockPos.ZERO.below()),
                    LocationCheck.checkLocation(blockPredicate(Blocks.BEACON, b -> b.hasNbt(beaconNbt)), BlockPos.ZERO.below()).invert()
                )))
            .save(saver, RegionsUnexplored.stringId("rgbeacon"));
        }
    }
    
    private static LocationPredicate.Builder blockPredicate(Block block, UnaryOperator<BlockPredicate.Builder> operator) {
        return LocationPredicate.Builder.location().setBlock(operator.apply(BlockPredicate.Builder.block().of(block)));
    }
    
    private static Advancement.Builder biomeAdvancement(AdvancementHolder parent, ItemLike item, String name, AdvancementType type) {
        return Advancement.Builder.advancement()
            .parent(parent)
            .display(
                item,
                Component.translatable("advancements.regions_unexplored." + name + ".title"),
                Component.translatable("advancements.regions_unexplored." + name + ".description"),
                null,
                type,
                true,
                true,
                type == AdvancementType.CHALLENGE
            );
    }
    
    private static Advancement.Builder addBiome(Advancement.Builder builder, HolderGetter<Biome> biomeGetter, ResourceKey<Biome> biome) {
        return builder.addCriterion(
            biome.identifier().getPath(),
            PlayerTrigger.TriggerInstance.located(
                LocationPredicate.Builder.inBiome(biomeGetter.getOrThrow(biome))
            )
        );
    }
    
    private static AdvancementHolder save(Consumer<AdvancementHolder> saver, Advancement.Builder builder, String name) {
        return builder.save(saver, RegionsUnexplored.stringId(name));
    }
    
    
    private static DisplayInfo display(ItemLike icon, String name, AdvancementType type) {
        return new DisplayInfo(
            new ItemStack(icon),
            text(name, "title"),
            text(name, "description"),
            Optional.empty(),
            type,
            true,
            true,
            false
        );
    }
    
    private static Component text(String name, String suffix) {
        return Component.translatable(String.format("advancements.regions_unexplored.%s.%s", name, suffix));
    }
}

package net.regions_unexplored.registry.tag;

import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.tags.TagKey;
import net.regions_unexplored.RegionsUnexplored;

public interface RUBlockTags {
   TagKey<Block> SUPPORTS_ASHEN_GRASS = key("supports/ashen_grass");
   TagKey<Block> SUPPORTS_BRANCHES = key("supports/branches");
   TagKey<Block> SUPPORTS_FROZEN_GRASS = key("supports/frozen_grass");
   TagKey<Block> SUPPORTS_GRASS_SPROUTS = key("supports/grass_sprouts");
   TagKey<Block> SUPPORTS_INFERNAL_PLANT = key("supports/infernal_plant");
   TagKey<Block> SUPPORTS_NETHER_PLANTS = key("supports/nether_plants");
   TagKey<Block> SUPPORTS_RED_SANDY_PLANTS = key("supports/red_sandy_plants");
   TagKey<Block> SUPPORTS_SANDY_PLANTS = key("supports/sandy_plants");
   TagKey<Block> SUPPORTS_SHRUBS = key("supports/shrubs");

   TagKey<Block> REPLACEABLE_BY_PEAT_DIRT = key("replaceable_by_peat_dirt");
   TagKey<Block> REPLACEABLE_BY_SILT_DIRT = key("replaceable_by_silt_dirt");

   TagKey<Block> DIRT_AND_PODZOL = key("dirt_and_podzol");
   
   TagKey<Block> PEAT_ALL = key("peat/all");
   TagKey<Block> PEAT_SUBSTRATE = key("peat/substrate");
   TagKey<Block> SILT_ALL = key("silt/all");
   TagKey<Block> SILT_SUBSTRATE = key("silt/substrate");

   TagKey<Block> CATTAIL_CAN_SURVIVE_ON = key("cattail_can_survive_on");
   TagKey<Block> BIOSHROOM_GROW_BLOCK = key("bioshroom_grow_block");
   TagKey<Block> REPLACEABLE_BLOCKS = key("replaceable_blocks");
   TagKey<Block> PRISMARITE_CRYSTALS = key("prismarite_crystals");
   TagKey<Block> GRASS = key("grass");
   TagKey<Block> SHRUBS = key("shrubs");
   TagKey<Block> TREE_GRASS_REPLACEABLES = key("tree_grass_replaceables");
   TagKey<Block> ASH = key("ash");
   TagKey<Block> BRANCHES = key("branches");
   TagKey<Block> HYACINTH_BLOOMS = key("hyacinth_blooms");
   TagKey<Block> ALPHA_LOGS = key("alpha_logs");
   TagKey<Block> BAMBOO_LOGS = key("bamboo_logs");
   TagKey<Block> BAOBAB_LOGS = key("baobab_logs");
   TagKey<Block> BLACKWOOD_LOGS = key("blackwood_logs");
   TagKey<Block> BLUE_BIOSHROOM_LOGS = key("blue_bioshroom_logs");
   TagKey<Block> BRIMWOOD_LOGS = key("brimwood_logs");
   TagKey<Block> COBALT_LOGS = key("cobalt_logs");
   TagKey<Block> CYPRESS_LOGS = key("cypress_logs");
   TagKey<Block> DEAD_LOGS = key("dead_logs");
   TagKey<Block> EUCALYPTUS_LOGS = key("eucalyptus_logs");
   TagKey<Block> GREEN_BIOSHROOM_LOGS = key("green_bioshroom_logs");
   TagKey<Block> JOSHUA_LOGS = key("joshua_logs");
   TagKey<Block> KAPOK_LOGS = key("kapok_logs");
   TagKey<Block> LARCH_LOGS = key("larch_logs");
   TagKey<Block> MAGNOLIA_LOGS = key("magnolia_logs");
   TagKey<Block> MAPLE_LOGS = key("maple_logs");
   TagKey<Block> PALM_LOGS = key("palm_logs");
   TagKey<Block> PINE_LOGS = key("pine_logs");
   TagKey<Block> PINK_BIOSHROOM_LOGS = key("pink_bioshroom_logs");
   TagKey<Block> REDWOOD_LOGS = key("redwood_logs");
   TagKey<Block> SOCOTRA_LOGS = key("socotra_logs");
   TagKey<Block> WILLOW_LOGS = key("willow_logs");
   TagKey<Block> WISTERIA_LOGS = key("wisteria_logs");
   TagKey<Block> YELLOW_BIOSHROOM_LOGS = key("yellow_bioshroom_logs");
   TagKey<Block> CROP_PLANTABLE_BLOCKS = key("crop_plantable_blocks");

   private static TagKey<Block> key(String name) {
      return TagKey.create(Registries.BLOCK, RegionsUnexplored.id(name));
   }
}

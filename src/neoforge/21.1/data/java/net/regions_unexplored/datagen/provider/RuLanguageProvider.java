package net.regions_unexplored.datagen.provider;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.LanguageProvider;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.RUBlocks;
import net.regions_unexplored.registry.RUItems;
import net.regions_unexplored.registry.data.RUBiomes;
import net.regions_unexplored.registry.RUEntityTypes;
import org.apache.commons.lang3.text.WordUtils;
import org.jetbrains.annotations.NotNull;

public class RuLanguageProvider extends LanguageProvider {
    public RuLanguageProvider(PackOutput output) {
        super(output, RegionsUnexplored.MOD_ID, "en_us");
    }

    @Override
    protected void addTranslations() {
        // Config translations
        category("client_options");
        category("particles");
        category("block_color_options");
        category("features");
        category("regions", "Regions");
        category("overworld_biome_toggles", "Biome Toggles (Overworld)");
        category("overworld_cave_biome_toggles", "Biome Toggles (Caves)");
        category("nether_biome_toggles", "Biome Toggles (Nether)");
        category("worldgen_surface_rules");


        this.add("item_group.regions_unexplored.main", "Regions Unexplored");
        // Advancement translations
        this.add("advancements.regions_unexplored.title", "Regions Unexplored");
        this.add("advancements.regions_unexplored.description", "Discover the many biomes and explore the world!");

        this.add("advancements.regions_unexplored.pioneer.title", "Pioneer");
        this.add("advancements.regions_unexplored.pioneer.description", "Explore all Surface biomes from Regions Unexplored!");

        this.add("advancements.regions_unexplored.regions_explored.title", "Regions Explored");
        this.add("advancements.regions_unexplored.regions_explored.description", "You've explored all the biomes from Regions Unexplored");

        this.add("advancements.regions_unexplored.every_bit_of_the_rainbow.title", "Every Bit of the Rainbow");
        this.add("advancements.regions_unexplored.every_bit_of_the_rainbow.description", "Collect or craft every colour of the Snowbelle Flower.");

        this.add("advancements.regions_unexplored.from_the_tree_tops.title", "From the Tree Tops");
        this.add("advancements.regions_unexplored.from_the_tree_tops.description", "Hang from a Kapok tree's vines.");

        this.add("advancements.regions_unexplored.light_as_a_frog.title", "Light as a Frog");
        this.add("advancements.regions_unexplored.light_as_a_frog.description", "Walk or bounce on a Giant Lily Pad.");

        this.add("advancements.regions_unexplored.eternal_expedition.title", "Eternal Expedition");
        this.add("advancements.regions_unexplored.eternal_expedition.description", "Venture into all Nether biomes from Regions Unexplored!");

        this.add("advancements.regions_unexplored.downer.title", "Downer");
        this.add("advancements.regions_unexplored.downer.description", "Walk through and take damage from a Dorcel Flower.");

        this.add("advancements.regions_unexplored.light_snack.title", "Light Snack");
        this.add("advancements.regions_unexplored.light_snack.description", "Consume a Hanging Earlight Fruit.");

        this.add("advancements.regions_unexplored.spelunker.title", "Spelunker");
        this.add("advancements.regions_unexplored.spelunker.description", "Find all Cave biomes from Regions Unexplored!");

        this.add("advancements.regions_unexplored.blind_as_a_bat.title", "Blind as a Bat");
        this.add("advancements.regions_unexplored.blind_as_a_bat.description", "Consume a Duskmelon.");

        this.add("advancements.regions_unexplored.this_tree_bleeds_red.title", "This Tree Bleeds Red");
        this.add("advancements.regions_unexplored.this_tree_bleeds_red.description", "Chop down a Socotra tree.");

        this.add("advancements.regions_unexplored.got_wood.title", "Got Wood?");
        this.add("advancements.regions_unexplored.got_wood.description", "Collect every log from Regions Unexplored.");

        this.add("advancements.regions_unexplored.mycologist.title", "Mycologist");
        this.add("advancements.regions_unexplored.mycologist.description", "Collect every Bioshroom type.");

        this.add("advancements.regions_unexplored.ancient_specimens.title", "Ancient Specimens");
        this.add("advancements.regions_unexplored.ancient_specimens.description", "Collect every Bioshroom Stem type.");
        
        this.add("advancements.regions_unexplored.rgbeacon.title", "RGBeacon");
        this.add("advancements.regions_unexplored.rgbeacon.description", "Place Prismaglass on an active Beacon");

        // Block translations
        BuiltInRegistries.BLOCK.stream().forEach(blockRegistryObject -> {
            if(blockRegistryObject.toString().contains("regions_unexplored")&&
                    !blockRegistryObject.toString().contains("potted_")&&
                    !blockRegistryObject.toString().contains("hanging_earlight")&&
                    !blockRegistryObject.toString().contains("duskmelon")&&
                    !blockRegistryObject.toString().contains("salmonberry")&&
                    !blockRegistryObject.toString().contains("_wall_sign")&&
                    !blockRegistryObject.toString().contains("_wall_hanging_sign")){
                this.add(blockRegistryObject, capitalizeString(filterBlockLang(blockRegistryObject)));
            }
        });
        //Misc block translations
        this.add(RUBlocks.DUSKMELON.get(), "Duskmelon Slice");
        this.add(RUBlocks.SALMONBERRY_BUSH.get(), "Salmonberry");
        this.add(RUBlocks.HANGING_EARLIGHT.get(), "Hanging Earlight Fruit");

        //Entity translations
        this.add(RUEntityTypes.ASHEN.get(), "Ashen");
        this.add(RUEntityTypes.BOAT.get(), "Boat");
        this.add(RUEntityTypes.CHEST_BOAT.get(), "Boat With Chest");

        this.add("death.attack.dorcel", "%s was dragged underground by Dorcel");
        this.add("death.attack.dorcel.player", "%s was dragged underground by Dorcel");

        this.add("death.attack.dusk_trap", "%s was eaten by a Dusktrap");
        this.add("death.attack.dusk_trap.player", "%s was eaten by a Dusktrap");

        // Item translations
        BuiltInRegistries.ITEM.stream().forEach(item -> {
            Identifier id = item.builtInRegistryHolder().key().identifier();
            if(id.getNamespace().equals("regions_unexplored")){
                String path = id.getPath();
                if (path.contains("chest_boat")) {
                    this.add(item, filterChestBoatLang(item));
                } else if (path.contains("boat")) {
                    this.add(item, capitalizeString(filterItemLang(item)));
                }
            }
        });
        this.add(RUItems.IRIDESCENT_RING.get(), "Iridescent Ring");

        // Biome Translations
        for (ResourceKey<Biome> biome : RUBiomes.ALL_BIOMES) {
            String name = capitalizeString(filterBiomeLang(biome));
            if (RUBiomes.REMOVED_BIOMES.contains(biome)) {
                name = name + " (Removed)";
            }
            this.add(biome, name);
        }
        
        this.add("config.regions_unexplored.default", "Default: ");
        addCategory("particle_rates");
        addOption("leaves", "Multiplier of falling leaf/needle particle spawn rates.");
        addOption("prismarite", "Multiplier of prismarite sparkle particle spawn rates.");
        addCategory("eucalyptus_colors");
        addOption("transition_size", "The size of a rainbow color band in Eucalyptus logs. Bigger value = bigger distance between colors.");
        addOption("saturation", "The saturation of the rainbow colors.");
        addOption("brightness", "The brightness of the rainbow colors.");
        addCategory("misc_worldgen_settings");
        addOption("branch_mode");
        addOption("branch_mode.place_branches", "Place RU's dedicated branch blocks on some trees.");
        addOption("branch_mode.place_logs", "Place log blocks in place of branch blocks on some trees.");
        addOption("branch_mode.dont_place", "Don't place any branches on trees that would usually have dedicated branch blocks.");
        addOption("custom_dirts", "Peat and Silt dirt block families will generate in some RU biomes");
        addCategory("biome_placements");
    }
    
    private void addCategory(String key) {
        this.add("config.regions_unexplored.category." + key, capitalizeString(key).replace("_", " "));
    }
    
    private void addOption(String key) {
        this.add("config.regions_unexplored.option." + key, capitalizeString(key).replace("_", " "));
    }
    
    private void addOption(String key, String comment) {
        this.addOption(key);
        this.add("config.regions_unexplored.option." + key + ".tooltip", comment);
    }
    
    
    /**
     * A method to capitalize a string and remove anything what's between it.
     * <a href="https://stackoverflow.com/questions/1892765/how-to-capitalize-the-first-character-of-each-word-in-a-string">Source</a>
     * @param string the string you want to capitalize.
     * @return a capitalised string.
     */
    private static @NotNull String capitalizeString(String string) {
        char[] chars = string.toLowerCase().toCharArray();
        boolean found = false;
        for (int i = 0; i < chars.length; i++) {
            if (!found && Character.isLetter(chars[i])) {
                chars[i] = Character.toUpperCase(chars[i]);
                found = true;
            } else if (Character.isWhitespace(chars[i]) || chars[i] == '.' || chars[i] == '_') {
                /*small change to fix chest boat lang
                if(string.contains("with")&&chars[i+1]!='w'){
                }*/
                found = false;
            }
        }
        return String.valueOf(chars);
    }

    /**
     * Filters the block to get the name of the block.
     * @param key The block.
     * @return The name of the block
     */
    private static @NotNull String filterBlockLang(@NotNull Block key) {
        return key.getDescriptionId()
                .replace("block.regions_unexplored.", "")
                .replace("_plant", "")
                .replace("_", " ");
    }

    private static @NotNull String filterItemLang(@NotNull ItemLike key) {
        return key.asItem().getDescriptionId()
                .replace("item.regions_unexplored.", "")
                .replace("_", " ");
    }

    private static @NotNull String filterChestBoatLang(@NotNull ItemLike key) {
        String type = key.asItem().getDescriptionId()
                .replace("item.regions_unexplored.", "")
                .replace("chest_boat", "")
                .replace("_", "");
	    
	    return capitalizeString(type) + " Boat with Chest";
    }


    private static String filterBiomeLang(ResourceKey<Biome> key) {
        return key.identifier().toLanguageKey()
                .replace("regions_unexplored.", "")
                .replace("_", " ");
    }

    // used to create a biome translation string
    private void add(ResourceKey<Biome> key, String translation) {
        this.add("biome." + key.identifier().toLanguageKey(), translation);
    }

    private void category(String name) {
        category(name, capitalizeString(name.replace("_", " ")));
    }

    private void category(String id, String name) {
        this.add("config.regions_unexplored.category." + id, name);
    }
}

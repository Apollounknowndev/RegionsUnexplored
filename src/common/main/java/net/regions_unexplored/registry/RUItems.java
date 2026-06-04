package net.regions_unexplored.registry;

import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.*;
import net.regions_unexplored.item.RUItemUtils;
import net.regions_unexplored.item.type.IridescentRingItem;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static net.regions_unexplored.RegionsUnexplored.id;
import static net.regions_unexplored.item.RUItemUtils.food;

public interface RUItems {
    Supplier<Item> SALMONBERRY = RUItemUtils.register("salmonberry", p -> new BlockItem(RUBlocks.SALMONBERRY_BUSH.get(), p.food(food(3, 0.3f, t -> t))));
    Supplier<Item> DUSKMELON_SLICE = RUItemUtils.register("duskmelon_slice", p -> new BlockItem(RUBlocks.DUSKMELON.get(), p.food(food(5, 1.1f, t -> t.effect(new MobEffectInstance(MobEffects.BLINDNESS, 240), 1)))));
    Supplier<Item> HANGING_EARLIGHT_FRUIT = RUItemUtils.register("hanging_earlight_fruit", p -> new BlockItem(RUBlocks.HANGING_EARLIGHT.get(), p.food(food(6, 0.4f, t -> t.effect(new MobEffectInstance(MobEffects.GLOWING, 200), 0.1F)))));
    Supplier<Item> MEADOW_SAGE = RUItemUtils.register("meadow_sage", p -> new BlockItem(RUBlocks.MEADOW_SAGE.get(), p.food(food(2, 0.15f, t -> t.effect(new MobEffectInstance(MobEffects.INSTANT_HEALTH, 20), 0.5f)))));
    Supplier<Item> IRIDESCENT_RING = RUItemUtils.register("iridescent_ring", IridescentRingItem::new);

    static void applyAliases(BiConsumer<Identifier, Identifier> consumer) {
        consumer.accept(id("medium_grass"), id("grass_sprouts"));
        consumer.accept(id("stone_bud"), id("grass_sprouts"));
        
        consumer.accept(id("cactus_flower"), id("saguaro_cactus_flower"));
        
        consumer.accept(id("maple_leaf_pile"), id("maple_leaf_litter"));
        consumer.accept(id("red_maple_leaf_pile"), id("red_maple_leaf_litter"));
        consumer.accept(id("orange_maple_leaf_pile"), id("orange_maple_leaf_litter"));
        consumer.accept(id("silver_birch_leaf_pile"), id("silver_birch_leaf_litter"));
        consumer.accept(id("enchanted_birch_leaf_pile"), id("enchanted_birch_leaf_litter"));
        
        consumer.accept(id("mauve_branch"), id("wisteria_branch"));
        consumer.accept(id("mauve_shrub"), id("lavender_wisteria_shrub"));
        consumer.accept(id("mauve_leaves"), id("lavender_wisteria_leaves"));
        consumer.accept(id("mauve_sapling"), id("lavender_wisteria_sapling"));
        
        consumer.accept(id("enchanted_birch_shrub"), id("sky_wisteria_shrub"));
        consumer.accept(id("enchanted_birch_leaves"), id("sky_wisteria_leaves"));
        consumer.accept(id("enchanted_birch_sapling"), id("sky_wisteria_sapling"));
        
        consumer.accept(id("mauve_log"), id("wisteria_log"));
        consumer.accept(id("mauve_wood"), id("wisteria_wood"));
        consumer.accept(id("stripped_mauve_log"), id("stripped_wisteria_log"));
        consumer.accept(id("stripped_mauve_wood"), id("stripped_wisteria_wood"));
        consumer.accept(id("mauve_planks"), id("wisteria_planks"));
        consumer.accept(id("mauve_stairs"), id("wisteria_stairs"));
        consumer.accept(id("mauve_slab"), id("wisteria_slab"));
        consumer.accept(id("mauve_fence"), id("wisteria_fence"));
        consumer.accept(id("mauve_fence_gate"), id("wisteria_fence_gate"));
        consumer.accept(id("mauve_door"), id("wisteria_door"));
        consumer.accept(id("mauve_trapdoor"), id("wisteria_trapdoor"));
        consumer.accept(id("mauve_pressure_plate"), id("wisteria_pressure_plate"));
        consumer.accept(id("mauve_button"), id("wisteria_button"));
        consumer.accept(id("mauve_sign"), id("wisteria_sign"));
        consumer.accept(id("mauve_hanging_sign"), id("wisteria_hanging_sign"));
        consumer.accept(id("mauve_boat"), id("wisteria_boat"));
        consumer.accept(id("mauve_chest_boat"), id("wisteria_chest_boat"));
    }

    static void init() {

    }
}

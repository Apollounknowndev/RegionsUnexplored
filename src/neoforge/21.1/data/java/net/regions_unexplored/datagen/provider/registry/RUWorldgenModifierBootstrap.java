package net.regions_unexplored.datagen.provider.registry;

import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries;
import dev.worldgen.lithostitched.api.util.InjectionType;
import dev.worldgen.lithostitched.api.worldgen.modifier.WorldgenModifier;
import dev.worldgen.lithostitched.api.worldgen.surface.LithostitchedSurfaceRules;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.level.Level;
import net.regions_unexplored.RegionsUnexplored;
import net.regions_unexplored.registry.data.RUSurfaceRules;

import static net.regions_unexplored.registry.data.RUDamageTypes.DORCEL;
import static net.regions_unexplored.registry.data.RUDamageTypes.DUSK_TRAP;

public class RUWorldgenModifierBootstrap {
    public static void bootstrap(BootstrapContext<WorldgenModifier> context) {
        context.register(
            RegionsUnexplored.key(LithostitchedRegistries.WORLDGEN_MODIFIER, "add_surface_rule/overworld"),
            WorldgenModifier.builder().addSurfaceRule(Registries.levelToLevelStem(Level.OVERWORLD), InjectionType.PREPEND, LithostitchedSurfaceRules.reference(
                context.lookup(LithostitchedRegistries.SURFACE_RULE).getOrThrow(RUSurfaceRules.OVERWORLD)
            ))
        );
    }
}

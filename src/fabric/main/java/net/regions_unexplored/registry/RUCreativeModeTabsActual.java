package net.regions_unexplored.registry;

import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
import net.minecraft.world.item.CreativeModeTab;
import net.msrandom.multiplatform.annotations.Actual;

public interface RUCreativeModeTabsActual {
    @Actual
    static CreativeModeTab.Builder builder() {
        return FabricCreativeModeTab.builder();
    }
}

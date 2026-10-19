package net.regions_unexplored.module.platform;

import java.nio.file.Path;

//? if fabric
import net.fabricmc.loader.api.FabricLoader;
//? else
//import net.neoforged.fml.loading.FMLPaths;

public class ConfigHelper {
    public static Path getConfigDirectory() {
        //? if fabric
        return FabricLoader.getInstance().getConfigDir().resolve("regions_unexplored");
        //? else
        //return FMLPaths.CONFIGDIR.get().resolve("regions_unexplored");
    }
}

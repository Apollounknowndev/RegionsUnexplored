package net.regions_unexplored.registry;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.regions_unexplored.platform.Registrar;
import net.regions_unexplored.worldgen.treedecorator.*;

import java.util.function.Supplier;

public interface RUTreeDecoratorTypes {
    Supplier<TreeDecoratorType<AttachedToLogsDecorator>> ATTACHED_TO_LOGS = register("attached_to_logs", AttachedToLogsDecorator.TYPE);
    Supplier<TreeDecoratorType<GroupBranchDecorator>> GROUP_BRANCH = register("group_branch", GroupBranchDecorator.TYPE);
    Supplier<TreeDecoratorType<HangingVinesDecorator>> HANGING_VINES = register("hanging_vines", HangingVinesDecorator.TYPE);
    Supplier<TreeDecoratorType<PlaceOnGroundDecorator>> PLACE_ON_GROUND = register("place_on_ground", PlaceOnGroundDecorator.TYPE);
    Supplier<TreeDecoratorType<RandomBranchDecorator>> RANDOM_BRANCH = register("random_branch", RandomBranchDecorator.TYPE);
    Supplier<TreeDecoratorType<WillowTrunkDecorator>> WILLOW = register("willow", WillowTrunkDecorator.TYPE);

    static <T extends TreeDecorator> Supplier<TreeDecoratorType<T>> register(String name, TreeDecoratorType<T> type) {
        Registrar.register(BuiltInRegistries.TREE_DECORATOR_TYPE, name, () -> type);
        return () -> type;
    }

    static void init() {
    }
}

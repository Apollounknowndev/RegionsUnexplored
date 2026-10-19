package net.regions_unexplored.mixin;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(VillagerProfession.class)
public interface VillagerProfessionAccessor {
	@Accessor("secondaryPoi")
	@Final @Mutable
	void regionsUnexplored$setSecondaryPoi(ImmutableSet<Block> secondaryPoi);
}

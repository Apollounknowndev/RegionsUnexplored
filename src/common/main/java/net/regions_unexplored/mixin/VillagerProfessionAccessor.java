package net.regions_unexplored.mixin;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(VillagerProfession.class)
public interface VillagerProfessionAccessor {
	@Accessor("secondaryPoi")
	@Final @Mutable
	void regionsUnexplored$setSecondaryPoi(ImmutableSet<Block> secondaryPoi);
}

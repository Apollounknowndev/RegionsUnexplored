package net.regions_unexplored.block.type.dirt;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.regions_unexplored.registry.RUBlocks;

import javax.annotation.Nullable;
import java.util.Optional;
import java.util.function.Supplier;

public class RUDirtBlock extends Block {
    private final Optional<Supplier<Block>> pathBlock;
    private final Optional<Supplier<Block>> farmlandBlock;
    private final boolean hasItemInteraction;
    
    public static RUDirtBlock simple(Properties properties) {
	    return new RUDirtBlock(null, null, properties);
    }

    public RUDirtBlock(@Nullable Supplier<Block> pathBlock, @Nullable Supplier<Block> farmlandBlock, Properties properties) {
        super(properties);
        this.pathBlock = Optional.ofNullable(pathBlock);
        this.farmlandBlock = Optional.ofNullable(farmlandBlock);
        this.hasItemInteraction = this.pathBlock.isPresent() || this.farmlandBlock.isPresent();
    }
    
    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        if (!this.hasItemInteraction || playerHasShieldUseIntent(player, hand) || !level.getBlockState(pos.above()).isAir()) {
            return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
        }
        
        if (stack.getItem() instanceof ShovelItem && updateBlock(this.pathBlock, SoundEvents.SHOVEL_FLATTEN, stack, level, pos, player, hand)) {
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        
        if (stack.getItem() instanceof HoeItem && updateBlock(this.farmlandBlock, SoundEvents.HOE_TILL, stack, level, pos, player, hand)) {
            return ItemInteractionResult.sidedSuccess(level.isClientSide);
        }
        
        return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }
    
    protected static boolean updateBlock(Optional<Supplier<Block>> block, SoundEvent sound, ItemStack stack, Level level, BlockPos pos, Player player, InteractionHand hand) {
        if (block.isEmpty()) return false;
        BlockState state = block.get().get().defaultBlockState();
        if (player instanceof ServerPlayer serverPlayer) {
            CriteriaTriggers.ITEM_USED_ON_BLOCK.trigger(serverPlayer, pos, stack);
        }
        level.playSound(player, pos, sound, SoundSource.BLOCKS, 1.0F, 1.0F);
        level.setBlock(pos, state, 11);
        level.gameEvent(GameEvent.BLOCK_CHANGE, pos, GameEvent.Context.of(player, state));
        stack.hurtAndBreak(1, player, LivingEntity.getSlotForHand(hand));
        return true;
    }
    
    private static boolean playerHasShieldUseIntent(Player player, InteractionHand hand) {
        return hand.equals(InteractionHand.MAIN_HAND) && player.getOffhandItem().is(Items.SHIELD) && !player.isSecondaryUseActive();
    }
}
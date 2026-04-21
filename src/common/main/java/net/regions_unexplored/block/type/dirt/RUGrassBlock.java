package net.regions_unexplored.block.type.dirt;

import java.util.Optional;
import java.util.function.Supplier;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.lighting.LightEngine;
import net.minecraft.world.phys.BlockHitResult;
import net.regions_unexplored.world.level.block.RUBlockActions;

import javax.annotation.Nullable;

public class RUGrassBlock extends SnowyDirtBlock implements BonemealableBlock {
	private final Supplier<Block> baseBlock;
	private final Optional<Supplier<Block>> pathBlock;
	private final Optional<Supplier<Block>> farmlandBlock;
	private final boolean hasItemInteraction;
	private final ResourceKey<PlacedFeature> bonemealFeature;
	
	public static RUGrassBlock simple(Supplier<Block> baseBlock, ResourceKey<PlacedFeature> bonemealFeature, Properties properties) {
		return new RUGrassBlock(baseBlock, null, null, bonemealFeature, properties);
	}
	
	public RUGrassBlock(Supplier<Block> baseBlock, @Nullable Supplier<Block> pathBlock, @Nullable Supplier<Block> farmlandBlock, ResourceKey<PlacedFeature> bonemealFeature, Properties properties) {
		super(properties);
		this.baseBlock = baseBlock;
		this.pathBlock = Optional.ofNullable(pathBlock);
		this.farmlandBlock = Optional.ofNullable(farmlandBlock);
		this.hasItemInteraction = this.pathBlock.isPresent() || this.farmlandBlock.isPresent();
		this.bonemealFeature = bonemealFeature;
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
	
	@Override
	public boolean isValidBonemealTarget(final LevelReader level, final BlockPos pos, final BlockState state) {
		return level.getBlockState(pos.above()).isAir() && !level.isOutsideBuildHeight(pos.above());
	}
	
	@Override
	public boolean isBonemealSuccess(final Level level, final RandomSource random, final BlockPos pos, final BlockState state) {
		return true;
	}
	
	@Override
	public void performBonemeal(final ServerLevel level, final RandomSource random, final BlockPos pos, final BlockState state) {
		RUBlockActions.performBonemeal(this, level, random, pos, this.bonemealFeature);
	}
	
	@Override
	public Type getType() {
		return Type.NEIGHBOR_SPREADER;
	}
	
	@Override
	protected void randomTick(final BlockState state, final ServerLevel level, final BlockPos pos, final RandomSource random) {
		if (!canStayAlive(state, level, pos)) {
			level.setBlockAndUpdate(pos, this.baseBlock.get().defaultBlockState());
		} else {
			if (level.getMaxLocalRawBrightness(pos.above()) >= 9) {
				BlockState defaultBlockState = this.defaultBlockState();
				
				for (int i = 0; i < 4; i++) {
					BlockPos testPos = pos.offset(random.nextInt(3) - 1, random.nextInt(5) - 3, random.nextInt(3) - 1);
					if (level.getBlockState(testPos).is(this.baseBlock.get()) && canPropagate(defaultBlockState, level, testPos)) {
						level.setBlockAndUpdate(testPos, defaultBlockState.setValue(SNOWY, level.getBlockState(testPos.above()).is(BlockTags.SNOW)));
					}
				}
			}
		}
	}
	
	private static boolean canStayAlive(final BlockState state, final LevelReader level, final BlockPos pos) {
		BlockPos above = pos.above();
		BlockState aboveState = level.getBlockState(above);
		if (aboveState.is(Blocks.SNOW) && aboveState.getValue(SnowLayerBlock.LAYERS) == 1) {
			return true;
		} else if (aboveState.getFluidState().getAmount() == 8) {
			return false;
		} else {
			int lightBlockInto = LightEngine.getLightBlockInto(level, state, pos, aboveState, above, Direction.UP, aboveState.getLightBlock(level, above));
			return lightBlockInto < 15;
		}
	}
	
	private static boolean canPropagate(final BlockState state, final LevelReader level, final BlockPos pos) {
		BlockPos above = pos.above();
		return canStayAlive(state, level, pos) && !level.getFluidState(above).is(FluidTags.WATER);
	}
}

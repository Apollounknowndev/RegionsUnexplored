package net.regions_unexplored.item.type;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.level.Level;
import net.regions_unexplored.client.color.RuColors;
import net.regions_unexplored.registry.RUParticleTypes;
import org.jetbrains.annotations.NotNull;

public class IridescentRingItem extends Item {
	public IridescentRingItem(Properties properties) {
		super(properties);
	}
	
	@Override
	public int getUseDuration(ItemStack stack, LivingEntity entity) {
		return 1200;
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		player.awardStat(Stats.ITEM_USED.get(this));
		return ItemUtils.startUsingInstantly(level, player, hand);
	}
	
	@Override
	public void onUseTick(Level level, LivingEntity entity, ItemStack stack, int ticksRemaining) {
		if (!(level instanceof ServerLevel serverLevel)) return;
		
		RandomSource random = level.getRandom();
		
		serverLevel.sendParticles(
			RUParticleTypes.PRISMARITE_SPARKLE.get(),
			entity.getX() + random.nextGaussian(),
			entity.getY(0.5) + random.nextGaussian(),
			entity.getZ() + random.nextGaussian(),
			1, 0, 0, 0, 0
		);
	}
	
	@Override
	@NotNull
	public Component getName(ItemStack stack) {
		String string = super.getName(stack).getString();
		char[] characters = string.toCharArray();
		
		MutableComponent name = Component.empty();
		float step = 50f / characters.length;
		
		for (int i = 0; i < characters.length; i++) {
			char character = characters[i];
			name.append(
				Component.literal(String.valueOf(character)).withColor(RuColors.getRainbowColor(0, i * step))
			);
		}
		return name;
	}
}

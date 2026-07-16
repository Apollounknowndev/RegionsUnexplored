package net.regions_unexplored.item.type;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
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
	public void inventoryTick(ItemStack itemStack, Level level, Entity owner, int i, boolean inSlot) {
		RandomSource random = level.getRandom();
		if (!(level instanceof ServerLevel serverLevel) || !inSlot || random.nextBoolean()) return;
		
		serverLevel.sendParticles(
			RUParticleTypes.PRISMARITE_SPARKLE.get(),
			owner.getX() + random.nextGaussian(),
			owner.getY(0.5) + random.nextGaussian(),
			owner.getZ() + random.nextGaussian(),
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
				Component.literal(String.valueOf(character)).withColor(RuColors.getRainbowColor(0, i * step, 0.8f))
			);
		}
		return name;
	}
}

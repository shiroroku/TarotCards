package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import javax.annotation.Nullable;
import java.util.List;

public class TemperanceTarot extends TarotItem {


	public static float handleExhaustionAmount(float amount, Player player) {
		if (hasTarot(player, ItemRegistry.temperance.get()) && amount != 0) {
			TarotCards.LOGGER.debug("{} - Reducing hunger", ItemRegistry.temperance.get());
			TarotCards.LOGGER.debug("From: {}, To: {}, For: {}", amount, (float) (amount * (1f - Configuration.temperance_reduction.get())), player);
			return (float) (amount * (1f - Configuration.temperance_reduction.get()));
		}
		return amount;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", String.valueOf(Configuration.temperance_reduction.get() * 100)).withStyle(ChatFormatting.BLUE));
	}

}

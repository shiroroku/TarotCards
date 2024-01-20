package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import javax.annotation.Nullable;
import java.util.List;

public class TheHierophantTarot extends TarotItem {

	public static void handleOnPlayerPickupXp(PlayerXpEvent.PickupXp event) {
		if (hasTarot(event.getPlayer(), ItemRegistry.the_hierophant.get())) {
			int new_value = (int) Math.round(event.getOrb().value * Configuration.the_hierophant_xpboost.get());
			TarotCards.LOGGER.debug("{} - XP Boost", ItemRegistry.the_hierophant.get());
			TarotCards.LOGGER.debug("From: {}, To: {}", event.getOrb().value, new_value);
			event.getOrb().value = new_value;
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc", Configuration.the_hierophant_xpboost.get() * 100 + "").withStyle(ChatFormatting.BLUE));
	}
}

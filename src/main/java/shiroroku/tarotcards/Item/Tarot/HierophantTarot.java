package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import shiroroku.tarotcards.TarotCards;
import shiroroku.tarotcards.Registry.ItemRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class HierophantTarot extends TarotItem {

	public static final float boost = 2f;

	public static void handleOnPlayerPickupXp(PlayerXpEvent.PickupXp event) {
		if (hasTarot(event.getPlayer(), ItemRegistry.the_hierophant.get())) {
			TarotCards.LOGGER.debug("TAROT PASSIVE: {} - XP Boost", ItemRegistry.the_hierophant.get());
			TarotCards.LOGGER.debug("From: {} To: {}", event.getOrb().value, event.getOrb().value * boost);
			event.getOrb().value = (int) (event.getOrb().value * boost);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc", boost * 100 + "").withStyle(ChatFormatting.BLUE));
	}
}

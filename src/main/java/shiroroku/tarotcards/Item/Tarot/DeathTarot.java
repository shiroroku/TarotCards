package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import shiroroku.tarotcards.TarotCards;
import shiroroku.tarotcards.Registry.ItemRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class DeathTarot extends TarotItem {

	public static final float boost = 0.5f;

	public static void handleOnHurt(LivingHurtEvent event) {
		if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Player player) {
			if (hasTarot(player, ItemRegistry.death.get())) {
				if (event.getEntityLiving().getMobType() != MobType.UNDEAD) {
					TarotCards.LOGGER.debug("TAROT PASSIVE: {} - 50% more damage to non-undead", ItemRegistry.death.get());
					TarotCards.LOGGER.debug("From : {}", player);
					TarotCards.LOGGER.debug("To : {}", event.getEntityLiving());
					TarotCards.LOGGER.debug("Amount : {} to {}", event.getAmount(), event.getAmount() * (1 + boost));
					event.setAmount(event.getAmount() * (1 + boost));
				}
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc", boost * 100).withStyle(ChatFormatting.BLUE));
	}
}

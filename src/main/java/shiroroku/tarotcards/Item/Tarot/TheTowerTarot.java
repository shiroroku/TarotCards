package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.TarotCards;
import shiroroku.tarotcards.Registry.ItemRegistry;

public class TheTowerTarot extends TarotItem {

	public static void handleOnHurt(LivingHurtEvent event) {
		if (event.getSource() == DamageSource.FALL && event.getEntityLiving() instanceof Player player) {
			if (hasTarot(player, ItemRegistry.the_tower.get())) {
				TarotCards.LOGGER.debug("TAROT PASSIVE: {} - Fall immune", ItemRegistry.the_tower.get());
				TarotCards.LOGGER.debug("To : {}", player);
				event.setCanceled(true);
			}
		}
	}

}

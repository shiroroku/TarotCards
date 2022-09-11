package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

public class TheDevilTarot extends TarotItem {

	public static void handleOnHurt(LivingHurtEvent event) {
		if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof Player player) {
			if (hasTarot(player, ItemRegistry.the_devil.get())) {
				TarotCards.LOGGER.debug("TAROT PASSIVE: {} - Inflict weakness", ItemRegistry.the_devil.get());
				TarotCards.LOGGER.debug("From : {}", player);
				TarotCards.LOGGER.debug("To : {}", event.getEntityLiving());
				event.getEntityLiving().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, Configuration.the_devil_weaknessamplifier.get()));
			}
		}
	}

}

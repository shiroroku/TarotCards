package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.neoforged.neoforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;

public class TheMoonTarot extends TarotItem {

	public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
		if (hasTarot(event.player, ItemRegistry.the_moon.get())) {
			event.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 220 + Configuration.tick_rate.get(), 0, true, false));
		}
	}
}

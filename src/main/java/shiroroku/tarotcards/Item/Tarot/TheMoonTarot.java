package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import shiroroku.tarotcards.Registry.ItemRegistry;

public class TheMoonTarot extends TarotItem {

	public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.player.tickCount % 20 == 0 && event.side == LogicalSide.SERVER) {
			if (hasTarot(event.player, ItemRegistry.the_moon.get())) {
				event.player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 240, 0, true, false));
			}
		}
	}
}

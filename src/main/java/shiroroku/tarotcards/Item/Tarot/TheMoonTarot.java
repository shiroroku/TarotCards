package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;

import java.util.function.Supplier;

public class TheMoonTarot extends TarotItem {

    private static final Supplier<MobEffectInstance> night_vision = () -> new MobEffectInstance(MobEffects.NIGHT_VISION, 240 + Configuration.tick_rate.get(), 0, true, false);

    public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
        if (hasTarot(event.player, ItemRegistry.the_moon.get())) {
            event.player.addEffect(night_vision.get());
        }
    }
}

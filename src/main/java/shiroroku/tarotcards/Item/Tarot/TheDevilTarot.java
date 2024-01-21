package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

public class TheDevilTarot extends TarotItem {

    public static void handleOnHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (hasTarot(player, ItemRegistry.the_devil.get())) {
                TarotCards.LOGGER.debug("{} - Inflict weakness", ItemRegistry.the_devil.get());
                TarotCards.LOGGER.debug("From: {}, To: {}", player, event.getEntity());
                event.getEntity().addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 60, Configuration.the_devil_weaknessamplifier.get()));
            }
        }
    }

}

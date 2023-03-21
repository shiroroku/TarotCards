package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

public class JusticeTarot extends TarotItem {

    private static final DamageSource justice = new DamageSource("justice").bypassArmor();

    public static void handleOnHurt(LivingHurtEvent event) {
        if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof LivingEntity attacker && event.getEntityLiving() instanceof Player player) {
            if (!event.getSource().getMsgId().equals(justice.getMsgId()) && hasTarot(player, ItemRegistry.justice.get())) {
                attacker.hurt(justice, event.getAmount());
                TarotCards.LOGGER.debug("TAROT PASSIVE: {} - Returning damage", ItemRegistry.justice.get());
                TarotCards.LOGGER.debug("From : {} [{}]", attacker, attacker.getHealth());
                TarotCards.LOGGER.debug("To : {}", player);
                TarotCards.LOGGER.debug("Amount : {}", event.getAmount());
            }
        }
    }

}

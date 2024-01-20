package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

public class TheEmpressTarot extends TarotItem {

    public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (hasTarot(player, ItemRegistry.the_empress.get())) {
            player.level().getNearbyEntities(Animal.class, TargetingConditions.forNonCombat(), player, player.getBoundingBox().inflate(Configuration.the_empress_range.get())).forEach(e -> {
                if (e.canFallInLove() && e.getAge() == 0) {
                    TarotCards.LOGGER.debug("{} - Set in love", ItemRegistry.the_empress.get());
                    TarotCards.LOGGER.debug("Animal: {}", e);
                    e.setInLove(player);
                }
                if (e.isBaby()) {
                    TarotCards.LOGGER.debug("{} - Feed baby", ItemRegistry.the_empress.get());
                    TarotCards.LOGGER.debug("Animal: {}", e);
                    e.ageUp(AgeableMob.getSpeedUpSecondsWhenFeeding(-e.getAge()), true);
                }
            });
        }
    }

}

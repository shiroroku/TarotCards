package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;

import java.util.List;

public class TheEmpressTarot extends TarotItem {

	public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		if (hasTarot(player, ItemRegistry.the_empress.get())) {
			List<Animal> entities = player.level.getNearbyEntities(Animal.class, TargetingConditions.DEFAULT, player, player.getBoundingBox().inflate(Configuration.the_empress_range.get()));
			for (Animal e : entities) {
				if (e.canFallInLove() && e.getAge() == 0) {
					e.setInLove(player);
				}

				if (e.isBaby()) {
					e.ageUp((int) ((float) (-e.getAge() / 20) * 0.1F), true);
				}
			}
		}
	}

}

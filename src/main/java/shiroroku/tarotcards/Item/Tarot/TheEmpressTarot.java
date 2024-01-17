package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.neoforged.neoforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;

import java.util.List;

public class TheEmpressTarot extends TarotItem {

	public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
		Player player = event.player;
		if (hasTarot(player, ItemRegistry.the_empress.get())) {
			List<Animal> animals = player.level().getNearbyEntities(Animal.class, TargetingConditions.DEFAULT, player, player.getBoundingBox().inflate(Configuration.the_empress_range.get()));

			//Breed adult animals
			animals.stream().filter(e -> e.canFallInLove() && e.getAge() == 0).forEach(e -> e.setInLove(player));

			//Grow baby animals
			animals.stream().filter(AgeableMob::isBaby).forEach(e -> e.ageUp(AgeableMob.getSpeedUpSecondsWhenFeeding(-e.getAge()), true));
		}
	}

}

package shiroroku.tarotcards;

import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import shiroroku.tarotcards.Item.Tarot.*;

public class Events {

	@SubscribeEvent
	public static void onLivingHurt(LivingHurtEvent event) {
		DeathTarot.handleOnHurt(event);
		JusticeTarot.handleOnHurt(event);
		TheDevilTarot.handleOnHurt(event);
		TheTowerTarot.handleOnHurt(event);
	}

	@SubscribeEvent
	public static void onLivingDamage(LivingDamageEvent event) {
		JudgementTarot.handleOnDamage(event);
	}

	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.player.tickCount % Configuration.tick_rate.get() == 0 && event.side == LogicalSide.SERVER) {
			event.player.level.getProfiler().push("TarotCards");
			StrengthTarot.handleOnPlayerTick(event);
			TheChariotTarot.handleOnPlayerTick(event);
			TheEmperorTarot.handleOnPlayerTick(event);
			TheFoolTarot.handleOnPlayerTick(event);
			TheHermitTarot.handleOnPlayerTick(event);
			TheHighPriestessTarot.handleOnPlayerTick(event);
			TheLoversTarot.handleOnPlayerTick(event);
			TheMoonTarot.handleOnPlayerTick(event);
			TheStarTarot.handleOnPlayerTick(event);
			TheSunTarot.handleOnPlayerTick(event);
			WheelOfFortuneTarot.handleOnPlayerTick(event);
			TheWorldTarot.handleOnPlayerTick(event);
			event.player.level.getProfiler().pop();
		}
	}

	@SubscribeEvent
	public static void onPlayerPickupXpEvent(PlayerXpEvent.PickupXp event) {
		TheHierophantTarot.handleOnPlayerPickupXp(event);
	}

}

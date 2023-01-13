package shiroroku.tarotcards;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import shiroroku.tarotcards.Container.TarotDeckScreen;
import shiroroku.tarotcards.Registry.ItemRegistry;

import java.awt.*;

@Mod.EventBusSubscriber(modid = TarotCards.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupClient {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> MenuScreens.register(TarotCards.tarot_deck.get(), TarotDeckScreen::new));
	}

	@SubscribeEvent
	public static void onItemColorHandler(RegisterColorHandlersEvent.Item event) {
		event.register((stack, tintIndex) -> {
			if (tintIndex == 0 && stack.getOrCreateTag().getBoolean("deactivated")) {
				float b = 0.3f;
				return new Color(b, b, b).getRGB();
			}
			return 16777215;
		}, ItemRegistry.death.get(), ItemRegistry.judgement.get(), ItemRegistry.justice.get(), ItemRegistry.strength.get(), ItemRegistry.temperance.get(), ItemRegistry.the_chariot.get(), ItemRegistry.the_devil.get(), ItemRegistry.the_emperor.get(), ItemRegistry.the_empress.get(), ItemRegistry.the_fool.get(), ItemRegistry.the_hanged_man.get(), ItemRegistry.the_hermit.get(), ItemRegistry.the_hierophant.get(), ItemRegistry.the_high_priestess.get(), ItemRegistry.the_lovers.get(), ItemRegistry.the_magician.get(), ItemRegistry.the_moon.get(), ItemRegistry.the_star.get(), ItemRegistry.the_sun.get(), ItemRegistry.the_tower.get(), ItemRegistry.the_world.get(), ItemRegistry.wheel_of_fortune.get());
	}
}


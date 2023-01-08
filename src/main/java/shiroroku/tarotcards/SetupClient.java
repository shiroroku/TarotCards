package shiroroku.tarotcards;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import shiroroku.tarotcards.Container.TarotDeckScreen;

@Mod.EventBusSubscriber(modid = TarotCards.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupClient {

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
		event.enqueueWork(() -> MenuScreens.register(TarotCards.tarot_deck.get(), TarotDeckScreen::new));
	}

}


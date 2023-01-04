package shiroroku.tarotcards;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.simple.SimpleChannel;

@Mod.EventBusSubscriber(modid = TarotCards.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupCommon {

	@SubscribeEvent
	public static void commonSetup(final FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(Events.class);
	}

}
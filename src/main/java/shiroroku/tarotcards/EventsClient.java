package shiroroku.tarotcards;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.level.ItemLike;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;
import shiroroku.tarotcards.Item.TarotDeck.TarotDeckScreen;
import shiroroku.tarotcards.Registry.ItemRegistry;

import java.awt.*;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = TarotCards.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventsClient {

    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(TarotCards.tarot_deck_menu.get(), TarotDeckScreen::new));
    }

    @SubscribeEvent
    public static void onItemColorHandler(RegisterColorHandlersEvent.Item event) {
        event.register((stack, tintIndex) -> {
            if (tintIndex == 0 && stack.getOrCreateTag().getBoolean("deactivated")) {
                float b = 0.3f;
                return new Color(b, b, b).getRGB();
            }
            return 16777215;
        }, ItemRegistry.ITEMS_CARDS.getEntries().stream().map(Supplier::get).toArray(ItemLike[]::new));
    }
}


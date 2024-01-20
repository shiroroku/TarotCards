package shiroroku.tarotcards;

import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
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


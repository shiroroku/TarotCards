package shiroroku.tarotcards;

import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import shiroroku.tarotcards.Item.TarotDeckItem;
import shiroroku.tarotcards.Registry.ItemRegistry;

@Mod.EventBusSubscriber(modid = TarotCards.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupCommon {

    @SubscribeEvent
    private static void registerCapability(RegisterCapabilitiesEvent pEvent) {
        pEvent.registerItem(Capabilities.ItemHandler.ITEM, (stack, ctx) -> new TarotDeckItem.TarotDeckInventory(stack), ItemRegistry.tarot_deck.get());
    }

}
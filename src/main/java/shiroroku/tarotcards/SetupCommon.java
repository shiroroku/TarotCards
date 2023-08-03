package shiroroku.tarotcards;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import shiroroku.tarotcards.World.LootModifier;

@Mod.EventBusSubscriber(modid = TarotCards.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupCommon {

	@SubscribeEvent
	public static void registerModifierSerializers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
		event.getRegistry().register(new LootModifier.Serializer().setRegistryName(new ResourceLocation(TarotCards.MODID, "loot_additions")));
	}
}
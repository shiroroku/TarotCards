package shiroroku.tarotcards;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.simple.SimpleChannel;
import shiroroku.tarotcards.World.LootModifier;

@Mod.EventBusSubscriber(modid = TarotCards.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SetupCommon {

	public static SimpleChannel CHANNEL;

	@SubscribeEvent
	public static void commonSetup(final FMLCommonSetupEvent event) {
		MinecraftForge.EVENT_BUS.register(Events.class);
	}

	@SubscribeEvent
	public static void registerModifierSerializers(RegistryEvent.Register<GlobalLootModifierSerializer<?>> event) {
		event.getRegistry().register(new LootModifier.Serializer().setRegistryName(new ResourceLocation(TarotCards.MODID, "loot_additions")));
	}

}
package shiroroku.tarotcards;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shiroroku.tarotcards.Registry.ContainerRegistry;
import shiroroku.tarotcards.Registry.ItemRegistry;

@Mod(TarotCards.MODID)
public class TarotCards {

	public static final String MODID = "tarotcards";
	public static final Logger LOGGER = LogManager.getLogger();

	public TarotCards() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ItemRegistry.ITEMS.register(bus);
		ContainerRegistry.CONTAINERS.register(bus);

	}

	public static final CreativeModeTab CREATIVETAB = new CreativeModeTab(MODID) {
		@Override
		public ItemStack makeIcon() {
			return new ItemStack(ItemRegistry.tarot_deck.get());
		}
	};

}

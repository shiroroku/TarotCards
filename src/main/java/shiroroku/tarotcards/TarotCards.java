package shiroroku.tarotcards;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shiroroku.tarotcards.Item.TarotDeck.TarotDeckContainer;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.World.TarotLootAdditions;

import java.util.function.Supplier;

@Mod(TarotCards.MODID)
public class TarotCards {

    public static final String MODID = "tarotcards";
    public static final Logger LOGGER = LogManager.getLogger();

    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, TarotCards.MODID);
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TarotCards.MODID);

	private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TarotCards.MODID);
	public static final RegistryObject<MenuType<TarotDeckContainer>> tarot_deck_menu = MENUS.register("tarot_deck", () -> IForgeMenuType.create((windowId, inv, data) -> new TarotDeckContainer(windowId, inv, inv.player)));


	public TarotCards() {
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.config);
		ItemRegistry.ITEMS.register(bus);
        ItemRegistry.ITEMS_CARDS.register(bus);
		MENUS.register(bus);
        LOOT_MODIFIERS.register("loot_additions", TarotLootAdditions.CODEC);
		LOOT_MODIFIERS.register(bus);
        TABS.register("tarot_cards", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.tarotcards"))
                .icon(() -> new ItemStack(ItemRegistry.tarot_deck.get()))
                .displayItems((enabledFeatures, entries) -> {
                    ItemRegistry.ITEMS.getEntries().stream().map(Supplier::get).forEach(entries::accept);
                    ItemRegistry.ITEMS_CARDS.getEntries().stream().map(Supplier::get).forEach(entries::accept);
                })
                .build());
		TABS.register(bus);
	}
}

package shiroroku.tarotcards;

import com.mojang.serialization.Codec;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import shiroroku.tarotcards.Container.TarotDeckContainer;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.World.TarotLootAdditions;

import java.util.function.Supplier;

@Mod(TarotCards.MODID)
public class TarotCards {

    public static final String MODID = "tarotcards";
    public static final Logger LOGGER = LogManager.getLogger();

    private static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, TarotCards.MODID);
    private static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TarotCards.MODID);

    private static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, TarotCards.MODID);
    public static final Supplier<MenuType<TarotDeckContainer>> tarot_deck_menu = MENUS.register("tarot_deck", () -> IMenuTypeExtension.create((windowId, inv, data) -> new TarotDeckContainer(windowId, inv, inv.player)));


    public TarotCards(IEventBus bus) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Configuration.config);
        ItemRegistry.ITEMS.register(bus);
        MENUS.register(bus);
        LOOT_MODIFIERS.register("loot_additions", TarotLootAdditions.CODEC);
        LOOT_MODIFIERS.register(bus);
        TABS.register("tarot_cards", () -> CreativeModeTab.builder()
                .title(Component.translatable("itemGroup.tarotcards"))
                .icon(() -> new ItemStack(ItemRegistry.tarot_deck.get()))
                .displayItems((enabledFeatures, entries) -> ItemRegistry.ITEMS.getEntries().stream().map(Supplier::get).forEach(entries::accept))
                .build());
        TABS.register(bus);
    }
}

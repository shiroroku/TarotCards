package shiroroku.tarotcards.Registry;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import shiroroku.tarotcards.Container.TarotDeckContainer;
import shiroroku.tarotcards.TarotCards;

public class ContainerRegistry {

	public static final DeferredRegister<MenuType<?>> CONTAINERS = DeferredRegister.create(ForgeRegistries.CONTAINERS, TarotCards.MODID);

	public static final RegistryObject<MenuType<TarotDeckContainer>> tarot_deck = CONTAINERS.register("tarot_deck", () -> IForgeMenuType.create((windowId, inv, data) -> new TarotDeckContainer(windowId, inv, inv.player)));

}

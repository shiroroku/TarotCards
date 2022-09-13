package shiroroku.tarotcards;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import shiroroku.tarotcards.Registry.ItemRegistry;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.SlotTypePreset;

@Mod.EventBusSubscriber(modid = TarotCards.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class CuriosCompat {

	@SubscribeEvent
	public static void enqueueIMC(InterModEnqueueEvent evt) {
		InterModComms.sendTo("curios", SlotTypeMessage.REGISTER_TYPE, () -> SlotTypePreset.CURIO.getMessageBuilder().size(1).build());
	}

	public static ItemStack getTarotDeckCurio(Player player) {
		if (CuriosApi.getCuriosHelper().findFirstCurio(player, ItemRegistry.tarot_deck.get()).isPresent()) {
			return CuriosApi.getCuriosHelper().findFirstCurio(player, ItemRegistry.tarot_deck.get()).get().stack();
		}
		return null;
	}
}

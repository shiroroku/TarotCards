package shiroroku.tarotcards.Item.Tarot;

import com.google.common.collect.ImmutableList;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.items.CapabilityItemHandler;
import shiroroku.tarotcards.CuriosCompat;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class TarotItem extends Item {
	public TarotItem() {
		super(new Properties().tab(TarotCards.CREATIVETAB).rarity(Rarity.UNCOMMON).stacksTo(1).fireResistant());
	}

	public boolean isFoil(ItemStack stack) {
		return true;
	}

	public static boolean hasTarot(Player player, Item tarot) {
		if (player == null) {
			return false;
		}
		Inventory pInv = player.getInventory();
		if (pInv.contains(new ItemStack(ItemRegistry.tarot_deck.get()))) {
			ItemStack deck = null;
			final List<NonNullList<ItemStack>> compartments = ImmutableList.of(pInv.items, pInv.armor, pInv.offhand);

			if (ModList.get().isLoaded("curios")) {
				deck = CuriosCompat.getTarotDeckCurio(player);
			}

			if (deck == null) {
				foundDeck:
				for (List<ItemStack> list : compartments) {
					for (ItemStack itemstack : list) {
						if (itemstack.getItem() == ItemRegistry.tarot_deck.get()) {
							deck = itemstack;
							break foundDeck;
						}
					}
				}
			}

			if (deck != null) {
				AtomicBoolean foundindeck = new AtomicBoolean(false);
				deck.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
					for (int i = 0; i < handler.getSlots(); i++) {
						if (handler.getStackInSlot(i).is(tarot)) {
							foundindeck.set(true);
							break;
						}
					}
				});

				if (foundindeck.get()) {
					return true;
				}
			}
		}
		return (pInv.contains(new ItemStack(tarot)));
	}

	public static void handleAttribute(Player player, Attribute a, AttributeModifier mod, Item tarot) {
		boolean hasCard = hasTarot(player, tarot);
		if (player.getAttribute(a).hasModifier(mod)) {
			if (!hasCard) {
				TarotCards.LOGGER.debug("Removing Tarot Modifier : {}", mod);
				player.getAttribute(a).removeModifier(mod);
			}
		} else {
			if (hasCard) {
				TarotCards.LOGGER.debug("Adding Tarot Modifier : {}", mod);
				player.getAttribute(a).addTransientModifier(mod);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.BLUE));
	}
}

package shiroroku.tarotcards.Item;

import com.google.common.collect.ImmutableList;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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
import java.util.concurrent.atomic.AtomicReference;

public abstract class TarotItem extends Item {
	public TarotItem() {
		super(new Properties().tab(TarotCards.CREATIVETAB).rarity(Rarity.UNCOMMON).stacksTo(1));
	}

	public boolean isFoil(ItemStack stack) {
		return isActivated(stack);
	}

	/**
	 * If the Tarot Card is active (Using toggles Tarot Card)
	 */
	public static boolean isActivated(ItemStack tarot) {
		return !tarot.getOrCreateTag().getBoolean("deactivated");
	}

	public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
		if (pPlayer.getItemInHand(pUsedHand).getItem() instanceof TarotItem) {
			ItemStack tarot = pPlayer.getItemInHand(pUsedHand);
			boolean deactivated = tarot.getOrCreateTag().getBoolean("deactivated");
			tarot.getOrCreateTag().putBoolean("deactivated", !deactivated);
		}

		return super.use(pLevel, pPlayer, pUsedHand);
	}

	/**
	 * If the specified player has the given tarot on them or in their Tarot Deck.
	 */
	public static boolean hasTarot(Player player, Item tarot) {
		if (player == null) {
			return false;
		}

		ItemStack deck = null;

		Inventory pInv = player.getInventory();
		final List<NonNullList<ItemStack>> fullInv = ImmutableList.of(pInv.items, pInv.armor, pInv.offhand);

		//Check curios for tarot deck
		if (ModList.get().isLoaded("curios")) {
			deck = CuriosCompat.getTarotDeckCurio(player);
		}

		//Check player for card
		for (List<ItemStack> compartment : fullInv) {
			for (ItemStack stack : compartment) {
				if (stack.is(tarot)) {
					return isActivated(stack);
				}
			}
		}

		//Check player for tarot deck
		if (deck == null && pInv.contains(new ItemStack(ItemRegistry.tarot_deck.get()))) {
			foundDeck:
			for (List<ItemStack> compartment : fullInv) {
				for (ItemStack stack : compartment) {
					if (stack.getItem() == ItemRegistry.tarot_deck.get()) {
						deck = stack;
						break foundDeck;
					}
				}
			}
		}

		//Check deck for card
		if (deck != null) {
			AtomicReference<ItemStack> finalCard = new AtomicReference<>(null);
			deck.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).ifPresent(handler -> {
				for (int i = 0; i < handler.getSlots(); i++) {
					if (handler.getStackInSlot(i).is(tarot)) {
						finalCard.set(handler.getStackInSlot(i).copy());
						break;
					}
				}
			});

			return finalCard.get() != null && isActivated(finalCard.get());
		}

		return false;
	}

	/**
	 * Will add or remove attribute modifiers if the player has the tarot.
	 */
	public static void handleAttribute(Player player, Attribute a, AttributeModifier mod, Item tarot) {
		boolean hasCard = hasTarot(player, tarot);
		if (player.getAttribute(a).hasModifier(mod)) {
			if (!hasCard) {
				TarotCards.LOGGER.debug("Removing Tarot Modifier : {} - {}", tarot, mod);
				player.getAttribute(a).removeModifier(mod);
			}
		} else {
			if (hasCard) {
				TarotCards.LOGGER.debug("Adding Tarot Modifier : {} - {}", tarot, mod);
				player.getAttribute(a).addTransientModifier(mod);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.BLUE));
	}

}

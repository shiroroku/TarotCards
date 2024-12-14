package shiroroku.tarotcards.Item;

import com.google.common.collect.ImmutableList;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.fml.ModList;
import shiroroku.tarotcards.CuriosCompat;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import javax.annotation.Nullable;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public abstract class TarotItem extends Item {

    public TarotItem() {
        super(new Properties().rarity(Rarity.UNCOMMON).stacksTo(1));
    }

    @Override
    public boolean isFoil(ItemStack stack) {
        return isActivated(stack);
    }

    /**
     * If the Tarot Card is active (Using toggles Tarot Card)
     */
    public static boolean isActivated(ItemStack tarot) {
        return !tarot.getOrCreateTag().getBoolean("deactivated");
    }

    /**
     * Toggles Tarot Card on use.
     */
    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack tarot = pPlayer.getItemInHand(pUsedHand);
        tarot.getOrCreateTag().putBoolean("deactivated", !tarot.getOrCreateTag().getBoolean("deactivated"));
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

        //Check curios
        if (ModList.get().isLoaded("curios")) {
            // If they have the card in curio slot
            ItemStack singlecard = CuriosCompat.getTarotCardCurio(player, tarot);
            if (singlecard != null && isActivated(singlecard)) {
                return true;
            }
            // If they have the deck in a curio slot, save it for checking later
            deck = CuriosCompat.getTarotDeckCurio(player);
        }

        //Check player for card and deck
        for (List<ItemStack> compartment : fullInv) {
            for (ItemStack stack : compartment) {
                //If we find the card, return it
                //if we find the deck, remember it
                if (stack.is(tarot)) {
                    return isActivated(stack);
                }
                if (stack.getItem() == ItemRegistry.tarot_deck.get()) { // This will choose player inventory decks over curios?
                    deck = stack;
                }
            }
        }

        //If we didnt find the deck either, return false
        if (deck == null) {
            return false;
        }

        //Check deck for card
        AtomicReference<ItemStack> finalCard = new AtomicReference<>(null);
        deck.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
            for (int i = 0; i < handler.getSlots(); i++) {
                if (handler.getStackInSlot(i).is(tarot)) {
                    finalCard.set(handler.getStackInSlot(i).copy());
                    break;
                }
            }
        });

        return finalCard.get() != null && isActivated(finalCard.get());
    }

    /**
     * Will add or remove attribute modifiers if the player has the tarot.
     */
    public static void handleAttributeTick(Player player, Attribute a, AttributeModifier mod, Item tarot) {
        handleAttributeTick(player, a, mod, tarot, () -> true);
    }

    public static void handleAttributeTick(Player player, Attribute a, AttributeModifier mod, Item tarot, Supplier<Boolean> additionalRequirements) {
        boolean hasCard = hasTarot(player, tarot) && additionalRequirements.get();
        if (player.getAttribute(a).hasModifier(mod)) {
            if (!hasCard) {
                TarotCards.LOGGER.debug("Removing Tarot Modifier: {} - {}", tarot, mod);
                player.getAttribute(a).removeModifier(mod.getId());
            }
        } else {
            if (hasCard) {
                TarotCards.LOGGER.debug("Adding Tarot Modifier: {} - {}", tarot, mod);
                player.getAttribute(a).addTransientModifier(mod);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.BLUE));
    }

}

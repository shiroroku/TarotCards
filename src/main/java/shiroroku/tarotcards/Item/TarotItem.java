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
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.items.ItemStackHandler;
import shiroroku.tarotcards.CuriosCompat;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import javax.annotation.Nullable;
import java.util.List;

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

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        ItemStack tarot = pPlayer.getItemInHand(pUsedHand);
        //Toggles activation
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

        //Check curios for tarot deck
        if (ModList.get().isLoaded("curios")) {
            //todo curios compat
            //deck = CuriosCompat.getTarotDeckCurio(player);
        }

        //Check player for card and deck
        for (List<ItemStack> compartment : fullInv) {
            for (ItemStack stack : compartment) {
                //If we find the card, return it
                //if we find the deck, remember it
                if (stack.is(tarot)) {
                    return isActivated(stack);
                }
                if (stack.getItem() == ItemRegistry.tarot_deck.get()) {
                    deck = stack;
                }
            }
        }

        //If we didnt find the deck either, return false
        if (deck == null) {
            return false;
        }

        //Check deck for card
        ItemStack finalCard = null;
        if (deck.getCapability(Capabilities.ItemHandler.ITEM) instanceof ItemStackHandler handler) {
            for (int i = 0; i < handler.getSlots(); i++) {
                if (handler.getStackInSlot(i).is(tarot)) {
                    finalCard = handler.getStackInSlot(i).copy();
                    break;
                }
            }
        }

        return finalCard != null && isActivated(finalCard);
    }

    /**
     * Will add or remove attribute modifiers if the player has the tarot.
     */
    public static void handleAttribute(Player player, Attribute a, AttributeModifier mod, Item tarot) {
        boolean hasCard = hasTarot(player, tarot);
        if (player.getAttribute(a).hasModifier(mod)) {
            if (!hasCard) {
                TarotCards.LOGGER.debug("Removing Tarot Modifier : {} - {}", tarot, mod);
                player.getAttribute(a).removeModifier(mod.getId());
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
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.BLUE));
    }

}

package shiroroku.tarotcards.Item.TarotDeck;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.items.ItemStackHandler;
import org.jetbrains.annotations.Nullable;
import shiroroku.tarotcards.TarotCards;

import java.util.List;

public class TarotDeckItem extends Item implements MenuProvider {

    private static final TagKey<Item> tarot = ItemTags.create(new ResourceLocation(TarotCards.MODID, "tarot_cards"));

    public TarotDeckItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant());
    }

    public static class TarotDeckInventory extends ItemStackHandler {

        private final ItemStack deck;

        public TarotDeckInventory(ItemStack parent) {
            super(22);
            this.deck = parent;
            deserializeNBT(parent.getOrCreateTagElement("held_cards"));
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return stack.getTags().anyMatch(t -> (t == tarot)) && this.stacks.stream().noneMatch(s -> (s.is(stack.getItem())));
        }

        @Override
        protected void onContentsChanged(int slot) {
            super.onContentsChanged(slot);
            deck.getOrCreateTag().put("held_cards", serializeNBT());
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            serverPlayer.openMenu(this);
        }
        return super.use(world, player, hand);
    }

    @Override
    public Component getDisplayName() {
        return this.getDescription();
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
        return new TarotDeckContainer(id, playerInventory, player);
    }

    @Override
    public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
    }
}

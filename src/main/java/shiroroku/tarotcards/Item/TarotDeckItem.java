package shiroroku.tarotcards.Item;

import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
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
import net.neoforged.neoforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import shiroroku.tarotcards.Container.TarotDeckContainer;
import shiroroku.tarotcards.TarotCards;

import java.util.List;

public class TarotDeckItem extends Item implements MenuProvider {

    private static final TagKey<Item> tarot = ItemTags.create(new ResourceLocation(TarotCards.MODID, "tarot_cards"));

    public TarotDeckItem() {
        super(new Item.Properties().stacksTo(1).rarity(Rarity.UNCOMMON).fireResistant());
    }

    public static class ItemHandler extends ItemStackHandler {

        private final ItemStack parent;

        public ItemHandler(ItemStack parent) {
            super(22);
            this.parent = parent;
        }

        private boolean canPut(ItemStack stack) {
            return stack.getTags().anyMatch(t -> (t == tarot)) && this.stacks.stream().noneMatch(s -> (s.is(stack.getItem())));
        }

        @Override
        public boolean isItemValid(int slot, ItemStack stack) {
            return canPut(stack);
        }

        @Override
        public ItemStack insertItem(int slot, ItemStack stack, boolean simulate) {
            if (!canPut(stack)) {
                return stack;
            }
            return super.insertItem(slot, stack, simulate);
        }

        //Todo test deck item handler

        @Override
        public CompoundTag serializeNBT() {
            ListTag nbtTagList = new ListTag();
            for (int i = 0; i < stacks.size(); i++) {
                if (!stacks.get(i).isEmpty()) {
                    CompoundTag itemTag = new CompoundTag();
                    itemTag.putInt("Slot", i);
                    stacks.get(i).save(itemTag);
                    nbtTagList.add(itemTag);
                }
            }
            CompoundTag nbt = new CompoundTag();
            nbt.put("Items", nbtTagList);
            nbt.putInt("Size", stacks.size());
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundTag nbt) {
            setSize(nbt.contains("Size", Tag.TAG_INT) ? nbt.getInt("Size") : stacks.size());
            ListTag tagList = nbt.getList("Items", Tag.TAG_COMPOUND);
            for (int i = 0; i < tagList.size(); i++) {
                CompoundTag itemTags = tagList.getCompound(i);
                int slot = itemTags.getInt("Slot");

                if (slot >= 0 && slot < stacks.size()) {
                    stacks.set(slot, ItemStack.of(itemTags));
                }
            }
            onLoad();
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
        if (player instanceof ServerPlayer serverPlayer) {
            NetworkHooks.openScreen(serverPlayer, this);
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

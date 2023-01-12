package shiroroku.tarotcards.Container;

import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;
import net.minecraftforge.items.wrapper.InvWrapper;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

public class TarotDeckContainer extends AbstractContainerMenu {

	ItemStack deck;

	public TarotDeckContainer(int id, Inventory playerInventory, Player player) {
		super(TarotCards.tarot_deck.get(), id);
		IItemHandler playerSlots = new InvWrapper(playerInventory);
		deck = player.getMainHandItem().is(ItemRegistry.tarot_deck.get()) ? player.getMainHandItem() : player.getOffhandItem();

		//Add deck slots
		if (deck != null) {
			deck.getCapability(ForgeCapabilities.ITEM_HANDLER).ifPresent(handler -> {
				int slotIndex = 0;
				int invX = 10;
				int invY = 25;
				for (int y = 0; y < 2; y++) {
					for (int x = 0; x < 11; x++) {
						addSlot(new SlotItemHandler(handler, slotIndex, invX + 14 * x, invY + 18 * y));
						slotIndex++;
					}
				}
			});
		}

		//Add player slots
		int slotIndex = 0;
		int invX = 8;
		int invY = 142;
		for (int x = 0; x < 9; x++) {
			addSlot(new SlotItemHandler(playerSlots, slotIndex, invX + 18 * x, invY));
			slotIndex++;
		}
		invY -= 58;
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 9; x++) {
				addSlot(new SlotItemHandler(playerSlots, slotIndex, invX + 18 * x, invY + 18 * y));
				slotIndex++;
			}

		}
	}

	@Override
	public ItemStack quickMoveStack(Player playerIn, int index) {
		ItemStack itemstack = ItemStack.EMPTY;
		Slot slot = this.slots.get(index);
		if (slot != null && slot.hasItem()) {
			ItemStack slotItem = slot.getItem();
			itemstack = slotItem.copy();
			if (index < 22) {
				if (!this.moveItemStackTo(slotItem, 22, this.slots.size(), true)) {
					return ItemStack.EMPTY;
				}
			} else {
				if (!this.moveItemStackTo(slotItem, 0, 22, false)) {
					return ItemStack.EMPTY;
				}
			}
			if (slotItem.isEmpty()) {
				slot.set(ItemStack.EMPTY);
			} else {
				slot.setChanged();
			}
		}
		return itemstack;
	}

	@Override
	public boolean stillValid(Player player) {
		return !player.isRemoved() && (player.getMainHandItem() == deck || player.getOffhandItem() == deck);
	}

}

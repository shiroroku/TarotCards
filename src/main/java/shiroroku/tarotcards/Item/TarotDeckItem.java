package shiroroku.tarotcards.Item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
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
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;
import shiroroku.tarotcards.Container.TarotDeckContainer;
import shiroroku.tarotcards.TarotCards;

import javax.annotation.Nonnull;
import java.util.List;

public class TarotDeckItem extends Item implements MenuProvider {
	private static final TagKey<Item> tarot = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(TarotCards.MODID, "tarot_cards"));

	public TarotDeckItem() {
		super(new Item.Properties().tab(TarotCards.CREATIVETAB).stacksTo(1).rarity(Rarity.UNCOMMON));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		if (player instanceof ServerPlayer serverPlayer) {
			NetworkHooks.openGui(serverPlayer, this);

		}
		return super.use(world, player, hand);
	}

	@Override
	public Component getDisplayName() {
		return this.getDescription();
	}

	public ICapabilityProvider initCapabilities(ItemStack stack, @javax.annotation.Nullable CompoundTag nbt) {
		return new ICapabilitySerializable<CompoundTag>() {
			private final ItemStackHandler itemHandler = createHandler();
			private final LazyOptional<IItemHandler> handler = LazyOptional.of(() -> itemHandler);

			@Nonnull
			@Override
			public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
				return CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.orEmpty(cap, handler);
			}

			@Override
			public CompoundTag serializeNBT() {
				return itemHandler.serializeNBT();
			}

			@Override
			public void deserializeNBT(CompoundTag nbt) {

				this.itemHandler.deserializeNBT(nbt);
			}
		};
	}

	private ItemStackHandler createHandler() {
		return new ItemStackHandler(22) {

			@Override
			public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
				return canPut(stack);
			}

			private boolean canPut(ItemStack stack) {
				return stack.getTags().anyMatch(t -> (t == tarot)) && this.stacks.stream().noneMatch(s -> (s.is(stack.getItem())));
			}

			@Nonnull
			@Override
			public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
				if (!canPut(stack)) {
					return stack;
				}
				return super.insertItem(slot, stack, simulate);
			}
		};
	}

	@Nullable
	@Override
	public AbstractContainerMenu createMenu(int id, Inventory playerInventory, Player player) {
		return new TarotDeckContainer(id, playerInventory, player);
	}

	@Override
	public void appendHoverText(ItemStack stack, @javax.annotation.Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc").withStyle(ChatFormatting.GRAY));
	}
}

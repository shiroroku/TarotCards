package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import shiroroku.tarotcards.Registry.ItemRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class TheChariotTarot extends TarotItem {

	private static final float boost = 0.5f;
	private static final AttributeModifier speedBoost = new AttributeModifier(UUID.nameUUIDFromBytes("TarotChariot".getBytes()).toString(), boost, AttributeModifier.Operation.MULTIPLY_BASE);

	public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.player.tickCount % 20 == 0 && event.side == LogicalSide.SERVER) {
			handleAttribute(event.player, Attributes.MOVEMENT_SPEED, speedBoost, ItemRegistry.the_chariot.get());
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc", boost * 100).withStyle(ChatFormatting.BLUE));
	}
}

package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class TheChariotTarot extends TarotItem {

	private static final String uuid = UUID.nameUUIDFromBytes("TarotChariot".getBytes()).toString();
	private static AttributeModifier speedBoost = null;

	public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
		if (speedBoost == null) {
			speedBoost = new AttributeModifier(uuid, Configuration.the_chariot_speedboost.get(), AttributeModifier.Operation.MULTIPLY_BASE);
		}
		handleAttribute(event.player, Attributes.MOVEMENT_SPEED, speedBoost, ItemRegistry.the_chariot.get());
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", Configuration.the_chariot_speedboost.get() * 100).withStyle(ChatFormatting.BLUE));
	}
}

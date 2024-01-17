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

public class WheelOfFortuneTarot extends TarotItem {

	private static final String uuid = UUID.nameUUIDFromBytes("TarotWheelOfFortune".getBytes()).toString();
	private static AttributeModifier luckBoost = null;

	public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
		if (luckBoost == null) {
			luckBoost = new AttributeModifier(uuid, Configuration.wheel_of_fortune_luckbonus.get(), AttributeModifier.Operation.ADDITION);
		}
		handleAttribute(event.player, Attributes.LUCK, luckBoost, ItemRegistry.wheel_of_fortune.get());
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", Configuration.wheel_of_fortune_luckbonus.get()).withStyle(ChatFormatting.BLUE));
	}
}

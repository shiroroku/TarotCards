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
import java.util.function.Supplier;

public class TheSunTarot extends TarotItem {

	private static final Supplier<AttributeModifier> healthBoost = ()-> new AttributeModifier(UUID.nameUUIDFromBytes("TarotSun".getBytes()), "Tarot Card", Configuration.the_sun_healthboost.get(), AttributeModifier.Operation.MULTIPLY_BASE);

	public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
		handleAttribute(event.player, Attributes.MAX_HEALTH, healthBoost.get(), ItemRegistry.the_sun.get());
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", Configuration.the_sun_healthboost.get() * 100).withStyle(ChatFormatting.BLUE));
	}
}

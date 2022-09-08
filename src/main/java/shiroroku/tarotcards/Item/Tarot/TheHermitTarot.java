package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import shiroroku.tarotcards.TarotCards;
import shiroroku.tarotcards.Registry.ItemRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class TheHermitTarot extends TarotItem {

	public static final float armor = 10f;
	public static final float range = 10f;
	private static final AttributeModifier armorBoost = new AttributeModifier(UUID.nameUUIDFromBytes("TarotHermit".getBytes()).toString(), armor, AttributeModifier.Operation.ADDITION);

	public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.player.tickCount % 20 == 0 && event.side == LogicalSide.SERVER) {
			handleAttribute(event.player, Attributes.ARMOR, armorBoost, ItemRegistry.the_hermit.get());
		}
	}

	public static void handleAttribute(Player player, Attribute a, AttributeModifier mod, Item tarot) {
		boolean hasCard = hasTarot(player, tarot) && !hasNearbyAllies(player);
		if (player.getAttribute(a).hasModifier(mod)) {
			if (!hasCard) {
				TarotCards.LOGGER.debug("Removing Tarot Modifier : {}", mod);
				player.getAttribute(a).removeModifier(mod);
			}
		} else {
			if (hasCard) {
				TarotCards.LOGGER.debug("Adding Tarot Modifier : {}", mod);
				player.getAttribute(a).addTransientModifier(mod);
			}
		}
	}

	private static boolean hasNearbyAllies(Player player) {
		List<LivingEntity> entities = player.level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, player, player.getBoundingBox().inflate(TheHermitTarot.range));
		for (LivingEntity e : entities) {
			if (e.isAlliedTo(player)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc", armor).withStyle(ChatFormatting.BLUE));
	}

}

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
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class TheHermitTarot extends TarotItem {

    private static final String uuid = UUID.nameUUIDFromBytes("TarotHermit".getBytes()).toString();
    private static AttributeModifier armorBoost = null;

    public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
        if (armorBoost == null) {
            armorBoost = new AttributeModifier(uuid, Configuration.the_hermit_armorbonus.get(), AttributeModifier.Operation.ADDITION);
        }
        handleAttribute(event.player, Attributes.ARMOR, armorBoost, ItemRegistry.the_hermit.get());
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
        List<LivingEntity> entities = player.level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, player, player.getBoundingBox().inflate(Configuration.the_hermit_allyrange.get()));
        for (LivingEntity e : entities) {
            if (e.isAlliedTo(player)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc", Configuration.the_hermit_armorbonus.get()).withStyle(ChatFormatting.BLUE));
    }

}

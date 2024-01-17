package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.neoforged.neoforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;

public class TheStarTarot extends TarotItem {

    private static final String uuid = UUID.nameUUIDFromBytes("TarotStar".getBytes()).toString();
    private static AttributeModifier reachBoost = null;

    public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
        if (reachBoost == null) {
            reachBoost = new AttributeModifier(uuid, Configuration.the_star_reachboost.get(), AttributeModifier.Operation.MULTIPLY_BASE);
        }
        handleAttribute(event.player, NeoForgeMod.BLOCK_REACH.value(), reachBoost, ItemRegistry.the_star.get());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", Configuration.the_star_reachboost.get() * 100).withStyle(ChatFormatting.BLUE));
    }

}

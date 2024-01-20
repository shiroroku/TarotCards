package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import javax.annotation.Nullable;
import java.util.List;

public class TheTowerTarot extends TarotItem {

    public static void handleOnHurt(LivingHurtEvent event) {
        if (event.getSource().isFall() && event.getEntity() instanceof Player player) {
            if (hasTarot(player, ItemRegistry.the_tower.get())) {
                float negation = (float) (event.getAmount() * (1 - Configuration.the_tower_damagenegation.get()));
                TarotCards.LOGGER.debug("{} - Fall negation", ItemRegistry.the_tower.get());
                TarotCards.LOGGER.debug("Negation: {}, For: {}", negation, player);
                event.setAmount(negation);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", String.valueOf(Configuration.the_tower_damagenegation.get() * 100)).withStyle(ChatFormatting.BLUE));
    }
}

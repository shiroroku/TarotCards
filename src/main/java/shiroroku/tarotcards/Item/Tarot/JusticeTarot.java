package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
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

public class JusticeTarot extends TarotItem {

    private static final DamageSource justice = new DamageSource("justice").bypassArmor();

	public static void handleOnHurt(LivingHurtEvent event) {
		//Make sure it is a living entity hurting a player
		if (event.getSource().getEntity() instanceof LivingEntity attacker && event.getEntity() instanceof Player player) {

			//Damage taken from justice shouldnt be returned
			if (event.getSource().getMsgId().equals(justice.getMsgId())) {
				return;
			}

			if (hasTarot(player, ItemRegistry.justice.get())) {
				float amount = (float) (event.getAmount() * Configuration.justice_damagemultiplier.get());
				attacker.hurt(justice, amount);

                TarotCards.LOGGER.debug("{} - Returning damage", ItemRegistry.justice.get());
                TarotCards.LOGGER.debug("From: {}, To: {} [{}]", player, attacker, attacker.getHealth());
                TarotCards.LOGGER.debug("Amount: {}", amount);
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc", String.valueOf(Configuration.justice_damagemultiplier.get() * 100)).withStyle(ChatFormatting.BLUE));
    }
}

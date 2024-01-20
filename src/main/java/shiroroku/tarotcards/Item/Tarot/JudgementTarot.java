package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import javax.annotation.Nullable;
import java.util.List;

public class JudgementTarot extends TarotItem {

    public static void handleOnDamage(LivingDamageEvent event) {
        if (event.getSource().getEntity() instanceof Player player) {
            if (hasTarot(player, ItemRegistry.judgement.get())) {
                if (player.getRandom().nextDouble() < Configuration.judgement_damagechance.get()) {

                    float new_damage = event.getAmount() * 2;

                    TarotCards.LOGGER.debug("{} - Chance to double damage", ItemRegistry.judgement.get());
                    TarotCards.LOGGER.debug("From: {}, To: {}", player, event.getEntity());
                    TarotCards.LOGGER.debug("Amount: {} to {}", event.getAmount(), new_damage);

                    player.level.playSound(null, player.blockPosition(), SoundEvents.SMALL_AMETHYST_BUD_BREAK, SoundSource.PLAYERS, 1f, player.getRandom().nextFloat() * 0.2f + 0.5f);
                    event.setAmount(new_damage);
                }
            }
        }
    }

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", Configuration.judgement_damagechance.get() * 100).withStyle(ChatFormatting.BLUE));
	}

}

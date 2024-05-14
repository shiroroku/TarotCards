package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.ExperienceOrb;
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

public class TheHangedManTarot extends TarotItem {

    public static void handleOnHurt(LivingHurtEvent event) {
        if (event.getEntity() instanceof Player player) {
            if (hasTarot(player, ItemRegistry.the_hanged_man.get())) {
                int xpamount = (int) Math.min(event.getAmount() * Configuration.the_hanged_man_xpratio.get(), Configuration.the_hanged_man_xpcap.get());
                TarotCards.LOGGER.debug("{} - Damage to xp orb", ItemRegistry.the_hanged_man.get());
                TarotCards.LOGGER.debug("Amount: {}, For: {}", xpamount, player);
                ExperienceOrb orb = new ExperienceOrb(player.level, player.getX(), player.getY(), player.getZ(), xpamount);
                player.level.addFreshEntity(orb);
            }
        }
    }

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc", Configuration.the_hanged_man_xpratio.get() * 100 + "").withStyle(ChatFormatting.BLUE));
	}
}

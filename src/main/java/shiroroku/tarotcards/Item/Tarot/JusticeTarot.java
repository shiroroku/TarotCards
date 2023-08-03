package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageType;
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

	public static ResourceKey<DamageType> JUSTICE = ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation(TarotCards.MODID, "justice"));

	public static void handleOnHurt(LivingHurtEvent event) {
		//Make sure it is a living entity hurting a player
		if (event.getSource().getEntity() instanceof LivingEntity attacker && event.getEntity() instanceof Player player) {

			//Damage taken from justice shouldnt be returned
			if (event.getSource().is(JUSTICE)) {
				return;
			}

			if (hasTarot(player, ItemRegistry.justice.get())) {
				float amount = (float) (event.getAmount() * Configuration.justice_damagemultiplier.get());
				attacker.hurt(event.getEntity().damageSources().source(JUSTICE), amount);

				TarotCards.LOGGER.debug("TAROT PASSIVE: {} - Returning damage", ItemRegistry.justice.get());
				TarotCards.LOGGER.debug("From : {}", player);
				TarotCards.LOGGER.debug("To : {} [{}]", attacker, attacker.getHealth());
				TarotCards.LOGGER.debug("Amount : {}", amount);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", String.valueOf(Configuration.justice_damagemultiplier.get() * 100)).withStyle(ChatFormatting.BLUE));
	}
}

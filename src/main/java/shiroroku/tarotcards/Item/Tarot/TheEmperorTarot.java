package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import shiroroku.tarotcards.Registry.ItemRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class TheEmperorTarot extends TarotItem {

	private static final int amplifier = 2;

	public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.player.tickCount % 20 == 0 && event.side == LogicalSide.SERVER) {
			if (hasTarot(event.player, ItemRegistry.the_emperor.get())) {
				event.player.addEffect(new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 20, amplifier, true, false));

			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(new TranslatableComponent(this.getDescriptionId() + ".desc", amplifier + 1).withStyle(ChatFormatting.BLUE));
	}
}


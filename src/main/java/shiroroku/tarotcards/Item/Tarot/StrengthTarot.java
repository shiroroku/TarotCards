package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class StrengthTarot extends TarotItem {

    private static final Supplier<MobEffectInstance> effect = () -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, Configuration.tick_rate.get() + 20, Configuration.strength_amplifier.get(), true, false);

    public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
        if (hasTarot(event.player, ItemRegistry.strength.get())) {
            event.player.addEffect(effect.get());
        }
    }

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", Configuration.strength_amplifier.get() + 1).withStyle(ChatFormatting.BLUE));
	}
}

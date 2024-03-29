package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class TheLoversTarot extends TarotItem {

    private static final TargetingConditions targeting = TargetingConditions.forNonCombat().ignoreLineOfSight().ignoreInvisibilityTesting();
    private static final Supplier<MobEffectInstance> effect = () -> new MobEffectInstance(MobEffects.REGENERATION, Configuration.tick_rate.get() + 20, Configuration.the_lovers_regenamplifier.get(), true, false, false);

    public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (hasTarot(player, ItemRegistry.the_lovers.get())) {
            player.level().getNearbyEntities(LivingEntity.class, targeting, player, player.getBoundingBox().inflate(Configuration.the_lovers_range.get())).stream()
                    .filter(e -> e.isAlliedTo(player))
                    .forEach(e -> {
                        TarotCards.LOGGER.debug("{} - Add regen", ItemRegistry.the_lovers.get());
                        TarotCards.LOGGER.debug("Ally: {}", e);
                        e.addEffect(effect.get());
                    });
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", Configuration.the_lovers_regenamplifier.get() + 1).withStyle(ChatFormatting.BLUE));
    }

}

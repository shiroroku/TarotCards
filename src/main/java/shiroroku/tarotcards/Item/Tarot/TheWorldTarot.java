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
import net.minecraftforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;

import javax.annotation.Nullable;
import java.util.List;

public class TheWorldTarot extends TarotItem {

    public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (hasTarot(player, ItemRegistry.the_world.get())) {
            MobEffectInstance slow_effect = new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60 + Configuration.tick_rate.get(), Configuration.the_world_slownessamplifier.get(), true, false, false);
            player.level().getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, player, player.getBoundingBox().inflate(Configuration.the_world_range.get())).stream()
                    .filter(e -> !e.isAlliedTo(player))
                    .filter(e -> {
                        // If we cant hurt the player, then filter out
                        if (e instanceof Player ePlayer && !player.canHarmPlayer(ePlayer)) {
                            return false;
                        } else {
                            // If its not a player then continue
                            return true;
                        }
                    })
                    .forEach(e -> e.addEffect(slow_effect));
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", Configuration.the_world_slownessamplifier.get() + 1).withStyle(ChatFormatting.BLUE));
    }

}

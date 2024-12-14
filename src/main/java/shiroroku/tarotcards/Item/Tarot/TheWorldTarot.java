package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.OwnableEntity;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import javax.annotation.Nullable;
import java.util.List;
import java.util.function.Supplier;

public class TheWorldTarot extends TarotItem {

    public static final Supplier<MobEffectInstance> effect = () -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60 + Configuration.tick_rate.get(), Configuration.the_world_slownessamplifier.get(), true, false, false);

    public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
        Player player = event.player;
        if (hasTarot(player, ItemRegistry.the_world.get())) {
            player.level.getNearbyEntities(LivingEntity.class, TargetingConditions.DEFAULT, player, player.getBoundingBox().inflate(Configuration.the_world_range.get())).stream()
                    .filter(e -> !e.isAlliedTo(player))
                    .filter(e -> !(e instanceof OwnableEntity eOwnedEntity) || eOwnedEntity.getOwner() == null)
                    .filter(e -> !(e instanceof Player ePlayer) || player.canHarmPlayer(ePlayer))
                    .forEach(e -> {
                        TarotCards.LOGGER.debug("{} - Slow nearby", ItemRegistry.the_world.get());
                        TarotCards.LOGGER.debug("Entity: {}", e);
                        e.addEffect(effect.get());
                    });
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", Configuration.the_world_slownessamplifier.get() + 1).withStyle(ChatFormatting.BLUE));
    }

}

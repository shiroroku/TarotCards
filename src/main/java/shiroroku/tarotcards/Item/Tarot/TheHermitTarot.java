package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
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
import java.util.UUID;
import java.util.function.Supplier;

public class TheHermitTarot extends TarotItem {

    private static final Supplier<AttributeModifier> attribute = () -> new AttributeModifier(UUID.nameUUIDFromBytes("TarotHermit".getBytes()), "Tarot Card", Configuration.the_hermit_armorbonus.get(), AttributeModifier.Operation.ADDITION);

    public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
        handleAttributeTick(event.player, Attributes.ARMOR, attribute.get(), ItemRegistry.the_hermit.get(), () -> !hasNearbyAllies(event.player));
    }

    private static boolean hasNearbyAllies(Player player) {
        return player.level.getNearbyEntities(LivingEntity.class, TargetingConditions.forNonCombat().ignoreLineOfSight(), player, player.getBoundingBox().inflate(Configuration.the_hermit_allyrange.get())).stream().anyMatch(e -> e.isAlliedTo(player));
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable(this.getDescriptionId() + ".desc", Configuration.the_hermit_armorbonus.get()).withStyle(ChatFormatting.BLUE));
    }

}

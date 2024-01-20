package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import java.util.Map;

public class TheHighPriestessTarot extends TarotItem {

    private static final TagKey<Item> upgradable_enchantment = ItemTags.create(new ResourceLocation(TarotCards.MODID, "upgradable_enchantment"));

    public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
        Player p = event.player;
        if (hasTarot(p, ItemRegistry.the_high_priestess.get())) {

            //Get the held item to upgrade
            ItemStack upgradable_item = p.getMainHandItem().is(upgradable_enchantment) ? p.getMainHandItem() : (p.getOffhandItem().is(upgradable_enchantment) ? p.getOffhandItem() : null);
            if (upgradable_item == null) {
                return;
            }

            //Get the first valid enchantment to level up
            Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(upgradable_item);
            enchantments.entrySet().stream()
                    .filter(ench -> ench.getValue() < ench.getKey().getMaxLevel() || !Configuration.the_highpriestess_capenchants.get())
                    .filter(ench -> p.experienceLevel > Configuration.the_highpriestess_upgradecost.get() * ench.getValue())
                    .findFirst().ifPresent(ench -> {
                        int cost = Configuration.the_highpriestess_upgradecost.get() * ench.getValue();
                        TarotCards.LOGGER.debug("{} - Enchantment upgrade", ItemRegistry.the_high_priestess.get());
                        TarotCards.LOGGER.debug("From: {}, To: {}, Cost: {}", ench, ench.getValue() + 1, cost);
                        p.giveExperienceLevels(-cost);
                        enchantments.put(ench.getKey(), ench.getValue() + 1);
                        EnchantmentHelper.setEnchantments(enchantments, upgradable_item);
                    });
        }
    }

}

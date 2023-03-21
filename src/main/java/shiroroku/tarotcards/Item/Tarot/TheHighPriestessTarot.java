package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.fml.LogicalSide;
import shiroroku.tarotcards.Configuration;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import java.util.Map;

public class TheHighPriestessTarot extends TarotItem {

    private static final TagKey<Item> upgradable_enchantment = TagKey.create(Registry.ITEM_REGISTRY, new ResourceLocation(TarotCards.MODID, "upgradable_enchantment"));

    public static void handleOnPlayerTick(TickEvent.PlayerTickEvent event) {
        Player p = event.player;
        if (p.tickCount % 20 == 0 && event.side == LogicalSide.SERVER && hasTarot(p, ItemRegistry.the_high_priestess.get())) {

            ItemStack upgradable = p.getMainHandItem().is(upgradable_enchantment) ? p.getMainHandItem() : (p.getOffhandItem().is(upgradable_enchantment) ? p.getOffhandItem() : null);

            if (upgradable != null) {
                Map<Enchantment, Integer> enchantments = EnchantmentHelper.getEnchantments(upgradable);
                Map<Enchantment, Integer> upgraded_enchantments = enchantments;
                boolean upgraded = false;
                for (Map.Entry<Enchantment, Integer> enchant : enchantments.entrySet()) {

                    if (!Configuration.the_highpriestess_capenchants.get() || enchant.getValue() < enchant.getKey().getMaxLevel()) {

                        int cost = (int) (Configuration.the_highpriestess_upgradecost.get() * enchant.getValue());

                        if (p.experienceLevel > cost) {
                            TarotCards.LOGGER.debug("TAROT PASSIVE: {} - Enchantment upgrade", ItemRegistry.the_high_priestess.get());
                            TarotCards.LOGGER.debug("From: {} To: {} Cost: {}", enchant, enchant.getValue() + 1, cost);
                            p.giveExperienceLevels(-cost);
                            upgraded_enchantments.put(enchant.getKey(), enchant.getValue() + 1);
                            upgraded = true;
                            break;
                        }
                    }


                }
                if (upgraded) {
                    EnchantmentHelper.setEnchantments(upgraded_enchantments, upgradable);
                }
            }
        }
    }

}

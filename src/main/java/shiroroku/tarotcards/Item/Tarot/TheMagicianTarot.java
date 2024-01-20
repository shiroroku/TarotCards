package shiroroku.tarotcards.Item.Tarot;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

public class TheMagicianTarot extends TarotItem {

	private static final TagKey<Item> goldenItem = ItemTags.create(new ResourceLocation(TarotCards.MODID, "golden"));

    public static boolean handleItemDamage(ItemStack item, Player player) {
        if (hasTarot(player, ItemRegistry.the_magician.get())) {
            if (item.isDamageableItem() && item.getTags().anyMatch(t -> (t.equals(goldenItem)))) {
                TarotCards.LOGGER.debug("{} - Unbreakable gold items", ItemRegistry.the_magician.get());
                TarotCards.LOGGER.debug("For: {}", player);
                return true;
            }
        }
        return false;
    }

}

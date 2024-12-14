package shiroroku.tarotcards;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import shiroroku.tarotcards.Registry.ItemRegistry;
import top.theillusivec4.curios.api.CuriosApi;

public class CuriosCompat {

    @SuppressWarnings({"deprecation", "UnstableApiUsage", "removal"})
    public static ItemStack getTarotDeckCurio(Player player) {
        if (CuriosApi.getCuriosHelper().findFirstCurio(player, ItemRegistry.tarot_deck.get()).isPresent()) {
            return CuriosApi.getCuriosHelper().findFirstCurio(player, ItemRegistry.tarot_deck.get()).get().stack();
        }
        return null;
    }

    @SuppressWarnings({"deprecation", "UnstableApiUsage", "removal"})
    public static ItemStack getTarotCardCurio(Player player, Item tarotItem) {
        if (CuriosApi.getCuriosHelper().findFirstCurio(player, tarotItem).isPresent()) {
            return CuriosApi.getCuriosHelper().findFirstCurio(player, tarotItem).get().stack();
        }
        return null;
    }
}

package shiroroku.tarotcards;

import net.minecraft.world.entity.player.Player;
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
}

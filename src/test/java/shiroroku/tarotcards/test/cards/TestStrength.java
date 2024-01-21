package shiroroku.tarotcards.test.cards;

import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

@PrefixGameTestTemplate(false)
@GameTestHolder(TarotCards.MODID)
public class TestStrength {

    @GameTest(templateNamespace = TarotCards.MODID, template = "empty3x3x3")
    public static void strength(GameTestHelper helper) {
        Player player = helper.makeMockPlayer();
        player.setPos(helper.absoluteVec(new Vec3(1, 1, 1)));
        player.addItem(new ItemStack(ItemRegistry.strength.get()));
        helper.succeedWhen(() -> player.hasEffect(MobEffects.DAMAGE_BOOST));
    }
}

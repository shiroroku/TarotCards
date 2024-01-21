package shiroroku.tarotcards.test.cards;

import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

@PrefixGameTestTemplate(false)
@GameTestHolder(TarotCards.MODID)
public class TestJudgement {

    /**
     * Player should eventually do double damage
     * - Spawns a player and a cow
     * - Player attacks cow and health is recorded
     * - Spawns a new player and a cow
     * - Gives player tarot card
     * - Player attacks cow and health is recorded
     * - Check if the cow has less health than without the card
     * - Repeat 100 times or until effect happens
     */
    @GameTest(templateNamespace = TarotCards.MODID, template = "empty3x3x3")
    public static void judgement(GameTestHelper helper) {
        //attempts doesnt work? so im doing it manually
        for (int i = 0; i < 100; i++) {
            Player player = helper.makeMockPlayer();
            player.setPos(helper.absoluteVec(new Vec3(1, 1, 1)));

            // Without card
            Mob living = helper.spawnWithNoFreeWill(EntityType.COW, 1, 1, 1);
            player.attack(living);
            float living_health_normal = living.getHealth();
            living.remove(Entity.RemovalReason.DISCARDED);

            player.addItem(new ItemStack(ItemRegistry.judgement.get()));

            // With card
            living = helper.spawnWithNoFreeWill(EntityType.COW, 1, 1, 1);
            player.attack(living);
            float living_health_after_card = living.getHealth();
            living.remove(Entity.RemovalReason.DISCARDED);
            player.remove(Entity.RemovalReason.DISCARDED);

            // If double damaged happened then they will have less health
            if (living_health_after_card < living_health_normal) {
                helper.succeed();
                return;
            }
        }
    }
}

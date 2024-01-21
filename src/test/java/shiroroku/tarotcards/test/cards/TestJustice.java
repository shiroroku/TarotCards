package shiroroku.tarotcards.test.cards;

import net.minecraft.gametest.framework.GameTest;
import net.minecraft.gametest.framework.GameTestAssertException;
import net.minecraft.gametest.framework.GameTestHelper;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.gametest.GameTestHolder;
import net.neoforged.neoforge.gametest.PrefixGameTestTemplate;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

@PrefixGameTestTemplate(false)
@GameTestHolder(TarotCards.MODID)
public class TestJustice {

    /**
     * Zombie should be hurt by attacking the player
     * - Spawn player with the tarot card, and a zombie
     * - Zombies health is recorded before it attacks the player, and after
     * - Check that the zombies health changed
     */
    @GameTest(templateNamespace = TarotCards.MODID, template = "empty3x3x3")
    public static void justice(GameTestHelper helper) {
        Player player = helper.makeMockPlayer();
        player.setPos(helper.absoluteVec(new Vec3(1, 1, 1)));
        player.addItem(new ItemStack(ItemRegistry.justice.get()));

        Mob attacker = helper.spawnWithNoFreeWill(EntityType.ZOMBIE, 1, 1, 1);
        float attacker_health_before = attacker.getHealth();
        attacker.doHurtTarget(player);
        float attacker_health_after = attacker.getHealth();
        attacker.remove(Entity.RemovalReason.DISCARDED);
        player.remove(Entity.RemovalReason.DISCARDED);

        // If health didnt change or is more, then damage wasnt returned
        if (attacker_health_after >= attacker_health_before) {
            throw new GameTestAssertException(String.format("damage wasnt returned! (before: %f, after: %f)", attacker_health_before, attacker_health_after));
        }

        helper.succeed();
    }
}

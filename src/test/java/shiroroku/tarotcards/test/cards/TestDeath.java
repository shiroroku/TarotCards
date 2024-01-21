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
import net.minecraftforge.gametest.GameTestHolder;
import net.minecraftforge.gametest.PrefixGameTestTemplate;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

@PrefixGameTestTemplate(false)
@GameTestHolder(TarotCards.MODID)
public class TestDeath {

    /**
     * Cow should take more damage than normal, while Skeleton should take normal damage
     * - Spawns a player with a cow and skeleton
     * - Player attacks both cow and skeleton and health after is recorded
     * - Spawns a new player with a cow and skeleton
     * - Gives player tarot card
     * - Player attacks both cow and skeleton and health after is recorded
     * - Check that the cow health difference is different and check that the skeleton health difference is the same
     */
    @GameTest(templateNamespace = TarotCards.MODID, template = "empty3x3x3")
    public static void death(GameTestHelper helper) {
        Player player = helper.makeMockPlayer();
        player.setPos(helper.absoluteVec(new Vec3(1, 1, 1)));

        // Without card
        Mob living = helper.spawnWithNoFreeWill(EntityType.COW, 1, 1, 1);
        Mob undead = helper.spawnWithNoFreeWill(EntityType.SKELETON, 1, 1, 1);
        player.attack(living);
        player.attack(undead);
        float living_health_normal = living.getHealth();
        float undead_health_normal = undead.getHealth();
        living.remove(Entity.RemovalReason.DISCARDED);
        undead.remove(Entity.RemovalReason.DISCARDED);

        player.addItem(new ItemStack(ItemRegistry.death.get()));

        // With card
        living = helper.spawnWithNoFreeWill(EntityType.COW, 1, 1, 1);
        undead = helper.spawnWithNoFreeWill(EntityType.SKELETON, 1, 1, 1);
        player.attack(living);
        player.attack(undead);
        float living_health_after_card = living.getHealth();
        float undead_health_after_card = undead.getHealth();
        living.remove(Entity.RemovalReason.DISCARDED);
        undead.remove(Entity.RemovalReason.DISCARDED);
        player.remove(Entity.RemovalReason.DISCARDED);

        // Living should be damaged
        if (living_health_after_card == living_health_normal) {
            throw new GameTestAssertException(String.format("living didnt take correct damage (with: %f, without: %f)", living_health_after_card, living_health_normal));
        }
        // Undead shouldnt be damaged
        if (undead_health_after_card != undead_health_normal) {
            throw new GameTestAssertException(String.format("undead didnt take correct damage (with: %f, without: %f)", undead_health_after_card, undead_health_normal));
        }

        helper.succeed();
    }
}

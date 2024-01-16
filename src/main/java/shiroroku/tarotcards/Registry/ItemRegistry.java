package shiroroku.tarotcards.Registry;

import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import shiroroku.tarotcards.Item.Tarot.*;
import shiroroku.tarotcards.Item.TarotDeckItem;
import shiroroku.tarotcards.TarotCards;

public class ItemRegistry {

	public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TarotCards.MODID);

	public static final DeferredItem<Item> tarot_deck = ITEMS.register("tarot_deck", TarotDeckItem::new);

	public static final DeferredItem<Item> death = ITEMS.register("death", DeathTarot::new);
	public static final DeferredItem<Item> judgement = ITEMS.register("judgement", JudgementTarot::new);
	public static final DeferredItem<Item> justice = ITEMS.register("justice", JusticeTarot::new);
	public static final DeferredItem<Item> strength = ITEMS.register("strength", StrengthTarot::new);
	public static final DeferredItem<Item> temperance = ITEMS.register("temperance", TemperanceTarot::new);
	public static final DeferredItem<Item> the_chariot = ITEMS.register("the_chariot", TheChariotTarot::new);
	public static final DeferredItem<Item> the_devil = ITEMS.register("the_devil", TheDevilTarot::new);
	public static final DeferredItem<Item> the_emperor = ITEMS.register("the_emperor", TheEmperorTarot::new);
	public static final DeferredItem<Item> the_empress = ITEMS.register("the_empress", TheEmpressTarot::new);
	public static final DeferredItem<Item> the_fool = ITEMS.register("the_fool", TheFoolTarot::new);
	public static final DeferredItem<Item> the_hanged_man = ITEMS.register("the_hanged_man", TheHangedManTarot::new);
	public static final DeferredItem<Item> the_hermit = ITEMS.register("the_hermit", TheHermitTarot::new);
	public static final DeferredItem<Item> the_hierophant = ITEMS.register("the_hierophant", TheHierophantTarot::new);
	public static final DeferredItem<Item> the_high_priestess = ITEMS.register("the_high_priestess", TheHighPriestessTarot::new);
	public static final DeferredItem<Item> the_lovers = ITEMS.register("the_lovers", TheLoversTarot::new);
	public static final DeferredItem<Item> the_magician = ITEMS.register("the_magician", TheMagicianTarot::new);
	public static final DeferredItem<Item> the_moon = ITEMS.register("the_moon", TheMoonTarot::new);
	public static final DeferredItem<Item> the_star = ITEMS.register("the_star", TheStarTarot::new);
	public static final DeferredItem<Item> the_sun = ITEMS.register("the_sun", TheSunTarot::new);
	public static final DeferredItem<Item> the_tower = ITEMS.register("the_tower", TheTowerTarot::new);
	public static final DeferredItem<Item> the_world = ITEMS.register("the_world", TheWorldTarot::new);
	public static final DeferredItem<Item> wheel_of_fortune = ITEMS.register("wheel_of_fortune", WheelOfFortuneTarot::new);

}

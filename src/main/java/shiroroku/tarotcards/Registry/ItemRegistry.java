package shiroroku.tarotcards.Registry;

import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import shiroroku.tarotcards.Item.Tarot.*;
import shiroroku.tarotcards.Item.TarotDeckItem;
import shiroroku.tarotcards.Item.TarotItem;
import shiroroku.tarotcards.TarotCards;

public class ItemRegistry {

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, TarotCards.MODID);

	public static final RegistryObject<Item> tarot_deck = ITEMS.register("tarot_deck", TarotDeckItem::new);

	public static final RegistryObject<Item> death = ITEMS.register("death", DeathTarot::new);
	public static final RegistryObject<Item> judgement = ITEMS.register("judgement", JudgementTarot::new);
	public static final RegistryObject<Item> justice = ITEMS.register("justice", JusticeTarot::new);
	public static final RegistryObject<Item> strength = ITEMS.register("strength", StrengthTarot::new);
	public static final RegistryObject<Item> temperance = ITEMS.register("temperance", TemperanceTarot::new);
	public static final RegistryObject<Item> the_chariot = ITEMS.register("the_chariot", TheChariotTarot::new);
	public static final RegistryObject<Item> the_devil = ITEMS.register("the_devil", TheDevilTarot::new);
	public static final RegistryObject<Item> the_emperor = ITEMS.register("the_emperor", TheEmperorTarot::new);
	public static final RegistryObject<Item> the_empress = ITEMS.register("the_empress", TarotItem::new);
	public static final RegistryObject<Item> the_fool = ITEMS.register("the_fool", TheFoolTarot::new);
	public static final RegistryObject<Item> the_hanged_man = ITEMS.register("the_hanged_man", TarotItem::new);
	public static final RegistryObject<Item> the_hermit = ITEMS.register("the_hermit", TheHermitTarot::new);
	public static final RegistryObject<Item> the_hierophant = ITEMS.register("the_hierophant", TheHierophantTarot::new);
	public static final RegistryObject<Item> the_high_priestess = ITEMS.register("the_high_priestess", TheHighPriestessTarot::new);
	public static final RegistryObject<Item> the_lovers = ITEMS.register("the_lovers", TheLoversTarot::new);
	public static final RegistryObject<Item> the_magician = ITEMS.register("the_magician", TheMagicianTarot::new);
	public static final RegistryObject<Item> the_moon = ITEMS.register("the_moon", TheMoonTarot::new);
	public static final RegistryObject<Item> the_star = ITEMS.register("the_star", TheStarTarot::new);
	public static final RegistryObject<Item> the_sun = ITEMS.register("the_sun", TheSunTarot::new);
	public static final RegistryObject<Item> the_tower = ITEMS.register("the_tower", TheTowerTarot::new);
	public static final RegistryObject<Item> the_world = ITEMS.register("the_world", TheWorldTarot::new);
	public static final RegistryObject<Item> wheel_of_fortune = ITEMS.register("wheel_of_fortune", WheelOfFortuneTarot::new);

}

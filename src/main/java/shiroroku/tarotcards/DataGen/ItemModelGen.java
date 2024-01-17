package shiroroku.tarotcards.DataGen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import java.util.Objects;

public class ItemModelGen extends ItemModelProvider {

	public ItemModelGen(PackOutput output, ExistingFileHelper existingFileHelper) {
		super(output, TarotCards.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels() {
		basicItem(ItemRegistry.tarot_deck.get());
		tarotModel(ItemRegistry.death.get());
		tarotModel(ItemRegistry.judgement.get());
		tarotModel(ItemRegistry.justice.get());
		tarotModel(ItemRegistry.strength.get());
		tarotModel(ItemRegistry.temperance.get());
		tarotModel(ItemRegistry.the_chariot.get());
		tarotModel(ItemRegistry.the_devil.get());
		tarotModel(ItemRegistry.the_emperor.get());
		tarotModel(ItemRegistry.the_empress.get());
		tarotModel(ItemRegistry.the_fool.get());
		tarotModel(ItemRegistry.the_hanged_man.get());
		tarotModel(ItemRegistry.the_hermit.get());
		tarotModel(ItemRegistry.the_hierophant.get());
		tarotModel(ItemRegistry.the_high_priestess.get());
		tarotModel(ItemRegistry.the_lovers.get());
		tarotModel(ItemRegistry.the_magician.get());
		tarotModel(ItemRegistry.the_moon.get());
		tarotModel(ItemRegistry.the_star.get());
		tarotModel(ItemRegistry.the_sun.get());
		tarotModel(ItemRegistry.the_tower.get());
		tarotModel(ItemRegistry.the_world.get());
		tarotModel(ItemRegistry.wheel_of_fortune.get());
	}

	public void tarotModel(Item item) {
		ResourceLocation location = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));
		getBuilder(item.toString())
				.parent(getExistingFile(modLoc("item/tarot")))
				.texture("layer0", new ResourceLocation(location.getNamespace(), "item/card/" + location.getPath()));
	}
}

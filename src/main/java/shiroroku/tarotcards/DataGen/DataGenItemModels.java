package shiroroku.tarotcards.DataGen;

import net.minecraft.data.DataGenerator;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import java.util.Objects;
import java.util.function.Supplier;

public class DataGenItemModels extends ItemModelProvider {

    public DataGenItemModels(DataGenerator output, ExistingFileHelper existingFileHelper) {
        super(output, TarotCards.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistry.tarot_deck.get());
        ItemRegistry.ITEMS_CARDS.getEntries().stream().map(Supplier::get).forEach(this::tarotModel);
    }

    private void tarotModel(Item item) {
        ResourceLocation location = Objects.requireNonNull(ForgeRegistries.ITEMS.getKey(item));
        getBuilder(item.toString())
                .parent(getExistingFile(modLoc("item/tarot")))
                .texture("layer0", new ResourceLocation(location.getNamespace(), "item/card/" + location.getPath()));
    }
}

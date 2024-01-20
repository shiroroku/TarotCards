package shiroroku.tarotcards.DataGen;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredHolder;
import shiroroku.tarotcards.Registry.ItemRegistry;
import shiroroku.tarotcards.TarotCards;

import java.util.Objects;

public class DataGenItemModels extends ItemModelProvider {

    public DataGenItemModels(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TarotCards.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ItemRegistry.tarot_deck.get());
        ItemRegistry.ITEMS_CARDS.getEntries().stream().map(DeferredHolder::get).forEach(this::tarotModel);
    }

    private void tarotModel(Item item) {
        ResourceLocation location = Objects.requireNonNull(BuiltInRegistries.ITEM.getKey(item));
        getBuilder(item.toString())
                .parent(getExistingFile(modLoc("item/tarot")))
                .texture("layer0", new ResourceLocation(location.getNamespace(), "item/card/" + location.getPath()));
    }
}

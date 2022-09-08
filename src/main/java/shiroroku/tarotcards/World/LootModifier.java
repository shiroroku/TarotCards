package shiroroku.tarotcards.World;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class LootModifier extends net.minecraftforge.common.loot.LootModifier {

	private List<Item> items;

	public LootModifier(LootItemCondition[] conditionsIn, List<Item> items) {
		super(conditionsIn);
		this.items = items;
	}

	@NotNull
	@Override
	public List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
		if (context.getRandom().nextFloat() < 0.75) {
			generatedLoot.add(new ItemStack(items.get(context.getRandom().nextInt(items.size()))));
		}
		return generatedLoot;
	}

	public static class Serializer extends GlobalLootModifierSerializer<LootModifier> {

		@Override
		public LootModifier read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
			List<Item> output = new ArrayList<>();
			JsonArray items = GsonHelper.getAsJsonArray(object, "items");
			for (JsonElement jo : items) {
				output.add(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(jo.getAsString())));
			}

			return new LootModifier(ailootcondition, output);
		}

		@Override
		public JsonObject write(LootModifier instance) {
			JsonObject json = makeConditions(instance.conditions);
			JsonArray items = new JsonArray();
			for (Item tarot : instance.items) {
				items.add(ForgeRegistries.ITEMS.getKey(tarot).toString());
			}
			json.add("items", items);
			return null;
		}
	}
}

package shiroroku.tarotcards.World;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.loot.LootModifier;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.function.Supplier;

public class TarotLootAdditions extends LootModifier {

	public List<Item> items;

	public static final Supplier<Codec<TarotLootAdditions>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).and(ForgeRegistries.ITEMS.getCodec().listOf().fieldOf("items").forGetter(v -> v.items)).apply(inst, TarotLootAdditions::new)));

	public TarotLootAdditions(LootItemCondition[] conditionsIn, List<Item> items) {
		super(conditionsIn);
		this.items = items;
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (context.getRandom().nextFloat() < 0.75) {
			generatedLoot.add(new ItemStack(items.get(context.getRandom().nextInt(items.size()))));
		}
		return generatedLoot;
	}

	@Override
	public Codec<? extends LootModifier> codec() {
		return CODEC.get();
	}


	/*
	public static class Serializer extends GlobalLootModifierSerializer<TarotLootAdditions> {

		@Override
		public TarotLootAdditions read(ResourceLocation location, JsonObject object, LootItemCondition[] ailootcondition) {
			List<Item> output = new ArrayList<>();
			JsonArray items = GsonHelper.getAsJsonArray(object, "items");
			for (JsonElement jo : items) {
				output.add(ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(jo.getAsString())));
			}

			return new TarotLootAdditions(ailootcondition, output);
		}

		@Override
		public JsonObject write(TarotLootAdditions instance) {
			JsonObject json = makeConditions(instance.conditions);
			JsonArray items = new JsonArray();
			for (Item tarot : instance.items) {
				items.add(ForgeRegistries.ITEMS.getKey(tarot).toString());
			}
			json.add("items", items);
			return null;
		}
	}*/
}

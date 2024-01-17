package shiroroku.tarotcards.World;

import com.google.common.base.Suppliers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;
import shiroroku.tarotcards.Configuration;

import java.util.List;
import java.util.function.Supplier;

public class TarotLootAdditions extends LootModifier {

	public List<Item> items;

	public static final Supplier<Codec<TarotLootAdditions>> CODEC = Suppliers.memoize(() -> RecordCodecBuilder.create(inst -> codecStart(inst).and(BuiltInRegistries.ITEM.byNameCodec().listOf().fieldOf("items").forGetter(v -> v.items)).apply(inst, TarotLootAdditions::new)));

	public TarotLootAdditions(LootItemCondition[] conditionsIn, List<Item> items) {
		super(conditionsIn);
		this.items = items;
	}

	@Override
	protected @NotNull ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext context) {
		if (Configuration.do_loot_generation.get() && context.getRandom().nextFloat() < Configuration.default_loot_chance.get()) {
			generatedLoot.add(new ItemStack(items.get(context.getRandom().nextInt(items.size()))));
		}
		return generatedLoot;
	}

	@Override
	public Codec<? extends LootModifier> codec() {
		return CODEC.get();
	}
}

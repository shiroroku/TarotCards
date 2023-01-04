package shiroroku.tarotcards.Registry;

import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import shiroroku.tarotcards.TarotCards;
import shiroroku.tarotcards.World.TarotLootAdditions;

public class GlobalLootModifierRegistry {

	public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, TarotCards.MODID);

	private static final RegistryObject<Codec<TarotLootAdditions>> loot_additions = MODIFIERS.register("loot_additions", TarotLootAdditions.CODEC);
}

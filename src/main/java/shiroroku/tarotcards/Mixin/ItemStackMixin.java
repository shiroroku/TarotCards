package shiroroku.tarotcards.Mixin;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shiroroku.tarotcards.Item.Tarot.TheMagicianTarot;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {

	@Shadow
	public abstract Item getItem();

	@Shadow
	public abstract ItemStack copy();

	@Inject(at = @At("HEAD"), method = "hurtAndBreak", cancellable = true)
	public <T extends LivingEntity> void hurtAndBreak(int amount, T entity, Consumer<T> consumer, CallbackInfo ci) {
		if (!entity.level().isClientSide && entity instanceof Player player) {
			if (TheMagicianTarot.handleItemDamage(this.copy(), player)) {
				ci.cancel();
			}
		}
	}

}

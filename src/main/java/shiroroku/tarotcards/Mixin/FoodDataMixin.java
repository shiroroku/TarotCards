package shiroroku.tarotcards.Mixin;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import shiroroku.tarotcards.Item.Tarot.TemperanceTarot;

@Mixin(FoodData.class)
public abstract class FoodDataMixin {

	@Shadow
	private float exhaustionLevel;

	private Player player;

	@Inject(at = @At("HEAD"), method = "addExhaustion", cancellable = true)
	public void addExhaustion(float amount, CallbackInfo ci) {
		float temp = TemperanceTarot.handleExhaustionAmount(amount, player);
		if (temp != amount) {
			this.exhaustionLevel = Math.min(this.exhaustionLevel + temp, 40.0F);
			ci.cancel();
		}
	}

	@Inject(at = @At("HEAD"), method = "tick")
	public void tick(Player player, CallbackInfo ci) {
		this.player = player;
	}
}

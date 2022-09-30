package shiroroku.tarotcards;

import com.mojang.blaze3d.audio.OggAudioStream;
import net.minecraftforge.common.ForgeConfigSpec;

public class Configuration {

	public static ForgeConfigSpec config;

	public static ForgeConfigSpec.DoubleValue death_damagebonus;
	public static ForgeConfigSpec.IntValue strength_amplifier;
	public static ForgeConfigSpec.DoubleValue wheel_of_fortune_luckbonus;
	public static ForgeConfigSpec.IntValue the_emperpor_heroofvillagebonus;
	public static ForgeConfigSpec.DoubleValue temperance_reduction;
	public static ForgeConfigSpec.DoubleValue the_hierophant_xpboost;
	public static ForgeConfigSpec.DoubleValue the_sun_healthboost;
	public static ForgeConfigSpec.DoubleValue the_hermit_armorbonus;
	public static ForgeConfigSpec.DoubleValue the_hermit_allyrange;
	public static ForgeConfigSpec.IntValue the_devil_weaknessamplifier;
	public static ForgeConfigSpec.IntValue the_lovers_regenamplifier;
	public static ForgeConfigSpec.DoubleValue the_lovers_range;
	public static ForgeConfigSpec.DoubleValue the_chariot_speedboost;
	public static ForgeConfigSpec.DoubleValue the_star_reachboost;

	static {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		builder.push("Tarot Cards");
		death_damagebonus = builder.comment("Percentage of damage increase to living (0.5 = +50%)").defineInRange("death_damagebonus", 0.5D, 0D, 100D);
		the_hierophant_xpboost = builder.comment("Percentage increase of experience (2 = +200%)").defineInRange("the_hierophant_xpboost", 2D, 1D, 100D);
		strength_amplifier = builder.comment("Amplifier for effect (1 = II)").defineInRange("strength_amplifier", 1, 0, 20);
		temperance_reduction = builder.comment("Multiplier for hunger gained (0.5 = -50%, 0 = no change)").defineInRange("temperance_reduction", 0.5D, 0.0D, 1D);
		the_sun_healthboost = builder.comment("Percentage increase of base health (0.5 = +50%)").defineInRange("the_sun_healthboost", 0.5D, 0.0D, 100D);
		wheel_of_fortune_luckbonus = builder.comment("How much luck (not fortune) is added to the player").defineInRange("wheel_of_fortune_luckbonus", 3D, 0.0D, 100D);
		the_emperpor_heroofvillagebonus = builder.comment("Amplifier for effect (1 = II)").defineInRange("the_emperpor_heroofvillagebonus", 2, 0, 20);
		the_hermit_armorbonus = builder.comment("How much armor is added when away from allies").defineInRange("the_hermit_armorbonus", 10D, 0.0D, 100D);
		the_hermit_allyrange = builder.comment("How far away the player must be from allies to activate").defineInRange("the_hermit_allyrange", 10D, 0.0D, 256D);
		the_devil_weaknessamplifier = builder.comment("Amplifier for effect (1 = II)").defineInRange("the_devil_weaknessamplifier", 2, 0, 20);
		the_lovers_regenamplifier = builder.comment("Amplifier for effect (1 = II)").defineInRange("the_lovers_regenamplifier", 2, 0, 20);
		the_lovers_range = builder.comment("How close allies need to be to apply regeneration").defineInRange("the_lovers_range", 5D, 0.0D, 256D);
		the_chariot_speedboost = builder.comment("Percentage increase of base speed (0.5 = +50%)").defineInRange("the_chariot_speedboost", 0.5D, 0.0D, 100D);
		the_star_reachboost = builder.comment("Percentage increase of reach distance (0.5 = +50%)").defineInRange("the_star_reachboost", 0.5D, 0.0D, 100D);

		builder.pop();

		config = builder.build();
	}

}

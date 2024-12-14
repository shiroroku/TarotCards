package shiroroku.tarotcards;

import net.neoforged.neoforge.common.ModConfigSpec;

public class Configuration {

    public static ModConfigSpec config = build();

    public static ModConfigSpec.BooleanValue require_card_in_curio;
    public static ModConfigSpec.BooleanValue tarot_deck_applies_effects;
    public static ModConfigSpec.BooleanValue do_loot_generation;
    public static ModConfigSpec.BooleanValue the_highpriestess_capenchants;
    public static ModConfigSpec.DoubleValue death_damagebonus;
    public static ModConfigSpec.DoubleValue default_loot_chance;
    public static ModConfigSpec.DoubleValue judgement_damagechance;
    public static ModConfigSpec.DoubleValue justice_damagemultiplier;
    public static ModConfigSpec.DoubleValue temperance_reduction;
    public static ModConfigSpec.DoubleValue the_chariot_speedboost;
    public static ModConfigSpec.DoubleValue the_empress_range;
    public static ModConfigSpec.DoubleValue the_hanged_man_xpratio;
    public static ModConfigSpec.IntValue the_hanged_man_xpcap;
    public static ModConfigSpec.DoubleValue the_hermit_allyrange;
    public static ModConfigSpec.DoubleValue the_hermit_armorbonus;
    public static ModConfigSpec.DoubleValue the_hierophant_xpboost;
    public static ModConfigSpec.DoubleValue the_lovers_range;
    public static ModConfigSpec.DoubleValue the_star_reachboost;
    public static ModConfigSpec.DoubleValue the_sun_healthboost;
    public static ModConfigSpec.DoubleValue the_tower_damagenegation;
    public static ModConfigSpec.DoubleValue the_world_range;
    public static ModConfigSpec.DoubleValue wheel_of_fortune_luckbonus;
    public static ModConfigSpec.IntValue strength_amplifier;
    public static ModConfigSpec.IntValue the_devil_weaknessamplifier;
    public static ModConfigSpec.IntValue the_emperpor_heroofvillagebonus;
    public static ModConfigSpec.IntValue the_fool_jumpboost;
    public static ModConfigSpec.IntValue the_highpriestess_upgradecost;
    public static ModConfigSpec.IntValue the_lovers_regenamplifier;
    public static ModConfigSpec.IntValue the_world_slownessamplifier;
    public static ModConfigSpec.IntValue tick_rate;

    private static ModConfigSpec build() {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();
        builder.push("Tarot Cards");
        tick_rate = builder.comment("How many ticks it takes to check if a player has a tarot or not, more = more performace but longer time to activate/deactivate effects").defineInRange("tick_rate", 20, 0, 1200);
        require_card_in_curio = builder.comment("Requires the card to be in your curio slot, or in the tarot deck (also in the curio slot) to function").define("require_card_in_curio", false);
        tarot_deck_applies_effects  = builder.comment("If cards in the tarot deck apply their effects to the player holding it").define("tarot_deck_applies_effects", true);
        builder.push("Loot");
        default_loot_chance = builder.comment("Chance a tarot card appears in default loot (0.75 = 75%)(1.0 = there will always be a single tarot card in chests)").defineInRange("default_loot_chance", 0.2D, 0D, 1D);
        do_loot_generation = builder.comment("If Tarot Cards should be added to the default loot tables specified in data").define("do_loot_generation", true);
        builder.pop();
        builder.push("Cards");
        death_damagebonus = builder.comment("Percentage of damage increase to living (0.5 = +50%)").defineInRange("death_damagebonus", 0.2D, 0D, 100D);
        judgement_damagechance = builder.comment("Percentage chance of dealing double damage (0.5 = 50%)").defineInRange("judgement_damagechance", 0.15D, 0D, 1D);
        the_tower_damagenegation = builder.comment("Percentage of fall damage negated (0.5 = 50%)").defineInRange("the_tower_damagenegation", 1D, 0D, 1D);
        justice_damagemultiplier = builder.comment("Percentage of damage returned to the attacker (1.0 = 100%)").defineInRange("justice_damagemultiplier", 0.25D, 0D, 100D);
        strength_amplifier = builder.comment("Amplifier for effect (1 = II)").defineInRange("strength_amplifier", 1, 0, 20);
        temperance_reduction = builder.comment("Multiplier for hunger gained (0.5 = -50%, 0 = no change)").defineInRange("temperance_reduction", 0.25D, 0.0D, 1D);
        the_chariot_speedboost = builder.comment("Percentage increase of base speed (0.5 = +50%)").defineInRange("the_chariot_speedboost", 0.20D, 0.0D, 100D);
        the_devil_weaknessamplifier = builder.comment("Amplifier for effect (1 = II)").defineInRange("the_devil_weaknessamplifier", 2, 0, 20);
        the_emperpor_heroofvillagebonus = builder.comment("Amplifier for effect (1 = II)").defineInRange("the_emperpor_heroofvillagebonus", 2, 0, 20);
        the_empress_range = builder.comment("How close animals need to be to set lovemode").defineInRange("the_empress_range", 3D, 0.0D, 256D);
        the_fool_jumpboost = builder.comment("Amplifier for effect (2 = III)").defineInRange("the_fool_jumpboost", 2, 0, 100);
        the_hanged_man_xpratio = builder.comment("How much of the damage is converted to experience").defineInRange("the_hanged_man_xpratio", 0.5D, 0.0D, 1D);
        the_hanged_man_xpcap = builder.comment("Max amount of experience that can be obtained from an instance of damage").defineInRange("the_hanged_man_xpcap", 25, 0, Integer.MAX_VALUE);
        the_hermit_allyrange = builder.comment("How far away the player must be from allies to activate").defineInRange("the_hermit_allyrange", 10D, 0.0D, 256D);
        the_hermit_armorbonus = builder.comment("How much armor is added when away from allies").defineInRange("the_hermit_armorbonus", 10D, 0.0D, 100D);
        the_hierophant_xpboost = builder.comment("Percentage increase of experience (2 = +200%)").defineInRange("the_hierophant_xpboost", 1.5D, 1D, 100D);
        the_highpriestess_capenchants = builder.comment("Stops upgrading enchantment books when they are at max level").define("the_highpriestess_capenchants", true);
        the_highpriestess_upgradecost = builder.comment("How much each level of enchantment costs to upgrade (cost = enchantment_level * this_config)").defineInRange("the_highpriestess_upgrademultiplier", 3, 1, 100);
        the_lovers_range = builder.comment("How close allies need to be to apply regeneration").defineInRange("the_lovers_range", 5D, 0.0D, 256D);
        the_lovers_regenamplifier = builder.comment("Amplifier for effect (1 = II)").defineInRange("the_lovers_regenamplifier", 2, 0, 20);
        the_star_reachboost = builder.comment("Percentage increase of reach distance (0.5 = +50%)").defineInRange("the_star_reachboost", 0.5D, 0.0D, 100D);
        the_sun_healthboost = builder.comment("Percentage increase of base health (0.5 = +50%)").defineInRange("the_sun_healthboost", 0.25D, 0.0D, 100D);
        the_world_range = builder.comment("How close non-allies need to be to apply slowness").defineInRange("the_world_range", 3D, 0.0D, 256D);
        the_world_slownessamplifier = builder.comment("Amplifier for effect (9 = X)").defineInRange("the_world_slownessamplifier", 3, 0, 20);
        wheel_of_fortune_luckbonus = builder.comment("How much luck (not fortune) is added to the player").defineInRange("wheel_of_fortune_luckbonus", 3D, 0.0D, 100D);
        builder.pop();
        builder.pop();
        return builder.build();
    }

}

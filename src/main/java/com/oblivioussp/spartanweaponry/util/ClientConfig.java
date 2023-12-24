package com.oblivioussp.spartanweaponry.util;

import org.apache.commons.lang3.tuple.Pair;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.client.gui.AlignmentHelper.Alignment;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.EnumValue;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientConfig 
{
	public static final ClientConfig INSTANCE;
	public static final ForgeConfigSpec CONFIG_SPEC;
	
	static
	{
		 final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
		 INSTANCE = specPair.getLeft();
		 CONFIG_SPEC = specPair.getRight();
	}
	
	// Client settings
	public BooleanValue disableNewCrosshairsCrossbow, disableNewCrosshairsThrowingWeapon,
						forceCompatibilityCrosshairs;
	public EnumValue<Alignment> quiverHudAlignment, oilUsesHudAlignment;
	public IntValue quiverHudOffsetX, quiverHudOffsetY, oilUsesHudOffsetX, oilUsesHudOffsetY;
	public BooleanValue forceDisableUncraftableTooltips;
	
	public ClientConfig(ForgeConfigSpec.Builder builder)
	{
		builder.push("general");
			forceDisableUncraftableTooltips = builder.comment("Requires game restart! Set to true to remove the uncraftable tooltips for any uncraftable weapon (highlighted in red). Useful for modpack makers who wish to change recipes.")
								.translation("config." + ModSpartanWeaponry.ID + ".client.force_disable_uncraftable_tooltips")
								.worldRestart()
								.define("force_disable_uncraftable_tooltips", false);
		builder.pop();
		builder.push("hud");
			disableNewCrosshairsCrossbow = builder.comment("Set to true to disable a new Crosshair for the Crossbow which visually shows inaccuracy, using the default Crosshair instead; false otherwise")
								.translation("config." + ModSpartanWeaponry.ID + ".client.disable_new_crosshairs_crossbow")
								.define("disable_new_crosshairs_crossbow", false);
			disableNewCrosshairsThrowingWeapon = builder.comment("Set to true to disable a new Crosshair for Throwing Weapons which show the charge for them, using the default Crosshair instead; false otherwise")
								.translation("config." + ModSpartanWeaponry.ID + ".client.disable_new_crosshairs_throwing_weapons")
								.define("disable_new_crosshairs_throwing_weapons", false);
			forceCompatibilityCrosshairs = builder.comment("Set to force compatibility crosshairs for Crosshairs and Throwing Weapons. This won't work if the new crosshairs are disabled")
								.translation("config." + ModSpartanWeaponry.ID + ".client.force_compatibility_crosshairs")
								.define("force_compatibility_crosshairs", false);
			builder.push("quiver");
				quiverHudAlignment = builder.comment("Sets where the Quiver HUD Element should be aligned")
									.translation("config." + ModSpartanWeaponry.ID + ".client.quiver_hud_alignment")
									.defineEnum("quiver_hud_alignment", Alignment.BOTTOM_CENTER);
				quiverHudOffsetX = builder.comment("Sets where on the X-axis the Quiver HUD element should be off-set from it's alignment point")
									.translation("config." + ModSpartanWeaponry.ID + ".client.quiver_hud_offset_x")
									.defineInRange("quiver_hud_offset_x", Defaults.DefaultQuiverHudOffsetX, -400, 400);
				quiverHudOffsetY = builder.comment("Sets where on the Y-axis the Quiver HUD element should be off-set from it's alignment point")
									.translation("config." + ModSpartanWeaponry.ID + ".client.quiver_hud_offset_y")
									.defineInRange("quiver_hud_offset_y", Defaults.DefaultQuiverHudOffsetY, -400, 400);
			builder.pop();
			builder.push("oil_uses");
				oilUsesHudAlignment = builder.comment("Sets where the Oil Uses HUD Element should be aligned")
									.translation("config." + ModSpartanWeaponry.ID + ".client.oil_uses_hud_alignment")
									.defineEnum("oil_uses_alignment", Alignment.CENTER);
				oilUsesHudOffsetX = builder.comment("Sets where on the X-axis the Oil Uses HUD element should be off-set from it's alignment point")
									.translation("config." + ModSpartanWeaponry.ID + ".client.oil_uses_hud_offset_x")
									.defineInRange("oil_uses_hud_offset_x", Defaults.DefaultOilUsesHudOffsetX, -400, 400);
				oilUsesHudOffsetY = builder.comment("Sets where on the Y-axis the Oil Uses HUD element should be off-set from it's alignment point")
									.translation("config." + ModSpartanWeaponry.ID + ".client.oil_uses_offset_y")
									.defineInRange("oil_uses_hud_offset_y", Defaults.DefaultOilUsesHudOffsetY, -400, 400);
			builder.pop();
		builder.pop();
	}
}

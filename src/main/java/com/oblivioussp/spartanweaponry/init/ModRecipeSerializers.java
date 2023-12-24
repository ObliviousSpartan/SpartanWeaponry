package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.api.crafting.condition.TypeDisabledCondition;
import com.oblivioussp.spartanweaponry.item.crafting.QuiverUpgradeRecipe;
import com.oblivioussp.spartanweaponry.item.crafting.TippedProjectileBaseRecipe;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModRecipeSerializers
{
	public static TippedProjectileBaseRecipe.Serializer TIPPED_PROJECTILE_BASE;
	public static QuiverUpgradeRecipe.Serializer QUIVER_UPGRADE_SMITHING;
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<IRecipeSerializer<?>> ev)
	{
		IForgeRegistry<IRecipeSerializer<?>> reg = ev.getRegistry();
		
		CraftingHelper.register(TypeDisabledCondition.Serializer.INSTANCE);
		
		TIPPED_PROJECTILE_BASE = new TippedProjectileBaseRecipe.Serializer();
		QUIVER_UPGRADE_SMITHING = new QuiverUpgradeRecipe.Serializer();

		reg.registerAll(TIPPED_PROJECTILE_BASE, QUIVER_UPGRADE_SMITHING);
	}
}

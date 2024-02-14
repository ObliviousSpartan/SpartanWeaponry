package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.crafting.condition.TypeDisabledCondition;
import com.oblivioussp.spartanweaponry.item.crafting.ApplyOilRecipe;
import com.oblivioussp.spartanweaponry.item.crafting.QuiverUpgradeRecipe;
import com.oblivioussp.spartanweaponry.item.crafting.TippedProjectileBaseRecipe;

import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipeSerializers
{
	public static final DeferredRegister<RecipeSerializer<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.RECIPE_SERIALIZERS, ModSpartanWeaponry.ID);
	
	public static final RegistryObject<TippedProjectileBaseRecipe.Serializer> TIPPED_PROJECTILE_BASE = REGISTRY.register("tipped_projectile", () -> new TippedProjectileBaseRecipe.Serializer());
	public static final RegistryObject<QuiverUpgradeRecipe.Serializer> QUIVER_UPGRADE_SMITHING = REGISTRY.register("quiver_upgrade_smithing", () -> new QuiverUpgradeRecipe.Serializer());
	public static final RegistryObject<SimpleRecipeSerializer<ApplyOilRecipe>> APPLY_OIL = REGISTRY.register("apply_oil", () -> new SimpleRecipeSerializer<>(ApplyOilRecipe::new));
	
	public static void registerRecipeConditions()
	{
		CraftingHelper.register(TypeDisabledCondition.Serializer.INSTANCE);
	}
}

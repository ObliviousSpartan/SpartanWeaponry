package com.oblivioussp.spartanweaponry.compat.jei;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.OilEffects;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.capability.OilHandler;
import com.oblivioussp.spartanweaponry.util.OilHelper;

import mezz.jei.api.ingredients.subtypes.IIngredientSubtypeInterpreter;
import mezz.jei.api.ingredients.subtypes.UidContext;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;

public class WeaponOilSubtypeInterpreter implements IIngredientSubtypeInterpreter<ItemStack>
{
	public static final WeaponOilSubtypeInterpreter INSTANCE = new WeaponOilSubtypeInterpreter();

	@Override
	public String apply(ItemStack itemStack, UidContext context)
	{
		if (!itemStack.hasTag())
			return null;
		
		OilEffect weaponOil = OilHelper.getOilFromStack(itemStack);
		Potion potion = OilHelper.getPotionFromStack(itemStack);
		
		ForgeRegistry<OilEffect> registry = RegistryManager.ACTIVE.getRegistry(OilEffects.REGISTRY_KEY);
		String result = registry.getKey(weaponOil).getPath();
		if(weaponOil == OilEffects.POTION.get())
		{
			StringBuilder stringBuilder = new StringBuilder(result);
			stringBuilder.append(potion.getName(":"));
			List<MobEffectInstance> mobEffects = PotionUtils.getAllEffects(itemStack.getOrCreateTag().getCompound(OilHandler.NBT_OIL));
			for(MobEffectInstance mobEffect : mobEffects)
			{
				stringBuilder.append(";").append(mobEffect);
			}
			result = stringBuilder.toString();
		}
		return result;
	}

}

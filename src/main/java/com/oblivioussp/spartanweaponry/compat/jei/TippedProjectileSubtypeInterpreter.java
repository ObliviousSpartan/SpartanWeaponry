package com.oblivioussp.spartanweaponry.compat.jei;

import java.util.List;

import mezz.jei.api.ISubtypeRegistry.ISubtypeInterpreter;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;

// Copy of PotionSubtypeInterpreter from JEI

// The MIT License Copyright (c) 2017 mezz
// Permission is hereby granted, free of charge, to any person obtaining a copy 
// of this software and associated documentation files (the "Software"), to deal 
// in the Software without restriction, including without limitation the rights to use,
// copy, modify, merge, publish, distribute, sublicense, and/or sell copies of
// the Software, and to permit persons to whom the Software is furnished to do so,
// subject to the following conditions:

// The above copyright notice and this permission notice shall be included in 
// all copies or substantial portions of the Software. 
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED,
// INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A
// PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR 
// COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY,
// WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR
// IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

public class TippedProjectileSubtypeInterpreter implements ISubtypeInterpreter 
{
	public static final TippedProjectileSubtypeInterpreter INSTANCE = new TippedProjectileSubtypeInterpreter();

	private TippedProjectileSubtypeInterpreter() {}

	@Override
	public String apply(ItemStack itemStack) 
	{
		if (!itemStack.hasTagCompound())
			return null;
		
		PotionType potionType = PotionUtils.getPotionFromItem(itemStack);
		String potionTypeString = potionType.getNamePrefixed("");
		StringBuilder stringBuilder = new StringBuilder(potionTypeString);
		List<PotionEffect> effects = PotionUtils.getEffectsFromStack(itemStack);
		for (PotionEffect effect : effects) 
		{
			stringBuilder.append(";").append(effect);
		}

		return stringBuilder.toString();
	}

}

package com.oblivioussp.spartanweaponry.api.data.model;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.model.generators.CustomLoaderBuilder;
import net.minecraftforge.client.model.generators.ModelBuilder;
import net.minecraftforge.common.data.ExistingFileHelper;

public class OilCoatingItemModelBuilder<T extends ModelBuilder<T>> extends CustomLoaderBuilder<T> 
{
	protected OilCoatingItemModelBuilder(T parent, ExistingFileHelper existingFileHelper) 
	{
		super(new ResourceLocation(ModSpartanWeaponry.ID, "oil_coated_item"), parent, existingFileHelper);
	}
}

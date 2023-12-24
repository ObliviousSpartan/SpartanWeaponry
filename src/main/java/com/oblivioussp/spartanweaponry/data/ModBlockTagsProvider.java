package com.oblivioussp.spartanweaponry.data;

import org.jetbrains.annotations.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.tags.ModBlockTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBlockTagsProvider extends BlockTagsProvider
{
	public ModBlockTagsProvider(DataGenerator dataGenerator, @Nullable ExistingFileHelper existingFileHelper) 
	{
		super(dataGenerator, ModSpartanWeaponry.ID, existingFileHelper);
	}
	
	@Override
	protected void addTags()
	{
		tag(ModBlockTags.GRASS).add(Blocks.GRASS, Blocks.SEAGRASS, Blocks.FERN);
	}
	
	@Override
	public String getName() 
	{
		return ModSpartanWeaponry.NAME + " Block Tags";
	}
}

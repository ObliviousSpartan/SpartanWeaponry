package com.oblivioussp.spartanweaponry.data;

import org.jetbrains.annotations.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.tags.ModEntityTypeTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModEntityTypeTagsProvider extends EntityTypeTagsProvider 
{

	public ModEntityTypeTagsProvider(DataGenerator dataGenIn, @Nullable ExistingFileHelper existingFileHelperIn) 
	{
		super(dataGenIn, ModSpartanWeaponry.ID, existingFileHelperIn);
	}
	
	@Override
	protected void addTags()
	{
		tag(ModEntityTypeTags.CREEPERS).add(EntityType.CREEPER);
		tag(ModEntityTypeTags.HUMANOIDS).addTag(EntityTypeTags.RAIDERS).add(EntityType.VILLAGER).add(EntityType.PLAYER);
		tag(ModEntityTypeTags.ENDER).add(EntityType.ENDERMAN, EntityType.ENDER_DRAGON, EntityType.ENDERMITE, EntityType.SHULKER);
	}
	
	@Override
	public String getName()
	{
		return ModSpartanWeaponry.NAME + super.getName();
	}

}

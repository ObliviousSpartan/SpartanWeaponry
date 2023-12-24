package com.oblivioussp.spartanweaponry.api.tags;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * This class contains all the different entity type tags used by Spartan Weaponry. 
 * Addon authors can add their entities to these tags to allow them to be affected by specific weapon Oils
 * @author ObliviousSpartan
 *
 */
public class ModEntityTypeTags 
{
	public static final TagKey<EntityType<?>> CREEPERS = createTag("forge:creepers");
	public static final TagKey<EntityType<?>> HUMANOIDS = createTag("forge:humanoids");
	public static final TagKey<EntityType<?>> ENDER = createTag("forge:ender");
			
	public static TagKey<EntityType<?>> createTag(String tagName)
	{
		return TagKey.create(ForgeRegistries.Keys.ENTITY_TYPES, new ResourceLocation(tagName));
	}
}

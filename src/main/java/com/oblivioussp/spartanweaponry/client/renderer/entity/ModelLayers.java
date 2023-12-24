package com.oblivioussp.spartanweaponry.client.renderer.entity;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModelLayers 
{
	public static final ModelLayerLocation SMALL_ARROW_QUIVER = createMainModelLayer("small_arrow_quiver");
	public static final ModelLayerLocation MEDIUM_ARROW_QUIVER = createMainModelLayer("medium_arrow_quiver");
	public static final ModelLayerLocation LARGE_ARROW_QUIVER = createMainModelLayer("large_arrow_quiver");
	
	public static final ModelLayerLocation SMALL_BOLT_QUIVER = createMainModelLayer("small_bolt_quiver");
	public static final ModelLayerLocation MEDIUM_BOLT_QUIVER = createMainModelLayer("medium_bolt_quiver");
	public static final ModelLayerLocation LARGE_BOLT_QUIVER = createMainModelLayer("large_bolt_quiver");
	
	public static final ModelLayerLocation BLAZE_HEAD = createMainModelLayer("blaze_head");
	public static final ModelLayerLocation ENDERMAN_HEAD = createMainModelLayer("enderman_head");
	public static final ModelLayerLocation SPIDER_HEAD = createMainModelLayer("spider_head");
	public static final ModelLayerLocation CAVE_SPIDER_HEAD = createMainModelLayer("cave_spider_head");
	public static final ModelLayerLocation PIGLIN_HEAD = createMainModelLayer("piglin_head");
	public static final ModelLayerLocation ZOMBIFIED_PIGLIN_HEAD = createMainModelLayer("zombified_piglin_head");
	public static final ModelLayerLocation HUSK_HEAD = createMainModelLayer("husk_head");
	public static final ModelLayerLocation STRAY_SKULL = createMainModelLayer("stray_skull");
	public static final ModelLayerLocation DROWNED_HEAD = createMainModelLayer("drowned_head");
	public static final ModelLayerLocation ILLAGER_HEAD = createMainModelLayer("illager_head");
	public static final ModelLayerLocation WITCH_HEAD = createMainModelLayer("witch_head");

	public static ModelLayerLocation createMainModelLayer(String location)
	{
		return new ModelLayerLocation(new ResourceLocation(ModSpartanWeaponry.ID, location), "main");
	}
}

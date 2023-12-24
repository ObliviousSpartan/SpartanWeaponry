package com.oblivioussp.spartanweaponry.client.model;

import java.util.Collection;
import java.util.Set;
import java.util.function.Function;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Transformation;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.client.model.CompositeModelState;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.IModelLoader;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.client.model.PerspectiveMapWrapper;
import net.minecraftforge.client.model.geometry.IModelGeometry;

public class OilCoatedItemModel implements IModelGeometry<OilCoatedItemModel> 
{
	private ImmutableList<Material> textures;
	private Material coatingTexture;
	private final ImmutableSet<Integer> fullbrightLayers;
	
	public OilCoatedItemModel(ImmutableList<Material> texturesIn)
	{
		this(texturesIn, ImmutableSet.of());
	}
	
	public OilCoatedItemModel(ImmutableList<Material> texturesIn, ImmutableSet<Integer> fullbrightLayersIn)
	{
		textures = texturesIn;
		fullbrightLayers = fullbrightLayersIn;
	}

	@Override
	public BakedModel bake(IModelConfiguration owner, ModelBakery bakery,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides,
			ResourceLocation modelLocation)
	{
		ImmutableMap<ItemTransforms.TransformType, Transformation> transformMap = PerspectiveMapWrapper.getTransforms(new CompositeModelState(owner.getCombinedTransform(), modelTransform));
		Transformation transform = modelTransform.getRotation();
		TextureAtlasSprite particleSprite = spriteGetter.apply(owner.isTexturePresent("particle") ? owner.resolveTexture("particle") : textures.get(0));
		
		OilCoatingItemBakedModel.Builder modelBuilder = OilCoatingItemBakedModel.makeBuilder(owner, particleSprite, overrides, transformMap);
		for(int i = 0; i < textures.size(); i++)
		{
			if(textures.get(i) == coatingTexture)	// Ignore the coating texture
				continue;
			TextureAtlasSprite texture = spriteGetter.apply(textures.get(i));
			boolean fullbright = fullbrightLayers.contains(i);
			RenderType renderType = ItemLayerModel.getLayerRenderType(fullbright);
			modelBuilder.addQuads(renderType, ItemLayerModel.getQuadsForSprite(i, texture, transform, fullbright));
		}
		
		// Additionally, add the coating layer too if present
		if(coatingTexture != null)
		{
			TextureAtlasSprite coatingTextureSprite = spriteGetter.apply(coatingTexture);
			boolean coatingFullbright = false;
			RenderType coatingRenderType = ItemLayerModel.getLayerRenderType(coatingFullbright);
			modelBuilder.addCoatingQuads(coatingRenderType, ItemLayerModel.getQuadsForSprite(100, coatingTextureSprite, transform, coatingFullbright));
		}
		else
			Log.error("Coating texture is not present in model: '" + owner.getModelName() + "'");
		
		return modelBuilder.build();
	}

	@Override
	public Collection<Material> getTextures(IModelConfiguration owner,
			Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors)
	{
		ImmutableList.Builder<Material> textureBuilder = ImmutableList.builder();
		for(int i = 0; owner.isTexturePresent("layer" + i); i++)
		{
			textureBuilder.add(owner.resolveTexture("layer" + i));
		}
		if(owner.isTexturePresent("coating"))
		{
			coatingTexture = owner.resolveTexture("coating");
			textureBuilder.add(coatingTexture);
		}
		textures = textureBuilder.build();
		return textures;
	}
	
	public static class Loader implements IModelLoader<OilCoatedItemModel>
	{
		public static final Loader INSTANCE = new Loader();

		@Override
		public void onResourceManagerReload(ResourceManager resourceManager) 
		{
		}

		@Override
		public OilCoatedItemModel read(JsonDeserializationContext deserializationContext, JsonObject modelContents) 
		{
			ImmutableSet.Builder<Integer> fullbrightBuilder = ImmutableSet.builder();
			if(modelContents.has("fullbright_layers"))
			{
				JsonArray fullbrightArray = GsonHelper.getAsJsonArray(modelContents, "fullbright_layers");
				for(JsonElement fullbrightElement : fullbrightArray)
					fullbrightBuilder.add(fullbrightElement.getAsInt());
			}
			return new OilCoatedItemModel(null, fullbrightBuilder.build());
		}
		
	}

}

package com.oblivioussp.spartanweaponry.client.model;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

import com.google.common.collect.ImmutableList;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Transformation;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.BlockElement;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.client.ForgeRenderTypes;
import net.minecraftforge.client.RenderTypeGroup;
import net.minecraftforge.client.model.ItemLayerModel;
import net.minecraftforge.client.model.QuadTransformers;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IGeometryLoader;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;
import net.minecraftforge.client.model.geometry.UnbakedGeometryHelper;

/** Copy of Forge's {@linkplain ItemLayerModel} with the addition of a coating layer for use with items that can be oiled
 * 
 * @author ObliviousSpartan
 *
 */
public class OilCoatedItemModel implements IUnbakedGeometry<OilCoatedItemModel>
{
	protected ImmutableList<Material> textures;
	protected Material coatingTexture;
	protected final IntSet emissiveLayers;
	protected final Int2ObjectMap<ResourceLocation> renderTypeNames;
//	protected final boolean logWarning;

	public OilCoatedItemModel(@Nullable ImmutableList<Material> texturesIn, @Nullable Material coatingTextureIn, IntSet emissiveLayersIn,
			Int2ObjectMap<ResourceLocation> renderTypeNamesIn) 
	{
		textures = texturesIn;
		coatingTexture = coatingTextureIn;
		emissiveLayers = emissiveLayersIn;
		renderTypeNames = renderTypeNamesIn;
//		logWarning = logWarningIn;
	}

	@Override
	public BakedModel bake(IGeometryBakingContext context, ModelBakery bakery,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelState, ItemOverrides overrides,
			ResourceLocation modelLocation) 
	{
		if(textures == null)
			throw new IllegalStateException("Textures aren't initialized! Pass into constructor or call getMaterials(...) first!");
		
//		if(logWarning)
//			Log.warn("Model \"" + modelLocation + " \" is using the deprecated \"fullbright_layers\" field in the model. Use \"emissive_layers\" instead.");
		
		TextureAtlasSprite particleSprite = spriteGetter.apply(context.hasMaterial("particle") ? context.getMaterial("particle") : textures.get(0));
		
		// Apply root transformation to the model state if not default
		Transformation transform = context.getRootTransform();
		if(!transform.isIdentity())
			modelState = UnbakedGeometryHelper.composeRootTransformIntoModelState(modelState, transform);
		
		RenderTypeGroup normalRenderTypes = new RenderTypeGroup(RenderType.translucent(), ForgeRenderTypes.ITEM_UNSORTED_TRANSLUCENT.get());
		OilCoatingItemBakedModel.Builder builder = OilCoatingItemBakedModel.makeBuilder(context, particleSprite, overrides, context.getTransforms());
		
		for(int i = 0; i < textures.size(); i++)
		{
			Material material = textures.get(i);
			
			// Ignore the coating texture
			if(material == coatingTexture)
				continue;
			
			TextureAtlasSprite sprite = spriteGetter.apply(textures.get(i));
			List<BlockElement> unbakedElements = UnbakedGeometryHelper.createUnbakedItemElements(i, sprite);
			List<BakedQuad> bakedQuads = UnbakedGeometryHelper.bakeElements(unbakedElements, mat -> sprite, modelState, modelLocation);
			if(emissiveLayers.contains(i))
				QuadTransformers.settingMaxEmissivity().processInPlace(bakedQuads);
			ResourceLocation renderTypeName = renderTypeNames.get(i);
			RenderTypeGroup renderTypes = renderTypeName != null ? context.getRenderType(renderTypeName) : normalRenderTypes;
			builder.addQuads(renderTypes, bakedQuads);
		}
		
		// Bake the coating quads
		if(coatingTexture != null)
		{
			final int coatingLayer = 100;
			TextureAtlasSprite sprite = spriteGetter.apply(coatingTexture);
			List<BlockElement> unbakedElements = UnbakedGeometryHelper.createUnbakedItemElements(coatingLayer, sprite);
			List<BakedQuad> bakedQuads = UnbakedGeometryHelper.bakeElements(unbakedElements, mat -> sprite, modelState, modelLocation);
			if(emissiveLayers.contains(coatingLayer))
				QuadTransformers.settingMaxEmissivity().processInPlace(bakedQuads);
			ResourceLocation renderTypeName = renderTypeNames.get(coatingLayer);
			RenderTypeGroup renderTypes = renderTypeName != null ? context.getRenderType(renderTypeName) : normalRenderTypes;
			builder.addCoatedQuads(renderTypes, bakedQuads);
		}
		
		return builder.build();
	}

	@Override
	public Collection<Material> getMaterials(IGeometryBakingContext context,
			Function<ResourceLocation, UnbakedModel> modelGetter, Set<Pair<String, String>> missingTextureErrors) 
	{
		if(textures != null)
			return textures;
		
		ImmutableList.Builder<Material> builder = ImmutableList.builder();
		if(context.hasMaterial("particle"))
			builder.add(context.getMaterial("particle"));
		for(int i = 0; context.hasMaterial("layer" + i); i++)
			builder.add(context.getMaterial("layer" + i));

		if(context.hasMaterial("coating"))
		{
			coatingTexture = context.getMaterial("coating");
			builder.add(coatingTexture);
		}
		textures = builder.build();
		return textures;
	}
	
	public static class Loader implements IGeometryLoader<OilCoatedItemModel>
	{
		public static final Loader INSTANCE = new Loader();

		@Override
		public OilCoatedItemModel read(JsonObject jsonObject, JsonDeserializationContext deserializationContext)
				throws JsonParseException 
		{
			Int2ObjectOpenHashMap<ResourceLocation> renderTypeNames = new Int2ObjectOpenHashMap<ResourceLocation>();
			if(jsonObject.has("render_types"))
			{
				JsonObject renderTypes = jsonObject.getAsJsonObject("render_types");
				for(Map.Entry<String, JsonElement> entry : renderTypes.entrySet())
				{
					ResourceLocation renderType = new ResourceLocation(entry.getKey());
					for(JsonElement layer : entry.getValue().getAsJsonArray())
					{
						if(renderTypeNames.put(layer.getAsInt(), renderType) != null)
							throw new JsonParseException("Duplicate render type for layer " + layer);
					}
				}
			}
			
			IntOpenHashSet emissiveLayers = new IntOpenHashSet();
			
			JsonArray emissiveLayersJson = jsonObject.has("emissive_layers") ? jsonObject.getAsJsonArray("emissive_layers") : new JsonArray();
			ResourceLocation renderType = new ResourceLocation("forge", "item_unlit");
			for(JsonElement emissiveLayer : emissiveLayersJson)
			{
				int layerIdx = emissiveLayer.getAsInt();
				emissiveLayers.add(layerIdx);
				renderTypeNames.putIfAbsent(layerIdx, renderType);
			}
			
			return new OilCoatedItemModel(null, null, emissiveLayers, renderTypeNames);
		}
	}
}

/*import java.util.Collection;
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
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.client.model.geometry.IModelGeometry;
import net.minecraftforge.client.model.geometry.IUnbakedGeometry;

public class OilCoatedItemModel implements IUnbakedGeometry<OilCoatedItemModel> 
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
	public BakedModel bake(IGeometryBakingContext owner, ModelBakery bakery,
			Function<Material, TextureAtlasSprite> spriteGetter, ModelState modelTransform, ItemOverrides overrides,
			ResourceLocation modelLocation)
	{
		ImmutableMap<ItemTransforms.TransformType, Transformation> transformMap = PerspectiveMapWrapper.getTransforms(new CompositeModelState(owner.getCombinedTransform(), modelTransform));
		Transformation transform = modelTransform.getRotation();
		TextureAtlasSprite particleSprite = spriteGetter.apply(owner.hasMaterial("particle") ? owner.getMaterial("particle") : textures.get(0));
		
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
	public Collection<Material> getMaterials(IGeometryBakingContext owner,
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

}*/

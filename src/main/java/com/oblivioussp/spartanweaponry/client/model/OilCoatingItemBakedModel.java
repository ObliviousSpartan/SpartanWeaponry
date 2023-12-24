package com.oblivioussp.spartanweaponry.client.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.mojang.math.Transformation;
import com.oblivioussp.spartanweaponry.capability.IOilHandler;
import com.oblivioussp.spartanweaponry.init.ModCapabilities;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.block.model.ItemTransforms.TransformType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.model.BakedItemModel;
import net.minecraftforge.client.model.IModelConfiguration;
import net.minecraftforge.client.model.ItemMultiLayerBakedModel;
import net.minecraftforge.common.util.LazyOptional;

public class OilCoatingItemBakedModel extends ItemMultiLayerBakedModel 
{
	private final ImmutableList<Pair<BakedModel, RenderType>> coatedLayerModels;
	
	public OilCoatingItemBakedModel(boolean smoothLighting, boolean shadedInGui, boolean sideLit,
			TextureAtlasSprite particle, ItemOverrides overrides,
			ImmutableMap<TransformType, Transformation> cameraTransforms,
			ImmutableList<Pair<BakedModel, RenderType>> layerModels,
			Pair<BakedModel, RenderType> coatingLayerModel)
	{
		super(smoothLighting, shadedInGui, sideLit, particle, overrides, cameraTransforms, layerModels);
		ImmutableList.Builder<Pair<BakedModel, RenderType>> coatedBuilder = ImmutableList.builder();
		coatedBuilder.addAll(layerModels);
		coatedBuilder.add(coatingLayerModel);
		coatedLayerModels = coatedBuilder.build();
	}
	
	@Override
	public List<Pair<BakedModel, RenderType>> getLayerModels(ItemStack itemStack, boolean fabulous) 
	{
		LazyOptional<IOilHandler> lazyHandler = itemStack.getCapability(ModCapabilities.OIL_CAPABILITY);
		return lazyHandler.isPresent() && lazyHandler.resolve().get().isOiled() ? coatedLayerModels : super.getLayerModels(itemStack, fabulous);
	}
	
	public static Builder makeBuilder(IModelConfiguration ownerIn, TextureAtlasSprite particleTextureIn, ItemOverrides overridesIn,
				ImmutableMap<ItemTransforms.TransformType, Transformation> cameraTransformsIn)
	{
		return new Builder(ownerIn, particleTextureIn, overridesIn, cameraTransformsIn);
	}

	public static class Builder
	{
		private final ImmutableList.Builder<Pair<BakedModel, RenderType>> layerBuilder = ImmutableList.builder();
		private final List<BakedQuad> quads = new ArrayList<>();
		private final ItemOverrides overrides;
		private final ImmutableMap<ItemTransforms.TransformType, Transformation> cameraTransforms;
		private final IModelConfiguration owner;
		private TextureAtlasSprite particleTexture;
		private RenderType lastRenderType;
		private Pair<BakedModel, RenderType> coatedLayer;
		
		private Builder(IModelConfiguration ownerIn, TextureAtlasSprite particleTextureIn, ItemOverrides overridesIn,
				ImmutableMap<ItemTransforms.TransformType, Transformation> cameraTransformsIn)
		{
			owner = ownerIn;
			particleTexture = particleTextureIn;
			overrides = overridesIn;
			cameraTransforms = cameraTransformsIn;
		}
		
		private void addLayer(ImmutableList.Builder<Pair<BakedModel, RenderType>> layerBuilderIn, List<BakedQuad> quadsIn, RenderType renderTypeIn)
		{
			BakedModel model = new BakedItemModel(ImmutableList.copyOf(quadsIn), particleTexture, ImmutableMap.of(), ItemOverrides.EMPTY, true, owner.isSideLit());
			layerBuilderIn.add(Pair.of(model, renderTypeIn));
		}
		
		private void flushQuads(RenderType renderTypeIn)
		{
			if(renderTypeIn != lastRenderType)
			{
				if(quads.size() > 0)
				{
					addLayer(layerBuilder, quads, lastRenderType);
					quads.clear();
				}
				lastRenderType = renderTypeIn;
			}
		}
		
		public Builder addQuads(RenderType renderTypeIn, Collection<BakedQuad> quadsIn)
		{
			flushQuads(renderTypeIn);
			quads.addAll(quadsIn);
			return this;
		}
		
		public Builder addCoatingQuads(RenderType renderTypeIn, Collection<BakedQuad> quadsIn)
		{
			if(coatedLayer == null)
			{
				BakedModel coatingModel = new BakedItemModel(ImmutableList.copyOf(quadsIn), particleTexture, ImmutableMap.of(), ItemOverrides.EMPTY, true, owner.isSideLit());
				coatedLayer = Pair.of(coatingModel, renderTypeIn);
			}
			else
				Log.error("Failed to add coating quads for model '" + owner.getModelName() + "'; Coating quads have already been added!");
			return this;
		}
		
		public OilCoatingItemBakedModel build()
		{
			if(quads.size() > 0)
			{
				addLayer(layerBuilder, quads, lastRenderType);
			}
			return new OilCoatingItemBakedModel(owner.useSmoothLighting(), owner.isShadedInGui(), owner.isSideLit(), particleTexture, overrides, cameraTransforms, layerBuilder.build(), coatedLayer);
		}
	}
}

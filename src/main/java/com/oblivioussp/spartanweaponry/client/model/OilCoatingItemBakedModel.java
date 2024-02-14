package com.oblivioussp.spartanweaponry.client.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.oblivioussp.spartanweaponry.capability.IOilHandler;
import com.oblivioussp.spartanweaponry.init.ModCapabilities;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.client.RenderTypeGroup;
import net.minecraftforge.client.model.CompositeModel;
import net.minecraftforge.client.model.IModelBuilder;
import net.minecraftforge.client.model.geometry.IGeometryBakingContext;
import net.minecraftforge.common.util.LazyOptional;

public class OilCoatingItemBakedModel extends CompositeModel.Baked
{
	private final ImmutableList<BakedModel> coatedLayerModels;

	public OilCoatingItemBakedModel(boolean isGui3d, boolean isSideLit, boolean isAmbientOcclusion,
			TextureAtlasSprite particle, ItemTransforms transforms, ItemOverrides overrides,
			ImmutableMap<String, BakedModel> children, ImmutableList<BakedModel> itemPasses,
			ImmutableList<BakedModel> coatedLayerModelsIn) 
	{
		super(isGui3d, isSideLit, isAmbientOcclusion, particle, transforms, overrides, children, itemPasses);
		coatedLayerModels = coatedLayerModelsIn;
	}
	
	@Override
	public List<BakedModel> getRenderPasses(ItemStack itemStack, boolean fabulous) 
	{
		LazyOptional<IOilHandler> lazyHandler = itemStack.getCapability(ModCapabilities.OIL_CAPABILITY);
		return lazyHandler.isPresent() && lazyHandler.resolve().get().isOiled() ? coatedLayerModels : super.getRenderPasses(itemStack, fabulous);
	}
	
	public static Builder makeBuilder(IGeometryBakingContext contextIn, TextureAtlasSprite particleIn, ItemOverrides overridesIn, ItemTransforms transformsIn)
	{
		return new Builder(contextIn.getModelName(), contextIn.useAmbientOcclusion(), contextIn.isGui3d(), contextIn.useBlockLight(), particleIn, overridesIn, transformsIn);
	}
	
	public static class Builder
	{
		private final String name;
		private final boolean isAmbientOcclusion, 
							isGui3d, 
							isSideLit;
		private final List<BakedModel> children = new ArrayList<>();
		private final List<BakedQuad> quads = new ArrayList<>();
		private final List<BakedModel> coatedModel = new ArrayList<>();
		private final ItemOverrides overrides;
		private final ItemTransforms transforms;
		private TextureAtlasSprite particle;
		private RenderTypeGroup lastRenderType = RenderTypeGroup.EMPTY;
		
		private Builder(String nameIn, boolean isAmbientOcclusionIn, boolean isGui3dIn, boolean isSideLitIn, TextureAtlasSprite particleIn, ItemOverrides overridesIn, ItemTransforms transformsIn)
		{
			name = nameIn;
			isAmbientOcclusion = isAmbientOcclusionIn;
			isGui3d = isGui3dIn;
			isSideLit = isSideLitIn;
			overrides = overridesIn;
			transforms = transformsIn;
			particle = particleIn;
		}
		
		private void addChildrenLayer(RenderTypeGroup renderTypeIn, List<BakedQuad> quadsIn)
		{
			IModelBuilder<?> modelBuilder = IModelBuilder.of(isAmbientOcclusion, isSideLit, isGui3d, transforms, overrides, particle, renderTypeIn);
			quadsIn.forEach(modelBuilder::addUnculledFace);
			children.add(modelBuilder.build());
		}
		
		private void addCoatedLayer(RenderTypeGroup renderTypeIn, List<BakedQuad> quadsIn)
		{
			IModelBuilder<?> modelBuilder = IModelBuilder.of(isAmbientOcclusion, isSideLit, isGui3d, transforms, overrides, particle, renderTypeIn);
			quadsIn.forEach(modelBuilder::addUnculledFace);
			coatedModel.add(modelBuilder.build());
		}
		
		private void flushChildrenQuads(RenderTypeGroup renderTypeIn)
		{
			if(!Objects.equals(renderTypeIn, lastRenderType))
			{
				if(quads.size() > 0) 
				{
					addChildrenLayer(lastRenderType, quads);
					quads.clear();
				}
				lastRenderType = renderTypeIn;
			}
		}
		
		public Builder addQuads(RenderTypeGroup renderTypeIn, List<BakedQuad> quadsIn)
		{
			flushChildrenQuads(renderTypeIn);
			quads.addAll(quadsIn);
			return this;
		}
		
		public Builder addCoatedQuads(RenderTypeGroup renderTypeIn, List<BakedQuad> quadsIn)
		{
			if(!coatedModel.isEmpty())
				Log.error("Failed to add coating quads for model '" + name + "'; Coating quads have already been added!");
			else
			{
//				flushChildrenQuads(renderType);
				if(quads.size() > 0)
				{
					addChildrenLayer(lastRenderType, quads);
					quads.clear();
				}
				addCoatedLayer(renderTypeIn, quadsIn);
				lastRenderType = renderTypeIn;
			}
			return this;
		}
		
		public BakedModel build()
		{
			if(coatedModel.isEmpty() && quads.size() > 0)
				addChildrenLayer(lastRenderType, quads);
			
			ImmutableMap.Builder<String, BakedModel> childrenBuilder = ImmutableMap.<String, BakedModel>builder();
			ImmutableList.Builder<BakedModel> itemPassesBuilder = ImmutableList.<BakedModel>builder();
			ImmutableList.Builder<BakedModel> coatedModelBuilder = ImmutableList.<BakedModel>builder();
			int i = 0;
			for(BakedModel model : children)
			{
				childrenBuilder.put("model_" + (i++), model);
				itemPassesBuilder.add(model);
			}
			if(!coatedModel.isEmpty())
			{
				coatedModelBuilder.addAll(children);
				coatedModelBuilder.addAll(coatedModel);
			}
			return new OilCoatingItemBakedModel(isGui3d, isSideLit, isAmbientOcclusion, particle, transforms, overrides,
					childrenBuilder.build(), itemPassesBuilder.build(), coatedModelBuilder.build());
		}
	}
}

/*import java.util.ArrayList;
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
}*/

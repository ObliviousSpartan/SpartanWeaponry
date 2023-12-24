package com.oblivioussp.spartanweaponry.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.client.renderer.entity.ModelLayers;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.client.CuriosRendererRegistry;
import top.theillusivec4.curios.api.client.ICurioRenderer;

public class CurioRenderer implements ResourceManagerReloadListener
{
	public static final CurioRenderer INSTANCE = new CurioRenderer();
	
	public static SmallArrowQuiverModel smallArrowQuiverModel;
	protected static final ResourceLocation smallArrowQuiverTexture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_arrow_small.png");
	public static MediumArrowQuiverModel mediumArrowQuiverModel;
	protected static final ResourceLocation mediumArrowQuiverTexture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_arrow_medium.png");
	public static LargeArrowQuiverModel largeArrowQuiverModel;
	protected static final ResourceLocation largeArrowQuiverTexture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_arrow_large.png");
	protected static final ResourceLocation hugeArrowQuiverTexture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_arrow_huge.png");
	
	public static SmallBoltQuiverModel smallBoltQuiverModel;
	protected static final ResourceLocation smallBoltQuiverTexture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_small.png");
	public static MediumBoltQuiverModel mediumBoltQuiverModel;
	protected static final ResourceLocation mediumBoltQuiverTexture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_medium.png");
	public static LargeBoltQuiverModel largeBoltQuiverModel;
	protected static final ResourceLocation largeBoltQuiverTexture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_large.png");
	protected static final ResourceLocation hugeBoltQuiverTexture = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_huge.png");
	
	public static final Renderer QUIVER_ARROW_SMALL_RENDERER = INSTANCE.new Renderer(smallArrowQuiverTexture);
	public static final Renderer QUIVER_ARROW_MEDIUM_RENDERER = INSTANCE.new Renderer(mediumArrowQuiverTexture);
	public static final Renderer QUIVER_ARROW_LARGE_RENDERER = INSTANCE.new Renderer(largeArrowQuiverTexture);
	public static final Renderer QUIVER_ARROW_HUGE_RENDERER = INSTANCE.new Renderer(hugeArrowQuiverTexture);
	
	public static final Renderer QUIVER_BOLT_SMALL_RENDERER = INSTANCE.new Renderer(smallBoltQuiverTexture);
	public static final Renderer QUIVER_BOLT_MEDIUM_RENDERER = INSTANCE.new Renderer(mediumBoltQuiverTexture);
	public static final Renderer QUIVER_BOLT_LARGE_RENDERER = INSTANCE.new Renderer(largeBoltQuiverTexture);
	public static final Renderer QUIVER_BOLT_HUGE_RENDERER = INSTANCE.new Renderer(hugeBoltQuiverTexture);
	
	private CurioRenderer() {}
	
	public static void register()
	{
		CuriosRendererRegistry.register(ModItems.SMALL_ARROW_QUIVER.get(), () -> QUIVER_ARROW_SMALL_RENDERER);
		CuriosRendererRegistry.register(ModItems.MEDIUM_ARROW_QUIVER.get(), () -> QUIVER_ARROW_MEDIUM_RENDERER);
		CuriosRendererRegistry.register(ModItems.LARGE_ARROW_QUIVER.get(), () -> QUIVER_ARROW_LARGE_RENDERER);
		CuriosRendererRegistry.register(ModItems.HUGE_ARROW_QUIVER.get(), () -> QUIVER_ARROW_HUGE_RENDERER);
		
		CuriosRendererRegistry.register(ModItems.SMALL_BOLT_QUIVER.get(), () -> QUIVER_BOLT_SMALL_RENDERER);
		CuriosRendererRegistry.register(ModItems.MEDIUM_BOLT_QUIVER.get(), () -> QUIVER_BOLT_MEDIUM_RENDERER);
		CuriosRendererRegistry.register(ModItems.LARGE_BOLT_QUIVER.get(), () -> QUIVER_BOLT_LARGE_RENDERER);
		CuriosRendererRegistry.register(ModItems.HUGE_BOLT_QUIVER.get(), () -> QUIVER_BOLT_HUGE_RENDERER);
	}

	@Override
	public void onResourceManagerReload(ResourceManager p_10758_) 
	{
		Minecraft mc = Minecraft.getInstance();
		smallArrowQuiverModel = new SmallArrowQuiverModel(mc.getEntityModels().bakeLayer(ModelLayers.SMALL_ARROW_QUIVER));
		mediumArrowQuiverModel = new MediumArrowQuiverModel(mc.getEntityModels().bakeLayer(ModelLayers.MEDIUM_ARROW_QUIVER));
		largeArrowQuiverModel = new LargeArrowQuiverModel(mc.getEntityModels().bakeLayer(ModelLayers.LARGE_ARROW_QUIVER));
		QUIVER_ARROW_SMALL_RENDERER.setModel(smallArrowQuiverModel);
		QUIVER_ARROW_MEDIUM_RENDERER.setModel(mediumArrowQuiverModel);
		QUIVER_ARROW_LARGE_RENDERER.setModel(largeArrowQuiverModel);
		QUIVER_ARROW_HUGE_RENDERER.setModel(largeArrowQuiverModel);
		
		smallBoltQuiverModel = new SmallBoltQuiverModel(mc.getEntityModels().bakeLayer(ModelLayers.SMALL_BOLT_QUIVER));
		mediumBoltQuiverModel = new MediumBoltQuiverModel(mc.getEntityModels().bakeLayer(ModelLayers.MEDIUM_BOLT_QUIVER));
		largeBoltQuiverModel = new LargeBoltQuiverModel(mc.getEntityModels().bakeLayer(ModelLayers.LARGE_BOLT_QUIVER));
		QUIVER_BOLT_SMALL_RENDERER.setModel(smallBoltQuiverModel);
		QUIVER_BOLT_MEDIUM_RENDERER.setModel(mediumBoltQuiverModel);
		QUIVER_BOLT_LARGE_RENDERER.setModel(largeBoltQuiverModel);
		QUIVER_BOLT_HUGE_RENDERER.setModel(largeBoltQuiverModel);
	}
	
	private class Renderer implements ICurioRenderer
	{
		private QuiverModelBase model;
		private ResourceLocation texture;
		
		public Renderer(ResourceLocation texture)
		{
			this.texture = texture;
		}
		
		public void setModel(QuiverModelBase model)
		{
			this.model = model;
		}
		
		@Override
		public <T extends LivingEntity, M extends EntityModel<T>> void render(ItemStack stack, SlotContext slotContext,
				PoseStack matrixStack, RenderLayerParent<T, M> renderLayerParent, MultiBufferSource renderTypeBuffer,
				int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks,
				float netHeadYaw, float headPitch) 
		{
			int arrowsToRender = 0;
			if(stack.getItem() instanceof QuiverBaseItem)
				arrowsToRender = ((QuiverBaseItem)stack.getItem()).getAmmoCount(stack);
			
			if(model != null)
				renderQuiverModel(model, texture, arrowsToRender, slotContext, 
					matrixStack, renderTypeBuffer, light);
		}
		
		private void renderQuiverModel(QuiverModelBase model, ResourceLocation texture, int arrowsToRender, SlotContext slotContext, PoseStack mStack, 
				MultiBufferSource renderTypeBuffer, int packedLight)
		{
			VertexConsumer buffer = renderTypeBuffer.getBuffer(model.renderType(texture));
			
			ICurioRenderer.translateIfSneaking(mStack, slotContext.entity());
			ICurioRenderer.rotateIfSneaking(mStack, slotContext.entity());
			model.renderToBuffer(mStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
			model.renderArrows(arrowsToRender, mStack, buffer, packedLight, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
		}
	}

}

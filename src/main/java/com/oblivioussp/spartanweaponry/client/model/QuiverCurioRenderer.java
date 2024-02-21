package com.oblivioussp.spartanweaponry.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.items.IItemHandlerModifiable;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class QuiverCurioRenderer implements ICurio 
{
	protected final ItemStack quiverStack;
	protected final QuiverBaseItem quiverItem;
	protected final QuiverModelBase model;
	protected final ResourceLocation texture;
	
	public QuiverCurioRenderer(QuiverBaseItem item, ItemStack stack)
	{
		quiverItem = item;
		quiverStack = stack;
		if(FMLEnvironment.dist.isClient())
		{
			model = item.getModel();
			texture = item.getModelTexture();
		}
		else
		{
			model = null;
			texture = null;
		}
	}
	
	@Override
	public boolean canSync(String identifier, int index, LivingEntity livingEntity) 
	{
		IItemHandlerModifiable curiosHandler = CuriosApi.getCuriosHelper().getEquippedCurios(livingEntity).orElse(null);
		if(curiosHandler == null)
			return false;
		
		ItemStack curioInSlot = curiosHandler.getStackInSlot(index);
		return quiverStack.getItem() == curioInSlot.getItem() && !quiverStack.areShareTagsEqual(curioInSlot);
	}
	
	@Override
	public void readSyncData(CompoundNBT compound)
	{
		quiverItem.readShareTag(quiverStack, compound);
	}
	
	@Override
	public CompoundNBT writeSyncData()
	{
		return quiverItem.getShareTag(quiverStack);
	}

	@Override
	public boolean canRender(String identifier, int index, LivingEntity livingEntity)
	{
		return true;
	}

	@Override
	public void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer renderTypeBuffer,
			int light, LivingEntity livingEntity, float limbSwing, float limbSwingAmount, float partialTicks,
			float ageInTicks, float netHeadYaw, float headPitch) 
	{
		int ammoFilled = quiverItem.getAmmoCount(quiverStack);
		if(model != null) 
		{
			model.setArrowsToRender(ammoFilled);
			
			ICurio.RenderHelper.translateIfSneaking(matrixStack, livingEntity);
			ICurio.RenderHelper.rotateIfSneaking(matrixStack, livingEntity);
			
			model.render(matrixStack, renderTypeBuffer.getBuffer(model.getRenderType(texture)), light, OverlayTexture.NO_OVERLAY, 1.0f, 1.0f, 1.0f, 1.0f);
		}
	}
}

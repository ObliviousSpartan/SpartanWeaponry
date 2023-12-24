package com.oblivioussp.spartanweaponry.proxy;

import com.oblivioussp.spartanweaponry.api.ToolMaterialEx;
import com.oblivioussp.spartanweaponry.client.KeyBinds;
import com.oblivioussp.spartanweaponry.client.render.projectile.RenderFactoryArrowSW;
import com.oblivioussp.spartanweaponry.client.render.projectile.RenderFactoryBolt;
import com.oblivioussp.spartanweaponry.client.render.projectile.RenderFactoryBoomerang;
import com.oblivioussp.spartanweaponry.client.render.projectile.RenderFactoryThrowingAxe;
import com.oblivioussp.spartanweaponry.client.render.projectile.RenderFactoryThrowingKnife;
import com.oblivioussp.spartanweaponry.client.render.projectile.RenderFactoryThrownJavelin;
import com.oblivioussp.spartanweaponry.client.render.projectile.RenderFactoryThrownWeapon;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityArrowDiamond;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityArrowExplosive;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityArrowIron;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityArrowWood;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoltDiamond;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoltSpectral;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoltTipped;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityBoomerang;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityDynamite;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrowingAxe;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrowingKnife;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownJavelin;
import com.oblivioussp.spartanweaponry.entity.projectile.EntityThrownWeapon;
import com.oblivioussp.spartanweaponry.init.ItemRegistrySW;
import com.oblivioussp.spartanweaponry.init.ModelRenderRegistry;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderSnowball;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class ClientProxy extends CommonProxy
{

	@Override
	public void preInit(FMLPreInitializationEvent ev) 
	{
		//ModelRenderRegistry.registerItemRenders();
		//ModelRenderRegistry.registerBlockRenders();
		ClientRegistry.registerKeyBinding(KeyBinds.KEY_ACCESS_QUIVER);
		
		RenderingRegistry.registerEntityRenderingHandler(EntityBoltTipped.class, new RenderFactoryBolt<EntityBoltTipped>());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoltSpectral.class, new RenderFactoryBolt<EntityBoltSpectral>());
		RenderingRegistry.registerEntityRenderingHandler(EntityThrownWeapon.class, new RenderFactoryThrownWeapon<EntityThrownWeapon>());
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowingKnife.class, new RenderFactoryThrowingKnife());
		RenderingRegistry.registerEntityRenderingHandler(EntityThrowingAxe.class, new RenderFactoryThrowingAxe<EntityThrowingAxe>());
		RenderingRegistry.registerEntityRenderingHandler(EntityThrownJavelin.class, new RenderFactoryThrownJavelin());
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowWood.class, new RenderFactoryArrowSW());
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowIron.class, new RenderFactoryArrowSW());
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowDiamond.class, new RenderFactoryArrowSW());
		RenderingRegistry.registerEntityRenderingHandler(EntityArrowExplosive.class, new RenderFactoryArrowSW());
		RenderingRegistry.registerEntityRenderingHandler(EntityBoomerang.class, new RenderFactoryBoomerang());
		RenderingRegistry.registerEntityRenderingHandler(EntityDynamite.class, new IRenderFactory<EntityDynamite>() {

			@Override
			public Render<? super EntityDynamite> createRenderFor(RenderManager manager) 
			{
				return new RenderSnowball(manager, ItemRegistrySW.dynamite, Minecraft.getMinecraft().getRenderItem());
			}
			
		});
		RenderingRegistry.registerEntityRenderingHandler(EntityBoltDiamond.class, new RenderFactoryBolt<EntityBoltDiamond>());
	}

	@Override
	public void init(FMLInitializationEvent ev) 
	{
		// Register Custom colours for items here
		
		// Tipped Bolts custom colours
		if(!ConfigHandler.disableCrossbow)
			registerTippedProjectileColourHandler(ItemRegistrySW.boltTipped);
		if(!ConfigHandler.disableNewArrows)
		{
			registerTippedProjectileColourHandler(ItemRegistrySW.arrowTippedWood);
			registerTippedProjectileColourHandler(ItemRegistrySW.arrowTippedIron);
			if(!ConfigHandler.disableDiamondArrowsAndBolts)
			{
				registerTippedProjectileColourHandler(ItemRegistrySW.arrowTippedDiamond);
				registerTippedProjectileColourHandler(ItemRegistrySW.boltTippedDiamond);
			}
		}
		
		//registerPotionColourHandler(ItemRegistrySW.weaponOil);
		
		ModelRenderRegistry.registerColourHandlers();
	}

	@Override
	public void postInit(FMLPostInitializationEvent ev) 
	{
	}
	
	protected void registerTippedProjectileColourHandler(Item item)
	{
		FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new IItemColor()
		{
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) 
			{
				PotionType potion = PotionUtils.getPotionFromItem(stack);
				if(potion == null)
					return 0xFFFFFF;
				
				return tintIndex == 1 ? PotionUtils.getPotionColor(potion) : 0xFFFFFF;
			}
		}
		, item);
	}
	
	protected void registerPotionColourHandler(Item item)
	{
		FMLClientHandler.instance().getClient().getItemColors().registerItemColorHandler(new IItemColor()
		{
			@Override
			public int colorMultiplier(ItemStack stack, int tintIndex) 
			{
				PotionType potion = PotionUtils.getPotionFromItem(stack);
				if(potion == null)
					return 0xFFFFFF;
				
				return tintIndex == 0 ? PotionUtils.getPotionColor(potion) : 0xFFFFFF;
			}
		}
		, item);
	}

	@Override
	public void addColourHandler(Item item, ToolMaterialEx material)
	{
		ModelRenderRegistry.addItemToColourHandler(item, material);
	}

}

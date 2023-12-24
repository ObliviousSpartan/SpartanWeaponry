package com.oblivioussp.spartanweaponry.client;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.ModelOverrides;
import com.oblivioussp.spartanweaponry.block.ExtendedSkullBlock;
import com.oblivioussp.spartanweaponry.client.gui.container.QuiverArrowScreen;
import com.oblivioussp.spartanweaponry.client.gui.container.QuiverBoltScreen;
import com.oblivioussp.spartanweaponry.client.renderer.entity.ArrowBaseRenderFactory;
import com.oblivioussp.spartanweaponry.client.renderer.entity.BoltRenderFactory;
import com.oblivioussp.spartanweaponry.client.renderer.entity.BoomerangRenderFactory;
import com.oblivioussp.spartanweaponry.client.renderer.entity.EndermanHeadModel;
import com.oblivioussp.spartanweaponry.client.renderer.entity.HeadwearHeadModel;
import com.oblivioussp.spartanweaponry.client.renderer.entity.IllagerHeadModel;
import com.oblivioussp.spartanweaponry.client.renderer.entity.JavelinRenderFactory;
import com.oblivioussp.spartanweaponry.client.renderer.entity.PiglinHeadModel;
import com.oblivioussp.spartanweaponry.client.renderer.entity.SimpleArrowRenderFactory;
import com.oblivioussp.spartanweaponry.client.renderer.entity.ThrowingWeaponRenderFactory;
import com.oblivioussp.spartanweaponry.client.renderer.entity.TomahawkRenderFactory;
import com.oblivioussp.spartanweaponry.client.renderer.entity.WitchHeadModel;
import com.oblivioussp.spartanweaponry.entity.projectile.ArrowBaseEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ArrowExplosiveEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltSpectralEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.DynamiteEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.JavelinEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.TomahawkEntity;
import com.oblivioussp.spartanweaponry.init.ModContainers;
import com.oblivioussp.spartanweaponry.init.ModEntities;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.init.ModTileEntities;
import com.oblivioussp.spartanweaponry.item.HeavyCrossbowItem;
import com.oblivioussp.spartanweaponry.item.LongbowItem;
import com.oblivioussp.spartanweaponry.item.QuiverBaseItem;
import com.oblivioussp.spartanweaponry.item.SwordBaseItem;
import com.oblivioussp.spartanweaponry.item.ThrowingWeaponItem;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraft.client.renderer.entity.model.GenericHeadModel;
import net.minecraft.client.renderer.tileentity.SkullTileEntityRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.client.registry.IRenderFactory;
import net.minecraftforge.fml.client.registry.RenderingRegistry;

@OnlyIn(Dist.CLIENT)
public class ClientHelper 
{
	
	public static void registerItemRenders()
	{
		// Register item property overrides here
		//DeferredWorkQueue.runLater(() -> {
			// TODO: Not type safe, implement it better later.
			//registerPropertyOverrides();
			
			//LongbowItem.registerPropertyOverrides((LongbowItem) ModItems.longbows.wood);
		//});
		
		registerTippedProjectile(ModItems.tippedArrowWood);
		registerTippedProjectile(ModItems.tippedArrowIron);
		registerTippedProjectile(ModItems.tippedArrowDiamond);
		registerTippedProjectile(ModItems.tippedBolt);
		registerTippedProjectile(ModItems.tippedBoltDiamond);
		/*Minecraft.getInstance().getItemColors().register((stack, idx) ->
		{
			WeaponOilEffect oil = WeaponOilItem.getOilFromStack(stack);
			return idx == 0 ? oil != null ? oil.getColor() : 0xFFFFFF : 0xFFFFFF;
		}, ModItems.weaponOil);*/
	}
	
	public static void registerBlockablePropertyOverrides(SwordBaseItem parryingDagger)
	{
		ItemModelsProperties.registerProperty(parryingDagger, new ResourceLocation(ModelOverrides.BLOCKING), (stack, world, living) ->
		{
			return living != null && living.isHandActive() && living.getActiveItemStack() == stack ? 1.0f : 0.0f;
		});
	}

	public static void registerHeavyCrossbowPropertyOverrides(HeavyCrossbowItem crossbow)
	{
		ItemModelsProperties.registerProperty(crossbow, new ResourceLocation(ModelOverrides.PULL), (stack, world, living) ->
		{
			if(living != null /*&& stack.getItem() == crossbow*/)
				return crossbow.isLoaded(stack) ? 0.0f : (float)(crossbow.getLoadingTicks(stack, living)) / crossbow.getFullLoadTicks(stack);
			return 0.0f;
		});
		ItemModelsProperties.registerProperty(crossbow, new ResourceLocation(ModelOverrides.PULLING), (stack, world, living) ->
		{
			return living != null && living.isHandActive() && living.getActiveItemStack() == stack ? 1.0f : 0.0f;
		});
		ItemModelsProperties.registerProperty(crossbow, new ResourceLocation(ModelOverrides.CHARGED), (stack, world, living) ->
		{
			return crossbow.isLoaded(stack) ? 1.0f : 0.0f;
		});
	}
	
	public static void registerLongbowPropertyOverrides(LongbowItem longbow)
	{
		ItemModelsProperties.registerProperty(longbow, new ResourceLocation(ModelOverrides.PULLING), (stack, world, living) ->
		{
			return living != null && living.isHandActive() && living.getActiveItemStack() == stack ? 1.0f : 0.0f;
		});
		ItemModelsProperties.registerProperty(longbow, new ResourceLocation(ModelOverrides.PULL), (stack, world, shooter) -> 
		{
			return shooter != null && shooter.getActiveItemStack() == stack ? longbow.getNockProgress(stack, shooter) : 0.0f;
		});
	}
	
	public static void registerThrowingWeaponPropertyOverrides(Item throwingWeapon)
	{
//		Log.debug("Registering Throwing Weapon Property Overrides for item: \"" + throwingWeapon.getRegistryName().toString() + "\"");
		ItemModelsProperties.registerProperty(throwingWeapon, new ResourceLocation(ModelOverrides.THROWING), (stack, world, living) ->
		{
			if(living == null || !stack.isItemEqual(living.getActiveItemStack()))	return 0.0f;
			return living.getItemInUseCount() > 0 ? 1.0f : 0.0f;
		});
		if(throwingWeapon instanceof ThrowingWeaponItem)
		{
			ItemModelsProperties.registerProperty(throwingWeapon, new ResourceLocation(ModelOverrides.EMPTY), (stack, world, living) ->
			{
				return stack.getOrCreateTag().getInt(ThrowingWeaponItem.NBT_AMMO_USED) == ((ThrowingWeaponItem)throwingWeapon).getMaxAmmo(stack) ? 1 : 0;
			});
		}
	}
	
	public static void registerQuiverPropertyOverrides(QuiverBaseItem quiver)
	{
		ItemModelsProperties.registerProperty(quiver, new ResourceLocation(ModelOverrides.ARROW), (stack, world, living) ->
		{
			return quiver.getAmmoCount(stack);
		});
	}
	
	public static void registerTippedProjectile(Item arrow)
	{
		Minecraft.getInstance().getItemColors().register((stack, idx) -> 
		{
			return idx == 1 ? PotionUtils.getColor(stack) : 0xFFFFFF;
		}, arrow);
	}
	
	public static void registerExtendedSkullRenders()
	{
		GenericHeadModel genericHeadModel = new GenericHeadModel(0, 0, 64, 32);
//		GenericHeadModel humanoidHeadModel = new HumanoidHeadModel();
		GenericHeadModel endermanHeadModel = new EndermanHeadModel();
		GenericHeadModel spiderHeadModel = new GenericHeadModel(32, 4, 64, 32);
		GenericHeadModel piglinModel = new PiglinHeadModel();
		GenericHeadModel huskHeadModel = new GenericHeadModel(0, 0, 64, 64);
		GenericHeadModel headwearHeadModel = new HeadwearHeadModel();
		GenericHeadModel illagerHeadModel = new IllagerHeadModel();
		GenericHeadModel witchHeadModel = new WitchHeadModel();
		SkullTileEntityRenderer.MODELS.put(ExtendedSkullBlock.Types.BLAZE, genericHeadModel);
		SkullTileEntityRenderer.MODELS.put(ExtendedSkullBlock.Types.ENDERMAN, endermanHeadModel);
		SkullTileEntityRenderer.MODELS.put(ExtendedSkullBlock.Types.SPIDER, spiderHeadModel);
		SkullTileEntityRenderer.MODELS.put(ExtendedSkullBlock.Types.CAVE_SPIDER, spiderHeadModel);
		SkullTileEntityRenderer.MODELS.put(ExtendedSkullBlock.Types.PIGLIN, piglinModel);
		SkullTileEntityRenderer.MODELS.put(ExtendedSkullBlock.Types.ZOMBIE_PIGLIN, piglinModel);
		SkullTileEntityRenderer.MODELS.put(ExtendedSkullBlock.Types.HUSK, huskHeadModel);
		SkullTileEntityRenderer.MODELS.put(ExtendedSkullBlock.Types.STRAY, headwearHeadModel);
		SkullTileEntityRenderer.MODELS.put(ExtendedSkullBlock.Types.DROWNED, headwearHeadModel);
		SkullTileEntityRenderer.MODELS.put(ExtendedSkullBlock.Types.ILLAGER, illagerHeadModel);
		SkullTileEntityRenderer.MODELS.put(ExtendedSkullBlock.Types.WITCH, witchHeadModel);
		
		SkullTileEntityRenderer.SKINS.put(ExtendedSkullBlock.Types.BLAZE, new ResourceLocation("minecraft", "textures/entity/blaze.png"));
		SkullTileEntityRenderer.SKINS.put(ExtendedSkullBlock.Types.ENDERMAN, new ResourceLocation(ModSpartanWeaponry.ID, "textures/entity/skull/enderman_head.png"));
		SkullTileEntityRenderer.SKINS.put(ExtendedSkullBlock.Types.SPIDER, new ResourceLocation("minecraft", "textures/entity/spider/spider.png"));
		SkullTileEntityRenderer.SKINS.put(ExtendedSkullBlock.Types.CAVE_SPIDER, new ResourceLocation("minecraft", "textures/entity/spider/cave_spider.png"));
		SkullTileEntityRenderer.SKINS.put(ExtendedSkullBlock.Types.PIGLIN, new ResourceLocation("minecraft", "textures/entity/piglin/piglin.png"));
		SkullTileEntityRenderer.SKINS.put(ExtendedSkullBlock.Types.ZOMBIE_PIGLIN, new ResourceLocation("minecraft", "textures/entity/piglin/zombified_piglin.png"));
		SkullTileEntityRenderer.SKINS.put(ExtendedSkullBlock.Types.HUSK, new ResourceLocation("minecraft", "textures/entity/zombie/husk.png"));
		SkullTileEntityRenderer.SKINS.put(ExtendedSkullBlock.Types.STRAY, new ResourceLocation(ModSpartanWeaponry.ID, "textures/entity/skull/stray_skull.png"));
		SkullTileEntityRenderer.SKINS.put(ExtendedSkullBlock.Types.DROWNED, new ResourceLocation(ModSpartanWeaponry.ID, "textures/entity/skull/drowned_head.png"));
		SkullTileEntityRenderer.SKINS.put(ExtendedSkullBlock.Types.ILLAGER, new ResourceLocation("minecraft", "textures/entity/illager/pillager.png"));
		SkullTileEntityRenderer.SKINS.put(ExtendedSkullBlock.Types.WITCH, new ResourceLocation("minecraft", "textures/entity/witch.png"));
		Log.info("Successfully added extended Skull renders!");
	}
	
	public static void registerEntityRenders()
	{
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ARROW_SW, new ArrowBaseRenderFactory<ArrowBaseEntity>());
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.ARROW_EXPLOSIVE, new SimpleArrowRenderFactory<ArrowExplosiveEntity>("textures/entity/projectiles/arrow_explosive.png"));
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.BOLT, new BoltRenderFactory<BoltEntity>());
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.BOLT_SPECTRAL, new BoltRenderFactory<BoltSpectralEntity>());
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.THROWING_WEAPON, new ThrowingWeaponRenderFactory<ThrowingWeaponEntity>());
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.TOMAHAWK, new TomahawkRenderFactory<TomahawkEntity>());
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.JAVELIN, new JavelinRenderFactory<JavelinEntity>());
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.BOOMERANG, new BoomerangRenderFactory());
		RenderingRegistry.registerEntityRenderingHandler(ModEntities.DYNAMITE, new IRenderFactory<DynamiteEntity>() {
			@Override
			public EntityRenderer<? super DynamiteEntity> createRenderFor(EntityRendererManager manager)
			{
				return new SpriteRenderer<DynamiteEntity>(manager, Minecraft.getInstance().getItemRenderer());
			}
		});
		
		ClientRegistry.bindTileEntityRenderer(ModTileEntities.EXTENDED_SKULL_TYPE, dispatcher -> new SkullTileEntityRenderer(dispatcher));
//		ClientRegistry.bindTileEntityRenderer(ModTileEntities.NEW_SKULL_TYPE, (dispatcher) -> new NewSkullTileEntityRenderer(dispatcher));
	}
	
	public static void registerScreens()
	{
		ScreenManager.registerFactory(ModContainers.QUIVER_ARROW, QuiverArrowScreen::new);
		ScreenManager.registerFactory(ModContainers.QUIVER_BOLT, QuiverBoltScreen::new);
	}
}

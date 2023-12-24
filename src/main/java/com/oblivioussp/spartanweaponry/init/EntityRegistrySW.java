package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
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
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.EntityRegistry;

@EventBusSubscriber(modid = ModSpartanWeaponry.ID)
public class EntityRegistrySW 
{
	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void registerEntities(RegistryEvent.Register<EntityEntry> ev)
	{
		int entityId = 0;
		
		Log.info("Registering entities!");
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "bolt"), EntityBoltTipped.class, ModSpartanWeaponry.ID + ":bolt", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "bolt_specral"), EntityBoltSpectral.class, ModSpartanWeaponry.ID + ":bolt_spectral", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "dagger_thrown"), EntityThrownWeapon.class, ModSpartanWeaponry.ID + ":dagger_thrown", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_knife"), EntityThrowingKnife.class, ModSpartanWeaponry.ID + ":throwing_knife", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_axe"), EntityThrowingAxe.class, ModSpartanWeaponry.ID + ":throwing_axe", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "javelin_thrown"), EntityThrownJavelin.class, ModSpartanWeaponry.ID + ":javelin_thrown", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "arrow_wood"), EntityArrowWood.class, ModSpartanWeaponry.ID + ":arrow_wood", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "arrow_iron"), EntityArrowIron.class, ModSpartanWeaponry.ID + ":arrow_iron", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "arrow_diamond"), EntityArrowDiamond.class, ModSpartanWeaponry.ID + ":arrow_diamond", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "arrow_explosive"), EntityArrowExplosive.class, ModSpartanWeaponry.ID + ":arrow_explosive", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "boomerang_thrown"), EntityBoomerang.class, ModSpartanWeaponry.ID + ":boomerang_thrown", ++entityId, ModSpartanWeaponry.instance, 64, 5, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "dynamite"), EntityDynamite.class, ModSpartanWeaponry.ID + ":dynamite", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
		EntityRegistry.registerModEntity(new ResourceLocation(ModSpartanWeaponry.ID, "bolt_diamond"), EntityBoltDiamond.class, ModSpartanWeaponry.ID + ":bolt_diamond", ++entityId, ModSpartanWeaponry.instance, 64, 10, true);
	}

}

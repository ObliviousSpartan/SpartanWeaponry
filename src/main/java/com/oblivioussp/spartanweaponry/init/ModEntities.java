package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.ArrowBaseEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ArrowExplosiveEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltSpectralEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.BoomerangEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.DynamiteEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.JavelinEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.TomahawkEntity;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities 
{
	public static final EntityType<ArrowBaseEntity> ARROW_SW = EntityType.Builder.<ArrowBaseEntity>create(ArrowBaseEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f).
			setCustomClientFactory(ArrowBaseEntity::new).build("arrow_sw");
	
	public static final EntityType<ArrowExplosiveEntity> ARROW_EXPLOSIVE = EntityType.Builder.<ArrowExplosiveEntity>create(ArrowExplosiveEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
			.setCustomClientFactory(ArrowExplosiveEntity::new).build("arrow_explosive");
	
	public static final EntityType<BoltEntity> BOLT = EntityType.Builder.<BoltEntity>create(BoltEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
			.setCustomClientFactory(BoltEntity::new).build("bolt");
	public static final EntityType<BoltSpectralEntity> BOLT_SPECTRAL = EntityType.Builder.<BoltSpectralEntity>create(BoltSpectralEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
			.setCustomClientFactory(BoltSpectralEntity::new).build("bolt_spectral");
	
	public static final EntityType<ThrowingWeaponEntity> THROWING_WEAPON = EntityType.Builder.<ThrowingWeaponEntity>create(ThrowingWeaponEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
			.setCustomClientFactory(ThrowingWeaponEntity::new).build("throwing_weapon");
	public static final EntityType<TomahawkEntity> TOMAHAWK = EntityType.Builder.<TomahawkEntity>create(TomahawkEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
			.setCustomClientFactory(TomahawkEntity::new).build("tomahawk");
	public static final EntityType<JavelinEntity> JAVELIN = EntityType.Builder.<JavelinEntity>create(JavelinEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
			.setCustomClientFactory(JavelinEntity::new).build("javelin");
	public static final EntityType<BoomerangEntity> BOOMERANG = EntityType.Builder.<BoomerangEntity>create(BoomerangEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
			.setCustomClientFactory(BoomerangEntity::new).build("boomerang");
	public static final EntityType<DynamiteEntity> DYNAMITE = EntityType.Builder.<DynamiteEntity>create(DynamiteEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
			.setCustomClientFactory(DynamiteEntity::new).build("dynamite");
	
	static
	{
		ARROW_SW.setRegistryName(ModSpartanWeaponry.ID, "arrow");
		ARROW_EXPLOSIVE.setRegistryName(ModSpartanWeaponry.ID, "arrow_explosive");
		BOLT.setRegistryName(ModSpartanWeaponry.ID, "bolt");
		BOLT_SPECTRAL.setRegistryName(ModSpartanWeaponry.ID, "bolt_spectral");
		THROWING_WEAPON.setRegistryName(ModSpartanWeaponry.ID, "throwing_weapon");
		TOMAHAWK.setRegistryName(ModSpartanWeaponry.ID, "tomahawk");
		JAVELIN.setRegistryName(ModSpartanWeaponry.ID, "javelin");
		BOOMERANG.setRegistryName(ModSpartanWeaponry.ID, "boomerang");
		DYNAMITE.setRegistryName(ModSpartanWeaponry.ID, "dynamite");
	}
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<EntityType<?>> ev)
	{
		Log.info("Registering Entities");
		IForgeRegistry<EntityType<?>> reg = ev.getRegistry();
		
		reg.registerAll(ARROW_SW, ARROW_EXPLOSIVE, BOLT, BOLT_SPECTRAL,
				THROWING_WEAPON, TOMAHAWK, JAVELIN, BOOMERANG, DYNAMITE);
	}
}

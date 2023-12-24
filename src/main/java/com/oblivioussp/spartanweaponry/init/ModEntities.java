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
	
//	public static final EntityType<ArrowWoodEntity> ARROW_WOOD = EntityType.Builder.<ArrowWoodEntity>create(ArrowWoodEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
//			.setCustomClientFactory(ArrowWoodEntity::new).build("arrow_wood");
//	public static final EntityType<ArrowIronEntity> ARROW_IRON = EntityType.Builder.<ArrowIronEntity>create(ArrowIronEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
//			.setCustomClientFactory(ArrowIronEntity::new).build("arrow_iron");
//	public static final EntityType<ArrowDiamondEntity> ARROW_DIAMOND = EntityType.Builder.<ArrowDiamondEntity>create(ArrowDiamondEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
//			.setCustomClientFactory(ArrowDiamondEntity::new).build("arrow_diamond");
	public static final EntityType<ArrowExplosiveEntity> ARROW_EXPLOSIVE = EntityType.Builder.<ArrowExplosiveEntity>create(ArrowExplosiveEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
			.setCustomClientFactory(ArrowExplosiveEntity::new).build("arrow_explosive");
	
	public static final EntityType<BoltEntity> BOLT = EntityType.Builder.<BoltEntity>create(BoltEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
			.setCustomClientFactory(BoltEntity::new).build("bolt");
	public static final EntityType<BoltSpectralEntity> BOLT_SPECTRAL = EntityType.Builder.<BoltSpectralEntity>create(BoltSpectralEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
			.setCustomClientFactory(BoltSpectralEntity::new).build("bolt_spectral");
//	public static final EntityType<BoltDiamondEntity> BOLT_DIAMOND = EntityType.Builder.<BoltDiamondEntity>create(BoltDiamondEntity::new, EntityClassification.MISC).setShouldReceiveVelocityUpdates(true).size(0.5f, 0.5f)
//			.setCustomClientFactory(BoltDiamondEntity::new).build("bolt_diamond");
	
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
//		ARROW_WOOD.setRegistryName(Reference.MOD_ID, "arrow_wood");
//		ARROW_IRON.setRegistryName(Reference.MOD_ID, "arrow_iron");
//		ARROW_DIAMOND.setRegistryName(Reference.MOD_ID, "arrow_diamond");
		ARROW_SW.setRegistryName(ModSpartanWeaponry.ID, "arrow");
		ARROW_EXPLOSIVE.setRegistryName(ModSpartanWeaponry.ID, "arrow_explosive");
		BOLT.setRegistryName(ModSpartanWeaponry.ID, "bolt");
		BOLT_SPECTRAL.setRegistryName(ModSpartanWeaponry.ID, "bolt_spectral");
//		BOLT_DIAMOND.setRegistryName(ModSpartanWeaponry.ID, "bolt_diamond");
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
		
		reg.registerAll(/*ARROW_WOOD, ARROW_IRON, ARROW_DIAMOND,*/ARROW_SW, ARROW_EXPLOSIVE,
				BOLT, BOLT_SPECTRAL,
				THROWING_WEAPON, TOMAHAWK, JAVELIN, BOOMERANG, DYNAMITE);
	}
}

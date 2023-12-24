package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.entity.projectile.ArrowBaseEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ArrowExplosiveEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.BoltSpectralEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.BoomerangEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.DynamiteEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.JavelinEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingKnifeEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.ThrowingWeaponEntity;
import com.oblivioussp.spartanweaponry.entity.projectile.TomahawkEntity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities 
{
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITIES, ModSpartanWeaponry.ID);
	
	public static final RegistryObject<EntityType<ArrowBaseEntity>> ARROW_SW = REGISTRY.register("arrow_sw", () -> EntityType.Builder.<ArrowBaseEntity>of(ArrowBaseEntity::new, MobCategory.MISC).
			clientTrackingRange(4).
			updateInterval(20).
			setShouldReceiveVelocityUpdates(true).
			sized(0.5f, 0.5f).
			setCustomClientFactory(ArrowBaseEntity::new).
			build("arrow_sw"));
	public static final RegistryObject<EntityType<ArrowExplosiveEntity>> ARROW_EXPLOSIVE = REGISTRY.register("arrow_explosive", () -> EntityType.Builder.<ArrowExplosiveEntity>of(ArrowExplosiveEntity::new, MobCategory.MISC).
			clientTrackingRange(4).
			updateInterval(20).
			setShouldReceiveVelocityUpdates(true).
			sized(0.5f, 0.5f).
			setCustomClientFactory(ArrowExplosiveEntity::new).
			build("arrow_explosive"));
	public static final RegistryObject<EntityType<BoltEntity>> BOLT = REGISTRY.register("bolt", () -> EntityType.Builder.<BoltEntity>of(BoltEntity::new, MobCategory.MISC).
			clientTrackingRange(4).
			updateInterval(20).
			setShouldReceiveVelocityUpdates(true).
			sized(0.5f, 0.5f).
			setCustomClientFactory(BoltEntity::new).
			build("bolt"));
	public static final RegistryObject<EntityType<BoltSpectralEntity>> BOLT_SPECTRAL = REGISTRY.register("bolt_spectral", () -> EntityType.Builder.<BoltSpectralEntity>of(BoltSpectralEntity::new, MobCategory.MISC).
			clientTrackingRange(4).
			updateInterval(20).
			setShouldReceiveVelocityUpdates(true).
			sized(0.5f, 0.5f).
			setCustomClientFactory(BoltSpectralEntity::new).
			build("bolt_spectral"));
	public static final RegistryObject<EntityType<ThrowingWeaponEntity>> THROWING_WEAPON = REGISTRY.register("throwing_weapon", () -> EntityType.Builder.<ThrowingWeaponEntity>of(ThrowingWeaponEntity::new, MobCategory.MISC).
			clientTrackingRange(4).
			updateInterval(20).
			setShouldReceiveVelocityUpdates(true).
			sized(0.5f, 0.5f).
			setCustomClientFactory(ThrowingWeaponEntity::new).
			build("throwing_weapon"));
	public static final RegistryObject<EntityType<ThrowingKnifeEntity>> THROWING_KNIFE = REGISTRY.register("throwing_knife", () -> EntityType.Builder.<ThrowingKnifeEntity>of(ThrowingKnifeEntity::new, MobCategory.MISC).
			clientTrackingRange(4).
			updateInterval(20).
			setShouldReceiveVelocityUpdates(true).
			sized(0.5f, 0.5f).
			setCustomClientFactory(ThrowingKnifeEntity::new).
			build("throwing_knife"));
	public static final RegistryObject<EntityType<TomahawkEntity>> TOMAHAWK = REGISTRY.register("tomahawk", () -> EntityType.Builder.<TomahawkEntity>of(TomahawkEntity::new, MobCategory.MISC).
			clientTrackingRange(4).
			updateInterval(20).
			setShouldReceiveVelocityUpdates(true).
			sized(0.5f, 0.5f).
			setCustomClientFactory(TomahawkEntity::new).
			build("tomahawk"));
	public static final RegistryObject<EntityType<JavelinEntity>> JAVELIN = REGISTRY.register("javelin", () -> EntityType.Builder.<JavelinEntity>of(JavelinEntity::new, MobCategory.MISC).
			clientTrackingRange(4).
			updateInterval(20).
			setShouldReceiveVelocityUpdates(true).
			sized(0.5f, 0.5f).
			setCustomClientFactory(JavelinEntity::new).
			build("javelin"));
	public static final RegistryObject<EntityType<BoomerangEntity>> BOOMERANG = REGISTRY.register("boomerang", () -> EntityType.Builder.<BoomerangEntity>of(BoomerangEntity::new, MobCategory.MISC).
			setShouldReceiveVelocityUpdates(true).
			sized(0.5f, 0.5f).
			setCustomClientFactory(BoomerangEntity::new).
			build("boomerang"));
	public static final RegistryObject<EntityType<DynamiteEntity>> DYNAMITE = REGISTRY.register("dynamite", () -> EntityType.Builder.<DynamiteEntity>of(DynamiteEntity::new, MobCategory.MISC).
			clientTrackingRange(4).
			updateInterval(20).
			setShouldReceiveVelocityUpdates(true).
			sized(0.5f, 0.5f).
			setCustomClientFactory(DynamiteEntity::new).
			build("dynamite"));
}

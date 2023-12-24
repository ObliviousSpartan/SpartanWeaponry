package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.sounds.SoundEventSW;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds
{
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.SOUND_EVENTS, ModSpartanWeaponry.ID);
	
	public static final RegistryObject<SoundEvent> THROWN_WEAPON_THROW = REGISTRY.register("throwing_weapon_throw", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_weapon_throw")));
	public static final RegistryObject<SoundEvent> THROWN_WEAPON_HIT_MOB = REGISTRY.register("throwing_weapon_hit_mob", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_weapon_hit_mob")));
	public static final RegistryObject<SoundEvent> THROWN_WEAPON_HIT_GROUND = REGISTRY.register("dagger_hit_ground", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_weapon_hit_ground")));
	public static final RegistryObject<SoundEvent> THROWING_KNIFE_THROW = REGISTRY.register("throwing_knife_throw", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_knife_throw")));
	public static final RegistryObject<SoundEvent> THROWING_KNIFE_HIT_MOB = REGISTRY.register("throwing_knife_hit_mob", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_knife_hit_mob")));
	public static final RegistryObject<SoundEvent> THROWING_KNIFE_HIT_GROUND = REGISTRY.register("throwing_knife_hit_ground", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_knife_hit_ground")));
	public static final RegistryObject<SoundEvent> TOMAHAWK_THROW = REGISTRY.register("tomahawk_throw", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "tomahawk_throw")));
	public static final RegistryObject<SoundEvent> TOMAHAWK_HIT_MOB = REGISTRY.register("tomahawk_hit_mob", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "tomahawk_hit_mob")));
	public static final RegistryObject<SoundEvent> TOMAHAWK_HIT_GROUND = REGISTRY.register("tomahawk_hit_ground", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "tomahawk_hit_ground")));
	public static final RegistryObject<SoundEvent> JAVELIN_THROW = REGISTRY.register("javelin_throw", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "javelin_throw")));
	public static final RegistryObject<SoundEvent> JAVELIN_HIT_MOB = REGISTRY.register("javelin_hit_mob", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "javelin_hit_mob")));
	public static final RegistryObject<SoundEvent> JAVELIN_HIT_GROUND = REGISTRY.register("javelin_hit_ground", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "javelin_hit_ground")));
	public static final RegistryObject<SoundEvent> BOOMERANG_THROW = REGISTRY.register("boomerang_throw", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "boomerang_throw")));
	public static final RegistryObject<SoundEvent> BOOMERANG_FLY = REGISTRY.register("boomerang_fly", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "boomerang_fly")));
	public static final RegistryObject<SoundEvent> BOOMERANG_HIT_MOB = REGISTRY.register("boomerang_hit_mob", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "boomerang_hit_mob")));
	public static final RegistryObject<SoundEvent> BOOMERANG_BOUNCE = REGISTRY.register("boomerang_bounce", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "boomerang_bounce")));
	public static final RegistryObject<SoundEvent> BOOMERANG_HIT_GROUND = REGISTRY.register("boomerang_hit_ground", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "boomerang_hit_ground")));
	public static final RegistryObject<SoundEvent> THROWING_WEAPON_LOYALTY_RETURN = REGISTRY.register("throwing_weapon_loyalty_return", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_weapon_loyalty_return")));

	public static final RegistryObject<SoundEvent> OIL_APPLIED = REGISTRY.register("oil_applied", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "oil_applied")));
	public static final RegistryObject<SoundEvent> HAMMER_SLAMS_INTO_GROUND = REGISTRY.register("hammer_slams_into_ground", () -> new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "hammer_slams_into_ground")));
}

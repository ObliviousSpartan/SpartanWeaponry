package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.util.SoundEventSW;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModSounds
{
	public static SoundEvent DAGGER_THROW = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "dagger_throw"));
	public static SoundEvent DAGGER_HIT_MOB = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "dagger_hit_mob"));
	public static SoundEvent DAGGER_HIT_GROUND = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "dagger_hit_ground"));
	public static SoundEvent THROWING_KNIFE_THROW = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_knife_throw"));
	public static SoundEvent THROWING_KNIFE_HIT_MOB = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_knife_hit_mob"));
	public static SoundEvent THROWING_KNIFE_HIT_GROUND = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_knife_hit_ground"));
	public static SoundEvent TOMAHAWK_THROW = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "tomahawk_throw"));
	public static SoundEvent TOMAHAWK_HIT_MOB = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "tomahawk_hit_mob"));
	public static SoundEvent TOMAHAWK_HIT_GROUND = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "tomahawk_hit_ground"));
	public static SoundEvent JAVELIN_THROW = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "javelin_throw"));
	public static SoundEvent JAVELIN_HIT_MOB = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "javelin_hit_mob"));
	public static SoundEvent JAVELIN_HIT_GROUND = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "javelin_hit_ground"));
	public static SoundEvent BOOMERANG_THROW = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "boomerang_throw"));
	public static SoundEvent BOOMERANG_FLY = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "boomerang_fly"));
	public static SoundEvent BOOMERANG_HIT_MOB = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "boomerang_hit_mob"));
	public static SoundEvent BOOMERANG_BOUNCE = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "boomerang_bounce"));
	public static SoundEvent BOOMERANG_HIT_GROUND = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "boomerang_hit_ground"));
	public static SoundEvent THROWING_WEAPON_LOYALTY_RETURN = new SoundEventSW(new ResourceLocation(ModSpartanWeaponry.ID, "throwing_weapon_loyalty_return"));

	@SubscribeEvent
	public static void register(RegistryEvent.Register<SoundEvent> ev)
	{
		IForgeRegistry<SoundEvent> reg = ev.getRegistry();
		
		reg.registerAll(DAGGER_THROW, DAGGER_HIT_MOB, DAGGER_HIT_GROUND,
				THROWING_KNIFE_THROW, THROWING_KNIFE_HIT_MOB, THROWING_KNIFE_HIT_GROUND,
				TOMAHAWK_THROW, TOMAHAWK_HIT_MOB, TOMAHAWK_HIT_GROUND,
				JAVELIN_THROW, JAVELIN_HIT_MOB, JAVELIN_HIT_GROUND,
				BOOMERANG_THROW, BOOMERANG_FLY, BOOMERANG_HIT_MOB, BOOMERANG_BOUNCE, BOOMERANG_HIT_GROUND,
				THROWING_WEAPON_LOYALTY_RETURN);
	}
}

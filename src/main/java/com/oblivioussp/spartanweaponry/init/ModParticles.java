package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.client.particle.DamageModifiedParticle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.client.event.ParticleFactoryRegisterEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModParticles 
{
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, ModSpartanWeaponry.ID);
	
	public static final RegistryObject<SimpleParticleType> DAMAGE_BOOSTED = REGISTRY.register("damage_boosted", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> DAMAGE_REDUCED = REGISTRY.register("damage_reduced", () -> new SimpleParticleType(false));
	public static final RegistryObject<SimpleParticleType> OIL_DAMAGE_BOOSTED = REGISTRY.register("oil_damage_boosted", () -> new SimpleParticleType(false));
	// TODO: Also consider adding boomerang trail particle and possibly for other throwing weapons too
	
	public static void registerFactories(ParticleFactoryRegisterEvent ev)
	{
		Minecraft mc = Minecraft.getInstance();
		ParticleEngine particleEngine = mc.particleEngine;

		particleEngine.register(DAMAGE_BOOSTED.get(), DamageModifiedParticle.DamageBoostedProvider::new);
		particleEngine.register(DAMAGE_REDUCED.get(), DamageModifiedParticle.DamageReducedProvider::new);
		particleEngine.register(OIL_DAMAGE_BOOSTED.get(), DamageModifiedParticle.OilDamageBoostedProvider::new);
	}
}

package com.oblivioussp.spartanweaponry.api;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.oil.MobOilEffect;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect;
import com.oblivioussp.spartanweaponry.api.oil.OilEffect.OilEffectType;
import com.oblivioussp.spartanweaponry.api.oil.PotionOilEffect;
import com.oblivioussp.spartanweaponry.init.ModMobEffects;
import com.oblivioussp.spartanweaponry.util.Defaults;

import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class OilEffects 
{
	public static final ResourceLocation REGISTRY_NAME = new ResourceLocation(ModSpartanWeaponry.ID, "oil_effects");
	public static final ResourceKey<Registry<OilEffect>> REGISTRY_KEY = ResourceKey.createRegistryKey(REGISTRY_NAME);
	public static final DeferredRegister<OilEffect> REGISTRY = DeferredRegister.create(REGISTRY_KEY, ModSpartanWeaponry.ID);
	
	public static final RegistryObject<OilEffect> NONE = REGISTRY.register("none", () -> new OilEffect("none", OilEffectType.NONE, 0xE2C467, 0, 0.0f, OilEffect.USE_NOTHING));
	public static final RegistryObject<OilEffect> UNDEAD = REGISTRY.register("undead", () -> new OilEffect("undead", OilEffectType.STANDARD, 0xFFE600, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_NORMAL, OilEffect.USE_UNDEAD));
	public static final RegistryObject<OilEffect> UNDEAD_STRONG = REGISTRY.register("undead_strong", () -> new OilEffect("undead", OilEffectType.POTENT, 0xFFE600, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_STRONG, OilEffect.USE_UNDEAD));
	public static final RegistryObject<OilEffect> ARTHOPOD = REGISTRY.register("arthropod", () -> new OilEffect("arthropod", OilEffectType.STANDARD, 0x804000, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_NORMAL, OilEffect.USE_ARTHROPOD));
	public static final RegistryObject<OilEffect> ARTHOPOD_STRONG = REGISTRY.register("arthropod_strong", () -> new OilEffect("arthropod", OilEffectType.POTENT, 0x804000, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_STRONG, OilEffect.USE_ARTHROPOD));
	public static final RegistryObject<OilEffect> CRYOTIC = REGISTRY.register("cryotic", () -> new OilEffect("cryotic", OilEffectType.STANDARD, 0x80A0FF, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_NORMAL, OilEffect.USE_CRYOTIC));
	public static final RegistryObject<OilEffect> CRYOTIC_STRONG = REGISTRY.register("cryotic_strong", () -> new OilEffect("cryotic", OilEffectType.POTENT, 0x80A0FF, Defaults.OIL_USES_NORMAL,  Defaults.OIL_DAMAGE_MODIFIER_STRONG, OilEffect.USE_CRYOTIC));
	public static final RegistryObject<OilEffect> NECROTIC = REGISTRY.register("necrotic", () -> new OilEffect("necrotic", OilEffectType.STANDARD, 0x208040, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_NORMAL, OilEffect.USE_NECTROTIC));
	public static final RegistryObject<OilEffect> NECROTIC_STRONG = REGISTRY.register("necrotic_strong", () -> new OilEffect("necrotic", OilEffectType.POTENT, 0x208040, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_STRONG, OilEffect.USE_NECTROTIC));
	public static final RegistryObject<OilEffect> CREEPER = REGISTRY.register("creeper", () -> new OilEffect("creeper", OilEffectType.STANDARD, 0x804080, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_NORMAL, OilEffect.USE_CREEPER));
	public static final RegistryObject<OilEffect> CREEPER_STRONG = REGISTRY.register("creeper_strong", () -> new OilEffect("creeper", OilEffectType.POTENT, 0x804080, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_STRONG, OilEffect.USE_CREEPER));
	public static final RegistryObject<OilEffect> AQUATIC = REGISTRY.register("aquatic", () -> new OilEffect("aquatic", OilEffectType.STANDARD, 0x4080C0, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_NORMAL, OilEffect.USE_AQUATIC));
	public static final RegistryObject<OilEffect> AQUATIC_STRONG = REGISTRY.register("aquatic_strong", () -> new OilEffect("aquatic", OilEffectType.POTENT, 0x4080C0, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_STRONG, OilEffect.USE_AQUATIC));
	public static final RegistryObject<OilEffect> ENDER = REGISTRY.register("ender", () -> new MobOilEffect("ender", OilEffectType.STANDARD, 0x208080, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_NORMAL, OilEffect.USE_ENDER, ModMobEffects.ENDER_DISRPUTION.get(), 300, 0));
	public static final RegistryObject<OilEffect> ENDER_STRONG = REGISTRY.register("ender_strong", () -> new MobOilEffect("ender", OilEffectType.POTENT, 0x208080, Defaults.OIL_USES_NORMAL, Defaults.OIL_DAMAGE_MODIFIER_STRONG, OilEffect.USE_ENDER, ModMobEffects.ENDER_DISRPUTION.get(), 600, 0));
	public static final RegistryObject<OilEffect> WITHER = REGISTRY.register("wither", () -> new MobOilEffect("wither", OilEffectType.EFFECT_ONLY, 0x402020, Defaults.OIL_USES_NORMAL, MobEffects.WITHER, 200, 0));
	public static final RegistryObject<OilEffect> WITHER_LONG = REGISTRY.register("wither_long", () -> new MobOilEffect("wither", OilEffectType.EFFECT_ONLY, 0x402020, Defaults.OIL_USES_LONG, MobEffects.WITHER, 200, 0));
	public static final RegistryObject<OilEffect> WITHER_STRONG = REGISTRY.register("wither_strong", () -> new MobOilEffect("wither", OilEffectType.EFFECT_ONLY, 0x402020, Defaults.OIL_USES_NORMAL, MobEffects.WITHER, 100, 1));

	// This Oil Effect covers every valid Oil that is based on existing Potion Effects
	public static final RegistryObject<OilEffect> POTION = REGISTRY.register("potion", () -> new PotionOilEffect());
}
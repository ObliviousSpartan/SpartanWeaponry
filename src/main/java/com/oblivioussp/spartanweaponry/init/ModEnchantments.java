package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.enchantment.CollectorangEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.ExpanseEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.SharpshooterEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.HydrodynamicEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.IncendiaryEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.LuckyThrowEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.PropelEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.RazorsEdgeEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.SuperchargeEnchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantment.Rarity;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEnchantments 
{	
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.ENCHANTMENTS, ModSpartanWeaponry.ID);
	
	public static final RegistryObject<Enchantment> PROPEL = REGISTRY.register("propel", () -> new PropelEnchantment(Rarity.UNCOMMON, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
	public static final RegistryObject<Enchantment> RAZORS_EDGE = REGISTRY.register("razors_edge", () -> new RazorsEdgeEnchantment(Rarity.COMMON, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
	public static final RegistryObject<Enchantment> INCENDIARY = REGISTRY.register("incendiary", () -> new IncendiaryEnchantment(Rarity.RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
	public static final RegistryObject<Enchantment> LUCKY_THROW = REGISTRY.register("lucky_throw", () -> new LuckyThrowEnchantment(Rarity.RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
	public static final RegistryObject<Enchantment> HYDRODYNAMIC = REGISTRY.register("hydrodynamic", () -> new HydrodynamicEnchantment(Rarity.RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
	public static final RegistryObject<Enchantment> SUPERCHARGE = REGISTRY.register("supercharge", () -> new SuperchargeEnchantment(Rarity.RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
	public static final RegistryObject<Enchantment> EXPANSE = REGISTRY.register("expanse", () -> new ExpanseEnchantment(Rarity.RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
	public static final RegistryObject<Enchantment> SHARPSHOOTER = REGISTRY.register("sharpshooter", () -> new SharpshooterEnchantment(Rarity.RARE, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
	public static final RegistryObject<Enchantment> COLLECTORANG = REGISTRY.register("collectorang", () -> new CollectorangEnchantment(Rarity.UNCOMMON, EquipmentSlot.MAINHAND, EquipmentSlot.OFFHAND));
}

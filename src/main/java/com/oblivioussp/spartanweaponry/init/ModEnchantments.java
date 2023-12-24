package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.enchantment.HeavyCrossbowSharpshooterEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.ThrowingAmmoEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.ThrowingChargeEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.ThrowingDamageEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.ThrowingFireEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.ThrowingHydrodynamicEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.ThrowingLuckEnchantment;
import com.oblivioussp.spartanweaponry.enchantment.ThrowingRangeEnchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantment.Rarity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
public class ModEnchantments 
{	
	public static final Enchantment THROWING_RANGE = new ThrowingRangeEnchantment(Rarity.UNCOMMON, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND);
	public static final Enchantment THROWING_DAMAGE = new ThrowingDamageEnchantment(Rarity.COMMON, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND);
	public static final Enchantment THROWING_FIRE = new ThrowingFireEnchantment(Rarity.RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND);
	public static final Enchantment THROWING_LUCK = new ThrowingLuckEnchantment(Rarity.RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND);
	public static final Enchantment THROWING_HYDRODYNAMIC = new ThrowingHydrodynamicEnchantment(Rarity.RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND);
	public static final Enchantment THROWING_CHARGE = new ThrowingChargeEnchantment(Rarity.RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND);
	public static final Enchantment THROWING_AMMO = new ThrowingAmmoEnchantment(Rarity.RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND);
	public static final Enchantment HEAVY_CROSSBOW_SHARPSHOOTER = new HeavyCrossbowSharpshooterEnchantment(Rarity.RARE, EquipmentSlotType.MAINHAND, EquipmentSlotType.OFFHAND);
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<Enchantment> ev)
	{
		IForgeRegistry<Enchantment> reg = ev.getRegistry();
		
		reg.registerAll(THROWING_RANGE, THROWING_DAMAGE, THROWING_FIRE, THROWING_LUCK, THROWING_HYDRODYNAMIC, THROWING_CHARGE, THROWING_AMMO,
				HEAVY_CROSSBOW_SHARPSHOOTER);
	}
}

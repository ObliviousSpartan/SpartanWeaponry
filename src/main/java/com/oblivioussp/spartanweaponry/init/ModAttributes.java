package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.APIAttributes;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.MOD)
public class ModAttributes 
{
	
	@SubscribeEvent
	public static void register(RegistryEvent.Register<Attribute> ev)
	{
		IForgeRegistry<Attribute> reg = ev.getRegistry();
		
		APIAttributes.ATTACK_REACH.setRegistryName(ModSpartanWeaponry.ID, "attack_reach").setShouldWatch(true);
		
		reg.register(APIAttributes.ATTACK_REACH);
	}
	
	@SubscribeEvent
	public static void onEntityModifyAttributes(EntityAttributeModificationEvent ev)
	{
		ev.add(EntityType.PLAYER, APIAttributes.ATTACK_REACH);
	}
}

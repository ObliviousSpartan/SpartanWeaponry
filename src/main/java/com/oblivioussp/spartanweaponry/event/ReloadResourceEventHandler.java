package com.oblivioussp.spartanweaponry.event;

import java.util.List;

import com.oblivioussp.spartanweaponry.api.IReloadable;
import com.oblivioussp.spartanweaponry.api.ReloadableHandler;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.client.OilCoatingColours;
import com.oblivioussp.spartanweaponry.init.ModOilRecipes;
import com.oblivioussp.spartanweaponry.util.Log;
import com.oblivioussp.spartanweaponry.util.WeaponArchetype;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TagsUpdatedEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(bus = EventBusSubscriber.Bus.FORGE)
public class ReloadResourceEventHandler
{
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void onUpdateTags(TagsUpdatedEvent ev)
	{
		List<WeaponMaterial> materialReloadList = ReloadableHandler.getMaterialReloadList();
		List<IReloadable> itemReloadList = ReloadableHandler.getItemReloadList();
		
		Log.debug("Initaliasing reloadables for " + materialReloadList.size() + " materials, " + WeaponArchetype.ALL_ARCHETYPES.size() + " archetypes and " + itemReloadList.size() + " items");
		long start = System.nanoTime();
		// Enforce an order of materials being reloaded first to ensure that items can fetch the appropriate traits from their materials
		// to prevent NullPointerExceptions!
		DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> OilCoatingColours::reload);
		materialReloadList.forEach((material) -> material.reload());
		WeaponArchetype.ALL_ARCHETYPES.forEach((archetype) -> archetype.reload());
		itemReloadList.forEach((item) -> item.reload());
		long end = System.nanoTime();
		double milliseconds = (end-start) / 1000000.0d;
		ModOilRecipes.loadOilMixes();
		Log.info("Finished initialising Weapon Traits & Attributes! Took " + milliseconds + "ms");
	}
}

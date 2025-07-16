package com.oblivioussp.spartanweaponry.api;

import java.util.ArrayList;
import java.util.List;

/**
 * Handler for reloading values when tags get updated. This gets added to and used automatically.
 * @author ObliviousSpartan
 *
 */
public class ReloadableHandler
{
	private static List<WeaponMaterial> materialReloadList = new ArrayList<>(100);
	private static List<IReloadable> itemReloadList = new ArrayList<>(768);
	
	public static void addToMaterialReloadList(WeaponMaterial material)
	{
		materialReloadList.add(material);
	}
	
	public static void addToItemReloadList(IReloadable item)
	{
		itemReloadList.add(item);
	}
	
	public static List<WeaponMaterial> getMaterialReloadList()
	{
		return materialReloadList;
	}
	
	public static List<IReloadable> getItemReloadList() 
	{
		return itemReloadList;
	}
}

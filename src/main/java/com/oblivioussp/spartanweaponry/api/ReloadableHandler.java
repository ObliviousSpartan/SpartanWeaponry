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
	private static List<IReloadable> reloadList = new ArrayList<IReloadable>(768);
	
	public static void addToReloadList(IReloadable item)
	{
		reloadList.add(item);
	}
	
	public static List<IReloadable> getReloadList() 
	{
		return reloadList;
	}
}

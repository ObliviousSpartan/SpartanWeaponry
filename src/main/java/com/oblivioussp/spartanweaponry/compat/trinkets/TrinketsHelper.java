package com.oblivioussp.spartanweaponry.compat.trinkets;

import net.minecraft.entity.Entity;
import xzeroair.trinkets.capabilities.Capabilities;
import xzeroair.trinkets.capabilities.race.EntityProperties;

public class TrinketsHelper 
{
	// NOTE: The other race names are here in the event that integration with other races are added
	
/*	public static final String RACE_NONE = "None";
	public static final String RACE_HUMAN = "Human";
	public static final String RACE_FAIRY = "Fairy";
	public static final String RACE_DWARF = "Dwarf";*/
	public static final String RACE_TITAN = "Titan";
/*	public static final String RACE_ELF = "Elf";
	public static final String RACE_GOBLIN = "Goblin";
	public static final String RACE_FAELIS = "Faelis";
	public static final String RACE_DRAGON = "Dragon";*/
	
	public static String getEntityRace(Entity entity)
	{
		try
		{
			final EntityProperties raceProp = Capabilities.getEntityRace(entity);
			if(raceProp != null)
				return raceProp.getCurrentRace().getName();
		}
		catch(final Exception e)
		{
			e.printStackTrace();
		}
		return "";
	}
}

package com.oblivioussp.spartanweaponry.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.critereon.AbstractCriterionInstance;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class AdvancementTrigger implements ICriterionTrigger 
{
	public static final AdvancementTrigger CRAFT_ITEM = new AdvancementTrigger("craft_item");
	
	public static final AdvancementTrigger[] TRIGGER_ARRAY = new AdvancementTrigger[] {
			CRAFT_ITEM};
	
	private final ResourceLocation id;
	private final Map<PlayerAdvancements, Listeners> listeners = Maps.newHashMap();
	
	public AdvancementTrigger(String idString)
	{
		super();
		id = new ResourceLocation(idString);
	}
	
	public AdvancementTrigger(ResourceLocation idRes)
	{
		super();
		id = idRes;
	}

	@Override
	public ResourceLocation getId() 
	{
		return id;
	}

	@Override
	public void addListener(PlayerAdvancements playerAdvancementsIn, Listener listener) 
	{
		Listeners triggerListeners = listeners.get(playerAdvancementsIn);
		
		if(triggerListeners == null)
		{
			triggerListeners = new Listeners(playerAdvancementsIn);
			listeners.put(playerAdvancementsIn, triggerListeners);
		}
		
		triggerListeners.add(listener);
	}

	@Override
	public void removeListener(PlayerAdvancements playerAdvancementsIn, Listener listener) 
	{
		Listeners triggerListeners = listeners.get(playerAdvancementsIn);
		
		if(triggerListeners != null)
		{
			triggerListeners.remove(listener);
			
			if(triggerListeners.isEmpty())
			{
				listeners.remove(playerAdvancementsIn);
			}
		}

	}

	@Override
	public void removeAllListeners(PlayerAdvancements playerAdvancementsIn) 
	{
		listeners.remove(playerAdvancementsIn);
	}

	@Override
	public ICriterionInstance deserializeInstance(JsonObject json, JsonDeserializationContext context) 
	{
		String type = json.get("className").getAsString();
		return new Instance(getId(), type);
	}
	
	public void trigger(EntityPlayerMP player, Item item)
	{
		Listeners triggerListeners = listeners.get(player.getAdvancements());
		
		if(triggerListeners != null)
		{
			triggerListeners.trigger(player, item);
		}
	}
	
	
	
	public static class Instance extends AbstractCriterionInstance
	{
		private final String itemClass;
		
		public Instance(ResourceLocation criterionId, String className) 
		{
			super(criterionId);
			itemClass = className;
		}
		
		public boolean test(Item item)
		{
//			LogHelper.info("Name: " + item.getClass().getName());
//			LogHelper.info("Canonical Name: " + item.getClass().getCanonicalName());
//			LogHelper.info("Simple Name: " + item.getClass().getSimpleName());
			String canonicalName = item != null ? item.getClass().getCanonicalName() : null;
			return canonicalName != null && canonicalName.equals(itemClass);
		}
	}
	
	protected static class Listeners
	{
		private final PlayerAdvancements playerAdvancements;
		private final Set<ICriterionTrigger.Listener> listeners = Sets.newHashSet();
		
		public Listeners(PlayerAdvancements advancements)
		{
			playerAdvancements = advancements;
		}
		
		public boolean isEmpty()
		{
			return listeners.isEmpty();
		}
		
		public void add(ICriterionTrigger.Listener listener)
		{
			listeners.add(listener);
		}
		
		public void remove(ICriterionTrigger.Listener listener)
		{
			this.listeners.remove(listener);
		}
		
		@SuppressWarnings("unused")
		public void trigger(EntityPlayerMP player, Item item)
		{
			List<ICriterionTrigger.Listener> list = null;
			
			for(ICriterionTrigger.Listener listener : listeners)
			{
				if(((Instance) listener.getCriterionInstance()).test(item))
				{
					if(list == null)
						list = new ArrayList<ICriterionTrigger.Listener>();
					
					list.add(listener);
				}
			}
			
			if(list != null)
			{
				for(ICriterionTrigger.Listener listener : list)
				{
					listener.grantCriterion(playerAdvancements);
				}
			}
		}
	}

	public static void registerTriggers()
	{
		try
		{
			Log.info("Attempting to add new advancement triggers...");
			Method method = ReflectionHelper.findMethod(CriteriaTriggers.class, "register", "func_192118_a", ICriterionTrigger.class);
			method.setAccessible(true);
			
			for(int i = 0; i < TRIGGER_ARRAY.length; i++)
			{
				method.invoke(null, TRIGGER_ARRAY[i]);
			}
			
			Log.info("New advancement triggers added successfully!");
		}
		catch(SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) 
		{
			Log.error("Couldn't register advancement triggers due to an exception occuring. " + e.getMessage());
		}
	}
}

package com.oblivioussp.spartanweaponry.event;

import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IWeaponPropertyContainer;
import com.oblivioussp.spartanweaponry.api.WeaponProperties;
import com.oblivioussp.spartanweaponry.api.weaponproperty.WeaponProperty;
import com.oblivioussp.spartanweaponry.client.KeyBinds;
import com.oblivioussp.spartanweaponry.client.gui.HudLoadState;
import com.oblivioussp.spartanweaponry.client.gui.HudQuiverAmmo;
import com.oblivioussp.spartanweaponry.init.EnchantmentRegistrySW;
import com.oblivioussp.spartanweaponry.item.IHudLoadState;
import com.oblivioussp.spartanweaponry.item.ItemCrossbow;
import com.oblivioussp.spartanweaponry.item.ItemDagger;
import com.oblivioussp.spartanweaponry.item.ItemLongbow;
import com.oblivioussp.spartanweaponry.item.ItemThrowingWeapon;
import com.oblivioussp.spartanweaponry.network.NetworkHandler;
import com.oblivioussp.spartanweaponry.network.PacketKeyHandle;
import com.oblivioussp.spartanweaponry.network.PacketLongReachAttack;
import com.oblivioussp.spartanweaponry.util.ConfigHandler;
import com.oblivioussp.spartanweaponry.util.Log;
import com.oblivioussp.spartanweaponry.util.NBTHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper;
import com.oblivioussp.spartanweaponry.util.QuiverHelper.IQuiverInfo;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.settings.GameSettings;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItemFrame;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EntitySelectors;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.client.event.MouseEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.client.event.RenderSpecificHandEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@EventBusSubscriber(Side.CLIENT)
public class EventHandlerClient 
{
	private static ItemStack activeQuiverStack = ItemStack.EMPTY;
	
	protected static final ResourceLocation CROSSHAIR_TEXTURES = new ResourceLocation(ModSpartanWeaponry.ID, "textures/gui/crosshairs.png");
	/**
	 * Checks the mouse to see if extended reach should be applied
	 * @param ev
	 */
	//@SideOnly(Side.CLIENT)
	/*@SubscribeEvent(priority=EventPriority.HIGHEST)
	public static void onInputEvent(InputEvent ev)
	{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.player;
		
		if(player != null)
		{
			ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
			
			if(!stack.isEmpty())
			{
				if(stack.getItem() instanceof IWeaponPropertyContainer)
				{
					IWeaponPropertyContainer container = (IWeaponPropertyContainer) stack.getItem();
					WeaponProperty reachProperty = container.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_REACH);
					
					if(reachProperty != null && mc.gameSettings.keyBindAttack.isPressed())
					{
						float reach = reachProperty.getMagnitude();
						RayTraceResult result = getMouseOverExtended(reach);
						
						if(result != null && result.entityHit != null && result.entityHit.hurtResistantTime == 0 && result.entityHit != player)
						{
							NetworkHandler.sendPacketToServer(new PacketLongReachAttack(result.entityHit.getEntityId()));
						}
						player.swingArm(EnumHand.MAIN_HAND);
					}
				}
			}
		}
	}*/
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent()
	public static void onKeyInputEvent(KeyInputEvent ev)
	{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.player;
		
		// Ensure that the input is only checked if the game is running and the game isn't already in a GUI
		if(player != null && !mc.isGamePaused() && mc.inGameHasFocus)
		{
			/*ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
			
			if(!stack.isEmpty())
			{
				if(stack.getItem() instanceof IWeaponPropertyContainer)
				{
					IWeaponPropertyContainer container = (IWeaponPropertyContainer) stack.getItem();
					WeaponProperty reachProperty = container.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_REACH);
					
					if(reachProperty != null && mc.gameSettings.keyBindAttack.isPressed())
					{
						float reach = reachProperty.getMagnitude();
						RayTraceResult result = getMouseOverExtended(reach);
						
						if(result != null && result.entityHit != null && result.entityHit.hurtResistantTime == 0 && result.entityHit != player)
						{
							NetworkHandler.sendPacketToServer(new PacketLongReachAttack(result.entityHit.getEntityId()));
						}
						player.swingArm(EnumHand.MAIN_HAND);
					}
				}
			}*/
			
			if(KeyBinds.KEY_ACCESS_QUIVER.isPressed())
			{
				NetworkHandler.sendPacketToServer(new PacketKeyHandle());
			}
		}
	}
	
	/**
	 * Checks the mouse to see if extended reach should be applied
	 * @param ev
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.NORMAL, receiveCanceled=true)
	public static void onMouseEvent(MouseEvent ev)
	{
		// Don't run this if RLCombat is loaded (it has it's own handlers for this)
		if(ModSpartanWeaponry.isRLCombatLoaded)
			return;
		
		KeyBinding keyAttack = Minecraft.getMinecraft().gameSettings.keyBindAttack;
		
		if(keyAttack.getKeyCode() < 0 && ev.getButton() == keyAttack.getKeyCode() + 100 && ev.isButtonstate())
		{
			Minecraft mc = Minecraft.getMinecraft();
			EntityPlayer player = mc.player;
			if(player == null || player.isActiveItemStackBlocking())	
				return;
			
			ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
			
			WeaponProperty reachProperty = null;
			if(!stack.isEmpty())
			{
				if(stack.getItem() instanceof IWeaponPropertyContainer)
				{
					IWeaponPropertyContainer container = (IWeaponPropertyContainer) stack.getItem();
					reachProperty = container.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_REACH);
				}
				
				if(reachProperty != null)
				{
					double initialReach = player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).getAttributeValue();
					float reach = reachProperty.getMagnitude() - 5.0f + (float)initialReach;
					Log.debug("Initial reach value: " + initialReach + " - Extended reach value: " + reach);
					// Attempt at a fix for double attacking when in a close range (not extended reach range)
					if(mc.pointedEntity != null)
						return;
					RayTraceResult result = getMouseOverExtended(reach);
					
					if(result != null)
					{
						if(result.entityHit != null && result.entityHit.hurtResistantTime == 0 && result.entityHit != player && result.entityHit != player.getRidingEntity())
						{
							float velocity = 0.0f;
							if(player.isRiding())
							{
								Entity riding = player.getRidingEntity();
								Vec3d vec = new Vec3d(riding.motionX, riding.motionY, riding.motionZ);
								velocity = (float)vec.length();
							}
							NetworkHandler.sendPacketToServer(new PacketLongReachAttack(result.entityHit.getEntityId(), velocity));
						}
					}
				}
			}
		}
	}
	
	protected static RayTraceResult getMouseOverExtended(float distance)
	{
		RayTraceResult result = null;
		Minecraft mc = Minecraft.getMinecraft();
		Entity renderViewEntity = mc.getRenderViewEntity();

        if (renderViewEntity != null)
        {
            if (mc.world != null)
            {
//                mc.pointedEntity = null;
                double d0 = distance;
                result = renderViewEntity.rayTrace(d0, 0);
                Vec3d eyePos = renderViewEntity.getPositionEyes(0);
                boolean flag = false;
//                int i = 3;
                double d1 = d0;

                if (mc.playerController.extendedReach() && d1 < 6.0D)
                {
                    d1 = 6.0D;
                    d0 = d1;
                }
                else
                {
                    if (d0 > distance)
                    {
                        flag = true;
                    }
                }

                if (result != null)
                {
                    d1 = result.hitVec.distanceTo(eyePos);
                }

                Vec3d lookVec = renderViewEntity.getLook(0);
                Vec3d vec3d2 = eyePos.add(lookVec.x * d0, lookVec.y * d0, lookVec.z * d0);
                Entity pointedEntity = null;
                Vec3d vec3d3 = null;
//                float f = 1.0F;
                List<Entity> list = mc.world.getEntitiesInAABBexcluding(renderViewEntity, renderViewEntity.getEntityBoundingBox().expand(lookVec.x * d0, lookVec.y * d0, lookVec.z * d0).expand(1.0D, 1.0D, 1.0D), Predicates.and(EntitySelectors.NOT_SPECTATING, new Predicate<Entity>()
                {
                    public boolean apply(@Nullable Entity p_apply_1_)
                    {
                        return p_apply_1_ != null && p_apply_1_.canBeCollidedWith();
                    }
                }));
                double d2 = d1;

                for (int j = 0; j < list.size(); ++j)
                {
                    Entity entity1 = list.get(j);
                    AxisAlignedBB axisalignedbb = entity1.getEntityBoundingBox().grow(entity1.getCollisionBorderSize());
                    RayTraceResult result0 = axisalignedbb.calculateIntercept(eyePos, vec3d2);

                    if (axisalignedbb.contains(eyePos))
                    {
                        if (d2 >= 0.0D)
                        {
                            pointedEntity = entity1;
                            vec3d3 = result0 == null ? eyePos : result0.hitVec;
                            d2 = 0.0D;
                        }
                    }
                    else if (result0 != null)
                    {
                        double d3 = eyePos.distanceTo(result0.hitVec);

                        if (d3 < d2 || d2 == 0.0D)
                        {
                            if (entity1.getLowestRidingEntity() == renderViewEntity.getLowestRidingEntity() && !renderViewEntity.canRiderInteract())
                            {
                                if (d2 == 0.0D)
                                {
                                    pointedEntity = entity1;
                                    vec3d3 = result0.hitVec;
                                }
                            }
                            else
                            {
                                pointedEntity = entity1;
                                vec3d3 = result0.hitVec;
                                d2 = d3;
                            }
                        }
                    }
                }

                if (pointedEntity != null && flag && eyePos.distanceTo(vec3d3) > distance)
                {
                    pointedEntity = null;
                    result = new RayTraceResult(RayTraceResult.Type.MISS, vec3d3, (EnumFacing)null, new BlockPos(vec3d3));
                    Log.debug("Ray Trace for extended reach has missed!");
                }

                if (pointedEntity != null && (d2 < d1 || result == null))
                {
                    result = new RayTraceResult(pointedEntity, vec3d3);
                    Log.debug("Ray Trace for extended reach has hit a " + pointedEntity.toString() + " d1 = " + d1 + " - d2 = " + d2);
                    
                    if (pointedEntity instanceof EntityLivingBase || pointedEntity instanceof EntityItemFrame)
                    {
//                        mc.pointedEntity = pointedEntity;
                    }
                }
            }
        }
        
        return result;
	}
	
	/**
	 * Changes the FOV for applicable weapons. Applies to the Longbow, Crossbow and Throwing Weapons.
	 * @param ev
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onFOVUpdate(FOVUpdateEvent ev)
	{
		if(ConfigHandler.disableFOVZoomIn)	return;
		
		EntityPlayer player = ev.getEntity();
		
		ItemStack activeStack = player.getActiveItemStack();
		if(activeStack.isEmpty())	return;
		
		float currentFov = ev.getFov();
		
		float fovZoomIn = 0.05f;	// The total fov change for zooming in.
		float newFov = currentFov;
		int ticksToZoomIn = 0;
		
		int currentTicks = activeStack.getMaxItemUseDuration() - player.getItemInUseCount();
		
		if(activeStack.getItem() instanceof ItemLongbow)
		{
//			ticksToZoomIn = (int) (ConfigHandler.multiplierLongbow * 20);
			ticksToZoomIn = ((ItemLongbow)activeStack.getItem()).getDrawTicks();
		}
		else if(activeStack.getItem() instanceof ItemCrossbow && NBTHelper.getBoolean(activeStack, ItemCrossbow.NBT_IS_LOADED))
		{
			ticksToZoomIn = ((ItemCrossbow)activeStack.getItem()).getAimTicks(activeStack);
		}
		else if(activeStack.getItem() instanceof ItemDagger || activeStack.getItem() instanceof ItemThrowingWeapon)
		{
			ticksToZoomIn = 5;
			fovZoomIn = 0.1f;
		}
		
		if(ticksToZoomIn > 0)
		{
			if(currentTicks >= ticksToZoomIn)
				currentTicks = ticksToZoomIn;
			
			newFov = currentFov - (fovZoomIn * (currentTicks / (float)ticksToZoomIn));
//			Log.info("New FOV = " + newFov + " Current Ticks = " + currentTicks);
			ev.setNewfov(newFov);
		}
	}
	
	/*@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onInputEvent(InputEvent ev)
	{
		Minecraft mc = Minecraft.getMinecraft();
		EntityPlayer player = mc.player;
		
		if(player != null)
		{
			for(KeyBinding kb : mc.gameSettings.keyBindings)
			{
				if(kb.getKeyCode() == mc.gameSettings.keyBindAttack.getKeyCode())
				{
					if(kb.isPressed())
					{
						LogHelper.info("Attack pressed! " + kb.getDisplayName());
						
						ItemStack stack = player.getItemStackFromSlot(EntityEquipmentSlot.MAINHAND);
						
						WeaponProperty reachProperty = null;
						if(!stack.isEmpty())
						{
							if(stack.getItem() instanceof IWeaponPropertyContainer)
							{
								IWeaponPropertyContainer container = (IWeaponPropertyContainer) stack.getItem();
								reachProperty = container.getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_REACH);
							}
							
							if(reachProperty != null)
							{
								float reach = reachProperty.getMagnitude();
								RayTraceResult result = getMouseOverExtended(reach);
								
								if(result != null)
								{
									if(result.entityHit != null && result.entityHit.hurtResistantTime == 0 && result.entityHit != player)
									{
										NetworkHandler.sendPacketToServer(new PacketLongReachAttack(result.entityHit.getEntityId()));
									}
								}
							}
						}
					}
					break;
				}
			}
		}
	}*/
	
	/**
	 * Adds the tooltip for throwing damage to the end of the Throwing Weapon
	 * @param ev
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onTooltip(ItemTooltipEvent ev)
	{
		ItemStack stack = ev.getItemStack();
		
		if(stack.getItem() instanceof ItemThrowingWeapon)
		{
			ItemThrowingWeapon weapon = (ItemThrowingWeapon)stack.getItem();
			
			int j = EnchantmentHelper.getEnchantmentLevel(EnchantmentRegistrySW.THROWING_DAMAGE, stack);
	    	float throwDamage = (weapon.getDirectAttackDamage() + 1.0f) * weapon.getThrownDamageMultiplier() + (j * 0.5f);
	    	//String throwDamageStr = ;
	    	ev.getToolTip().add("");
	    	ev.getToolTip().add(StringHelper.translateString("modifiers.thrown", "tooltip"));
	    	ev.getToolTip().add(" " + StringHelper.translateFormattedString("attribute.damage", "tooltip", ModSpartanWeaponry.ID, ItemStack.DECIMALFORMAT.format(throwDamage)));
		}

        // Debug (Show NBT data on *EVERYTHING*)
        if(ModSpartanWeaponry.debugMode && stack.hasTagCompound() && ev.getFlags().isAdvanced())
        	ev.getToolTip().add(TextFormatting.DARK_PURPLE + "NBT: " + TextFormatting.DARK_GRAY + stack.getTagCompound().toString());
	}
	
	/**
	 * Event to prevent rendering of items in the opposite hands of Longbows and Crossbows
	 * @param ev
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onSpecificHandRender(RenderSpecificHandEvent ev)
	{
		EntityPlayer player = Minecraft.getMinecraft().player;

		if(player.getActiveHand() != ev.getHand())
		{
			ItemStack activeStack = player.getActiveItemStack();
			
			if(activeStack.getItem() instanceof ItemLongbow)
				ev.setCanceled(true);
			else if(activeStack.getItem() instanceof ItemCrossbow)
				ev.setCanceled(true);
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.HIGHEST)
	public static void onRenderGameOverlayPre(RenderGameOverlayEvent.Pre ev)
	{
		if(ev.getType() == ElementType.CROSSHAIRS)
		{
			Minecraft mc = Minecraft.getMinecraft();
//			GameSettings settings = mc.gameSettings;
			EntityPlayer player = Minecraft.getMinecraft().player;
			int x = ev.getResolution().getScaledWidth() / 2, y = ev.getResolution().getScaledHeight() / 2;
			
			if(player.isSpectator())
				return;
			
			ItemStack equipStack = ItemStack.EMPTY;
			ItemStack mainStack = player.getHeldItemMainhand().isEmpty() /*|| player.getHeldItemMainhand().getMaxItemUseDuration() == 0*/ ? player.getHeldItemOffhand() : player.getHeldItemMainhand();
			ItemStack activeStack = player.getActiveItemStack();
			
//			equipStack = player.isHandActive() && activeStack.getItem() instanceof ItemCrossbow ? activeStack : mainStack.getItem() instanceof ItemCrossbow ? mainStack : ItemStack.EMPTY;
			
			// Pecking order!
			equipStack = player.isHandActive() && activeStack.getItem() instanceof ItemCrossbow ? activeStack : ItemStack.EMPTY;
			if(!equipStack.isEmpty())
			{
				renderCrossbowCrosshair(ev, mc, player, equipStack, x, y);
				return;
			}
			equipStack = player.isHandActive() && activeStack.getItem() instanceof ItemThrowingWeapon ? activeStack : mainStack.getItem() instanceof ItemThrowingWeapon ? mainStack : ItemStack.EMPTY;
			if(!equipStack.isEmpty())
			{
				renderThrowingWeaponCrosshair(ev, mc, player, equipStack, x, y);
				return;
			}
			equipStack = mainStack.getItem() instanceof ItemCrossbow ? mainStack : ItemStack.EMPTY;
			if(!equipStack.isEmpty())
			{
				renderCrossbowCrosshair(ev, mc, player, equipStack, x, y);
				return;
			}
			equipStack = mainStack.getItem() instanceof ItemThrowingWeapon ? mainStack : ItemStack.EMPTY;
			if(!equipStack.isEmpty())
			{
				renderThrowingWeaponCrosshair(ev, mc, player, equipStack, x, y);
				return;
			}
		}
	}
	
	private static void renderCrossbowCrosshair(RenderGameOverlayEvent.Pre ev, Minecraft mc, EntityPlayer player, ItemStack equipStack, int x, int y)
	{
		GameSettings settings = mc.gameSettings;
		if((!ConfigHandler.disableNewCrosshairCrossbow  || ConfigHandler.forceCompatibilityCrosshairs) &&
				settings.thirdPersonView == 0 && !equipStack.isEmpty())
		{
			ItemStack crossbow = player.getActiveItemStack();
			
			int offset = 80 / ev.getResolution().getScaleFactor();
			if(player.isHandActive() && NBTHelper.getBoolean(equipStack, ItemCrossbow.NBT_IS_LOADED))
			{
				int currentTicks = equipStack.getMaxItemUseDuration() - player.getItemInUseCount();

				if(!(crossbow.getItem() instanceof ItemCrossbow))
					return;
				ItemCrossbow crossbowItem = (ItemCrossbow)crossbow.getItem();
				// Added a paradox-absorbing crumple zone by checking load ticks so a division by zero doesn't occur
				int aimTicks = crossbowItem.getAimTicks(crossbow);
				float percentage = aimTicks == 0 ? 1.0f : MathHelper.clamp((currentTicks + ev.getPartialTicks()) / aimTicks, 0.0f, 1.0f);
				offset *= (1.0f - percentage);
				//LogHelper.info("Partial Ticks: " + Float.toString(ev.getPartialTicks()));
			}
			if(settings.showDebugInfo && !settings.hideGUI && !mc.player.hasReducedDebug() && !settings.reducedDebugInfo)	return;
	
			//mc.getTextureManager().bindTexture(mc.ingameGUI.ICONS);
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        GlStateManager.disableBlend();
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        GlStateManager.enableBlend();
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	        
			mc.getTextureManager().bindTexture(CROSSHAIR_TEXTURES);
	        //mc.ingameGUI.drawTexturedModalRect(x, y, 0, 0, 16, 16);
	        // Draw the Crosshair parts
			
			// Better Combat compatible crosshair
			if(ModSpartanWeaponry.isRLCombatLoaded || ConfigHandler.forceCompatibilityCrosshairs)
			{
				// New offset
				// sqrt(a^2 + b^2) = c
				// Invert -> sqrt(c^2 / 2) because a = b
				offset = (int) Math.sqrt((offset * offset) / 2.0);	// Inverse pythagoras for two equal opposite and adjacent sides
	            mc.ingameGUI.drawTexturedModalRect(x - 5 - offset, y - 5 - offset, 11, 12, 4, 4);		// Top-Left Part
	            mc.ingameGUI.drawTexturedModalRect(x + 2 + offset, y - 5 - offset, 18, 12, 4, 4);		// Top-Right Part
	            mc.ingameGUI.drawTexturedModalRect(x - 5 - offset, y + 2 + offset, 11, 19, 4, 4);		// Bottom-Left Part
	            mc.ingameGUI.drawTexturedModalRect(x + 2 + offset, y + 2 + offset, 18, 19, 4, 4);		// Bottom-Right Part
			}
			// Vanilla crosshair
			else
			{
	            mc.ingameGUI.drawTexturedModalRect(x - 1, y - 1, 4, 4, 3, 3);			// Center Part
	            mc.ingameGUI.drawTexturedModalRect(x, y - 5 - offset, 5, 0, 1, 4);		// Top Part
	            mc.ingameGUI.drawTexturedModalRect(x, y + 2 + offset, 5, 7, 1, 4);		// Bottom Part
	            mc.ingameGUI.drawTexturedModalRect(x - 5 - offset, y, 0, 5, 4, 1);		// Left Part
	            mc.ingameGUI.drawTexturedModalRect(x + 2 + offset, y, 7, 5, 4, 1);		// Right Part
				ev.setCanceled(true);
			}
	        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        GlStateManager.disableBlend();
		}
	}
	
	private static void renderThrowingWeaponCrosshair(RenderGameOverlayEvent.Pre ev, Minecraft mc, EntityPlayer player, ItemStack equipStack, int x, int y)
	{
		GameSettings settings = mc.gameSettings;
		if((!ConfigHandler.disableNewCrosshairThrowingWeapon || ConfigHandler.forceCompatibilityCrosshairs) &&
				settings.thirdPersonView == 0 && !equipStack.isEmpty())
			{
	//		ItemStack throwingWeaponStack = player.getHeldItemMainhand();
			ItemThrowingWeapon throwingWeapon = (ItemThrowingWeapon)equipStack.getItem();
			int offset = ModSpartanWeaponry.isRLCombatLoaded ? 24 : 10; /** ev.getResolution().getScaleFactor()*/;
			if(player.isHandActive())
			{
				int currentTicks = equipStack.getMaxItemUseDuration() - player.getItemInUseCount();
				float percentage = MathHelper.clamp((currentTicks + ev.getPartialTicks()) / throwingWeapon.getMaxChargeTicks(equipStack), 0.0f, 1.0f);
				offset *= (1.0f - percentage);
			}
			
			if(settings.showDebugInfo && !settings.hideGUI && !mc.player.hasReducedDebug() && !settings.reducedDebugInfo)	return;
			
			GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.ONE_MINUS_DST_COLOR, GlStateManager.DestFactor.ONE_MINUS_SRC_COLOR, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        GlStateManager.enableBlend();
	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
	        
			mc.getTextureManager().bindTexture(CROSSHAIR_TEXTURES);
			
			if(ModSpartanWeaponry.isRLCombatLoaded || ConfigHandler.forceCompatibilityCrosshairs)
			{
				offset = (int) Math.sqrt((offset * offset) / 2.0);	// Inverse pythagoras for two equal opposite and adjacent sides
	            mc.ingameGUI.drawTexturedModalRect(x - 5 - offset, y - 5 - offset, 11, 12, 4, 4);		// Top-Left Part
	            mc.ingameGUI.drawTexturedModalRect(x + 2 + offset, y - 5 - offset, 18, 12, 4, 4);		// Top-Right Part
	            mc.ingameGUI.drawTexturedModalRect(x - 5 - offset, y + 2 + offset, 11, 19, 4, 4);		// Bottom-Left Part
	            mc.ingameGUI.drawTexturedModalRect(x + 2 + offset, y + 2 + offset, 18, 19, 4, 4);		// Bottom-Right Part
			}
			else
			{
	            mc.ingameGUI.drawTexturedModalRect(x - 4, y - 4, 12, 1, 9, 5);
	            mc.ingameGUI.drawTexturedModalRect(x - 4, y - 7 - offset, 12, 1, 9, 5);
				ev.setCanceled(true);
			}
	        
	        
	        // Render the attack indicator if applicable and if Better Combat Rebirth is not installed
	        if (mc.gameSettings.attackIndicator == 1 && !ModSpartanWeaponry.isRLCombatLoaded && !ConfigHandler.forceCompatibilityCrosshairs)
	        {
	            mc.getTextureManager().bindTexture(mc.ingameGUI.ICONS);
	            GlStateManager.enableAlpha();
	            float f = player.getCooledAttackStrength(0.0F);
	            boolean flag = false;
	
	            if (mc.pointedEntity != null && mc.pointedEntity instanceof EntityLivingBase && f >= 1.0F)
	            {
	                flag = mc.player.getCooldownPeriod() > 5.0F;
	                flag = flag & ((EntityLivingBase)mc.pointedEntity).isEntityAlive();
	            }
	
	            int i = y - 7 + 16;
	            int j = x - 8;
	
	            if (flag)
	            {
	            	mc.ingameGUI.drawTexturedModalRect(j, i, 68, 94, 16, 16);
	            }
	            else if (f < 1.0F)
	            {
	                int k = (int)(f * 17.0F);
	                mc.ingameGUI.drawTexturedModalRect(j, i, 36, 94, 16, 4);
	                mc.ingameGUI.drawTexturedModalRect(j, i, 52, 94, k, 4);
	            }
	
	            GlStateManager.disableAlpha();
	        }
	        
	        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
	        GlStateManager.disableBlend();
	        
	        // Display ammo if applicable
	        if(equipStack.hasTagCompound() && equipStack.getTagCompound().hasKey(ItemThrowingWeapon.NBT_AMMO_USED))
	        {
	        	NBTTagCompound tag = equipStack.getTagCompound();
	            int maxAmmo = throwingWeapon.getMaxAmmo(equipStack);
	            int ammo = maxAmmo - tag.getInteger(ItemThrowingWeapon.NBT_AMMO_USED);
	            
	            String text = String.format("%d/%d", ammo, maxAmmo);
	            mc.fontRenderer.drawStringWithShadow(text, x - (mc.fontRenderer.getStringWidth(text) / 2), y + 20, 0xFFFFFFFF);
	        }
		}
	}
	
	/**
	 * Renders the Quiver Ammo HUD Element on the screen when applicable
	 * @param ev
	 */
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onRenderGameOverlay(RenderGameOverlayEvent.Post ev)
	{
		if(ev.getType() == ElementType.HOTBAR)
		{
			EntityPlayer player = Minecraft.getMinecraft().player;
			
			/*ItemStack weaponStack = ItemStack.EMPTY;
			ItemStack quiverStack = ItemStack.EMPTY;
			//IHudQuiverDisplay weapon = null;
			//int quiverSlot = -1;
			
			for(IQuiverInfo info : QuiverHelper.info)
			{
				if(info.isWeapon(player.getHeldItemMainhand()))
				{
					weaponStack = player.getHeldItemMainhand();
					quiverStack = QuiverHelper.findFirstOfType(player, info);
					break;
				}
				else if(info.isWeapon(player.getHeldItemOffhand()))
				{
					weaponStack = player.getHeldItemOffhand();
					quiverStack = QuiverHelper.findFirstOfType(player, info);
					break;
				}
			}
			
			String weaponName = weaponStack.getItem().getRegistryName().toString();
			for(String compName : ConfigHandler.quiverBowBlacklist)
			{
				if(compName.equals(weaponName))
					weaponStack = ItemStack.EMPTY;
			}
			
			if(weaponStack.isEmpty() || quiverStack.isEmpty())
			{
				HudQuiverAmmo.hudActive = null;
				//return;
			}
			
			// Init the Hud
			if(!weaponStack.isEmpty() && !quiverStack.isEmpty())
			{
				if(HudQuiverAmmo.hudActive == null)
					HudQuiverAmmo.hudActive = new HudQuiverAmmo(64, 20, quiverStack);
				else
					HudQuiverAmmo.hudActive.setQuiver(quiverStack);
				
				if(HudQuiverAmmo.hudActive != null)
					HudQuiverAmmo.hudActive.render();
			}*/
			
	// Load HUD
			
			int loadSlot = -1;
			ItemStack loadStack = ItemStack.EMPTY;
			IHudLoadState loadItem = null;
			boolean isOffhand = false;
			//boolean isLoaded = false;
			
			if(player.getHeldItemMainhand().getItem() instanceof IHudLoadState)
			{
				loadStack = player.getHeldItemMainhand();
				loadItem = (IHudLoadState)loadStack.getItem();
			}
			else if(player.getHeldItemOffhand().getItem() instanceof IHudLoadState)
			{
				loadStack = player.getHeldItemOffhand();
				loadItem = (IHudLoadState)loadStack.getItem();
				isOffhand = true;
			}
			
			if(loadStack != null && loadItem != null && ((!loadStack.isEmpty() && player.getActiveItemStack().isItemEqual(loadStack)) || loadItem.isLoaded(loadStack)))
			{
				if(HudLoadState.hudActive == null)
					HudLoadState.hudActive = new HudLoadState();
				
				HudLoadState.hudActive.setLoaded(loadItem.isLoaded(loadStack));
				HudLoadState.hudActive.setLoadProgress(loadItem.getLoadProgress(loadStack, player));
				HudLoadState.hudActive.setOffhand(isOffhand);
				
				if(!isOffhand)
				{
					/*for(int i = 0; i < player.inventory.getHotbarSize(); i++)
					{
						if(player.inventory.getStackInSlot(i).isItemEqual(loadStack))
						{
							loadSlot = i;
						}
					}*/
					
					// An optimization/bug-fix; the current main-hand item slot in the hotbar is already stored. Let's use that!
					loadSlot = player.inventory.currentItem;
					HudLoadState.hudActive.setHighlightedSlot(loadSlot);
				}
				
				if(HudLoadState.hudActive != null)
					HudLoadState.hudActive.render();
			}
			else if(HudLoadState.hudActive != null)
				HudLoadState.hudActive = null;
			
			
	// Quiver HUD
			ItemStack quiverStack = ItemStack.EMPTY;
			
			// Check and see if the weapon equipped has an appropriate quiver first  [first pass]
			for(IQuiverInfo info : QuiverHelper.info)
			{
				if(info.isWeapon(player.getHeldItemMainhand()))
				{
					quiverStack = QuiverHelper.findFirstOfType(player, info);
					break;
				}
			}
			
			// Now check and find the first available quiver if none was found in the first pass [second pass]
			if(quiverStack.isEmpty())
			{
//				for(IQuiverInfo info : QuiverHelper.info)
//				{
					quiverStack = QuiverHelper.findFirst(player);
//					if(!quiverStack.isEmpty())
//						break;
//				}
			}
			
			if(quiverStack.isEmpty())
			{
				HudQuiverAmmo.hudActive = null;
				activeQuiverStack = ItemStack.EMPTY;
			}
			else
			{
				activeQuiverStack = quiverStack;
				// Initialize the Quiver Ammo HUD
				if(!ModSpartanWeaponry.isSpartanHudBaublesLoaded || !QuiverHelper.isInBaublesSlot(player, activeQuiverStack))
				{
					if(HudQuiverAmmo.hudActive == null)
						HudQuiverAmmo.hudActive = new HudQuiverAmmo(22, 22, activeQuiverStack);
					else
						HudQuiverAmmo.hudActive.setQuiver(activeQuiverStack);
					HudQuiverAmmo.hudActive.render();
				}
			}
		}
	}
	
	public static ItemStack getActiveQuiverStack() 
	{
		return activeQuiverStack;
	}

/*	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public static void onDrawInventoryGui(DrawScreenEvent.Post ev)
	{
		int x = 0;
		int y = 0;
		GuiScreen gui = ev.getGui();
		if(ev.getGui() instanceof GuiInventory)
		{
			GuiInventory guiInv = (GuiInventory)ev.getGui();
			x = guiInv.getGuiLeft() + 77;
			y = guiInv.getGuiTop() + 62;
		}
		else if(ev.getGui() instanceof GuiContainerCreative && ((GuiContainerCreative)ev.getGui()).getSelectedTabIndex() == CreativeTabs.INVENTORY.getIndex())
		{
			GuiContainerCreative guiCr = (GuiContainerCreative)ev.getGui();
			x = guiCr.getGuiLeft() + 35;
			y = guiCr.getGuiTop() + 20;
		}
		if(x != 0 && y != 0)
		{
			Minecraft mc = Minecraft.getMinecraft();
			EntityPlayer player = mc.player;
			
			// Find any main- or off-hand item with Two-Handed (priority on main-hand)
			ItemStack item = player.getHeldItemMainhand();
			ItemStack offhand = player.getHeldItemOffhand();
			if(item.getItem() instanceof IWeaponPropertyContainer && ((IWeaponPropertyContainer)item.getItem()).getFirstWeaponPropertyWithType(WeaponProperties.PROPERTY_TYPE_TWO_HANDED) != null)
			{
				// Render item in other hand
				//GlStateManager.enableBlend();
				//GlStateManager.color(1.0f, 1.0f, 1.0f, 0.5f);
				//gui.drawRect(x, y, x + 16, y + 16, offhand.isEmpty() ? 0xFF202020 : 0xFFC02020);
				if(offhand.isEmpty())
				{
//					GlStateManager.disableLighting();
					mc.getRenderItem().renderItemAndEffectIntoGUI(item, x, y);
//					GlStateManager.depthFunc(516);
//					gui.drawRect(x, y, x + 16, y + 16, offhand.isEmpty() ? 0xFF202020 : 0xFFC02020);
//					GlStateManager.depthFunc(515);
//					GlStateManager.enableLighting();
				}
				//GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
				//GlStateManager.disableBlend();
			}
		}
	}*/
	
/*	public static int tickCounter = 0;
	
	@SuppressWarnings("unused")
	@SubscribeEvent
	public static void onClientTick(ClientTickEvent ev)
	{
		if(!Minecraft.getMinecraft().isGamePaused())
			tickCounter++;
	}
	
	@SubscribeEvent
	public static void onHandRender(RenderSpecificHandEvent ev)
	{
		Minecraft mc = Minecraft.getMinecraft();
		
		AbstractClientPlayer player = mc.player;
		
		if(player.getHeldItemMainhand().isEmpty())
		{
			EnumHandSide side = ev.getHand() == EnumHand.MAIN_HAND ? player.getPrimaryHand() : player.getPrimaryHand().opposite();

			GlStateManager.pushMatrix();
			float equipProgress = ev.getEquipProgress();
			float swingProgress = ev.getSwingProgress();
			
			boolean flag = side != EnumHandSide.LEFT;
	        float f = flag ? 1.0F : -1.0f;
	        float f1 = MathHelper.sqrt(swingProgress);
	        float f2 = -0.3F * MathHelper.sin(f1 * (float)Math.PI);
	        float f3 = 0.4F * MathHelper.sin(f1 * ((float)Math.PI * 2F));
	        float f4 = -0.4F * MathHelper.sin(swingProgress * (float)Math.PI);
	        
	        float movement = MathHelper.sin((tickCounter + ev.getPartialTicks()) * 0.05f) * 0.05f;

	        GlStateManager.translate(f * (f2 + 0.64000005F) + (movement * 0.5f), f3 + -0.6F + equipProgress * -0.6F + movement, f4 + -0.71999997F);
	        GlStateManager.rotate(f * 45.0F, 0.0F, 1.0F, 0.0F);
	        float f5 = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
	        float f6 = MathHelper.sin(f1 * (float)Math.PI);
	        GlStateManager.rotate(f * f6 * 70.0F, 0.0F, 1.0F, 0.0F);
	        GlStateManager.rotate(f * f5 * -20.0F + (movement * 90.0f), 0.0F, 0.0F, 1.0F);
	        GlStateManager.translate(f * -1.0F, 3.6F, 3.5F);
	        GlStateManager.rotate(f * 120.0F, 0.0F, 0.0F, 1.0F);
	        GlStateManager.rotate(200.0F, 1.0F, 0.0F, 0.0F);
	        GlStateManager.rotate(f * -135.0F, 0.0F, 1.0F, 0.0F);
	        GlStateManager.translate(f * 5.6F, 0.0F, 0.0F);

			mc.getTextureManager().bindTexture(player.getLocationSkin());
	        
			RenderPlayer render = (RenderPlayer)mc.getRenderManager().<AbstractClientPlayer>getEntityRenderObject(player);
	        GlStateManager.disableCull();
	        
	        if(flag)
			{
				render.renderRightArm(player);
			}

	        GlStateManager.enableCull();
			GlStateManager.popMatrix();
			ev.setCanceled(true);
			return;
		}
		
		EnumHandSide side = ev.getHand() == EnumHand.MAIN_HAND ? player.getPrimaryHand() : player.getPrimaryHand().opposite();

		GlStateManager.pushMatrix();
		float equipProgress = ev.getEquipProgress();
		float swingProgress = ev.getSwingProgress();
		
		boolean flag = side != EnumHandSide.LEFT;
        float f = flag ? 1.0F : -1.0f;
        float f1 = MathHelper.sqrt(swingProgress);
        float f2 = -0.3F * MathHelper.sin(f1 * (float)Math.PI);
        float f3 = 0.4F * MathHelper.sin(f1 * ((float)Math.PI * 2F));
        float f4 = -0.4F * MathHelper.sin(swingProgress * (float)Math.PI);
        
        GlStateManager.translate(f * (f2 + 0.64000005F), f3 + -0.6F + equipProgress * -0.6F, f4 + -0.51999997F);
        GlStateManager.rotate(f * 45.0F, 0.0F, 1.0F, 0.0F);
        float f5 = MathHelper.sin(swingProgress * swingProgress * (float)Math.PI);
        float f6 = MathHelper.sin(f1 * (float)Math.PI);
        GlStateManager.rotate(f * f6 * 150.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.rotate(f * f5 * -20.0F, 0.0F, 0.0F, 1.0F);
		mc.getTextureManager().bindTexture(player.getLocationSkin());
        GlStateManager.translate(f * -1.0F, 3.6F, 3.5F);
        GlStateManager.rotate(f * 120.0F, 0.0F, 0.0F, 1.0F);
        GlStateManager.rotate(200.0F, 1.0F, 0.0F, 0.0F);
        GlStateManager.rotate(f * -135.0F, 0.0F, 1.0F, 0.0F);
        GlStateManager.translate(f * 5.6F, 0.0F, 0.0F);
        
        if(flag)
        {
            GlStateManager.rotate(-115.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(15.0f, 0.0f, 0.0f, 1.0f);
        	GlStateManager.translate(0.625f, -0.25f, 0.3f);
        }

		mc.getTextureManager().bindTexture(player.getLocationSkin());
        
		RenderPlayer render = (RenderPlayer)mc.getRenderManager().<AbstractClientPlayer>getEntityRenderObject(player);
        GlStateManager.disableCull();
		
		if(flag)
		{
			render.renderRightArm(player);
		}
		else
		{
//			render.renderLeftArm(player);
		}
		
        GlStateManager.enableCull();
		GlStateManager.popMatrix();
//		ev.setCanceled(true);
	}*/
}

package com.oblivioussp.spartanweaponry.event;

import org.lwjgl.glfw.GLFW;

import com.oblivioussp.spartanweaponry.api.APIAttributes;
import com.oblivioussp.spartanweaponry.client.KeyBinds;
import com.oblivioussp.spartanweaponry.network.KeyHandlePacket;
import com.oblivioussp.spartanweaponry.network.LongReachAttackPacket;
import com.oblivioussp.spartanweaponry.network.NetworkHandler;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileHelper;
import net.minecraft.util.Direction;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.client.event.InputEvent.MouseInputEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEventHandler 
{
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onMouseEvent(MouseInputEvent ev)
	{
		if(Minecraft.getInstance().world == null || Minecraft.getInstance().currentScreen != null || Minecraft.getInstance().isGamePaused())
			return;
		
		KeyBinding keyAttack = Minecraft.getInstance().gameSettings.keyBindAttack;

		// Check if the attack key is being pressed (first press only; no repeat presses)
		if(ev.getButton() == keyAttack.getKey().getKeyCode() && ev.getAction() == GLFW.GLFW_PRESS)
		{
			checkForReachAttack();
		}
		if(ev.getButton() == KeyBinds.KEY_ACCESS_QUIVER.getKey().getKeyCode() && ev.getAction() == GLFW.GLFW_PRESS)
		{
			NetworkHandler.sendPacketToServer(new KeyHandlePacket());
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
	public static void onKeyEvent(KeyInputEvent ev)
	{
		if(Minecraft.getInstance().world == null || Minecraft.getInstance().currentScreen != null || Minecraft.getInstance().isGamePaused())
			return;
		
		KeyBinding keyAttack = Minecraft.getInstance().gameSettings.keyBindAttack;

		// Check if the attack key is being pressed (first press only; no repeat presses)
		if(ev.getKey() == keyAttack.getKey().getKeyCode() && ev.getAction() == GLFW.GLFW_PRESS)
		{
			checkForReachAttack();
		}
		if(ev.getKey() == KeyBinds.KEY_ACCESS_QUIVER.getKey().getKeyCode() && ev.getAction() == GLFW.GLFW_PRESS)
		{
			NetworkHandler.sendPacketToServer(new KeyHandlePacket());
		}
	}

	private static void checkForReachAttack()
	{
		Minecraft mc = Minecraft.getInstance();
		PlayerEntity player = mc.player;
		
		// Ensure the following
		// - The game is NOT paused
		// - The game is NOT in any GUI
		// - The game is loaded into a world
		// - The player is valid
		// - The player is blocking with a shield
		// If not, then don't continue the attack
		if(player == null || player.isActiveItemStackBlocking())
			return;
		
		double reach = (float)player.getAttributeValue(APIAttributes.ATTACK_REACH);
		if(reach == APIAttributes.ATTACK_REACH.getDefaultValue())
			return;
		
		RayTraceResult rayTrace = getEntityMouseOverExtended((float)reach);
		
		if(!(rayTrace instanceof EntityRayTraceResult))
			return;
		
		EntityRayTraceResult entityRayTrace = (EntityRayTraceResult)rayTrace;
		Entity entityHit = entityRayTrace.getEntity();
		
		if(entityHit != null && entityHit.hurtResistantTime == 0 && entityHit != player && entityHit != player.getRidingEntity())
		{
			float velocity = 0.0f;
			if(player.getRidingEntity() != null)
			{
				Entity riding = player.getRidingEntity();
				Vector3d vec = riding.getMotion();
				velocity = (float)vec.length();
			}
			NetworkHandler.sendPacketToServer(new LongReachAttackPacket(entityHit.getEntityId(), velocity));
			Log.debug("Long Reach Packet sent!");
		}
	}

	private static RayTraceResult getEntityMouseOverExtended(float reach)
	{
		RayTraceResult result = null;
		Minecraft mc = Minecraft.getInstance();
		Entity viewEntity = mc.getRenderViewEntity();
		
		if(viewEntity != null)
		{
			double d0 = (double)reach;
			RayTraceResult rayTrace = viewEntity.pick(d0, 0, false);
			Vector3d eyePos = viewEntity.getEyePosition(0);
			boolean flag = false;
			double d1 = d0;
			
			if(mc.playerController.extendedReach() && d1 < 6.0D)
			{
				d1 = 6.0D;
				d0 = d1;
			}
			else if(d0 > reach)
				flag = true;
			
			d1 = d1 * d1;
			
			if(rayTrace != null)
				d1 = rayTrace.getHitVec().squareDistanceTo(eyePos);
			
			Vector3d lookVec = viewEntity.getLook(1.0f);
			Vector3d attackVec = eyePos.add(lookVec.x * d0, lookVec.y * d0, lookVec.z * d0);
			
			AxisAlignedBB expBounds = viewEntity.getBoundingBox().expand(lookVec.scale(d0)).grow(1.0D, 1.0D, 1.0D);
			EntityRayTraceResult entityRayTrace = ProjectileHelper.rayTraceEntities(viewEntity, eyePos, attackVec, expBounds, (entity) -> 
			{ 
				return !entity.isSpectator() && entity.canBeCollidedWith();
			}, d1);
			
			if(entityRayTrace != null)
			{
				Vector3d hitVec = entityRayTrace.getHitVec();
				double d2 = eyePos.squareDistanceTo(hitVec);
				if(flag && d2 > (reach * reach))
					result = BlockRayTraceResult.createMiss(hitVec, Direction.getFacingFromVector(lookVec.x, lookVec.y, lookVec.z), new BlockPos(hitVec));
				
				else if(d2 < d1 || result == null)
					result = entityRayTrace;
			}
			else
			{
				result = BlockRayTraceResult.createMiss(attackVec, Direction.getFacingFromVector(lookVec.x, lookVec.y, lookVec.z), new BlockPos(attackVec));
			}
		}
		
		return result;
	}
	
	/*@SubscribeEvent
	public static void onTooltip(ItemTooltipEvent ev)
	{
		ItemStack stack = ev.getItemStack();

        // Debug (Show NBT data on *EVERYTHING*)
        if(ModSpartanWeaponry.debugMode && stack.hasTag() && ev.getFlags().isAdvanced())
        {
        	// Format NBT debug string
        	String nbtStr = stack.getTag().toString();
        	ev.getToolTip().add(new StringTextComponent("NBT: " + TextFormatting.DARK_GRAY + nbtStr).mergeStyle(TextFormatting.DARK_PURPLE));
        }
	}*/
}

package com.oblivioussp.spartanweaponry.client;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants.Type;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(value = Dist.CLIENT, bus = Bus.MOD)
public class KeyBinds
{
	public static KeyMapping KEY_ACCESS_QUIVER = new KeyMapping("key." + ModSpartanWeaponry.ID + ".access_quiver", KeyConflictContext.IN_GAME, Type.KEYSYM, GLFW.GLFW_KEY_I, "key." + ModSpartanWeaponry.ID + ".category.title");

	public static void registerKeyBinds(RegisterKeyMappingsEvent ev)
	{
		ev.register(KeyBinds.KEY_ACCESS_QUIVER);
	}
}

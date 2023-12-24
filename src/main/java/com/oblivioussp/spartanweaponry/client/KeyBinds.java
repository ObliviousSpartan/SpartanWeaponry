package com.oblivioussp.spartanweaponry.client;

import org.lwjgl.glfw.GLFW;

import com.mojang.blaze3d.platform.InputConstants.Type;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.settings.KeyConflictContext;

@OnlyIn(Dist.CLIENT)
public class KeyBinds 
{
	public static KeyMapping KEY_ACCESS_QUIVER = new KeyMapping("key." + ModSpartanWeaponry.ID + ".access_quiver", KeyConflictContext.IN_GAME, Type.KEYSYM, GLFW.GLFW_KEY_I, "key." + ModSpartanWeaponry.ID + ".category.title");

	public static void registerKeyBinds()
	{
		ClientRegistry.registerKeyBinding(KeyBinds.KEY_ACCESS_QUIVER);
	}
}

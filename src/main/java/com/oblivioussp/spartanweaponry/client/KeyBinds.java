package com.oblivioussp.spartanweaponry.client;

import org.lwjgl.glfw.GLFW;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.client.settings.KeyBinding;
import net.minecraft.client.util.InputMappings.Type;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.client.registry.ClientRegistry;

@OnlyIn(Dist.CLIENT)
public class KeyBinds 
{
	public static KeyBinding KEY_ACCESS_QUIVER = new KeyBinding("key." + ModSpartanWeaponry.ID + ".access_quiver", KeyConflictContext.IN_GAME, Type.KEYSYM, GLFW.GLFW_KEY_I, "key." + ModSpartanWeaponry.ID + ".category.title");

	public static void registerKeyBinds()
	{
		ClientRegistry.registerKeyBinding(KeyBinds.KEY_ACCESS_QUIVER);
	}
}

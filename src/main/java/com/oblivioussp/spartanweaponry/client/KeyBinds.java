package com.oblivioussp.spartanweaponry.client;

import org.lwjgl.input.Keyboard;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class KeyBinds
{
	public static final KeyBinding KEY_ACCESS_QUIVER = new KeyBinding("key." + ModSpartanWeaponry.ID + ".access_quiver", KeyConflictContext.IN_GAME, Keyboard.KEY_I, "key." + ModSpartanWeaponry.ID + ":category.title");

}

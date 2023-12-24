package com.oblivioussp.spartanweaponry.mixin;

import java.util.Map;

import org.spongepowered.asm.launch.MixinBootstrap;
import org.spongepowered.asm.mixin.Mixins;

import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.12.2")
@IFMLLoadingPlugin.Name("SpartanWeaponry-MixinLoader")
@IFMLLoadingPlugin.SortingIndex(-4000)
public class MixinLoader implements IFMLLoadingPlugin 
{
	public MixinLoader()
	{
//		Log.info("Loading Mixins for Spartan Weaponry!");
		MixinBootstrap.init();
		Mixins.addConfiguration("spartanweaponry.mixins.json");
	}

	@Override
	public String[] getASMTransformerClass() 
	{
		return new String[0];
	}

	@Override
	public String getModContainerClass() 
	{
		return null;
	}

	@Override
	public String getSetupClass() 
	{
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data)
	{

	}

	@Override
	public String getAccessTransformerClass() 
	{
		return null;
	}

}

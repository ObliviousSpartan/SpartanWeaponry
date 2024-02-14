package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.inventory.QuiverArrowMenu;
import com.oblivioussp.spartanweaponry.inventory.QuiverBoltMenu;

import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus 
{
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.Keys.MENU_TYPES, ModSpartanWeaponry.ID);
	
	public static final RegistryObject<MenuType<QuiverArrowMenu>> QUIVER_ARROW = REGISTRY.register("quiver_arrow", () -> IForgeMenuType.create(QuiverArrowMenu::createFromNetwork));
	public static final RegistryObject<MenuType<QuiverBoltMenu>> QUIVER_BOLT = REGISTRY.register("quiver_bolt", () -> IForgeMenuType.create(QuiverBoltMenu::createFromNetwork));
}

package com.oblivioussp.spartanweaponry.init;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.item.crafting.QuiverUpgradeRecipe;
import com.oblivioussp.spartanweaponry.item.crafting.RecipeEnchantmentConverter;
import com.oblivioussp.spartanweaponry.item.crafting.RecipeTippedProjectile;
import com.oblivioussp.spartanweaponry.util.Log;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.oredict.OreIngredient;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = ModSpartanWeaponry.ID)
public class RecipeRegistry 
{
	@SubscribeEvent
	public static void addRecipes(RegistryEvent.Register<IRecipe> ev)
	{
		IForgeRegistry<IRecipe> reg = ev.getRegistry();
	
		Log.info("Registering recipes for Tipped Bolts!");
		reg.register(new RecipeTippedProjectile(ItemRegistrySW.bolt, ItemRegistrySW.boltTipped).setRegistryName(ModSpartanWeaponry.ID, "bolt_tipped"));
		reg.register(new RecipeTippedProjectile(ItemRegistrySW.arrowWood, ItemRegistrySW.arrowTippedWood).setRegistryName(ModSpartanWeaponry.ID, "arrow_wood_tipped"));
		reg.register(new RecipeTippedProjectile(ItemRegistrySW.arrowIron, ItemRegistrySW.arrowTippedIron).setRegistryName(ModSpartanWeaponry.ID, "arrow_iron_tipped"));
		reg.register(new RecipeTippedProjectile(ItemRegistrySW.arrowDiamond, ItemRegistrySW.arrowTippedDiamond).setRegistryName(ModSpartanWeaponry.ID, "arrow_diamond_tipped"));
		reg.register(new RecipeTippedProjectile(ItemRegistrySW.boltDiamond, ItemRegistrySW.boltTippedDiamond).setRegistryName(ModSpartanWeaponry.ID, "bolt_diamond_tipped"));
		
		reg.register(new RecipeEnchantmentConverter().setRegistryName(ModSpartanWeaponry.ID, "enchantment_converter"));
		
		Ingredient leather = new OreIngredient("leather");
		Ingredient ingotGold = new OreIngredient("ingotGold");
		Ingredient diamond = new OreIngredient("gemDiamond");
		Ingredient quiverArrowLight = Ingredient.fromItem(ItemRegistrySW.quiverArrowLight);
		Ingredient quiverArrowModerate = Ingredient.fromItem(ItemRegistrySW.quiverArrowModerate);
		Ingredient quiverBoltLight = Ingredient.fromItem(ItemRegistrySW.quiverBoltLight);
		Ingredient quiverBoltModerate = Ingredient.fromItem(ItemRegistrySW.quiverBoltModerate);
		
		NonNullList<Ingredient> itemsModerateArrowQuiver = NonNullList.<Ingredient>from(Ingredient.EMPTY,
				leather, quiverArrowLight, leather,
				ingotGold, ingotGold, ingotGold);
		reg.register(new QuiverUpgradeRecipe("upgrade_quiver_arrow_moderate", "", 3, 2, itemsModerateArrowQuiver, new ItemStack(ItemRegistrySW.quiverArrowModerate)));

		NonNullList<Ingredient> itemsHeavyArrowQuiver = NonNullList.<Ingredient>from(Ingredient.EMPTY,
				leather, quiverArrowModerate, leather,
				diamond, diamond, diamond);
		reg.register(new QuiverUpgradeRecipe("upgrade_quiver_arrow_heavy", "", 3, 2, itemsHeavyArrowQuiver, new ItemStack(ItemRegistrySW.quiverArrowHeavy)));
		
		NonNullList<Ingredient> itemsModerateBoltQuiver = NonNullList.<Ingredient>from(Ingredient.EMPTY,
				leather, quiverBoltLight, leather,
				ingotGold, ingotGold, ingotGold);
		reg.register(new QuiverUpgradeRecipe("upgrade_quiver_bolt_moderate", "", 3, 2, itemsModerateBoltQuiver, new ItemStack(ItemRegistrySW.quiverBoltModerate)));

		NonNullList<Ingredient> itemsHeavyBoltQuiver = NonNullList.<Ingredient>from(Ingredient.EMPTY,
				leather, quiverBoltModerate, leather,
				diamond, diamond, diamond);
		reg.register(new QuiverUpgradeRecipe("upgrade_quiver_bolt_heavy", "", 3, 2, itemsHeavyBoltQuiver, new ItemStack(ItemRegistrySW.quiverBoltHeavy)));
		
		//PotionHelper.addContainer(ItemRegistrySW.weaponOil);
		//PotionHelper.addContainerRecipe(Items.POTIONITEM, ItemRegistrySW.greaseball, ItemRegistrySW.weaponOil);
		
		//BrewingRecipeRegistry.addRecipe(new BrewingRecipeWeaponOil());
		
		Log.info("Recipes added!");
	}
}

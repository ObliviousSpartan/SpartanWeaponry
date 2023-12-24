package com.oblivioussp.spartanweaponry.item;

/*import java.util.List;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.init.ModItems;
import com.oblivioussp.spartanweaponry.util.WeaponOilEffect;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class WeaponOilItem extends BasicItem 
{
	public static final String NBT_OIL = "Oil";
	public static IForgeRegistry<WeaponOilEffect> weaponOilRegistry;

	public WeaponOilItem(String regName) 
	{
		super(regName, new Item.Properties().group(ModItems.GROUP_SW).maxStackSize(1));
	}

	@Override
	public void fillItemGroup(ItemGroup group, NonNullList<ItemStack> items) 
	{
//		super.fillItemGroup(group, items);
		if (this.isInGroup(group))
		{
//			for(RegistryObject<WeaponOilEffect> oil : ModRegistries.WeaponOilRegister.getEntries())
//			{
//				ItemStack item = new ItemStack(this);
//				if(oil.isPresent())
//					setOilInStack(item, oil.get());
//				items.add(item);
//			}
		}
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn)
	{
		super.addInformation(stack, worldIn, tooltip, flagIn);
		WeaponOilEffect oil = getOilFromStack(stack);
		if(oil != null)
		{
			tooltip.add(new StringTextComponent(""));
			tooltip.add(new TranslationTextComponent("potion.whenDrank").mergeStyle(TextFormatting.DARK_PURPLE));
			tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".weapon_oil.applied." + oil.getRegistryName().getPath(), 4.0f).mergeStyle(TextFormatting.BLUE));
			tooltip.add(new TranslationTextComponent("tooltip." + ModSpartanWeaponry.ID + ".weapon_oil.uses", 30).mergeStyle(TextFormatting.DARK_GREEN));
		}
		//tooltip.add(new StringTextComponent(stack.getOrCreateTag().getString(NBT_OIL)));
	}
	
	@Override
	public ITextComponent getDisplayName(ItemStack stack) 
	{
		WeaponOilEffect oil = getOilFromStack(stack);
		if(oil != null)
			return new TranslationTextComponent(this.getTranslationKey() + "." + oil.getRegistryName().getPath());
		
		return super.getDisplayName(stack);
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) 
	{
		// TODO: Allow this to apply it's effects to a weapon...
		return super.onItemRightClick(worldIn, playerIn, handIn);
	}
	
	public static WeaponOilEffect getOilFromStack(ItemStack stack)
	{
		ResourceLocation oil = new ResourceLocation(stack.getOrCreateTag().getString(NBT_OIL));
		if(weaponOilRegistry == null)
			weaponOilRegistry = GameRegistry.findRegistry(WeaponOilEffect.class);
		if(weaponOilRegistry != null && weaponOilRegistry.containsKey(oil))
			return weaponOilRegistry.getValue(oil);
		return null;
	}
	
	public static void setOilInStack(ItemStack stack, WeaponOilEffect oil)
	{
		stack.getOrCreateTag().putString(NBT_OIL, oil.getRegistryName().toString());
	}
}*/

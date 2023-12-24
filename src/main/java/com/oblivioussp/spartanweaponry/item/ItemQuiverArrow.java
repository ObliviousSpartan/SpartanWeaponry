package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.client.gui.GuiHandler;
import com.oblivioussp.spartanweaponry.client.model.ModelArrowQuiverHeavy;
import com.oblivioussp.spartanweaponry.client.model.ModelArrowQuiverLight;
import com.oblivioussp.spartanweaponry.client.model.ModelArrowQuiverMedium;
import com.oblivioussp.spartanweaponry.client.model.ModelQuiverBase;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemArrow;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemQuiverArrow extends ItemQuiverBase 
{
	public static final ResourceLocation TextureQuiverLight = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_arrow_light.png");
	public static final ResourceLocation TextureQuiverMedium = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_arrow_medium.png");
	public static final ResourceLocation TextureQuiverHeavy = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_arrow_heavy.png");

	public ItemQuiverArrow(String unlocName, int inventorySize) 
	{
		super(unlocName, inventorySize);
		this.guiIdQuiver = GuiHandler.GUI_ID_QUIVER_ARROW;
	}

	/**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	//tooltip.add(TextFormatting.RED + StringHelper.translateString("wip", "dev"));
    	//tooltip.add("");

    	
		tooltip.add(StringHelper.translateString("quiver_arrow.desc", "tooltip", ModSpartanWeaponry.ID));
    	
    	super.addInformation(stack, worldIn, tooltip, flagIn);
    }

	@Override
	public boolean isAmmoValid(ItemStack ammo, ItemStack quiver) 
	{
		return ammo.getItem() instanceof ItemArrow;
	}

	@Override
	public ModelQuiverBase initModel() 
	{
		if(arrowSlots == 6)
			return new ModelArrowQuiverMedium();
		else if(arrowSlots == 9)
			return new ModelArrowQuiverHeavy();
		return new ModelArrowQuiverLight();
	}

	@Override
	public ResourceLocation getTexture() 
	{
		if(arrowSlots == 6)
			return TextureQuiverMedium;
		else if(arrowSlots == 9)
			return TextureQuiverHeavy;
		return TextureQuiverLight;
	}
}

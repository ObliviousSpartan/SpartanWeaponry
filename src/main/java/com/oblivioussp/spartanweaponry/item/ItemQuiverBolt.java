package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.client.gui.GuiHandler;
import com.oblivioussp.spartanweaponry.client.model.ModelBoltQuiverHeavy;
import com.oblivioussp.spartanweaponry.client.model.ModelBoltQuiverLight;
import com.oblivioussp.spartanweaponry.client.model.ModelBoltQuiverMedium;
import com.oblivioussp.spartanweaponry.client.model.ModelQuiverBase;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemQuiverBolt extends ItemQuiverBase 
{
	public static final ResourceLocation TextureBoltQuiverLight = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_light.png");
	public static final ResourceLocation TextureBoltQuiverMedium = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_medium.png");
	public static final ResourceLocation TextureBoltQuiverHeavy = new ResourceLocation(ModSpartanWeaponry.ID, "textures/model/quiver_bolt_heavy.png");
	
	public ItemQuiverBolt(String unlocName, int inventorySize) 
	{
		super(unlocName, inventorySize);
		this.guiIdQuiver = GuiHandler.GUI_ID_QUIVER_BOLT;
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

		tooltip.add(StringHelper.translateString("quiver_bolt.desc", "tooltip", ModSpartanWeaponry.ID));
    	
    	super.addInformation(stack, worldIn, tooltip, flagIn);
    }

	@Override
	public boolean isAmmoValid(ItemStack ammo, ItemStack quiver)
	{
		return ammo.getItem() instanceof ItemBolt;
	}

	@Override
	public ModelQuiverBase initModel() 
	{
		if(arrowSlots == 6)
			return new ModelBoltQuiverMedium();
		else if(arrowSlots == 9)
			return new ModelBoltQuiverHeavy();
		return new ModelBoltQuiverLight();
	}

	@Override
	public ResourceLocation getTexture() 
	{
		if(arrowSlots == 6)
			return TextureBoltQuiverMedium;
		else if(arrowSlots == 9)
			return TextureBoltQuiverHeavy;
		return TextureBoltQuiverLight;
	}
}

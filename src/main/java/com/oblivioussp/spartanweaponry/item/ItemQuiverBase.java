package com.oblivioussp.spartanweaponry.item;

import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.capability.CapabilityProviderQuiver;
import com.oblivioussp.spartanweaponry.client.model.ModelQuiverBase;
import com.oblivioussp.spartanweaponry.util.StringHelper;

import baubles.api.BaubleType;
import baubles.api.IBauble;
import baubles.api.render.IRenderBauble;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.AbstractClientPlayer;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.Render;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants.NBT;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;

@Optional.Interface(iface="baubles.api.IBauble", modid=ModSpartanWeaponry.ModID_Baubles)
@Optional.Interface(iface="baubles.api.render.IRenderBauble", modid=ModSpartanWeaponry.ModID_Baubles)
public abstract class ItemQuiverBase extends ItemSW implements IBauble, IRenderBauble
{
	public enum SlotType
	{
		UNDEFINED,
		MAIN_HAND,
		OFF_HAND,
		HOTBAR,
		BAUBLE
	}
	
	public static final String NBT_SIZE = "size";
	public static final String NBT_CURRENT_AMMO = "currentAmmo";
	public static final String NBT_TOTAL_AMMO = "totalAmmo";
	public static final String NBT_AMMO_COLLECT = "ammoCollect";
	// NBT Tags for moving and moving back offhand items when the quiver places ammo
	public static final String NBT_OFFHAND_MOVED = "offhandMoved";
	public static final String NBT_ITEM_ID = "id";
	public static final String NBT_ITEM_SLOT = "slot";
	public static final String NBT_CLIENT_INVENTORY = "ClientInventory";
	
	@SideOnly(Side.CLIENT)
	protected ModelQuiverBase model;
	
	protected int guiIdQuiver = -1;
	protected int arrowSlots = 4;
	
	public ItemQuiverBase(String unlocName, int inventorySize) 
	{
		super(unlocName);
		this.setMaxStackSize(1);
		arrowSlots = inventorySize;
		
		this.addPropertyOverride(new ResourceLocation("arrow"), new IItemPropertyGetter()
		{
			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) 
			{
				return getArrowCount(stack);
			}
		});
	}
	
	protected int getArrowCount(ItemStack stack)
	{
		int arrows = 0;
		NBTTagList list;
		//NBTTagCompound invTag;
		
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		
		//arrows = stack.getTagCompound().getInteger(NBT_SIZE);
		
		//invTag = stack.getTagCompound().getCompoundTag(ItemStorageStackHandler.NBT_INVENTORY);
		list = stack.getTagCompound().getTagList(NBT_CLIENT_INVENTORY, NBT.TAG_COMPOUND);
		
		for(int i = 0; i < list.tagCount(); i++)
		{
			ItemStack item = new ItemStack(list.getCompoundTagAt(i));
			if(!item.isEmpty())
				arrows++;
		}
		
		/*IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		if(handler != null)
		{
			for(int i = 0; i < handler.getSlots(); i++)
			{
				if(!CompatHelper.isStackEmpty(handler.getStackInSlot(i)))
					arrows++;
			}
		}*/
		
		// Have 6 separate states for the Heavy Arrow Quiver, instead of 4
		if(arrowSlots == 9)
		{
			if(arrows > 5)
				arrows = 5;
		}
		else if(arrows > 3)
			arrows = 3;
		
		return arrows;
	}
	
	public int getInventorySlots()
	{
		return arrowSlots;
	}
	
	@Nonnull
	@Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable NBTTagCompound nbt)
    {
        return new CapabilityProviderQuiver(stack, getInventorySlots());
    }
	
	/**
     * Called when the equipped item is right clicked.
     */
	@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
    {
		ItemStack heldItem = playerIn.getHeldItem(handIn);
		
		if(!worldIn.isRemote)
		{
			if(!playerIn.isSneaking() && this.guiIdQuiver != -1)
			{
				/*if(playerIn instanceof EntityPlayerMP)
				{
					((EntityPlayerMP)playerIn).connection.sendPacket(new SPacketHeldItemChange(playerIn.inventory.currentItem));
				}*/

				if(!heldItem.hasTagCompound())
					heldItem.setTagCompound(new NBTTagCompound());
				
				IItemHandler handler = heldItem.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
				if(!(handler instanceof ItemStackHandler))
					return new ActionResult<ItemStack>(EnumActionResult.FAIL, heldItem);
				
				SlotType slotType = handIn == EnumHand.MAIN_HAND ? SlotType.MAIN_HAND : SlotType.OFF_HAND;
				playerIn.openGui(ModSpartanWeaponry.instance, this.guiIdQuiver, worldIn, slotType.ordinal(), -1, 0);
		        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, heldItem);
			}
			else if(playerIn.isSneaking())
			{
				if(!heldItem.hasTagCompound())
					heldItem.setTagCompound(new NBTTagCompound());
				
				boolean ammoCollect = !heldItem.getTagCompound().getBoolean(NBT_AMMO_COLLECT);
				heldItem.getTagCompound().setBoolean(NBT_AMMO_COLLECT, ammoCollect);
				
				String collectStatus = ammoCollect ? "enabled" : "disabled";
				TextFormatting collectColour = ammoCollect ? TextFormatting.GREEN : TextFormatting.RED;
				String message = StringHelper.translateFormattedString("ammoCollectToggle", "message", ModSpartanWeaponry.ID, collectColour + StringHelper.translateString(collectStatus, "tooltip"));
				playerIn.sendStatusMessage(new TextComponentString(message), true);
				return new ActionResult<ItemStack>(EnumActionResult.FAIL, heldItem);
			}
		}
        return new ActionResult<ItemStack>(EnumActionResult.PASS, heldItem);
    }
	
	@Override
	public boolean getShareTag()
	{
		return true;
	}
	
	@Override
	public NBTTagCompound getNBTShareTag(ItemStack stack)
    {
		IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		NBTBase capTag = CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.getStorage().writeNBT(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, handler, null);
		NBTTagCompound tag = super.getNBTShareTag(stack);
		if(tag == null)
			tag = new NBTTagCompound();
		
		if(capTag != null)
			tag.setTag(NBT_CLIENT_INVENTORY, capTag);
		return tag;
    }

	/*@Override
	public void readNBTShareTag(ItemStack stack, NBTTagCompound nbt) 
	{
		IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		NBTTagList tag = nbt.getTagList(ItemStackStorageHandler.NBT_CLIENT_INVENTORY, Constants.NBT.TAG_COMPOUND);
		if(tag != null)
			CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.readNBT(handler, null, tag);
	}*/

	/**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
    {
    	addQuiverInventoryTooltip(stack, tooltip);
    }
    
    @SideOnly(Side.CLIENT)
    protected void addQuiverInventoryTooltip(ItemStack stack, List<String> tooltip)
    {
		if(!stack.hasTagCompound())
			stack.setTagCompound(new NBTTagCompound());
		
		int itemCount = 0;
		NBTTagList list;
		//NBTTagCompound invTag;
		
		tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("quiver_capacity", "tooltip", ModSpartanWeaponry.ID, TextFormatting.GRAY +  "" + arrowSlots));
		
		// Passive
		boolean ammoCollect = stack.getTagCompound().getBoolean(NBT_AMMO_COLLECT);
		String collectStatus = ammoCollect ? "enabled" : "disabled";
		TextFormatting statusColour = ammoCollect ? TextFormatting.GREEN : TextFormatting.RED;
		tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateFormattedString("quiver_collect_status", "tooltip", ModSpartanWeaponry.ID, statusColour + StringHelper.translateString(collectStatus, "tooltip")));
		
		tooltip.add(TextFormatting.DARK_AQUA + StringHelper.translateString("quiver_contains", "tooltip"));
		
		//invTag = stack.getTagCompound().getCompoundTag(ItemStorageStackHandler.NBT_INVENTORY);
		list = stack.getTagCompound().getTagList(NBT_CLIENT_INVENTORY, NBT.TAG_COMPOUND);
		
		//IItemHandler handler = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null);
		
		//if(handler != null)
		//{
			//for(int i = 0; i < handler.getSlots(); i++)
			for(int i = 0; i < list.tagCount(); i++)
			{
				//NBTTagCompound tag = list.getCompoundTagAt(i);
				//ItemStack tagStack = null;
				//if(tag != null)
				//	tagStack = new ItemStack(tag);
				//if(tagStack != null)
				//ItemStack slotStack = handler.getStackInSlot(i);
				ItemStack slotStack = new ItemStack(list.getCompoundTagAt(i));
				if(!slotStack.isEmpty())
				{
					if(itemCount < 2 || GuiScreen.isShiftKeyDown())
						tooltip.add("  " + StringHelper.translateFormattedString("quiver_ammo", "tooltip", ModSpartanWeaponry.ID, slotStack.getCount(), TextFormatting.AQUA + "" + slotStack.getDisplayName()));
					else if(itemCount == 2)
						tooltip.add(TextFormatting.DARK_GRAY + StringHelper.translateFormattedString("quiver_show_contents", "tooltip", ModSpartanWeaponry.ID, TextFormatting.AQUA + "SHIFT" + TextFormatting.DARK_GRAY));
					
					itemCount++;
				}
			}
		//}
		if(itemCount == 0)
			tooltip.add("  " + StringHelper.translateString("quiver_none", "tooltip"));
    }

	@Override
	public BaubleType getBaubleType(ItemStack itemstack) 
	{
		return BaubleType.BODY;
	}

	@Override
	public boolean willAutoSync(ItemStack itemstack, EntityLivingBase player)
	{
		return true;
	}
	
	@Override
	public void onPlayerBaubleRender(ItemStack stack, EntityPlayer player, RenderType type, float partialTicks) 
	{
		if(type == RenderType.BODY)
		{
			int arrowsToRender = getArrowCount(stack);
			
			if(model == null)
				model = initModel();
				
			model.setArrowsToRender(arrowsToRender);
			
			//float trZ = player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).isEmpty() ? -0.05f: 0.0f; 
			if(Loader.isModLoaded("mobends"))
			{
				Render<AbstractClientPlayer> render = Minecraft.getMinecraft().getRenderManager().<AbstractClientPlayer>getEntityRenderObject(player);
				if(render instanceof RenderPlayer)
				{
					RenderPlayer renderPlayer = (RenderPlayer)render;
					renderPlayer.getMainModel().bipedBody.postRender(0.0625f);
				}
				
				if(player.isSneaking())
					GlStateManager.translate(0.0f, 0.49f, -0.0875f);
				else
					GlStateManager.translate(0.0f, 0.3f, 0.0f);
			}
			else
			{
				Helper.rotateIfSneaking(player);
				GlStateManager.translate(0.0f, 0.3f, 0.0f);
			}
			//Helper.translateToChest();
			
			Minecraft.getMinecraft().getTextureManager().bindTexture(getTexture());
			model.render(0.0625f);
			
			// Attempt to render an arrow if I can...
			/*Tessellator tess = Tessellator.getInstance();
			BufferBuilder vertexBuffer = tess.getBuffer();

	        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
			GlStateManager.pushMatrix();
			model.quiver.postRender(0.0625f);
			
			GlStateManager.glNormal3f(0.05625F, 0.0F, 0.0F);
			vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
			vertexBuffer.pos(-6.0D, -2.0D, -2.0D).tex(0.0D, (17 / 32)).endVertex();
			vertexBuffer.pos(-6.0D, -2.0D, 2.0D).tex((6 / 32), (17 / 32)).endVertex();
			vertexBuffer.pos(-6.0D, 2.0D, 2.0D).tex((6 / 32), (20 / 32)).endVertex();
			vertexBuffer.pos(-6.0D, 2.0D, -2.0D).tex(0.0D, (20 / 32)).endVertex();
			tess.draw();
			GlStateManager.popMatrix();*/
		}
	}
	
	public int getGuiId()
	{
		return guiIdQuiver;
	}
	
	public abstract ModelQuiverBase initModel();
	public abstract ResourceLocation getTexture();

	public abstract boolean isAmmoValid(ItemStack ammo, ItemStack quiver);
}

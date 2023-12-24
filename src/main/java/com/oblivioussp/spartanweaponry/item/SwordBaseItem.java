
package com.oblivioussp.spartanweaponry.item;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.IReloadable;
import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.ReloadableHandler;
import com.oblivioussp.spartanweaponry.api.WeaponMaterial;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.trait.VersatileWeaponTrait;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;
import com.oblivioussp.spartanweaponry.client.ClientHelper;
import com.oblivioussp.spartanweaponry.util.ClientConfig;
import com.oblivioussp.spartanweaponry.util.WeaponArchetype;

import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.tags.ITagManager;

public class SwordBaseItem extends SwordItem implements IWeaponTraitContainer<SwordBaseItem>, IReloadable
{
	protected final Set<ToolAction> DEFAULT_SWORD_NO_SWEEP_ACTIONS = ImmutableSet.of(ToolActions.SWORD_DIG);
	
	protected float attackDamage = 1.0f;
	protected double attackSpeed = 0.0D;
	protected WeaponMaterial material;
	protected String customDisplayName = null;
	
	protected boolean doCraftCheck = true;
	protected boolean canBeCrafted = true;
	
	protected Multimap<Attribute, AttributeModifier> modifiers;
	protected final WeaponArchetype archetype;
	
	/**
	 * A list of *ALL* Weapon Traits, including material bonus traits. Refreshed when the world is loaded (after tags are updated)
	 */
	protected List<WeaponTrait> traits;
	
	public SwordBaseItem(Item.Properties prop, WeaponMaterial materialIn, WeaponArchetype archetypeIn, float weaponBaseDamage, float weaponDamageMultiplier, double weaponSpeed) 
	{
		super(materialIn, 3, -2.4f, prop.durability(materialIn.getUses()));
		material = materialIn;
		archetype = archetypeIn;
		setAttackDamageAndSpeed(weaponBaseDamage, weaponDamageMultiplier, weaponSpeed);
		
		ReloadableHandler.addToReloadList(this);
		
		if(FMLEnvironment.dist.isClient())
			ClientHelper.registerMeleeWeaponPropertyOverrides(this);
	}
	
	public SwordBaseItem(Item.Properties prop, WeaponMaterial materialIn, WeaponArchetype archetypeIn, float weaponBaseDamage, float weaponDamageMultiplier, double weaponSpeed, String customDisplayNameIn)
	{
		this(prop, materialIn, archetypeIn, weaponBaseDamage, weaponDamageMultiplier, weaponSpeed);
		if(materialIn.useCustomDisplayName())
			customDisplayName = customDisplayNameIn;
	}
	
	@Override
	public void reload() 
	{
		ImmutableList.Builder<WeaponTrait> builder = ImmutableList.builder();
		
		builder.addAll(archetype.getTraits());
		builder.addAll(material.getBonusTraits());
		traits = builder.build();
		
		// Initialize the weapon's attribute modifier map
		ImmutableMultimap.Builder<Attribute, AttributeModifier> mapBuilder = ImmutableMultimap.builder();
		mapBuilder.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(BASE_ATTACK_DAMAGE_UUID, "Weapon modifier", getDirectAttackDamage(), AttributeModifier.Operation.ADDITION));
		mapBuilder.put(Attributes.ATTACK_SPEED, new AttributeModifier(BASE_ATTACK_SPEED_UUID, "Weapon modifier", attackSpeed - 4.0D, AttributeModifier.Operation.ADDITION));
		
		// Add melee attributes from Weapon Traits
		if(traits != null)
			traits.forEach((trait) ->
				trait.getMeleeCallback().ifPresent((callback) -> callback.onModifyAttributesMelee(mapBuilder)));
		
		modifiers = mapBuilder.build();
	}
	
/*	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) 
	{
//		return super.initCapabilities(stack, nbt);
		SwordBaseItem item = this;
		return new ICapabilityProvider()
			{
				@Override
				public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) 
				{
					return ModCapabilities.WEAPON_TRAITS.orEmpty(cap, LazyOptional.of(() -> item));
				}
			};
	}*/
	
	@Override
	public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot equipmentSlot, ItemStack stack)
    {
		return modifiers != null && equipmentSlot == EquipmentSlot.MAINHAND ? modifiers : super.getAttributeModifiers(equipmentSlot, stack);
    }
	
	/**
     * Called each tick as long the item is on a player inventory. Uses by maps to check if is on a player hand and
     * update it's contents.
     */
	@Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected)
    {
		// Check for two-handed traits, and other such effects
		if(entity instanceof LivingEntity)
		{
			LivingEntity living = (LivingEntity)entity;
			
			if(traits != null)
			{
				traits.forEach((trait) ->
					trait.getMeleeCallback().ifPresent((callback) -> callback.onItemUpdate(material, stack, level, living, itemSlot, isSelected)));
			}
		}
    }

	/**
     * Returns the amount of damage this item will deal. One heart of damage is equal to 2 damage points.
     */
	@Override
    public float getDamage()
    {
        return material.getAttackDamageBonus();
    }
	
	@Override
	public int getMaxDamage(ItemStack stack) 
	{
		return material.getUses();
	}
	
	@Override
	public float getDestroySpeed(ItemStack stack, BlockState state)
    {
		for(WeaponTrait trait : getAllWeaponTraitsWithType(WeaponTraits.TYPE_VERSATILE))
		{
			VersatileWeaponTrait versatileTrait = (VersatileWeaponTrait)trait;
			if(state.is(versatileTrait.getEffectiveBlocks()))
				return material.getSpeed();
		}
		if(archetype.isBladed() && state.is(Blocks.COBWEB))
			return 15.0f;
        return super.getDestroySpeed(stack, state);
    }
	
	@Override
	public boolean canDisableShield(ItemStack stack, ItemStack shield, LivingEntity entity, LivingEntity attacker)
	{
		return hasWeaponTrait(WeaponTraits.SHIELD_BREACH.get());
	}
	
	@Override
	public Component getName(ItemStack stack) 
	{
		if(customDisplayName == null)
			return super.getName(stack);
		return new TranslatableComponent(customDisplayName, material.translateName());
	}
	
	@Override
	public void appendHoverText(ItemStack stack, Level levelIn, List<Component> tooltip, TooltipFlag flagIn) 
	{
		boolean isShiftPressed = Screen.hasShiftDown();

    	if(doCraftCheck && levelIn != null)
    	{
    		if(!ClientConfig.INSTANCE.forceDisableUncraftableTooltips.get() && material.getModId() == ModSpartanWeaponry.ID)
    		{
    			ITagManager<Item> tagManager = ForgeRegistries.ITEMS.tags();
        		if(!tagManager.isKnownTagName(material.getRepairTag()) || tagManager.getTag(material.getRepairTag()).isEmpty())
	    			canBeCrafted = false;
    		}
	    	doCraftCheck = false;
    	}

    	if(!canBeCrafted)
    		tooltip.add(new TranslatableComponent(String.format("tooltip.%s.uncraftable_missing_material", ModSpartanWeaponry.ID), material.getRepairTagName()).withStyle(ChatFormatting.RED));
		
    	archetype.addTagErrorTooltip(stack, tooltip);
    	material.addTagErrorTooltip(stack, tooltip);
    	
		if(traits != null && !traits.isEmpty())
		{
			if(isShiftPressed)
				tooltip.add(new TranslatableComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".showing_details").withStyle(ChatFormatting.DARK_GRAY)).withStyle(ChatFormatting.GOLD));
			else
				tooltip.add(new TranslatableComponent(String.format("tooltip.%s.traits", ModSpartanWeaponry.ID), new TranslatableComponent("tooltip." + ModSpartanWeaponry.ID + ".show_details", ChatFormatting.AQUA.toString() + "SHIFT").withStyle(ChatFormatting.DARK_GRAY)).withStyle(ChatFormatting.GOLD));

			archetype.addTraitsToTooltip(stack, tooltip, isShiftPressed);
//			tooltip.add(new TextComponent(""));
		}
		tooltip.add(new TextComponent(""));
    	if(material.hasAnyBonusTraits())
    	{
    		tooltip.add(new TranslatableComponent(String.format("tooltip.%s.trait.material_bonus", ModSpartanWeaponry.ID)).withStyle(ChatFormatting.AQUA));
    		material.addTraitsToTooltip(stack, tooltip, isShiftPressed);
    	}
		
		super.appendHoverText(stack, levelIn, tooltip, flagIn);
	}
	
	public float getDirectAttackDamage()
	{
		return attackDamage;
	}
	
	@Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker)
    {
		traits.forEach((trait) -> 
			trait.getMeleeCallback().ifPresent((callback) -> callback.onHitEntity(material, stack, target, attacker, null)));
		
        return super.hurtEnemy(stack, target, attacker);
    }
	
	@Override
	public InteractionResult useOn(UseOnContext contextIn) 
	{
		Optional<WeaponTrait> actionTrait = archetype.getActionTrait();
		if(actionTrait.isPresent())
		{
			WeaponTrait trait = actionTrait.get();
			if(trait.getActionCallback().isPresent())
				return trait.getActionCallback().get().useOn(contextIn);
		}
		return super.useOn(contextIn);
	}
	
	@Override
	public InteractionResultHolder<ItemStack> use(Level levelIn, Player player, InteractionHand hand)
	{
		ItemStack stack = player.getItemInHand(hand);
		Optional<WeaponTrait> actionTrait = archetype.getActionTrait();
		if(actionTrait.isPresent())
		{
			WeaponTrait trait = actionTrait.get();
			if(trait.getActionCallback().isPresent())
				return trait.getActionCallback().get().use(stack, levelIn, player, hand);
		}
		return super.use(levelIn, player, hand);
	}
	
	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity entityLiving, int timeLeft) 
	{
		Optional<WeaponTrait> actionTrait = archetype.getActionTrait();
		actionTrait.ifPresent((trait) -> 
			trait.getActionCallback().ifPresent((callback) -> 
				callback.releaseUsing(stack, level, entityLiving, timeLeft, getDirectAttackDamage())));
		super.releaseUsing(stack, level, entityLiving, timeLeft);
	}
	
	@Override
	public void onUsingTick(ItemStack stack, LivingEntity player, int count) 
	{
		Optional<WeaponTrait> actionTrait = archetype.getActionTrait();
		actionTrait.ifPresent((trait) -> 
			trait.getActionCallback().ifPresent((callback) -> 
				callback.onUsingTick(stack, player, count, getDirectAttackDamage())));
		super.onUsingTick(stack, player, count);
	}
	
	@Override
	public int getUseDuration(ItemStack stack) 
	{
		Optional<WeaponTrait> actionTrait = archetype.getActionTrait();
		if(actionTrait.isPresent())
		{
			WeaponTrait trait = actionTrait.get();
			if(trait.getActionCallback().isPresent())
				return trait.getActionCallback().get().getUseDuration(stack);
		}
		
		return super.getUseDuration(stack);
	}
	
	@Override
	public UseAnim getUseAnimation(ItemStack stack) 
	{
		Optional<WeaponTrait> actionTrait = archetype.getActionTrait();
		if(actionTrait.isPresent())
		{
			WeaponTrait trait = actionTrait.get();
			if(trait.getActionCallback().isPresent())
				return trait.getActionCallback().get().getUseAnimation(stack);
		}
		return super.getUseAnimation(stack);
	}
	
	@Override
	public boolean doesSneakBypassUse(ItemStack stack, LevelReader level, BlockPos pos, Player player) 
	{
		Optional<WeaponTrait> actionTrait = archetype.getActionTrait();
		if(actionTrait.isPresent())
		{
			WeaponTrait trait = actionTrait.get();
			if(trait.getActionCallback().isPresent())
				return trait.getActionCallback().get().doesSneakBypassUse(stack, level, pos, player);
		}
		return super.doesSneakBypassUse(stack, level, pos, player);
	}
	
	@Override
	public void onCraftedBy(ItemStack stack, Level levelIn, Player playerIn) 
	{
		traits.forEach((trait) -> 
			trait.getMeleeCallback().ifPresent((callback) -> callback.onCreateItem(material, stack)));
		super.onCraftedBy(stack, levelIn, playerIn);
	}
	
	@Override
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> items) 
	{
		if(allowdedIn(group))
		{
			ItemStack stack = new ItemStack(this);
			if(traits != null)
				traits.forEach((trait) ->
					trait.getMeleeCallback().ifPresent((callback) -> callback.onCreateItem(material, stack)));
			items.add(stack);
		}
	}
	
	@Override
	public boolean canPerformAction(ItemStack stack, ToolAction toolAction) 
	{
		return hasWeaponTraitWithType(WeaponTraits.TYPE_SWEEP_DAMAGE) ? super.canPerformAction(stack, toolAction) : DEFAULT_SWORD_NO_SWEEP_ACTIONS.contains(toolAction);
	}
    
    @Override
    public boolean canApplyAtEnchantingTable(ItemStack stack, Enchantment enchantment)
    {
    	for(WeaponTrait trait : traits)
    	{
    		if(trait.isEnchantmentCompatible(enchantment))
    			return true;
    		else if(trait.isEnchantmentIncompatible(enchantment))
    			return false;
    	}
    	// Sweeping Edge is incompatible with most weapons unless they have the Sweep I trait
        return enchantment != Enchantments.SWEEPING_EDGE && super.canApplyAtEnchantingTable(stack, enchantment);
    }
    
    // IWeaponTraitContainer
    
    @Override
    public SwordBaseItem getAsItem() 
    {
    	return this;
    }

	@Override
	public boolean hasWeaponTrait(WeaponTrait prop) 
	{
		return traits.contains(prop);
	}

	@Override
	public boolean hasWeaponTraitWithType(String type)
	{
		return traits.stream().anyMatch((trait) -> trait.getType() == type);
	}

	@Override
	public WeaponTrait getFirstWeaponTraitWithType(String type) 
	{
		for(WeaponTrait trait : traits)
		{
			if(trait.getType() == type)
				return trait;
		}
		return null;
	}

	@Override
	public List<WeaponTrait> getAllWeaponTraitsWithType(String type)
	{
		if(traits.isEmpty())
			return ImmutableList.of();
		
		return traits.stream().filter((trait) -> trait.getType() == type).collect(Collectors.toUnmodifiableList());
	}

	@Override
	public Collection<WeaponTrait> getAllWeaponTraits() 
	{
		// Traits are immutable anyway so it should be safe to reference them directly
		return traits;
	}

	@Override
	public WeaponMaterial getMaterial()
	{
		return material;
	}
	
	public void setAttackDamageAndSpeed(float baseDamage, float damageMultiplier, double speed)
	{
		attackDamage = (material.getAttackDamageBonus() * damageMultiplier) + baseDamage - 1.0f;
		attackSpeed = speed;
	}
}

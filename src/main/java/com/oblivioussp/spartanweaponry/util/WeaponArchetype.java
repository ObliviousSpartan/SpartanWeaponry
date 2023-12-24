package com.oblivioussp.spartanweaponry.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.oblivioussp.spartanweaponry.api.IReloadable;
import com.oblivioussp.spartanweaponry.api.ReloadableHandler;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.tags.ModWeaponTraitTags;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.tags.ITag;
import net.minecraftforge.registries.tags.ITagManager;

/**
 * This class contains all the data that are constant for every weapon of a certain type (e.g. Dagger, Longsword, etc.)
 * This should reduce redundant values on each weapon item.
 * Currently only filters and pre-caches traits to be used for Weapon items to improve performance
 * @author ObliviousSpartan
 */
public class WeaponArchetype implements IReloadable
{
	public static final Predicate<WeaponTrait> IS_MELEE = (trait) -> trait.isMeleeTrait();
	public static final Predicate<WeaponTrait> IS_RANGED = (trait) -> trait.isRangedTrait();
	public static final Predicate<WeaponTrait> IS_THROWING = (trait) -> trait.isThrowingTrait();
	
	public static final WeaponArchetype DAGGER = new WeaponArchetype("Dagger", true, ModWeaponTraitTags.DAGGER, IS_MELEE);
	public static final WeaponArchetype PARRYING_DAGGER = new WeaponArchetype("Parrying Dagger", true, ModWeaponTraitTags.PARRYING_DAGGER, IS_MELEE);
	public static final WeaponArchetype LONGSWORD = new WeaponArchetype("Longsword", true, ModWeaponTraitTags.LONGSWORD, IS_MELEE);
	public static final WeaponArchetype KATANA = new WeaponArchetype("Katana", true, ModWeaponTraitTags.KATANA, IS_MELEE);
	public static final WeaponArchetype SABER = new WeaponArchetype("Saber", true, ModWeaponTraitTags.SABER, IS_MELEE);
	public static final WeaponArchetype RAPIER = new WeaponArchetype("Rapier", true, ModWeaponTraitTags.RAPIER, IS_MELEE);
	public static final WeaponArchetype GREATSWORD = new WeaponArchetype("Greatsword", true, ModWeaponTraitTags.GREATSWORD, IS_MELEE);
	public static final WeaponArchetype CLUB = new WeaponArchetype("Club", false, ModWeaponTraitTags.CLUB, IS_MELEE);
	public static final WeaponArchetype CESTUS = new WeaponArchetype("Cestus", false, ModWeaponTraitTags.CESTUS, IS_MELEE);
	public static final WeaponArchetype BATTLE_HAMMER = new WeaponArchetype("Battle Hammer", false, ModWeaponTraitTags.BATTLE_HAMMER, IS_MELEE);
	public static final WeaponArchetype WARHAMMER = new WeaponArchetype("Warhammer", false, ModWeaponTraitTags.WARHAMMER, IS_MELEE);
	public static final WeaponArchetype SPEAR = new WeaponArchetype("Spear", false, ModWeaponTraitTags.SPEAR, IS_MELEE);
	public static final WeaponArchetype HALBERD = new WeaponArchetype("Halberd", false, ModWeaponTraitTags.HALBERD, IS_MELEE);
	public static final WeaponArchetype PIKE = new WeaponArchetype("Pike", false, ModWeaponTraitTags.PIKE, IS_MELEE);
	public static final WeaponArchetype LANCE = new WeaponArchetype("Lance", false, ModWeaponTraitTags.LANCE, IS_MELEE);
	public static final WeaponArchetype THROWING_KNIFE = new WeaponArchetype("Throwing Knife", true, ModWeaponTraitTags.THROWING_KNIFE, IS_THROWING);
	public static final WeaponArchetype TOMAHAWK = new WeaponArchetype("Tomahawk", false, ModWeaponTraitTags.TOMAHAWK, IS_THROWING);
	public static final WeaponArchetype JAVELIN = new WeaponArchetype("Javelin", false, ModWeaponTraitTags.JAVELIN, IS_THROWING);
	public static final WeaponArchetype BOOMERANG = new WeaponArchetype("Boomerang", false, ModWeaponTraitTags.BOOMERANG, IS_THROWING);
	public static final WeaponArchetype BATTLEAXE = new WeaponArchetype("Battleaxe", false, ModWeaponTraitTags.BATTLEAXE, IS_MELEE);
	public static final WeaponArchetype FLANGED_MACE = new WeaponArchetype("Flanged Mace", false, ModWeaponTraitTags.FLANGED_MACE, IS_MELEE);
	public static final WeaponArchetype GLAIVE = new WeaponArchetype("Glaive", true, ModWeaponTraitTags.GLAIVE, IS_MELEE);
	public static final WeaponArchetype QUARTERSTAFF = new WeaponArchetype("Quarterstaff", false, ModWeaponTraitTags.QUARTERSTAFF, IS_MELEE);
	public static final WeaponArchetype SCYTHE = new WeaponArchetype("Scythe", false, ModWeaponTraitTags.SCYTHE, IS_MELEE);
	
	// TODO: Perhaps make a Supertype value to define if it is a melee, ranged or throwing weapon
	protected final String name;
	protected final TagKey<WeaponTrait> traitsTag;
	protected boolean isValidTag = true;
	protected List<WeaponTrait> traits;
	protected Optional<WeaponTrait> actionTrait = Optional.empty();
	protected Optional<List<Pair<WeaponTrait, WeaponTrait.InvalidReason>>> invalidTraits = Optional.empty();
	protected final Predicate<WeaponTrait> traitFilter;
	protected final boolean isBladed;						// Used to determine if the weapon has a blade can cut through things such as Cobwebs
	
	public WeaponArchetype(String nameIn, boolean isBladedIn, TagKey<WeaponTrait> traitsTagIn, Predicate<WeaponTrait> traitFilterIn)
	{
		name = nameIn;
		traitsTag = traitsTagIn;
		traitFilter = traitFilterIn;
		isBladed = isBladedIn;
		
		ReloadableHandler.addToReloadList(this);
	}

	@Override
	public void reload() 
	{
		IForgeRegistry<WeaponTrait> registry = RegistryManager.ACTIVE.getRegistry(WeaponTraits.REGISTRY_KEY);
		ITagManager<WeaponTrait> tagManager = registry.tags();

		if(!(isValidTag = tagManager.isKnownTagName(traitsTag)))
		{
			Log.error("Weapon Trait tag \"" + traitsTag.location() +  "\" couldn't be found for weapon archetype \"" + name + "\"!");
		}
		
		ITag<WeaponTrait> tag = tagManager.getTag(traitsTag);

		invalidTraits = Optional.empty();
		List<Pair<WeaponTrait, WeaponTrait.InvalidReason>> invalidTraitList = new ArrayList<>();
		List<String> invalidTraitValues = new ArrayList<>();
		AtomicReference<WeaponTrait> actionTraitRef = new AtomicReference<WeaponTrait>(null);
		
		traits = tag.stream().filter((trait) ->
		{
			boolean isValid = traitFilter.test(trait);
			if(isValid && trait.isActionTrait())
			{
				if(actionTraitRef.get() == null)
					actionTraitRef.set(trait);
				else
				{
					invalidTraitList.add(Pair.of(trait, WeaponTrait.InvalidReason.MULTIPLE_ACTION_TRAITS));
					invalidTraitValues.add(registry.getKey(trait).toString());
					return false;
				}
			}
			else if(!isValid)
			{
				WeaponTrait.InvalidReason reason = trait.isMeleeTrait() ? WeaponTrait.InvalidReason.WEAPON_NOT_MELEE :
												trait.isRangedTrait() ? WeaponTrait.InvalidReason.WEAPON_NOT_RANGED :
												trait.isThrowingTrait() ? WeaponTrait.InvalidReason.WEAPON_NOT_THROWING :
													WeaponTrait.InvalidReason.WEAPON_NOT_SUPPORTED;
				
				invalidTraitList.add(Pair.of(trait, reason));
				invalidTraitValues.add(trait.getRegistryName().toString());
			}
			return isValid;
		}).collect(Collectors.toUnmodifiableList());
		
		WeaponTrait trait = actionTraitRef.get();
			actionTrait = trait != null ? Optional.of(actionTraitRef.get()) : Optional.empty();
		
		if(!invalidTraitList.isEmpty())
		{
			Log.warn("Found invalid Weapon Traits for weapon archetype \"" + name + "\" which have not been added: " + String.join(", ", invalidTraitValues));
			invalidTraits = Optional.of(invalidTraitList);
		}
	}
	
	public boolean isBladed() 
	{
		return isBladed;
	}
	
	public List<WeaponTrait> getTraits() 
	{
		return traits;
	}
	
	public Optional<List<Pair<WeaponTrait, WeaponTrait.InvalidReason>>> getInvalidTraits() {
		return invalidTraits;
	}
	
	public Optional<WeaponTrait> getActionTrait()
	{
		return actionTrait;
	}
	
	public void addTagErrorTooltip(ItemStack stack, List<Component> tooltip)
	{
		if(!isValidTag)
			tooltip.add(new TranslatableComponent(String.format("tooltip.%s.trait.invalid.archetype_tag", SpartanWeaponryAPI.MOD_ID), name, traitsTag.location()).withStyle(ChatFormatting.DARK_RED));
	}
	
	public void addTraitsToTooltip(ItemStack stack, List<Component> tooltip, boolean isShiftPressed)
	{
		traits.forEach((trait) -> trait.addTooltip(stack, tooltip, isShiftPressed, WeaponTrait.InvalidReason.NONE));
		if(invalidTraits.isPresent())
			invalidTraits.get().forEach((traitPair) -> traitPair.getLeft().addTooltip(stack, tooltip, isShiftPressed, traitPair.getRight()));
	}
}

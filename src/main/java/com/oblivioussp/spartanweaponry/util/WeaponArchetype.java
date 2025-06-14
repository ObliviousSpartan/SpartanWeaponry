package com.oblivioussp.spartanweaponry.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.oblivioussp.spartanweaponry.api.IReloadable;
import com.oblivioussp.spartanweaponry.api.ReloadableHandler;
import com.oblivioussp.spartanweaponry.api.SpartanWeaponryAPI;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;
import com.oblivioussp.spartanweaponry.api.tags.ModWeaponTraitTags;
import com.oblivioussp.spartanweaponry.api.trait.WeaponTrait;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.ForgeRegistry;
import net.minecraftforge.registries.RegistryManager;
import net.minecraftforge.registries.tags.ITag;
import net.minecraftforge.registries.tags.ITagManager;

/**
 * This class contains all the data that are constant for every weapon of a certain type (e.g. Dagger, Longsword, etc.)<br>
 * This should reduce redundant values on each weapon item.<br>
 * Currently only filters and pre-caches traits to be used for Weapon items to improve performance
 * in addition to updating each weapon archetype's config values
 * @author ObliviousSpartan
 */
public class WeaponArchetype implements IReloadable
{
	public static final WeaponArchetype DAGGER = new WeaponArchetype("Dagger", true, ModWeaponTraitTags.DAGGER, WeaponType.MELEE, 
			() -> Config.INSTANCE.daggers.speed.get(), () -> Config.INSTANCE.daggers.baseDamage.get().floatValue(), () -> Config.INSTANCE.daggers.damageMultipler.get().floatValue());
	public static final WeaponArchetype PARRYING_DAGGER = new WeaponArchetype("Parrying Dagger", true, ModWeaponTraitTags.PARRYING_DAGGER, WeaponType.MELEE, 
			() -> Config.INSTANCE.parryingDaggers.speed.get(), () -> Config.INSTANCE.parryingDaggers.baseDamage.get().floatValue(), () -> Config.INSTANCE.parryingDaggers.damageMultipler.get().floatValue());
	public static final WeaponArchetype LONGSWORD = new WeaponArchetype("Longsword", true, ModWeaponTraitTags.LONGSWORD, WeaponType.MELEE, 
			() -> Config.INSTANCE.longswords.speed.get(), () -> Config.INSTANCE.longswords.baseDamage.get().floatValue(), () -> Config.INSTANCE.longswords.damageMultipler.get().floatValue(), ToolActions.SWORD_DIG);
	public static final WeaponArchetype KATANA = new WeaponArchetype("Katana", true, ModWeaponTraitTags.KATANA, WeaponType.MELEE, 
			() -> Config.INSTANCE.katanas.speed.get(), () -> Config.INSTANCE.katanas.baseDamage.get().floatValue(), () -> Config.INSTANCE.katanas.damageMultipler.get().floatValue(), ToolActions.SWORD_DIG);
	public static final WeaponArchetype SABER = new WeaponArchetype("Saber", true, ModWeaponTraitTags.SABER, WeaponType.MELEE, 
			() -> Config.INSTANCE.sabers.speed.get(), () -> Config.INSTANCE.sabers.baseDamage.get().floatValue(), () -> Config.INSTANCE.sabers.damageMultipler.get().floatValue(), ToolActions.SWORD_DIG);
	public static final WeaponArchetype RAPIER = new WeaponArchetype("Rapier", true, ModWeaponTraitTags.RAPIER, WeaponType.MELEE, 
			() -> Config.INSTANCE.rapiers.speed.get(), () -> Config.INSTANCE.rapiers.baseDamage.get().floatValue(), () -> Config.INSTANCE.rapiers.damageMultipler.get().floatValue());
	public static final WeaponArchetype GREATSWORD = new WeaponArchetype("Greatsword", true, ModWeaponTraitTags.GREATSWORD, WeaponType.MELEE, 
			() -> Config.INSTANCE.greatswords.speed.get(), () -> Config.INSTANCE.greatswords.baseDamage.get().floatValue(), () -> Config.INSTANCE.greatswords.damageMultipler.get().floatValue(), ToolActions.SWORD_DIG);
	public static final WeaponArchetype CLUB = new WeaponArchetype("Club", false, ModWeaponTraitTags.CLUB, WeaponType.MELEE, 
			() -> Config.INSTANCE.clubs.speed.get(), () -> Config.INSTANCE.clubs.baseDamage.get().floatValue(), () -> Config.INSTANCE.clubs.damageMultipler.get().floatValue());
	public static final WeaponArchetype CESTUS = new WeaponArchetype("Cestus", false, ModWeaponTraitTags.CESTUS, WeaponType.MELEE, 
			() -> Config.INSTANCE.cestus.speed.get(), () -> Config.INSTANCE.cestus.baseDamage.get().floatValue(), () -> Config.INSTANCE.cestus.damageMultipler.get().floatValue());
	public static final WeaponArchetype BATTLE_HAMMER = new WeaponArchetype("Battle Hammer", false, ModWeaponTraitTags.BATTLE_HAMMER, WeaponType.MELEE, 
			() -> Config.INSTANCE.battleHammers.speed.get(), () -> Config.INSTANCE.battleHammers.baseDamage.get().floatValue(), () -> Config.INSTANCE.battleHammers.damageMultipler.get().floatValue());
	public static final WeaponArchetype WARHAMMER = new WeaponArchetype("Warhammer", false, ModWeaponTraitTags.WARHAMMER, WeaponType.MELEE, 
			() -> Config.INSTANCE.warhammers.speed.get(), () -> Config.INSTANCE.warhammers.baseDamage.get().floatValue(), () -> Config.INSTANCE.warhammers.damageMultipler.get().floatValue());
	public static final WeaponArchetype SPEAR = new WeaponArchetype("Spear", false, ModWeaponTraitTags.SPEAR, WeaponType.MELEE, 
			() -> Config.INSTANCE.spears.speed.get(), () -> Config.INSTANCE.spears.baseDamage.get().floatValue(), () -> Config.INSTANCE.spears.damageMultipler.get().floatValue());
	public static final WeaponArchetype HALBERD = new WeaponArchetype("Halberd", false, ModWeaponTraitTags.HALBERD, WeaponType.MELEE, 
			() -> Config.INSTANCE.halberds.speed.get(), () -> Config.INSTANCE.halberds.baseDamage.get().floatValue(), () -> Config.INSTANCE.halberds.damageMultipler.get().floatValue());
	public static final WeaponArchetype PIKE = new WeaponArchetype("Pike", false, ModWeaponTraitTags.PIKE, WeaponType.MELEE, 
			() -> Config.INSTANCE.pikes.speed.get(), () -> Config.INSTANCE.pikes.baseDamage.get().floatValue(), () -> Config.INSTANCE.pikes.damageMultipler.get().floatValue());
	public static final WeaponArchetype LANCE = new WeaponArchetype("Lance", false, ModWeaponTraitTags.LANCE, WeaponType.MELEE, 
			() -> Config.INSTANCE.lances.speed.get(), () -> Config.INSTANCE.lances.baseDamage.get().floatValue(), () -> Config.INSTANCE.lances.damageMultipler.get().floatValue());
	public static final WeaponArchetype THROWING_KNIFE = new WeaponArchetype("Throwing Knife", true, ModWeaponTraitTags.THROWING_KNIFE, WeaponType.THROWING, 
			() -> Config.INSTANCE.throwingKnives.speed.get(), () -> Config.INSTANCE.throwingKnives.baseDamage.get().floatValue(), () -> Config.INSTANCE.throwingKnives.damageMultipler.get().floatValue());
	public static final WeaponArchetype TOMAHAWK = new WeaponArchetype("Tomahawk", false, ModWeaponTraitTags.TOMAHAWK, WeaponType.THROWING, 
			() -> Config.INSTANCE.tomahawks.speed.get(), () -> Config.INSTANCE.tomahawks.baseDamage.get().floatValue(), () -> Config.INSTANCE.tomahawks.damageMultipler.get().floatValue());
	public static final WeaponArchetype JAVELIN = new WeaponArchetype("Javelin", false, ModWeaponTraitTags.JAVELIN, WeaponType.THROWING, 
			() -> Config.INSTANCE.javelins.speed.get(), () -> Config.INSTANCE.javelins.baseDamage.get().floatValue(), () -> Config.INSTANCE.javelins.damageMultipler.get().floatValue());
	public static final WeaponArchetype BOOMERANG = new WeaponArchetype("Boomerang", false, ModWeaponTraitTags.BOOMERANG, WeaponType.THROWING, 
			() -> Config.INSTANCE.boomerangs.speed.get(), () -> Config.INSTANCE.boomerangs.baseDamage.get().floatValue(), () -> Config.INSTANCE.boomerangs.damageMultipler.get().floatValue());
	public static final WeaponArchetype BATTLEAXE = new WeaponArchetype("Battleaxe", false, ModWeaponTraitTags.BATTLEAXE, WeaponType.MELEE, 
			() -> Config.INSTANCE.battleaxes.speed.get(), () -> Config.INSTANCE.battleaxes.baseDamage.get().floatValue(), () -> Config.INSTANCE.battleaxes.damageMultipler.get().floatValue(), ToolActions.DEFAULT_AXE_ACTIONS);
	public static final WeaponArchetype FLANGED_MACE = new WeaponArchetype("Flanged Mace", false, ModWeaponTraitTags.FLANGED_MACE, WeaponType.MELEE, 
			() -> Config.INSTANCE.flangedMaces.speed.get(), () -> Config.INSTANCE.flangedMaces.baseDamage.get().floatValue(), () -> Config.INSTANCE.flangedMaces.damageMultipler.get().floatValue());
	public static final WeaponArchetype GLAIVE = new WeaponArchetype("Glaive", true, ModWeaponTraitTags.GLAIVE, WeaponType.MELEE, 
			() -> Config.INSTANCE.glaives.speed.get(), () -> Config.INSTANCE.glaives.baseDamage.get().floatValue(), () -> Config.INSTANCE.glaives.damageMultipler.get().floatValue());
	public static final WeaponArchetype QUARTERSTAFF = new WeaponArchetype("Quarterstaff", false, ModWeaponTraitTags.QUARTERSTAFF, WeaponType.MELEE, 
			() -> Config.INSTANCE.quarterstaves.speed.get(), () -> Config.INSTANCE.quarterstaves.baseDamage.get().floatValue(), () -> Config.INSTANCE.quarterstaves.damageMultipler.get().floatValue());
	public static final WeaponArchetype SCYTHE = new WeaponArchetype("Scythe", false, ModWeaponTraitTags.SCYTHE, WeaponType.MELEE, 
			() -> Config.INSTANCE.scythes.speed.get(), () -> Config.INSTANCE.scythes.baseDamage.get().floatValue(), () -> Config.INSTANCE.scythes.damageMultipler.get().floatValue());
	
	protected final String name;
	protected final TagKey<WeaponTrait> traitsTag;
	protected boolean isValidTag = true;
	protected List<WeaponTrait> traits = ImmutableList.of();
	protected Optional<WeaponTrait> actionTrait = Optional.empty();
	protected Optional<List<Pair<WeaponTrait, WeaponTrait.InvalidReason>>> invalidTraits = Optional.empty();
//	protected final Predicate<WeaponTrait> traitFilter;
	protected final WeaponType type;
	protected final boolean isBladed;						// Used to determine if the weapon has a blade can cut through things such as Cobwebs
	protected final Set<ToolAction> toolActions;
	protected final Supplier<Double> speedValue;
	protected final Supplier<Float> baseDamage;
	protected final Supplier<Float> damageMultiplier;

	public WeaponArchetype(String nameIn, boolean isBladedIn, TagKey<WeaponTrait> traitsTagIn, WeaponType typeIn, Supplier<Double> speedValueIn, Supplier<Float> baseDamageIn, 
			Supplier<Float> damageMultiplierIn, Set<ToolAction> toolActionsIn)
	{
		name = nameIn;
		traitsTag = traitsTagIn;
		type = typeIn;
		isBladed = isBladedIn;
		toolActions = toolActionsIn;
		
		speedValue = speedValueIn;
		baseDamage = baseDamageIn;
		damageMultiplier = damageMultiplierIn;
		ReloadableHandler.addToReloadList(this);
	}
	
	public WeaponArchetype(String nameIn, boolean isBladedIn, TagKey<WeaponTrait> traitsTagIn, WeaponType typeIn, Supplier<Double> speedValueIn, Supplier<Float> baseDamageIn, 
			Supplier<Float> damageMultiplierIn, ToolAction... toolActionsIn)
	{
		this(nameIn, isBladedIn, traitsTagIn, typeIn, speedValueIn, baseDamageIn, damageMultiplierIn, ImmutableSet.copyOf(toolActionsIn));
	}

	@Override
	public void reload() 
	{
		ForgeRegistry<WeaponTrait> registry = RegistryManager.ACTIVE.getRegistry(WeaponTraits.REGISTRY_KEY);
		ITagManager<WeaponTrait> tagManager = registry.tags();

		if(!(isValidTag = tagManager.isKnownTagName(traitsTag)))
		{
			Log.error("Weapon Trait tag \"" + traitsTag.location() +  "\" couldn't be found for weapon archetype \"" + name + "\"!");
			return;
		}
		
		ITag<WeaponTrait> tag = tagManager.getTag(traitsTag);

		invalidTraits = Optional.empty();
		List<Pair<WeaponTrait, WeaponTrait.InvalidReason>> invalidTraitList = new ArrayList<>();
		List<String> invalidTraitValues = new ArrayList<>();
		AtomicReference<WeaponTrait> actionTraitRef = new AtomicReference<WeaponTrait>(null);
		
		traits = tag.stream().filter((trait) ->
		{
			boolean isValid = type.getTraitFilter().test(trait);
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
				invalidTraitValues.add(registry.getKey(trait).toString());
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
	
	public boolean canPerformToolAction(ToolAction toolAction)
	{
		return toolActions.contains(toolAction);
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
	
	public WeaponType getType() 
	{
		return type;
	}
	
	public void addTagErrorTooltip(ItemStack stack, List<Component> tooltip)
	{
		if(!isValidTag)
			tooltip.add(Component.translatable(String.format("tooltip.%s.trait.invalid.archetype_tag", SpartanWeaponryAPI.MOD_ID), name, traitsTag.location()).withStyle(ChatFormatting.DARK_RED));
	}
	
	public void addTraitsToTooltip(ItemStack stack, List<Component> tooltip, boolean isShiftPressed)
	{
		getTraits().forEach((trait) -> trait.addTooltip(stack, tooltip, isShiftPressed, WeaponTrait.InvalidReason.NONE));
		if(invalidTraits.isPresent())
			invalidTraits.get().forEach((traitPair) -> traitPair.getLeft().addTooltip(stack, tooltip, isShiftPressed, traitPair.getRight()));
	}
	
	public double getAttackSpeed()
	{
		return speedValue.get().doubleValue();
	}
	
	public float getBaseDamage()
	{
		return baseDamage.get().floatValue();
	}
	
	public float getDamageMultiplier()
	{
		return damageMultiplier.get().floatValue();
	}
}

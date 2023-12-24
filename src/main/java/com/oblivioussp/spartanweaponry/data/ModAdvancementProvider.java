package com.oblivioussp.spartanweaponry.data;

import java.util.function.Consumer;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.advancement.criterion.BrewOilTrigger;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModAdvancementProvider extends AdvancementProvider 
{

	public ModAdvancementProvider(DataGenerator generatorIn, ExistingFileHelper fileHelperIn) 
	{
		super(generatorIn, fileHelperIn);
	}
	
	@Override
	protected void registerAdvancements(Consumer<Advancement> consumer, ExistingFileHelper fileHelper) 
	{
		Advancement root = Advancement.Builder.advancement().display(ModItems.LONGSWORDS.diamond.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".root.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".root.desc"),
				new ResourceLocation("minecraft", "textures/block/anvil.png"), FrameType.TASK, false, false, false).addCriterion("has_handle", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HANDLE.get())).addCriterion("has_pole", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.POLE.get())).requirements(RequirementsStrategy.OR).
				save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "root"), fileHelper);
		
		Advancement daggers = Advancement.Builder.advancement().parent(root).display(ModItems.DAGGERS.stone.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_dagger.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_dagger.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_dagger", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.DAGGERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "dagger"), fileHelper);
		Advancement.Builder.advancement().parent(root).display(ModItems.PARRYING_DAGGERS.gold.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_parrying_dagger.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_parrying_dagger.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_parrying_dagger", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.PARRYING_DAGGERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "parrying_dagger"), fileHelper);
		Advancement longswords = Advancement.Builder.advancement().parent(root).display(ModItems.LONGSWORDS.iron.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_longsword.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_longsword.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_longsword", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.LONGSWORDS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "longsword"), fileHelper);
		Advancement katanas = Advancement.Builder.advancement().parent(root).display(ModItems.KATANAS.stone.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_katana.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_katana.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_katana", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.KATANAS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "katana"), fileHelper);
		Advancement sabers = Advancement.Builder.advancement().parent(katanas).display(ModItems.SABERS.iron.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_saber.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_saber.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_saber", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.SABERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "saber"), fileHelper);
		Advancement.Builder.advancement().parent(sabers).display(ModItems.RAPIERS.diamond.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_rapier.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_rapier.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_rapier", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.RAPIERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "rapier"), fileHelper);
		Advancement.Builder.advancement().parent(longswords).display(ModItems.GREATSWORDS.diamond.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_greatsword.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_greatsword.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_greatsword", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.GREATSWORDS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "greatsword"), fileHelper);
		Advancement.Builder.advancement().parent(root).display(ModItems.WOODEN_CLUB.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_club.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_club.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_club", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.CLUBS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "club"), fileHelper);
		Advancement.Builder.advancement().parent(root).display(ModItems.CESTUS.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_cestus.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_cestus.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_cestus", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.CESTUSAE).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "cestus"), fileHelper);
		Advancement battleHammers = Advancement.Builder.advancement().parent(root).display(ModItems.BATTLE_HAMMERS.gold.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_battle_hammer.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_battle_hammer.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_battle_hammer", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.BATTLE_HAMMERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "battle_hammer"), fileHelper);
		Advancement.Builder.advancement().parent(battleHammers).display(ModItems.WARHAMMERS.diamond.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_warhammer.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_warhammer.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_warhammer", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.WARHAMMERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "warhammer"), fileHelper);
		Advancement spears = Advancement.Builder.advancement().parent(root).display(ModItems.SPEARS.iron.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_spear.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_spear.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_spear", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.SPEARS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "spear"), fileHelper);
		Advancement.Builder.advancement().parent(spears).display(ModItems.HALBERDS.gold.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_halberd.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_halberd.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_halberd", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.HALBERDS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "halberd"), fileHelper);
		Advancement.Builder.advancement().parent(spears).display(ModItems.PIKES.diamond.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_pike.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_pike.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_pike", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.PIKES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "pike"), fileHelper);
		Advancement.Builder.advancement().parent(spears).display(ModItems.LANCES.iron.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_lance.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_lance.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_lance", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.LANCES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "lance"), fileHelper);
		Advancement longbows = Advancement.Builder.advancement().parent(root).display(ModItems.LONGBOWS.wood.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_longbow.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_longbow.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_longbow", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.LONGBOWS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "longbow"), fileHelper);
		Advancement.Builder.advancement().parent(longbows).display(ModItems.HEAVY_CROSSBOWS.wood.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_heavy_crossbow.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_heavy_crossbow.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_heavy_crossbow", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.HEAVY_CROSSBOWS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "heavy_crossbow"), fileHelper);
		Advancement.Builder.advancement().parent(daggers).display(ModItems.THROWING_KNIVES.iron.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_throwing_knife.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_throwing_knife.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_throwing_knife", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.THROWING_KNIVES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "throwing_knife"), fileHelper);
		Advancement.Builder.advancement().parent(root).display(ModItems.TOMAHAWKS.gold.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_tomahawk.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_tomahawk.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_tomahawk", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.TOMAHAWKS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "tomahawk"), fileHelper);
		Advancement.Builder.advancement().parent(root).display(ModItems.JAVELINS.diamond.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_javelin.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_javelin.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_javelin", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.JAVELINS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "javelin"), fileHelper);
		Advancement.Builder.advancement().parent(daggers).display(ModItems.BOOMERANGS.wood.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_boomerang.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_boomerang.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_boomerang", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.BOOMERANGS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "boomerang"), fileHelper);
		Advancement.Builder.advancement().parent(root).display(ModItems.BATTLEAXES.diamond.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_battleaxe.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_battleaxe.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_battleaxe", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.BATTLEAXES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "battleaxe"), fileHelper);
		Advancement.Builder.advancement().parent(root).display(ModItems.FLANGED_MACES.iron.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_flanged_mace.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_flanged_mace.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_flanged_mace", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.FLANGED_MACES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "flanged_mace"), fileHelper);
		Advancement glaives = Advancement.Builder.advancement().parent(spears).display(ModItems.GLAIVES.iron.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_glaive.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_glaive.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_glaive", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.GLAIVES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "glaive"), fileHelper);
		Advancement.Builder.advancement().parent(root).display(ModItems.QUARTERSTAVES.gold.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_quarterstaff.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_quarterstaff.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_quarterstaff", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.QUARTERSTAVES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff"), fileHelper);
		Advancement scythes = Advancement.Builder.advancement().parent(glaives).display(ModItems.SCYTHES.diamond.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_scythe.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_scythe.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_scythe", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.SCYTHES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "scythe"), fileHelper);

		Advancement quivers = Advancement.Builder.advancement().parent(longbows).display(ModItems.SMALL_ARROW_QUIVER.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_quiver.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".craft_quiver.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_quiver", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.SMALL_QUIVERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "small_quiver"), fileHelper);
		Advancement upgradeQuiver = Advancement.Builder.advancement().parent(quivers).display(ModItems.LARGE_ARROW_QUIVER.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_quiver.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_quiver.desc"), 
				null, FrameType.TASK, true, true, false).addCriterion("has_upgraded_quiver", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.UPGRADED_QUIVERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "upgrade_quiver"), fileHelper);
		Advancement.Builder.advancement().parent(upgradeQuiver).display(ModItems.HUGE_ARROW_QUIVER.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_quiver_max.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_quiver_max.desc"), 
				null, FrameType.GOAL, true, true, false).addCriterion("has_max_upgraded_quiver", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.UPGRADED_QUIVERS_MAX).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "upgrade_quiver_max"), fileHelper);

		Advancement.Builder.advancement().parent(root).display(ModItems.BATTLEAXES.netherite.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_netherite.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_netherite.desc"), 
				null, FrameType.GOAL, true, true, false).addCriterion("has_netherite_weapon", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(ModItemTags.NETHERITE_WEAPONS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "upgrade_netherite_weapon"), fileHelper);
		
		Advancement.Builder.advancement().parent(scythes).display(ModItems.PIGLIN_HEAD.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".collect_heads.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".collect_heads.desc"),
				null, FrameType.CHALLENGE, true, true, false).rewards(AdvancementRewards.Builder.experience(200).build()).
				addCriterion("creeper_head", InventoryChangeTrigger.TriggerInstance.hasItems(Items.CREEPER_HEAD)).addCriterion("skeleton_skull", InventoryChangeTrigger.TriggerInstance.hasItems(Items.SKELETON_SKULL)).
				addCriterion("wither_skeleton_skull", InventoryChangeTrigger.TriggerInstance.hasItems(Items.WITHER_SKELETON_SKULL)).addCriterion("zombie_head", InventoryChangeTrigger.TriggerInstance.hasItems(Items.ZOMBIE_HEAD)).
				addCriterion("blaze_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.BLAZE_HEAD.get())).addCriterion("enderman_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ENDERMAN_HEAD.get())).
				addCriterion("spider_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.SPIDER_HEAD.get())).addCriterion("cave_spider_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.CAVE_SPIDER_HEAD.get())).
				addCriterion("piglin_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.PIGLIN_HEAD.get())).addCriterion("zombified_piglin_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ZOMBIFIED_PIGLIN_HEAD.get())).
				addCriterion("husk_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.HUSK_HEAD.get())).addCriterion("stray_skull", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.STRAY_SKULL.get())).
				addCriterion("drowned_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.DROWNED_HEAD.get())).addCriterion("illager_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.ILLAGER_HEAD.get())).
				addCriterion("witch_head", InventoryChangeTrigger.TriggerInstance.hasItems(ModItems.WITCH_HEAD.get())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "collect_heads"), fileHelper);
	
		Advancement.Builder.advancement().parent(root).display(ModItems.WEAPON_OIL.get(), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".brew_oil.title"), new TranslatableComponent("advancement." + ModSpartanWeaponry.ID + ".brew_oil.desc"),
				null, FrameType.TASK, true, true, false).addCriterion("has_brewed_oil", BrewOilTrigger.TriggerInstance.brewedOil()).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "brew_oil"), fileHelper);
	}

	@Override
	public String getName() 
	{
		return ModSpartanWeaponry.NAME + " Advancements";
	}
}

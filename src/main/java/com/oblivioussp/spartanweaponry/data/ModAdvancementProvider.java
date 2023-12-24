package com.oblivioussp.spartanweaponry.data;

import java.util.function.Consumer;

import com.oblivioussp.spartanweaponry.ModSpartanWeaponry;
import com.oblivioussp.spartanweaponry.api.tags.ModItemTags;
import com.oblivioussp.spartanweaponry.init.ModItems;

import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.data.AdvancementProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TranslationTextComponent;
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
		Advancement root = Advancement.Builder.builder().withParent(null).withDisplay(ModItems.longswords.diamond, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".root.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".root.desc"),
				new ResourceLocation("minecraft", "textures/block/anvil.png"), FrameType.TASK, false, false, false).withCriterion("has_handle", InventoryChangeTrigger.Instance.forItems(ModItems.handle)).withCriterion("has_pole", InventoryChangeTrigger.Instance.forItems(ModItems.pole)).withRequirementsStrategy(IRequirementsStrategy.OR).
				save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "root"), fileHelper);
		
		Advancement daggers = Advancement.Builder.builder().withParent(root).withDisplay(ModItems.daggers.stone, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_dagger.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_dagger.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_dagger", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.DAGGERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "dagger"), fileHelper);
		Advancement.Builder.builder().withParent(root).withDisplay(ModItems.parryingDaggers.gold, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_parrying_dagger.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_parrying_dagger.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_parrying_dagger", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.PARRYING_DAGGERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "parrying_dagger"), fileHelper);
		Advancement longswords = Advancement.Builder.builder().withParent(root).withDisplay(ModItems.longswords.iron, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_longsword.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_longsword.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_longsword", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.LONGSWORDS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "longsword"), fileHelper);
		Advancement katanas = Advancement.Builder.builder().withParent(root).withDisplay(ModItems.katanas.stone, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_katana.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_katana.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_katana", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.KATANAS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "katana"), fileHelper);
		Advancement sabers = Advancement.Builder.builder().withParent(katanas).withDisplay(ModItems.sabers.iron, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_saber.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_saber.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_saber", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.SABERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "saber"), fileHelper);
		Advancement.Builder.builder().withParent(sabers).withDisplay(ModItems.rapiers.diamond, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_rapier.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_rapier.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_rapier", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.RAPIERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "rapier"), fileHelper);
		Advancement.Builder.builder().withParent(longswords).withDisplay(ModItems.greatswords.diamond, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_greatsword.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_greatsword.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_greatsword", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.GREATSWORDS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "greatsword"), fileHelper);
		Advancement.Builder.builder().withParent(root).withDisplay(ModItems.clubWood, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_club.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_club.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_club", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.CLUBS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "club"), fileHelper);
		Advancement.Builder.builder().withParent(root).withDisplay(ModItems.cestus, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_cestus.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_cestus.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_cestus", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.CESTUSAE).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "cestus"), fileHelper);
		Advancement battleHammers = Advancement.Builder.builder().withParent(root).withDisplay(ModItems.battleHammers.gold, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_battle_hammer.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_battle_hammer.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_battle_hammer", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.BATTLE_HAMMERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "battle_hammer"), fileHelper);
		Advancement.Builder.builder().withParent(battleHammers).withDisplay(ModItems.warhammers.diamond, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_warhammer.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_warhammer.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_warhammer", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.WARHAMMERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "warhammer"), fileHelper);
		Advancement spears = Advancement.Builder.builder().withParent(root).withDisplay(ModItems.spears.iron, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_spear.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_spear.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_spear", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.SPEARS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "spear"), fileHelper);
		Advancement.Builder.builder().withParent(spears).withDisplay(ModItems.halberds.gold, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_halberd.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_halberd.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_halberd", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.HALBERDS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "halberd"), fileHelper);
		Advancement.Builder.builder().withParent(spears).withDisplay(ModItems.pikes.diamond, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_pike.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_pike.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_pike", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.PIKES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "pike"), fileHelper);
		Advancement.Builder.builder().withParent(spears).withDisplay(ModItems.lances.iron, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_lance.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_lance.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_lance", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.LANCES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "lance"), fileHelper);
		Advancement longbows = Advancement.Builder.builder().withParent(root).withDisplay(ModItems.longbows.wood, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_longbow.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_longbow.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_longbow", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.LONGBOWS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "longbow"), fileHelper);
		Advancement.Builder.builder().withParent(longbows).withDisplay(ModItems.heavyCrossbows.wood, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_heavy_crossbow.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_heavy_crossbow.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_heavy_crossbow", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.HEAVY_CROSSBOWS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "heavy_crossbow"), fileHelper);
		Advancement.Builder.builder().withParent(daggers).withDisplay(ModItems.throwingKnives.iron, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_throwing_knife.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_throwing_knife.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_throwing_knife", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.THROWING_KNIVES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "throwing_knife"), fileHelper);
		Advancement.Builder.builder().withParent(root).withDisplay(ModItems.tomahawks.gold, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_tomahawk.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_tomahawk.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_tomahawk", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.TOMAHAWKS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "tomahawk"), fileHelper);
		Advancement.Builder.builder().withParent(root).withDisplay(ModItems.javelins.diamond, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_javelin.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_javelin.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_javelin", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.JAVELINS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "javelin"), fileHelper);
		Advancement.Builder.builder().withParent(daggers).withDisplay(ModItems.boomerangs.wood, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_boomerang.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_boomerang.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_boomerang", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.BOOMERANGS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "boomerang"), fileHelper);
		Advancement.Builder.builder().withParent(root).withDisplay(ModItems.battleaxes.diamond, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_battleaxe.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_battleaxe.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_battleaxe", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.BATTLEAXES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "battleaxe"), fileHelper);
		Advancement.Builder.builder().withParent(root).withDisplay(ModItems.flangedMaces.iron, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_flanged_mace.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_flanged_mace.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_flanged_mace", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.FLANGED_MACES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "flanged_mace"), fileHelper);
		Advancement glaives = Advancement.Builder.builder().withParent(spears).withDisplay(ModItems.glaives.iron, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_glaive.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_glaive.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_glaive", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.GLAIVES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "glaive"), fileHelper);
		Advancement.Builder.builder().withParent(root).withDisplay(ModItems.quarterstaves.gold, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_quarterstaff.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_quarterstaff.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_quarterstaff", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.QUARTERSTAVES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "quarterstaff"), fileHelper);
		Advancement scythes = Advancement.Builder.builder().withParent(glaives).withDisplay(ModItems.scythes.diamond, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_scythe.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_scythe.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_scythe", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.SCYTHES).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "scythe"), fileHelper);

		Advancement quivers = Advancement.Builder.builder().withParent(longbows).withDisplay(ModItems.quiverArrowSmall, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_quiver.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".craft_quiver.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_quiver", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.SMALL_QUIVERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "small_quiver"), fileHelper);
		Advancement upgradeQuiver = Advancement.Builder.builder().withParent(quivers).withDisplay(ModItems.quiverArrowLarge, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_quiver.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_quiver.desc"), 
				null, FrameType.TASK, true, true, false).withCriterion("has_upgraded_quiver", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.UPGRADED_QUIVERS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "upgrade_quiver"), fileHelper);
		Advancement.Builder.builder().withParent(upgradeQuiver).withDisplay(ModItems.quiverArrowHuge, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_quiver_max.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_quiver_max.desc"), 
				null, FrameType.GOAL, true, true, false).withCriterion("has_max_upgraded_quiver", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.UPGRADED_QUIVERS_MAX).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "upgrade_quiver_max"), fileHelper);

		Advancement.Builder.builder().withParent(root).withDisplay(ModItems.battleaxes.netherite, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_netherite.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".upgrade_netherite.desc"), 
				null, FrameType.GOAL, true, true, false).withCriterion("has_netherite_weapon", InventoryChangeTrigger.Instance.forItems(ItemPredicate.Builder.create().tag(ModItemTags.NETHERITE_WEAPONS).build())).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "upgrade_netherite_weapon"), fileHelper);
		
		Advancement.Builder.builder().withParent(scythes).withDisplay(ModItems.piglinHead, new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".collect_heads.title"), new TranslationTextComponent("advancement." + ModSpartanWeaponry.ID + ".collect_heads.desc"),
				null, FrameType.CHALLENGE, true, true, false).withRewards(AdvancementRewards.Builder.experience(200).build()).
				withCriterion("creeper_head", InventoryChangeTrigger.Instance.forItems(Items.CREEPER_HEAD)).withCriterion("skeleton_skull", InventoryChangeTrigger.Instance.forItems(Items.SKELETON_SKULL)).
				withCriterion("wither_skeleton_skull", InventoryChangeTrigger.Instance.forItems(Items.WITHER_SKELETON_SKULL)).withCriterion("zombie_head", InventoryChangeTrigger.Instance.forItems(Items.ZOMBIE_HEAD)).
				withCriterion("blaze_head", InventoryChangeTrigger.Instance.forItems(ModItems.blazeHead)).withCriterion("enderman_head", InventoryChangeTrigger.Instance.forItems(ModItems.endermanHead)).
				withCriterion("spider_head", InventoryChangeTrigger.Instance.forItems(ModItems.spiderHead)).withCriterion("cave_spider_head", InventoryChangeTrigger.Instance.forItems(ModItems.caveSpiderHead)).
				withCriterion("piglin_head", InventoryChangeTrigger.Instance.forItems(ModItems.piglinHead)).withCriterion("zombified_piglin_head", InventoryChangeTrigger.Instance.forItems(ModItems.zombifiedPiglinHead)).
				withCriterion("husk_head", InventoryChangeTrigger.Instance.forItems(ModItems.huskHead)).withCriterion("stray_skull", InventoryChangeTrigger.Instance.forItems(ModItems.straySkull)).
				withCriterion("drowned_head", InventoryChangeTrigger.Instance.forItems(ModItems.drownedHead)).withCriterion("illager_head", InventoryChangeTrigger.Instance.forItems(ModItems.illagerHead)).
				withCriterion("witch_head", InventoryChangeTrigger.Instance.forItems(ModItems.witchHead)).save(consumer, new ResourceLocation(ModSpartanWeaponry.ID, "collect_heads"), fileHelper);
	}

	@Override
	public String getName() 
	{
		return ModSpartanWeaponry.NAME + " Advancements";
	}
}

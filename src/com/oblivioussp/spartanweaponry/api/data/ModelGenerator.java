package com.oblivioussp.spartanweaponry.api.data;

import com.oblivioussp.spartanweaponry.api.ModelOverrides;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile.ExistingModelFile;

/**
 * Contains helper methods to generate customised model files based off items from Spartan Weaponry.<br>
 * Add to the addon mod's {@linkplain ItemModelProvider#registerModels()} method to use them
 * @author ObliviousSpartan
 *
 */
public class ModelGenerator
{
	protected final ItemModelProvider itemModelProvider;
	
	public ModelGenerator(ItemModelProvider itemModelProviderIn)
	{
		itemModelProvider = itemModelProviderIn;
	}
	
	/**
	 * Generates a model using the same base model as most mundane items
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createSimpleModel(Item item)
	{
		String itemPath = item.getRegistryName().getPath();
		return itemModelProvider.withExistingParent(itemPath, itemModelProvider.mcLoc("item/generated")).texture("layer0", "item/" + itemPath).getLocation();
	}
	
	/**
	 * Generates a model using a defined parent model
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @param parent The location of the parent model
	 * @return The generated models location
	 */
	public ResourceLocation createSimpleModel(Item item, ResourceLocation parent)
	{
		String itemPath = item.getRegistryName().getPath();
		return itemModelProvider.withExistingParent(itemPath, parent).texture("layer0", "item/" + itemPath).getLocation();
	}
	
	/**
	 * Generates standard and throwing models using the same base model as a Dagger
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createDaggerModels(Item item)
	{
		String itemPath = item.getRegistryName().getPath();
		ResourceLocation throwingModel = itemModelProvider.withExistingParent(itemPath + "_throwing", BaseModels.baseDaggerThrowing).texture("layer0", "item/" + itemPath).getLocation();
		return itemModelProvider.withExistingParent(itemPath, BaseModels.baseDagger).texture("layer0", "item/" + itemPath).override().predicate(new ResourceLocation(ModelOverrides.THROWING), 1.0f).model(new ExistingModelFile(throwingModel, itemModelProvider.existingFileHelper)).end().
				getLocation();
	}
	
	/**
	 * Generates standard and blocking models using the same base model as a Parrying Dagger
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createParryingDaggerModels(Item item) 
	{
		String itemPath = item.getRegistryName().getPath();
		ResourceLocation blockingModel = itemModelProvider.withExistingParent(itemPath + "_blocking", BaseModels.baseParryingDaggerBlocking).texture("layer0", "item/" + itemPath).getLocation();
		return itemModelProvider.withExistingParent(itemPath, BaseModels.baseParryingDagger).texture("layer0", "item/" + itemPath).override().predicate(new ResourceLocation(ModelOverrides.BLOCKING), 1.0f).model(new ExistingModelFile(blockingModel, itemModelProvider.existingFileHelper)).end().
			getLocation();
	}

	/**
	 * Generates a model using the same base model as a Longsword
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createLongswordModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseLongsword);
	}
	
	/**
	 * Generates a model using the same base model as a Katana
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createKatanaModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseKatana);
	}
	
	/**
	 * Generates a model using the same base model as a Saber
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createSaberModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseSaber);
	}
	
	/**
	 * Generates a model using the same base model as a Rapier
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createRapierModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseRapier);
	}
	
	/**
	 * Generates a model using the same base model as a Greatsword
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createGreatswordModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseGreatsword);
	}
	
	/**
	 * Generates a model using the same base model as a Club
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createClubModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseClub);
	}
	
	/**
	 * Generates a model using the same base model as a Cestus
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createCestusModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseCestus);
	}
	
	/**
	 * Generates a model using the same base model as a Battle Hammer
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createBattleHammerModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseBattleHammer);
	}
	
	/**
	 * Generates a model using the same base model as a Warhammer
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createWarhammerModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseWarhammer);
	}
	
	/**
	 * Generates a model using the same base model as a Spear
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createSpearModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseSpear);
	}
	
	/**
	 * Generates a model using the same base model as a Halberd
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createHalberdModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseHalberd);
	}
	
	/**
	 * Generates a model using the same base model as a Pike
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createPikeModel(Item item)
	{
		return createSimpleModel(item, BaseModels.basePike);
	}
	
	/**
	 * Generates a model using the same base model as a Lance
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createLanceModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseLance);
	}
	
	/**
	 * Generates standard and 3 drawing models using the same base model as a Longbow
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createLongbowModels(Item item)
	{
		String itemPath = item.getRegistryName().getPath();
		ResourceLocation pulling0 = itemModelProvider.withExistingParent(itemPath + "_pulling_0", BaseModels.baseLongbowPulling).texture("layer0", "item/" + itemPath + "_pulling_0").getLocation();
		ResourceLocation pulling1 = itemModelProvider.withExistingParent(itemPath + "_pulling_1", BaseModels.baseLongbowPulling).texture("layer0", "item/" + itemPath + "_pulling_1").getLocation();
		ResourceLocation pulling2 = itemModelProvider.withExistingParent(itemPath + "_pulling_2", BaseModels.baseLongbowPulling).texture("layer0", "item/" + itemPath + "_pulling_2").getLocation();
		return itemModelProvider.withExistingParent(itemPath, BaseModels.baseLongbow).texture("layer0", "item/" + itemPath + "_standby").
				override().predicate(new ResourceLocation(ModelOverrides.PULLING), 1.0f).model(new ExistingModelFile(pulling0, itemModelProvider.existingFileHelper)).end().
				override().predicate(new ResourceLocation(ModelOverrides.PULLING), 1.0f).predicate(new ResourceLocation(ModelOverrides.PULL), 0.65f).model(new ExistingModelFile(pulling1, itemModelProvider.existingFileHelper)).end().
				override().predicate(new ResourceLocation(ModelOverrides.PULLING), 1.0f).predicate(new ResourceLocation(ModelOverrides.PULL), 0.9f).model(new ExistingModelFile(pulling2, itemModelProvider.existingFileHelper)).end().
				getLocation();
	}
	
	/**
	 * Generates 1 standard, 3 drawing, 1 loaded and 1 firing models using the same base model as a Heavy Crossbow
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createHeavyCrossbowModels(Item item)
	{
		String itemPath = item.getRegistryName().getPath();
		ResourceLocation pulling0 = itemModelProvider.withExistingParent(itemPath + "_pulling_0", BaseModels.baseHeavyCrossbowPulling).texture("layer0", "item/" + itemPath + "_pulling_0").getLocation();
		ResourceLocation pulling1 = itemModelProvider.withExistingParent(itemPath + "_pulling_1", BaseModels.baseHeavyCrossbowPulling).texture("layer0", "item/" + itemPath + "_pulling_1").getLocation();
		ResourceLocation pulling2 = itemModelProvider.withExistingParent(itemPath + "_pulling_2", BaseModels.baseHeavyCrossbowPulling).texture("layer0", "item/" + itemPath + "_pulling_2").getLocation();
		ResourceLocation loaded = itemModelProvider.withExistingParent(itemPath + "_loaded", BaseModels.baseHeavyCrossbowLoaded).texture("layer0", "item/" + itemPath + "_loaded").getLocation();
		ResourceLocation firing = itemModelProvider.withExistingParent(itemPath + "_firing", BaseModels.baseHeavyCrossbowFiring).texture("layer0", "item/" + itemPath + "_loaded").getLocation();
		return itemModelProvider.withExistingParent(itemPath, BaseModels.baseHeavyCrossbow).texture("layer0", "item/" + itemPath + "_standby").
				override().predicate(new ResourceLocation(ModelOverrides.PULLING), 1.0f).model(new ExistingModelFile(pulling0, itemModelProvider.existingFileHelper)).end().
				override().predicate(new ResourceLocation(ModelOverrides.PULLING), 1.0f).predicate(new ResourceLocation(ModelOverrides.PULL), 0.65f).model(new ExistingModelFile(pulling1, itemModelProvider.existingFileHelper)).end().
				override().predicate(new ResourceLocation(ModelOverrides.PULLING), 1.0f).predicate(new ResourceLocation(ModelOverrides.PULL), 1.0f).model(new ExistingModelFile(pulling2, itemModelProvider.existingFileHelper)).end().
				override().predicate(new ResourceLocation(ModelOverrides.CHARGED), 1.0f).model(new ExistingModelFile(loaded, itemModelProvider.existingFileHelper)).end().
				override().predicate(new ResourceLocation(ModelOverrides.PULLING), 1.0f).predicate(new ResourceLocation(ModelOverrides.CHARGED), 1.0f).model(new ExistingModelFile(firing, itemModelProvider.existingFileHelper)).end().
				getLocation();
	}

	/**
	 * Generates standard and throwing models using the same base model as a Throwing Knife
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createThrowingKnifeModels(Item item)
	{
		String itemPath = item.getRegistryName().getPath();
		ResourceLocation throwingModel = itemModelProvider.withExistingParent(itemPath + "_throwing", BaseModels.baseThrowingKnifeThrowing).texture("layer0", "item/" + itemPath).getLocation();
		return itemModelProvider.withExistingParent(itemPath, BaseModels.baseThrowingKnife).texture("layer0", "item/" + itemPath).
				override().predicate(new ResourceLocation(ModelOverrides.THROWING), 1.0f).predicate(new ResourceLocation(ModelOverrides.EMPTY), 0.0f).model(new ExistingModelFile(throwingModel, itemModelProvider.existingFileHelper)).end().
				override().predicate(new ResourceLocation(ModelOverrides.EMPTY), 1.0f).model(new ExistingModelFile(BaseModels.baseThrowingKnifeEmpty, itemModelProvider.existingFileHelper)).end().
				getLocation();
	}

	/**
	 * Generates standard and throwing models using the same base model as a Tomahawk
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createTomahawkModels(Item item)
	{
		String itemPath = item.getRegistryName().getPath();
		ResourceLocation throwingModel = itemModelProvider.withExistingParent(itemPath + "_throwing", BaseModels.baseTomahawkThrowing).texture("layer0", "item/" + itemPath).getLocation();
		return itemModelProvider.withExistingParent(itemPath, BaseModels.baseTomahawk).texture("layer0", "item/" + itemPath).
				override().predicate(new ResourceLocation(ModelOverrides.THROWING), 1.0f).predicate(new ResourceLocation(ModelOverrides.EMPTY), 0.0f).model(new ExistingModelFile(throwingModel, itemModelProvider.existingFileHelper)).end().
				override().predicate(new ResourceLocation(ModelOverrides.EMPTY), 1.0f).model(new ExistingModelFile(BaseModels.baseTomahawkEmpty, itemModelProvider.existingFileHelper)).end().
				getLocation();
	}

	/**
	 * Generates standard and throwing models using the same base model as a Javelin
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createJavelinModels(Item item)
	{
		String itemPath = item.getRegistryName().getPath();
		ResourceLocation throwingModel = itemModelProvider.withExistingParent(itemPath + "_throwing", BaseModels.baseJavelinThrowing).texture("layer0", "item/" + itemPath).getLocation();
		return itemModelProvider.withExistingParent(itemPath, BaseModels.baseJavelin).texture("layer0", "item/" + itemPath).
				override().predicate(new ResourceLocation(ModelOverrides.THROWING), 1.0f).predicate(new ResourceLocation(ModelOverrides.EMPTY), 0.0f).model(new ExistingModelFile(throwingModel, itemModelProvider.existingFileHelper)).end().
				override().predicate(new ResourceLocation(ModelOverrides.EMPTY), 1.0f).model(new ExistingModelFile(BaseModels.baseJavelinEmpty, itemModelProvider.existingFileHelper)).end().
				getLocation();
	}

	/**
	 * Generates standard and throwing models using the same base model as a Boomerang
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createBoomerangModels(Item item)
	{
		String itemPath = item.getRegistryName().getPath();
		ResourceLocation throwingModel = itemModelProvider.withExistingParent(itemPath + "_throwing", BaseModels.baseBoomerangThrowing).texture("layer0", "item/" + itemPath).getLocation();
		return itemModelProvider.withExistingParent(itemPath, BaseModels.baseBoomerang).texture("layer0", "item/" + itemPath).
				override().predicate(new ResourceLocation(ModelOverrides.THROWING), 1.0f).predicate(new ResourceLocation(ModelOverrides.EMPTY), 0.0f).model(new ExistingModelFile(throwingModel, itemModelProvider.existingFileHelper)).end().
				override().predicate(new ResourceLocation(ModelOverrides.EMPTY), 1.0f).model(new ExistingModelFile(BaseModels.baseBoomerangEmpty, itemModelProvider.existingFileHelper)).end().
				getLocation();
	}
	
	/**
	 * Generates a model using the same base model as a Battleaxe
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createBattleaxeModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseBattleaxe);
	}
	
	/**
	 * Generates a model using the same base model as a Flanged Mace
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createFlangedMaceModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseFlangedMace);
	}
	
	/**
	 * Generates a model using the same base model as a Glaive
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createGlaiveModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseGlaive);
	}
	
	/**
	 * Generates a model using the same base model as a Quarterstaff
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createQuarterstaffModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseQuarterstaff);
	}
	
	/**
	 * Generates a model using the same base model as a Scythe
	 * @param item The item to generate the model for. The registry name is used for the texture name
	 * @return The generated models location
	 */
	public ResourceLocation createScytheModel(Item item)
	{
		return createSimpleModel(item, BaseModels.baseScythe);
	}
}

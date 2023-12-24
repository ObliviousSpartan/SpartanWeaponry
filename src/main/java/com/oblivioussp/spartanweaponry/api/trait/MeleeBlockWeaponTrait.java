package com.oblivioussp.spartanweaponry.api.trait;

import java.util.Optional;

import com.oblivioussp.spartanweaponry.api.IWeaponTraitContainer;
import com.oblivioussp.spartanweaponry.api.WeaponTraits;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.ShieldBlockEvent;

public class MeleeBlockWeaponTrait extends WeaponTrait implements IActionTraitCallback
{
	public MeleeBlockWeaponTrait(String typeIn, String modIdIn, TraitQuality qualityIn) 
	{
		super(typeIn, modIdIn, qualityIn);
	}
	
	@Override
	public Optional<IActionTraitCallback> getActionCallback()
	{
		return Optional.of(this);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(ItemStack usingStackIn, Level levelIn, Player playerIn,
			InteractionHand handIn)
	{
		if(playerIn.isCrouching())
			return InteractionResultHolder.fail(usingStackIn);
		playerIn.startUsingItem(handIn);
		return InteractionResultHolder.consume(usingStackIn);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stackIn) 
	{
		return UseAnim.BLOCK;
	}

	public static void onBlockEvent(ShieldBlockEvent ev)
	{
		LivingEntity living = ev.getEntityLiving();
		if(living.getUseItem().getItem() instanceof IWeaponTraitContainer<?> container && container.hasWeaponTrait(WeaponTraits.BLOCK_MELEE.get()))
		{
			DamageSource source = ev.getDamageSource();
			
			// Block Melee attacks only! Explosion, Fire, Magic, Projectile and unblockable damage won't be blocked!
			if(source.isExplosion() || source.isFire() || source.isMagic() || source.isProjectile() || source.isBypassArmor())
				ev.setCanceled(true);
		}
	}
}

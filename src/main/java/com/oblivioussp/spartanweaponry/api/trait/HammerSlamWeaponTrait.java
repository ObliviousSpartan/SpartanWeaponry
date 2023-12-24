package com.oblivioussp.spartanweaponry.api.trait;

import java.util.Optional;

import com.oblivioussp.spartanweaponry.init.ModSounds;

import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.decoration.ArmorStand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

public class HammerSlamWeaponTrait extends WeaponTrait implements IActionTraitCallback 
{

	public HammerSlamWeaponTrait(String typeIn, String modIdIn) 
	{
		super(typeIn, modIdIn, TraitQuality.POSITIVE);
	}
	
	@Override
	public Optional<IActionTraitCallback> getActionCallback() 
	{
		return Optional.of(this);
	}
	
	@Override
	public InteractionResult useOn(UseOnContext contextIn) 
	{
		Level level = contextIn.getLevel();
		Player player = contextIn.getPlayer();
		Vec3 hitPos = contextIn.getClickLocation();
		ItemStack weapon = contextIn.getItemInHand();
		
		if(!level.isClientSide && !player.getCooldowns().isOnCooldown(weapon.getItem()) && player.getAttackStrengthScale(0.0f) == 1.0f)
		{
			for(LivingEntity entity : level.getEntitiesOfClass(LivingEntity.class, AABB.ofSize(hitPos, 3.0f, 3.0f, 3.0f)))
			{
				if(entity != player && !player.isAlliedTo(entity) && (!(entity instanceof ArmorStand) || ((ArmorStand)entity).isMarker()))
				{
					float yRot = entity.getYRot();
					final float halfPi = (float)Math.PI / 180.0f;
					entity.knockback(1.0f, Mth.sin(yRot * halfPi), -Mth.cos(yRot * halfPi));
					entity.hurt(DamageSource.playerAttack(player), (float)(player.getAttributeValue(Attributes.ATTACK_DAMAGE) / 2.0d));
					weapon.hurtAndBreak(1, player, (playerIn) -> playerIn.broadcastBreakEvent(contextIn.getHand()));
				}
			}
			player.swing(contextIn.getHand(), true);
			player.playNotifySound(ModSounds.HAMMER_SLAMS_INTO_GROUND.get(), SoundSource.PLAYERS, 1.0f, 0.6f);
			((ServerLevel)level).sendParticles(ParticleTypes.EXPLOSION, hitPos.x, hitPos.y, hitPos.z, 8, 0.0d, 0.1d, 0.0d, 0.5d);
			player.resetAttackStrengthTicker();
			player.getCooldowns().addCooldown(weapon.getItem(), 60);
		}
		
		return InteractionResult.PASS;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(ItemStack usingStackIn, Level levelIn, Player playerIn,
			InteractionHand handIn) 
	{
		return InteractionResultHolder.pass(usingStackIn);
	}
}

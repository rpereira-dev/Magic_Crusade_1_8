package fr.toss.magiccrusade.common.classes.spell.champion;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellEarthShield implements ISpell
{

	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.EARTH_SHIELD);
	}

	@Override
	public void animate(Entity caster, Entity target)
	{
		float	rayon;
		float	v_x;
		float	v_y;
		float	v_z;
		
		rayon = 4.0f;
		for (int phi = -180; phi < 180; phi++)
		{
			for (int teta = -90; teta < 90; teta++)
			{
				if (caster.worldObj.rand.nextInt() % 100 == 0)
				{
					v_x = rayon * MathHelper.cos(teta) * MathHelper.cos(phi);
					v_y = rayon * MathHelper.cos(teta) * MathHelper.sin(phi);
					v_z = rayon * MathHelper.sin(teta);
					caster.worldObj.spawnParticle(EnumParticleTypes.CRIT_MAGIC, caster.posX, caster.posY, caster.posZ, v_x / 2.0f, v_y *2.0f, v_z / 2.0f, 1);
				}
			}
		}
	}

	@Override
	public void do_spell(Entity caster, Entity target, Stats stat)
	{
		EntityLivingBase	entity;
		
		entity = (EntityLivingBase)caster;
		entity.addPotionEffect(new PotionEffect(Potion.absorption.id, 40, (int) (3 + stat.get_endurance() * 0.01f)));
	}

	@Override
	public int	get_target_id(EntityLivingBase caster) throws SpellException
	{
		return (-1);
	}

}

package fr.toss.magiccrusade.common.classes.spell.champion;

import com.sun.org.apache.xml.internal.security.utils.I18n;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellIronskin implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.IRONSKIN);
	}

	@Override
	public void animate(Entity caster, Entity target)
	{
		float	rayon;
		float	v_x;
		float	v_y;
		float	v_z;
		
		rayon = 4.0f;
		for (int i = 0; i < 100; i++)
		{
			v_x = caster.worldObj.rand.nextFloat();
			v_y = caster.worldObj.rand.nextFloat();
			v_z = caster.worldObj.rand.nextFloat();
			if (caster.worldObj.rand.nextInt(2) == 0)
			{
				v_x = -v_x;
			}
			else if (caster.worldObj.rand.nextInt(2) == 0)
			{
				v_y = -v_y;
			}
			else if (caster.worldObj.rand.nextInt(2) == 0)
			{
				v_z = -v_z;
			}
			caster.worldObj.spawnParticle(EnumParticleTypes.SPELL_MOB_AMBIENT, caster.posX, caster.posY, caster.posZ, v_x , v_y, v_z, 10);
		}
	}

	@Override
	public void do_spell(Entity caster, Entity target, Stats stat)
	{
		EntityLivingBase	entity;
		
		entity = (EntityLivingBase)caster;
		entity.addPotionEffect(new PotionEffect(Potion.resistance.id, 30 * 20, 1));
	}

	@Override
	public int	get_target_id(EntityLivingBase caster) throws SpellException
	{
		return (-1);
	}

}

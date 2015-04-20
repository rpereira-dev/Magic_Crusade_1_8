package fr.toss.magiccrusade.common.classes.spell.rogue;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.classes.spell.SpellUtils;
import fr.toss.magiccrusade.common.entity.EntityDoppleganger;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellShadowClones implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.SHADOWCLONES);
	}
	
	@Override
	public void animate(IMagicEntity caster, Entity target)
	{
		//TODO
	}

	@Override
	public void do_spell(IMagicEntity magic_caster, Entity target, Stats stat)
	{
		EntityDoppleganger	entity;
		Entity	caster;
		
		caster = magic_caster.getEntity();
		entity = new EntityDoppleganger(caster.worldObj, caster);
		entity.setLocationAndAngles(caster.posX + 1,  caster.posY , caster.posZ, 0.1f, 0.1f);
		caster.worldObj.spawnEntityInWorld(entity);
		entity = new EntityDoppleganger(caster.worldObj, caster);
		entity.setLocationAndAngles(caster.posX - 1,  caster.posY , caster.posZ, 0.1f, 0.1f);
		caster.worldObj.spawnEntityInWorld(entity);
	}
	
	@Override
	public int	get_target_id(EntityLivingBase caster) throws SpellException
	{
		List	lst;
		int		count;

		lst  = SpellUtils.getEntitiesAround(caster, 16.0d, 16.0d, 16.0d);
		count = 0;
		for (Object obj : lst)
		{
			if (obj instanceof EntityDoppleganger)
			{
				if (((EntityDoppleganger)obj).getOwnerId().equals(caster.getUniqueID().toString()))
				{
					count++;
					if (count == 2)
					{
						throw(new SpellException("You already have 2 Doppleganger summoned"));
					}
				}
			}
		}
		return (-1);
	}

}

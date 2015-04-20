package fr.toss.magiccrusade.common.classes.spell.mage;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.classes.spell.SpellUtils;
import fr.toss.magiccrusade.common.entity.EntitySnowCube;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellSummonSnowcube implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.SUMMON_SNOWCUBE);
	}
	
	@Override
	public void animate(IMagicEntity caster, Entity target)
	{
		//TODO
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
			if (obj instanceof EntitySnowCube)
			{
				if (((EntitySnowCube)obj).getOwnerId().equals(caster.getUniqueID().toString()))
				{
					count++;
					if (count == 2)
					{
						throw(new SpellException("You already have 2 snowcubes summoned"));
					}
				}
			}
		}
		return (-1);
	}

	@Override
	public void do_spell(IMagicEntity caster, Entity target, Stats stat)
	{
		EntitySnowCube	entity;
		
		entity = new EntitySnowCube(caster.getEntity().worldObj, caster.getEntity());
		entity.setLocationAndAngles(caster.getEntity().posX + 1,  caster.getEntity().posY + 1, caster.getEntity().posZ, 0.1f, 0.1f);
		caster.getEntity().worldObj.spawnEntityInWorld(entity);
	}
}

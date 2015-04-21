package fr.toss.magiccrusade.common.classes.spell.champion;

import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.classes.spell.SpellUtils;
import fr.toss.magiccrusade.common.entity.EntitySnowCube;
import fr.toss.magiccrusade.common.entity.EntityTurret;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellTurret implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.TURRET);
	}
	
	@Override
	public void animate(IMagicEntity caster, Entity target) {}
	
	@Override
	public int	get_target_id(EntityLivingBase caster) throws SpellException
	{
		return (-1);
	}

	@Override
	public void do_spell(IMagicEntity caster, Entity target, Stats stat)
	{
		EntityTurret	entity;
		
		entity = new EntityTurret(caster.getEntity().worldObj, caster.getEntity());
		entity.setLocationAndAngles(caster.getEntity().posX + 1,  caster.getEntity().posY + 2, caster.getEntity().posZ, 0.1f, 0.1f);
		caster.getEntity().worldObj.spawnEntityInWorld(entity);
	}
}

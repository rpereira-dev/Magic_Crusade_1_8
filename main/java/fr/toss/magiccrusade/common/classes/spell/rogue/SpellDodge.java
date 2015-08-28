package fr.toss.magiccrusade.common.classes.spell.rogue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellDodge implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.DODGE);
	}
	
	@Override
	public void animate(IMagicEntity caster, Entity target)
	{
		this.do_spell(caster, target, null);
	}

	@Override
	public void do_spell(IMagicEntity caster, Entity target, Stats stat)
	{
		Vec3	vec;
		
		vec = caster.getEntity().getLookVec();
		caster.getEntity().motionX = vec.xCoord * -1.5d;
		caster.getEntity().motionY += 0.5d;
		caster.getEntity().motionZ = vec.zCoord * -1.5d;
	}
	
	@Override
	public int	get_target_id(EntityLivingBase caster) throws SpellException
	{
		return (-1);
	}
}

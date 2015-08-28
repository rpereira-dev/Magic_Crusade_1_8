package fr.toss.magiccrusade.common.classes.spell.rogue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.entity.EntityKnife;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellKnife implements ISpell
{

	private World worldIn;

	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.KNIFE);
	}

	@Override
	public void animate(IMagicEntity caster, Entity target)
	{
		
	}

	@Override
	public void do_spell(IMagicEntity caster, Entity target, Stats stat)
	{
        EntityKnife	entity;
        Vec3	vec;
		
		vec = caster.getEntity().getLookVec();
		entity = new EntityKnife(caster.getWorld());
		entity.setLocationAndAngles(caster.getEntity().posX,  caster.getEntity().posY +1 , caster.getEntity().posZ, 0.1f, 0.1f);
		caster.getWorld().spawnEntityInWorld(entity);		
		entity.motionX = vec.xCoord * 1.5d;
		entity.motionZ = vec.zCoord * 1.5d;
	}

	@Override
	public int get_target_id(EntityLivingBase caster) throws SpellException
	{
		return (-1);
	}


}

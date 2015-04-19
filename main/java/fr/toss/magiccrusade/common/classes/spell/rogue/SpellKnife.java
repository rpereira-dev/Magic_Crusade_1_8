package fr.toss.magiccrusade.common.classes.spell.rogue;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.entity.projectile.EntityFireball;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.entity.EntityDoppleganger;
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
        EntityArrow	entity;
        Vec3	vec;
		
		vec = caster.getLookVec();
		
		entity = new EntityArrow(caster.worldObj);
		entity.setLocationAndAngles(caster.posX,  caster.posY +1 , caster.posZ, 0.1f, 0.1f);
		
		caster.worldObj.spawnEntityInWorld(entity);		
		entity.motionX = vec.xCoord * 1.5d;
		entity.motionZ = vec.zCoord * 1.5d;
	}

	@Override
	public int get_target_id(EntityLivingBase caster) throws SpellException {
		// TODO Auto-generated method stub
		return 0;
	}


}

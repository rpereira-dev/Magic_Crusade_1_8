package fr.toss.magiccrusade.common.classes.spell.rogue;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import fr.toss.magiccrusade.client.entity.EntityFX_Colored;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.classes.spell.SpellUtils;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellPoison implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.POISON);
	}

	@Override
	public void animate(IMagicEntity caster, Entity target)
	{
		Random				rand;
		EntityFX_Colored	particles;
		World				world;
		float				v_x;
		float				v_y;
		float				v_z;
		
		rand = caster.getWorld().rand;
		world = caster.getWorld();
		for (int i = 0; i < 100; i++)
		{
			v_x = rand.nextFloat();
			v_y = rand.nextFloat();
			v_z = rand.nextFloat();
			if (rand.nextInt(2) == 0)
			{
				v_x = -v_x;
			}
			if (rand.nextInt(2) == 0)
			{
				v_y = -v_y;
			}
			if (rand.nextInt(2) == 0)
			{
				v_z = -v_z;
			}
			particles = new EntityFX_Colored(world, target.posX, target.posY, target.posZ, v_x, v_y, v_z, 2.0f, 0.2f, 0.7f, 0.2f);
			Minecraft.getMinecraft().effectRenderer.addEffect(particles);
			particles = new EntityFX_Colored(world, target.posX, target.posY, target.posZ, v_x, v_y, v_z, 2.0f, 0.5f, rand.nextFloat(), 0.5f);
			Minecraft.getMinecraft().effectRenderer.addEffect(particles);
		}
	}

	@Override
	public void do_spell(IMagicEntity caster, Entity target, Stats stat)
	{
		if (target instanceof EntityLivingBase)
		{
			((EntityLivingBase) target).addPotionEffect(new PotionEffect(Potion.poison.id, 10 * 20));
		}
	}

	@Override
	public int	get_target_id(EntityLivingBase caster) throws SpellException
	{
		Entity	target;
		
		target = SpellUtils.getLookingEntity(caster, 16.0d);
		if (target == null)
		{
			throw(new SpellException("No target available"));
		}
		return (target.getEntityId());
	}

}

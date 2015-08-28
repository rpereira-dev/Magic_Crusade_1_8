package fr.toss.magiccrusade.common.classes.spell.champion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import fr.toss.magiccrusade.client.entity.EntityFX_Colored;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellCharge implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.CHARGE);
	}
	
	@Override
	public void animate(IMagicEntity caster, Entity target)
	{
		World 			world;
		EntityFX		particles;
		double			velx;
		double			velz;

		this.do_spell(caster, target, null);
		world = Minecraft.getMinecraft().theWorld;
		for (int i = 0; i < 100; i++)
        {
			velx = world.rand.nextFloat();
			velz = world.rand.nextFloat();
			if (world.rand.nextInt(2) == 0)
			{
				velx = -velx;
			}
			else if (world.rand.nextInt(2) == 0)
			{
				velz = -velz;
			}
    		particles = new EntityFX_Colored(world, caster.getEntity().posX, caster.getEntity().posY, caster.getEntity().posZ, velx, 0, velz, 2.5f, 0, 1.0f, 5.0f);
    		Minecraft.getMinecraft().effectRenderer.addEffect(particles);
        }
	}
	
	@Override
	public int	get_target_id(EntityLivingBase caster) throws SpellException
	{
		return (-1);
	}

	@Override
	public void do_spell(IMagicEntity caster, Entity target, Stats stat)
	{
		Vec3	vec;
		
		vec = caster.getEntity().getLookVec();
		caster.getEntity().motionX = vec.xCoord * 3.5d;
		caster.getEntity().motionY += 1.2d;
		caster.getEntity().motionZ = vec.zCoord * 3.5d;
	}
}

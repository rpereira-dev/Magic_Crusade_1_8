package fr.toss.magiccrusade.common.classes.spell.necromancer;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.client.render.EntityFX_Colored;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;

public class SpellRaise implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.RAISE);
	}

	@Override
	public void animate(Entity caster, Entity target)
	{
		EntityFX_Colored	particles;
		World				world;
		float				x;
		float				y;
		float				z;
		float				rayon;
		
		world = caster.worldObj;
		rayon = 4.0f;
		for (int phi = -180; phi < 180; phi += 2)
		{
			for (int teta = -90; teta < 90; teta += 2)
			{
				x = rayon * MathHelper.cos(teta) * MathHelper.cos(phi);
				y = rayon * MathHelper.cos(teta) * MathHelper.sin(phi);
				z = rayon * MathHelper.sin(teta);
				
				world.spawnParticle(EnumParticleTypes.CLOUD, caster.posX, caster.posY, caster.posZ, x, -y, z);
				particles = new EntityFX_Colored(world, x, y, z, x, -y, z, 2.0f, 5.0f, 0.0f, 1.5f);
				Minecraft.getMinecraft().effectRenderer.addEffect(particles);
			}
		}
	}

	@Override
	public void do_spell(Entity caster, Entity target)
	{
		//TODO
	}

	@Override
	public int get_target_id(ClientPlayer client)
	{
		return (-1);
	}

}

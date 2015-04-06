package fr.toss.magiccrusade.common.classes.spell.champion;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.Packet;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.client.render.EntityFX_Colored;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.network.PacketSpellServer;
import fr.toss.magiccrusade.common.network.Packets;
import fr.toss.magiccrusade.utils.MagicLogger;

public class SpellCharge implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.CHARGE);
	}
	
	@Override
	public void animate(Entity caster, Entity target)
	{
		World 			world;
		EntityFX		particles;
		double			velx;
		double			velz;
		
		this.do_spell(caster, target);
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
    		particles = new EntityFX_Colored(world, caster.posX, caster.posY, caster.posZ, velx, 0, velz, 2.5f, 0, 1.0f, 5.0f);
    		Minecraft.getMinecraft().effectRenderer.addEffect(particles);
        }
	}
	
	@Override
	public int	get_target_id(ClientPlayer client)
	{
		return (-1);
	}

	@Override
	public void do_spell(Entity caster, Entity target)
	{
		Vec3	vec;
		
		vec = caster.getLookVec();
		caster.motionX = vec.xCoord * 3.5d;
		caster.motionY += 1.2d;
		caster.motionZ = vec.zCoord * 3.5d;
	}
}

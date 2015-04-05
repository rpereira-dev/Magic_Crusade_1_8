package fr.toss.magiccrusade.common.classes.spell;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.client.render.EntityFX_Colored;
import fr.toss.magiccrusade.common.network.PacketSpellServer;
import fr.toss.magiccrusade.common.network.Packets;

public class SpellShockwave implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.SHOCKWAVE);
	}

	@Override
	public void animate(Entity caster, Entity target)
	{
		World 			world;
		EntityPlayer	player;
		EntityFX		particles;
		float			velx;
		float			velz;
		
		world = Minecraft.getMinecraft().theWorld;
    	player = Minecraft.getMinecraft().thePlayer;
		for (int i = 0; i < 250; i++)
        {
			velx = world.rand.nextFloat();
			velz = world.rand.nextFloat();
			if (world.rand.nextInt(2) == 0)
			{
				velx = -velx;
			}
			if (world.rand.nextInt(2) == 0)
			{
				velz = -velz;
			}
    		particles = new EntityFX_Colored(world, caster.posX, caster.posY, caster.posZ, velx, 0, velz, 2.5f, 0, 2.5f, 5.0f);
    		Minecraft.getMinecraft().effectRenderer.addEffect(particles);
        }
	}

	@Override
	public void send_spell_to_server(ClientPlayer client)
	{
		PacketSpellServer	packet;
		
		packet = new PacketSpellServer(this.get_enum_spell().id, client.get_player().getEntityId(), get_target_id(client));
		Packets.network.sendToServer(packet);
	}
	
	@Override
	public int	get_target_id(ClientPlayer client)
	{
		return (-1);
	}

	@Override
	public void do_spell(Entity caster, Entity target)
	{
		for (int x = 0; x < 6; x++)
		{
			for (int z = 0; z < 6; z++)
			{
				caster.worldObj.createExplosion(caster, caster.posX + x - 3, caster.posY, caster.posZ + z - 3, 0.5f, true);	
			}
		}
	}
}

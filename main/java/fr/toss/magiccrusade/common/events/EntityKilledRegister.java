package fr.toss.magiccrusade.common.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.common.network.PacketExperience;
import fr.toss.magiccrusade.common.network.Packets;

public class EntityKilledRegister
{
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		IMessage	packet;

		if (event.source.getDamageType().equals("player") && event.source.getEntity() instanceof EntityPlayerMP)
		{
			packet = new PacketExperience(100);
			Packets.network.sendTo(packet, (EntityPlayerMP)event.source.getEntity());
		}
	}
}

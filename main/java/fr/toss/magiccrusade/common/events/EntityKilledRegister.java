package fr.toss.magiccrusade.common.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import fr.toss.magiccrusade.common.network.PacketExperience;
import fr.toss.magiccrusade.common.network.Packets;
import fr.toss.magiccrusade.common.player.ServerPlayer;

public class EntityKilledRegister
{
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		ServerPlayer	player;
		IMessage		packet;

		if (event.source.getDamageType().equals("player") && event.source.getEntity() instanceof EntityPlayerMP)
		{
			player = ServerPlayer.from_player_mp((EntityPlayerMP) event.source.getEntity());
			player.add_experience(100);
			packet = new PacketExperience(100);
			Packets.network.sendTo(packet, (EntityPlayerMP)event.source.getEntity());
		}
	}
}

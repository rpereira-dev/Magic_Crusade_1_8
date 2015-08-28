package fr.toss.magiccrusade.common.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import fr.toss.magiccrusade.common.player.ServerPlayer;

public class EntityKilledRegister
{
	@SubscribeEvent
	public void onEntityDeath(LivingDeathEvent event)
	{
		ServerPlayer	player;

		if (event.source.getDamageType().equals("player") && event.source.getEntity() instanceof EntityPlayerMP)
		{
			player = ServerPlayer.from_player_mp((EntityPlayerMP) event.source.getEntity());
			player.add_experience(100);
		}
	}
}

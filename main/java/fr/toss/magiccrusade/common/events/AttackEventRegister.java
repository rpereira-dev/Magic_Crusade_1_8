package fr.toss.magiccrusade.common.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.event.entity.player.AttackEntityEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.ClasseChampion;
import fr.toss.magiccrusade.common.player.ServerPlayer;

public class AttackEventRegister
{
	@SubscribeEvent
    public void onAttackEntity(AttackEntityEvent event)
	{
		if (event.entityPlayer instanceof EntityPlayerMP)
		{
			this.onAttackEntityServerSide(event);
		}
		else
		{
			this.onAttackEntityClientSide(event);
		}
	}

	public void onAttackEntityServerSide(AttackEntityEvent event)
	{
		ServerPlayer	player;
		
		player = ServerPlayer.from_player_mp((EntityPlayerMP)event.entityPlayer);
		if (!event.target.isDead)
		{
			player.get_classe().hit_entity(event.target);
		}
	}
	
	@SideOnly(Side.CLIENT)
    public void onAttackEntityClientSide(AttackEntityEvent event)
	{
		ClientPlayer	player;
		
		player = ClientPlayer.instance();
		if (!event.target.isDead)
		{
			player.get_classe().hit_entity(event.target);
		}
	}
}

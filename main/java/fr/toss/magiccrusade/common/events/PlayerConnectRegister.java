package fr.toss.magiccrusade.common.events;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.common.network.PacketPlayerData;
import fr.toss.magiccrusade.common.network.Packets;
import fr.toss.magiccrusade.common.player.ServerPlayer;

public class PlayerConnectRegister
{
	@SideOnly(Side.SERVER)
	@SubscribeEvent
    public void onPlayerLog(PlayerEvent.PlayerLoggedInEvent event)
    {
		ServerPlayer		player;
		PacketPlayerData	packet;

		player = ServerPlayer.from_player_mp((EntityPlayerMP) event.player);
		packet = new PacketPlayerData(player.get_level(), player.get_experience(), player.get_classe().get_enum_classe().ordinal());
		Packets.network.sendTo(packet, (EntityPlayerMP) event.player);
    }

}

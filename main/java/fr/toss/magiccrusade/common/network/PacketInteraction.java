package fr.toss.magiccrusade.common.network;

import fr.toss.magiccrusade.common.player.ServerPlayer;
import fr.toss.magiccrusade.utils.MagicLogger;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketInteraction implements IMessage
{
	public int data;
	public int id;
	
	public PacketInteraction()
	{
		this(-1, -1);
	}
	
	public PacketInteraction(int p_data, int p_id)
	{
		this.data 	= p_data;
		this.id 	= p_id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.data = buf.readInt();
		this.id = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.data);
		buf.writeInt(this.id);
	}
	
   public static class Handler implements IMessageHandler<PacketInteraction, IMessage> 
   {
		@Override
		public IMessage onMessage(PacketInteraction message, MessageContext ctx)
		{
			ServerPlayer	player;
			
			if (message.id == PacketId.INTERACT_SET_CLASSE.ordinal())
			{
				player = ServerPlayer.from_player_mp(ctx.getServerHandler().playerEntity);
				player.set_classe(message.data);
				
				MagicLogger.log(player.get_classe().toString() + " id : " + message.data);
			}
			return (null);
		}
   }
   
   public enum	PacketId
   {
	   INTERACT_SET_CLASSE
   }
}
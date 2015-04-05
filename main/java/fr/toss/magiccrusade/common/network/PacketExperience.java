package fr.toss.magiccrusade.common.network;

import fr.toss.magiccrusade.client.ClientPlayer;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketExperience implements IMessage
{
	public int experience;
	
	public PacketExperience()
	{
		this(0);
	}
	
	public PacketExperience(int experience)
	{
		this.experience = experience;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.experience = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.experience);
	}
	
   public static class Handler implements IMessageHandler<PacketExperience, IMessage> 
   {
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketExperience message, MessageContext ctx)
		{
			ClientPlayer	player;
			
			player = ClientPlayer.instance();
			player.experience_to_receive += message.experience;
			return (null);
		}
   }
}

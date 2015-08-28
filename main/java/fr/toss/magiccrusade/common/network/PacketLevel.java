package fr.toss.magiccrusade.common.network;

import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.client.gui.ChatColor;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketLevel implements IMessage
{
	public int level;
	
	public PacketLevel()
	{
		this(0);
	}
	
	public PacketLevel(int level)
	{
		this.level = level;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.level = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.level);
	}
	
   public static class Handler implements IMessageHandler<PacketLevel, IMessage> 
   {
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketLevel message, MessageContext ctx)
		{
			ClientPlayer	player;
			
			player = ClientPlayer.instance();
			player.add_chat_message("You level was set to: " + message.level + " (" + (player.get_level() > message.level ? ChatColor.RED + "-" + (player.get_level() - message.level) : ChatColor.AQUA + "+" + (message.level - player.get_level())) + ChatColor.RESET + ")");
			player.set_level(message.level);
			return (null);
		}
   }
}

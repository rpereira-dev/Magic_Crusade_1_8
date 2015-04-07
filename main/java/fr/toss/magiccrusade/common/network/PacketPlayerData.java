package fr.toss.magiccrusade.common.network;

import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.EnumClasse;
import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketPlayerData implements IMessage
{
	public int level;
	public int experience;
	public int classe_id;

	public PacketPlayerData()
	{
		this(1, 0, EnumClasse.FARMER.get_id());
	}
	
	public PacketPlayerData(int p_level, int p_experience, int p_classe_id)
	{
		this.level = p_level;
		this.experience = p_experience;
		this.classe_id = p_classe_id;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.level = buf.readInt();
		this.experience = buf.readInt();
		this.classe_id = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.level);
		buf.writeInt(this.experience);
		buf.writeInt(this.classe_id);
	}
	
   public static class Handler implements IMessageHandler<PacketPlayerData, IMessage> 
   {
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketPlayerData message, MessageContext ctx)
		{
			ClientPlayer	player;
			
			player = ClientPlayer.instance();
			player.set_level(message.level);
			player.set_experience(message.experience);
			player.set_classe(message.classe_id);
			return (null);
		}
   }
}

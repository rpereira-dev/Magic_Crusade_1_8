package fr.toss.magiccrusade.common.network;

import io.netty.buffer.ByteBuf;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.EnumClasse;
import fr.toss.magiccrusade.common.player.ServerPlayer;

public class PacketPlayerData implements IMessage
{
	public char level;
	public int 	experience;
	public char	classe_id;
	public int 	energy;
	public int	experience_to_next_level;

	public PacketPlayerData()
	{
		this.level = 0;
		this.experience = 0;
		this.classe_id = (char) EnumClasse.FARMER.ordinal();
		this.energy = 0;
		this.experience_to_next_level = 0;
	}
	
	public PacketPlayerData(ServerPlayer player)
	{
		this.from_player(player);
	}
	
	public void	from_player(ServerPlayer player)
	{
		this.level = (char) player.get_level();
		this.experience = player.get_experience();
		this.classe_id = (char) player.get_classe().get_enum_classe().ordinal();
		this.energy = player.get_classe().get_energy();
		this.experience_to_next_level = player.calcul_next_level_experience();
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.level = buf.readChar();
		this.experience = buf.readInt();
		this.classe_id = buf.readChar();
		this.energy = buf.readInt();
		this.experience_to_next_level = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeChar(this.level);
		buf.writeInt(this.experience);
		buf.writeChar(this.classe_id);
		buf.writeInt(this.energy);
		buf.writeInt(this.experience_to_next_level);
	}
	
   public static class Handler implements IMessageHandler<PacketPlayerData, IMessage> 
   {
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketPlayerData message, MessageContext ctx)
		{
			ClientPlayer	player;
			
			player = ClientPlayer.instance();
			player.receive_data(message);
			return (null);
		}
   }
}

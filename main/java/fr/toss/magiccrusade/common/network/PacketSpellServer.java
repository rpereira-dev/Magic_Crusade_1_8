package fr.toss.magiccrusade.common.network;

import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.player.ServerPlayer;
import fr.toss.magiccrusade.common.player.Stats;
import fr.toss.magiccrusade.utils.MagicLogger;
import io.netty.buffer.ByteBuf;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class PacketSpellServer implements IMessage
{
	public static final double	ANIMATION_DISTANCE	= 64.0d;
	
	public int	id;
	public int	caster;
	public int	target;
	int			dimension_id;
	
	public PacketSpellServer()
	{
		this(-1, -1, -1);
	}
	
	public PacketSpellServer(int p_id, int p_caster, int p_target)
	{
		this.id = p_id;
		this.caster = p_caster;
		this.target = p_target;
	}
	
	@Override
	public void fromBytes(ByteBuf buf)
	{
		this.id = buf.readInt();
		this.caster = buf.readInt();
		this.target = buf.readInt();
	}
	
	@Override
	public void toBytes(ByteBuf buf)
	{
		buf.writeInt(this.id);
		buf.writeInt(this.caster);
		buf.writeInt(this.target);
	}
	
   public static class Handler implements IMessageHandler<PacketSpellServer, IMessage> 
   {
		@Override
		public IMessage onMessage(PacketSpellServer message, MessageContext ctx)
		{
			ServerPlayer	player;
			ISpell			spell;
			EnumSpell		enum_spell;
			TargetPoint 	point;
			World			world;

			player = ServerPlayer.from_player_mp(ctx.getServerHandler().playerEntity);
			enum_spell = EnumSpell.get_spell_by_id(message.id);
			MagicLogger.log("Server received spell: " + enum_spell.get_spell_name());
			if (player.get_level() < enum_spell.get_spell_level())
			{
				MagicLogger.log("Too low level");
			}
			else if (player.get_classe().get_energy() < enum_spell.get_spell_cost())
			{
				MagicLogger.log("Not enough energy: " + player.get_classe().get_energy() + " / " + enum_spell.get_spell_cost());
			}
			else
			{
				world = ctx.getServerHandler().playerEntity.worldObj;
				try {
					spell = (ISpell)enum_spell.get_spell_class().getConstructor().newInstance();
				} catch (Exception e) {
					e.printStackTrace();
					MagicLogger.log("Error while reading server packet: wrong spell class type");
					return (null);
				}
				spell.do_spell((IMagicEntity)world.getEntityByID(message.caster), world.getEntityByID(message.target), Stats.get_entity_stats(player.get_player()));
				player.get_classe().set_energy(player.get_classe().get_energy() - enum_spell.get_spell_cost());
				point = new TargetPoint(player.get_player().dimension, player.get_player().posX, player.get_player().posY, player.get_player().posZ, ANIMATION_DISTANCE);
				Packets.network.sendToAllAround(new PacketSpellClient(message), point);
				
			}
			return (null);
		}
   }
}

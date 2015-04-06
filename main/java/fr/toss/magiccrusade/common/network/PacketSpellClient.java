package fr.toss.magiccrusade.common.network;

import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.utils.MagicLogger;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PacketSpellClient implements IMessage
{
	public int	id;
	public int	caster;
	public int	target;
	
	public PacketSpellClient()
	{
		this(-1, -1, -1);
	}
	
	public PacketSpellClient(int p_id, int p_caster, int p_target)
	{
		this.id = p_id;
		this.caster = p_caster;
		this.target = p_target;
	}
	
	public PacketSpellClient(PacketSpellServer message)
	{
		this(message.id, message.caster, message.target);
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
	
   public static class Handler implements IMessageHandler<PacketSpellClient, IMessage> 
   {
		@Override
		@SideOnly(Side.CLIENT)
		public IMessage onMessage(PacketSpellClient message, MessageContext ctx)
		{
			World		world;
			ISpell		spell;
			EnumSpell	enum_spell;
			
			enum_spell = EnumSpell.get_spell_by_id(message.id);
			MagicLogger.log("Client received spell: " + enum_spell.get_spell_name());
			try {
				spell = (ISpell)enum_spell.get_spell_class().getConstructor().newInstance();
			} catch (Exception e) {
				e.printStackTrace();
				MagicLogger.log("Error while reading server packet: wrong spell class type");
				return (null);
			}
			world = Minecraft.getMinecraft().theWorld;
			spell.animate(world.getEntityByID(message.caster), world.getEntityByID(message.target));
			return (null);
		}
   }
}

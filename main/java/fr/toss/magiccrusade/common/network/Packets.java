package fr.toss.magiccrusade.common.network;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;
import fr.toss.magiccrusade.common.loader.Loader;

public class Packets extends Loader
{
	public static SimpleNetworkWrapper network;

    public Packets()
    {
		super("Packets");
	}

	public void	on_load()
	{
    	network = NetworkRegistry.INSTANCE.newSimpleChannel("MagicCrusade");
    	
    	network.registerMessage(PacketExperience.Handler.class, PacketExperience.class, Packets.packet_id.PACKET_EXPERIENCE.ordinal(), Side.CLIENT);
    	network.registerMessage(PacketLevel.Handler.class, PacketLevel.class, Packets.packet_id.PACKET_LEVEL.ordinal(), Side.CLIENT);
    	network.registerMessage(PacketSpellClient.Handler.class, PacketSpellClient.class, Packets.packet_id.PACKET_SPELL_CLIENT.ordinal(), Side.CLIENT);
    	
    	
    	network.registerMessage(PacketSpellServer.Handler.class, PacketSpellServer.class, Packets.packet_id.PACKET_SPELL_SERVER.ordinal(), Side.SERVER);
	}
	
	enum packet_id
	{
		PACKET_EXPERIENCE,
		PACKET_LEVEL,
		PACKET_SPELL_CLIENT,
		PACKET_SPELL_SERVER
	}

}

package fr.toss.magiccrusade.common.classes.spell;

import net.minecraft.entity.Entity;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.network.PacketSpellServer;
import fr.toss.magiccrusade.common.network.Packets;

public class Spell implements ISpell
{
	public void send_spell_to_server(ClientPlayer client)
	{
		PacketSpellServer	packet;
		
		packet = new PacketSpellServer(this.get_enum_spell().id, client.get_player().getEntityId(), get_target_id(client));
		Packets.network.sendToServer(packet);
	}

	@Override
	public EnumSpell get_enum_spell() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void animate(Entity caster, Entity target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void do_spell(Entity caster, Entity target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int get_target_id(ClientPlayer client) {
		// TODO Auto-generated method stub
		return 0;
	}
}

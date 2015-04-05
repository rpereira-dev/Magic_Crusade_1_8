package fr.toss.magiccrusade.common.classes.spell;

import net.minecraft.client.resources.I18n;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.EnumClasse;
import fr.toss.magiccrusade.common.classes.IClasse;
import fr.toss.magiccrusade.common.network.PacketSpellServer;
import fr.toss.magiccrusade.common.network.Packets;
import fr.toss.magiccrusade.utils.MagicLogger;

public enum EnumSpell
{	
	CHARGE(0, 0, "charge", 1, 100, EnumClasse.CHAMPION, SpellCharge.class),
	SHOCKWAVE(1, 1, "shockwave", 1, 180, EnumClasse.CHAMPION, SpellShockwave.class),
	IRONSKIN(2, 2, "ironskin", 4, 240, EnumClasse.CHAMPION, SpellIronskin.class),;
	
	
	public int			id;
	public int			classe_id;
	public String		name;
	public int			level;
	public int			cost;
	public EnumClasse	classe;
	public Class<?>		spell_class;

	/**
	 * @param p_id:				unique id of this spell
	 * @param str:				spell name
	 * @param p_classe_id:		spell id of classe
	 * @param p_level:			level required for this spell
	 * @param p_cost			spell cost in energy
	 * @param p_classe			spell classe
	 * @param spell				spell class (for animation and data handlings)
	 */
	EnumSpell(int p_id, int p_classe_id, String str, int p_level, int p_cost, EnumClasse p_classe, Class<?> spell)
	{
		ISpell.spell_list.add(this);
		this.id = p_id;
		this.name = I18n.format("spell." + p_classe.name.toLowerCase() + "." + str.toLowerCase());
		this.level = p_level;
		this.cost = p_cost;
		this.classe = p_classe;
		this.spell_class = spell;
	}

	/** get a spell from it unique id */
	public static EnumSpell	get_spell_by_id(int id)
	{
		for (EnumSpell spell : ISpell.spell_list)
		{
			if (spell.id == id)
				return (spell);
		}
		return (null);
	}
	
	/** get a spell from it classe and classe id */
	public static EnumSpell	get_spell_by_id_and_classe(IClasse classe, int id)
	{
		for (EnumSpell spell : ISpell.spell_list)
		{
			MagicLogger.log(spell.name + " : " + spell.id);
			if (classe.get_enum_classe().id == spell.classe_id && id == spell.id)
				return (spell);
		}
		return (null);
	}
	
	/** send spell to server */
	public static void send_spell_to_server(ClientPlayer client, ISpell spell)
	{
		PacketSpellServer	packet;
		
		packet = new PacketSpellServer(spell.get_enum_spell().id, client.get_player().getEntityId(), spell.get_target_id(client));
		Packets.network.sendToServer(packet);
	}
}

package fr.toss.magiccrusade.common.classes.spell;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.resources.I18n;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.client.gui.ChatColor;
import fr.toss.magiccrusade.common.classes.EnumClasse;
import fr.toss.magiccrusade.common.classes.IClasse;
import fr.toss.magiccrusade.common.classes.spell.champion.SpellCharge;
import fr.toss.magiccrusade.common.classes.spell.champion.SpellEarthShield;
import fr.toss.magiccrusade.common.classes.spell.champion.SpellIronskin;
import fr.toss.magiccrusade.common.classes.spell.champion.SpellShockwave;
import fr.toss.magiccrusade.common.classes.spell.necromancer.SpellRaise;
import fr.toss.magiccrusade.common.network.PacketSpellServer;
import fr.toss.magiccrusade.common.network.Packets;
import fr.toss.magiccrusade.common.player.Stats;
import fr.toss.magiccrusade.utils.MagicLogger;

public enum EnumSpell
{	
	CHARGE(0, 0, "charge", 1, 100, EnumClasse.CHAMPION, SpellCharge.class),
	IRONSKIN(1, 1, "ironskin", 1, 240, EnumClasse.CHAMPION, SpellIronskin.class),
	EARTH_SHIELD(2, 2, "earth_shield", 4, 460, EnumClasse.CHAMPION, SpellEarthShield.class),
	SHOCKWAVE(3, 3, "shockwave", 1, 180, EnumClasse.CHAMPION, SpellShockwave.class),
	RAISE(4, 0, "raise", 1, 0, EnumClasse.NECROMANCER, SpellRaise.class);

	
	private int			id;
	private int			spell_index;
	private String		name;
	private int			level;
	private int			cost;
	private EnumClasse	classe;
	private Class<?>	spell_class;

	/**
	 * @param p_id:				unique id of this spell
	 * 	 * @param p_index			spell index for key bindings
	 * @param str:				spell name
	 * @param p_level:			level required for this spell
	 * @param p_cost			spell cost in energy
	 * @param p_classe			spell classe
	 * @param spell				spell class (for animation and data handlings)
	 */
	EnumSpell(int p_id, int p_index, String str, int p_level, int p_cost, EnumClasse p_classe, Class<?> spell)
	{
		ISpell.spell_list.add(this);
		this.id = p_id;
		this.spell_index = p_index;
		this.name = str;
		this.level = p_level;
		this.cost = p_cost;
		this.classe = p_classe;
		this.spell_class = spell;
	}

	/** return unique spell id */
	public int	get_spell_id()
	{
		return (this.id);
	}
	
	/** return spell name */
	public String	get_spell_name()
	{
		return (I18n.format("spell." + this.classe.get_name().toLowerCase() + "." + this.name.toLowerCase()));
	}
	
	/** return spell minimum level */
	public int	get_spell_level()
	{
		return (this.level);
	}
	
	/** return unique spell id */
	public int	get_spell_cost()
	{
		return (this.cost);
	}
	
	/** return spell corresponding default classe */
	public EnumClasse	get_classe()
	{
		return (this.classe);
	}
	
	/** return spell corresponding class (for animation and packets), implementing ISpell */
	public Class	get_spell_class()
	{
		return (this.spell_class);
	}
	
		/** 
	 * get spell description 
	 * formatting example:
	 * `Deals 5 (+%f)
	 * */
	public static String[]	get_description(EnumSpell spell) throws Exception
	{
		String			str;
		String[] 		tab;
		List<String>	lst;
		String[]		format;
		String[]		words;
		StringBuffer	tmp;
		Stats			stats;
		int				j;

		str = I18n.format(spell.name + "." + "description");
		System.out.println(str);
		lst = new ArrayList<String>();
		words = str.split("\\s+");
		format = str.split("#");
		stats = ClientPlayer.instance().get_stats();
		j = 1;
		tmp = new StringBuffer();
		for (int i = 0; i < words.length; i++)
		{
		    if (tmp.length() > 24)
		    {
		    	lst.add(tmp.toString());
		    	tmp = new StringBuffer();
		    }
			if (words[i].charAt(0) == '@')
			{
				if (words[i].charAt(1) == 'd')
				{
					tmp.append(ChatColor.GOLD + String.valueOf(Float.valueOf(format[j]) * stats.get_strength()) + ChatColor.RESET);
				}
				else if (words[i].charAt(1) == 'D')
				{
					tmp.append(ChatColor.GOLD + String.valueOf(Float.valueOf(format[j])) + ChatColor.RESET);
				}
				else if (words[i].charAt(1) == 'm')
				{
					tmp.append(ChatColor.AQUA + String.valueOf(Float.valueOf(format[j]) * stats.get_magic()) + ChatColor.RESET);
				}
				else if (words[i].charAt(1) == 'M')
				{
					tmp.append(ChatColor.AQUA + String.valueOf(Float.valueOf(format[j])) + ChatColor.RESET);
				}
				else if (words[i].charAt(1) == 'a')
				{
					tmp.append(ChatColor.GREEN + String.valueOf(Float.valueOf(format[j]) * stats.get_stamina()) + ChatColor.RESET);
				}
				else if (words[i].charAt(1) == 'A')
				{
					tmp.append(ChatColor.GREEN + String.valueOf(Float.valueOf(format[j])) + ChatColor.RESET);
				}
				j++;
			}
			else if (words[i].equals("#"))
			{
				break ;
			}
			else
			{
			    tmp.append(words[i]);
			}
			tmp.append(' ');
		}
		if (!tmp.toString().isEmpty())
		{
			lst.add(tmp.toString());
		}
		tab = new String[lst.size()];
		tab = lst.toArray(tab);;
		return (tab);
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
			MagicLogger.log(id + " : " + spell.id);
			if (classe.get_enum_classe().get_id() == spell.classe.get_id() && id == spell.id)
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

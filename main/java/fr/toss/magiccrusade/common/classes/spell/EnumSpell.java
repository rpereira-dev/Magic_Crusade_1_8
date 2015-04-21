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
import fr.toss.magiccrusade.common.classes.spell.champion.SpellTurret;
import fr.toss.magiccrusade.common.classes.spell.mage.SpellSummonSnowcube;
import fr.toss.magiccrusade.common.classes.spell.necromancer.SpellDrain;
import fr.toss.magiccrusade.common.classes.spell.priest.SpellSelfHeal;
import fr.toss.magiccrusade.common.classes.spell.rogue.SpellDodge;
import fr.toss.magiccrusade.common.classes.spell.rogue.SpellInstinct;
import fr.toss.magiccrusade.common.classes.spell.rogue.SpellJump;
import fr.toss.magiccrusade.common.classes.spell.rogue.SpellKnife;
import fr.toss.magiccrusade.common.classes.spell.rogue.SpellPoison;
import fr.toss.magiccrusade.common.classes.spell.rogue.SpellShadowClones;
import fr.toss.magiccrusade.common.classes.spell.rogue.SpellSmokeScreen;
import fr.toss.magiccrusade.common.network.PacketSpellServer;
import fr.toss.magiccrusade.common.network.Packets;
import fr.toss.magiccrusade.common.player.Stats;

public enum EnumSpell
{	
	//Sorts du Champion
	
	CHARGE("charge", 1, 140, EnumClasse.CHAMPION, SpellCharge.class),
	IRONSKIN("ironskin", 1, 250, EnumClasse.CHAMPION, SpellIronskin.class),
	EARTH_SHIELD("earth_shield", 1, 240, EnumClasse.CHAMPION, SpellEarthShield.class),
	SHOCKWAVE("shockwave", 1, 180, EnumClasse.CHAMPION, SpellShockwave.class),
	TURRET("turret", 1, 180, EnumClasse.CHAMPION, SpellTurret.class),
	
	//Sorts du Necromancer
	
	DRAIN("drain", 1, 200, EnumClasse.NECROMANCER, SpellDrain.class),
	
	//Sorts du Mage
	SUMMON_SNOWCUBE("summon_snowcube", 1, 200, EnumClasse.MAGE, SpellSummonSnowcube.class),
	
	
	//Sorts du Priest
	
	SELFHEAL_LOW("SelfHeal_Low", 1, 200, EnumClasse.PRIEST, SpellSelfHeal.class),
	
	//Sorts du Rogue
	
	DODGE("Dodge", 1, 25, EnumClasse.ROGUE, SpellDodge.class),
	SMOKESCREEN("Smokescreen", 1, 75, EnumClasse.ROGUE, SpellSmokeScreen.class),
	INSTINCT("Instinct", 2, 50, EnumClasse.ROGUE, SpellInstinct.class),
	JUMP("jump", 2, 10, EnumClasse.ROGUE, SpellJump.class),
	POISON("poison", 3, 50, EnumClasse.ROGUE, SpellPoison.class),
	KNIFE("knife", 3, 75, EnumClasse.ROGUE, SpellKnife.class),
	SHADOWCLONES("Shadowclones", 10, 100, EnumClasse.ROGUE, SpellShadowClones.class);
	//CHINSTRAP("chinstrap", 10, 100, EnumClasse.ROGUE, SpellSmokeScreen.class);
	
	private String		name;
	private int			level;
	private int			cost;
	private EnumClasse	classe;
	private Class<? extends ISpell>	spell_class;

	/**
	 * @param str:				spell name
	 * @param p_level:			level required for this spell
	 * @param p_cost			spell cost in energy
	 * @param p_classe			spell classe
	 * @param spell				spell class (for animation and data handlings)
	 */
	EnumSpell(String str, int p_level, int p_cost, EnumClasse p_classe, Class<? extends ISpell> spell)
	{
		ISpell.spell_list.add(this);
		this.name = str;
		this.level = p_level;
		this.cost = p_cost;
		this.classe = p_classe;
		this.spell_class = spell;
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
	public Class<? extends ISpell>	get_spell_class()
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

		str = I18n.format("spell." + spell.name + "." + "description");
		System.out.println(str);
		lst = new ArrayList<String>();
		words = str.split("\\s+");
		format = str.split("#");
		stats = ClientPlayer.instance().get_stats();
		j = 1;
		tmp = new StringBuffer();
		for (int i = 0; i < words.length; i++)
		{
		    if (tmp.length() > 22)
		    {
		    	lst.add(tmp.toString());
		    	tmp = new StringBuffer();
		    }
			if (words[i].charAt(0) == '@')
			{
				if (words[i].charAt(1) == 'd')
				{
					str =  String.valueOf(Float.valueOf(format[j]) * stats.get_strength());
					if (str.length() > 4)
						str = str.subSequence(0, 4).toString();
					tmp.append(ChatColor.GOLD + str + ChatColor.RESET);
				}
				else if (words[i].charAt(1) == 'D')
				{
					str =  String.valueOf(Float.valueOf(format[j]) * stats.get_strength());
					if (str.length() > 4)
						str = str.subSequence(0, 4).toString();
					tmp.append(ChatColor.GOLD + String.valueOf(Float.valueOf(format[j])) + ChatColor.RESET);
				}
				else if (words[i].charAt(1) == 'm')
				{
					str =  String.valueOf(Float.valueOf(format[j]) * stats.get_magic());
					if (str.length() > 4)
						str = str.subSequence(0, 4).toString();
					tmp.append(ChatColor.AQUA + str + ChatColor.RESET);
				}
				else if (words[i].charAt(1) == 'M')
				{
					tmp.append(ChatColor.AQUA + String.valueOf(Float.valueOf(format[j])) + ChatColor.RESET);
				}
				else if (words[i].charAt(1) == 'a')
				{
					str =  String.valueOf(Float.valueOf(format[j]) * stats.get_stamina());
					if (str.length() > 4)
						str = str.subSequence(0, 4).toString();
					tmp.append(ChatColor.YELLOW + str + ChatColor.RESET);
				}
				else if (words[i].charAt(1) == 'A')
				{
					tmp.append(ChatColor.YELLOW + String.valueOf(Float.valueOf(format[j])) + ChatColor.RESET);
				}
				else if (words[i].charAt(1) == 'e')
				{
					str =  String.valueOf(Float.valueOf(format[j]) * stats.get_endurance());
					if (str.length() > 4)
						str = str.subSequence(0, 4).toString();
					tmp.append(ChatColor.GREEN + str + ChatColor.RESET);
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
			if (spell.ordinal() == id)
				return (spell);
		}
		return (null);
	}
	
	/** get a spell from it classe and classe id */
	public static EnumSpell	get_spell_by_id_and_classe(IClasse classe, int id)
	{
		if (id >= 0 && id < classe.get_spells().size())
		{
			return (classe.get_spells().get(id));
		}
		return (null);
	}
	
	/** send spell to server */
	public static void send_spell_to_server(int spell_id, int caster_id, int target_id)
	{
		PacketSpellServer	packet;

		packet = new PacketSpellServer(spell_id, caster_id, target_id);
		Packets.network.sendToServer(packet);
	}
}

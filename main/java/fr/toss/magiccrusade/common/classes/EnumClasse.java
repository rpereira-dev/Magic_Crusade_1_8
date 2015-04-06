package fr.toss.magiccrusade.common.classes;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import fr.toss.magiccrusade.client.gui.ChatColor;

public enum EnumClasse
{	
	CHAMPION("Champion", "Rage", ChatColor.RED, 0, ClasseChampion.class),
	ROGUE("Rogue", "Energy", ChatColor.YELLOW, 1, ClasseRogue.class),
	NECROMANCER("Necromancer", "Mana", ChatColor.DARK_PURPLE, 2, ClasseNecromancer.class),
	RANGER("Ranger", "Energy", ChatColor.GOLD, 3, ClasseRanger.class),
	MAGE("Mage", "Mana", ChatColor.BLUE, 4, ClasseMage.class),
	PRIEST("Priest", "Mana", ChatColor.AQUA, 5, ClassePriest.class);
	
	public static final List<IClasse>	list_classes = new ArrayList<IClasse>();

	private String		name;
	private String		energy;
	private ChatColor	color;
	private int			id;
	private Class<?>	classe;
	
	/**
	 * @param p_name:		classe name
	 * @param p_energy		classe energy name type
	 * @param p_color		classe ChatColor
	 * @param p_id			classe unique id
	 */
	EnumClasse(String p_name, String p_energy, ChatColor p_color, int p_id, Class<?> p_classe)
	{
		IClasse.list_class.add(this);
		this.name = p_name;
		this.energy = p_energy;
		this.color = p_color;
		this.id = p_id;
		this.classe = p_classe;
	}
	
	/** return classe name */
	public String		get_name()
	{
		return (this.name);
	}
	
	/** return classe energy type name (Mana, rage, energy) */
	public String		get_energy_name()
	{
		return (this.energy);
	}
	
	/** return classe corresponding chat color */
	public ChatColor	get_chat_color()
	{
		return (this.color);
	}
	
	/** return classe unique id */
	public int			get_id()
	{
		return (this.id);
	}
	
	/** return classe's class file */
	public Class	get_classe()
	{
		return (this.classe);
	}
	
	public static IClasse	load_classe_from_id(int id)
	{
		for (EnumClasse enum_classe : IClasse.list_class)
		{
			if (enum_classe.id == id)
			{
				try {
					return ((IClasse) (enum_classe.get_classe().getConstructor().newInstance()));
				} catch (Exception e) {
					return (null);
				}
			}
		}
		return null;
	}
}

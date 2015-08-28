package fr.toss.magiccrusade.common.classes;

import java.util.ArrayList;
import java.util.List;

import fr.toss.magiccrusade.client.gui.ChatColor;

public enum EnumClasse
{	
	FARMER("Farmer", "Energy", ChatColor.WHITE, ClasseFarmer.class),
	CHAMPION("Champion", "Rage", ChatColor.RED, ClasseChampion.class),
	ROGUE("Rogue", "Energy", ChatColor.YELLOW, ClasseRogue.class),
	NECROMANCER("Necromancer", "Mana", ChatColor.DARK_PURPLE, ClasseNecromancer.class),
	RANGER("Ranger", "Energy", ChatColor.GOLD, ClasseRanger.class),
	MAGE("Mage", "Mana", ChatColor.BLUE, ClasseMage.class),
	PRIEST("Priest", "Mana", ChatColor.AQUA, ClassePriest.class);
	
	public static final List<IClasse>	list_classes = new ArrayList<IClasse>();

	private String		name;
	private String		energy;
	private ChatColor	color;
	private Class<?>	classe;
	
	/**
	 * @param p_name:		classe name
	 * @param p_energy		classe energy name type
	 * @param p_color		classe ChatColor
	 * @param p_id			classe unique id
	 */
	EnumClasse(String p_name, String p_energy, ChatColor p_color, Class<?> p_classe)
	{
		IClasse.list_class.add(this);
		this.name = p_name;
		this.energy = p_energy;
		this.color = p_color;
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

	/** return classe's class file */
	public Class	get_classe()
	{
		return (this.classe);
	}
	
	public static IClasse	load_classe_from_ord(int ord)
	{
		for (EnumClasse enum_classe : IClasse.list_class)
		{
			if (enum_classe.ordinal() == ord)
			{
				try {
					return ((IClasse) (enum_classe.get_classe().getConstructor().newInstance()));
				} catch (Exception e) {
					return (new ClasseFarmer());
				}
			}
		}
		return (new ClasseFarmer());
	}
}

package fr.toss.magiccrusade.common.classes;

import fr.toss.magiccrusade.client.gui.ChatColor;

public enum EnumClasse
{
	CHAMPION("Champion", "Rage", ChatColor.RED, 0),
	ROGUE("Rogue", "Energy", ChatColor.YELLOW, 1),
	NECROMANCER("Necromancer", "Mana", ChatColor.DARK_PURPLE, 2),
	RANGER("Ranger", "Energy", ChatColor.GOLD, 3),
	MAGE("Mage", "Mana", ChatColor.BLUE, 4),
	PRIEST("Priest", "Mana", ChatColor.AQUA, 5);
	
	public String		name;
	public String		energy;
	public ChatColor	color;
	public int			id;
	
	/**
	 * @param p_name:		classe name
	 * @param p_energy		classe energy name type
	 * @param p_color		classe ChatColor
	 * @param p_id			classe unique id
	 */
	EnumClasse(String p_name, String p_energy, ChatColor p_color, int p_id)
	{
		this.name = p_name;
		this.energy = p_energy;
		this.color = p_color;
		this.id = p_id;
	}
}

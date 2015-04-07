package fr.toss.magiccrusade.common.player;

import net.minecraft.item.ItemStack;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.EnumClasse;
import fr.toss.magiccrusade.common.items.ItemMagicArmor;

public class Stats
{
	/** 
	 * Integer package class to deal with stats
	 */
	
	/** magic is increased by clarity. increase spells damages depending on it ratio*/
	private int	magic;
	
	/** endurance increase player health; (+0.1f per endurance points). Means 20 endurance == 1 hearth */
	private int	endurance;
	
	/** Strength increase player damages; (+0.1f per strength points). Means 20 strength == 1 hearth */
	private int	strength;
	
	/** stamina increase critical ratio: (+0.25% per point) */
	private int	stamina;
	
	/** increase magic and mana. 1 clarity = 10 magic. 1 clarity = 50 mana */
	private int	clarity;
	
	/** increase mana regeneration. stat to fix */
	private int	spirit;

	public Stats()
	{
		this.magic = 0;
		this.endurance = 0;
		this.strength = 0;
		this.stamina = 0;
		this.clarity = 0;
		this.spirit = 0;
	}
	
	/**
	 * set player default stat for classe and level
	 * 
	 * @param classe:	player classe (for stats ratios*)
	 * @param level	:	player level (to calcul per level stats)
	 */
	public void	set_default_stats(EnumClasse classe, int level)
	{
		
	}

	/**
	 * Set player stats depending on equipements. Also call `set_default_stats`
	 * @param equipements : player equipements
	 * @see Stats.set_default_stats
	 */
	public void	set_equipement_stats(ItemStack equipements[])
	{
		for (ItemStack item : player.get_player().inventory.armorInventory)
		{
			if (item.getItem() instanceof ItemMagicArmor)
			{
				
			}
		}
	}
	
	/** combine two stats */
	public void	combine(Stats stat)
	{
		this.endurance += stat.endurance;
		this.clarity += stat.clarity;
		this.magic += stat.magic;
		this.strength += stat.strength;
		this.spirit += stat.spirit;
		this.stamina += stat.stamina;
	}

	/** return stats endurance */
	public int	get_magic()
	{
		return (this.magic);
	}
	
	/** return stats endurance */
	public int	get_strength()
	{
		return (this.strength);
	}
	
	/** return stats endurance */
	public int	get_stamina()
	{
		return (this.stamina);
	}
	
	/** return stats endurance */
	public int	get_clarity()
	{
		return (this.clarity);
	}
	
	/** return stats endurance */
	public int	get_spirit()
	{
		return (this.spirit);
	}
	
	/** set stats endurance */
	public void	set_magic(int value)
	{
		this.magic = value;
	}
	
	/** set stats endurance */
	public void	set_strength(int value)
	{
		this.strength = value;
	}
	
	/** set stats endurance */
	public void	set_stamina(int value)
	{
		this.stamina = value;
	}
	
	/** set stats endurance */
	public void	set_clarity(int value)
	{
		this.clarity = value;
	}
	
	/** set stats endurance */
	public void	set_spirit(int value)
	{
		this.spirit = value;
	}
}

package fr.toss.magiccrusade.common.items;

import fr.toss.magiccrusade.common.player.Stats;

/** items giving player stats if equipped in armor slots or hand*/
public interface IItemStatable
{
	/** get item stats*/
	public Stats	get_stats();
}

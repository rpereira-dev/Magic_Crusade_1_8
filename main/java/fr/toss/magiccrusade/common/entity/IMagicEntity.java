package fr.toss.magiccrusade.common.entity;

import fr.toss.magiccrusade.common.classes.IClasse;

public interface IMagicEntity
{
	/** return player classe */
	public IClasse	get_classe();
	

	/** return player current level */
	public int		get_level();
}

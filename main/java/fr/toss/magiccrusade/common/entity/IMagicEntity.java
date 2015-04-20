package fr.toss.magiccrusade.common.entity;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.world.World;
import fr.toss.magiccrusade.common.classes.IClasse;
import fr.toss.magiccrusade.common.player.Stats;

public interface IMagicEntity
{
	/** return player classe */
	public IClasse	get_classe();
	

	/** return player current level */
	public int		get_level();

	/** return the real minecraft entity */
	public EntityLivingBase getEntity();

	/** return entity stats */
	public Stats getStats();

	/** get entity world */
	public World	getWorld();
}

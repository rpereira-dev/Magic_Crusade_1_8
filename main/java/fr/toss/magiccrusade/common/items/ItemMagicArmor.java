package fr.toss.magiccrusade.common.items;

import net.minecraft.item.ItemArmor;
import fr.toss.magiccrusade.common.player.Stats;

public class ItemMagicArmor extends ItemArmor
{
	private Stats	stats;
	
	public ItemMagicArmor(ArmorMaterial material, int renderIndex, int armorType)
	{
		super(material, renderIndex, armorType);
	}
	
	/** get item stats*/
	public Stats	get_stats()
	{
		return (this.stats);
	}
}

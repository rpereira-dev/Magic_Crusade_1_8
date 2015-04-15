package fr.toss.magiccrusade.common.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.common.player.Stats;

public class ItemMagicArmor extends ItemArmor implements IItemMagic
{
	private Stats	stats;
	
	public ItemMagicArmor(ArmorMaterial material, int renderIndex, int armorType)
	{
		super(material, renderIndex, armorType);
		this.stats = new Stats();
	}
	
	
	 /**
    * allows items to add custom lines of information to the mouseover description
    */
   @SideOnly(Side.CLIENT)
   public void addInformation(ItemStack item, EntityPlayer player, List list, boolean bool)
   {
	   list.addAll(this.stats.to_string_list());
   }
   
	
	/** get item stats*/
	public Stats	get_stats()
	{
		return (this.stats);
	}

	@Override
	public void setCreativeTabsContainer()
	{
		this.setCreativeTab(CreativeTabsLoader.tabArmor);
	}
}

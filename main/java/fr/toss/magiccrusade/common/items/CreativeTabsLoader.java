package fr.toss.magiccrusade.common.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.Main;
import fr.toss.magiccrusade.common.loader.Loader;

public class CreativeTabsLoader extends Loader
{	
	public CreativeTabsLoader()
	{
		super("Creative Tabs");
	}

	public static CreativeTabs tabBlock;
	public static CreativeTabs tabItemUtil;
	public static CreativeTabs tabTools;
	public static CreativeTabs tabArmor;
	public static CreativeTabs tabArmorLoot;
	public static CreativeTabs tabEpic;

	public void on_load()
	{		
		tabEpic = new CreativeTabs("item_epic")
		{
		    @Override
		    @SideOnly(Side.CLIENT)
		    public Item getTabIconItem() 
		    {
		        return (ItemLoader.items.get("shadowmourne"));
		    }
		 };
	}

	public void	load_items()
	{
		IItemMagic	item;
		
		for (String key : ItemLoader.items.keySet())
		{
			item = (IItemMagic)ItemLoader.items.get(key);
			item.setCreativeTabsContainer();
		}
	}
}

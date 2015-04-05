package fr.toss.magiccrusade.client.render;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.common.items.ItemLoader;
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
		        return (ItemLoader.FROSTMOURNE);
		    }
		 };
	}

	public void	load_items()
	{
		ItemLoader.FROSTMOURNE.setCreativeTab(CreativeTabsLoader.tabEpic);
		ItemLoader.SULFURAS.setCreativeTab(CreativeTabsLoader.tabEpic);
	}
}

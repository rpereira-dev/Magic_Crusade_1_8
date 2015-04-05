package fr.toss.magiccrusade.common.items;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import fr.toss.magiccrusade.Main;
import fr.toss.magiccrusade.common.items.epic.ItemFrostmourne;
import fr.toss.magiccrusade.common.items.epic.ItemSulfuras;
import fr.toss.magiccrusade.common.loader.Loader;

public class ItemLoader extends Loader
{
	public static Item		SULFURAS;
	public static Item		FROSTMOURNE;

	
	public ItemLoader()
	{
		super("Items");
	}
	
	@Override
	public void	on_load()
	{
        FROSTMOURNE	= new ItemFrostmourne();
        SULFURAS	= new ItemSulfuras();

        GameRegistry.registerItem(FROSTMOURNE,	"frostmourne",	Main.MODID);
        GameRegistry.registerItem(SULFURAS, 	"sulfuras",		Main.MODID);
	}
}

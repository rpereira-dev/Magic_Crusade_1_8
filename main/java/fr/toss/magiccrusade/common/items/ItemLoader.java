package fr.toss.magiccrusade.common.items;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import fr.toss.magiccrusade.Main;
import fr.toss.magiccrusade.common.items.weapon.ItemFrostmourne;
import fr.toss.magiccrusade.common.items.weapon.ItemShadowmourne;
import fr.toss.magiccrusade.common.items.weapon.ItemSulfuras;
import fr.toss.magiccrusade.common.loader.Loader;
import fr.toss.magiccrusade.utils.MagicLogger;


/**
 * @author Romain
 *	To add an item, dont forget to:
 *
 *		- create your item and make it implements IItemMagic
 *		- register it in ItemLoader
 *		- create item rendering json files in ressources
 *		- dont forget textures en lang files
 *		- register items in the hash map via ItemLoader.registerItem(Item.class, "my item name")
 *
 */
public class ItemLoader extends Loader
{
	public static final Map<String, Item> items = new HashMap<String, Item>(256);	//hash map, size in memory is 256 * size of an Item
	
	
	public ItemLoader()
	{
		super("Items");
	}

	@Override
	public void	on_load()
	{
		registerItem(ItemFrostmourne.class, "frostmourne");
        registerItem(ItemSulfuras.class, "sulfuras");
        registerItem(ItemShadowmourne.class, "shadowmourne");
        registerAll();
	}
	
	private static void	registerItem(Class<? extends Item> clazz, String id)
	{
        try
        {
			items.put(id, clazz.getConstructor().newInstance());
		}
        catch (Exception e)
		{
        	MagicLogger.log("ERROR: COULDNT REGISTER ITEM: " + id + " - " + clazz);
			e.printStackTrace();
		}
	}
	
	private static void	registerAll()
	{
		for (String key : ItemLoader.items.keySet())
		{
	        GameRegistry.registerItem(ItemLoader.items.get(key), key, Main.MODID);
		}
	}
}

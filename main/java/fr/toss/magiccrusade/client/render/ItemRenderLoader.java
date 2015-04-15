package fr.toss.magiccrusade.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import fr.toss.magiccrusade.Main;
import fr.toss.magiccrusade.common.items.ItemLoader;
import fr.toss.magiccrusade.common.loader.Loader;

public class ItemRenderLoader extends Loader
{
	private static ItemModelMesher	mesher;
	
	public ItemRenderLoader()
	{
		super("Item renderer");
	}
	
	public void	on_load()
	{
		mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		for (String key : ItemLoader.items.keySet())
		{
			register_item_renderer(ItemLoader.items.get(key));
		}
	}
	
	public static void	register_item_renderer(Item item)
	{
		mesher.register(item, 0, new ModelResourceLocation(Main.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
	}

}

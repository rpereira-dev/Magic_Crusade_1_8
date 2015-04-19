package fr.toss.magiccrusade.client.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemModelMesher;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import fr.toss.magiccrusade.Main;
import fr.toss.magiccrusade.client.entity.model.ModelBelier;
import fr.toss.magiccrusade.client.entity.model.ModelOrc;
import fr.toss.magiccrusade.client.entity.model.ModelSnowCube;
import fr.toss.magiccrusade.client.entity.render.RenderBelier;
import fr.toss.magiccrusade.client.entity.render.RenderOrc;
import fr.toss.magiccrusade.client.entity.render.RenderSnowCube;
import fr.toss.magiccrusade.common.entity.EntityBelier;
import fr.toss.magiccrusade.common.entity.EntityOrc;
import fr.toss.magiccrusade.common.entity.EntitySnowCube;
import fr.toss.magiccrusade.common.items.ItemLoader;
import fr.toss.magiccrusade.common.loader.Loader;

public class RenderLoader extends Loader
{
	private static ItemModelMesher	mesher;
	private static RenderManager	renderer;

	public RenderLoader()
	{
		super("Renderer");
	}

	@Override
	public void	on_load()
	{
		mesher = Minecraft.getMinecraft().getRenderItem().getItemModelMesher();
		renderer = Minecraft.getMinecraft().getRenderManager();
		register_item_renderer();
		register_entity_renderer();
	}
	
	private void register_entity_renderer()
	{
        RenderingRegistry.registerEntityRenderingHandler(EntityOrc.class, new RenderOrc(renderer, new ModelOrc(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(EntityBelier.class, new RenderBelier(renderer, new ModelBelier(), 0.7F));
        RenderingRegistry.registerEntityRenderingHandler(EntitySnowCube.class, new RenderSnowCube(renderer, new ModelSnowCube(), 0.7F));
	}

	private void register_item_renderer()
	{
		Item	item;
		
		for (String key : ItemLoader.items.keySet())
		{
			item = ItemLoader.items.get(key);
			mesher.register(item, 0, new ModelResourceLocation(Main.MODID + ":" + item.getUnlocalizedName().substring(5), "inventory"));
		}		
	}
}

package fr.toss.magiccrusade.client;

import fr.toss.magiccrusade.client.event.EventClientLoader;
import fr.toss.magiccrusade.client.keys.KeyBindingsLoader;
import fr.toss.magiccrusade.client.loader.ClientLoader;
import fr.toss.magiccrusade.client.render.ItemRenderLoader;
import fr.toss.magiccrusade.common.CommonProxy;
import fr.toss.magiccrusade.common.events.EventLoader;

public class ClientProxy extends CommonProxy
{
	public ClientLoader			client_loader;
	public KeyBindingsLoader	key_binding_loader;
	public ItemRenderLoader		items_renderer_loader;

	public ClientProxy()
	{
		super();
		this.client_loader			= new ClientLoader();
		this.key_binding_loader		= new KeyBindingsLoader();
		this.items_renderer_loader	= new ItemRenderLoader();
		this.event_loader			= new EventClientLoader();
	}
	
	@Override
	public void load()
	{
		super.load();
		this.client_loader.load();
		this.key_binding_loader.load();
		this.items_renderer_loader.load();
	}
}

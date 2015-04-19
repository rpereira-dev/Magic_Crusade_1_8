package fr.toss.magiccrusade.common;

import fr.toss.magiccrusade.common.achievement.AchievementLoader;
import fr.toss.magiccrusade.common.blocks.BlockLoader;
import fr.toss.magiccrusade.common.entity.EntityLoader;
import fr.toss.magiccrusade.common.events.EventLoader;
import fr.toss.magiccrusade.common.items.CreativeTabsLoader;
import fr.toss.magiccrusade.common.items.ItemLoader;
import fr.toss.magiccrusade.common.player.PlayerLoader;

public class CommonProxy
{
	public BlockLoader			blocks_loader;
	public EntityLoader			entity_loader;
	public ItemLoader			items_loader;
	public AchievementLoader	achivement_loader;
	public PlayerLoader			player_loader;
	public CreativeTabsLoader	creative_tabs_loader;
	public EventLoader			event_loader;

	
	public CommonProxy()
	{
		this.blocks_loader			= new BlockLoader();
		this.items_loader 			= new ItemLoader();
		this.achivement_loader		= new AchievementLoader();
		this.player_loader			= new PlayerLoader();
		this.creative_tabs_loader	= new CreativeTabsLoader();
		this.event_loader			= new EventLoader();
		this.entity_loader			= new EntityLoader();
	}
	
	public void load()
	{
		this.event_loader.load();
		this.entity_loader.load();
		this.blocks_loader.load();
		this.items_loader.load();
		this.achivement_loader.load();
		this.player_loader.load();
		this.creative_tabs_loader.load();
		this.creative_tabs_loader.load_items();
	}
}

package fr.toss.magiccrusade.common.player;

import api.player.server.ServerPlayerAPI;
import fr.toss.magiccrusade.common.loader.Loader;

public class PlayerLoader extends Loader
{
	public PlayerLoader()
	{
		super("Player");
	}

	@Override
	public void	on_load()
	{
		ServerPlayerAPI.register("Magic Crusade", ServerPlayer.class);
	}
}

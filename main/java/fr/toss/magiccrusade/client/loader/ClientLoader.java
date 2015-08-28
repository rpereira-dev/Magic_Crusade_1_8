package fr.toss.magiccrusade.client.loader;

import net.minecraft.client.Minecraft;
import api.player.client.ClientPlayerAPI;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.client.gui.GuiIngameOverlay;
import fr.toss.magiccrusade.client.keys.KeyInputHandler;
import fr.toss.magiccrusade.common.loader.Loader;

public class ClientLoader extends Loader
{
	public ClientLoader()
	{
		super("Client");
	}
	
	@Override
	public void	on_load()
	{		
		ClientPlayerAPI.register("Magic Crusade", ClientPlayer.class);
	}
}

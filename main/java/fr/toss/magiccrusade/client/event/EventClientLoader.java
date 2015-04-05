package fr.toss.magiccrusade.client.event;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import fr.toss.magiccrusade.client.gui.GuiIngameOverlay;
import fr.toss.magiccrusade.client.keys.KeyInputHandler;
import fr.toss.magiccrusade.common.events.EventLoader;
import fr.toss.magiccrusade.utils.MagicLogger;

public class EventClientLoader extends EventLoader
{
	public void	on_load()
	{
		super.on_load();
		this.register_event(new GuiIngameOverlay(Minecraft.getMinecraft()));
		this.register_event(new KeyInputHandler());
	}
}

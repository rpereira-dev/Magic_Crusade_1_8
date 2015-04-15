package fr.toss.magiccrusade.common.events;

import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import fr.toss.magiccrusade.client.gui.GuiIngameOverlay;
import fr.toss.magiccrusade.client.keys.KeyInputHandler;
import fr.toss.magiccrusade.common.loader.Loader;

public class EventLoader extends Loader
{
	public EventLoader()
	{
		super("Events");
	}

	public void	on_load()
	{
		this.register_event(new EntityKilledRegister());
		this.register_event(new AttackEventRegister());
		this.register_event(new PlayerConnectRegister());
	}
	
	public void	register_event(Object event)
	{
		FMLCommonHandler.instance().bus().register(event);
		MinecraftForge.EVENT_BUS.register(event);
	}
	
}

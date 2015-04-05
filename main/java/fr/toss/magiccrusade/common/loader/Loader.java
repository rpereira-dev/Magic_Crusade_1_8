package fr.toss.magiccrusade.common.loader;

import java.util.logging.Logger;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.FMLCommonHandler;
import fr.toss.magiccrusade.utils.MagicLogger;

public class Loader
{
	private String name;
	
	public Loader(String str)
	{
		this.name = str;
	}
	
	public void	load()
	{
		this.pre_load();
		this.on_load();
		this.post_load();
	}
	
	public void	pre_load()
	{
		MagicLogger.log("Loading:\t" + this.name);
	}
	
	public void	on_load()
	{

	}
	
	public void	post_load()
	{
		MagicLogger.log("Done:\t" + this.name);
	}
	
	public void	log(String msg)
	{
		Logger.getGlobal().fine("\t\t" + this.name + ": " + msg);
	}
}

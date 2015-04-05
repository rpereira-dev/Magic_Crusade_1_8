package fr.toss.magiccrusade;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;
import fr.toss.magiccrusade.common.CommandLoader;
import fr.toss.magiccrusade.common.CommonProxy;
import fr.toss.magiccrusade.common.network.Packets;

@Mod(modid = Main.MODID, version = Main.VERSION)
public class Main
{
	@SidedProxy(clientSide="fr.toss.magiccrusade.client.ClientProxy", serverSide="fr.toss.magiccrusade.common.CommonProxy")
	public static CommonProxy		proxy;
	public static Packets			packets_loader;
	
	
    public static final String MODID = "magiccrusade";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
    	Main.proxy.load();
    }
    
    @EventHandler
    public void 		preInit(FMLPreInitializationEvent event)
    {
    	packets_loader = new Packets();
    	packets_loader.load();
    }
    
	@EventHandler
    public void 		serverLoad(FMLServerStartingEvent event)
    {
    	CommandLoader.load(event);
    }
}

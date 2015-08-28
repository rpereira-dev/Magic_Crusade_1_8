package fr.toss.magiccrusade.client.gui;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.client.config.GuiUtils;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.client.ClientPlayer;

public class GuiIngameOverlay
{
	/** Msg liste to print on screen */
	private static final List<GuiString>	list_msg = new ArrayList<GuiString>();

	public static final ResourceLocation CHARGE_BARRE = new ResourceLocation("magiccrusade:textures/gui/ChargeGUI.png");
	
	private Minecraft 		mc;
	private ClientPlayer	player;
	
	public GuiIngameOverlay(Minecraft m)
	{
		this.mc = m;
	}
	
	@SubscribeEvent
	@SideOnly(Side.CLIENT)
	public void onGuiIngameRender(RenderGameOverlayEvent.Chat event)
	{
		int	x;
		int	y;
		
		this.player	= ClientPlayer.instance();
    	x = 2;
    	y = 6;
		if (this.mc.inGameHasFocus && !this.mc.gameSettings.showDebugInfo)
		{
	        this.mc.mcProfiler.startSection("render_health_bar");
	        this.render_health_bar(x + 20, y);
	        this.mc.mcProfiler.endSection();
	        
	        this.mc.mcProfiler.startSection("render_energy_bar");
	    	this.mc.getTextureManager().bindTexture(CHARGE_BARRE);
	        this.player.get_classe().render_energy_bar(mc, x + 20, y);
	        this.mc.mcProfiler.endSection();
	        
	        this.mc.mcProfiler.startSection("render_experience_bar");
	        this.render_experience_bar(x + 22, y);
	        this.mc.mcProfiler.endSection();

	        this.mc.mcProfiler.startSection("render_player");
	        this.render_player_icon(x, y);
	        this.mc.mcProfiler.endSection();
	        
	        this.mc.mcProfiler.startSection("render_messages");
	        this.render_messages(event.resolution);
	        this.mc.mcProfiler.endSection();

		}
	}
	
	private void	render_messages(ScaledResolution resolution)
	{
		GuiString	string;
		int			x;
		int			y;
		
		if (list_msg.size() > 0)
		{
			string = list_msg.get(0);
			x = (resolution.getScaledWidth() - this.mc.fontRendererObj.getStringWidth(string.str)) / 2;
			y = 10;
			this.mc.fontRendererObj.drawStringWithShadow(string.str, x, y, string.color);
			string.timer--;
			if (string.timer <= 0)
			{
				list_msg.remove(0);
			}
		}
	}

	private void	render_experience_bar(int x, int y)
	{
		String	str;
		
    	this.mc.getTextureManager().bindTexture(CHARGE_BARRE);
    	GuiUtils.drawTexturedModalRect(x - 17, y + 30, 130, 0, 81, 5, 0);
    	GuiUtils.drawTexturedModalRect(x - 17, y + 30, 130, 5, (int) (81.0f / this.player.get_total_experience() * this.player.get_experience()), 5, 0);
    	str = "Level: " + this.player.get_level();
    	this.mc.fontRendererObj.drawStringWithShadow(str, x + 28 - this.mc.fontRendererObj.getStringWidth(str) / 2, y + 28,  0xffffffff);
	}

	private void	render_player_icon(int x, int y)
	{
		GuiInventory.drawEntityOnScreen(x + 8, y + 26, 14, 0, 0, this.player.get_player());
	}
    
	private void	render_health_bar(int x, int y)
	{
		String	health;

    	this.mc.getTextureManager().bindTexture(CHARGE_BARRE);
    	GuiUtils.drawTexturedModalRect(x, y, 0, 0, 65, 13, 0);
    	GuiUtils.drawTexturedModalRect(x, y, 65, 0, (int) (65 / this.player.getMaxHealth() * this.player.getHealth()), 13, 0);
		health = this.player.getRoundedHealth() + "/" + this.player.getMaxHealth();
        this.mc.fontRendererObj.drawStringWithShadow(health, 34 + x - this.mc.fontRendererObj.getStringWidth(health) / 2, y + 2,  0xffffffff);
	}
	
	/** add a string to the screen if it wasnt already */
	public static void	add_message(String str, int timer, int color)
	{
		if (list_msg.size() > 0 && list_msg.get(0).str.equals(str))
			return ;
		list_msg.add(new GuiString(str, timer, color));
	}
}
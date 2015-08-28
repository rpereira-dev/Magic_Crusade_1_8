package fr.toss.magiccrusade.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.client.config.GuiUtils;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.client.keys.KeyBindingsLoader;
import fr.toss.magiccrusade.common.network.PacketInteraction;
import fr.toss.magiccrusade.common.network.Packets;
import fr.toss.magiccrusade.utils.MagicLogger;

public class GuiClasseInformation extends GuiScreen {
	
	private String description[];
	private String advices[];
	private String classe;
	private int x;
	private int y;
	private int color1;
	private int color2;
	private int classe_id;

	public GuiClasseInformation(String classe_name, String p_description[], String p_advices[], int px, int py, int p_color1, int p_color2, int classe_id)
	{
		super();
		this.description = p_description;
		this.advices = p_advices;
		this.classe = classe_name;
		this.x = px;
		this.y = py;
		this.color1 = p_color1;
		this.color2 = p_color2;
		this.classe_id = classe_id;
	}

	@Override
	public void initGui()
	{
		this.buttonList.add(new GuiButton(42, this.width / 2 - 40, 34, 80, 20, I18n.format("button.selection")));
		this.buttonList.add(new GuiButton(43, 10, this.height - 30, 120, 20, ChatColor.RESET + I18n.format("button.back_selection")));
	}


    /**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int x, int y, float f)
    {
    	int i;
    	
    	i = 0;
        this.drawGradientRect(0, 0, this.width, this.height, this.color1, this.color2);
    	this.drawCenteredString(this.fontRendererObj, this.classe + ChatColor.RESET, this.width / 2, 14, Integer.MAX_VALUE);
	    GuiInventory.drawEntityOnScreen(this.width / 2, this.height / 2, 28, 0, 0, this.mc.thePlayer);

    	for (String str : this.description)
    	{
    		this.drawCenteredString(this.fontRendererObj, str + ChatColor.RESET, this.width / 2, this.height / 2 + (i + 1) * 16, Integer.MAX_VALUE);
    		i++;
    	}
    	
		this.drawString(this.fontRendererObj, ChatColor.UNDERLINE + "Advices:", this.width / 4 * 3 + 10, 28, Integer.MAX_VALUE);
    	i = 0;
		for (String str : this.advices)
    	{
			this.drawString(this.fontRendererObj, str + ChatColor.RESET, this.width / 4 * 3 + 10, 46 + i * 14, Integer.MAX_VALUE);
    		i++;
    	}
		
    	this.mc.renderEngine.bindTexture(GuiSelectClass.CLASSES);
    	GuiUtils.drawTexturedModalRect(20, 14, this.x * 52, this.y * 52, 52, 52, 0);

    	super.drawScreen(x, y, f);
    }
    
    /**
     * Called from the main game loop to update the screen.
     */
    @Override
    public void updateScreen() 
    {
    	super.updateScreen();
    }
    
    @Override
    public boolean doesGuiPauseGame()
    {
        return false;
    }
    
    @Override
    protected void actionPerformed(GuiButton b)
    {
    	ClientPlayer		player;
		GuiSelectClass		gui;
		PacketInteraction	packet;
		
    	if (b.id == 42)
    	{
    		player = ClientPlayer.instance();
    		packet = new PacketInteraction(this.classe_id, PacketInteraction.PacketId.INTERACT_SET_CLASSE.ordinal());
    		Packets.network.sendToServer(packet);
    		player.add_chat_message("You can press " + ChatColor.RED + KeyBindingsLoader.get_key_char(KeyBindingsLoader.KEY_STATS) + ChatColor.RESET + " on your keyboard to see more informations about " + classe + ChatColor.RESET + " and it spells.");
    		player.add_chat_message("Don't forget to configure your controls!");
    		player.set_classe(this.classe_id);
			MagicLogger.log(player.get_classe().toString() + " id : " + this.classe_id);
    		this.mc.displayGuiScreen(null);
    	}
    	else if (b.id == 43)
    	{    		
    		gui = new GuiSelectClass();
    		this.mc.displayGuiScreen(gui);
    	}
    }
}

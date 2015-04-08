package fr.toss.magiccrusade.client.gui;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.EnumClasse;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.Spell;
import fr.toss.magiccrusade.common.player.Stats;
import fr.toss.magiccrusade.utils.MagicLogger;

public class GuiStats extends GuiScreen
{
	public static final ResourceLocation GUI	= new ResourceLocation("magiccrusade:textures/gui/gui_stats.png");
	
	private Stats			stats;
	private SpellSlot		slots[];

	private ClientPlayer	player;	
	
	@Override
	public void initGui()
	{
		super.initGui();
		this.player = ClientPlayer.instance();
		this.stats = Stats.get_default_stats(this.player);
		this.stats.combine(Stats.get_equipement_stats(this.player.get_player().inventory.armorInventory, this.player.get_player().inventory.getCurrentItem()));
		this.slots = new SpellSlot[this.player.get_classe().get_spells().size()];
		this.init_slots();
	}

    private void init_slots()
    {
    	int	x;
    	int	y;
    	int	i;
    	
    	x = this.width / 2 + 78;
    	y = 47;
    	i = 0;
		for (EnumSpell spell : this.player.get_classe().get_spells())
		{
			this.slots[i] = new SpellSlot(spell, x, y);
			i++;
			y += 22;
		}
	}

	/**
     * Draws the screen and all the components in it.
     */
    @Override
    public void drawScreen(int mouseX, int mouseY, float partial)
    {
    	super.drawScreen(mouseX, mouseY, partial);
    	this.drawBackground();
        this.drawTitles();
        this.drawSlots();
        this.checkSpellHover(mouseX, mouseY);
    }
    
    private void checkSpellHover(int x, int y)
    {
    	int	x_len;
    	int	y_len;
    	int	x_tmp;
    	int	x_pos;
    	int	y_pos;
    	
    	for (SpellSlot slot : this.slots)
    	{
    		if (x >= slot.x && x <= slot.x + 16 && y >= slot.y && y <= slot.y + 16)
    		{
    			x_len = 0;
    			y_len = 0;
    			for (String str : slot.description)
    			{
    				x_tmp = this.mc.fontRendererObj.getStringWidth(str);
    				if (x_len < x_tmp)
    				{
    					x_len = x_tmp;
    				}
    				y_len += 10;
    			}
        		x_len += 16;
        		y_len += 24;
        		this.drawRect(0, 0, this.width, this.height, Integer.MIN_VALUE);
    			this.drawRect(x - x_len / 2, y - 20, x + x_len / 2, y + y_len, Integer.MAX_VALUE);
    			x_pos = x;
    			y_pos = y;
				this.drawCenteredString(this.mc.fontRendererObj, ChatColor.UNDERLINE + slot.spell.get_spell_name() + ChatColor.RESET, x, y - 14, 0x00eaee);
    			for (String str : slot.description)
    			{
    				this.drawCenteredString(this.mc.fontRendererObj, str, x_pos, y_pos, 0xffffff);
    				y_pos += 10;
    			}
    			y_pos += 8;

    			if (slot.spell.get_spell_level() > this.player.get_level())
    			{
					this.drawCenteredString(this.mc.fontRendererObj, ChatColor.RED + "Requiered level: " + slot.spell.get_spell_level() + ChatColor.RESET, x_pos, y_pos, 0);
    			}
    			else
    			{
					this.drawCenteredString(this.mc.fontRendererObj, ChatColor.GREEN + "Requiered level: " + slot.spell.get_spell_level() + ChatColor.RESET, x_pos, y_pos, 0);
    			}
    		}
    	}
	}
    
    private void drawSlots()
    {
    	int texel_x;

    	texel_x = 0;
        this.mc.getTextureManager().bindTexture(this.player.get_classe().get_texture());
    	for (SpellSlot slot : this.slots)
    	{
	        GuiUtils.drawTexturedModalRect(slot.x, slot.y, texel_x, 0, 16, 16, 0);
	        texel_x += 16;
    	}
	}

	private void drawTitles()
    {
		String		str;
		EnumClasse	classe;
		
		classe = this.player.get_classe().get_enum_classe();
        str = classe.get_chat_color() + classe.get_name() + ChatColor.RESET + " " + this.player.get_username();
        this.drawCenteredString(this.fontRendererObj, str, this.width / 2, 14, Integer.MAX_VALUE);
        str = ChatColor.YELLOW + "" + ChatColor.UNDERLINE  + "Level: " + this.player.get_level() + ChatColor.RESET;
        this.drawCenteredString(this.fontRendererObj, str, this.width / 2 - 26, 50, Integer.MAX_VALUE);
	}

	private void	drawBackground()
    {
        this.drawDefaultBackground();
        this.mc.getTextureManager().bindTexture(GUI);
        GuiUtils.drawTexturedModalRect(this.width / 2 - 128, 0, 0, 0, 256, 256, 0);
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
        return (false);
    }
}

class LineStat
{
	public String	hover_text;
	public String	line;
	public float	value;
	public int		x;
	public int		y;
	
	public LineStat(String a, float b, String name)
	{
		this.value = b;
		this.line = a;
		this.hover_text = (b > 0) ? (ChatColor.GREEN + "+ ") : (b < 0) ? (ChatColor.RED + "- ") : ("");
		this.hover_text += b + " " + name;
	}
}

class SpellSlot
{
	public EnumSpell	spell;
	public String		description[];
	public int 			x;
	public int 			y;
	
	public SpellSlot(EnumSpell p_enum, int p_x, int p_y)
	{
		this.spell = p_enum;
		this.x = p_x;
		this.y = p_y;
		this.description = EnumSpell.get_description(p_enum);
	}
}

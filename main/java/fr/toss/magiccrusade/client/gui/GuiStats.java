package fr.toss.magiccrusade.client.gui;

import java.util.List;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.inventory.GuiInventory;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.EnumClasse;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.player.Stats;

public class GuiStats extends GuiScreen
{
	public static final ResourceLocation GUI	= new ResourceLocation("magiccrusade:textures/gui/gui_stats.png");
	
	private Stats			stats;
	private Stats			stats_default;
	private SpellSlot		slots[];
	private StatsLine		stats_line[];

	private ClientPlayer	player;	
	
	@Override
	public void initGui()
	{
		super.initGui();
		this.player = ClientPlayer.instance();
		this.stats = Stats.get_player_stats();
		this.stats_default = Stats.get_default_stats(this.player);
		this.init_slots();
		this.init_stats();
	}

    private void init_stats()
    {
    	int	x;
    	int	y;
    	
		this.stats_line = new StatsLine[7];    	
    	x = this.width / 2 - 86;
    	y =  this.height / 2 - 28;
    	this.stats_line[0] = new StatsLine(Stats.get_endurance_name(), Stats.get_endurance_desc(), this.stats.get_endurance(), this.stats_default.get_endurance(), x, y);
    	this.stats_line[1] = new StatsLine(Stats.get_strength_name(), Stats.get_strength_desc(), this.stats.get_strength(), this.stats_default.get_strength(), x, y + 16);
    	this.stats_line[2] = new StatsLine(Stats.get_stamina_name(), Stats.get_stamina_desc(), this.stats.get_stamina(), this.stats_default.get_stamina(), x, y + 32);
    	this.stats_line[3] = new StatsLine(Stats.get_clarity_name(), Stats.get_clarity_desc(), this.stats.get_clarity(), this.stats_default.get_clarity(), x, y + 48);
    	this.stats_line[4] = new StatsLine(Stats.get_spirit_name(), Stats.get_spirit_desc(), this.stats.get_spirit(), this.stats_default.get_spirit(), x, y + 64);
    	this.stats_line[5] = new StatsLine(Stats.get_magic_name(), Stats.get_magic_desc(), this.stats.get_magic(), this.stats_default.get_magic(), x, y + 80);
    	this.stats_line[6] = new StatsLine(Stats.get_mana_name(), Stats.get_mana_desc(), this.stats.get_mana(), this.stats_default.get_mana(), x, y + 96);
    }

	private void init_slots()
    {
    	int	x;
    	int	y;
    	int	i;
    	
		this.slots = new SpellSlot[this.player.get_classe().get_spells().size()];
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
        this.drawStats();
        this.checkSpellHover(mouseX, mouseY);
        this.checkStatsHover(mouseX, mouseY);
    	this.renderPlayer(mouseX, mouseY);
    }

	private void renderPlayer(int mouseX, int mouseY)
	{
		EnumClasse	classe;
		String	str;
		List 	lst;
		
		mouseX += this.width / 2;
		mouseY -= this.height / 2;
		
		
		classe = this.player.get_classe().get_enum_classe();
        str = classe.get_chat_color() + classe.get_name() + ChatColor.RESET + " " + this.player.get_username();
        this.drawCenteredString(this.fontRendererObj, str, 64, 50, 0xffffff);
		
        str = this.player.get_player().getHealth() + " / " + this.player.get_player().getMaxHealth();
        this.drawCenteredString(this.fontRendererObj, str, 64, 64, 0xffffff);
	        
       	GuiInventory.drawEntityOnScreen(64, this.height / 2 + 86, 64, -mouseX * 16, -mouseY, this.player.get_player());

		lst = this.player.get_player().worldObj.getEntitiesWithinAABBExcludingEntity(this.player.get_player(), this.player.get_player().getEntityBoundingBox().expand(64, 64, 64));
		for (Object obj : lst)
		{
			if (obj instanceof EntityTameable)
			{
				if (((EntityTameable)obj).getOwnerEntity() == this.player.get_player())
				{
			        str = ((EntityTameable)obj).getName();
			        this.drawCenteredString(this.fontRendererObj, str, this.width - 64, 50, 0xffffff);

			        str = ((EntityTameable)obj).getHealth() + " / " + ((EntityTameable)obj).getMaxHealth();
			        this.drawCenteredString(this.fontRendererObj, str, this.width - 64, 64, 0xffffff);
			        
					GuiInventory.drawEntityOnScreen(this.width - 64, this.height / 2 + 64, 64, mouseX / 2, -mouseY, (EntityLivingBase) obj);
					break ;
				}
			}
		}
	}

	private void checkStatsHover(int x, int y)
    {
		int	x_len;
		int	x_len_details;
		
    	for (StatsLine line : this.stats_line)
    	{
    		x_len = this.mc.fontRendererObj.getStringWidth(line.stat);
    		x_len_details = this.mc.fontRendererObj.getStringWidth(line.details);

    		if (x >= line.x && x <= line.x + x_len && y >= line.y - 4 && y <= line.y + 12)
    		{
        		this.drawRect(0, 0, this.width, this.height, Integer.MIN_VALUE);
    			this.drawRect(x - 	x_len_details / 2 - 16, y - 18, x + 	x_len_details / 2 + 16, y, Integer.MAX_VALUE);
				this.drawCenteredString(this.mc.fontRendererObj, ChatColor.UNDERLINE + line.details + ChatColor.RESET, x, y - 14, 0xffffff);
    		}
    	}
    }
    
    private void drawStats()
    {
    	for (StatsLine s : this.stats_line)
    	{
    		this.drawString(this.mc.fontRendererObj, s.stat, s.x, s.y, 0xffffffff);
    	}
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

        str = ChatColor.AQUA + "" + ChatColor.UNDERLINE  + "Level: " + this.player.get_level() + ChatColor.RESET;
        this.drawCenteredString(this.fontRendererObj, str, this.width / 2 - 26, 50, 0xffffff);
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
		try
		{
			this.description = EnumSpell.get_description(p_enum);
		}
		catch (Exception e)
		{
			this.description = new String[1];
			this.description[0] = "no description available :(";
		}
	}
}


class StatsLine
{
	String	stat;
	String	details;
	String	str;
	int 	x;
	int 	y;
	
	public StatsLine(String p_stat, String p_details, float p_value, float p_default, int p_x, int p_y)
	{
		String	value;
		
		str = null;
		value = Float.toString(p_value);
		if (value.length() > 5)
		{
			value = value.subSequence(0, 5).toString();
		}
		if (p_value > p_default)
		{
			str = "(+ " + (p_value - p_default);
			this.stat = ChatColor.GREEN.toString();
		}
		else if (p_value < p_default)
		{
			str = "(- " + (p_default - p_value);
			this.stat = ChatColor.RED.toString();
		}
		else
		{
			stat = ChatColor.WHITE.toString();
		}
		this.stat += p_stat +  " : " + value;
		if (str != null)
		{
			this.stat += str;
		}
		this.stat += ChatColor.RESET;
		this.details = p_details;
		this.x = p_x;
		this.y = p_y;
	}
}

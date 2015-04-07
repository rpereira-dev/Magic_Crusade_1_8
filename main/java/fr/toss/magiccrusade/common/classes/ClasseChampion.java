package fr.toss.magiccrusade.common.classes;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.client.config.GuiUtils;
import fr.toss.magiccrusade.common.player.Stats;

public class ClasseChampion implements IClasse
{	
	/** amount of rage */
	public int	rage;
	
	/** last hit timer */
	private static final int MILLIS_TO_LOOSE_RAGE = 2000;
	private long	last_hit;
	
	public ClasseChampion()
	{
		this.rage = 0;
		this.last_hit = 0;
	}
	
	@Override
	public EnumClasse	get_enum_classe()
	{
		return (EnumClasse.CHAMPION);
	}

	@Override
	public int get_default_max_energy()
	{
		return (1000);
	}

	@Override
	public int get_energy()
	{
		return (this.rage);
	}

	@Override
	public void	set_energy(int value)
	{
		this.rage = value;
		this.rage = (this.rage >= this.get_default_max_energy()) ? this.get_default_max_energy() : this.rage;
	}
	
	@Override
	public void update()
	{
		if (System.currentTimeMillis() - this.last_hit >= MILLIS_TO_LOOSE_RAGE && this.rage > 0)
		{
			this.rage--;
		}
	}
	
	@Override
	public void	hit_entity(Entity target)
	{
		this.rage = this.rage + 1000;
		this.rage = (this.rage >= this.get_default_max_energy()) ? this.get_default_max_energy() : this.rage;
		this.last_hit = System.currentTimeMillis();
	}

	@Override
	public void render_energy_bar(Minecraft minecraft, int x, int y)
	{
		String	str;
		
		str = (int) this.rage + "/" + (int)this.get_default_max_energy();
    	GuiUtils.drawTexturedModalRect(x, y + 15, 0, 14, 65, 13, 0);
    	GuiUtils.drawTexturedModalRect(x, y + 15, 65, 70, (int) (65.0f / this.get_default_max_energy() * this.rage), 13, 0);
    	minecraft.fontRendererObj.drawStringWithShadow(str, x + 32 - minecraft.fontRendererObj.getStringWidth(str) / 2, y + 17, 0xffffffff);
	}

	@Override
	public void write_to_nbt(NBTTagCompound nbt)
	{
		 nbt.setInteger("rage", this.rage); 
		 nbt.setLong("last_hit", this.last_hit); 
	}

	@Override
	public void read_from_nbt(NBTTagCompound nbt)
	{
		 this.rage		= nbt.getInteger("rage");
		 this.last_hit	= nbt.getInteger("last_hit");
	}

	@Override
	public Stats get_default_stats()
	{
		Stats	stats;
		
		stats = new Stats();
		stats.set_endurance(50);
		stats.set_strength(20);
		stats.set_stamina(0);
		stats.set_spirit(20);
		stats.set_clarity(25);
		stats.set_magic(0);
		return (stats);
	}

	@Override
	public Stats get_stats_per_lvl()
	{
		Stats	stats;

		stats = new Stats();
		stats.set_endurance(5);
		stats.set_strength(2);
		stats.set_stamina(1);
		stats.set_spirit(1);
		stats.set_clarity(1);
		stats.set_magic(0);
		return (stats);
	}

}

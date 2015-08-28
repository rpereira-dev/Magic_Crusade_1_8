package fr.toss.magiccrusade.common.classes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.player.Stats;

public class ClasseRage implements IClasse
{	
	/** amount of rage */
	public int	rage;
	
	/** last hit timer */
	private static final int MILLIS_TO_LOOSE_RAGE = 2000;
	
	private long			last_hit;
	protected List<EnumSpell> spells;
	
	public ClasseRage()
	{
		this.rage = 0;
		this.last_hit = 0;
		this.spells = new ArrayList<EnumSpell>();
	}
	
	@Override
	public EnumClasse	get_enum_classe()
	{
		return (null);
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
		this.rage += target.worldObj.rand.nextInt(100) + 40;
		//this.rage = 1000;
		this.rage = (this.rage >= this.get_default_max_energy()) ? this.get_default_max_energy() : this.rage;
		this.last_hit = System.currentTimeMillis();
	}

	@Override
	public void render_energy_bar(Minecraft minecraft, int x, int y) {}

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
		return (new Stats());
	}

	@Override
	public Stats get_stats_per_lvl()
	{
		return (new Stats());
	}

	@Override
	public ResourceLocation get_texture()
	{
		return (null);
	}

	@Override
	public List<EnumSpell> get_spells() 
	{
		return (this.spells);
	}

	@Override
	public void addSpell(EnumSpell spell)
	{
		this.spells.add(spell);
	}
}

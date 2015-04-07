package fr.toss.magiccrusade.common.classes;

import fr.toss.magiccrusade.common.player.Stats;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.client.config.GuiUtils;

public class ClasseRogue implements IClasse
{
	private int	energy;

	@Override
	public EnumClasse get_enum_classe()
	{
		return (EnumClasse.ROGUE);
	}

	@Override
	public int get_default_max_energy()
	{
		return (100);
	}

	@Override
	public int get_energy()
	{
		return (this.energy);
	}

	@Override
	public void set_energy(int value)
	{
		this.energy = value;
	}

	@Override
	public void update()
	{
		if (this.energy < this.get_default_max_energy())
		{
			this.energy++;
		}
	}

	@Override
	public void render_energy_bar(Minecraft minecraft, int x, int y)
	{
		String	str;
		
		str = (int) this.energy + "/" + (int)this.get_default_max_energy();
    	GuiUtils.drawTexturedModalRect(x, y + 15, 0, 14, 65, 13, 0);
    	GuiUtils.drawTexturedModalRect(x, y + 15, 130, 84, (int) (65.0f / this.get_default_max_energy() * this.energy), 13, 0);

    	minecraft.fontRendererObj.drawStringWithShadow(str, x + 32 - minecraft.fontRendererObj.getStringWidth(str) / 2, y + 17, 0xffffffff);
	}

	@Override
	public void hit_entity(Entity target) {}

	@Override
	public void write_to_nbt(NBTTagCompound nbt)
	{
		 nbt.setInteger("energy", this.energy); 
	}

	@Override
	public void read_from_nbt(NBTTagCompound nbt)
	{
		 this.energy = nbt.getInteger("energy");
	}
	
	@Override
	public Stats get_default_stats()
	{
		Stats	stats;
		
		stats = new Stats();
		stats.set_endurance(36);
		stats.set_strength(16);
		stats.set_stamina(20);
		stats.set_spirit(10);
		stats.set_clarity(10);
		stats.set_magic(5);
		return (stats);
	}

	@Override
	public Stats get_stats_per_lvl()
	{
		Stats	stats;

		stats = new Stats();
		stats.set_endurance(2);
		stats.set_strength(4);
		stats.set_stamina(8);
		stats.set_spirit(0);
		stats.set_clarity(0);
		stats.set_magic(0);
		return (stats);
	}
}

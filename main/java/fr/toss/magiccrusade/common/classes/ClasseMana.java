package fr.toss.magiccrusade.common.classes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.player.Stats;

public class ClasseMana implements IClasse
{
	protected List<EnumSpell> spells;
	protected int	mana;

	public ClasseMana()
	{
		this.spells = new ArrayList<EnumSpell>();
	}

	@Override
	public int get_default_max_energy()
	{
		return (2000);
	}

	public void	addSpell(EnumSpell spell)
	{
		this.spells.add(spell);
	}
	
	@Override
	public int get_energy()
	{
		return (this.mana);
	}

	@Override
	public void set_energy(int value)
	{
		this.mana = value;
	}

	@Override
	public void update()
	{
		if (this.mana < this.get_default_max_energy())
		{
			this.mana += 4;
			if (this.mana > this.get_default_max_energy())
			{
				this.mana = this.get_default_max_energy();
			}
		}
	}

	@Override
	public void hit_entity(Entity target) {}

	@Override
	public void write_to_nbt(NBTTagCompound nbt)
	{
		nbt.setInteger("mana", this.mana);
	}

	@Override
	public void read_from_nbt(NBTTagCompound nbt)
	{
		this.mana = nbt.getInteger("mana");
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
	public EnumClasse get_enum_classe()
	{
		return (null);
	}

	@Override
	public void render_energy_bar(Minecraft minecraft, int x, int y) {}
}

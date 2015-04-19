package fr.toss.magiccrusade.common.classes;

import java.util.ArrayList;
import java.util.List;

import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.player.Stats;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;

public class ClasseMage implements IClasse
{
	private List<EnumSpell> spells;
	private int mana;

	public ClasseMage()
	{
		this.spells = new ArrayList<EnumSpell>();
		this.spells.add(EnumSpell.FIREBALL_LOW);
		this.spells.add(EnumSpell.SUMMON_SNOWCUBE);
	}

	@Override
	public EnumClasse get_enum_classe()
	{
		return (EnumClasse.MAGE);
	}

	@Override
	public int get_default_max_energy() {
		// TODO Auto-generated method stub
		return (2000);
	}

	@Override
	public int get_energy() {
		return (this.mana);
	}

	@Override
	public void set_energy(int value) {
		this.mana = value;
		
	}

	@Override
	public void update() {
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
	public void render_energy_bar(Minecraft minecraft, int x, int y)
	{
		String	str;
		
		str = (int) this.mana + "/" + (int)this.get_default_max_energy();
    	GuiUtils.drawTexturedModalRect(x, y + 15, 0, 0, 65, 13, 0);
    	GuiUtils.drawTexturedModalRect(x, y + 15, 0, 98, (int) (65.0f / this.get_default_max_energy() * this.mana), 13, 0);
    	minecraft.fontRendererObj.drawStringWithShadow(str, x + 32 - minecraft.fontRendererObj.getStringWidth(str) / 2, y + 17, 0xffffffff);
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
		Stats	stats;
		
		stats = new Stats();
		stats.set_endurance(30);
		stats.set_strength(5);
		stats.set_stamina(0);
		stats.set_spirit(50);
		stats.set_clarity(25);
		stats.set_magic(0);
		stats.set_mana(0);
		return (stats);
	}

	@Override
	public Stats get_stats_per_lvl()
	{
		Stats	stats;

		stats = new Stats();
		stats.set_endurance(3);
		stats.set_strength(1);
		stats.set_stamina(0);
		stats.set_spirit(2);
		stats.set_clarity(4);
		stats.set_magic(2);
		stats.set_mana(50);
		return (stats);
	}
	

	@Override
	public ResourceLocation get_texture()
	{
		return (IClasse.MAGE_RES);
	}

	@Override
	public List<EnumSpell> get_spells()
	{
		return (this.spells);
	}

}

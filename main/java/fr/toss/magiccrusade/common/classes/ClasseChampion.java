package fr.toss.magiccrusade.common.classes;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.player.Stats;

public class ClasseChampion extends ClasseRage
{	
	public ClasseChampion()
	{
		super();
		this.addSpell(EnumSpell.CHARGE);
		this.addSpell(EnumSpell.IRONSKIN);
		this.addSpell(EnumSpell.EARTH_SHIELD);
		this.addSpell(EnumSpell.SHOCKWAVE);
		this.addSpell(EnumSpell.TURRET);
	}
	
	@Override
	public EnumClasse	get_enum_classe()
	{
		return (EnumClasse.CHAMPION);
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
	public Stats get_default_stats()
	{
		Stats	stats;
		
		stats = new Stats();
		stats.set_endurance(50);
		stats.set_strength(20);
		stats.set_stamina(8);
		stats.set_spirit(0);
		stats.set_clarity(10);
		stats.set_magic(0);
		return (stats);
	}

	@Override
	public Stats get_stats_per_lvl()
	{
		Stats	stats;

		stats = new Stats();
		stats.set_endurance(20);
		stats.set_strength(2);
		stats.set_stamina(1);
		stats.set_spirit(0.5f);
		stats.set_clarity(0.7f);
		stats.set_magic(0);
		return (stats);
	}

	@Override
	public ResourceLocation get_texture()
	{
		return (IClasse.CHAMPION_RES);
	}
}

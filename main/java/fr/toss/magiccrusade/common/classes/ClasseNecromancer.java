package fr.toss.magiccrusade.common.classes;

import java.util.ArrayList;
import java.util.List;

import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.player.Stats;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public class ClasseNecromancer implements IClasse
{
	private List<EnumSpell> spells;

	public ClasseNecromancer()
	{
		this.spells = new ArrayList<EnumSpell>();
	}
	
	@Override
	public EnumClasse get_enum_classe()
	{
		return (EnumClasse.NECROMANCER);
	}

	@Override
	public int get_default_max_energy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int get_energy() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void set_energy(int value) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render_energy_bar(Minecraft minecraft, int x, int y) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hit_entity(Entity target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write_to_nbt(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void read_from_nbt(NBTTagCompound nbt) {
		// TODO Auto-generated method stub
		
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
		stats.set_endurance(5);
		stats.set_strength(1);
		stats.set_stamina(0);
		stats.set_spirit(1);
		stats.set_clarity(2);
		stats.set_magic(1);
		stats.set_mana(50);
		return (stats);
	}

	@Override
	public ResourceLocation get_texture()
	{
		return (IClasse.NECROMANCER_RES);
	}

	@Override
	public List<EnumSpell> get_spells()
	{
		return (this.spells);
	}
}

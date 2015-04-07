package fr.toss.magiccrusade.common.classes;

import fr.toss.magiccrusade.common.player.Stats;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;

public class ClasseRanger implements IClasse
{

	@Override
	public EnumClasse get_enum_classe() {
		// TODO Auto-generated method stub
		return null;
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
		stats.set_endurance(40);
		stats.set_strength(20);
		stats.set_stamina(10);
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
		stats.set_strength(2);
		stats.set_stamina(5);
		stats.set_spirit(1);
		stats.set_clarity(1);
		stats.set_magic(1);
		return (stats);
	}
}

package fr.toss.magiccrusade.common.classes;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;

public interface IClasse
{
	public static final ResourceLocation PRIEST = new ResourceLocation("magiccrusade:textures/spells/priest.png");
	public static final ResourceLocation MAGE = new ResourceLocation("magiccrusade:textures/spells/mage.png");
	public static final ResourceLocation CHAMPION = new ResourceLocation("magiccrusade:textures/spells/champion.png");
	public static final ResourceLocation DRAGON_SLAYER = new ResourceLocation("magiccrusade:textures/spells/dragon_slayer.png");
	public static final ResourceLocation NECROMANCER = new ResourceLocation("magiccrusade:textures/spells/necromancer.png");
	public static final ResourceLocation ROGUE = new ResourceLocation("magiccrusade:textures/spells/rogue.png");
	
	/** list of every classes */
	public static final List<EnumClasse> list_class = new ArrayList<EnumClasse>();
	
	
	/** Return class enum */
	public EnumClasse	get_enum_classe();

	/** return default max energy */
	public int			get_default_max_energy();
	
	/** return current amount of energy */
	public int			get_energy();
	
	/** return current amount of energy */
	public void			set_energy(int value);
	
	/** update the class */
	public void			update();	

	/** render energy bar, texture is already binded */
	public void			render_energy_bar(Minecraft minecraft, int x, int y);

	/** When the player hit and entity */
	public void			hit_entity(Entity target);

	/** save classe to nbt */
	public void write_to_nbt(NBTTagCompound nbt);

	/** load classe from nbt */
	public void read_from_nbt(NBTTagCompound nbt);
	
}

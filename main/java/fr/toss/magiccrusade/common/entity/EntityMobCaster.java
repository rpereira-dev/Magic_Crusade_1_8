package fr.toss.magiccrusade.common.entity;

import java.lang.reflect.InvocationTargetException;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import fr.toss.magiccrusade.common.classes.IClasse;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.network.PacketSpellClient;
import fr.toss.magiccrusade.common.network.PacketSpellServer;
import fr.toss.magiccrusade.common.network.Packets;
import fr.toss.magiccrusade.common.player.Stats;

public class EntityMobCaster extends EntityMob implements IMagicEntity
{
	protected IClasse	classe;
	protected Stats		stats;
	
	public EntityMobCaster(World worldIn)
	{
		super(worldIn);
		this.stats = new Stats();
	}
	
	@Override
	public void	onUpdate()
	{
		super.onUpdate();
	    this.classe.update();
	}

	protected void launch_spells_randomly()
	{
    	Entity		target;
    	EnumSpell	spell;
		int	id;
		
		id = this.rand.nextInt(this.classe.get_spells().size());
		spell = this.classe.get_spells().get(id);
		try
		{
			this.tryLaunchSpell(this, spell);
		} catch (Exception e) {}		
	}
	

	/** try to launch the spell for the given entity 
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws SpellException */
	protected void tryLaunchSpell(IMagicEntity entity, EnumSpell enumspell) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, SpellException
	{
		Entity	target;
    	ISpell	spell;
    	TargetPoint	point;
    	int	total;
    	int	cost;
    	int	target_id;
    	
    	total = entity.get_classe().get_energy();
    	cost = enumspell.get_spell_cost();
    	if (total < cost)
    	{
    		return ;
    	}
		spell = (ISpell) enumspell.get_spell_class().getConstructor().newInstance();
		target_id = spell.get_target_id(entity.getEntity());
		target = entity.getEntity().worldObj.getEntityByID(target_id);
		spell.do_spell(entity, target, entity.getStats());
		point = new TargetPoint(entity.getEntity().dimension, entity.getEntity().posX, entity.getEntity().posY, entity.getEntity().posZ, PacketSpellServer.ANIMATION_DISTANCE);
		Packets.network.sendToAllAround(new PacketSpellClient(enumspell.ordinal(), entity.getEntity().getEntityId(), target_id), point);
		entity.get_classe().set_energy(total - cost);
	}
	
	protected void addSpell(EnumSpell spell)
	{
		this.classe.addSpell(spell);
	}

	@Override
	public EntityLivingBase getEntity()
	{
		return (this);
	}

	@Override
	public Stats getStats()
	{
		return (this.stats);
	}

	@Override
	public IClasse get_classe()
	{
		return (this.classe);
	}

	@Override
	public int get_level()
	{
		return (30);
	}

	@Override
	public World getWorld()
	{
		return (this.worldObj);
	}
}

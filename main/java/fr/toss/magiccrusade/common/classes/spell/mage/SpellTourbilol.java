package fr.toss.magiccrusade.common.classes.spell.mage;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellTourbilol implements ISpell
{

	@Override
	public EnumSpell get_enum_spell()
	{
		return (null);
	}

	@Override
	public void animate(Entity caster, Entity target) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void do_spell(Entity caster, Entity target, Stats stats) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int	get_target_id(EntityLivingBase caster) throws SpellException
	{
		return 0;
	}

}

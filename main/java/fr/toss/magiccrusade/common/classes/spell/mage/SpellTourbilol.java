package fr.toss.magiccrusade.common.classes.spell.mage;

import net.minecraft.entity.Entity;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;

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
	public void do_spell(Entity caster, Entity target) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int get_target_id(ClientPlayer client) {
		// TODO Auto-generated method stub
		return 0;
	}

}

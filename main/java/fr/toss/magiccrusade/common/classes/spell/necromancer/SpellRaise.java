package fr.toss.magiccrusade.common.classes.spell.necromancer;

import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityZombie;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;

public class SpellRaise implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.RAISE);
	}

	@Override
	public void animate(Entity caster, Entity target)
	{
		
	}

	@Override
	public void do_spell(Entity caster, Entity target)
	{
		EntityZombie	zombie;
		
		zombie = new EntityZombie(caster.worldObj);
		zombie.setPosition(caster.posX, caster.posY + 16, caster.posZ);
		caster.worldObj.spawnEntityInWorld(zombie);
	}

	@Override
	public int get_target_id(ClientPlayer client)
	{
		return (-1);
	}

}

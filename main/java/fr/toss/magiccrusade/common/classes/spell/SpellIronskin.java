package fr.toss.magiccrusade.common.classes.spell;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import fr.toss.magiccrusade.client.ClientPlayer;

public class SpellIronskin implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.IRONSKIN);
	}

	@Override
	public void animate(Entity caster, Entity target) {}

	@Override
	public void do_spell(Entity caster, Entity target)
	{
		EntityLivingBase	entity;
		
		entity = (EntityLivingBase)caster;
		entity.addPotionEffect(new PotionEffect(Potion.resistance.id, 15 * 60, 1));
	}

	@Override
	public int get_target_id(ClientPlayer client)
	{
		return (-1);
	}

}

package fr.toss.magiccrusade.common.classes.spell.priest;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellSelfHeal implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.SELFHEAL_LOW);
	}

	@Override
	public void animate(IMagicEntity caster, Entity target)
	{
		
	}

	@Override
	public void do_spell(IMagicEntity caster, Entity target, Stats stat)
	{
		caster.getEntity().addPotionEffect(new PotionEffect(Potion.heal.id, 1, 1));
	}

	@Override
	public int	get_target_id(EntityLivingBase caster) throws SpellException
	{
		return (-1);
	}

}

package fr.toss.magiccrusade.common.classes.spell.champion;

import com.sun.org.apache.xml.internal.security.utils.I18n;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;

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
		entity.addPotionEffect(new PotionEffect(Potion.resistance.id, 30 * 20, 1));
	}

	@Override
	public int get_target_id(ClientPlayer client)
	{
		return (-1);
	}

}

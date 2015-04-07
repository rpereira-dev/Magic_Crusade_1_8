package fr.toss.magiccrusade.common.classes.spell.rogue;

import net.minecraft.entity.Entity;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;

public class SpellPasive implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (null);
	}

	@Override
	public void animate(Entity caster, Entity target) {}

	@Override
	public void do_spell(Entity caster, Entity target) {}

	@Override
	public int get_target_id(ClientPlayer client)
	{
		return 0;
	}
}

package fr.toss.magiccrusade.common.classes.spell;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import fr.toss.magiccrusade.client.ClientPlayer;

/**
 * @author Romain
 *
 *
 * The implementation MUST HAVE a default constructor, else way, client and server packet handler wont be able to catch spell
 *
 */
public interface ISpell
{
	
	/** list of every spells */
	public static final List<EnumSpell> spell_list = new ArrayList<EnumSpell>();
	
	
	/** get spell enum data */
	public EnumSpell	get_enum_spell();
	
	/** play spell animation */
	public void			animate(Entity caster, Entity target);
	
	/** do spell, only executed server side */
	public void			do_spell(Entity caster, Entity target);
	
	/** return entity target id */
	public int			get_target_id(ClientPlayer client);



}

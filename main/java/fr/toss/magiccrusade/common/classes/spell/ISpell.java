package fr.toss.magiccrusade.common.classes.spell;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.player.Stats;

/**
 * @author Romain
 *
 *
 * The implementation MUST HAVE a default constructor, else way, client and server packet handler wont be able to catch spell
 * 
 * How spells works:
 * 	:: input are catched inside `KeyInputHandler`.
 * 			- It check CLIENT side that the player player has the requiered level, and that the player has enough energy for the spell
 * 			- if it has, it try to instanciate the new spell, and to get the target id
 * 			- if an error occured (SpellException), it is shown in caster chat
 * 			- else, if everything went alright; it send the player id (as caster_id), the target id, and the spell id to the server
 * 			- then the player mana, rage or energy is decreased
 * 			The packet sent is a : `PacketSpellServer`
 * 
 * When a server catched a spell, it rechecks that the caster entity has the requiered level and energy (to prevent from hacks).
 * If everything is alright, the server send the spell to every nearby clients, see `PacketSpellServer` and `PacketSpellServer.ANIMATION_DISTANCE`
 * The packet send is `PacketSpellClient`. taking caster id, target id, and spell id
 * When a client received this packet, it rebuild the spell from it ID, and do the animation at the correct coordinates
 *
 */
public interface ISpell
{
	
	/** list of every spells */
	public static final List<EnumSpell> spell_list = new ArrayList<EnumSpell>();
	
	
	/** get spell enum data */
	public EnumSpell	get_enum_spell();
	
	/** play spell animation */
	public void			animate(IMagicEntity caster, Entity target);
	
	/** do spell, only executed server side */
	public void			do_spell(IMagicEntity caster, Entity target, Stats stat);
	
	/** return entity target id , should -1 if theres no target, else way, if a random id is sent, unhandle cases may append
	 * @throws SpellException */
	public int			get_target_id(EntityLivingBase caster) throws SpellException;



}

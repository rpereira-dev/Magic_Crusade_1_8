package fr.toss.magiccrusade.client.keys;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.client.ClientPlayer;
import fr.toss.magiccrusade.client.gui.GuiIngameOverlay;
import fr.toss.magiccrusade.client.gui.GuiSelectClass;
import fr.toss.magiccrusade.client.gui.GuiStats;
import fr.toss.magiccrusade.client.gui.GuiString;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.player.Stats;
import fr.toss.magiccrusade.utils.MagicLogger;

public class KeyInputHandler {

    @SubscribeEvent
    @SideOnly(Side.CLIENT)
    public void onKeyInput(InputEvent.KeyInputEvent event)
    {
    	ClientPlayer	player;
    	EnumSpell		enum_spell;
    	ISpell			spell;
    	
    	player = ClientPlayer.instance();
    	if(KeyBindingsLoader.KEY_SELECT_CLASSE.isPressed())
    	{
        	Minecraft.getMinecraft().displayGuiScreen(new GuiSelectClass());
    	}
        else if(KeyBindingsLoader.KEY_STATS.isPressed())
        {
        	Minecraft.getMinecraft().displayGuiScreen(new GuiStats());
        }
        else if(KeyBindingsLoader.KEY_GROUP.isPressed())
        {
        	
        }
        else if (player.get_classe() != null)
        {
        	for (int i = 0; i < KeyBindingsLoader.KEY_SPELLS.length; i++)
        	{
        		if (KeyBindingsLoader.KEY_SPELLS[i].isPressed())
        		{
        			enum_spell = EnumSpell.get_spell_by_id_and_classe(player.get_classe(), i);
                	if (enum_spell == null)
                		return ;
                	else if (enum_spell.get_spell_level() > player.get_level())
                	{
                		GuiIngameOverlay.add_message(I18n.format("required.level"), GuiString.TIMER_SHORT, GuiString.RED);
                	}
                	else if (enum_spell.get_spell_cost() <= player.get_classe().get_energy())
                	{
	        			try {
	        				spell = (ISpell)enum_spell.get_spell_class().getConstructor().newInstance();
	        			} catch (Exception e) {
	        				e.printStackTrace();
	        				MagicLogger.log("Error while sending spell packet: wrong spell class type");
	        				return ;
	        			}
	        			player.get_classe().set_energy(player.get_classe().get_energy() - enum_spell.get_spell_cost());
	        			EnumSpell.send_spell_to_server(player, spell);
                	}
                	else
                	{
                		GuiIngameOverlay.add_message(I18n.format("not.enough") + " " + player.get_classe().get_enum_classe().get_energy_name(), GuiString.TIMER_SHORT, GuiString.RED_SMOOTH);
                	}
        		}
        	}
        }
    }

}
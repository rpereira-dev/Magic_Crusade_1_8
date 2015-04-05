package fr.toss.magiccrusade.client.keys;

import net.minecraft.client.settings.KeyBinding;
import net.minecraftforge.fml.client.registry.ClientRegistry;

import org.lwjgl.input.Keyboard;

import fr.toss.magiccrusade.common.loader.Loader;

public class KeyBindingsLoader extends Loader
{
	public static KeyBinding KEY_GENERATE;
    public static KeyBinding KEY_STATS;
    public static KeyBinding KEY_GROUP;
    public static KeyBinding KEY_SELECT_CLASSE;
    public static KeyBinding KEY_SPELLS[];

    public KeyBindingsLoader()
    {
		super("Key bindings");
	}
    
    @Override
    public void on_load()
    {
    	
    	KEY_GENERATE = new KeyBinding("key.GENERATE", Keyboard.KEY_O, "key.categories.magiccrusade");
        ClientRegistry.registerKeyBinding(KEY_GENERATE);
        
        KEY_STATS = new KeyBinding("key.stats", Keyboard.KEY_P, "key.categories.magiccrusade");
        ClientRegistry.registerKeyBinding(KEY_STATS);
      
    	KEY_GROUP = new KeyBinding("key.group", Keyboard.KEY_G, "key.categories.magiccrusade");
        ClientRegistry.registerKeyBinding(KEY_GROUP);
        
        KEY_SELECT_CLASSE = new KeyBinding("key.select_classe", Keyboard.KEY_I, "key.categories.magiccrusade");
        ClientRegistry.registerKeyBinding(KEY_SELECT_CLASSE);
        
        KEY_SPELLS = new KeyBinding[8];
        for (int i = 0; i < 8; i++)
        {
        	KEY_SPELLS[i] = new KeyBinding("key.spells_" + i, Keyboard.KEY_NUMPAD1 + i, "key.categories.magiccrusade");
            ClientRegistry.registerKeyBinding(KEY_SPELLS[i]);
        }
    }

	public static String get_key_char(KeyBinding key)
	{
		return (Keyboard.getKeyName(key.getKeyCode()));
	}

}
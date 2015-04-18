package fr.toss.magiccrusade.client.entity;

import net.minecraft.entity.Entity;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import fr.toss.magiccrusade.common.loader.Loader;

/**
 * 
 * @author Romain
 *
 *	How to: REGISTER AN ENTITY
 *
 *	- Create it class
 *	- register it as it is shown in EntityLoader (inside `on_load()` method)
 *	- create it model and it render class, and register in RenderLoader methods
 *	- you're done :)
 *
 *	See EntityOrc, RenderOrc, ModelOrc, EntityLoader, RenderLoader for example
 */

public class EntityLoader extends Loader
{
	public EntityLoader()
	{
		super("Entities");
	}

	public void	on_load()
	{
		registerEntity(EntityOrc.class, "Orc");
	}
	
	public static void registerEntity(Class <? extends Entity > entityClass, String string)
	{
        EntityRegistry.registerGlobalEntityID(entityClass, string, EntityRegistry.findGlobalUniqueEntityId(), 0xeaff00, 0x888888);
	}
}

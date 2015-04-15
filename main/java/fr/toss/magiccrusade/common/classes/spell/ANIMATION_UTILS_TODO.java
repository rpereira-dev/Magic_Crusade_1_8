package fr.toss.magiccrusade.common.classes.spell;

import net.minecraft.client.Minecraft;
import net.minecraft.util.MathHelper;
import fr.toss.magiccrusade.client.render.EntityFX_Colored;

public class ANIMATION_UTILS_TODO {

	/**

	SPHERE ANIMATION:

	rayon = 4.0f;
	for (int phi = -180; phi < 180; phi += 2)
	{
		for (int teta = -90; teta < 90; teta += 2)
		{
			v_x = rayon * MathHelper.cos(teta) * MathHelper.cos(phi);
			v_y = rayon * MathHelper.cos(teta) * MathHelper.sin(phi);
			v_z = rayon * MathHelper.sin(teta);
			particles = new EntityFX_Colored(world, target.posX, target.posY, target.posZ, v_x, v_y, v_z, 2.0f, 5.0f, 0.0f, 1.5f);
			Minecraft.getMinecraft().effectRenderer.addEffect(particles);
		}
	}
	*/
}

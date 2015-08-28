package fr.toss.magiccrusade.common.classes.spell;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.network.NetworkRegistry.TargetPoint;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.network.PacketSpellClient;
import fr.toss.magiccrusade.common.network.PacketSpellServer;
import fr.toss.magiccrusade.common.network.Packets;

public class SpellUtils
{
	public static void deal_player_magic_damages(EntityPlayer caster, EntityLivingBase target, float amount)
	{
		target.attackEntityFrom(DamageSource.causePlayerDamage(caster), amount);
		target.attackEntityFrom(DamageSource.magic, 0.0f);
	}
	
	/** Get entity this Entity around caster */
	public static List getEntitiesAround(Entity caster, double rangex, double rangey, double rangez)
	{
		List	lst;
		
		lst = caster.worldObj.getEntitiesWithinAABBExcludingEntity(caster, caster.getEntityBoundingBox().expand(rangex, rangey, rangez));
		return (lst);
	}
	
	/** Get entity this EntityLivingBase is looking at */
	public static Entity getLookingEntity(EntityLivingBase caster, double range)
	{
		List	list;
		Entity 	entity;
		Vec3 	vec3d;
		Vec3 	vec3d1;
		double 	d;
		double 	d1;

		list = caster.worldObj.getEntitiesWithinAABBExcludingEntity(caster, caster.getEntityBoundingBox().expand(range, range, range));
		for (int i = 0; i < list.size(); i++)
		{
			entity = (Entity)list.get(i);
            if (!entity.isDead)
            {
         	   if(entity instanceof EntityLivingBase)
         	   {
         		   vec3d = caster.getLook(1.0F).normalize();
         		   vec3d1 = new Vec3(entity.posX - caster.posX, (entity.getEntityBoundingBox().minY + (double)(entity.height / 2.0F)) - (caster.posY + (double)caster.getEyeHeight()), entity.posZ - caster.posZ);
         		   d = vec3d1.lengthVector();
         		   vec3d1 = vec3d1.normalize();
         		   d1 = vec3d.dotProduct(vec3d1);
         	       if (d1 > 1.0D - 0.0250001D / d)
         	       {
         	    	   return (entity);
         	       }
         	   }
            }     
		}
		return (null);
	}



}

package fr.toss.magiccrusade.common.classes.spell.champion;

import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.EntityFX;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import fr.toss.magiccrusade.client.entity.EntityFX_Colored;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.classes.spell.ISpell;
import fr.toss.magiccrusade.common.classes.spell.SpellException;
import fr.toss.magiccrusade.common.classes.spell.SpellUtils;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.player.ServerPlayer;
import fr.toss.magiccrusade.common.player.Stats;

public class SpellShockwave implements ISpell
{
	@Override
	public EnumSpell get_enum_spell()
	{
		return (EnumSpell.SHOCKWAVE);
	}

	@Override
	public void animate(IMagicEntity caster, Entity target)
	{
		World 			world;
		EntityPlayer	player;
		EntityFX		particles;
		float			velx;
		float			velz;
		
		world = Minecraft.getMinecraft().theWorld;
    	player = Minecraft.getMinecraft().thePlayer;
		for (int i = 0; i < 250; i++)
        {
			velx = world.rand.nextFloat();
			velz = world.rand.nextFloat();
			if (world.rand.nextInt(2) == 0)
			{
				velx = -velx;
			}
			if (world.rand.nextInt(2) == 0)
			{
				velz = -velz;
			}
    		particles = new EntityFX_Colored(world, caster.getEntity().posX, caster.getEntity().posY, caster.getEntity().posZ, velx, 0, velz, 2.5f, 0, 2.5f, 5.0f);
    		Minecraft.getMinecraft().effectRenderer.addEffect(particles);
        }
	}
	
	@Override
	public int	get_target_id(EntityLivingBase caster) throws SpellException
	{
		return (-1);
	}

	@Override
	public void do_spell(IMagicEntity magic_caster, Entity target, Stats stat)
	{
		Entity			caster;
		List			lst;
		ServerPlayer	player;
		float			damages;
		
		caster = magic_caster.getEntity();
		caster.worldObj.createExplosion(caster, caster.posX + 3, caster.posY, caster.posZ + 3, 0.5f, true);	
		caster.worldObj.createExplosion(caster, caster.posX - 3, caster.posY, caster.posZ + 3, 0.5f, true);	
		caster.worldObj.createExplosion(caster, caster.posX + 3, caster.posY, caster.posZ - 3, 0.5f, true);	
		caster.worldObj.createExplosion(caster, caster.posX - 3, caster.posY, caster.posZ - 3, 0.5f, true);
		lst = SpellUtils.getEntitiesAround(caster, 8.0d, 8.0d, 8.0d);
		if (lst == null)
		{
			return ;
		}
		if (caster instanceof EntityPlayerMP)
		{
			player = ServerPlayer.from_player_mp((EntityPlayerMP)caster);
			damages = 1.0f + player.get_stats().get_strength() * 0.015f;
			for (Object obj : lst)
			{
				((Entity)obj).attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) caster), 1);
				((Entity)obj).attackEntityFrom(DamageSource.magic, damages);
			}
		}
	}
}

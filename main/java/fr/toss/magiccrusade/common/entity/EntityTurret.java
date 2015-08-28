package fr.toss.magiccrusade.common.entity;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

public class EntityTurret extends EntityTameable implements IRangedAttackMob
{
	private long	timer;
	
    public EntityTurret(World w)
    {
    	this(w, null);
    }
    
    public EntityTurret(World world, Entity caster)
    {
        super(world);
	    this.setSize(0.6F, 1.8F);
	    this.tasks.addTask(1, new EntityAIArrowAttack(this, 1.25D, 1, 10.0F));
	    this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
	    this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
	    this.tasks.addTask(4, new EntityAILookIdle(this));
	    this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
	    this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
	    this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
	    this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));

	    if (caster != null)
	    {
	        this.setTamed(true);
			this.setAttackTarget((EntityLivingBase)null);
			this.setOwnerId(caster.getUniqueID().toString());
			this.worldObj.setEntityState(this, (byte)7);
	    }
	    timer = System.currentTimeMillis();
    }
   
    @Override
    public void onUpdate()
    {
    	super.onUpdate();
    	if (System.currentTimeMillis() - this.timer > 5000)
    	{
    		this.setDead();
    	}
    }
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0);
    }


    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase entity, float f)
    {
    	if (System.currentTimeMillis() % 1000 < 100)
    	{
	        EntityArrow arrow;
	
	        arrow = new EntityArrow(this.worldObj, this, entity, 1.6F, 0);
	        arrow.setDamage((double)(f * 2.0F) + this.rand.nextGaussian() * 0.25D);
	        arrow.setDamage(arrow.getDamage() + 0.5D);
	   
	        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	        this.worldObj.spawnEntityInWorld(arrow);
    	}
    }



	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return (null);
	}
}
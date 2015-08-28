package fr.toss.magiccrusade.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.IRangedAttackMob;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIArrowAttack;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAITargetNonTamed;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.monster.IMob;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityRabbit;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.pathfinding.PathEntity;
import net.minecraft.pathfinding.PathNavigateGround;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.world.World;

import com.google.common.base.Predicate;

public class EntitySnowCube extends EntityTameable implements IRangedAttackMob
{
    public EntitySnowCube(World w)
    {
    	this(w, null);
    }
    
    
    public EntitySnowCube(World world, Entity caster)
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
    }

    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(6.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.30000000298023224D);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        super.onLivingUpdate();

        if (this.isWet())
        	this.attackEntityFrom(DamageSource.drown, 1.0F);
    }

    protected Item getDropItem()
    {
        return (Items.snowball);
    }
    
	@Override
   protected String getLivingSound()
   {
		int	r = this.worldObj.rand.nextInt(2);

		if (r == 0)
		{
			return ("magiccrusade:snowcube_live");
		}
		return ("magiccrusade:snowcube_live_2");

   }


    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */
    protected void dropFewItems(boolean bool, int nb)
    {
        int j = 2 + this.rand.nextInt(2);

        for (int k = 0; k < j; ++k)
        {
        	if (rand.nextInt(2) == 0)
            	this.dropItem(Items.snowball, 1);
        	else
            	this.dropItem(Items.snowball, 1);
        }
    }

    /**
     * Attack the specified entity using a ranged attack.
     */
    public void attackEntityWithRangedAttack(EntityLivingBase entity, float damage)
    {
    	if (System.currentTimeMillis() % 5000 < 2000)
    	{
	        EntitySnowball entitysnowball = new EntitySnowball(this.worldObj, this)
	        {
	            protected void onImpact(MovingObjectPosition obj)
	            {
	                if (obj.entityHit != null)
	                {
	                	if (!obj.entityHit.getUniqueID().toString().equals(EntitySnowCube.this.getOwnerId()))
	                	{
	                		obj.entityHit.attackEntityFrom(DamageSource.causeThrownDamage(this, this.getThrower()), 1.0f);
	                	}
	                }

	                for (int i = 0; i < 8; ++i)
	                {
	                    this.worldObj.spawnParticle(EnumParticleTypes.SNOWBALL, this.posX, this.posY, this.posZ, 0.0D, 0.0D, 0.0D, new int[0]);
	                }

	                if (!this.worldObj.isRemote)
	                {
	                    this.setDead();
	                }
	            }
	        };
	        
	        
	        double d0 = entity.posX - this.posX;
	        double d1 = entity.posY + (double)entity.getEyeHeight() - 1.100000023841858D - entitysnowball.posY;
	        double d2 = entity.posZ - this.posZ;
	        float f1 = MathHelper.sqrt_double(d0 * d0 + d2 * d2) * 0.2F;
	        
	        entitysnowball.setThrowableHeading(d0, d1 + (double)f1, d2, 1.6F, 12.0F);
	        this.playSound("random.bow", 1.0F, 1.0F / (this.getRNG().nextFloat() * 0.4F + 0.8F));
	        this.worldObj.spawnEntityInWorld(entitysnowball);
    	}
    }


	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return (null);
	}
}
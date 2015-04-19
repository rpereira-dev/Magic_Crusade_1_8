package fr.toss.magiccrusade.common.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIFollowOwner;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIOwnerHurtByTarget;
import net.minecraft.entity.ai.EntityAIOwnerHurtTarget;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.passive.EntityTameable;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class EntityDoppleganger extends EntityTameable //implements IRangedAttackMob
{

    public EntityDoppleganger(World w)
    {
    	this(w, null);
    	
    }
    
    
    public EntityDoppleganger(World world, Entity caster)
    {
        super(world);
	    this.setSize(0.6F, 1.8F);
	    this.tasks.addTask(2, new EntityAIWander(this, 1.0D));
	    this.tasks.addTask(3, new EntityAIWatchClosest(this, EntityPlayer.class, 6.0F));
	    this.tasks.addTask(4, new EntityAILookIdle(this));
	    this.tasks.addTask(5, new EntityAIFollowOwner(this, 1.0D, 10.0F, 2.0F));
	    this.targetTasks.addTask(1, new EntityAIOwnerHurtByTarget(this));
	    this.targetTasks.addTask(2, new EntityAIOwnerHurtTarget(this));
	    this.targetTasks.addTask(3, new EntityAIHurtByTarget(this, true, new Class[0]));
	    this.setEquipement();

	    if (caster != null)
	    {
	        this.setTamed(true);
			this.setAttackTarget((EntityLivingBase)null);
			this.setOwnerId(caster.getUniqueID().toString());
			this.worldObj.setEntityState(this, (byte)7);
	    }
    }
    
    /**
     * Returns true if the newer Entity AI code should be run
     */
    public boolean isAIEnabled()
    {
        return true;
    }
    
	/*public void updateEntityActionState()
	{
		List list = worldObj.getEntitiesWithinAABB(EntityCreature.class, AxisAlignedBB.getAABBPool().getAABB(posX, posY, posZ, posX + 1, posY + 1, posZ + 1).expand(16D, 4D, 16D));
		for(int i = 0; i< list.size(); i++)
		{
			Entity entity = (Entity)list.get(i);
			if(!list.isEmpty())
			{
				if(!(entity instanceof EntityPlayer))
				{
					this.setTarget(entity);
				}
			}
		}
		super.updateEntityActionState();
	}*/
    
	
	private void setEquipement()
	{
		Item	item[] = {Items.iron_sword};
		
		if (rand.nextInt(2) == 0)
		{
			this.setCurrentItemOrArmor(0, new ItemStack(item[this.getRNG().nextInt(item.length)]));
		}
	}


    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(10.0D);
        this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(1.0D);
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     * @param entity 
     */
    public void onLivingUpdate(EntityPlayer entity)
    {
        super.onLivingUpdate();

        if(!(entity instanceof EntityPlayer))
		{
			this.setAttackTarget(entity);
		}
    }
    


    protected Item getDropItem()
    {
        return (null);
    }
    
	@Override
   protected String getLivingSound()
   {
		int	r = this.worldObj.rand.nextInt(2);

		if (r == 0)
		{
			return ("magiccrusade:dopple_laughs");
		}
		return ("magiccrusade:dopple_laughs");

   }


    /**
     * Drop 0-2 items of this living's type. @param par1 - Whether this entity has recently been hit by a player. @param
     * par2 - Level of Looting used to kill this mob.
     */

    /**
     * Attack the specified entity using a ranged attack.
     */
    


	
	@Override
	public EntityAgeable createChild(EntityAgeable ageable)
	{
		return (null);
	}
}
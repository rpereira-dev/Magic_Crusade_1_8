package fr.toss.magiccrusade.common.entity;

import fr.toss.magiccrusade.utils.MagicLogger;
import net.minecraft.block.Block;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.EntityAIAttackOnCollide;
import net.minecraft.entity.ai.EntityAIHurtByTarget;
import net.minecraft.entity.ai.EntityAILookIdle;
import net.minecraft.entity.ai.EntityAIMoveThroughVillage;
import net.minecraft.entity.ai.EntityAIMoveTowardsRestriction;
import net.minecraft.entity.ai.EntityAINearestAttackableTarget;
import net.minecraft.entity.ai.EntityAISwimming;
import net.minecraft.entity.ai.EntityAIWander;
import net.minecraft.entity.ai.EntityAIWatchClosest;
import net.minecraft.entity.monster.EntityIronGolem;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;

public class EntityOrc extends EntityMob
{
	public EntityOrc(World worldIn)
	{
		super(worldIn);
        this.tasks.addTask(0, new EntityAISwimming(this));
        this.tasks.addTask(2, new EntityAIAttackOnCollide(this, EntityPlayer.class, 1.0D, false));
        this.tasks.addTask(2, this.field_175455_a);
        this.tasks.addTask(5, new EntityAIMoveTowardsRestriction(this, 1.0D));
        this.tasks.addTask(7, new EntityAIWander(this, 1.0D));
        this.tasks.addTask(8, new EntityAIWatchClosest(this, EntityPlayer.class, 8.0F));
        this.tasks.addTask(8, new EntityAILookIdle(this));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityVillager.class, 1.0D, true));
        this.tasks.addTask(4, new EntityAIAttackOnCollide(this, EntityIronGolem.class, 1.0D, true));
        this.tasks.addTask(6, new EntityAIMoveThroughVillage(this, 1.0D, false));
        this.targetTasks.addTask(1, new EntityAIHurtByTarget(this, true, new Class[] {EntityOrc.class}));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityPlayer.class, true));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityVillager.class, false));
        this.targetTasks.addTask(2, new EntityAINearestAttackableTarget(this, EntityIronGolem.class, true));
        this.setSize(0.8F, 1.95F);
        this.setEquipement();
		this.setEquipmentDropChance(0, 1.0f);
		this.setEquipmentDropChance(1, 0.25f);
		this.setEquipmentDropChance(2, 0.25f);
		this.setEquipmentDropChance(3, 0.25f);
		this.setEquipmentDropChance(4, 0.25f);

	}
	
	private void setEquipement()
	{
		Item	item[] = {Items.wooden_axe, Items.stone_shovel, Items.stone_sword, Items.bowl, Items.rabbit, Items.brick, Items.fishing_rod, Items.baked_potato, Items.carrot};
		
		if (rand.nextInt(2) == 0)
		{
			this.setCurrentItemOrArmor(0, new ItemStack(item[this.getRNG().nextInt(item.length)]));
		}
	}

	@Override
    protected void applyEntityAttributes()
    {
        super.applyEntityAttributes();
        this.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0D);
        switch (this.worldObj.rand.nextInt(3))
        {
	        case 0:
	            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.18f);
	            break ;
	            
	        case 1:
	            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.23f);
	            break ;
	            
	        case 2:
	            this.getEntityAttribute(SharedMonsterAttributes.movementSpeed).setBaseValue(0.29f);
        }
        this.getEntityAttribute(SharedMonsterAttributes.followRange).setBaseValue(20.0d);
    }
	
	@Override
    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
        if (this.worldObj.rand.nextInt(100) == 0)
        {
        	this.motionY += 0.2f;
        }
    }

    /**
     * Returns the sound this mob makes while it's alive.
     */
	@Override
    protected String getLivingSound()
    {
		int	r = this.worldObj.rand.nextInt(10);

		if (r == 0)
		{
			return ("magiccrusade:orc_burp");
		}
		if (this.worldObj.rand.nextInt(2) == 0)
		{
			return ("magiccrusade:orc_live");
		}
		return ("magiccrusade:orc_live_2");
    }

    /**
     * Returns the sound this mob makes when it is hurt.
     */
	@Override
    protected String getHurtSound()
    {
		int	r = this.worldObj.rand.nextInt(3);
		
		if (r == 0)
		{
	        return ("magiccrusade:orc_hurt");
		}
		else if (r == 1)
		{
	        return ("magiccrusade:orc_hurt_2");
		}
        return ("magiccrusade:orc_hurt_3");
    }

    /**
     * Returns the sound this mob makes on death.
     */
	@Override
    protected String getDeathSound()
    {
        return "magiccrusade:orc_hurt_3";
    }
}

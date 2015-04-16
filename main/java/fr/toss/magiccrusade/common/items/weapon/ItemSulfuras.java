package fr.toss.magiccrusade.common.items.weapon;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.client.gui.ChatColor;
import fr.toss.magiccrusade.common.classes.spell.SpellUtils;
import fr.toss.magiccrusade.common.items.EnumMaterial;
import fr.toss.magiccrusade.common.items.ItemWeapon;

public class ItemSulfuras extends ItemWeapon
{
	public ItemSulfuras()
	{
		super(EnumMaterial.TOOL_EPIC);
//		this.set_strenght(50.0f);
		this.setUnlocalizedName("sulfuras");
		this.setHasEffect();
	}
	
	 /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean bool)
    {
    	super.addInformation(item, player, list, bool);
    	list.add(" ");
    	list.add(ChatColor.LIGHT_PURPLE + I18n.format("item.description.sulfuras") + ChatColor.RESET);
    }
    
    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack is, World world, EntityPlayer player)
    {
    	BlockPos	pos;
    	Entity 		e;
    	
    	e = SpellUtils.getLookingEntity(player, 20.0d);
    	if (e != null)
    	{
    		world.setBlockState(new BlockPos(world.getTopSolidOrLiquidBlock(e.getPosition())).east(), Blocks.fire.getStateFromMeta(0));
    		world.setBlockState(new BlockPos(world.getTopSolidOrLiquidBlock(e.getPosition())).west(), Blocks.fire.getStateFromMeta(0));
    		world.setBlockState(new BlockPos(world.getTopSolidOrLiquidBlock(e.getPosition())).north(), Blocks.fire.getStateFromMeta(0));
    		world.setBlockState(new BlockPos(world.getTopSolidOrLiquidBlock(e.getPosition())).south(), Blocks.fire.getStateFromMeta(0));
    	}
    	player.extinguish();
    	return (super.onItemRightClick(is, world, player));
    }
    
    @Override
    public boolean onLeftClickEntity(ItemStack stack, EntityPlayer player, Entity entity)
    {
    	entity.setFire(1);
    	player.extinguish();
    	return (true);
    }

}

package fr.toss.magiccrusade.common.items.epic;

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
    	Entity e;
    	
    	e = this.getLookingEntity(player, 20.0d);
    	if (e != null)
    	{
    		world.setBlockState(new BlockPos(e.posX, e.posY, e.posZ), Blocks.fire.getStateFromMeta(0));
    	}

    	return (super.onItemRightClick(is, world, player));
    }

}

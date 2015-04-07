package fr.toss.magiccrusade.common.items.epic;

import java.util.List;

import net.minecraft.client.resources.I18n;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.client.gui.ChatColor;
import fr.toss.magiccrusade.common.items.EnumMaterial;
import fr.toss.magiccrusade.common.items.IItemStatable;
import fr.toss.magiccrusade.common.items.ItemWeapon;
import fr.toss.magiccrusade.common.player.Stats;

public class ItemFrostmourne extends ItemWeapon implements IItemStatable
{	
	public ItemFrostmourne()
	{
		super(EnumMaterial.TOOL_EPIC);
		this.setUnlocalizedName("frostmourne");
		this.setHasEffect();
		this.stats.set_strength(60.0f);
	}
	
	 /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean bool)
    {
    	super.addInformation(item, player, list, bool);
    	list.add(" ");
    	list.add(ChatColor.LIGHT_PURPLE + I18n.format("item.description.frostmourne") + ChatColor.RESET);
    }

	@Override
    public boolean hitEntity(ItemStack is, final EntityLivingBase entity, EntityLivingBase player)
    {
		if (entity != null)
			entity.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100, 1));
		return (true);
	}

	@Override
	public Stats get_stats()
	{
		return (this.stats);
	}
}

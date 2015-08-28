package fr.toss.magiccrusade.common.items;

import java.util.List;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import fr.toss.magiccrusade.common.player.Stats;

public class ItemWeapon extends ItemSword implements IItemMagic
{
	protected Stats	stats;
	private boolean hasEffect;

	public ItemWeapon(ToolMaterial material)
	{
		super(material);
		this.stats = new Stats();
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack is)
    {
        return (this.hasEffect || is.isItemEnchanted());
    }
	
	 /**
     * allows items to add custom lines of information to the mouseover description
     */
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack item, EntityPlayer player, List list, boolean bool)
    {
    	list.addAll(this.stats.to_string_list());
    }
    
	public ItemWeapon setHasEffect()
	{
		this.hasEffect = true;
		return (this);
	}
	
	@Override
	public void setCreativeTabsContainer()
	{
		this.setCreativeTab(CreativeTabsLoader.tabEpic);
	}
}

package fr.toss.magiccrusade.common.items;

import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor.ArmorMaterial;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.common.util.EnumHelper;

public class EnumMaterial {
		
	public static ToolMaterial TOOL_TITANIUM = EnumHelper.addToolMaterial("Titanium", 4, 1561 * 2, 10.0F, 3.5F, 20);
	public static ToolMaterial TOOL_ETHERNIUM = EnumHelper.addToolMaterial("Ethernium", 4, 1561, 10.0F, 3.5F, 50);
	public static ToolMaterial TOOL_SILVER = EnumHelper.addToolMaterial("Silver", 4, 1561, 7.0F, 2.5F, 20);
	public static ToolMaterial TOOL_EPIC = EnumHelper.addToolMaterial("Epic", 4, 1561 * 10, 10.0F, 5.0F, 50);
	public static ToolMaterial TOOL_ZERO = EnumHelper.addToolMaterial("Zero", 0, 1561 * 2, 0.0F, 0.0F, 100);
	public static ToolMaterial STAFF = EnumHelper.addToolMaterial("Staff", 4, 200, 6.0F, 2.5F, 50);


	public static EnumRarity RARITY_GOLD = EnumHelper.addEnum(EnumRarity.class, "Gold", EnumChatFormatting.GOLD, "Gold");
	public static EnumRarity RARITY_GREEN= EnumHelper.addEnum(EnumRarity.class, "Green", EnumChatFormatting.GREEN, "Green");
	public static EnumRarity RARITY_RED = EnumHelper.addEnum(EnumRarity.class, "Red", EnumChatFormatting.RED, "Red");
	public static EnumRarity RARITY_GRAY = EnumHelper.addEnum(EnumRarity.class, "Gray", EnumChatFormatting.GRAY, "Gray");
	public static EnumRarity RARITY_OBFUSCATED = EnumHelper.addEnum(EnumRarity.class, "Obf", EnumChatFormatting.OBFUSCATED, "Obf");

}

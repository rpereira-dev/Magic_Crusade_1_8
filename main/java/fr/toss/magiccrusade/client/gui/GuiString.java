package fr.toss.magiccrusade.client.gui;

public class GuiString {

	public static final int	RED_SMOOTH = 0xffff4444;

	
	public static final int TIMER_SHORT 	= 30;
	public static final int TIMER_NORMAL 	= 120;
	public static final int TIMER_LONG 		= 360;
	
	String	str;
	int		color;
	int		timer;
	
	public GuiString()
	{
		this("", 0, 0);
	}
	
	public GuiString(String str, int t)
	{
		this(str, t, 0xffffffff);
	}
	
	public GuiString(String str, int t, int c)
	{
		this.str = str;
		this.timer = t;
		this.color = c;
	}
}

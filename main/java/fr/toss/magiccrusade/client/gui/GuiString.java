package fr.toss.magiccrusade.client.gui;

public class GuiString {

	public static final int	RED_SMOOTH		= 0xffff4444;
	public static final int	BLUE_SMOOTH		= 0xff4444ff;
	public static final int	GREEN_SMOOTH	= 0xff44ff44;

	public static final int	RED				= 0xffff0000;
	public static final int	BLUE			= 0xff0000ff;
	public static final int	GREEN			= 0xff00ff00;

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

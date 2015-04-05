package fr.toss.magiccrusade.utils;

import java.util.Calendar;

public class MagicLogger
{
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";
	
	public static void	log(String str)
	{/*
		Calendar calendar;
		String	hour;
		String	minute;
		String	sec;
		
		calendar = Calendar.getInstance();
		hour = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		minute = Integer.toString(calendar.get(Calendar.MINUTE));
		sec = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
		hour = (hour.length() == 1) ? "0" + hour : hour;
		minute = (minute.length() == 1) ? "0" + minute : minute;
		sec = (sec.length() == 1) ? "0" + sec : sec;
		System.out.print("[" + hour + ":" + minute + ":" +  sec + "]");
		System.out.print(" ");
		System.out.print(" ");
		System.out.print(logtype.color + "[" + logtype.str + "]" + MagicLogger.ANSI_RESET);
		System.out.print(" ");
		System.out.println("[Magic Crusade]");*/
		System.out.println(str);
		
	}
	
	public enum LogType
	{
		LOG_SEVERE("SEVERE", MagicLogger.ANSI_RED),
		LOG_WARNING("WARNING", MagicLogger.ANSI_YELLOW),
		LOG_FINE("FINE", MagicLogger.ANSI_GREEN);
		
		String	str;
		String	color;
		
		LogType(String p_str, String p_color)
		{
			this.str	= p_str;
			this.color	= p_color;
		}
		
		@Override
		public String	toString()
		{
			return (this.str);
		}
		
		public String	toColor()
		{
			return (this.color);
		}
	}
}
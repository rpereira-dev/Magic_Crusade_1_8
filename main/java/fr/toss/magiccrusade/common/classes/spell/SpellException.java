package fr.toss.magiccrusade.common.classes.spell;

public class SpellException extends Exception
{
	private String	error;
	
	public SpellException(String err)
	{
		this.error = err;
	}
	
	public String	getErrorMessage()
	{
		return (this.error);
	}
}

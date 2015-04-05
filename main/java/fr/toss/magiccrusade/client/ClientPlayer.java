package fr.toss.magiccrusade.client;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ChatComponentText;
import api.player.client.ClientPlayerAPI;
import api.player.client.ClientPlayerBase;
import fr.toss.magiccrusade.client.gui.ChatColor;
import fr.toss.magiccrusade.common.classes.ClasseChampion;
import fr.toss.magiccrusade.common.classes.IClasse;

public class ClientPlayer extends ClientPlayerBase
{
	private static ClientPlayer	instance;
	
	private IClasse classe;
	
	/** amount of experience the player has */
	public int	experience;
	public int	experience_to_receive;

	/** player experience to achieve next level */
	public int	experience_to_next_level;
	
	/** player current level */
	public int	level;


	public ClientPlayer(ClientPlayerAPI playerapi)
	{
		super(playerapi);
		ClientPlayer.instance = this;
		this.classe = new ClasseChampion();
		this.level = 1;
		this.experience_to_next_level = this.calcul_next_level_experience();
		this.experience = 0;
		this.experience_to_receive = 0;
	}
	
	public static ClientPlayer	instance()
	{
		return (instance);
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		this.classe.update();
		if (this.experience_to_receive > 0)
		{
			this.experience += 8;
			this.experience_to_receive -= 8;
			if (this.experience >= this.experience_to_next_level)
			{
				this.on_level_up();
			}
		}
	}
	
	private void	on_level_up()
	{
		this.level++;
		this.experience = 0;
		this.experience_to_next_level = this.calcul_next_level_experience();
		this.add_chat_message(ChatColor.GREEN + "You have reached level " + this.level + ChatColor.RESET);
	}
	
	private int	calcul_next_level_experience()
	{
		return (this.level * 20 * (this.level + 1));
	}

	public float	getMaxHealth()
	{
		return (this.player.getMaxHealth());
	}

	public float	getHealth()
	{
		return (this.player.getHealth());
	}
	
	public void		add_chat_message(String str)
	{
		this.player.addChatMessage(new ChatComponentText(str));
	}

	public IClasse	get_classe()
	{
		return (this.classe);
	}
	
	public EntityPlayerSP	get_player()
	{
		return (this.player);
	}
}

package fr.toss.magiccrusade.common.player;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ChatComponentText;
import api.player.server.IServerPlayerAPI;
import api.player.server.ServerPlayerAPI;
import api.player.server.ServerPlayerBase;
import fr.toss.magiccrusade.client.gui.ChatColor;
import fr.toss.magiccrusade.common.classes.ClasseChampion;
import fr.toss.magiccrusade.common.classes.IClasse;

public class ServerPlayer extends ServerPlayerBase
{
	/** amount of experience the player has */
	public int	experience;
	
	/** player experience to achieve next level */
	public int experience_to_next_level;
	
	/** player current level */
	public int	level;
	
	/** Player classe */
	private IClasse classe;
	
	public ServerPlayer(ServerPlayerAPI playerapi)
	{
		super(playerapi);
		this.classe = new ClasseChampion();
		this.level = 1;
		this.experience_to_next_level = this.calcul_next_level_experience();
		this.experience = 0;
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		this.classe.update();
	}
	
	public void		add_chat_message(String str)
	{
		this.player.addChatMessage(new ChatComponentText(str));
	}

	private int	calcul_next_level_experience()
	{
		return (this.level * 20 * (this.level + 1));
	}

	public IClasse	get_classe()
	{
		return (this.classe);
	}

	public static ServerPlayer from_player_mp(EntityPlayerMP player)
	{
		return ((ServerPlayer) ((IServerPlayerAPI)player).getServerPlayerBase("Magic Crusade"));
	}

	public EntityPlayerMP		get_player()
	{
		return (this.player);
	}
}

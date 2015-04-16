package fr.toss.magiccrusade.client;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.util.ChatComponentText;
import api.player.client.ClientPlayerAPI;
import api.player.client.ClientPlayerBase;
import fr.toss.magiccrusade.client.gui.GuiIngameOverlay;
import fr.toss.magiccrusade.client.gui.GuiString;
import fr.toss.magiccrusade.common.classes.EnumClasse;
import fr.toss.magiccrusade.common.classes.IClasse;
import fr.toss.magiccrusade.common.classes.spell.EnumSpell;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.network.PacketPlayerData;
import fr.toss.magiccrusade.common.player.Stats;
import fr.toss.magiccrusade.utils.MagicLogger;

public class ClientPlayer extends ClientPlayerBase implements IMagicEntity
{
	private static ClientPlayer	instance;
	
	private IClasse classe;
	
	/** amount of experience the player has */
	private int	experience;
	
	/** amount of experience the player need to reach next level */
	private int experience_to_next_level;
	
	/** player current level */
	private int	level;
	
	/** Player stats */
	private Stats	stats;


	public ClientPlayer(ClientPlayerAPI playerapi)
	{
		super(playerapi);
		ClientPlayer.instance = this;
		this.classe = EnumClasse.load_classe_from_ord(EnumClasse.FARMER.ordinal());
		this.level = 1;
		this.experience = 0;
	}
	
	public static ClientPlayer	instance()
	{
		return (instance);
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		this.stats = Stats.get_player_stats(this);
		this.classe.update();
		this.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0d + this.stats.get_endurance() / 20.0f);
	}
	
/*	private void handle_experience()
	{
		if (this.experience_to_receive > 0)
		{
			if (this.experience_to_receive > 8)
			{
				this.experience += 8;
				this.experience_to_receive -= 8;
			}
			else
			{
				this.experience += this.experience_to_receive;
				this.experience_to_receive = 0;
			}
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
		GuiIngameOverlay.add_message("You have reached level " + this.level, GuiString.TIMER_SHORT, GuiString.GREEN_SMOOTH);
		for (EnumSpell spell : this.get_classe().get_spells())
		{
			if (spell.get_spell_level() == this.level)
			{
				GuiIngameOverlay.add_message("You unlocked a new spell: " + spell.get_spell_name(), GuiString.TIMER_LONG, GuiString.BLUE_SMOOTH);
			}
		}
	}*/
	
	public Stats	get_stats()
	{
		return (this.stats);
	}
	
	private int	calcul_next_level_experience()
	{
		return (this.level * 20 * (this.level + 1));
	}

	/** return player max health */
	public float	getMaxHealth()
	{
		return (this.player.getMaxHealth());
	}

	/** return player health */
	public float	getHealth()
	{
		return (this.player.getHealth());
	}
	
	 @Override
	public void readEntityFromNBT(net.minecraft.nbt.NBTTagCompound nbt)
	{
		 super.readEntityFromNBT(nbt);
		 this.level			= nbt.getInteger("level"); 
		 this.experience	= nbt.getInteger("experience");
		 this.classe		= EnumClasse.load_classe_from_ord(nbt.getInteger("classe_id"));
		 this.classe.read_from_nbt(nbt);
	}
		
	 @Override
	 public void writeEntityToNBT(net.minecraft.nbt.NBTTagCompound nbt)
	 {
		 super.writeEntityToNBT(nbt);
		 nbt.setInteger("level", this.level); 
		 nbt.setInteger("experience", this.experience);
		 nbt.setInteger("classe_id", this.classe.get_enum_classe().ordinal());
		 this.classe.write_to_nbt(nbt);
	 }
	
	/** add a messege to player chat */
	public void		add_chat_message(String str)
	{
		this.player.addChatMessage(new ChatComponentText(str));
	}

	public IClasse	get_classe()
	{
		return (this.classe);
	}
	
	/** return player current level */
	public int get_level()
	{
		return (this.level);
	}
	
	/** get entity player client side */
	public EntityPlayerSP	get_player()
	{
		return (this.player);
	}

	/** get player current experience */
	public int	get_experience()
	{
		return (this.experience);
	}
	
	/** set player level */
	public void set_level(int lvl)
	{
		this.level = lvl;
	}


	/** set player current amount of experience */
	public void set_experience(int exp)
	{
		this.experience = exp;
	}

	/** set playe current class */
	public void set_classe(int id)
	{
		this.classe = EnumClasse.load_classe_from_ord(id);
	}

	public String	get_username()
	{
		return (this.get_player().getDisplayNameString());
	}

	public float get_total_experience()
	{
		return (this.experience_to_next_level);
	}

	public void receive_data(PacketPlayerData message)
	{
		this.level = message.level;
		this.experience = message.experience;
		this.experience_to_next_level = message.experience_to_next_level;
		this.set_classe(message.classe_id);
		this.get_classe().set_energy(message.energy);
	}
}

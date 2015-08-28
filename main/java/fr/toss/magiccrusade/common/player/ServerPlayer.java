package fr.toss.magiccrusade.common.player;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.ChatComponentText;
import net.minecraft.world.World;
import api.player.server.IServerPlayerAPI;
import api.player.server.ServerPlayerAPI;
import api.player.server.ServerPlayerBase;
import fr.toss.magiccrusade.common.classes.EnumClasse;
import fr.toss.magiccrusade.common.classes.IClasse;
import fr.toss.magiccrusade.common.entity.IMagicEntity;
import fr.toss.magiccrusade.common.network.PacketPlayerData;
import fr.toss.magiccrusade.common.network.Packets;
import fr.toss.magiccrusade.utils.MagicLogger;

public class ServerPlayer extends ServerPlayerBase implements IMagicEntity
{
	/** amount of experience the player has */
	private int	experience;
	
	/** player experience to achieve next level */
	private int experience_to_next_level;
	
	/** player current level */
	private int	level;
	
	/** Player classe */
	private IClasse classe;
	
	/** player stats */
	private Stats	stats;
	
	/** update packet */
	private PacketPlayerData	packet;
	
	public ServerPlayer(ServerPlayerAPI playerapi)
	{
		super(playerapi);
		this.classe = EnumClasse.load_classe_from_ord(EnumClasse.FARMER.ordinal());
		this.level = 1;
		this.experience_to_next_level = this.calcul_next_level_experience();
		this.experience = 0;
		this.packet = new PacketPlayerData();
	}
	
	public void onUpdate()
	{
		super.onUpdate();
		this.level = 10;

		this.stats = Stats.get_player_stats(this); //can be optimize by filling the object instead of creating a new one
		this.classe.update();
		this.player.getEntityAttribute(SharedMonsterAttributes.maxHealth).setBaseValue(20.0d + this.stats.get_endurance() / 20.0f);
		if (MinecraftServer.getServer().getTickCounter() % 20 == 0)
		{
			this.sendPlayerData();
		}
	}
	
	private void	sendPlayerData()
	{
		this.packet.from_player(this);
		Packets.network.sendTo(this.packet, this.player);
	}

	 @Override
	public void readEntityFromNBT(net.minecraft.nbt.NBTTagCompound nbt)
	{
		super.readEntityFromNBT(nbt);
		this.level			= nbt.getInteger("level"); 
		this.experience	= nbt.getInteger("experience");
		this.classe		= EnumClasse.load_classe_from_ord(nbt.getInteger("classe_id"));
		this.classe.read_from_nbt(nbt);
		this.experience_to_next_level = this.calcul_next_level_experience();
	}
		
	 @Override
	 public void writeEntityToNBT(net.minecraft.nbt.NBTTagCompound nbt)
	 {
		super.writeEntityToNBT(nbt);
		nbt.setInteger("level", this.level); 
		nbt.setInteger("experience", this.experience);
		nbt.setInteger("classe_id", this.classe.get_enum_classe().ordinal());
		this.classe.write_to_nbt(nbt);
		this.experience_to_next_level = this.calcul_next_level_experience();
	 }
	
	public void		add_chat_message(String str)
	{
		this.player.addChatMessage(new ChatComponentText(str));
	}

	public int	calcul_next_level_experience()
	{
		return (this.level * 20 * (this.level + 1));
	}

	/** return player classe */
	public IClasse	get_classe()
	{
		return (this.classe);
	}

	/** get the instance of a ServerPlayer from the corresponding EntityPlayerMP */
	public static ServerPlayer from_player_mp(EntityPlayerMP player)
	{
		return ((ServerPlayer) ((IServerPlayerAPI)player).getServerPlayerBase("Magic Crusade"));
	}
	
	/** return corresponding EntityPlayerMP */
	public EntityPlayerMP	get_player()
	{
		return (this.player);
	}

	/** add player experience and make it level up if needed */
	public void	add_experience(int amount)
	{
		this.experience += amount;
		if (this.experience >= this.experience_to_next_level)
		{
			this.level++;
			this.experience = this.experience - this.experience_to_next_level;
			this.experience_to_next_level = this.calcul_next_level_experience();
			this.get_player().worldObj.playSoundEffect(this.get_player().posX, this.get_player().posY, this.get_player().posZ, "random.chestopen", 1, 1);
		}
	}

	public Stats	get_stats()
	{
		return (this.stats);
	}
	
	/** return player current level */
	public int get_level()
	{
		return (this.level);
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

	public int get_experience()
	{
		return (this.experience);
	}
	
	public int	get_experience_to_next_level()
	{
		return (this.experience_to_next_level);
	}
	

	@Override
	public EntityLivingBase getEntity()
	{
		return (this.get_player());
	}

	@Override
	public Stats getStats()
	{
		return (this.stats);
	}

	@Override
	public World getWorld()
	{
		return (this.get_player().worldObj);
	}
}

package StevenDimDoors.mod_pocketDim.commands;

import StevenDimDoors.mod_pocketDim.DDProperties;
import StevenDimDoors.mod_pocketDim.DungeonGenerator;
import StevenDimDoors.mod_pocketDim.mod_pocketDim;
import StevenDimDoors.mod_pocketDim.helpers.DungeonHelper;
import StevenDimDoors.mod_pocketDim.helpers.dimHelper;
import net.minecraft.command.CommandBase;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;

public class CommandEndDungeonCreation extends CommandBase
{
	private static DDProperties properties = null;
	
	public CommandEndDungeonCreation()
	{
		if (properties == null)
			properties = DDProperties.instance();
	}
	
	public String getCommandName()//the name of our command
	{
		return "dimdoors-endDungeonCreation";
	}

	@Override
	public void processCommand(ICommandSender var1, String[] var2) 

	{
		
		EntityPlayer player =this.getCommandSenderAsPlayer(var1);
		
		if(!mod_pocketDim.dungeonHelper.customDungeonStatus.containsKey(player.worldObj.provider.dimensionId))
		{
			if(var2.length<2)
			{
				player.sendChatToPlayer("Must have started dungeon creation, use argument OVERRIDE to export anyway");
				return;

			}
		
			else if(!var2[1].contains("OVERRIDE"))
			{
				player.sendChatToPlayer("Must have started dungeon creation, use argument OVERRIDE to export anyway");
				return;
	
			}

		}
		
		int x = (int) player.posX;
		int y = (int) player.posY;
		int z = (int) player.posZ;
		
		if(var2.length==0)
		{
			player.sendChatToPlayer("Must name file");
		}
		else if(!player.worldObj.isRemote)
		{
			DungeonGenerator newDungeon = mod_pocketDim.dungeonHelper.exportDungeon(player.worldObj, x, y, z, properties.CustomSchematicDirectory + "/" + var2[0] + ".schematic");
			player.sendChatToPlayer("created dungeon schematic in " + properties.CustomSchematicDirectory +"/"+var2[0]+".schematic");
			mod_pocketDim.dungeonHelper.customDungeons.add(newDungeon);
			
			if(mod_pocketDim.dungeonHelper.customDungeonStatus.containsKey(player.worldObj.provider.dimensionId)&&!player.worldObj.isRemote)
			{
			//	mod_pocketDim.dungeonHelper.customDungeonStatus.remove(player.worldObj.provider.dimensionId);
			//	dimHelper.instance.teleportToPocket(player.worldObj, mod_pocketDim.dungeonHelper.customDungeonStatus.get(player.worldObj.provider.dimensionId), player);

			}
		}

		
	// TODO Auto-generated method stub
	
	}
}
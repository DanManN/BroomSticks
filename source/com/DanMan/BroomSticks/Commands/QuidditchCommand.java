package com.DanMan.BroomSticks.Commands;

import com.DanMan.BroomSticks.Quidditch.QArena;
import com.DanMan.BroomSticks.main.BroomSticks;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;







public class QuidditchCommand
    implements CommandExecutor
{
    private BroomSticks plugin;
    private boolean bool;
    
    public QuidditchCommand(BroomSticks plugin)
    {
	this.plugin = plugin;
    }
    
    public boolean onCommand(CommandSender sender, Command cmd, String string, String[] args)
    {
	if (cmd.getName().equalsIgnoreCase("q")) {
	    this.bool = qCommand(sender, args);
	}
	return this.bool;
    }
    
    public boolean qCommand(CommandSender sender, String[] args) {
	if (sender.hasPermission("broomsticks.quidditch")) {
	    if (args.length == 2) {
		QArena qa = new QArena(args[1], this.plugin);
		
		if (args[0].equalsIgnoreCase("new")) {
		    if ((sender instanceof Player)) {
			if (qa.arenaExists()) {
			    sender.sendMessage(ChatColor.RED + "That arena already exists.");
			} else {
			    qa.setAndSave(((Player)sender).getLocation());
			    sender.sendMessage(ChatColor.BLUE + "You created the quidditch arena named: " + args[1]);
			}
		    } else {
			sender.sendMessage(ChatColor.RED + "Only players can create arena's");
		    }
		}
		else if (args[0].equalsIgnoreCase("delete")) {
		    qa.deleteArena();
		    sender.sendMessage(ChatColor.BLUE + "You deleted the quidditch arena named: " + args[1]);
		} else {
		    sender.sendMessage(ChatColor.RED + "No such command: /q " + args[0]);
		    return false;
		}
		return true;
	    }
	    sender.sendMessage(ChatColor.RED + "Incorrect number of arguments!");
	    return false;
	}
	
	sender.sendMessage(ChatColor.RED + "You don't have the broomsticks.quidditch permission");
	return true;
    }
}

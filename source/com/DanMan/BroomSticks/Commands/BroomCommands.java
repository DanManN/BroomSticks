package com.DanMan.BroomSticks.Commands;

import com.DanMan.BroomSticks.main.Broom;
import com.DanMan.BroomSticks.main.BroomSticks;
import com.DanMan.BroomSticks.main.ConfigLoader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BroomCommands implements CommandExecutor {
	BroomSticks plugin;
	private final ConfigLoader info;
	private final ArrayList<Broom> broomStick;
	boolean bool;

	public BroomCommands(BroomSticks plugin) {
		this.plugin = plugin;
		this.info = plugin.getConfigLoader();
		this.broomStick = this.info.getBrooms();
	}

	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel,
							 String[] args) {
		if (cmd.getName().equalsIgnoreCase("broom")) {
			this.bool = giveBroom(sender, args);
		}
		return this.bool;
	}

	private boolean giveBroom(CommandSender sender, String[] args) {
		if (sender.hasPermission("broomsticks.spawn")) {
			Player player = null;
			if (args.length == 1) {
				if ((sender instanceof Player)) {
					player = (Player)sender;
				} else {
					sender.sendMessage(ChatColor.RED +
									   "Only players can fly broom sticks!");
					return false;
				}
			} else if (args.length == 2) {
				Pattern pat = Pattern.compile("[^A-Za-z0-9_]+");
				Matcher m = pat.matcher(args[0]);
				if (!m.find()) {
					player = Bukkit.getServer().getPlayer(args[0]);
				} else {
					sender.sendMessage(
						ChatColor.RED +
						"A player's username can only contain letters, numbers and _");
					return false;
				}
				args[0] = args[1];
			} else {
				sender.sendMessage(ChatColor.RED + "Incorrect number of arguments!");
				return false;
			}
			giveBroom(player, sender, args[0]);
			return true;
		}
		sender.sendMessage(ChatColor.RED +
						   "You don't have the broomSticks.spawn permission");
		return true;
	}

	public void giveBroom(Player player, CommandSender sender, String broomName) {
		ItemStack broom = null;
		if (player != null) {
			for (Broom bs : this.broomStick) {
				if (ChatColor.stripColor(bs.getName()).equalsIgnoreCase(broomName)) {
					broom = bs.getItem();
					ItemMeta meta = broom.getItemMeta();
					meta.addEnchant(Enchantment.ARROW_INFINITE, 1, true);
					meta.setDisplayName(bs.getName().replace("_", " "));
					broom.setItemMeta(meta);
					break;
				}
			}
			if (broom != null) {
				player.getInventory().addItem(new ItemStack[] {broom});
			} else {
				sender.sendMessage(ChatColor.RED +
								   "Sorry, that is not a name of a valid broom stick.");
			}
		} else {
			sender.sendMessage(ChatColor.RED + "Sorry, that player is not online.");
		}
	}
}

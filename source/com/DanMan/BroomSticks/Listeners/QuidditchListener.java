package com.DanMan.BroomSticks.Listeners;

import com.DanMan.BroomSticks.Quidditch.Balls;
import com.DanMan.BroomSticks.main.BroomSticks;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class QuidditchListener implements Listener {
	private BroomSticks plugin;

	public QuidditchListener(BroomSticks plugin)
	{
		this.plugin = plugin;
	}

	@EventHandler public void onReleaseSnitch(PlayerInteractEvent evt)
	{
		if (evt.getItem() == null) {
			return;
		}
		if (evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
			Location loc = evt.getClickedBlock().getLocation();
			loc.setY(loc.getY() + 1.0D);
			if (evt.getItem().getType() == Material.STICK) {
				Balls.releaseSnitch(loc, this.plugin);
			}
		}
	}

	@EventHandler public void onCatchSnitch(EntityDamageByEntityEvent evt)
	{
		if (((evt.getDamager() instanceof Player)) &&
		    ((evt.getEntity() instanceof Bat))) {
			Player player = (Player)evt.getDamager();
			Bat b = (Bat)evt.getEntity();
			player.sendMessage(ChatColor.GOLD +
					   "Congratulations you have caught the Snitch!");
			Balls.catchSnitch(player, b, this.plugin);
		}
	}
}

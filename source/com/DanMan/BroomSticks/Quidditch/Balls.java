package com.DanMan.BroomSticks.Quidditch;

import com.DanMan.BroomSticks.main.BroomSticks;
import com.DanMan.BroomSticks.utils.SNGMetaData;
import org.bukkit.Location;
import org.bukkit.Server;
import org.bukkit.World;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.util.Vector;

public class Balls {
	public static void releaseSnitch(Location loc, BroomSticks plugin) {
		Bat snitch = (Bat)loc.getWorld().spawnEntity(loc, EntityType.BAT);
		int id = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(
			plugin, new Runnable() {
				public void run() {
					Vector bat = Balls.this.getLocation().getDirection();
					bat.multiply(1.5D);
					Balls.this.setVelocity(bat);
				}
			}, 10L, 10L);
		SNGMetaData.setBatMetadata(snitch, id, plugin);
	}

	public static void catchSnitch(Player player, Bat snitch, BroomSticks plugin) {
		int id = SNGMetaData.getBatMetadata(snitch, plugin);
		plugin.getServer().getScheduler().cancelTask(id);
		snitch.remove();
	}
}

package com.DanMan.BroomSticks.main;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

public class FlyTask {
	public static int flying(final BroomSticks plugin, final Player player,
				 final Entity broom, final double speed)
	{
		int id = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(
			plugin, new Runnable() {
				public void run()
				{
					/*if (player.isSneaking()) {
					  Broom.dismount(broom, player, speed, plugin);
					  }*/
					Broom.fly(player, broom, speed);
					if (broom.getPassenger() != player) {
						Broom.dismount(broom, player, speed, plugin);
					}
				}
			}, 1L, 1L);

		// System.out.println("Started Flying" + id);
		return id;
	}

	public static void stopFlying(BroomSticks plugin, int id)
	{
		plugin.getServer().getScheduler().cancelTask(id);
		// System.out.println("Stopped Flying" + id);
	}
}

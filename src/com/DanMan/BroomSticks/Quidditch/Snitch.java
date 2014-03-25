/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.BroomSticks.Quidditch;

import com.DanMan.BroomSticks.main.BroomSticks;
import com.DanMan.BroomSticks.utils.SNGMetaData;
import org.bukkit.Location;
import org.bukkit.entity.Bat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

/**
 *
 * @author DAY
 */
public class Snitch {

    public static void releaseSnitch(Location loc, BroomSticks plugin) {
        final Bat snitch = (Bat) loc.getWorld().spawnEntity(loc, EntityType.BAT);
        int id = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {
                Vector bat = snitch.getLocation().getDirection();
                bat.multiply(1.5);
                snitch.setVelocity(bat);
            }
        }, 10, 10);
        SNGMetaData.setBatMetadata(snitch, id, plugin);
    }

    public static void catchSnitch(Player player, Bat snitch, BroomSticks plugin) {
        int id = SNGMetaData.getBatMetadata(snitch, plugin);
        plugin.getServer().getScheduler().cancelTask(id);
        snitch.remove();
        
    }
}

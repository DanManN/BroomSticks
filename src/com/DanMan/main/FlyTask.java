/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.main;

import org.bukkit.entity.Player;
import org.bukkit.entity.Squid;

/**
 *
 * @author DAY
 */
public class FlyTask {
    
    public static int flying(BroomSticks plugin, final Player player, final Squid broom, final double speed) {
        
        int id = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {
                    if (player.isSneaking()) {
                        Broom.fly(player, broom, speed);
                    } else {
                        Broom.glide(player, broom, speed);
                    }
            }
        }, 1, 1);
        return id;
    }
    
    public static void stopFlying(BroomSticks plugin, int id) {
        plugin.getServer().getScheduler().cancelTask(id);
    }
}

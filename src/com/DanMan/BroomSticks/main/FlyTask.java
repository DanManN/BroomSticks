/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.BroomSticks.main;

import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;

/**
 *
 * @author DAY
 */
public class FlyTask {
    
    public static int flying(BroomSticks plugin, final Player player, final Horse broom, final double speed) {
        
        int id = plugin.getServer().getScheduler().scheduleSyncRepeatingTask(plugin, new Runnable() {

            @Override
            public void run() {
//                    if (player.isSneaking()) {
//                        Broom.fly(player, broom, speed);
//                    } else {
//                        Broom.glide(player, broom, speed);
//                    }
                
                Broom.fly(player, broom, speed);
            }
        }, 1, 1);
        return id;
    }
    
    public static void stopFlying(BroomSticks plugin, int id) {
        plugin.getServer().getScheduler().cancelTask(id);
    }
}

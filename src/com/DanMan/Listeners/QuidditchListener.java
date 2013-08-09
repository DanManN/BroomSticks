/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.Listeners;

import com.DanMan.Quidditch.Snitch;
import com.DanMan.main.BroomSticks;
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

/**
 *
 * @author DAY
 */
public class QuidditchListener implements Listener{

    private BroomSticks plugin;

    public QuidditchListener(BroomSticks plugin) {
        this.plugin = plugin;
    }

    //delete later
    @EventHandler
    public void onReleaseSnitch(PlayerInteractEvent evt) {
        if (evt.getItem() == null) {
            return;
        }
        if (evt.getAction() == Action.RIGHT_CLICK_BLOCK) {
            Location loc = evt.getClickedBlock().getLocation();
            loc.setY(loc.getY() + 1);
            if (evt.getItem().getType() == Material.STICK) {
                Snitch.releaseSnitch(loc, plugin);
            }
        }
    }
    
    @EventHandler
    public void onCatchSnitch(EntityDamageByEntityEvent evt) {
        if (evt.getDamager() instanceof Player && evt.getEntity() instanceof Bat) {
            Player player = (Player) evt.getDamager();
            Bat b = (Bat) evt.getEntity();
            player.sendMessage(ChatColor.GOLD + "Congratulations you have caught the Snitch!");
            Snitch.catchSnitch(player, b, plugin);
        }
    }
}

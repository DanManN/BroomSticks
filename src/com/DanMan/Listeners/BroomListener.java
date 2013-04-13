/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.Listeners;

import com.DanMan.main.Brooms;
import com.DanMan.main.ConfigLoader;
import com.DanMan.main.BroomSticks;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author DAY
 */
public class BroomListener implements Listener {

    private BroomSticks plugin;
    private ConfigLoader info;
    private Brooms broomStick[];

    public BroomListener(BroomSticks plugin) {
        this.plugin = plugin;
        info = plugin.getConfigLoader();
        broomStick = info.getBrooms();
    }

    @EventHandler
    public void onUseBroom(PlayerInteractEvent evt) {
        ItemStack item = evt.getItem();
        if (item != null && item.getEnchantments().containsKey(Enchantment.ARROW_INFINITE)) {
            Material broom = item.getType();
            double speed = 0;
            for (Brooms bs : broomStick) {
                if (bs.getItem().getType() == broom) {
                    speed = bs.getSpeed();
                    break;
                }
            }
            if (speed != 0) {
                Player player = evt.getPlayer();
                if (player.isSneaking()) {
                    Brooms.fly(player, speed);
                } else {
                    Brooms.glide(player, speed);
                }
            }
        }
    }
}

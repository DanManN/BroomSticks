/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.Listeners;

import com.DanMan.main.Broom;
import com.DanMan.main.BroomSticks;
import com.DanMan.main.ConfigLoader;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 *
 * @author DAY
 */
public class BroomCraftListener implements Listener {

    private BroomSticks plugin;
    private ConfigLoader info;
    private Broom broomStick[];

    public BroomCraftListener(BroomSticks plugin) {
        this.plugin = plugin;
        info = plugin.getConfigLoader();
        broomStick = info.getBrooms();
    }

    @EventHandler
    public void onBroomEnchant(InventoryClickEvent evt) {
        if (evt.getInventory() instanceof AnvilInventory) {
            ItemStack item = evt.getCurrentItem();
            if (item != null) {
                Material broom = item.getType();
                if (item.containsEnchantment(Enchantment.ARROW_INFINITE)) {
                    for (Broom bs : broomStick) {
                        if (bs.getItem().getType() == broom) {
                            ItemMeta meta = item.getItemMeta();
                            if (!meta.hasDisplayName()) {
                                meta.setDisplayName(ChatColor.BLUE + bs.getName());
                                item.setItemMeta(meta);
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
}

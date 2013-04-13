/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.main;

import java.util.logging.Level;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author DAY
 */
public class ConfigLoader {

    private BroomSticks plugin;
    private Brooms[] brooms;
    

    public ConfigLoader(BroomSticks plugin) {
        this.plugin = plugin;
    }

    public Brooms[] getBrooms() {
        return brooms;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        String key = "Brooms.";
        int nob = plugin.getConfig().getInt(key + "number-of-brooms");
        brooms = new Brooms[nob--];
        for (int i = 0; i <= nob; i++) {
            key = "Brooms.broom" + (i + 1) + ".";
            try {
                String name = plugin.getConfig().getString(key + "name");
                //System.out.println(name);
                double speed = plugin.getConfig().getDouble(key + "speed");
                //System.out.println(speed);
                int itemId = plugin.getConfig().getInt(key + "item");
                ItemStack item = new ItemStack(itemId);
                //System.out.println(item);
                brooms[i] = new Brooms(name, speed, item);
            } catch (Exception e) {
                plugin.getLogger().log(Level.SEVERE, "Invalid Config: Could not load brooms.", e);
            }
        }
    }
}

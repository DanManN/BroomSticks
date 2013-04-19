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
    private Broom[] brooms;
    

    public ConfigLoader(BroomSticks plugin) {
        this.plugin = plugin;
    }

    public Broom[] getBrooms() {
        return brooms;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        String key = "Brooms.";
        int nob = plugin.getConfig().getInt(key + "number-of-brooms");
        brooms = new Broom[nob--];
        for (int i = 0; i <= nob; i++) {
            key = "Brooms.broom" + (i + 1);
            if (!plugin.getConfig().contains(key)) {
                plugin.getLogger().log(Level.INFO, "BroomSticks: Loaded all config values.");
                break;
            }
                //load config values
                String name = plugin.getConfig().getString(key + ".name");
                if (name == null) {
                    name = plugin.getConfig().getDefaults().getString(key + ".name");
                    plugin.getLogger().log(Level.WARNING, "Invalid Config Value: {0}name! Replacing with default value.", key);
                }
                
                double speed = plugin.getConfig().getDouble(key + ".speed");
                if (speed == 0) {
                    speed = plugin.getConfig().getDefaults().getDouble(key + ".speed");
                    plugin.getLogger().log(Level.WARNING, "Invalid Config Value: {0}speed! Replacing with default value.", key);
                }
                
                int itemId = plugin.getConfig().getInt(key + ".item");
                if (itemId == 0) {
                    itemId = plugin.getConfig().getDefaults().getInt(key + ".name");
                    plugin.getLogger().log(Level.WARNING, "Invalid Config Value: {0}item! Replacing with default value.", key);
                }
                ItemStack item = new ItemStack(itemId);
                
                int durability = plugin.getConfig().getInt(key + ".durability");
                if (durability == 0) {
                    durability = plugin.getConfig().getDefaults().getInt(key + ".durability");
                    plugin.getLogger().log(Level.WARNING, "Invalid Config Value: {0}durability! Replacing with default value.", key);
                }
                
                //create broom
                brooms[i] = new Broom(name, speed, item, durability);
                
        }
    }
}

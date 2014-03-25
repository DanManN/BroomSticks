/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.BroomSticks.main;

import java.util.ArrayList;
import java.util.logging.Level;
import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author DAY
 */
public class ConfigLoader {

    private BroomSticks plugin;
    private ArrayList<Broom> brooms;
    private int arenaRadius;

    public ConfigLoader(BroomSticks plugin) {
        this.plugin = plugin;
    }

    public ArrayList<Broom> getBrooms() {
        return brooms;
    }

    public int getArenaRadius() {
        return arenaRadius;
    }

    public void loadConfig() {
        plugin.saveDefaultConfig();
        //arenaRadius = plugin.getConfig().getInt("quidditch-pitch-radius");
        brooms = new ArrayList();
        int i = 1;
        String key = "Brooms.broom" + i;
        while (plugin.getConfig().contains(key)) {
            if (!plugin.getConfig().contains(key)) {
                plugin.getLogger().info("BroomSticks: Loaded all config values.");
                break;
            }
            //load config values
            String name = ChatColor.translateAlternateColorCodes('$', plugin.getConfig().getString(key + ".name"));
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
            brooms.add(new Broom(name, speed, item, durability));
            //iterate
            i++;
            key = "Brooms.broom" + i;
        }
    }
}

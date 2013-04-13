/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.main;

import com.DanMan.Listeners.BroomCraftListener;
import com.DanMan.Listeners.BroomListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 *
 * @author DAY
 */
public class BroomSticks extends JavaPlugin{

    private ConfigLoader config;
    
    @Override
    public void onEnable() {
        config = new ConfigLoader(this);
        config.loadConfig();
        registerListeners();
        getLogger().info("Wizardry: Enabled");
    }

    @Override
    public void onDisable() {
    }

    public ConfigLoader getConfigLoader() {
        return config;
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BroomListener(this), this);
        pm.registerEvents(new BroomCraftListener(this), this);
    }
}

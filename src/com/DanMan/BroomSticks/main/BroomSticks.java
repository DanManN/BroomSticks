/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.BroomSticks.main;

import com.DanMan.BroomSticks.Commands.BroomCommands;
import com.DanMan.BroomSticks.Listeners.BroomCraftListener;
import com.DanMan.BroomSticks.Listeners.BroomListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 *
 * @author DAY
 */
public class BroomSticks extends JavaPlugin{

    private ConfigLoader config;
    private BroomCommands myEx;
    //private QuidditchCommand myEx2;
    
    @Override
    public void onEnable() {
        config = new ConfigLoader(this);
        config.loadConfig();
        myEx = new BroomCommands(this);
        //myEx2 = new QuidditchCommand(this);
        getCommand("broom").setExecutor(myEx);
        //getCommand("q").setExecutor(myEx2);
        registerListeners();
        getLogger().info("BroomSticks: Enabled");
    }

    @Override
    public void onDisable() {
        Broom.dismountAll(this);
        getLogger().info("BroomSticks: Disabled");
    }

    public ConfigLoader getConfigLoader() {
        return config;
    }

    public void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new BroomListener(this), this);
        pm.registerEvents(new BroomCraftListener(this), this);
        //pm.registerEvents(new QuidditchListener(this), this);
    }
}

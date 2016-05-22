package com.DanMan.BroomSticks.main;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.ChatColor;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;





public class ConfigLoader
{
    private BroomSticks plugin;
    private ArrayList<Broom> brooms;
    private int arenaRadius;
    
    public ConfigLoader(BroomSticks plugin)
    {
	this.plugin = plugin;
    }
    
    public ArrayList<Broom> getBrooms() {
	return this.brooms;
    }
    
    public int getArenaRadius() {
	return this.arenaRadius;
    }
    
    public void loadConfig() {
	this.plugin.saveDefaultConfig();
	
	this.brooms = new ArrayList();
	int i = 1;
	String key = "Brooms.broom" + i;
	while (this.plugin.getConfig().contains(key)) {
	    if (!this.plugin.getConfig().contains(key)) {
		this.plugin.getLogger().info("BroomSticks: Loaded all config values.");
		break;
	    }
	    
	    String name = ChatColor.translateAlternateColorCodes('$', this.plugin.getConfig().getString(key + ".name"));
	    if (name == null) {
		name = this.plugin.getConfig().getDefaults().getString(key + ".name");
		this.plugin.getLogger().log(Level.WARNING, "Invalid Config Value: {0}name! Replacing with default value.", key);
	    }
	    
	    double speed = this.plugin.getConfig().getDouble(key + ".speed");
	    if (speed == 0.0D) {
		speed = this.plugin.getConfig().getDefaults().getDouble(key + ".speed");
		this.plugin.getLogger().log(Level.WARNING, "Invalid Config Value: {0}speed! Replacing with default value.", key);
	    }
	    
	    int itemId = this.plugin.getConfig().getInt(key + ".item");
	    if (itemId == 0) {
		itemId = this.plugin.getConfig().getDefaults().getInt(key + ".name");
		this.plugin.getLogger().log(Level.WARNING, "Invalid Config Value: {0}item! Replacing with default value.", key);
	    }
	    ItemStack item = new ItemStack(itemId);
	    
	    int durability = this.plugin.getConfig().getInt(key + ".durability");
	    if (durability == 0) {
		durability = this.plugin.getConfig().getDefaults().getInt(key + ".durability");
		this.plugin.getLogger().log(Level.WARNING, "Invalid Config Value: {0}durability! Replacing with default value.", key);
	    }
	    
	    
	    this.brooms.add(new Broom(name, speed, item, durability));
	    
	    i++;
	    key = "Brooms.broom" + i;
	}
    }
}

package com.DanMan.BroomSticks.main;

import com.DanMan.BroomSticks.Commands.BroomCommands;
import com.DanMan.BroomSticks.Listeners.BroomCraftListener;
import com.DanMan.BroomSticks.Listeners.BroomListener;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class BroomSticks extends JavaPlugin
{
	private ConfigLoader config;
	private BroomCommands myEx;
	private PluginManager evtBus;

	public void onEnable()
	{
		this.config = new ConfigLoader(this);
		this.config.loadConfig();
		this.myEx = new BroomCommands(this);
		getCommand("broom").setExecutor(this.myEx);
		registerListeners();
		getLogger().info("BroomSticks: Enabled");
	}


	public void onDisable()
	{
		Broom.dismountAll(this);
		getLogger().info("BroomSticks: Disabled");
	}

	public ConfigLoader getConfigLoader()
	{
		return this.config;
	}

	public void registerListeners()
	{
		this.evtBus = getServer().getPluginManager();
		this.evtBus.registerEvents(new BroomListener(this), this);
		this.evtBus.registerEvents(new BroomCraftListener(this), this);
	}
}

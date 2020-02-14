package com.DanMan.BroomSticks.Quidditch;

import com.DanMan.BroomSticks.main.BroomSticks;
import com.DanMan.BroomSticks.main.ConfigLoader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Scanner;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

public class QArena {
	private BroomSticks plugin;
	private static File sFile = new File("plugins/BroomSticks/arenas.txt");
	private Location center;
	private String name;

	public QArena(String name, BroomSticks plugin)
	{
		this.plugin = plugin;
		this.name = name;
		this.center = loadCenter();
	}

	public void setAndSave(Location center)
	{
		this.center = center;
		try {
			sFile.createNewFile();
		} catch (IOException e) {
			System.err.println(
				"Error: Could not create file due to illegal characters." + e);
		}
		try {
			PrintWriter out = new PrintWriter(
				new BufferedWriter(new FileWriter(sFile, true)));
			out.println(this.name + ": " + center.getWorld().getName() + "," +
				    center.getBlockX() + "," + center.getBlockZ());
			out.flush();
			out.close();
		} catch (IOException e) {
			System.err.println("Error: Could not write to file: " + e);
		}
	}

	public boolean arenaExists()
	{
		String data = "";
		if (sFile.exists()) {
			try {
				Scanner in = new Scanner(sFile);
				while (in.hasNextLine()) {
					data = in.nextLine();
					if (data.startsWith(this.name)) {
						in.close();
						return true;
					}
				}
				in.close();
			} catch (FileNotFoundException ex) {
				System.err.println("Error: Could not read from file: " + ex);
			}
		}
		return false;
	}

	private Location loadCenter()
	{
		String data = "";
		if (sFile.exists()) {
			try {
				Scanner in = new Scanner(sFile);
				while (in.hasNextLine()) {
					data = in.nextLine();
					if (data.startsWith(this.name)) {
						break;
					}
				}
				in.close();
			} catch (FileNotFoundException ex) {
				System.err.println("Error: Could not read from file: " + ex);
			}
			if ((!data.equals("")) && (data.startsWith(this.name))) {
				data.replace(this.name + ": ", "");
				String[] coords = data.split(",");
				World w = Bukkit.getWorld(coords[0]);
				int x = Integer.parseInt(coords[1]);
				int z = Integer.parseInt(coords[2]);
				return new Location(w, x, 0.0D, z);
			}
		}
		return null;
	}

	public Location getCenter()
	{
		return this.center;
	}

	public void deleteArena()
	{
		if (sFile.exists()) {
			StringBuilder nContent = new StringBuilder();
			try {
				Scanner in = new Scanner(sFile);

				while (in.hasNextLine()) {
					String data = in.nextLine();
					if (!data.startsWith(this.name)) {
						nContent.append(data).append("\r\n");
					}
				}
				in.close();
			} catch (FileNotFoundException ex) {
				System.err.println("Error: Could not read from file: " + ex);
			}
			try {
				PrintWriter out = new PrintWriter(sFile);
				out.print(nContent);
				out.flush();
				out.close();
			} catch (FileNotFoundException e) {
				System.err.println("Error: Could not write to file: " + e);
			}
		}
	}

	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getRadius()
	{
		return this.plugin.getConfigLoader().getArenaRadius();
	}

	public boolean inArena(Location loc)
	{
		if (this.center.getWorld() == loc.getWorld()) {
			double x1 = this.center.getX();
			double z1 = this.center.getZ();

			double x2 = loc.getX();
			double z2 = loc.getZ();

			double distance =
				Math.sqrt(Math.pow(x2 - x1, 2.0D) + Math.pow(z2 - z1, 2.0D));

			return distance <= getRadius();
		}
		return false;
	}

	public static void arenaBroadcast(String message, QArena qa)
	{
		for (Player player :) {
			if (qa.inArena(player.getLocation())) {
				player.sendMessage(message);
			}
		}
	}
}

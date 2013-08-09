/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.Quidditch;

import com.DanMan.main.BroomSticks;
import java.io.*;
import java.util.Scanner;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

/**
 *
 * @author DAY
 */
public class QArena {

    private BroomSticks plugin;
    private static File sFile = new File("plugins/BroomSticks/arenas.txt");
    private Location center;
    private String name;

    public QArena(String name, BroomSticks plugin) {
        this.plugin = plugin;
        this.name = name;
        this.center = loadCenter();
    }

    public void setAndSave(Location center) {
        this.center = center;
        try {
            sFile.createNewFile();
        } catch (IOException e) {
            System.err.println("Error: Could not create file due to illegal characters." + e);
        }
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(sFile, true)));
            out.println(name + ": " + center.getWorld().getName() + "," + center.getBlockX() + "," + center.getBlockZ());
            out.flush();
            out.close();
        } catch (Exception e) {
            System.err.println("Error: Could not write to file: " + e);
        }
    }

    public boolean arenaExists() {
        String data = "";
        if (sFile.exists()) {
            try {
                Scanner in = new Scanner(sFile);
                while (in.hasNextLine()) {
                    data = in.nextLine();
                    if (data.startsWith(name)) {
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

    private Location loadCenter() {
        String data = "";
        if (sFile.exists()) {
            try {
                Scanner in = new Scanner(sFile);
                while (in.hasNextLine()) {
                    data = in.nextLine();
                    if (data.startsWith(name)) {
                        break;
                    }
                }
                in.close();
            } catch (FileNotFoundException ex) {
                System.err.println("Error: Could not read from file: " + ex);
            }
            if (!data.equals("") && data.startsWith(name)) {
                data.replace(name + ": ", "");
                String[] coords = data.split(",");
                World w = Bukkit.getWorld(coords[0]);
                int x = Integer.parseInt(coords[1]);
                int z = Integer.parseInt(coords[2]);
                return new Location(w, x, 0, z);
            }
        }
        return null;
    }

    public Location getCenter() {
        return center;
    }

    public void deleteArena() {
        if (sFile.exists()) {
            StringBuilder nContent = new StringBuilder();
            try {
                Scanner in = new Scanner(sFile);
                String data;
                while (in.hasNextLine()) {
                    data = in.nextLine();
                    if (!data.startsWith(name)) {
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
            } catch (Exception e) {
                System.err.println("Error: Could not write to file: " + e);
            }
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRadius() {
        return plugin.getConfigLoader().getArenaRadius();
    }

    public static boolean inArena(Entity e, QArena qa) {
        //arena center location
        Location center = qa.getCenter();
        double x1 = center.getX();
        double z1 = center.getZ();

        //entity location
        Location loc = e.getLocation();
        double x2 = loc.getX();
        double z2 = loc.getZ();

        double distance = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((z2 - z1), 2));

        if (distance <= qa.getRadius()) {
            return true;
        } else {
            return false;
        }
    }
}

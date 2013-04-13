/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.main;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

/**
 *
 * @author DAY
 */
public class Brooms {
    
    private String name;
    private double speed;
    private ItemStack item;

    public Brooms(String name, double speed, ItemStack item) {
        this.name = name;
        this.speed = speed;
        this.item = item;
    }

    public ItemStack getItem() {
        return item;
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }
    
    private static Vector dir;

    public static void fly(Player player, double speed) {
        dir = player.getLocation().getDirection();
        dir.multiply(speed);
        dir.setY(dir.getY() + 0.3 * speed);
        player.setVelocity(dir);
        player.setFallDistance(0.0F);
    }

    public static void glide(Player player, double speed) {
        dir = player.getLocation().getDirection();
        dir.multiply(0.3 * speed);
        dir.setY(dir.getY() + 0.1 * speed);
        player.setVelocity(dir);
        player.setFallDistance(0.0F);
    }
}

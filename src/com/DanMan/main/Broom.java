/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.main;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Squid;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

/**
 *
 * @author DAY
 */
public class Broom {

    private String name;
    private double speed;
    private ItemStack item;
    private int durability;

    public Broom(String name, double speed, ItemStack item, int durability) {
        this.name = name;
        this.speed = speed;
        this.item = item;
        this.durability = durability;
    }

    public int getDurability() {
        return durability;
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

    public static Entity mount(Player player, int maxH) {
        Squid broom = (Squid) player.getWorld().spawnEntity(player.getLocation(), EntityType.SQUID);
        broom.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 0));
        broom.setMaxHealth(maxH);
        broom.setMaximumAir(1000);
        broom.setPassenger(player);
        return broom;
    }

    public static void dismount(Squid broom) {
        broom.eject();
        broom.remove();
    }

    public static void fly(Player player, Squid broom, double speed) {
        dir = player.getLocation().getDirection();
        dir.multiply(speed);
        dir.setY(dir.getY() + 0.2 * speed);
        broom.setVelocity(dir);
        broom.setFallDistance(0.0F);
        broom.setRemainingAir(1000);
    }

    public static void glide(Player player, Squid broom, double speed) {
        dir = player.getLocation().getDirection();
        dir.multiply(0.2 * speed);
        dir.setY(dir.getY() + 0.1 * speed);
        broom.setVelocity(dir);
        broom.setFallDistance(0.0F);
        broom.setRemainingAir(1000);
    }
}

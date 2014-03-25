/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.BroomSticks.main;

import com.DanMan.BroomSticks.utils.SNGMetaData;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
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

    public static Horse mount(Player player, int maxH) {
        Horse broom = (Horse) player.getWorld().spawnEntity(player.getLocation(), EntityType.HORSE);
        broom.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, 2147483647, 0));
        broom.setMaxHealth(maxH);
        broom.setHealth(maxH);
        broom.setOwner(player);
        broom.setPassenger(player);
        broom.getInventory().addItem(new ItemStack(Material.SADDLE));
        return broom;
    }

    public static void dismount(Horse broom, Player player, BroomSticks plugin) {
        int taskId = SNGMetaData.getIntMetadata(player, plugin);
        if (taskId != -1) {
            FlyTask.stopFlying(plugin, taskId);
            SNGMetaData.delMetaData(player, plugin);
            player.setVelocity(broom.getVelocity());
            broom.remove();
        }
    }

    public static void dismountAll(BroomSticks plugin) {
        for (Player player : Bukkit.getOnlinePlayers()) {
            int taskId = SNGMetaData.getIntMetadata(player, plugin);
            if (taskId != -1) {
                FlyTask.stopFlying(plugin, taskId);
                SNGMetaData.delMetaData(player, plugin);
                player.getVehicle().remove();
            }
        }
    }

    public static void fly(Player player, Horse broom, double speed) {
        dir = player.getLocation().getDirection();
        dir.multiply(speed);
        dir.setY(dir.getY() + 0.1 * speed);
        broom.setVelocity(dir);
        broom.setFallDistance(0.0F);
    }
//    public static void glide(Player player, Horse broom, double speed) {
//        dir = player.getLocation().getDirection();
//        dir.multiply(0.2 * speed);
//        dir.setY(dir.getY() + 0.1 * speed);
//        broom.setVelocity(dir);
//        broom.setFallDistance(0.0F);
//        broom.setRemainingAir(1000);
//    }
}

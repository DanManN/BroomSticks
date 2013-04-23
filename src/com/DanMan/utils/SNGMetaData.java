/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.utils;

import com.DanMan.main.Broom;
import java.util.List;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

/**
 *
 * @author DAY
 */
public class SNGMetaData {

    static final String keyInt = "BroomsInt";
    static final String keyItem = "BroomsItem";
    
    public static void setIntMetadata(Player player, int value, Plugin plug) {
        player.setMetadata(keyInt, new FixedMetadataValue(plug, value));
    }

    public static int getIntMetadata(Player player, Plugin plug) {
        List<MetadataValue> values = player.getMetadata(keyInt);
        for (MetadataValue value : values) {
            if (value.getOwningPlugin().getDescription().getName().equals(plug.getDescription().getName())) {
                return (int) value.value();
            }
        }
        return -1;
    }
    
    public static void setBroomItemMetadata(Player player, ItemStack value, Plugin plug) {
        player.setMetadata(keyItem, new FixedMetadataValue(plug, value));
    }
    
    public static ItemStack getBroomItemMetadata(Player player, Plugin plug) {
        List<MetadataValue> values = player.getMetadata(keyItem);
        for (MetadataValue value : values) {
            if (value.getOwningPlugin().getDescription().getName().equals(plug.getDescription().getName())) {
                return (ItemStack) value.value();
            }
        }
        return null;
    }
    
    public static void showMetadata(Player player, Plugin plug, String key) {
        List<MetadataValue> values = player.getMetadata(key);
        System.out.println("Values: " + values);
        for (MetadataValue value : values) {
           System.out.println("MetaData: " + value.value());
        }
    }
    
    public static void delMetaData(Player player, Plugin plug) {
        player.removeMetadata(keyInt, plug);
        player.removeMetadata(keyItem, plug);
    }
}

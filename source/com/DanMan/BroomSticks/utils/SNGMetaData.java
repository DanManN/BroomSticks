package com.DanMan.BroomSticks.utils;

import java.util.List;

import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.plugin.Plugin;

public class SNGMetaData
{
	static final String keyInt = "BroomsInt";
	static final String keyItem = "BroomsItem";
	static final String keyBat = "BroomsBat";

	public static void setIntMetadata(Player player, int value, Plugin plug)
	{
		player.setMetadata("BroomsInt", new FixedMetadataValue(plug, Integer.valueOf(value)));
	}

	public static int getIntMetadata(Player player, Plugin plug) {
		List<MetadataValue> values = player.getMetadata("BroomsInt");
		for (MetadataValue value : values) {
			if (value.getOwningPlugin().getDescription().getName().equals(plug.getDescription().getName())) {
				return value.asInt();
			}
		}
		return -1;
	}

	public static void setBroomItemMetadata(Player player, ItemStack value, Plugin plug) {
		player.setMetadata("BroomsItem", new FixedMetadataValue(plug, value));
	}

	public static ItemStack getBroomItemMetadata(Player player, Plugin plug) {
		List<MetadataValue> values = player.getMetadata("BroomsItem");
		for (MetadataValue value : values) {
			if (value.getOwningPlugin().getDescription().getName().equals(plug.getDescription().getName())) {
				return (ItemStack)value.value();
			}
		}
		return null;
	}

	public static void setBatMetadata(Bat b, int value, Plugin plug) { b.setMetadata("BroomsBat", new FixedMetadataValue(plug, Integer.valueOf(value))); }

	public static int getBatMetadata(Bat b, Plugin plug)
	{
		List<MetadataValue> values = b.getMetadata("BroomsBat");
		for (MetadataValue value : values) {
			if (value.getOwningPlugin().getDescription().getName().equals(plug.getDescription().getName())) {
				return value.asInt();
			}
		}
		return -1;
	}

	public static void showMetadata(Player player, Plugin plug, String key) {
		List<MetadataValue> values = player.getMetadata(key);
		System.out.println("Values: " + values);
		for (MetadataValue value : values) {
			System.out.println("MetaData: " + value.value());
		}
	}

	public static void delMetaData(Player player, Plugin plug) {
		player.removeMetadata("BroomsInt", plug);
		player.removeMetadata("BroomsItem", plug);
	}
}

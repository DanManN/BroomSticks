package com.DanMan.BroomSticks.main;

import com.DanMan.BroomSticks.utils.SNGMetaData;

import org.bukkit.Bukkit;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;





public class Broom
{
	private String name;
	private double speed;
	private ItemStack item;
	private int durability;
	private static Vector dir;

	public Broom(String name, double speed, ItemStack item, int durability)
	{
		this.name = name;
		this.speed = speed;
		this.item = item;
		this.durability = durability;
	}

	public int getDurability()
	{
		return this.durability;
	}

	public ItemStack getItem()
	{
		return this.item;
	}

	public String getName()
	{
		return this.name;
	}

	public double getSpeed()
	{
		return this.speed;
	}



	public static Entity mount(Player player, int maxH)
	{
		ArmorStand broom = (ArmorStand) player.getWorld().spawnEntity(player.getLocation(), EntityType.ARMOR_STAND);
		//broom.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Integer.MAX_VALUE, 0));
		//broom.addPotionEffect(new PotionEffect(PotionEffectType.LEVITATION, Integer.MAX_VALUE, 0));
		broom.setVisible(false);
		//broom.setGliding(true);
		//broom.setSaddle(true);
		//broom.setGliding(true);
		broom.setInvulnerable(true);
		//broom.setMaxHealth(maxH);
		//broom.setHealth(maxH);
		//broom.setMaximumAir(Integer.MAX_VALUE);
		//broom.setRemainingAir(Integer.MAX_VALUE);
		//broom.getInventory().addItem(new ItemStack[] { new ItemStack(Material.SADDLE) });
		//broom.setOwner(player);
		broom.setPassenger(player);
		return broom;
	}

	public static void dismount(Entity broom, Player player, double speed, BroomSticks plugin)
	{
		int taskId = SNGMetaData.getIntMetadata(player, plugin);

		if (taskId != -1) {
			FlyTask.stopFlying(plugin, taskId);
			SNGMetaData.delMetaData(player, plugin);
			broom.eject();
			broom.remove();
			dir = player.getLocation().getDirection();
			dir.multiply(speed);
			dir.setY(dir.getY() + 0.1D * speed);
			player.setVelocity(dir);
			// System.out.println("Dismounted: " + dir.toString());
		}
	}

	public static void dismountAll(BroomSticks plugin)
	{
		for (Player player : Bukkit.getServer().getOnlinePlayers()) {
			int taskId = SNGMetaData.getIntMetadata(player, plugin);
			if (taskId != -1) {
				FlyTask.stopFlying(plugin, taskId);
				SNGMetaData.delMetaData(player, plugin);
				player.getVehicle().remove();
			}
		}
	}

	public static void fly(Player player, Entity broom, double speed)
	{
		dir = player.getLocation().getDirection();
		dir.multiply(speed);
		dir.setY(dir.getY() + 0.1D * speed);
		broom.setVelocity(dir);
		broom.setFallDistance(0.0F);
	}
}

package com.DanMan.BroomSticks.Listeners;
import java.util.ArrayList;
import java.util.List;

import com.DanMan.BroomSticks.main.Broom;
import com.DanMan.BroomSticks.main.BroomSticks;
import com.DanMan.BroomSticks.main.ConfigLoader;
import com.DanMan.BroomSticks.main.FlyTask;
import com.DanMan.BroomSticks.utils.SNGMetaData;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.HorseInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public class BroomListener implements Listener
{
	private BroomSticks plugin;
	private ConfigLoader info;
	private ArrayList<Broom> broomStick;

	public BroomListener(BroomSticks plugin)
	{
		this.plugin = plugin;
		this.info = plugin.getConfigLoader();
		this.broomStick = this.info.getBrooms();
	}


	public boolean cbCheck(Block b)
	{
		if (b != null) {
			Material mb = b.getType();
			Plugin[] arrayOfPlugin; int j = (arrayOfPlugin = Bukkit.getPluginManager().getPlugins()).length; for (int i = 0; i < j; i++) { Plugin plug = arrayOfPlugin[i];
				if (plug.getName().equalsIgnoreCase("craftbook")) {
					Plugin cb = Bukkit.getServer().getPluginManager().getPlugin("CraftBook");
					if (cb.getConfig().getBoolean("mechanics.chair.enable")) {
						String[] blocks = null;
						List<String> sblocks = cb.getConfig().getStringList("mechanics.chair.blocks");
						if (sblocks != null)
							blocks = (String[])sblocks.toArray(new String[sblocks.size()]);
						String[] arrayOfString1;
						int m = (arrayOfString1 = blocks).length; for (int k = 0; k < m; k++) { String sb = arrayOfString1[k];
							if (mb.toString().equalsIgnoreCase(sb)) {
								return true;
							}
						}
					}
				}
			}
		}

		return false;
	}


	@EventHandler
	public void onUseBroom(PlayerInteractEvent evt)
	{
		if (cbCheck(evt.getClickedBlock())) {
			return;
		}

		ItemStack item = evt.getItem();
		if ((item != null) &&
				(item.getEnchantments().containsKey(Enchantment.ARROW_INFINITE)) && (
					(evt.getAction() == Action.RIGHT_CLICK_AIR) || (evt.getAction() == Action.RIGHT_CLICK_BLOCK)))
		{
			if (evt.getClickedBlock() != null) {
				Block b = evt.getClickedBlock();
				if (((b.getState() instanceof InventoryHolder)) ||
						(b.getType() == Material.DARK_OAK_DOOR) ||
						(b.getType() == Material.OAK_DOOR) ||
						(b.getType() == Material.BIRCH_DOOR) ||
						(b.getType() == Material.ACACIA_DOOR) ||
						(b.getType() == Material.JUNGLE_DOOR) ||
						(b.getType() == Material.SPRUCE_DOOR) ||
						(b.getType() == Material.DARK_OAK_TRAPDOOR) ||
						(b.getType() == Material.OAK_TRAPDOOR) ||
						(b.getType() == Material.BIRCH_TRAPDOOR) ||
						(b.getType() == Material.ACACIA_TRAPDOOR) ||
						(b.getType() == Material.JUNGLE_TRAPDOOR) ||
						(b.getType() == Material.SPRUCE_TRAPDOOR) ||
						(b.getType() == Material.OAK_FENCE_GATE) ||
						(b.getType() == Material.BIRCH_FENCE_GATE) ||
						(b.getType() == Material.ACACIA_FENCE_GATE) ||
						(b.getType() == Material.JUNGLE_FENCE_GATE) ||
						(b.getType() == Material.SPRUCE_FENCE_GATE) ||
						(b.getType() == Material.CRAFTING_TABLE) ||
						(b.getType() == Material.ANVIL) ||
						(b.getType() == Material.LEVER) ||
						(b.getType() == Material.BIRCH_BUTTON) ||
						(b.getType() == Material.OAK_BUTTON) ||
						(b.getType() == Material.STONE_BUTTON) ||
						(b.getType() == Material.ACACIA_BUTTON) ||
						(b.getType() == Material.JUNGLE_BUTTON) ||
						(b.getType() == Material.SPRUCE_BUTTON) ||
						(b.getType() == Material.DARK_OAK_BUTTON) ||
						(b.getType() == Material.REPEATER) ||
						(b.getType() == Material.COMPARATOR) ||
						(b.getType() == Material.ENCHANTING_TABLE)) {
							return;
						}
			}
			Player player = evt.getPlayer();

			int taskId = SNGMetaData.getIntMetadata(player, this.plugin);

			if (taskId == -1) {
				Material broomItem = item.getType();
				double sMult = 0.0D;
				int durability = 0;

				for (Broom bs : this.broomStick) {
					if (bs.getItem().getType() == broomItem) {
						sMult = bs.getSpeed();
						durability = bs.getDurability();
						break;
					}
				}
				if ((sMult == 0.0D) || (durability == 0)) {
					return;
				}
				if (player.hasPermission("broomsticks.ride"))
				{
					Entity broom = Broom.mount(player, durability);
					double speed = /*0.1D */ sMult*0.5D;
					taskId = FlyTask.flying(this.plugin, player, broom, speed);
					SNGMetaData.setIntMetadata(player, taskId, this.plugin);
					SNGMetaData.setBroomItemMetadata(player, item, this.plugin);
				} else {
					player.sendMessage(ChatColor.BLUE + "Dream all you want but only witches fly on brooms.");
				}
			}
			evt.setCancelled(true);
		}
	}

	@EventHandler
	public void onChangeSpeed(HorseJumpEvent evt)
	{
		Entity broom = evt.getEntity();
		Player player = (Player)broom.getPassenger();
		int taskId = SNGMetaData.getIntMetadata(player, this.plugin);
		ItemStack item = SNGMetaData.getBroomItemMetadata(player, this.plugin);
		if (taskId != -1) {
			FlyTask.stopFlying(this.plugin, taskId);
		}
		if (item != null) {
			Material broomItem = item.getType();
			double sMult = 0.0D;
			int durability = 0;

			for (Broom bs : this.broomStick) {
				if (bs.getItem().getType() == broomItem) {
					sMult = bs.getSpeed();
					durability = bs.getDurability();
					break;
				}
			}
			if ((sMult == 0.0D) || (durability == 0)) {
				return;
			}


			double power = (evt.getPower() / 0.4D - 1.0D) / 1.5D;

			double speed = power * sMult;

			taskId = FlyTask.flying(this.plugin, player, broom, speed);
			SNGMetaData.setIntMetadata(player, taskId, this.plugin);
		}
	}


	@EventHandler
	public void onSaddleClick(InventoryClickEvent evt)
	{
		HumanEntity he = evt.getWhoClicked();
		Inventory inv = evt.getInventory();
		if (((inv instanceof HorseInventory)) && ((he instanceof Player))) {
			Player p = (Player)he;
			if ((SNGMetaData.getIntMetadata(p, this.plugin) != -1) &&
					(evt.getSlot() == 0) &&
					(evt.getCurrentItem().getType() == Material.SADDLE)) {
				evt.setCancelled(true);
					}
		}
	}



	/*@EventHandler
	  public void onHorseDamaged(EntityDamageByEntityEvent evt)
	  {
	  Entity e = evt.getEntity();
	  if ((e instanceof Horse))
	  {
	  if ((e.getPassenger() != null) && ((e.getPassenger() instanceof Player)))
	  {
	  if ((evt.getCause() != EntityDamageEvent.DamageCause.ENTITY_EXPLOSION) ||
	  (evt.getCause() != EntityDamageEvent.DamageCause.FIRE) ||
	  (evt.getCause() != EntityDamageEvent.DamageCause.LAVA) ||
	  (evt.getCause() != EntityDamageEvent.DamageCause.FIRE_TICK) ||
	  (evt.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) ||
	  (evt.getCause() != EntityDamageEvent.DamageCause.VOID)) {
	  evt.setCancelled(true);
	  }
	  }
	  }
	  }*/

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent evt)
	{
		Player player = evt.getEntity();
		Entity broom = player.getVehicle();
		removeBroom(player, broom, this.plugin);
	}

	@EventHandler
	public void onPlayerDisconnect(PlayerQuitEvent evt)
	{
		Player player = evt.getPlayer();
		Entity broom = player.getVehicle();
		removeBroom(player, broom, this.plugin);
	}

	public static void removeBroom(Player player, Entity e, BroomSticks plugin)
	{
		//if ((e instanceof Horse)) {
		//Entity broom = e;
		Broom.dismount(e, player, 0.0D, plugin);
		//}
	}
}

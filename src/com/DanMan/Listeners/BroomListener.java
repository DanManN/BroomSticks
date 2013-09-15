/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.Listeners;

import com.DanMan.main.Broom;
import com.DanMan.main.BroomSticks;
import com.DanMan.main.ConfigLoader;
import com.DanMan.main.FlyTask;
import com.DanMan.utils.SNGMetaData;
import java.util.logging.Level;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author DAY
 */
public class BroomListener implements Listener {

    private BroomSticks plugin;
    private ConfigLoader info;
    private Broom[] broomStick;

    public BroomListener(BroomSticks plugin) {
        this.plugin = plugin;
        info = plugin.getConfigLoader();
        broomStick = info.getBrooms();
    }

    @EventHandler
    public void onUseBroom(PlayerInteractEvent evt) {
        ItemStack item = evt.getItem();
        if (item != null
                && item.getEnchantments().containsKey(Enchantment.ARROW_INFINITE)
                && (evt.getAction() == Action.RIGHT_CLICK_AIR || evt.getAction() == Action.RIGHT_CLICK_BLOCK)) {
            //prevent flying when opening block inventory
            if (evt.getClickedBlock() != null) {
                Block b = evt.getClickedBlock();
                if (b.getState() instanceof InventoryHolder
                        || b.getType() == Material.ENDER_CHEST
                        || b.getType() == Material.ANVIL
                        || b.getType() == Material.WORKBENCH
                        || b.getType() == Material.ENCHANTMENT_TABLE) {
                    return;
                }
            }
            Player player = evt.getPlayer();
            int taskId = SNGMetaData.getIntMetadata(player, plugin);
            //start or stop flying
            if (taskId == -1) {
                Material broomItem = item.getType();
                double sMult = 0;
                int durability = 0;
                //get config values
                for (Broom bs : broomStick) {
                    if (bs.getItem().getType() == broomItem) {
                        sMult = bs.getSpeed();
                        durability = bs.getDurability();
                        break;
                    }
                }
                if (sMult == 0 || durability == 0) {
                    return;
                }
                //start flying
                Horse broom = Broom.mount(player, durability);
                double speed = 0.2 * sMult;
                taskId = FlyTask.flying(plugin, player, broom, speed);
                SNGMetaData.setIntMetadata(player, taskId, plugin);
                SNGMetaData.setBroomItemMetadata(player, item, plugin);
            }
        }
    }

    @EventHandler
    public void onChangeSpeed(HorseJumpEvent evt) {
        Horse broom = evt.getEntity();
        Player player = (Player) broom.getPassenger();
        int taskId = SNGMetaData.getIntMetadata(player, plugin);
        ItemStack item = SNGMetaData.getBroomItemMetadata(player, plugin);
        if (taskId != -1) {
            FlyTask.stopFlying(plugin, taskId);
        }
        if (item != null) {
            Material broomItem = item.getType();
            double sMult = 0;
            int durability = 0;
            //get config values
            for (Broom bs : broomStick) {
                if (bs.getItem().getType() == broomItem) {
                    sMult = bs.getSpeed();
                    durability = bs.getDurability();
                    break;
                }
            }
            if (sMult == 0 || durability == 0) {
                return;
            }
            //restart flying
            double power = evt.getPower() < 0.5 ? evt.getPower() - 0.2 : evt.getPower();
            double speed = power * sMult;
            taskId = FlyTask.flying(plugin, player, broom, speed);
            SNGMetaData.setIntMetadata(player, taskId, plugin);
        }
    }

    @EventHandler
    public void onStopFlying(VehicleExitEvent evt) {
        if (evt.getVehicle() instanceof Horse && evt.getExited() instanceof Player) {
            Horse broom = (Horse) evt.getVehicle();
            Player player = (Player) evt.getExited();
            Broom.dismount(broom, player, plugin);
        }
    }

    public void onHorseDie(final EntityDeathEvent evt) {
        Entity e = evt.getEntity();
        if (e instanceof Horse) {
            if (e.getPassenger() != null && e.getPassenger() instanceof Player) {
                final Player player = (Player) e.getPassenger();
                int taskId = SNGMetaData.getIntMetadata(player, plugin);
                if (taskId != -1) {
                    ItemStack broom = SNGMetaData.getBroomItemMetadata(player, plugin);
                    FlyTask.stopFlying(plugin, taskId);
                    player.getInventory().clear(player.getInventory().first(broom));
                    SNGMetaData.delMetaData(player, plugin);
                    plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {

                        @Override
                        public void run() {
                            for (ItemStack item : evt.getDrops()) {
                                player.getInventory().remove(item);
                            }
                        }
                    }, 20);
                }
            }
        }
    }

    //prevent Horse from getting hurt
    @EventHandler
    public void onHorseDamaged(EntityDamageByEntityEvent evt) {
        Entity e = evt.getEntity();
        if (e instanceof Horse) {
            //Horse broom = (Horse) e;
            if (e.getPassenger() != null && e.getPassenger() instanceof Player) {
//                Player rider = (Player) e.getPassenger();
//                ItemStack item = rider.getItemInHand();
//                if (evt.getDamager() == rider) {
//                    if (item.getType() != Material.POTION) {
//                        item.setDurability((short) 0);
//                        rider.updateInventory();
//                    }
//                }
                if (evt.getCause() == DamageCause.ENTITY_ATTACK
                        || evt.getCause() == DamageCause.MAGIC
                        || evt.getCause() == DamageCause.POISON
                        || evt.getCause() == DamageCause.WITHER) {
                    evt.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent evt) {
        Player player = evt.getEntity();
        Entity broom = player.getVehicle();
        removeBroom(player, broom, plugin);
    }

    @EventHandler
    public void onPlayerDisconnect(PlayerQuitEvent evt) {
        Player player = evt.getPlayer();
        Entity broom = player.getVehicle();
        removeBroom(player, broom, plugin);
    }

    public static void removeBroom(Player player, Entity e, BroomSticks plugin) {;
        if (e instanceof Horse) {
            Horse broom = (Horse) e;
            Broom.dismount(broom, player, plugin);
        }
    }
}

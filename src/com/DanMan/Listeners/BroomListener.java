/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DanMan.Listeners;

import com.DanMan.main.BroomSticks;
import com.DanMan.main.Broom;
import com.DanMan.main.ConfigLoader;
import com.DanMan.main.FlyTask;
import com.DanMan.utils.SNLMetaData;
import java.util.logging.Level;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Squid;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
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

            Player player = evt.getPlayer();
            int taskId = SNLMetaData.getIntMetadata(player, plugin);

            if (taskId == -1) {
                Material broomItem = item.getType();
                double speed = 0;
                int durability = 0;
                //get config values
                for (Broom bs : broomStick) {
                    if (bs.getItem().getType() == broomItem) {
                        speed = bs.getSpeed();
                        durability = bs.getDurability();
                        break;
                    }
                }
                if (speed == 0 || durability == 0) {
                    return;
                }
                Squid broom = (Squid) Broom.mount(player, durability);
                taskId = FlyTask.flying(plugin, player, broom, speed);
                SNLMetaData.setIntMetadata(player, taskId, plugin);
                SNLMetaData.setBroomItemMetadata(player, item, plugin);
            } else {
                stopFlying(player, taskId);
            }
        }
    }

//    @EventHandler
//    public void onChangeBroom(PlayerItemHeldEvent evt) {
//        Player player = evt.getPlayer();
//        ItemStack item = player.getInventory().getItem(evt.getPreviousSlot());
//        if (item != null
//                && item.getEnchantments().containsKey(Enchantment.ARROW_INFINITE)) {
//            int taskId = SNLMetaData.getIntMetadata(player, plugin);
//            if (taskId != -1) {
//                stopFlying(player, taskId);
//            }
//        }
//    }
    public void stopFlying(Player player, int taskId) {
        Squid broom = (Squid) player.getVehicle();
        FlyTask.stopFlying(plugin, taskId);
        SNLMetaData.delMetaData(player, plugin);
        if (broom != null) {
            Broom.dismount(broom);
        } else {
            plugin.getLogger().log(Level.FINE, "BroomSticks Error: Null broom. Could not dismount broom because broom doesn't exist.");
        }
    }

    @EventHandler
    public void onSquidDie(final EntityDeathEvent evt) {
        Entity e = evt.getEntity();
        if (e instanceof Squid) {
            if (e.getPassenger() != null && e.getPassenger() instanceof Player) {
                final Player player = (Player) e.getPassenger();
                int taskId = SNLMetaData.getIntMetadata(player, plugin);
                ItemStack broom = SNLMetaData.getBroomItemMetadata(player, plugin);
                FlyTask.stopFlying(plugin, taskId);
                player.getInventory().clear(player.getInventory().first(broom));
                SNLMetaData.delMetaData(player, plugin);
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

    @EventHandler
    public void onSquidDamaged(EntityDamageByEntityEvent evt) {
        Entity e = evt.getEntity();
        if (e instanceof Squid) {
            Squid broom = (Squid) e;
            if (e.getPassenger() != null && e.getPassenger() instanceof Player) {
                Player rider = (Player) e.getPassenger();
                ItemStack item = rider.getItemInHand();
                if (evt.getDamager() == rider) {
                    if (item.getType() != Material.POTION) {
                        item.setDurability((short) 0);
                        rider.updateInventory();
                    }
                    evt.setCancelled(true);
                } else if (evt.getDamage() < broom.getMaxHealth()
                        && (evt.getCause() != DamageCause.FIRE || evt.getCause() != DamageCause.FIRE_TICK || evt.getCause() != DamageCause.LAVA)) {
                    evt.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent evt) {
        Player player = evt.getEntity();
        int taskId = SNLMetaData.getIntMetadata(player, plugin);
        FlyTask.stopFlying(plugin, taskId);
        SNLMetaData.delMetaData(player, plugin);
    }
}

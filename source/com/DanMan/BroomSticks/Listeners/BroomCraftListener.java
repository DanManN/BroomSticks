package com.DanMan.BroomSticks.Listeners;

import com.DanMan.BroomSticks.main.Broom;
import com.DanMan.BroomSticks.main.BroomSticks;
import com.DanMan.BroomSticks.main.ConfigLoader;
import java.util.ArrayList;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class BroomCraftListener
	implements Listener
{
	private ConfigLoader info;
	private ArrayList<Broom> broomStick;

	public BroomCraftListener(BroomSticks plugin)
	{
		this.info = plugin.getConfigLoader();
		this.broomStick = this.info.getBrooms();
	}

	@EventHandler
	public void onBroomEnchant(InventoryClickEvent evt)
	{
		if ((evt.getInventory() instanceof AnvilInventory)) {
			ItemStack item = evt.getCurrentItem();
			if (item != null) {
				Material broom = item.getType();
				if (item.containsEnchantment(Enchantment.ARROW_INFINITE)) {
					for (Broom bs : this.broomStick) {
						if (bs.getItem().getType() == broom) {
							ItemMeta meta = item.getItemMeta();
							if (meta.hasDisplayName()) break;
							meta.setDisplayName(bs.getName().replace("_", " "));
							item.setItemMeta(meta);

							break;
						}
					}
				}
			}
		}
	}
}

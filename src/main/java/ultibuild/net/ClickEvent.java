package ultibuild.net;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;

import java.util.ArrayList;

public class ClickEvent implements Listener {
    @EventHandler
    public void onclick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(p.getOpenInventory().getTitle().equalsIgnoreCase("ItemGUI")) {
            if(e.getClickedInventory() != null) {
                if (!e.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                    if (e.getCurrentItem() != null) {
                        if(e.getClick().equals(ClickType.LEFT)) {
                            p.getInventory().addItem(e.getCurrentItem());
                        } else if(e.getClick().equals(ClickType.MIDDLE)){
                            data d = new data();
                            ArrayList<ItemStack> items = (ArrayList<ItemStack>) d.getConfig().get("Items");
                            items.remove(e.getSlot());
                            d.getConfig().set("Items",items);
                            d.saveConfig();
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&aSuccessfully removed item!"));
                            p.performCommand("itemgui");
                        }
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}

package ultibuild.net;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;

public class ClickEvent implements Listener {
    @EventHandler
    public void onclick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(p.getOpenInventory().getTitle().equalsIgnoreCase("ItemGUI")) {
            if(e.getClickedInventory() != null) {
                if (!e.getClickedInventory().getType().equals(InventoryType.PLAYER)) {
                    if (e.getCurrentItem() != null) {
                        p.getInventory().addItem(e.getCurrentItem());
                        e.setCancelled(true);
                    }
                }
            }
        }
    }
}

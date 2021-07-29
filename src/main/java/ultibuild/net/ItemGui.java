package ultibuild.net;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.plugin.java.JavaPlugin;
import ultibuild.net.Commands.command;

import java.util.logging.Level;

public final class ItemGui extends JavaPlugin implements Listener {
    public static ItemGui instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("itemgui").setExecutor(new command());
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        this.getLogger().log(Level.INFO,"Successfully started ItemGUI");
        UpdateChecker.checkForUpdates();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ItemGui getInstance() {
        return instance;
    }

}

package ultibuild.net.Commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Cat;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import ultibuild.net.data;

import java.sql.BatchUpdateException;
import java.util.ArrayList;

public class command implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(label.equalsIgnoreCase("itemgui")) {
            if (args.length == 0) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    if(p.isOp() || p.hasPermission("itemgui.gui")) {
                        data d = new data();
                        if (d.getConfig().get("Items") != null) {
                            ArrayList<ItemStack> items = (ArrayList<ItemStack>) d.getConfig().get("Items");
                            Inventory inv = Bukkit.createInventory(null, 54, "ItemGUI");
                            for (int i = 0; i < items.size(); i++) {
                                inv.addItem(items.get(i));
                            }
                            p.openInventory(inv);
                        } else {
                            p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThere have been no saved items! Save an item first!"));
                        }
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cSorry you don't have permission for that!"));
                    }
                } else {
                    System.out.println("Command " + label + " can only be executed by a player");
                }
            } else if(args[0].equalsIgnoreCase("save")) {
                if (sender instanceof Player) {
                    Player p = (Player) sender;
                    if(p.isOp() || p.hasPermission("itemgui.save")) {
                        data d = new data();
                        if (d.getConfig().get("Items") != null) {
                            ArrayList<ItemStack> items = (ArrayList<ItemStack>) d.getConfig().get("Items");
                            if (!p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                                items.add(p.getInventory().getItemInMainHand());
                                d.getConfig().set("Items", items);
                                d.saveConfig();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully saved item!"));
                            } else {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlease hold an item to save!"));
                            }
                        } else {
                            ArrayList<ItemStack> items = new ArrayList<>();
                            if (!p.getInventory().getItemInMainHand().getType().equals(Material.AIR)) {
                                items.add(p.getInventory().getItemInMainHand());
                                d.getConfig().set("Items", items);
                                d.saveConfig();
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully saved item!"));
                            } else {
                                p.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cPlease hold an item to save!"));
                            }
                        }
                    } else {
                        p.sendMessage(ChatColor.translateAlternateColorCodes('&',"&cSorry you don't have permission for that!"));
                    }
                }
            }
        }
        return true;
    }
}
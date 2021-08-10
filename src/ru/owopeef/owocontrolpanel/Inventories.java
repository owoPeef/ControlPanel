package ru.owopeef.owocontrolpanel;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

public class Inventories
{
    static Plugin plugin = Main.getPlugin(Main.class);
    public static List<Inventory> inventories = new ArrayList<>();
    public static void createInventory(String inventoryName)
    {
        Inventory inv = plugin.getServer().createInventory(null, 9, inventoryName);
        ItemStack item = new ItemStack(Material.COMMAND);
        ItemMeta IM = item.getItemMeta();
        IM.setDisplayName(ChatColor.RED + "Ban " + inventoryName);
        item.setItemMeta(IM);
        inv.setItem(1, item);
        inventories.add(inv);
    }
}

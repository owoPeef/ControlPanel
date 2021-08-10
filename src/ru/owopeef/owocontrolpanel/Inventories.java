package ru.owopeef.owocontrolpanel;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import ru.owopeef.owocontrolpanel.utils.Config;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Inventories
{
    static Plugin plugin = Main.getPlugin(Main.class);
    public static List<Inventory> inventories = new ArrayList<>();
    public static void createInventory(String inventoryName)
    {
        int a = 0;
        Inventory inv = plugin.getServer().createInventory(null, 9, inventoryName);
        while (true)
        {
            String title = Config.readConfigWithException(String.valueOf(a), "title");
            String command = Config.readConfigWithException(String.valueOf(a), "command");
            String slot = Config.readConfigWithException(String.valueOf(a), "slot");
            String material = Config.readConfigWithException(String.valueOf(a), "material");
            if (Objects.equals(title, "break") || Objects.equals(command, "break") || Objects.equals(slot, "break") || Objects.equals(material, "material"))
            {
                break;
            }
            try
            {
                ItemStack item = new ItemStack(Material.getMaterial(material.toUpperCase()));
                ItemMeta IM = item.getItemMeta();
                IM.setDisplayName(title.replace("{player_name}", inventoryName));
                item.setItemMeta(IM);
                inv.setItem(Integer.parseInt(slot), item);
            }
            catch (NullPointerException e)
            {
                int itemNumber = a + 1;
                plugin.getLogger().warning("Material " + material + " not found, item number " + itemNumber + " skipped.");
            }
            a++;
        }
        inventories.add(inv);
    }
}

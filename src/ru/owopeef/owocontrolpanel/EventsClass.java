package ru.owopeef.owocontrolpanel;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

import java.util.List;
import java.util.Objects;

public class EventsClass implements Listener
{
    static Plugin plugin = Main.getPlugin(Main.class);
    Player currentPlayer;
    @EventHandler
    public void InventoryClick(InventoryClickEvent event)
    {
        Player player = (Player) event.getWhoClicked();
        Inventory open = event.getClickedInventory();
        ItemStack item = event.getCurrentItem();
        int a = 0;
        int b = 0;
        List<Inventory> inventories;
        List<Player> players = plugin.getServer().getWorld("world").getPlayers();
        while (a != players.size())
        {
            inventories = Inventories.inventories;
            currentPlayer = players.get(a);
            if (open == null) { return; }
            if (open.getName().equals("Control Panel"))
            {
                event.setCancelled(true);
                if (item == null || !item.hasItemMeta())
                {
                    return;
                }
                if (inventories.size() == 0)
                {
                    Inventories.createInventory(item.getItemMeta().getDisplayName());
                    player.openInventory(inventories.get(a));
                }
                else
                {
                    while (b != inventories.size())
                    {
                        if (Objects.equals(inventories.get(b).getTitle(), item.getItemMeta().getDisplayName()))
                        {
                            player.openInventory(inventories.get(b));
                        }
                        b++;
                    }
                }
            }
            a++;
        }
    }
}

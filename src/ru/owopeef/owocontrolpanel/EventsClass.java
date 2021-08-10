package ru.owopeef.owocontrolpanel;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import ru.owopeef.owocontrolpanel.utils.Config;

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
        int c = 0;
        int d = 0;
        List<Inventory> inventories;
        List<Player> players = plugin.getServer().getWorld("world").getPlayers();
        while (a != players.size())
        {
            inventories = Inventories.inventories;
            currentPlayer = players.get(a);
            if (open == null) { return; }
            String menuTitle = Config.readConfig("menu_title");
            if (open.getName().equals(menuTitle))
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
            while (c != inventories.size())
            {
                if (open.getName().equals(inventories.get(c).getTitle()))
                {
                    event.setCancelled(true);
                    if (item == null || !item.hasItemMeta())
                    {
                        return;
                    }
                    while (true)
                    {
                        String title = Config.readConfigWithException(String.valueOf(d), "title");
                        String command = Config.readConfigWithException(String.valueOf(d), "command");
                        if (Objects.equals(title, "break") || Objects.equals(command, "break"))
                        {
                            break;
                        }
                        String titleSplit = title.split(" ")[0];
                        String nick = inventories.get(c).getTitle().substring(2);
                        Player player1 = Bukkit.getPlayer(nick);
                        if (item.getItemMeta().getDisplayName().startsWith(titleSplit))
                        {
                            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replace("{player_name}", player1.getName()));
                        }
                        d++;
                    }
                }
                c++;
            }
            a++;
        }
    }
}

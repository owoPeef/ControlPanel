package ru.owopeef.owocontrolpanel.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.Plugin;
import ru.owopeef.owocontrolpanel.Inventories;
import ru.owopeef.owocontrolpanel.Main;

import java.util.ArrayList;
import java.util.List;

public class Commands implements CommandExecutor
{
    Plugin plugin = Main.getPlugin(Main.class);
    public static List<ItemStack> items = new ArrayList<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) { return true; }
        Player player = (Player) sender;
        if (cmd.getName().equalsIgnoreCase("control_panel"))
        {
            Inventory control_panel = plugin.getServer().createInventory(null, 9 * 6, "Control Panel");
            items.clear();
            List<Player> players = plugin.getServer().getWorld("world").getPlayers();
            int a = 0;
            while (a != players.size())
            {
                Player currentPlayer = players.get(a).getPlayer();
                Inventories.createInventory("§a" + currentPlayer.getName());
                ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
                SkullMeta skullMeta = (SkullMeta) is.getItemMeta();
                skullMeta.setOwner(players.get(a).getName());
                skullMeta.setDisplayName("§a" + players.get(a).getName());
                List<String> loreList = new ArrayList<>();
                loreList.add("§cPosition: " + currentPlayer.getLocation().getBlockX() + " " + currentPlayer.getLocation().getBlockY() + " " + currentPlayer.getLocation().getBlockZ() + "\nLevel: " + currentPlayer.getLevel());
                skullMeta.setLore(loreList);
                is.setItemMeta(skullMeta);
                items.add(is);
                a++;
            }
            int b = 0;
            while (b != items.size())
            {
                control_panel.setItem(b+1, items.get(b));
                b++;
            }
            player.openInventory(control_panel);
        }
        return true;
    }
}

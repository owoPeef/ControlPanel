package ru.owopeef.owocontrolpanel;

import org.bukkit.plugin.java.JavaPlugin;
import ru.owopeef.owocontrolpanel.commands.Commands;

public class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        getServer().getPluginManager().registerEvents(new EventsClass(), this);
        getCommand("control_panel").setExecutor(new Commands());
    }
}

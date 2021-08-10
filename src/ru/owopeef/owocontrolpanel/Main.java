package ru.owopeef.owocontrolpanel;

import org.bukkit.plugin.java.JavaPlugin;
import ru.owopeef.owocontrolpanel.commands.Commands;
import ru.owopeef.owocontrolpanel.utils.Config;

public class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        Config.loadConfig();
        getServer().getPluginManager().registerEvents(new EventsClass(), this);
        getCommand("control_panel").setExecutor(new Commands());
    }
}

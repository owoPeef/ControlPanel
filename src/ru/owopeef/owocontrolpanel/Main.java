package ru.owopeef.owocontrolpanel;

import org.bukkit.plugin.java.JavaPlugin;
import ru.owopeef.owocontrolpanel.commands.Commands;

public class Main extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        getCommand("control_panel").setExecutor(new Commands());
    }
}

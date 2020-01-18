package fr.dwightstudio.skyfactions;

import brian.menuinterface.MenuListener;
import fr.dwightstudio.skyfactions.Configs.Loader;
import fr.dwightstudio.skyfactions.Listeners.BlocksProtectionListener;
import fr.dwightstudio.skyfactions.Listeners.ChatInputListener;
import fr.dwightstudio.skyfactions.Listeners.SetPlayerProfileListener;
import fr.dwightstudio.skyfactions.CommandExecutors.CommandSkyFaction;
import fr.dwightstudio.skyfactions.CommandExecutors.CommandFaction;
import fr.dwightstudio.skyfactions.TabCompleters.TabCompleterFaction;
import fr.dwightstudio.skyfactions.TabCompleters.TabCompleterSkyFaction;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static final String logPrefix = "[SkyFactions] ";
    public static final String messagePrefix = ChatColor.DARK_GREEN + "[SkyFactions] " + ChatColor.RESET;
    public static Loader loader;
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {

        plugin = this;
        MenuListener.register(plugin);

        // Configs
        loader = new Loader();

        // Listeners
        getServer().getPluginManager().registerEvents(new BlocksProtectionListener(), this);
        getServer().getPluginManager().registerEvents(new ChatInputListener(), this);
        getServer().getPluginManager().registerEvents(new SetPlayerProfileListener(), this);

        // Commands
        getCommand("faction").setExecutor(new CommandFaction());
        getCommand("faction").setTabCompleter(new TabCompleterFaction());

        getCommand("skyfaction").setExecutor(new CommandSkyFaction());
        getCommand("skyfaction").setTabCompleter(new TabCompleterSkyFaction());
    }

    @Override
    public void onDisable() {
        loader.close();
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}

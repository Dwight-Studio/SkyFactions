package fr.skynnotopia.skyfactions;

import fr.skynnotopia.skyfactions.Configs.Loader;
import fr.skynnotopia.skyfactions.Listeners.BlocksProtection;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    public static String logPrefix = "[SkyFactions] ";
    public static String messagePrefix = ChatColor.DARK_GREEN + "[SkyFactions] " + ChatColor.RESET;
    public static Loader loader;
    public static JavaPlugin plugin;

    @Override
    public void onEnable() {

        plugin = this;

        // Configs
        loader = new Loader();

        // Listeners
        getServer().getPluginManager().registerEvents(new BlocksProtection(), this);
    }

    @Override
    public void onDisable() {
        loader.close();
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }
}

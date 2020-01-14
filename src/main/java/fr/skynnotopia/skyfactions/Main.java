package fr.skynnotopia.skyfactions;

import brian.menuinterface.MenuListener;
import fr.skynnotopia.skyfactions.CommandExecutors.CommandSkyFaction;
import fr.skynnotopia.skyfactions.CommandExecutors.CommandFaction;
import fr.skynnotopia.skyfactions.CommandExecutors.CommandFactions;
import fr.skynnotopia.skyfactions.Configs.Loader;
import fr.skynnotopia.skyfactions.Listeners.BlocksProtectionListener;
import fr.skynnotopia.skyfactions.Listeners.ChatInputListener;
import fr.skynnotopia.skyfactions.Listeners.SetPlayerProfileListener;
import fr.skynnotopia.skyfactions.TabCompleters.TabCompleterFaction;
import fr.skynnotopia.skyfactions.TabCompleters.TabCompleterFactions;
import fr.skynnotopia.skyfactions.TabCompleters.TabCompleterSkyFaction;
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

        getCommand("factions").setExecutor(new CommandFactions());
        getCommand("factions").setTabCompleter(new TabCompleterFactions());

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

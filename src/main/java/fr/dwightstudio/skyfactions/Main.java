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
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    public static final String logPrefix = "[SkyFactions] ";
    public static final String messagePrefix = ChatColor.DARK_GREEN + "[SkyFactions] " + ChatColor.RESET;
    public static Loader loader;
    public static JavaPlugin plugin;
    public static List<Player> mapPlayerList;

    @Override
    public void onEnable() {

        plugin = this;
        MenuListener.register(plugin);

        //Configs
        loader = new Loader();

        //Listeners
        getServer().getPluginManager().registerEvents(new BlocksProtectionListener(), this);
        getServer().getPluginManager().registerEvents(new ChatInputListener(), this);
        getServer().getPluginManager().registerEvents(new SetPlayerProfileListener(), this);

        // Commands
        getCommand("faction").setExecutor(new CommandFaction());
        getCommand("faction").setTabCompleter(new TabCompleterFaction());

        getCommand("skyfaction").setExecutor(new CommandSkyFaction());
        getCommand("skyfaction").setTabCompleter(new TabCompleterSkyFaction());

        getCommand("factionchat").setExecutor(new CommandFactionChat());
        getCommand("factionchat").setTabCompleter(new TabCompleterFactionChat());

        //Map
        mapPlayerList = new ArrayList<Player>();
        new BukkitRunnable() {
            @Override
            public void run() {
                mapNotification();
            }
        }.runTaskTimer(this, 40, 40);
    }

    @Override
    public void onDisable() {
        loader.close();
    }

    public static JavaPlugin getPlugin() {
        return plugin;
    }

    public static void mapNotification() {
        for (Player player : mapPlayerList) {
            try {
                player.sendMessage("");
                //TODO: Faire la map
            } catch (NullPointerException exception) {
                continue;
            }
        }
    }
}

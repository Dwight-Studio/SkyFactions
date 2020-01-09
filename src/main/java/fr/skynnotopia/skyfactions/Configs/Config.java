package fr.skynnotopia.skyfactions.Configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;

import static fr.skynnotopia.skyfactions.Main.getPlugin;
import static fr.skynnotopia.skyfactions.Main.logPrefix;

public class Config {

    private static File territoriesConfigFile;
    private static FileConfiguration territoriesConfig;

    private static File factionsConfigFile;
    private static FileConfiguration factionsConfig;

    private static File playersConfigFile;
    private static FileConfiguration playersConfig;

    public Config() {
        createCustomConfigs();
    }

    // Default

    public static boolean defaultConfigs_getBoolean(String s) throws NullPointerException {
        return getPlugin().getConfig().getBoolean(s);
    }

    public static int defaultConfigs_getInt(String s) throws NullPointerException {
        return getPlugin().getConfig().getInt(s);
    }

    public static String defaultConfigs_getString(String s) throws NullPointerException {
        return getPlugin().getConfig().getString(s);
    }

    public static java.util.List<String> defaultConfigs_getStringList(String s) throws NullPointerException {
        return getPlugin().getConfig().getStringList(s);
    }

    public static void defaultConfigs_set(String s, Object o) throws NullPointerException {
        getPlugin().getConfig().set(s, o);
        getPlugin().saveConfig();
    }

    // Factions

    public static boolean factionsConfig_getBoolean(String s) throws NullPointerException {
        return factionsConfig.getBoolean(s);
    }

    public static int factionsConfig_getInt(String s) throws NullPointerException {
        return factionsConfig.getInt(s);
    }

    public static String factionsConfig_getString(String s) throws NullPointerException {
        return factionsConfig.getString(s);
    }

    public static java.util.List<String> factionsConfig_getStringList(String s) throws NullPointerException {
        return factionsConfig.getStringList(s);
    }

    public static void factionsConfig_set(String s, Object o) throws NullPointerException {
        factionsConfig.set(s, o);
        getPlugin().saveConfig();
    }

    // Territories

    public static boolean territoriesConfig_getBoolean(String s) throws NullPointerException {
        return territoriesConfig.getBoolean(s);
    }

    public static int territoriesConfig_getInt(String s) throws NullPointerException {
        return territoriesConfig.getInt(s);
    }

    public static String territoriesConfig_getString(String s) throws NullPointerException {
        return territoriesConfig.getString(s);
    }

    public static java.util.List<String> territoriesConfig_getStringList(String s) throws NullPointerException {
        return territoriesConfig.getStringList(s);
    }

    public static void territoriesConfig_set(String s, Object o) throws NullPointerException {
        territoriesConfig.set(s, o);
        getPlugin().saveConfig();
    }
    
    // Players

    public static boolean playersConfig_getBoolean(String s) throws NullPointerException {
        return playersConfig.getBoolean(s);
    }

    public static int playersConfig_getInt(String s) throws NullPointerException {
        return playersConfig.getInt(s);
    }

    public static String playersConfig_getString(String s) throws NullPointerException {
        return playersConfig.getString(s);
    }

    public static java.util.List<String> playersConfig_getStringList(String s) throws NullPointerException {
        return playersConfig.getStringList(s);
    }

    public static void playersConfig_set(String s, Object o) throws NullPointerException {
        playersConfig.set(s, o);
        getPlugin().saveConfig();
    }
    
    public static boolean reload() {
        boolean r = true;
        try {
            getPlugin().reloadConfig();
            createCustomConfigs();
        } catch (Exception e) {
            Bukkit.getLogger().log(Level.SEVERE,logPrefix + e.getMessage());
            r = false;
        }
        return r;
    }

    private static void createCustomConfigs() {
        Bukkit.getLogger().log(Level.ALL, "" + getPlugin().getDataFolder() + " territories.yml");
        territoriesConfigFile = new File(getPlugin().getDataFolder(), "territories.yml");
        if (!territoriesConfigFile.exists()) {
            territoriesConfigFile.getParentFile().mkdirs();
            getPlugin().saveResource("territories.yml", false);
        }

        factionsConfigFile = new File(getPlugin().getDataFolder(), "factions.yml");
        if (!factionsConfigFile.exists()) {
            factionsConfigFile.getParentFile().mkdirs();
            getPlugin().saveResource("factions.yml", false);
        }

        playersConfigFile = new File(getPlugin().getDataFolder(), "players.yml");
        if (!playersConfigFile.exists()) {
            playersConfigFile.getParentFile().mkdirs();
            getPlugin().saveResource("players.yml", false);
        }

        territoriesConfig = new YamlConfiguration();
        factionsConfig = new YamlConfiguration();
        playersConfig = new YamlConfiguration();

        try {
            playersConfig.load(playersConfigFile);
            territoriesConfig.load(territoriesConfigFile);
            factionsConfig.load(factionsConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


}
package fr.dwightstudio.skyfactions.Configs;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Set;
import java.util.logging.Level;

import static fr.dwightstudio.skyfactions.Main.getPlugin;
import static fr.dwightstudio.skyfactions.Main.logPrefix;

public class Config {

    private static File territoriesConfigFile;
    private static FileConfiguration territoriesConfig;

    private static File factionsConfigFile;
    private static FileConfiguration factionsConfig;

    private static File playersConfigFile;
    private static FileConfiguration playersConfig;

    private static File relationsConfigFile;
    private static FileConfiguration relationsConfig;

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

    public static Set<String> defaultConfigs_getConfigSections(String s) throws NullPointerException{
        return getPlugin().getConfig().getConfigurationSection(s).getKeys(false);
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

    public static Set<String> factionsConfig_getConfigSections(String s) throws NullPointerException{
        return factionsConfig.getConfigurationSection(s).getKeys(false);
    }

    public static void factionsConfig_set(String s, Object o) throws NullPointerException, IOException {
        factionsConfig.set(s, o);
        factionsConfig.save(factionsConfigFile);
    }

    // Factions

    public static boolean relationsConfig_getBoolean(String s) throws NullPointerException {
        return relationsConfig.getBoolean(s);
    }

    public static int relationsConfig_getInt(String s) throws NullPointerException {
        return relationsConfig.getInt(s);
    }

    public static String relationsConfig_getString(String s) throws NullPointerException {
        return relationsConfig.getString(s);
    }

    public static java.util.List<String> relationsConfig_getStringList(String s) throws NullPointerException {
        return relationsConfig.getStringList(s);
    }

    public static Set<String> relationsConfig_getConfigSections(String s) throws NullPointerException{
        return relationsConfig.getConfigurationSection(s).getKeys(false);
    }

    public static void relationsConfig_set(String s, Object o) throws NullPointerException, IOException {
        relationsConfig.set(s, o);
        relationsConfig.save(relationsConfigFile);
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

    public static Set<String> territoriesConfig_getConfigSections(String s) throws NullPointerException{
        return territoriesConfig.getConfigurationSection(s).getKeys(false);
    }

    public static void territoriesConfig_set(String s, Object o) throws NullPointerException, IOException {
        territoriesConfig.set(s, o);
        territoriesConfig.save(territoriesConfigFile);
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

    public static Set<String> playersConfig_getConfigSections(String s) throws NullPointerException{
        return playersConfig.getConfigurationSection(s).getKeys(false);
    }

    public static void playersConfig_set(String s, Object o) throws NullPointerException, IOException {
        playersConfig.set(s, o);
        playersConfig.save(playersConfigFile);
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

        relationsConfigFile = new File(getPlugin().getDataFolder(), "relations.yml");
        if (!relationsConfigFile.exists()) {
            relationsConfigFile.getParentFile().mkdirs();
            getPlugin().saveResource("relations.yml", false);
        }

        playersConfigFile = new File(getPlugin().getDataFolder(), "players.yml");
        if (!playersConfigFile.exists()) {
            playersConfigFile.getParentFile().mkdirs();
            getPlugin().saveResource("players.yml", false);
        }

        territoriesConfig = new YamlConfiguration();
        factionsConfig = new YamlConfiguration();
        relationsConfig = new YamlConfiguration();
        playersConfig = new YamlConfiguration();

        try {
            playersConfig.load(playersConfigFile);
            territoriesConfig.load(territoriesConfigFile);
            factionsConfig.load(factionsConfigFile);
            relationsConfig.load(relationsConfigFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }


}
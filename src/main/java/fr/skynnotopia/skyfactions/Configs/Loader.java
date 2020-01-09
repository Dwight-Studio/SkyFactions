package fr.skynnotopia.skyfactions.Configs;

import fr.skynnotopia.skyfactions.Objects.Faction;
import fr.skynnotopia.skyfactions.Objects.PlayerProfile;
import fr.skynnotopia.skyfactions.Objects.Territory;
import fr.skynnotopia.skyfactions.Objects.TerritoryChunk;
import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import static fr.skynnotopia.skyfactions.Main.getPlugin;
import static fr.skynnotopia.skyfactions.Main.logPrefix;

public class Loader {

    public static Config config;

    public Territory getTerritory(UUID uuid) {
        for (Territory t : territories) {
            if (t.getUUID().equals(uuid)) {
                return t;
            }
        }
        return null;
    }

    public Faction getFaction(UUID uuid) {
        for (Faction f : factions) {
            if (f.getUUID().equals(uuid)) {
                return f;
            }
        }
        return null;
    }

    private List<Territory> territories = new ArrayList<Territory>();
    private List<Faction> factions = new ArrayList<Faction>();
    private List<PlayerProfile> players = new ArrayList<PlayerProfile>();

    public Loader() {
        config = new Config();

        Bukkit.getLogger().log(Level.INFO, logPrefix + "--- SkyFaction Configs Loader ---");
        try {

            // Territories
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 1 : Loading Territories");
            List<String> territoriesUUIDs = Config.factionsConfig_getStringList("activeTerritories");
            for (int i = 0; i != territoriesUUIDs.size(); i++) {
                String uuid = territoriesUUIDs.get(i);

                Location base = new Location(Bukkit.getWorld(UUID.fromString(
                        Config.territoriesConfig_getString("territories." + uuid + ".base.world"))),
                        (long) Config.territoriesConfig_getInt("territories." + uuid + ".base.x"),
                        (long) Config.territoriesConfig_getInt("territories." + uuid + ".base.y"),
                        (long) Config.territoriesConfig_getInt("territories." + uuid + ".base.z")
                );

                Location vault = new Location(Bukkit.getWorld(UUID.fromString(Config.territoriesConfig_getString("territories." + uuid + ".vault.world"))),
                        (long) Config.territoriesConfig_getInt("territories." + uuid + ".vault.x"),
                        (long) Config.territoriesConfig_getInt("territories." + uuid + ".vault.y"),
                        (long) Config.territoriesConfig_getInt("territories." + uuid + ".vault.z"));

                int lastChunkNumber = Config.territoriesConfig_getInt("territories." + uuid + ".lastChunkNumber");

                List<TerritoryChunk> chunks = new ArrayList<TerritoryChunk>();

                for (int e = 0 ; lastChunkNumber != e; e++) {
                    chunks.add(new TerritoryChunk(base.getWorld().getChunkAt(
                            Config.territoriesConfig_getInt("territories." + uuid + ".chunks." + e + ".x"),
                            Config.territoriesConfig_getInt("territories." + uuid + ".chunks." + e + ".z"))
                            ,e));
                }

                territories.add(new Territory(UUID.fromString(uuid), chunks, lastChunkNumber, base, vault));

                Bukkit.getLogger().log(Level.INFO, logPrefix + uuid + " has been successfully loaded. (" + i+1 + "/" + territoriesUUIDs.size() + ")");

            }

            // Factions
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 2 : Loading Factions");
            List<String> factionsUUIDs = Config.factionsConfig_getStringList("activeFactions");
            for (int i = 0; i != factionsUUIDs.size(); i++) {
                String uuid = factionsUUIDs.get(i);
                String name = Config.factionsConfig_getString("factions." + uuid + ".name");
                int reputationPoints = Config.factionsConfig_getInt("factions." + uuid + ".reputationPoints");
                int influencePoints  = Config.factionsConfig_getInt("factions." + uuid + ".influencePoints");
                UUID chief = UUID.fromString(Config.factionsConfig_getString("factions." + uuid + ".chief"));

                List<UUID> officers = new ArrayList<UUID>();
                for (String u : Config.factionsConfig_getStringList("factions." + uuid + ".officers")) {
                    officers.add(UUID.fromString(u));
                }

                List<UUID> members = new ArrayList<UUID>();
                for (String u : Config.factionsConfig_getStringList("factions." + uuid + ".members")) {
                    officers.add(UUID.fromString(u));
                }


                Territory territory = getTerritory(UUID.fromString(Config.factionsConfig_getString("factions." + uuid + ".territory")));

                factions.add(new Faction(UUID.fromString(uuid), name, reputationPoints, influencePoints, chief, officers, members, territory));

                Bukkit.getLogger().log(Level.INFO, logPrefix + uuid + " has been successfully loaded. (" + i+1 + "/" + factionsUUIDs.size() + ")");
            }

            // Players
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 3 : Loading Players");
            List<String> playersUUID = Config.playersConfig_getStringList("activePlayers");
            for (int i = 0; i != playersUUID.size(); i++) {
                String uuid = playersUUID.get(i);
                boolean isChief = Config.playersConfig_getBoolean("players." + uuid + ".isChief");
                Faction faction = getFaction(UUID.fromString(Config.playersConfig_getString("players." + uuid + ".faction")));
                players.add(new PlayerProfile(UUID.fromString(uuid), faction, isChief));

                Bukkit.getLogger().log(Level.INFO, logPrefix + uuid + " has been successfully loaded. (" + i+1 + "/" + playersUUID.size() + ")");
            }

            Bukkit.getLogger().log(Level.INFO, logPrefix + "Done.");


        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().log(Level.SEVERE, logPrefix + "Can't load configs, disabling.");
            Bukkit.getServer().getPluginManager().disablePlugin(getPlugin());
        }

        Bukkit.getLogger().log(Level.INFO, logPrefix + "--- SkyFaction Configs Loader ---");
    }

    public void reload() {

    }
}

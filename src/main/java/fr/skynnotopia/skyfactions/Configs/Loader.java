package fr.skynnotopia.skyfactions.Configs;

import fr.skynnotopia.skyfactions.Objects.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import static fr.skynnotopia.skyfactions.Main.getPlugin;
import static fr.skynnotopia.skyfactions.Main.logPrefix;

public class Loader {

    private int retryNumber = 0;
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

    public PlayerProfile getPlayerProfile(UUID uuid) {
        for (PlayerProfile p : players) {
            if (p.getUUID().equals(uuid)) {
                return p;
            }
        }
        return null;
    }

    public List<Territory> territories = new ArrayList<Territory>();
    public List<Faction> factions = new ArrayList<Faction>();
    public List<PlayerProfile> players = new ArrayList<PlayerProfile>();

    public Loader() {
        config = new Config();

        Bukkit.getLogger().log(Level.INFO, logPrefix + "--- SkyFaction Configs Loader ---");
        Bukkit.getLogger().log(Level.INFO, logPrefix + "## Loading protocol ##");
        try {

            // Territories
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 1 : Loading Territories");
            List<String> territoriesUUIDs = Config.territoriesConfig_getStringList("activeTerritories");
            for (int i = 0; i != territoriesUUIDs.size(); i++) {
                String uuid = territoriesUUIDs.get(i);
                Location base;
                try {
                    base = new Location(Bukkit.getWorld(UUID.fromString(
                            Config.territoriesConfig_getString("territories." + uuid + ".base.world"))),
                            (long) Config.territoriesConfig_getInt("territories." + uuid + ".base.x"),
                            (long) Config.territoriesConfig_getInt("territories." + uuid + ".base.y"),
                            (long) Config.territoriesConfig_getInt("territories." + uuid + ".base.z")
                    );
                } catch (NullPointerException e) {
                    base = null;
                }

                Location vault;
                try {
                vault = new Location(Bukkit.getWorld(UUID.fromString(Config.territoriesConfig_getString("territories." + uuid + ".vault.world"))),
                        (long) Config.territoriesConfig_getInt("territories." + uuid + ".vault.x"),
                        (long) Config.territoriesConfig_getInt("territories." + uuid + ".vault.y"),
                        (long) Config.territoriesConfig_getInt("territories." + uuid + ".vault.z"));
                } catch (NullPointerException e) {
                    vault = null;
                }

                int lastChunkNumber = Config.territoriesConfig_getInt("territories." + uuid + ".lastChunkNumber");

                List<TerritoryChunk> chunks = new ArrayList<TerritoryChunk>();

                for (int e = 0 ; lastChunkNumber != e; e++) {
                    chunks.add(new TerritoryChunk(base.getWorld().getChunkAt(
                            Config.territoriesConfig_getInt("territories." + uuid + ".chunks." + e + ".x"),
                            Config.territoriesConfig_getInt("territories." + uuid + ".chunks." + e + ".z"))
                            ,e));
                }

                territories.add(new Territory(UUID.fromString(uuid), chunks, lastChunkNumber, base, vault));

                Bukkit.getLogger().log(Level.INFO, logPrefix + uuid + " has been successfully loaded. (" + (i+1) + "/" + territoriesUUIDs.size() + ")");

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


                Territory territory;

                try {
                    territory = getTerritory(UUID.fromString(Config.factionsConfig_getString("factions." + uuid + ".territory")));
                } catch (NullPointerException e) {
                    territory = null;
                }


                factions.add(new Faction(UUID.fromString(uuid), name, null, reputationPoints, influencePoints, chief, officers, members, territory, false));

                Bukkit.getLogger().log(Level.INFO, logPrefix + uuid + " has been successfully loaded. (" + (i+1) + "/" + factionsUUIDs.size() + ")");
            }

            // Relations
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 3 : Loading Relations");
            List<Relation> relations = new ArrayList<Relation>();
            List<Faction> tempFactions = new ArrayList<Faction>();
            tempFactions.addAll(factions);
            for (Faction faction : tempFactions) {
                try {
                    for (String s : Config.factionsConfig_getConfigSections("factions." + faction.getUUID().toString() + ".relations")) {
                        Relations out;
                        switch (Config.factionsConfig_getInt("factions." + faction.getUUID().toString() + ".relations." + s + ".type")) {
                            default:
                                out = Relations.NEUTRAL;
                                break;
                            case 1:
                                out = Relations.ENEMY;
                                break;
                            case 2:
                                out = Relations.ALLY;
                                break;
                        }
                        relations.add(new Relation(out, Config.factionsConfig_getBoolean("factions." + faction.getUUID().toString() + ".relations." + s + ".confirmed"), getFaction(UUID.fromString(s))));
                    }
                } catch (NullPointerException e) {
                    relations = null;
                }
                Faction f = new Faction(faction.getUUID(),faction.getName(),relations,faction.getReputationPoints(),faction.getInfluencePoints(),faction.getChief(),faction.getOfficers(),faction.getMembers(),faction.getTerritory(),faction.isComplete());
                factions.remove(faction);
                factions.add(f);
                Bukkit.getLogger().log(Level.INFO, logPrefix + "Relations of " + f.getUUID().toString() + " has been successfully loaded.");
            }

            // Players
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 4 : Loading Players");
            List<String> playersUUID = Config.playersConfig_getStringList("activePlayers");
            for (int i = 0; i != playersUUID.size(); i++) {
                String uuid = playersUUID.get(i);
                boolean isChief = Config.playersConfig_getBoolean("players." + uuid + ".isChief");
                Faction faction;
                try {
                    faction = getFaction(UUID.fromString(Config.playersConfig_getString("players." + uuid + ".faction")));
                } catch (NullPointerException e) {
                    faction = null;
                }
                players.add(new PlayerProfile(UUID.fromString(uuid), faction, isChief));

                Bukkit.getLogger().log(Level.INFO, logPrefix + uuid + " has been successfully loaded. (" + (i+1) + "/" + playersUUID.size() + ")");
            }

            Bukkit.getLogger().log(Level.INFO, logPrefix + "Done.");


        } catch (Exception e) {
            e.printStackTrace();
            Bukkit.getLogger().log(Level.SEVERE, logPrefix + "Can't load configs, disabling.");
            Bukkit.getServer().getPluginManager().disablePlugin(getPlugin());
        }

        Bukkit.getLogger().log(Level.INFO, logPrefix + "--- SkyFaction Configs Loader ---");
    }

    public void close() {

        Bukkit.getLogger().log(Level.INFO, logPrefix + "--- SkyFaction Configs Loader ---");
        Bukkit.getLogger().log(Level.INFO, logPrefix + "## Saving protocol ##");
        try {
            // Territories
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 1 : Saving Territories");
            List<String> activeTerritories = new ArrayList<String>();
            for (Territory terr : territories) {
                activeTerritories.add(terr.getUUID().toString());

                if (terr.getBase() != null) {
                    Config.territoriesConfig_set("territories." + terr.getUUID().toString() + ".base.world", terr.getBase().getWorld().getUID().toString());
                    Config.territoriesConfig_set("territories." + terr.getUUID().toString() + ".base.x", terr.getBase().getBlockX());
                    Config.territoriesConfig_set("territories." + terr.getUUID().toString() + ".base.y", terr.getBase().getBlockY());
                    Config.territoriesConfig_set("territories." + terr.getUUID().toString() + ".base.z", terr.getBase().getBlockZ());
                }

                if (terr.getVault() != null) {
                    Config.territoriesConfig_set("territories." + terr.getUUID().toString() + ".vault.world", terr.getVault().getWorld().getUID().toString());
                    Config.territoriesConfig_set("territories." + terr.getUUID().toString() + ".vault.x", terr.getVault().getBlockX());
                    Config.territoriesConfig_set("territories." + terr.getUUID().toString() + ".vault.y", terr.getVault().getBlockY());
                    Config.territoriesConfig_set("territories." + terr.getUUID().toString() + ".vault.z", terr.getVault().getBlockZ());
                }

                if (terr.getChunks().size() != 0) {
                    Config.territoriesConfig_set("territories." + terr.getUUID().toString() + ".lastChunkNumber", terr.getChunks().size());
                }

                for (TerritoryChunk ch : terr.getChunks()) {
                    Config.territoriesConfig_set("territories." + terr.getUUID().toString() + ".chunks." + ch.getNumber() + ".x", ch.getChunk().getX());
                    Config.territoriesConfig_set("territories." + terr.getUUID().toString() + ".chunks." + ch.getNumber() + ".z", ch.getChunk().getZ());
                }
                Bukkit.getLogger().log(Level.INFO, logPrefix + terr.getUUID().toString() + " has been successfully saved.");
            }
            Config.territoriesConfig_set("activeTerritories", activeTerritories);

            // Factions
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 2 : Saving Factions");
            List<String> activeFactions = new ArrayList<String>();
            for (Faction fac : factions) {
                activeFactions.add(fac.getUUID().toString());

                Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".name", fac.getName());

                Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".reputationPoints", fac.getReputationPoints());

                Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".influencePoints", fac.getInfluencePoints());

                Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".chief", fac.getChief().toString());

                if (fac.getTerritory() != null) {
                    Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".territory", fac.getTerritory().getUUID().toString());
            }

                List<String> officers = new ArrayList<String>();
                for (UUID off : fac.getOfficers()) {
                    officers.add(off.toString());
                }

                Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".officers", officers);

                List<String> members = new ArrayList<String>();
                for (UUID mem : fac.getMembers()) {
                    members.add(mem.toString());
                }

                Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".members", members);

                for (Relation relation : fac.getRelations()) {
                    switch (relation.getRelation()) {
                        case ALLY:
                            Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".relations." + fac.getUUID().toString() + ".type", 2);
                            Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".relations." + fac.getUUID().toString() + ".confirmed", relation.isConfirmed());
                            Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".relations." + fac.getUUID().toString() + ".faction", relation.getFaction().getUUID().toString());
                            break;
                        case ENEMY:
                            Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".relations." + fac.getUUID().toString() + ".type", 1);
                            Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".relations." + fac.getUUID().toString() + ".confirmed", relation.isConfirmed());
                            Config.factionsConfig_set("factions." + fac.getUUID().toString() + ".relations." + fac.getUUID().toString() + ".faction", relation.getFaction().getUUID().toString());
                            break;
                        case NEUTRAL:
                            break;
                    }
                }
                Bukkit.getLogger().log(Level.INFO, logPrefix + fac.getUUID().toString() + " has been successfully saved.");
            }

                Config.factionsConfig_set("activeFactions", activeFactions);

            // PlayerProfile
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 3 : Saving Players");
            List<String> activePlayers = new ArrayList<String>();
            for (PlayerProfile pl : players) {
                activePlayers.add(pl.getUUID().toString());

                activePlayers.add(pl.getUUID().toString());

                if (pl.getFaction() != null) {
                    Config.playersConfig_set("players." + pl.getUUID().toString() + ".faction", pl.getFaction().getUUID().toString());
                }

                Config.playersConfig_set("players." + pl.getUUID().toString() + ".isChief", pl.isChief());

                Bukkit.getLogger().log(Level.INFO, logPrefix + pl.getUUID().toString() + " has been successfully saved.");
            }
            Config.playersConfig_set("activePlayers", activePlayers);
            Bukkit.getLogger().log(Level.INFO, logPrefix + "--- SkyFaction Configs Loader ---");

        } catch (Exception e) {
            e.printStackTrace();
            retryNumber++;
            if (retryNumber <= 3) {
                Bukkit.getLogger().log(Level.SEVERE, logPrefix + "Can't save configs, Retrying...");
                close();
            } else  {
                Bukkit.getLogger().log(Level.SEVERE, logPrefix + "Can't save configs after 3 retry.");
                Bukkit.getLogger().log(Level.INFO, logPrefix + "--- SkyFaction Configs Loader ---");
            }
        }
    }

    public void reload() {

    }
}

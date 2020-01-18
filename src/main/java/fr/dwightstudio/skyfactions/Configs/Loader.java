package fr.dwightstudio.skyfactions.Configs;

import fr.dwightstudio.skyfactions.Objects.*;
import fr.dwightstudio.skyfactions.Objects.*;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import static fr.dwightstudio.skyfactions.Main.getPlugin;
import static fr.dwightstudio.skyfactions.Main.logPrefix;

public class Loader {

    private int retryNumber = 0;
    public static Config config;

    public Territory getTerritory(UUID territoryUUID) {
        for (Territory territory : territories) {
            if (territory.getUUID().equals(territoryUUID)) {
                return territory;
            }
        }
        return null;
    }

    public Faction getFaction(UUID factionUUID) {
        for (Faction faction : factions) {
            if (faction.getUUID().equals(factionUUID)) {
                return faction;
            }
        }
        return null;
    }

    public PlayerProfile getPlayerProfile(UUID playerUUID) {
        for (PlayerProfile playerProfile : players) {
            if (playerProfile.getUUID().equals(playerUUID)) {
                return playerProfile;
            }
        }
        return null;
    }

    public List<Territory> territories = new ArrayList<Territory>();
    public List<Faction> factions = new ArrayList<Faction>();
    public List<PlayerProfile> players = new ArrayList<PlayerProfile>();
    public List<Relation> relations = new ArrayList<Relation>();

    public Loader() {
        config = new Config();

        Bukkit.getLogger().log(Level.INFO, logPrefix + "--- SkyFaction Configs Loader ---");
        Bukkit.getLogger().log(Level.INFO, logPrefix + "## Loading protocol ##");
        try {

            // Territories
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 1 : Loading Territories");
            List<String> territoriesUUIDs = Config.territoriesConfig_getStringList("activeTerritories");
            for (int i = 0; i != territoriesUUIDs.size(); i++) {
                String territoryUUID = territoriesUUIDs.get(i);
                Location base;
                try {
                    base = new Location(Bukkit.getWorld(UUID.fromString(
                            Config.territoriesConfig_getString("territories." + territoryUUID + ".base.world"))),
                            (long) Config.territoriesConfig_getInt("territories." + territoryUUID + ".base.x"),
                            (long) Config.territoriesConfig_getInt("territories." + territoryUUID + ".base.y"),
                            (long) Config.territoriesConfig_getInt("territories." + territoryUUID + ".base.z")
                    );
                } catch (NullPointerException e) {
                    base = null;
                }

                Location vault;
                try {
                vault = new Location(Bukkit.getWorld(UUID.fromString(Config.territoriesConfig_getString("territories." + territoryUUID + ".vault.world"))),
                        (long) Config.territoriesConfig_getInt("territories." + territoryUUID + ".vault.x"),
                        (long) Config.territoriesConfig_getInt("territories." + territoryUUID + ".vault.y"),
                        (long) Config.territoriesConfig_getInt("territories." + territoryUUID + ".vault.z"));
                } catch (NullPointerException e) {
                    vault = null;
                }

                int lastChunkNumber = Config.territoriesConfig_getInt("territories." + territoryUUID + ".lastChunkNumber");

                List<TerritoryChunk> chunks = new ArrayList<TerritoryChunk>();

                for (int e = 0 ; lastChunkNumber != e; e++) {
                    chunks.add(new TerritoryChunk(base.getWorld().getChunkAt(
                            Config.territoriesConfig_getInt("territories." + territoryUUID + ".chunks." + e + ".x"),
                            Config.territoriesConfig_getInt("territories." + territoryUUID + ".chunks." + e + ".z"))
                            ,e));
                }

                territories.add(new Territory(UUID.fromString(territoryUUID), chunks, lastChunkNumber, base, vault));

                Bukkit.getLogger().log(Level.INFO, logPrefix + territoryUUID + " successfully loaded. (" + (i+1) + "/" + territoriesUUIDs.size() + ")");

            }

            // Factions
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 2 : Loading Factions");
            List<String> factionsUUIDs = Config.factionsConfig_getStringList("activeFactions");
            for (int i = 0; i != factionsUUIDs.size(); i++) {
                String territoryUUID = factionsUUIDs.get(i);
                String name = Config.factionsConfig_getString("factions." + territoryUUID + ".name");
                int reputationPoints = Config.factionsConfig_getInt("factions." + territoryUUID + ".reputationPoints");
                int influencePoints  = Config.factionsConfig_getInt("factions." + territoryUUID + ".influencePoints");
                UUID chief = UUID.fromString(Config.factionsConfig_getString("factions." + territoryUUID + ".chief"));

                List<UUID> officers = new ArrayList<UUID>();
                for (String u : Config.factionsConfig_getStringList("factions." + territoryUUID + ".officers")) {
                    officers.add(UUID.fromString(u));
                }

                List<UUID> members = new ArrayList<UUID>();
                for (String u : Config.factionsConfig_getStringList("factions." + territoryUUID + ".members")) {
                    officers.add(UUID.fromString(u));
                }


                Territory territory;

                try {
                    territory = getTerritory(UUID.fromString(Config.factionsConfig_getString("factions." + territoryUUID + ".territory")));
                } catch (NullPointerException e) {
                    territory = null;
                }

                boolean isComplete = Config.factionsConfig_getBoolean("factions." + territoryUUID + ".isComplete");
                boolean isOpen = Config.factionsConfig_getBoolean("factions." + territoryUUID + ".isOpen");

                factions.add(new Faction(UUID.fromString(territoryUUID), name, null, reputationPoints, influencePoints, chief, officers, members, territory, isComplete, isOpen));

                Bukkit.getLogger().log(Level.INFO, logPrefix + territoryUUID + " successfully loaded. (" + (i+1) + "/" + factionsUUIDs.size() + ")");
            }

            // Relations
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 3 : Loading Relations");
            List<String> relationsUUIDs = Config.factionsConfig_getStringList("activeRelations");
            for (int i = 0; i != relationsUUIDs.size(); i++) {
                String relationUUID = relationsUUIDs.get(i);
                Relations type;
                switch (Config.relationsConfig_getInt("relations." + relationUUID + ".type")) {
                    case 0:
                        type = Relations.NEUTRAL;
                        break;
                    case 1:
                        type = Relations.ENEMY;
                        break;
                    case 2:
                        type = Relations.ALLY;
                        break;
                }

                Faction firstFaction = getFaction(UUID.fromString(Config.relationsConfig_getString("relations." + relationUUID + ".first.faction")));
                boolean firstConfirmed = Config.relationsConfig_getBoolean("relations." + relationUUID + ".first.isConfirmed");
                Faction secondFaction = getFaction(UUID.fromString(Config.relationsConfig_getString("relations." + relationUUID + ".second.faction")));
                boolean secondConfirmed = Config.relationsConfig_getBoolean("relations." + relationUUID + ".second.isConfirmed");
                
                relations.add(new Relation(UUID.fromString(relationUUID),Relations.ALLY,new FactionCouple(firstFaction,firstConfirmed,secondFaction,secondConfirmed)));
                Bukkit.getLogger().log(Level.INFO, logPrefix + "Relation " + relationUUID + " successfully loaded.");
            }

            // Players
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 4 : Loading Players");
            List<String> playersUUID = Config.playersConfig_getStringList("activePlayers");
            for (int i = 0; i != playersUUID.size(); i++) {
                String playerUUID = playersUUID.get(i);
                boolean isChief = Config.playersConfig_getBoolean("players." + playerUUID + ".isChief");
                boolean isOfficer = Config.playersConfig_getBoolean("players." + playerUUID + ".isOfficer");
                Faction faction;
                try {
                    faction = getFaction(UUID.fromString(Config.playersConfig_getString("players." + playerUUID + ".faction")));
                } catch (NullPointerException e) {
                    faction = null;
                }
                players.add(new PlayerProfile(UUID.fromString(playerUUID), faction, isChief, isOfficer));

                Bukkit.getLogger().log(Level.INFO, logPrefix + playerUUID + " successfully loaded. (" + (i+1) + "/" + playersUUID.size() + ")");
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
            for (Territory territory : territories) {
                activeTerritories.add(territory.getUUID().toString());

                if (territory.getBase() != null) {
                    Config.territoriesConfig_set("territories." + territory.getUUID().toString() + ".base.world", territory.getBase().getWorld().getUID().toString());
                    Config.territoriesConfig_set("territories." + territory.getUUID().toString() + ".base.x", territory.getBase().getBlockX());
                    Config.territoriesConfig_set("territories." + territory.getUUID().toString() + ".base.y", territory.getBase().getBlockY());
                    Config.territoriesConfig_set("territories." + territory.getUUID().toString() + ".base.z", territory.getBase().getBlockZ());
                }

                if (territory.getVault() != null) {
                    Config.territoriesConfig_set("territories." + territory.getUUID().toString() + ".vault.world", territory.getVault().getWorld().getUID().toString());
                    Config.territoriesConfig_set("territories." + territory.getUUID().toString() + ".vault.x", territory.getVault().getBlockX());
                    Config.territoriesConfig_set("territories." + territory.getUUID().toString() + ".vault.y", territory.getVault().getBlockY());
                    Config.territoriesConfig_set("territories." + territory.getUUID().toString() + ".vault.z", territory.getVault().getBlockZ());
                }

                if (territory.getChunks().size() != 0) {
                    Config.territoriesConfig_set("territories." + territory.getUUID().toString() + ".lastChunkNumber", territory.getChunks().size());
                }

                for (TerritoryChunk ch : territory.getChunks()) {
                    Config.territoriesConfig_set("territories." + territory.getUUID().toString() + ".chunks." + ch.getNumber() + ".x", ch.getChunk().getX());
                    Config.territoriesConfig_set("territories." + territory.getUUID().toString() + ".chunks." + ch.getNumber() + ".z", ch.getChunk().getZ());
                }
                Bukkit.getLogger().log(Level.INFO, logPrefix + territory.getUUID().toString() + " successfully saved.");
            }

            Config.territoriesConfig_set("activeTerritories", activeTerritories);

            // Factions
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 2 : Saving Factions");
            List<String> activeFactions = new ArrayList<String>();
            for (Faction faction : factions) {
                activeFactions.add(faction.getUUID().toString());

                Config.factionsConfig_set("factions." + faction.getUUID().toString() + ".name", faction.getName());

                Config.factionsConfig_set("factions." + faction.getUUID().toString() + ".reputationPoints", faction.getReputationPoints());

                Config.factionsConfig_set("factions." + faction.getUUID().toString() + ".influencePoints", faction.getInfluencePoints());

                Config.factionsConfig_set("factions." + faction.getUUID().toString() + ".chief", faction.getChief().toString());

                if (faction.getTerritory() != null) {
                    Config.factionsConfig_set("factions." + faction.getUUID().toString() + ".territory", faction.getTerritory().getUUID().toString());
                }

                List<String> officers = new ArrayList<String>();
                for (UUID off : faction.getOfficers()) {
                    officers.add(off.toString());
                }

                Config.factionsConfig_set("factions." + faction.getUUID().toString() + ".officers", officers);

                Config.factionsConfig_set("factions." + faction.getUUID().toString() + ".isComplete", faction.isComplete());
                Config.factionsConfig_set("factions." + faction.getUUID().toString() + ".isOpen", faction.isOpen());

                List<String> members = new ArrayList<String>();
                for (UUID mem : faction.getMembers()) {
                    members.add(mem.toString());
                }

                Config.factionsConfig_set("factions." + faction.getUUID().toString() + ".members", members);
                Bukkit.getLogger().log(Level.INFO, logPrefix + faction.getUUID().toString() + " successfully saved.");
            }

                Config.factionsConfig_set("activeFactions", activeFactions);

            // Relations
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 3 : Saving Relations");
            for (Relation relation : relations) {
                switch (relation.getRelation()) {
                    case ALLY:
                        Config.factionsConfig_set("relations." + relation.getUUID().toString() + ".type", 2);
                        Config.factionsConfig_set("relations." + relation.getUUID().toString() + ".relations.first.faction", relation.getFactions().first);
                        Config.factionsConfig_set("relations." + relation.getUUID().toString() + ".relations.first.isConfirmed", relation.getFactions().isFirstConfirmed());
                        Config.factionsConfig_set("relations." + relation.getUUID().toString() + ".relations.second.faction", relation.getFactions().second);
                        Config.factionsConfig_set("relations." + relation.getUUID().toString() + ".relations.second.isConfirmed", relation.getFactions().isSecondConfirmed());
                        break;
                    case ENEMY:
                        Config.factionsConfig_set("relations." + relation.getUUID().toString() + ".type", 1);
                        Config.factionsConfig_set("relations." + relation.getUUID().toString() + ".relations.first.faction", relation.getFactions().first);
                        Config.factionsConfig_set("relations." + relation.getUUID().toString() + ".relations.first.isConfirmed", relation.getFactions().isFirstConfirmed());
                        Config.factionsConfig_set("relations." + relation.getUUID().toString() + ".relations.second.faction", relation.getFactions().second);
                        Config.factionsConfig_set("relations." + relation.getUUID().toString() + ".relations.second.isConfirmed", relation.getFactions().isSecondConfirmed());
                        break;
                    case NEUTRAL:
                        break;
                }
            }

            // PlayerProfile
            Bukkit.getLogger().log(Level.INFO, logPrefix + "Step 4 : Saving Players");
            List<String> activePlayers = new ArrayList<String>();
            for (PlayerProfile playerProfile : players) {
                activePlayers.add(playerProfile.getUUID().toString());

                if (playerProfile.getFaction() != null) {
                    Config.playersConfig_set("players." + playerProfile.getUUID().toString() + ".faction", playerProfile.getFaction().getUUID().toString());
                }

                Config.playersConfig_set("players." + playerProfile.getUUID().toString() + ".isChief", playerProfile.isChief());
                Config.playersConfig_set("players." + playerProfile.getUUID().toString() + ".isOfficer", playerProfile.isOfficer());

                Bukkit.getLogger().log(Level.INFO, logPrefix + playerProfile.getUUID().toString() + " successfully saved.");
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
                Bukkit.getLogger().log(Level.SEVERE, logPrefix + "Can't save configs after 3 retries.");
                Bukkit.getLogger().log(Level.INFO, logPrefix + "--- SkyFaction Configs Loader ---");
            }
        }
    }

    public void reload() {

    }
}

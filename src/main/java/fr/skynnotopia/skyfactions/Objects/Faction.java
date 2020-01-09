package fr.skynnotopia.skyfactions.Objects;

import fr.skynnotopia.skyfactions.Configs.Config;

import java.util.List;
import java.util.UUID;

public class Faction {

    private UUID uuid;
    private String name;
    private int reputationPoints;
    private int influencePoints;
    private UUID chief;
    private List<UUID> officers;
    private List<UUID> members;
    private Territory territory;
    private boolean isComplete;

    public Faction(UUID uuid, String name, int reputationPoints, int influencePoints, UUID chief, List<UUID> officers, List<UUID> members, Territory territory) {
        this.uuid = uuid;
        this.name = name;
        this.reputationPoints = reputationPoints;
        this.influencePoints = influencePoints;
        this.chief = chief;
        this.officers = officers;
        this.members = members;
        this.territory = territory;
    }

    public void save(boolean isNew) {
        if (isNew) {
            List<String> factionsUUIDs = Config.factionsConfig_getStringList("activeFactions");
            factionsUUIDs.add(this.uuid.toString());
            Config.factionsConfig_set("activeFactions", factionsUUIDs);
        }

        Config.factionsConfig_set("factions." + uuid.toString() + "name", name);

        Config.factionsConfig_set("factions." + uuid.toString() + ".reputationPoints", reputationPoints);

        Config.factionsConfig_set("factions." + uuid.toString() + ".influencePoints", influencePoints);

        Config.factionsConfig_set("factions." + uuid.toString() + ".chief", chief.toString());

        Config.factionsConfig_set("factions." + uuid.toString() + ".territory", territory.getUUID());

        for (UUID off : officers) {
            Config.factionsConfig_set("factions." + uuid.toString() + ".officers", off.toString());
        }

        for (UUID mem : members) {
            Config.factionsConfig_set("factions." + uuid.toString() + ".members", mem.toString());
        }
    }

    public UUID getUUID() {
        return uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getReputationPoints() {
        return reputationPoints;
    }

    public void setReputationPoints(int reputationPoints) {
        this.reputationPoints = reputationPoints;
    }

    public int getInfluencePoints() {
        return influencePoints;
    }

    public void setInfluencePoints(int influencePoints) {
        this.influencePoints = influencePoints;
    }

    public UUID getChief() {
        return chief;
    }

    public void setChief(UUID chief) {
        this.chief = chief;
    }

    public List<UUID> getOfficers() {
        return officers;
    }

    public List<UUID> getMembers() {
        return members;
    }

    public Territory getTerritory() {
        return territory;
    }

    public boolean isComplete() {
        return isComplete;
    }
}

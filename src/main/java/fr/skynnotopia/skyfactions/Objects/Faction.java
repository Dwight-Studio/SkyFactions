package fr.skynnotopia.skyfactions.Objects;

import java.util.List;
import java.util.UUID;

public class Faction {

    private UUID uuid;
    private String name;
    private List<Relation> relations;
    private int reputationPoints;
    private int influencePoints;
    private UUID chief;
    private List<UUID> officers;
    private List<UUID> members;
    private Territory territory;
    private boolean isComplete;
    private boolean isOpen;

    public Faction(UUID uuid, String name, List<Relation> relations, int reputationPoints, int influencePoints, UUID chief, List<UUID> officers, List<UUID> members, Territory territory, boolean isComplete, boolean isOpen) {
        this.uuid = uuid;
        this.name = name;
        this.relations = relations;
        this.reputationPoints = reputationPoints;
        this.influencePoints = influencePoints;
        this.chief = chief;
        this.officers = officers;
        this.members = members;
        this.territory = territory;
        this.isComplete = isComplete;
        this.isOpen = isOpen;
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

    public List<Relation> getRelations() {
        return relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    //TODO: Gestion des candidatures (avec notifications)
}

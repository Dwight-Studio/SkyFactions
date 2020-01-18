package fr.dwightstudio.skyfactions.Objects;

import java.util.UUID;

public class Relation {
    private UUID uuid;
    private Relations relation;
    private FactionCouple factions;

    public Relation(UUID uuid, Relations relation, FactionCouple factions) {
        this.relation = relation;
        this.factions = factions;
    }

    public Relations getRelation() {
        return relation;
    }

    public void setRelation(Relations relation) {
        this.relation = relation;
    }

    public boolean isConfirmed() {
        return factions.isConfirmed();
    }

    public FactionCouple getFactions() {
        return factions;
    }

    public UUID getUUID() {
        return uuid;
    }
}

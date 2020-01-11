package fr.skynnotopia.skyfactions.Objects;

public class Relation {
    private Relations relation;
    private boolean confirmed;
    private Faction faction;

    public Relation(Relations relation, boolean confirmed, Faction faction) {
        this.relation = relation;
        this.confirmed = confirmed;
        this.faction = faction;
    }

    public Relations getRelation() {
        return relation;
    }

    public void setRelation(Relations relation) {
        this.relation = relation;
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    public void setConfirmed(boolean confirmed) {
        this.confirmed = confirmed;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }
}

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

    /*TODO: Revoir le système de relations:
        Faire en sorte que les relations soit stocker dans un fichier à-part
        Faire en sorte que les relations soit un objet à-part entière (ne sont pas des variables des objets Faction)
        Modifier le loader pour prendre en compte les changement
     */
}

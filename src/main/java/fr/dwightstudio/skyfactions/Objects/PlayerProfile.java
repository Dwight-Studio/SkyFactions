package fr.dwightstudio.skyfactions.Objects;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerProfile {

    private UUID uuid;
    private Faction faction;
    private boolean isChief;
    private boolean isOfficer;
    private String title;

    public PlayerProfile(UUID uuid, Faction faction, boolean isChief, boolean isOfficer, String title) {
        this.uuid = uuid;
        this.faction = faction;
        this.isChief = isChief;
        this.isOfficer = isOfficer;
        this.title = title;
    }

    public UUID getUUID() {
        return uuid;
    }

    public Faction getFaction() {
        return faction;
    }

    public void setFaction(Faction faction) {
        this.faction = faction;
    }

    public boolean isChief() {
        return isChief;
    }

    public void setChief(boolean chief) {
        isChief = chief;
    }

    public boolean isOfficer() {
        return isOfficer;
    }

    public void setOfficer(boolean officer) {
        isOfficer = officer;
    }

    public Player getPlayer() {
        return Bukkit.getPlayer(uuid);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    //TODO: Gestion des invitations (avec notifications)
}

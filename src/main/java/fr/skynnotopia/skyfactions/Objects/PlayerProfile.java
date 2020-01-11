package fr.skynnotopia.skyfactions.Objects;

import fr.skynnotopia.skyfactions.Configs.Config;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class PlayerProfile {

    private UUID uuid;
    private Faction faction;
    private boolean isChief;

    public PlayerProfile(UUID uuid, Faction faction, boolean isChief) {
        this.uuid = uuid;
        this.faction = faction;
        this.isChief = isChief;
    }


    public UUID getUUID() {
        return uuid;
    }

    public Faction getFaction() {
        return faction;
    }

    public boolean isChief() {
        return isChief;
    }
}

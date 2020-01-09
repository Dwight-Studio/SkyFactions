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

    public void save(boolean isNew) {
        if (isNew) {
            List<String> playersUUIDs = Config.factionsConfig_getStringList("activePlayers");
            playersUUIDs.add(this.uuid.toString());
            Config.factionsConfig_set("activePlayers", playersUUIDs);
        }

        Config.playersConfig_set("players." + uuid.toString() + ".faction", faction.getUUID());

        Config.playersConfig_set("players." + uuid.toString() + ".faction", isChief);

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

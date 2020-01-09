package fr.skynnotopia.skyfactions.Objects;

import fr.skynnotopia.skyfactions.Configs.Config;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Territory {

    private UUID uuid;
    private List<TerritoryChunk> chunks;
    private int lastChunkNumber;
    private Location base;
    private Location vault;

    public Territory(UUID uuid, List<TerritoryChunk> chunks, int lastChunkNumber, Location base, Location vault) {
        this.uuid = uuid;
        this.chunks = chunks;
        this.lastChunkNumber = lastChunkNumber;
        this.base = base;
        this.vault = vault;
    }

    public void save(boolean isNew) {
        if (isNew) {
            List<String> territoriesUUIDs = Config.factionsConfig_getStringList("activeTerritories");
            territoriesUUIDs.add(this.uuid.toString());
            Config.factionsConfig_set("activeTerritories", territoriesUUIDs);
        }

        Config.territoriesConfig_set("territories." + uuid.toString() + ".base.world", base.getWorld().getUID());
        Config.territoriesConfig_set("territories." + uuid.toString() + ".base.x", base.getBlockX());
        Config.territoriesConfig_set("territories." + uuid.toString() + ".base.y", base.getBlockY());
        Config.territoriesConfig_set("territories." + uuid.toString() + ".base.z", base.getBlockZ());

        Config.territoriesConfig_set("territories." + uuid.toString() + ".vault.world", vault.getWorld().getUID());
        Config.territoriesConfig_set("territories." + uuid.toString() + ".vault.x", vault.getBlockX());
        Config.territoriesConfig_set("territories." + uuid.toString() + ".vault.y", vault.getBlockY());
        Config.territoriesConfig_set("territories." + uuid.toString() + ".vault.z", vault.getBlockZ());

        Config.territoriesConfig_set("territories." + uuid.toString() + ".lastChunkNumber", chunks.size());

        for (TerritoryChunk ch : chunks) {
            Config.territoriesConfig_set("territories." + uuid.toString() + ".chunks." + ch.getNumber() + ".x", ch.getChunk().getX());
            Config.territoriesConfig_set("territories." + uuid.toString() + ".chunks." + ch.getNumber() + ".z", ch.getChunk().getZ());
        }
    }

    public List<TerritoryChunk> getChunks() {
        return chunks;
    }

    public UUID getUUID() {
        return uuid;
    }

    public int getLastChunkNumber() {
        return lastChunkNumber;
    }

    public Location getBase() {
        return base;
    }

    public void setBase(Location base) {
        this.base = base;
    }

    public Location getVault() {
        return vault;
    }

    public void setVault(Location vault) {
        this.vault = vault;
    }
}

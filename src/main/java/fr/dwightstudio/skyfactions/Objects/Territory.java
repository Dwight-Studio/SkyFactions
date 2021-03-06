package fr.dwightstudio.skyfactions.Objects;

import org.bukkit.Location;

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
        this.lastChunkNumber = 1;
        this.base = base;
        this.vault = vault;
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

    public void upgrade() {
        //TODO: Claim des chunks
    }

    public void downgrade() {
        /*TODO: Perte d'un territoire:
            Dernier Chunk Claim
            Si le niveau de réputation diminue
         */

    }
}

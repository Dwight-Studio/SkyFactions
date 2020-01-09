package fr.skynnotopia.skyfactions.Objects;

import org.bukkit.Chunk;

public class TerritoryChunk {

    private Chunk chunk;
    private int number;

    public TerritoryChunk(Chunk chunk, int number) {
        this.chunk = chunk;
        this.number = number;
    }

    public Chunk getChunk() {
        return chunk;
    }

    public int getNumber() {
        return number;
    }
}

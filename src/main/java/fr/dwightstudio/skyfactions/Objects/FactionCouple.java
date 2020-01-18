package fr.dwightstudio.skyfactions.Objects;

public class FactionCouple {
    public final Faction first;
    private boolean firstConfirmed;
    public final Faction second;
    private boolean secondConfirmed;

    public FactionCouple(Faction first, boolean firstConfirmed, Faction second, boolean secondConfirmed) {
        this.first = first;
        this.firstConfirmed = firstConfirmed;
        this.second = second;
        this.secondConfirmed = secondConfirmed;
    }

    public boolean isFirstConfirmed() {
        return firstConfirmed;
    }

    public void setFirstConfirmed(boolean firstConfirmed) {
        this.firstConfirmed = firstConfirmed;
    }

    public boolean isSecondConfirmed() {
        return secondConfirmed;
    }

    public void setSecondConfirmed(boolean secondConfirmed) {
        this.secondConfirmed = secondConfirmed;
    }

    public boolean isConfirmed() {
        return firstConfirmed && secondConfirmed;
    }
}

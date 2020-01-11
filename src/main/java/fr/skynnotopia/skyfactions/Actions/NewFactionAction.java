package fr.skynnotopia.skyfactions.Actions;

import me.lyras.api.gui.action.Action;
import me.lyras.api.gui.ui.Listing;
import me.lyras.api.gui.utilities.EventStatus;
import me.lyras.api.gui.utilities.HandleStatus;
import org.bukkit.entity.Player;

import static fr.skynnotopia.skyfactions.Listeners.ChatInputListener.factionCreationPlayer;
import static fr.skynnotopia.skyfactions.Main.messagePrefix;

public class NewFactionAction extends Action {
    private Listing parent;
    private Player pl;
    public void load(Object... arguments) {
        try {
            if (arguments.length == 1 && arguments[0] instanceof Listing) {
                this.parent = (Listing)arguments[0];
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public NewFactionAction(Player pl) {
        this.pl = pl;
    }

    @Override
    public void execute () {
        factionCreationPlayer.add(pl);
        pl.sendMessage(messagePrefix + "Envoyez dans le chat le nom de votre faction.");
        this.setEventStatus( EventStatus.CANCEL );
        this.setHandleStatus( HandleStatus.HANDLE );
        parent.close();
    }
}

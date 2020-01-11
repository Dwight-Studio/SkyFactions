package fr.skynnotopia.skyfactions.Actions;

import me.lyras.api.gui.action.Action;
import me.lyras.api.gui.ui.Listing;
import me.lyras.api.gui.utilities.EventStatus;
import me.lyras.api.gui.utilities.HandleStatus;
import org.bukkit.entity.Player;

public class PlayerSelectAction extends Action {

    private Player selectedPlayer;

    public PlayerSelectAction(Player sp) {
        selectedPlayer = sp;
    }

    Listing parent;
    public void load(Object... arguments) {
        try {
            if (arguments.length == 1 && arguments[0] instanceof Listing) {
                this.parent = (Listing)arguments[0];
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    @Override
    public void execute () {
        this.setEventStatus( EventStatus.CANCEL );
        this.setHandleStatus( HandleStatus.HANDLE );
        parent.close();


    }


    public Player getSelectedPlayer() {
        return selectedPlayer;
    }
}

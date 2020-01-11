package fr.skynnotopia.skyfactions.Actions;

import me.lyras.api.gui.ui.Listing;
import me.lyras.api.gui.utilities.EventStatus;
import me.lyras.api.gui.utilities.HandleStatus;
import me.lyras.api.gui.action.Action;
import org.bukkit.entity.Player;

public class JoinFactionAction extends Action {
    Listing parent;
    Player pl;
    public void load(Object... arguments) {
        try {
            if (arguments.length == 1 && arguments[0] instanceof Listing) {
                this.parent = (Listing)arguments[0];
            }
        } catch (Exception var3) {
            var3.printStackTrace();
        }

    }

    public JoinFactionAction(Player pl) {
        this.pl = pl;
    }

    @Override
    public void execute () {

        this.setEventStatus( EventStatus.CANCEL );
        this.setHandleStatus( HandleStatus.HANDLE );
        parent.close();
    }
}

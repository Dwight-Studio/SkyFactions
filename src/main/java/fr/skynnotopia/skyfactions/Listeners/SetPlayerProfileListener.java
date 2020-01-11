package fr.skynnotopia.skyfactions.Listeners;

import fr.skynnotopia.skyfactions.Main;
import fr.skynnotopia.skyfactions.Objects.PlayerProfile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.List;

public class SetPlayerProfileListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        if (!Main.loader.players.contains(event.getPlayer())) {
            Main.loader.players.add(new PlayerProfile(event.getPlayer().getUniqueId(),null,false));
        }
    }
}

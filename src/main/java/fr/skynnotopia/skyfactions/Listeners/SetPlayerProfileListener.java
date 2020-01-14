package fr.skynnotopia.skyfactions.Listeners;

import fr.skynnotopia.skyfactions.Main;
import fr.skynnotopia.skyfactions.Objects.PlayerProfile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SetPlayerProfileListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        for (PlayerProfile playerProfile : Main.loader.players) {
            if (playerProfile.getUUID() == event.getPlayer().getUniqueId()) {
                return;
            }
        }
        Main.loader.players.add(new PlayerProfile(event.getPlayer().getUniqueId(), null, false, false));
    }
}

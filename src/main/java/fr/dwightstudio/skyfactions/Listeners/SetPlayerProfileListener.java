package fr.dwightstudio.skyfactions.Listeners;

import fr.dwightstudio.skyfactions.Objects.PlayerProfile;
import fr.dwightstudio.skyfactions.Main;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class SetPlayerProfileListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        for (PlayerProfile playerProfile : Main.loader.getPlayers()) {
            if (playerProfile.getUUID() == event.getPlayer().getUniqueId()) {
                return;
            }
        }
        Main.loader.addPlayerProfile(new PlayerProfile(event.getPlayer().getUniqueId(), null, false, false, null));
    }
}

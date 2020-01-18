package fr.dwightstudio.skyfactions.Listeners;

import fr.dwightstudio.skyfactions.Objects.PlayerProfile;
import fr.dwightstudio.skyfactions.Main;
import fr.dwightstudio.skyfactions.Objects.Faction;
import fr.dwightstudio.skyfactions.Objects.Relation;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static fr.dwightstudio.skyfactions.Main.messagePrefix;

public class ChatInputListener implements Listener {

    public static List<Player> factionCreationPlayer = new ArrayList<Player>();
    public static List<Player> factionRenamePlayer = new ArrayList<Player>();

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (factionCreationPlayer.contains(event.getPlayer())) {
            event.setCancelled(true);
            factionCreationPlayer.remove(event.getPlayer());
            List<UUID> members = new ArrayList<UUID>();
            List<UUID> officers = new ArrayList<UUID>();
            List<Relation> relations = new ArrayList<Relation>();
            Faction fac = new Faction(UUID.randomUUID(), event.getMessage(), relations, 20,20, event.getPlayer().getUniqueId(), officers, members,null, false, false);
            Main.loader.factions.add(fac);
            Main.loader.getPlayerProfile(event.getPlayer().getUniqueId()).setFaction(fac);
            Main.loader.getPlayerProfile(event.getPlayer().getUniqueId()).setChief(true);
            event.getPlayer().sendMessage(messagePrefix + ChatColor.AQUA + event.getMessage() + ChatColor.WHITE + " a bien été créée !" );
        }

        if (factionRenamePlayer.contains(event.getPlayer())) {
            event.setCancelled(true);
            factionRenamePlayer.remove(event.getPlayer());
            PlayerProfile playerProfile = Main.loader.getPlayerProfile(event.getPlayer().getUniqueId());
            playerProfile.getFaction().setName(event.getMessage());
            event.getPlayer().sendMessage(messagePrefix + ChatColor.AQUA + event.getMessage() + ChatColor.WHITE + " a bien été renomée !" );
        }
    }
}

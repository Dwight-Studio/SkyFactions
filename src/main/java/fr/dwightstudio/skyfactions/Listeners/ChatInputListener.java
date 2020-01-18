package fr.dwightstudio.skyfactions.Listeners;

import fr.dwightstudio.skyfactions.Objects.PlayerProfile;
import fr.dwightstudio.skyfactions.Main;
import fr.dwightstudio.skyfactions.Objects.Faction;
import fr.dwightstudio.skyfactions.Objects.Relation;
import org.bukkit.Bukkit;
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

    public static List<Player> factionCreationPhase1 = new ArrayList<Player>();
    public static List<Player> factionCreationPhase2 = new ArrayList<Player>();
    public static List<Player> factionCreationPhase3 = new ArrayList<Player>();
    public static List<Player> factionRename = new ArrayList<Player>();

    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        if (factionCreationPhase1.contains(event.getPlayer())) {
            event.setCancelled(true);
            factionCreationPhase1.remove(event.getPlayer());
            List<UUID> members = new ArrayList<UUID>();
            List<UUID> officers = new ArrayList<UUID>();
            List<Relation> relations = new ArrayList<Relation>();
            Faction fac = new Faction(UUID.randomUUID(), event.getMessage(), relations, 20,20, event.getPlayer().getUniqueId(), officers, members,null, false, false, null, null);
            Main.loader.factions.add(fac);
            Main.loader.getPlayerProfile(event.getPlayer().getUniqueId()).setFaction(fac);
            Main.loader.getPlayerProfile(event.getPlayer().getUniqueId()).setChief(true);
            event.getPlayer().sendMessage(messagePrefix + ChatColor.GOLD +
                    "Indiquer le diminutif de votre Faction. " +
                    "Il doit être composé de 5 caractères (couleurs non comprises) ou moins et peut contenir des couleurs. " +
                    "Il apparaît devant le nom dans le chat : " + "[" + ChatColor.AQUA + "fact" + ChatColor.GREEN + "ion" + ChatColor.WHITE + "]" + event.getPlayer().getDisplayName() + ChatColor.WHITE + " : message" + ChatColor.GOLD + ".");
            factionCreationPhase2.add(event.getPlayer());
        } else if (factionRename.contains(event.getPlayer())) {
            event.setCancelled(true);
            factionRename.remove(event.getPlayer());
            PlayerProfile playerProfile = Main.loader.getPlayerProfile(event.getPlayer().getUniqueId());
            playerProfile.getFaction().setName(event.getMessage());
            event.getPlayer().sendMessage(messagePrefix + ChatColor.AQUA + event.getMessage() + ChatColor.WHITE + " a bien été renomée !" );

        } else if (factionCreationPhase2.contains(event.getPlayer())) {
            event.setCancelled(true);
            PlayerProfile playerProfile = Main.loader.getPlayerProfile(event.getPlayer().getUniqueId());
            String msg = ChatColor.translateAlternateColorCodes('&',event.getMessage());
            if (ChatColor.stripColor(msg).length() <= 5) {
                playerProfile.getFaction().setNickname(msg);
                factionCreationPhase2.remove(event.getPlayer());
                factionCreationPhase3.add(event.getPlayer());
                event.getPlayer().sendMessage(messagePrefix + ChatColor.GOLD + "Indiquer la description de votre Faction. Ne peut pas contenir de couleur, peut être vide.");
            } else {
                event.getPlayer().sendMessage(messagePrefix + ChatColor.RED + "Le diminutif doit être composé de 5 caractères ou moins ! (couleurs non comprises)");
            }

        } else if (factionCreationPhase3.contains(event.getPlayer())) {
            PlayerProfile playerProfile = Main.loader.getPlayerProfile(event.getPlayer().getUniqueId());
            playerProfile.getFaction().setDescription(event.getMessage());
            factionCreationPhase3.remove(event.getPlayer());
            event.getPlayer().sendMessage(messagePrefix + ChatColor.AQUA + playerProfile.getFaction().getName() + ChatColor.WHITE + " a bien été créée !" );
        }
    }
}

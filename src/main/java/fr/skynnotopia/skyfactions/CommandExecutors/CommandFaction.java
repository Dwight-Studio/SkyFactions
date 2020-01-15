package fr.skynnotopia.skyfactions.CommandExecutors;

import brian.menuinterface.button.DefaultButtons;
import brian.menuinterface.button.MenuButton;
import brian.menuinterface.events.ButtonClickEvent;
import brian.menuinterface.types.StandardMenu;
import fr.skynnotopia.skyfactions.Main;
import fr.skynnotopia.skyfactions.Objects.ItemBuilder;
import fr.skynnotopia.skyfactions.Objects.PlayerProfile;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class CommandFaction implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender csender, Command command, String label, String[] args) {
        PlayerProfile sender;

        if (csender instanceof Player) {
            sender = Main.loader.getPlayerProfile(((Player) csender).getUniqueId());
        } else {
            return false;
        }

        // Si le joueur n'a pas de faction
        if (sender.getFaction() == null) {
            //TODO: Menu pour les joueurs sans faction

         // Si le joueur à une faction, mais n'est pas chef, ni officier
        } else if (!sender.isChief() && !sender.isOfficer()) {
            //TODO: Menu pour les joueurs avec faction

        // Si le joueur à une faction, et est officier
        } else if (!sender.isChief()) {
            //TODO: Menu pour les chef de faction

        } else {

        }
    return  true;
    }
}

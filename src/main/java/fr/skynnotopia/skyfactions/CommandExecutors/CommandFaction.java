package fr.skynnotopia.skyfactions.CommandExecutors;

import brian.menuinterface.button.DefaultButtons;
import brian.menuinterface.button.MenuButton;
import brian.menuinterface.events.ButtonClickEvent;
import brian.menuinterface.types.StandardMenu;
import fr.skynnotopia.skyfactions.Main;
import fr.skynnotopia.skyfactions.Menus.MenuBuilder;
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

        MenuBuilder.withoutFaction(sender.getPlayer());

        // Si le joueur n'a pas de faction
        if (sender.getFaction() == null) {
            /*TODO: Menu pour les joueurs sans faction
                Parcourir les factions
                    Selectionner une faction (Créer un nouveau type de bouton) (liste de toutes les factions)
                        Rejoindre
                        Infos (...)
                Créer une faction
                    Ajouter le joueur dans la liste @see ChatInputListener
            */

         // Si le joueur à une faction, mais n'est pas chef, ni officier
        } else if (!sender.isChief() && !sender.isOfficer()) {
            /*TODO: Menu pour les joueurs avec faction
                Parcourir les factions
                        Infos (...)
                Gestion des membres
                    Liste (...)
                Territoires
                    Carte
             */


        // Si le joueur à une faction, et est officier
        } else if (!sender.isChief()) {
            /*TODO: Menu pour les officiers de faction
                Parcourir les factions
                    Selectionner une faction (Créer un nouveau type de bouton) (liste de toutes les factions)
                        Infos (...)
                Membres
                    Inviter
                        Selectionner une membre (Créer un nouveau type de bouton) (liste de toutes les membres)
                    Gestion des membres
                        Selectionner une membre (Créer un nouveau type de bouton) (liste de toutes les membres)
                            Promouvoir
                            Expulser
                Territoires
                    Carte
                    Revendiquer un chunk (position actuelle)
                    Supprimer un chunk (position actuelle)
                    Infos (...)
            */

            // Si le joueur à une faction, et est chef
        } else {
            /*TODO: Menu pour les chef de faction
                Parcourir les factions
                    Selectionner une faction (Créer un nouveau type de bouton) (liste de toutes les factions)
                        infos (...)
                        Envoyer un message
                        Déclarer la guerre / Rompre l'alliance
                        Faire une alliance / Faire la paix
                Membres
                    Inviter
                        Selectionner une membre (Créer un nouveau type de bouton) (liste de toutes les membres)
                    Gestion des membres
                        Selectionner une membre (Créer un nouveau type de bouton) (liste de toutes les membres)
                            Promouvoir
                            Promouvoir chef (vous remplace)
                            Expulser
                Territoires
                    Carte
                    Revendiquer un chunk (position actuelle)
                    Supprimer un chunk (position actuelle)
                    Infos (...)
                 Diplomacie
                    Relations
                        Selectionner une faction (Créer un nouveau type de bouton) (liste de toutes les factions avec relation avec cette faction)
                            Envoyer un message
                            Rompre l'alliance/ Faire la paix
                    Messages reçus
                        Selectionner un message (Créer un nouveau type de bouton) (liste de tous les messages)
                            Infos (message)
                            Repondre
                    Envoyer un message
                        Selectionner une faction (Créer un nouveau type de bouton) (liste de toutes les factions)
            */
        }
    return  true;
    }
}

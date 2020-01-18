package fr.dwightstudio.skyfactions.CommandExecutors;

import fr.dwightstudio.skyfactions.Main;
import fr.dwightstudio.skyfactions.Menus.MenuBuilder;
import fr.dwightstudio.skyfactions.Objects.PlayerProfile;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

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
                Carte (On/Off)
            */

         // Si le joueur à une faction, mais n'est pas chef, ni officier
        } else if (!sender.isChief() && !sender.isOfficer()) {
            /*TODO: Menu pour les joueurs avec faction
                Parcourir les factions
                    Infos (...)
                Liste des membres
                    Liste (...)
                Carte (On/Off)
                Quitter la faction
                    Confirmer
             */


        // Si le joueur à une faction, et est officier
        } else if (!sender.isChief()) {
            /*TODO: Menu pour les officiers de faction
                Parcourir les factions
                    Selectionner une faction (Créer un nouveau type de bouton) (liste de toutes les factions)
                        Infos (...)
                Inviter
                    Selectionner un joueur (Créer un nouveau type de bouton) (liste de tous les joueurs)
                Gestion des membres
                    Selectionner un membre (Créer un nouveau type de bouton) (liste de tous les membres)
                        Changer le titre
                        Promouvoir
                        Expulser
                Carte (On/Off)
                Revendiquer un chunk (position actuelle)
                Supprimer un chunk (position actuelle)
                Rendre la faction ouverte/fermée
                Infos sur la faction (...)
                Quitter la faction
                    Confirmer
            */

            // Si le joueur à une faction, et est chef
        } else {
            /*TODO: Menu pour les chef de faction
                Parcourir les factions
                    Selectionner une faction (Créer un nouveau type de bouton) (liste de toutes les factions)
                        infos (...)
                        Déclarer la guerre / Rompre l'alliance
                        Faire une alliance / Faire la paix
                 Inviter
                    Selectionner un joueur (Créer un nouveau type de bouton) (liste de tous les joueurs)
                 Gestion des membres
                    Selectionner un membre (Créer un nouveau type de bouton) (liste de tous les membres)
                        Changer le titre
                        Promouvoir
                        Promouvoir chef (vous remplace)
                        Expulser
                Carte (On/Off)
                Revendiquer un chunk (position actuelle)
                Supprimer un chunk (position actuelle)
                Rendre la faction ouverte/fermée
                Renomer la faction
                Changer le diminutif (5 caractères maximums) (S'affiche devant le nom des membres)
                Changer la description
                Infos sur la faction (...)
                Dissoudre la faction
                    Confirmer
            */
        }
    return  true;
    }
}

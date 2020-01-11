package fr.skynnotopia.skyfactions.CommandExecutors;

import fr.skynnotopia.skyfactions.Actions.JoinFactionAction;
import fr.skynnotopia.skyfactions.Actions.NewFactionAction;
import fr.skynnotopia.skyfactions.Main;
import me.lyras.api.gui.link.Link;
import me.lyras.api.gui.permission.Permission;
import me.lyras.api.gui.permission.PermissionedPlayer;
import me.lyras.api.gui.ui.Listing;
import me.lyras.api.gui.ui.ListingManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CommandFaction implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender csender, Command command, String label, String[] args) {
        Player sender;

        if (csender instanceof Player) {
            sender = (Player) csender;
        } else {
            return false;
        }

        //Listing
        Listing listing;

        if (Main.loader.getPlayerProfile(sender.getUniqueId()).getFaction() == null) {
            listing = new Listing(27, "Faction : " + ChatColor.AQUA + "Aucune") {
                @Override
                public void load() {
                    ItemStack itemStack = new ItemStack(Material.SHIELD);
                    ItemMeta itemStackMeta = itemStack.getItemMeta();
                    itemStackMeta.setDisplayName("Rejoindre une faction");
                    itemStack.setItemMeta(itemStackMeta);
                    this.set(12, new Link(itemStack, new JoinFactionAction(sender)));

                    ItemStack itemStack2 = new ItemStack(Material.CRAFTING_TABLE);
                    ItemMeta itemStackMeta2 = itemStack2.getItemMeta();
                    itemStackMeta2.setDisplayName("Créer une faction");
                    itemStack2.setItemMeta(itemStackMeta2);
                    this.set(14, new Link(itemStack2, new NewFactionAction(sender)));
                }

            };
        } else {
                listing = new Listing(27, "Faction : " + ChatColor.AQUA + Main.loader.getPlayerProfile(sender.getUniqueId()).getFaction().getName()) {
                    @Override
                    public void load() {
                        this.set(12, new Link(new ItemStack(Material.PAPER, 1), new JoinFactionAction(sender)));
                        this.set(14, new Link(new ItemStack(Material.CRAFTING_TABLE, 1), new NewFactionAction(sender)));
                    }

                };
        }

        PermissionedPlayer permissioned = new PermissionedPlayer( sender , Permission.CLICK , Permission.CLOSE , Permission.OPEN );
        ListingManager.bind(listing, permissioned );
        listing.getOptions().setClosing( true );
        listing.open();
        return true;
    }
}

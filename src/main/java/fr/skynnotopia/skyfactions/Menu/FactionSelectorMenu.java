package fr.skynnotopia.skyfactions.Menu;

import fr.skynnotopia.skyfactions.Actions.FactionSelectAction;
import fr.skynnotopia.skyfactions.Actions.PlayerSelectAction;
import fr.skynnotopia.skyfactions.Objects.Faction;
import me.lyras.api.gui.action.NextAction;
import me.lyras.api.gui.action.PreviousAction;
import me.lyras.api.gui.link.Link;
import me.lyras.api.gui.permission.Permission;
import me.lyras.api.gui.permission.PermissionedPlayer;
import me.lyras.api.gui.ui.Listing;
import me.lyras.api.gui.ui.ListingManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;

public class FactionSelectorMenu {
    public FactionSelectorMenu(List<Faction> factions, Player player) {
        //Listing
        Listing listing;

        listing = new Listing(27, "Selectionner une faction") {
            @Override
            public void load() {
                List<Faction> factionsList = factions;
                for (int i = 0; i <= factionsList.size()/18; i++) {
                    if (i != 0) {
                        ItemStack itemStack = new ItemStack(Material.ARROW);
                        ItemMeta itemStackMeta = itemStack.getItemMeta();
                        itemStackMeta.setDisplayName("Page précedente");
                        this.set(i + 18, new Link(itemStack, new PreviousAction()));

                        ItemStack itemStack2 = new ItemStack(Material.ARROW);
                        ItemMeta itemStackMeta2 = itemStack2.getItemMeta();
                        itemStackMeta2.setDisplayName("Page suivante");
                        this.set(i-1, new Link(itemStack2, new NextAction()));
                    }


                    for (int e = 0; e <= 17 && e <= factionsList.size(); e++) {
                        ItemStack itemStack = new ItemStack(Material.SHIELD);
                        ItemMeta itemStackMeta = itemStack.getItemMeta();
                        itemStackMeta.setDisplayName(factionsList.get(e).getName());
                        this.set(e, new Link(itemStack, new FactionSelectAction(factionsList.get(e))));
                    }
                }
            }

        };

        PermissionedPlayer permissioned = new PermissionedPlayer( player , Permission.CLICK , Permission.CLOSE , Permission.OPEN );
        ListingManager.bind(listing, permissioned );
        listing.getOptions().setClosing( true );
        listing.open();
    }
}

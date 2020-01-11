package fr.skynnotopia.skyfactions.Menu;

import fr.skynnotopia.skyfactions.Actions.PlayerSelectAction;
import fr.skynnotopia.skyfactions.Main;
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
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class PlayerSelectorMenu {
    public PlayerSelectorMenu(List<Player> players, Player player) {
        //Listing
        Listing listing;

            listing = new Listing(27, "Selectionner un joueur") {
                @Override
                public void load() {
                    List<Player> playerList = players;
                    for (int i = 0; i <= playerList.size()/18; i++) {
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


                        for (int e = 0; e <= 17 && e <= playerList.size(); e++) {
                            this.set(e, new Link(getHead(playerList.remove(e)), new PlayerSelectAction(playerList.get(e))));
                        }
                    }
                }

            };

        PermissionedPlayer permissioned = new PermissionedPlayer( player , Permission.CLICK , Permission.CLOSE , Permission.OPEN );
        ListingManager.bind(listing, permissioned );
        listing.getOptions().setClosing( true );
        listing.open();
    }

    private ItemStack getHead(Player player) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setDisplayName(player.getName());
        ArrayList<String> lore = new ArrayList<String>();
        if (Main.loader.getPlayerProfile(player.getUniqueId()).getFaction() != null) {
            lore.add("Faction :" + Main.loader.getPlayerProfile(player.getUniqueId()).getFaction());
        }
        skull.setLore(lore);
        skull.setOwningPlayer(player);
        item.setItemMeta(skull);
        return item;
    }

}

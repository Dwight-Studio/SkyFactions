package fr.dwightstudio.skyfactions.Menus;

import brian.menuinterface.button.ButtonOptions;
import brian.menuinterface.button.DefaultButtons;
import brian.menuinterface.button.IMenuButton;
import brian.menuinterface.button.MenuButton;
import brian.menuinterface.design.MenuDesigner;
import brian.menuinterface.events.ButtonClickEvent;
import brian.menuinterface.types.StandardMenu;
import fr.dwightstudio.skyfactions.Listeners.ChatInputListener;
import fr.dwightstudio.skyfactions.Main;
import fr.dwightstudio.skyfactions.Objects.Faction;
import fr.dwightstudio.skyfactions.Objects.ItemBuilder;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

import static fr.dwightstudio.skyfactions.Main.messagePrefix;

public class MenuBuilder {

    public static void withoutFaction(Player player) {

        //Parent menu (Main Page)
        StandardMenu menu = StandardMenu.create(3, "Faction : " + ChatColor.AQUA + "Aucune");

        //Child menu (Parcourir les factions)
        StandardMenu factionsMenu = StandardMenu.create(3, "Parcourir les factions");
        int slot = 0;
        for (Faction faction : Main.loader.getFactions()) {
            StandardMenu factionMenu = StandardMenu.create(3, "Faction : " + faction.getName());
            MenuButton factionButton = new MenuButton(new ItemBuilder(Material.FILLED_MAP).setName(faction.getName()).addLoreLine(faction.getDescription()).toItemStack(),slot);
            factionsMenu.addChild("factionMenu",factionMenu);
            factionMenu.addButton(factionButton);
            slot++;
        }

        //Buttons
        MenuButton factionsButton = DefaultButtons.OPEN.getButtonOfItemStack(new ItemBuilder(Material.SHIELD).setName("Parcourir les factions").addLoreLine("Rejoindre ou candidater pour une Faction.").toItemStack(),
                "factionsMenu");
        factionsButton.setSlot(12);

        //FIXME: CA FONTIONNEL PAS JSP PK

        MenuButton mapButton = new MenuButton(new ItemBuilder(Material.FILLED_MAP).setName("Carte (ON/OFF)").addLoreLine("Activer/Désactiver l'affichage de la carte.").toItemStack(), 13);
        mapButton.setClickEvent(new Consumer<ButtonClickEvent>() {
            @Override
            public void accept(ButtonClickEvent event) {
                if (Main.mapPlayerList.contains(event.getWhoClicked())) {
                    Main.mapPlayerList.remove(event.getWhoClicked());
                } else {
                    Main.mapPlayerList.add(event.getWhoClicked());
                }
                event.getWhoClicked().closeInventory();
            }
        });

        MenuButton createFactionButton = new MenuButton(new ItemBuilder(Material.CRAFTING_TABLE).setName("Créer une faction").addLoreLine("Devenir chef d'une Faction.").toItemStack(), 14);
        createFactionButton.setClickEvent(new Consumer<ButtonClickEvent>() {
            @Override
            public void accept(ButtonClickEvent event) {
                ChatInputListener.factionCreationPhase1.add(event.getWhoClicked());
                event.getWhoClicked().sendMessage(messagePrefix + ChatColor.GOLD + "Indiquer le nom de votre Faction. "
                                + "Il doit être composé de 20 caractères ou moins et ne doit pas contenir de couleurs.");
                event.getWhoClicked().closeInventory();
            }
        });

        //Bind Buttons and Menus
        player.sendMessage(factionsButton.getIdentifier());
        menu.addButton(factionsButton);
        menu.addChild("factionsMenu",factionsMenu);
        menu.addButton(mapButton);
        menu.addButton(createFactionButton);

        player.openInventory(menu.build());

    }

}

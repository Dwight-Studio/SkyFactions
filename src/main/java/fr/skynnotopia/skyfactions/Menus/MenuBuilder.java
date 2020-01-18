package fr.skynnotopia.skyfactions.Menus;

import brian.menuinterface.button.DefaultButtons;
import brian.menuinterface.button.PagedButton;
import brian.menuinterface.design.MenuDesigner;
import brian.menuinterface.events.ButtonClickEvent;
import brian.menuinterface.types.PagedMenu;
import brian.menuinterface.types.StandardMenu;
import fr.skynnotopia.skyfactions.Main;
import fr.skynnotopia.skyfactions.Objects.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.function.Consumer;

public class MenuBuilder {

    public static void withoutFaction(Player player) {

        //Parent menu (Main Page)
        StandardMenu menu = StandardMenu.create(3, "&cSelect...");

        //Child menu (Staff)
        StandardMenu staffMenu = StandardMenu.create(3, "Staff");

        //Child menu (Members)
        StandardMenu membersMenu = StandardMenu.create(3, "Members");

        MenuDesigner mainDesign = MenuDesigner.create();

        player.openInventory(menu.build());

    }

}

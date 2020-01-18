package fr.dwightstudio.skyfactions.Menus;

import brian.menuinterface.design.MenuDesigner;
import brian.menuinterface.types.StandardMenu;
import org.bukkit.entity.Player;

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

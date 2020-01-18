package fr.dwightstudio.skyfactions.TabCompleters;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.List;

public class TabCompleterSkyFaction implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        List<String> c = new ArrayList<String>();
        if (args.length == 0) {
            c.add("reload");
            c.add("infos");
        }
        return c;
    }
}

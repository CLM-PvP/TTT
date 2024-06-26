package de.clmpvp.ttt.Commands;

import de.clmpvp.ttt.Util.ConfigLocation;
import de.clmpvp.ttt.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetupCommand implements CommandExecutor {

    private Main plugin;

    public SetupCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (p.hasPermission("tt.setup")) {
                if (args.length == 0) {
                    p.sendMessage(Main.use + "setup <lobby>");
                } else {
                     if (args[0].equalsIgnoreCase("lobby")) {
                         if (args.length == 1) {
                            new ConfigLocation(plugin, p.getLocation(), "Lobby").saveLocation();
                            p.sendMessage(Main.prefix + "Die Lobby wurde neu gesetzt");
                         } else {
                             p.sendMessage(Main.use + "setup <lobby>");
                         }
                     }
                }
            } else {
                p.sendMessage(Main.noperms);
            }
        }
        return false;
    }
}

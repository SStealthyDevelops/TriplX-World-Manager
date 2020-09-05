package core.triplxmc.world.commands.subcommands;

import core.triplxmc.world.commands.SubCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import triplx.core.api.chat.Color;

public class TeleportCommand implements SubCommand {

    @Override
    public void onExecute(CommandSender sender, String[] args) {

        if (!(sender instanceof Player)) {
            if (args.length < 2) {
                sender.sendMessage(Color.cc("&cPlease specify a player to teleport... /twm <player> <world>"));
                return;
            }
        }

        if (args.length == 1) {
            // twm teleport <world>
            if (Bukkit.getWorld(args[0]) == null) {
                sender.sendMessage(Color.cc("&cCould not find world " + args[0]));
                return;
            }

            Player p = (Player) sender;
            p.teleport(Bukkit.getWorld(args[0]).getSpawnLocation());
            p.sendMessage(Color.cc("&bTeleported you to &b&n" + args[0] + "&b."));
        }
        if (args.length == 2) {
            // twm teleport <player> <world>

            Player target = Bukkit.getPlayer(args[0]);
            if (target == null) {
                sender.sendMessage(Color.cc("&cCould not find player " + args[0]));
                return;
            }

            if (Bukkit.getWorld(args[1]) == null) {
                sender.sendMessage(Color.cc("&cCould not find world " + args[1]));
                return;
            }


            target.teleport(Bukkit.getWorld(args[1]).getSpawnLocation());
            sender.sendMessage(Color.cc("&a" + target.getName() + " was teleported to &b&n" + args[1]));
        }



    }

}

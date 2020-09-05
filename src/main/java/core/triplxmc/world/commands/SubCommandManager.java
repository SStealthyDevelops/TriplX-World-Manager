package core.triplxmc.world.commands;

import core.triplxmc.world.commands.subcommands.TeleportCommand;
import org.bukkit.command.CommandSender;
import triplx.core.api.chat.Color;

import java.util.HashMap;

public class SubCommandManager {

    private static HashMap<String, SubCommand> commands = new HashMap<>();

    public static void init() {
        commands.put("teleport", new TeleportCommand());
        commands.put("tp", new TeleportCommand());
    }

    public static String helpMessage = Color.cc("&cUsage: /twm <list/create/duplicate/teleport> (souce) <target>");

    public static void runCommand(CommandSender sender, String command, String[] args) {
        SubCommand cmd = commands.get(command.toLowerCase());
        if (cmd == null) {
            sender.sendMessage(helpMessage);
            return;
        }
        cmd.onExecute(sender, args);
    }

    public static SubCommand getCommand(String label) {
        for (String command : commands.keySet()) {
            if (command.equalsIgnoreCase(label)) {
                return commands.get(command);
            }
        }
        return null;
    }


}

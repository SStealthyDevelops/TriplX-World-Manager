package core.triplxmc.world.commands;

import org.bukkit.command.CommandSender;

public interface SubCommand {

    public void onExecute(CommandSender sender, String[] args);
}

package core.triplxmc.world.commands.subcommands;

import core.triplxmc.world.commands.SubCommand;
import core.triplxmc.world.manager.WorldManager;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import triplx.core.api.chat.Color;
import triplx.core.api.ranking.Rank;
import triplx.core.api.ranking.RankingManager;

public class ListCommand implements SubCommand {

    @Override
    public void onExecute(CommandSender sender, String[] args) {

        if (sender instanceof Player) {
            Player p = (Player) sender;
            if (!RankingManager.getRank(p).hasPermission(Rank.ADMIN)) {
                sender.sendMessage(Color.cc("&cYou do not have permission to do this!"));
                return;
            }

            TextComponent text = new TextComponent(Color.cc("&7--------- &dWORLDS &7---------"));
            for (World w : Bukkit.getWorlds()) {
                String s = String.valueOf(WorldManager.getInstance().isPersistent(w));
                TextComponent world = new TextComponent(Color.cc("\n&a" + w.getName() + " &c(persistent: " +  s + ")"));
                world.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Color.cc("&aTeleport to " + w.getName())).create()));
                world.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/twm tp " + w.getName()));
                text.addExtra(world);
            }
            ((Player)sender).spigot().sendMessage(text);
            return;
        }

        StringBuilder str = new StringBuilder();
        str.append(Color.cc("\n&7--------- &dWORLDS &7---------"));
        for (World w : Bukkit.getWorlds()){
            String s = String.valueOf(WorldManager.getInstance().isPersistent(w));
            String ss = Color.cc("\n&a" + w.getName() + " &c(persistent: " + s + ")");
            str.append(ss);
        }

        sender.sendMessage(Color.cc(str.toString()));



    }
}

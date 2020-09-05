package core.triplxmc.world.log.debug;

import org.bukkit.Bukkit;
import triplx.core.api.chat.Color;

public class Debugger {

    public static void debug(DebugLevel level, String message){
        Bukkit.getConsoleSender().sendMessage(Color.cc(level.getPrefix() + " " + message));
        if (level.isCritical()) {
            Bukkit.getServer().savePlayers();
            Bukkit.getServer().shutdown();
        }
    }

}

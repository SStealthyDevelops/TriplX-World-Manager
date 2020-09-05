package core.triplxmc.world.manager;

import core.triplxmc.world.file.FileManager;
import core.triplxmc.world.log.debug.DebugLevel;
import core.triplxmc.world.log.debug.Debugger;
import lombok.Getter;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WorldManager {

    @Getter
    private final List<TWorld> worlds;

    @Getter
    private static WorldManager instance;

    public WorldManager() {
        worlds = new ArrayList<>();
        instance = this;
    }

    public void addWorld(TWorld world) throws IOException {
        if (world == null) {
            Debugger.debug(DebugLevel.ERROR, "&cWorld addition could not be completed... World is null!");
            return;
        }
        worlds.add(world);
        FileManager.getInstance().createDirectory(world);
    }

    public void deleteWorld(String directory) {
        if (Bukkit.getWorld(directory) == null)  {
            Debugger.debug(DebugLevel.ERROR, "&cCould not find BWorld " + directory + "!");
            return;
        }
        Bukkit.unloadWorld(directory, false);
        FileManager.getInstance().delete(FileManager.getWorldFile(directory));
    }

}

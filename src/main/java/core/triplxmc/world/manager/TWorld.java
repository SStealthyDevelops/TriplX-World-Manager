package core.triplxmc.world.manager;

import lombok.Getter;
import org.bukkit.World;

@Getter
public class TWorld {

    private final String worldName;
    private final String directory;
    private final int playerLimit;
    private World original = null;

    public TWorld(String worldName, String directory, int playerLimit) {
        this.worldName = worldName;
        this.directory = directory;
        this.playerLimit = playerLimit;
    }

    public TWorld(String directory, int playerLimit) {
        this.directory = directory;
        this.worldName = directory;
        this.playerLimit = playerLimit;
    }

    public TWorld(World world, String newDirectory, int playerLimit) { // create a duplicate world for minigames or whatever
        this.worldName = world.getName();
        this.directory = newDirectory;
        this.playerLimit = playerLimit;
        this.original = world;
    }

}

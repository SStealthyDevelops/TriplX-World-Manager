package core.triplxmc.world.manager;

import lombok.Getter;
import org.bukkit.World;

@Getter
public class TWorld {

    private final String worldName;
    private final String directory;
    private final int playerLimit;
    private World original = null;
    private final boolean permanent;

    public TWorld(String worldName, String directory, int playerLimit, boolean permanent) {
        this.worldName = worldName;
        this.directory = directory;
        this.playerLimit = playerLimit;
        this.permanent = permanent;
    }

    public TWorld(String directory, int playerLimit, boolean permanent) {
        this.directory = directory;
        this.worldName = directory;
        this.playerLimit = playerLimit;
        this.permanent = permanent;
    }

    public TWorld(World world, String newDirectory, int playerLimit, boolean permanent) { // create a duplicate world for minigames or whatever
        this.worldName = world.getName();
        this.directory = newDirectory;
        this.playerLimit = playerLimit;
        this.original = world;
        this.permanent = permanent;
    }

}

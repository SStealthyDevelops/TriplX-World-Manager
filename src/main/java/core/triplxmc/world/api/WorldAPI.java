package core.triplxmc.world.api;

import core.triplxmc.world.manager.TWorld;
import core.triplxmc.world.manager.WorldManager;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.World;

import java.io.IOException;

@SuppressWarnings("unused")
/*
 *  Kind of a wrapper for the WorldManager.java
 */
public class WorldAPI {

    @Getter
    @Setter
    private static WorldAPI instance;


    /**
     *
     * @param world The origin world to copy
     * @param newDirectory The new world directory
     * @throws IOException Duplicating a folder or creating one can throw an IOException
     */
    public void duplicateWorld(World world, String newDirectory) throws IOException {
        WorldManager.getInstance().addWorld(new TWorld(world, newDirectory, 200, false));
    }


    /**
     *
     * @param directory The folder in which the world files are in
     */
    public void deleteWorld(String directory) {
        WorldManager.getInstance().deleteWorld(directory);
    }


    /**
     *
     * @param directory The folder in which the world files are in
     * @param persistent Whether or not the world should be deleted when the server stops
     *                   (true = will stay, false = will delete)
     */
    public void generateWorld(String directory, boolean persistent) throws IOException {
        WorldManager.getInstance().addWorld(new TWorld(directory, directory, 200, persistent));
    }


    /**
     *
     * @param world If you want full control of the world details
     * @throws IOException Duplicating a folder or creating one can throw an IOException
     */
    public void addWorld(TWorld world) throws IOException {
        WorldManager.getInstance().addWorld(world);
    }

}

package core.triplxmc.world.file;

import core.triplxmc.world.Core;
import core.triplxmc.world.log.debug.DebugLevel;
import core.triplxmc.world.log.debug.Debugger;
import core.triplxmc.world.manager.TWorld;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.io.*;
import java.util.Objects;

public class FileManager {

    @Getter
    @Setter
    private static FileManager instance;

    public void createDirectory(TWorld world) throws IOException { // dont create world if it already exists
        if (Bukkit.getWorld(world.getDirectory()) != null) {
            Debugger.debug(DebugLevel.ERROR, "&cWorld " + world.getDirectory() + " already exists. Please delete if first.");
            return;
        }


        delete(Objects.requireNonNull(getWorldFile(world.getDirectory())));
        Bukkit.getServer().createWorld(new WorldCreator(world.getDirectory()));
        if (world.getOriginal() != null) {
            World w = Bukkit.getWorld(world.getDirectory());
            Bukkit.getServer().unloadWorld(w, false);

            delete(Objects.requireNonNull(getWorldFile(world.getDirectory())));
            copyFolder(Objects.requireNonNull(getWorldFile(world.getOriginal().getName())), getWorldFile(world.getDirectory()));


        Bukkit.getServer().createWorld(new WorldCreator(world.getDirectory()));
        } else {
            if (Bukkit.getWorld(world.getDirectory()) == null) {
                Bukkit.createWorld(new WorldCreator(world.getDirectory()));
            }
            Bukkit.getServer().createChunkData(Bukkit.getServer().getWorld(world.getDirectory()));
        }

    }

    public static File getWorldFile(String worldName) {
        try {
            return new File(Core.getInstance().getDataFolder().getCanonicalPath() + "/../../" + worldName);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    @SuppressWarnings("all")
    public void delete(File file){
        try {
            if (file.isDirectory()) {
                for (File subFile : file.listFiles()) {
                    if(subFile.isDirectory()) {
                        delete(subFile);
                    } else {
                        subFile.delete();
                    }
                }
            }
            file.delete();
        } catch (Exception e) {
            Debugger.debug(DebugLevel.ERROR, "&cCould not delete file: " + file.getName());
            e.printStackTrace();
        }
    }



    @SuppressWarnings("ALL") private void copyFolder(File src, File dest) throws IOException {

        if (src.isDirectory()) {

            //if directory not exists, create it
            if (!dest.exists()) {
                dest.mkdir();
            }

            // list all the directory contents\
            String files[] = src.list();

            if (files != null) {
                for (String file : files) {
                    //construct the src and dest file structure
                    File srcFile = new File(src, file);
                    File destFile = new File(dest, file);
                    //recursive copy
                    if (!file.toString().contains("uid"))
                    copyFolder(srcFile, destFile);
                }
            }
        } else {
            // if file, then copy it
            // Use byte stream to support all file types
            InputStream in = new FileInputStream(src);
            OutputStream out = new FileOutputStream(dest);

            byte[] buffer = new byte[1024];

            int length;
            // copy the file content in bytes
            while ((length = in.read(buffer)) > 0){
                out.write(buffer, 0, length);
            }

            in.close();
            out.close();
        }
    }

}

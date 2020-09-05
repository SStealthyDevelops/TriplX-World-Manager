package core.triplxmc.world;

import core.triplxmc.world.commands.SubCommandManager;
import core.triplxmc.world.commands.TWMCommand;
import core.triplxmc.world.events.PlayerJoinQuit;
import core.triplxmc.world.file.FileManager;
import core.triplxmc.world.file.JSONManager;
import core.triplxmc.world.manager.TWorld;
import core.triplxmc.world.manager.WorldManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import triplx.core.api.chat.Color;

import java.util.Objects;

public class Core extends JavaPlugin {

    @Getter
    private static Core instance;

    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(Color.cc("&c[TRIPLX WORLD MANAGER] &7World manager intializing..."));
        instance = this;

        getConfig().options().copyDefaults(true);
        saveConfig();

        FileManager.setInstance(new FileManager());

        new WorldManager();


        JSONManager.setInstance(new JSONManager());
        JSONManager.getInstance().init();

        Bukkit.getConsoleSender().sendMessage(Color.cc("&7|--------------- LOADING WORLDS ------------------|"));
        Bukkit.getConsoleSender().sendMessage("");

        int i = 0;
        if (WorldManager.getInstance().getWorlds() == null || WorldManager.getInstance().getWorlds().size() == 0){
            Bukkit.getConsoleSender().sendMessage(Color.cc("&cError >> No worlds to laod!"));
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getServer().getConsoleSender().sendMessage(Color.cc("&7|---------------- 0 WORLDS LOADED ---------------|"));
        } else {
            for (TWorld world : WorldManager.getInstance().getWorlds()) {
                Bukkit.getConsoleSender().sendMessage(Color.cc("&7Loading world: " + world.getWorldName() + "..."));
                i++;
            }
            Bukkit.getConsoleSender().sendMessage("");
            Bukkit.getServer().getConsoleSender().sendMessage(Color.cc("&7|----------------" + i + " " + "WORLDS LOADED ---------------|"));
        }


        FileManager.setInstance(new FileManager());

        registerListeners();
        SubCommandManager.init();
        getCommand("twm").setExecutor(new TWMCommand());
    }

    @Override
    public void onDisable() {
        for (TWorld world : WorldManager.getInstance().getWorlds()) {
            if (!world.isPermanent()) {
                Bukkit.unloadWorld(world.getDirectory(), false);
                FileManager.getInstance().delete(FileManager.getWorldFile(world.getDirectory()));
            }
        }
    }

    private void registerListeners() {
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new PlayerJoinQuit(), this);
    }



}


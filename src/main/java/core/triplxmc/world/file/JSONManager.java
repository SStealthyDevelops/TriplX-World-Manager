package core.triplxmc.world.file;

import core.triplxmc.world.Core;
import core.triplxmc.world.log.debug.*;
import core.triplxmc.world.manager.TWorld;
import core.triplxmc.world.manager.WorldManager;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;

public class JSONManager {

    @Getter
    @Setter
    private static JSONManager instance;

    public void init() {
        try {
            File worldsFile = new File(Core.getInstance().getDataFolder(), "worlds.json");

            if (!worldsFile.exists()) {
                initFile(worldsFile);
            }

            JSONObject oj = (JSONObject) new JSONParser().parse(new InputStreamReader(new FileInputStream(worldsFile)));

            if (oj == null) {
                System.out.println("Failed to read worlds.json - ERROR CODE: 5x00001");
                return;
            }

            JSONArray array = (JSONArray) oj.get("worlds");



            for (Object o : array) {
                JSONObject object = (JSONObject) o;

                String name = object.get("name").toString();
                String directory = object.get("directory").toString();
                int playerLimit = Integer.parseInt(object.get("player-limit").toString());

                TWorld world = new TWorld(name, directory, playerLimit);
                WorldManager.getInstance().addWorld(world);
            }



        } catch (Exception e) {
            e.printStackTrace();
            Debugger.debug(DebugLevel.CRITICAL, "&c[WORLD MANAGER] Could not read worlds.json!");
        }
    }


    @SuppressWarnings("all") private void initFile(File file) {
        try {

            file.createNewFile();

            JSONObject obj1 = new JSONObject();
            JSONArray worldsArray = new JSONArray();

            // basic hub

            JSONObject hub = new JSONObject();
            hub.put("directory", "world");
            hub.put("name", "World 1");
            hub.put("player-limit", 50);

            worldsArray.add(hub);
            obj1.put("worlds", worldsArray);

            try (FileWriter fileWriter = new FileWriter(file)) {

                fileWriter.write(obj1.toJSONString());
                fileWriter.flush();

            } catch (IOException e){
                e.printStackTrace();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}



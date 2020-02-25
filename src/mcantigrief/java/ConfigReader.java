package mcantigrief.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class ConfigReader {
    public static final String SEPARATOR = ", ";
    private static ConfigReader instance;
    private Set<String> playerList = new HashSet();
    private String joinMessage = "Welcome! Since you have not been trusted yet, you will need to contact administration to gain building privileges";

    public Set removePlayer(String uuid) {
        playerList.remove(uuid);
        return playerList;
    }
    public Set addPlayer(String uuid) {
        playerList.add(uuid);
        return playerList;
    }
    public void start() {
        try {
            String currentDir = System.getProperty("user.dir");
            Path folderPath = Paths.get(currentDir + "/MCAntiGrief");
            File folderFile = new File(currentDir + "/MCAntiGrief");
            if (!Files.exists(folderPath)) {

                this.initiate();
            }
            FileInputStream input = new FileInputStream(folderFile + "/MCAntiGriefConfig.properties");
            Properties prop = new Properties();


            prop.load(input);
            String uuidString = prop.getProperty("UUID'sAllowed");

            if (uuidString != null) {
                for (String uuid : uuidString.split(SEPARATOR)) {
                    playerList.add(uuid);
                }
            }
            if (prop.getProperty("joinMessage") == null) {
                joinMessage = "Welcome! Since you have not been trusted yet, you will need to contact administration to gain building privileges";
            } else {
                joinMessage = prop.getProperty("joinMessage");
            }
            ConfigDataHandler configDataHandler = ConfigDataHandler.getInstance();
            configDataHandler.dataHandle(joinMessage, playerList);

        } catch (IOException ex) {

        }
    }

    public void initiate() {
        try {
            String currentDir = System.getProperty("user.dir");
            File folderFile = new File(currentDir + "/MCAntiGrief");
            folderFile.mkdirs();
            FileOutputStream output = new FileOutputStream(folderFile + "/MCAntiGriefConfig.properties");

            Properties prop = new Properties();
            prop.setProperty("UUID'sAllowed", "");
            prop.setProperty("joinMessage", joinMessage);
            prop.store(output, null);


        } catch (IOException ex) {
        }
    }

    public void close() {

        try {
            String currentDir = System.getProperty("user.dir");
            Path folderPath = Paths.get(currentDir + "/MCAntiGrief");
            File folderFile = new File(currentDir + "/MCAntiGrief");
            if (!Files.exists(folderPath)) {


                this.initiate();
            }
            FileOutputStream output = new FileOutputStream(folderFile + "/MCAntiGriefConfig.properties");
            Properties prop = new Properties();

            String playerListAsString = "";
            for (String player : playerList) {
                playerListAsString += player + SEPARATOR;
            }
            System.out.println(playerListAsString);
            prop.setProperty("UUID'sAllowed", playerListAsString);
            prop.setProperty("joinMessage", joinMessage);
            prop.store(output, null);
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }

    }

    public static synchronized ConfigReader getInstance() {
        if (instance == null) {

            instance = new ConfigReader();
            instance.start();
        }
        return instance;
    }
}

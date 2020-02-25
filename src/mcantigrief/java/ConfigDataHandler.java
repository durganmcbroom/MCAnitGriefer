package mcantigrief.java;

import java.util.HashSet;
import java.util.Set;

public class ConfigDataHandler {
    private static ConfigDataHandler instance;
    ConfigReader configReader = ConfigReader.getInstance();
    String joinMessage;
    private Set<String> playerList = new HashSet();
    public void addPlayer(String uuid) {
        playerList = configReader.addPlayer(uuid);
    }
    public Set getAllPlayers() {
        return playerList;
    }
    public void removePlayer(String uuid) {
        playerList = configReader.removePlayer(uuid);
    }
    public boolean testForPlayer(String uuid) {
        return playerList.contains(uuid);
    }
    public void dataHandle(String joinMessage, Set players) {
        this.joinMessage = joinMessage;
        this.playerList = players;
    }
    public static synchronized ConfigDataHandler getInstance() {
        if (instance == null) {
            instance = new ConfigDataHandler();
        }
        return instance;
    }
}

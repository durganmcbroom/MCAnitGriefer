package mcantigrief.java;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.UUID;

public class MCAntiGrief extends JavaPlugin implements Listener {
    ConfigReader configReader = ConfigReader.getInstance();
    ConfigDataHandler configDataHandler = ConfigDataHandler.getInstance();
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new JListeners(), this);
    }
    public void onDisable() {
      configReader.close();
    }
    public boolean onCommand(CommandSender sender,
                             Command command,
                             String label,
                             String[] args) {
        Player player = Bukkit.getServer().getPlayer(sender.getName());
        UUID uniqueID = player.getUniqueId();

        if (sender.isOp()) {
            if (command.getName().equalsIgnoreCase("addplayer")) {
                configDataHandler.addPlayer(uniqueID.toString());
                sender.sendMessage("§4[§6MCAntiGriefer§4]§r §2Successfully§r added §3"+ args[0] + "§r to the list");
            } else if (command.getName().equalsIgnoreCase("delplayer")) {
                configDataHandler.removePlayer(uniqueID.toString());
                sender.sendMessage("§4[§6MCAntiGriefer§4]§r §2Successfully§r §4deleted§r §3"+ args[0] + "§r from the list");
            }
        }

        return true;
    }




}

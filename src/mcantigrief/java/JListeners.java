package mcantigrief.java;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;


public class JListeners implements Listener {
    ConfigDataHandler configDataHandler = ConfigDataHandler.getInstance();
    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!configDataHandler.testForPlayer(event.getPlayer().getUniqueId().toString())) {
            event.setCancelled(true);
        }
    }
}
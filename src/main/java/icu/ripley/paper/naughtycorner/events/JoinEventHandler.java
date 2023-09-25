package icu.ripley.paper.naughtycorner.events;

import icu.ripley.paper.naughtycorner.util.BungeeUtilities;
import icu.ripley.paper.naughtycorner.util.CC;
import org.bukkit.BanList;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.io.IOException;

public class JoinEventHandler implements Listener {

    private final BungeeUtilities bungeeUtilities;
    private final FileConfiguration config;

    public JoinEventHandler(BungeeUtilities bungeeUtilities, FileConfiguration config) {
        this.bungeeUtilities = bungeeUtilities;
        this.config = config;
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event) {
        Player player = event.getPlayer();
        if (Bukkit.getServer().getBanList(BanList.Type.NAME).isBanned(player.getName())) {
            event.allow(); // allow login

            try {
                bungeeUtilities.sendPlayerToServer(event.getPlayer(), config.getString("jail-server-name"));
            } catch (IOException ioException){
                ioException.printStackTrace(); // yes I know. hop off my ass.
                player.kickPlayer(CC.translate(config.getString("failed-kick-message")));
            }
        }
    }

}

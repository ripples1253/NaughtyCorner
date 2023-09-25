package icu.ripley.paper.naughtycorner;

import icu.ripley.paper.naughtycorner.events.JoinEventHandler;
import icu.ripley.paper.naughtycorner.util.BungeeUtilities;
import org.bukkit.plugin.java.JavaPlugin;

public final class NaughtyCorner extends JavaPlugin {

    @Override
    public void onEnable() {
        this.saveDefaultConfigValues();
        this.saveDefaultConfig();

        // register bungee channel
        this.getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");

        this.getServer().getPluginManager().registerEvents(new JoinEventHandler(new BungeeUtilities(this), this.getConfig()), this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void saveDefaultConfigValues(){
        getConfig().addDefault("failed-kick-message", "&cNaughtyCorner > Unable to send you to Jail, so I've kicked you instead.");
        getConfig().addDefault("jail-server-name", "jail");
    }
}

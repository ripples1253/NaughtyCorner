package icu.ripley.paper.naughtycorner.util;

import icu.ripley.paper.naughtycorner.NaughtyCorner;
import org.bukkit.entity.Player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BungeeUtilities {

    private final NaughtyCorner plugin;

    public BungeeUtilities(NaughtyCorner plugin) {
        this.plugin = plugin;
    }

    public void sendPlayerToServer(Player player, String server) throws IOException {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(b);
        out.writeUTF("Connect");
        out.writeUTF(server);
        player.sendPluginMessage(plugin, "BungeeCord", b.toByteArray());
        b.close();
        out.close();
    }

}

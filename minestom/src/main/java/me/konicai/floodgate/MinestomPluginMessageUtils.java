package me.konicai.floodgate;

import net.minestom.server.MinecraftServer;
import org.geysermc.floodgate.platform.pluginmessage.PluginMessageUtils;

import java.util.UUID;

public class MinestomPluginMessageUtils extends PluginMessageUtils {

    @Override
    public boolean sendMessage(UUID player, String channel, byte[] data) {
        try {
            MinecraftServer.getConnectionManager().getPlayer(player).sendPluginMessage(channel, data);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

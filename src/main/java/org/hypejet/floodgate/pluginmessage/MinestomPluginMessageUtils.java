package org.hypejet.floodgate.pluginmessage;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import org.geysermc.floodgate.platform.pluginmessage.PluginMessageUtils;

import java.util.UUID;

public class MinestomPluginMessageUtils extends PluginMessageUtils {

    @Override
    public boolean sendMessage(UUID player, String channel, byte[] data) {
        try {
            Player minestomPlayer = MinecraftServer.getConnectionManager().getPlayer(player);
            if (minestomPlayer != null) {
                minestomPlayer.sendPluginMessage(channel, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}

package org.hypejet.floodgate.pluginmessage;

import org.hypejet.floodgate.FloodgateMinestom;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerPluginMessageEvent;
import org.geysermc.floodgate.pluginmessage.PluginMessageChannel;
import org.geysermc.floodgate.pluginmessage.PluginMessageRegistration;

public class MinestomPluginMessageRegistration implements PluginMessageRegistration {

    @Override
    public void register(PluginMessageChannel pluginMessageChannel) {
        FloodgateMinestom floodgateMinestom = FloodgateMinestom.getInstance();
        floodgateMinestom.getEventNode().addListener(PlayerPluginMessageEvent.class, event -> {
            Player player = event.getPlayer();
            String identifier = event.getIdentifier();
            byte[] message = event.getMessage();
            pluginMessageChannel.handleServerCall(message, player.getUuid(), identifier);
        });
    }
}

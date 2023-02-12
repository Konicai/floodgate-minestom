package me.konicai.floodgate.pluginmessage;

import lombok.RequiredArgsConstructor;
import net.minestom.server.entity.Player;
import net.minestom.server.event.player.PlayerPluginMessageEvent;
import net.minestom.server.extensions.Extension;
import org.geysermc.floodgate.pluginmessage.PluginMessageChannel;
import org.geysermc.floodgate.pluginmessage.PluginMessageRegistration;

@RequiredArgsConstructor
public class MinestomPluginMessageRegistration implements PluginMessageRegistration {

    private final Extension extension;

    @Override
    public void register(PluginMessageChannel pluginMessageChannel) {
        extension.getEventNode().addListener(PlayerPluginMessageEvent.class, event -> {
            Player player = event.getPlayer();
            String identifier = event.getIdentifier();
            byte[] message = event.getMessage();
            pluginMessageChannel.handleServerCall(message, player.getUuid(), identifier);
        });
    }
}

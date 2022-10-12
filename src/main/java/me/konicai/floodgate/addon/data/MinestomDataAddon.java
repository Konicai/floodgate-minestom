package me.konicai.floodgate.addon.data;

import io.netty.channel.Channel;
import org.geysermc.floodgate.api.inject.InjectorAddon;

public class MinestomDataAddon implements InjectorAddon {

    @Override
    public void onInject(Channel channel, boolean toServer) {

    }

    @Override
    public void onRemoveInject(Channel channel) {

    }

    @Override
    public boolean shouldInject() {
        return false;
    }
}

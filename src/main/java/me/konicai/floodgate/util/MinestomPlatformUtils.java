package me.konicai.floodgate.util;

import net.minestom.server.MinecraftServer;
import org.geysermc.floodgate.platform.util.PlatformUtils;

public class MinestomPlatformUtils extends PlatformUtils {

    @Override
    public AuthType authType() {
        return AuthType.PROXIED; //fixme
    }

    @Override
    public String minecraftVersion() {
        return MinecraftServer.VERSION_NAME;
    }

    @Override
    public String serverImplementationName() {
        return MinecraftServer.getBrandName();
    }
}

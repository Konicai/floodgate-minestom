package me.konicai.floodgate.util;

import com.google.inject.Singleton;
import net.minestom.server.MinecraftServer;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.extras.bungee.BungeeCordProxy;
import net.minestom.server.extras.velocity.VelocityProxy;
import org.geysermc.floodgate.platform.util.PlatformUtils;

@Singleton
public class MinestomPlatformUtils extends PlatformUtils {

    @Override
    public AuthType authType() {
        if (BungeeCordProxy.isEnabled() || VelocityProxy.isEnabled()) {
            return AuthType.PROXIED;
        }

        return MojangAuth.isEnabled() ? AuthType.ONLINE : AuthType.OFFLINE;
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

package me.konicai.floodgate.module;

import com.google.inject.AbstractModule;
import lombok.RequiredArgsConstructor;
import me.konicai.floodgate.logger.Slf4jFloodgateLogger;
import me.konicai.floodgate.util.MinestomPlatformUtils;
import net.minestom.server.extensions.Extension;
import org.geysermc.floodgate.api.logger.FloodgateLogger;
import org.geysermc.floodgate.platform.util.PlatformUtils;
import org.slf4j.Logger;

@RequiredArgsConstructor
public class MinestomPlatformModule extends AbstractModule {
    private final Extension extension;

    @Override
    protected void configure() {
        bind(PlatformUtils.class).to(MinestomPlatformUtils.class);
        bind(Logger.class).toInstance(extension.getLogger());
        bind(FloodgateLogger.class).to(Slf4jFloodgateLogger.class);
    }
}

package org.hypejet.floodgate;

import com.google.inject.Guice;
import com.google.inject.Injector;
import lombok.Getter;
import org.geysermc.floodgate.config.FloodgateConfig;
import org.hypejet.floodgate.module.MinestomAddonModule;
import org.hypejet.floodgate.module.MinestomPlatformModule;
import net.minestom.server.extensions.Extension;
import org.geysermc.floodgate.FloodgatePlatform;
import org.geysermc.floodgate.api.logger.FloodgateLogger;
import org.geysermc.floodgate.module.CommandModule;
import org.geysermc.floodgate.module.PluginMessageModule;
import org.geysermc.floodgate.module.ServerCommonModule;

@EntryPoint
public class FloodgateMinestom extends Extension {

    @Getter
    private static FloodgateMinestom instance;
    private Injector injector;
    private FloodgatePlatform platform;

    @Override
    public void initialize() {
        instance = this;
        long ctm = System.currentTimeMillis();
        injector = Guice.createInjector(
                new ServerCommonModule(getDataDirectory()),
                new MinestomPlatformModule(this)
        );

        platform = injector.getInstance(FloodgatePlatform.class);
        injector.getInstance(FloodgateLogger.class)
                .translatedInfo("floodgate.core.finish", System.currentTimeMillis() - ctm);
    }

    @Override
    public void postInitialize() {
        platform.enable(
            new CommandModule(),
            new MinestomAddonModule(),
            new PluginMessageModule()
        );
    }

    @Override
    public void terminate() {
        platform.disable();
    }
}

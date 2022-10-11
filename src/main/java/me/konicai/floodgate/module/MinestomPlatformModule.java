package me.konicai.floodgate.module;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import lombok.RequiredArgsConstructor;
import me.konicai.floodgate.injector.minestom.MinestomInjector;
import me.konicai.floodgate.logger.Slf4jFloodgateLogger;
import me.konicai.floodgate.pluginmessage.MinestomPluginMessageRegistration;
import me.konicai.floodgate.pluginmessage.MinestomPluginMessageUtils;
import me.konicai.floodgate.pluginmessage.MinestomSkinApplier;
import me.konicai.floodgate.util.MinestomCommandUtil;
import me.konicai.floodgate.util.MinestomPlatformUtils;
import net.minestom.server.event.EventListener;
import net.minestom.server.extensions.Extension;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.api.logger.FloodgateLogger;
import org.geysermc.floodgate.inject.CommonPlatformInjector;
import org.geysermc.floodgate.platform.command.CommandUtil;
import org.geysermc.floodgate.platform.listener.ListenerRegistration;
import org.geysermc.floodgate.platform.pluginmessage.PluginMessageUtils;
import org.geysermc.floodgate.platform.util.PlatformUtils;
import org.geysermc.floodgate.pluginmessage.PluginMessageRegistration;
import org.geysermc.floodgate.skin.SkinApplier;
import org.geysermc.floodgate.util.LanguageManager;
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

    @Provides
    @Singleton
    public Extension extension() {
        return extension;
    }

    /*
    Commands / Listeners
     */

    @Provides
    @Singleton
    public CommandUtil commandUtil(LanguageManager languageManager, FloodgateApi api) {
        return new MinestomCommandUtil(languageManager, api);
    }

    @Provides
    @Singleton
    public ListenerRegistration<EventListener<?>> listenerRegistration() {
        throw new UnsupportedOperationException(); //fixme
    }

    /*
    PlatformInjector
     */

    @Provides
    @Singleton
    public CommonPlatformInjector platformInjector() {
        return new MinestomInjector();
    }

    @Provides
    @Named("implementationName")
    public String implementationName() {
        return "Minestom";
    }
    /*
    Others
     */

    @Provides
    @Singleton
    public PluginMessageUtils pluginMessageUtils() {
        return new MinestomPluginMessageUtils();
    }

    @Provides
    @Singleton
    public PluginMessageRegistration pluginMessageRegister() {
        return new MinestomPluginMessageRegistration();
    }

    @Provides
    @Singleton
    public SkinApplier skinApplier() {
        return new MinestomSkinApplier();
    }
}

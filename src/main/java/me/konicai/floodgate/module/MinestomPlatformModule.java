package me.konicai.floodgate.module;

import cloud.commandframework.CommandManager;
import cloud.commandframework.execution.CommandExecutionCoordinator;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import io.github.openminigameserver.cloudminestom.MinestomCommandManager;
import lombok.RequiredArgsConstructor;
import me.konicai.floodgate.logger.Slf4jFloodgateLogger;
import me.konicai.floodgate.util.MinestomCommandUtil;
import me.konicai.floodgate.util.MinestomPlatformUtils;
import me.konicai.floodgate.injector.minestom.MinestomInjector;
import me.konicai.floodgate.pluginmessage.MinestomPluginMessageRegistration;
import me.konicai.floodgate.pluginmessage.MinestomPluginMessageUtils;
import me.konicai.floodgate.pluginmessage.MinestomSkinApplier;
import net.minestom.server.command.CommandSender;
import net.minestom.server.event.EventListener;
import net.minestom.server.extensions.Extension;
import org.geysermc.floodgate.api.logger.FloodgateLogger;
import org.geysermc.floodgate.inject.CommonPlatformInjector;
import org.geysermc.floodgate.platform.command.CommandUtil;
import org.geysermc.floodgate.platform.listener.ListenerRegistration;
import org.geysermc.floodgate.platform.pluginmessage.PluginMessageUtils;
import org.geysermc.floodgate.platform.util.PlatformUtils;
import org.geysermc.floodgate.player.FloodgateCommandPreprocessor;
import org.geysermc.floodgate.player.UserAudience;
import org.geysermc.floodgate.pluginmessage.PluginMessageRegistration;
import org.geysermc.floodgate.skin.SkinApplier;
import org.slf4j.Logger;

@RequiredArgsConstructor
public class MinestomPlatformModule extends AbstractModule {
    private final Extension extension;

    @Override
    protected void configure() {
        bind(CommandUtil.class).to(MinestomCommandUtil.class);
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
    public CommandManager<UserAudience> commandManager(CommandUtil commandUtil) {
        CommandManager<UserAudience> commandManager = new MinestomCommandManager<>(
            CommandExecutionCoordinator.simpleCoordinator(),
            commandUtil::getUserAudience,
            audience -> (CommandSender) audience.source()
        );
        commandManager.registerCommandPreProcessor(new FloodgateCommandPreprocessor<>(commandUtil));
        return commandManager;
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
    @Named("packetEncoder")
    public String packetEncoder() {
        return "dummy-encoder";
    }

    @Provides
    @Named("packetDecoder")
    public String packetDecoder() {
        return "dummy-decoder";
    }

    @Provides
    @Named("packetHandler")
    public String packetHandler() {
        return "dummy-handler";
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
        return new MinestomPluginMessageRegistration(extension);
    }

    @Provides
    @Singleton
    public SkinApplier skinApplier() {
        return new MinestomSkinApplier();
    }
}

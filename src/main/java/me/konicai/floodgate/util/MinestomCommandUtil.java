package me.konicai.floodgate.util;

import com.google.inject.Inject;
import net.minestom.server.MinecraftServer;
import net.minestom.server.adventure.MinestomAdventure;
import net.minestom.server.adventure.audience.Audiences;
import net.minestom.server.command.CommandSender;
import net.minestom.server.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.geysermc.floodgate.api.FloodgateApi;
import org.geysermc.floodgate.platform.command.CommandUtil;
import org.geysermc.floodgate.player.UserAudience;
import org.geysermc.floodgate.player.UserAudience.ConsoleAudience;
import org.geysermc.floodgate.player.UserAudience.PlayerAudience;
import org.geysermc.floodgate.util.LanguageManager;
import org.geysermc.floodgate.util.Utils;

import java.util.Collection;
import java.util.Locale;
import java.util.UUID;

public class MinestomCommandUtil extends CommandUtil {
    private final UserAudience console = new ConsoleAudience(Audiences.console(), this);

    @Inject
    public MinestomCommandUtil(LanguageManager manager, FloodgateApi api) {
        super(manager, api);
    }

    @Override
    public @NonNull UserAudience getUserAudience(@NonNull Object source) {
        if (!(source instanceof CommandSender sender)) {
            throw new IllegalArgumentException("Can only work with CommandSender!");
        }

        if (!(sender instanceof Player player)) {
            return console;
        }

        UUID uuid = player.getUuid();
        String username = player.getUsername();
        Locale locale = player.getLocale();
        String localeTag = Utils.getLocale(locale != null ? locale : MinestomAdventure.getDefaultLocale());
        return new PlayerAudience(uuid, username, localeTag, sender, this, true);
    }

    @Override
    protected String getUsernameFromSource(@NonNull Object source) {
        return ((Player) source).getUsername();
    }

    @Override
    protected UUID getUuidFromSource(@NonNull Object source) {
        return ((Player) source).getUuid();
    }

    @Override
    protected Collection<?> getOnlinePlayers() {
        return MinecraftServer.getConnectionManager().getOnlinePlayers();
    }

    @Override
    public Object getPlayerByUuid(@NonNull UUID uuid) {
        Player player = MinecraftServer.getConnectionManager().getPlayer(uuid);
        return player == null ? uuid : player;
    }

    @Override
    public Object getPlayerByUsername(@NonNull String username) {
        Player player = MinecraftServer.getConnectionManager().getPlayer(username);
        return player == null ? username : player;
    }

    @Override
    public boolean hasPermission(Object source, String permission) {
        return ((CommandSender) source).hasPermission(permission);
    }

    @Override
    public void sendMessage(Object source, String message) {
        ((CommandSender) source).sendMessage(message);
    }

    @Override
    public void kickPlayer(Object source, String message) {
        if (source instanceof Player player) {
            player.kick(message);
        }
    }
}

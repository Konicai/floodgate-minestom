package me.konicai.floodgate.pluginmessage;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.timer.TaskSchedule;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.geysermc.floodgate.skin.SkinApplier;

import static org.geysermc.floodgate.api.event.skin.SkinApplyEvent.SkinData;

public class MinestomSkinApplier implements SkinApplier {

    @Override
    public void applySkin(@NonNull FloodgatePlayer floodgatePlayer, @NonNull SkinData skinData) {
        applySkin0(floodgatePlayer, skinData, true);
    }

    private void applySkin0(FloodgatePlayer floodgatePlayer, SkinData skinData, boolean firstTry) {
        Player player = MinecraftServer.getConnectionManager().getPlayer(floodgatePlayer.getCorrectUniqueId());

        // player is probably not logged in yet
        if (player == null) {
            if (firstTry) {
                MinecraftServer.getSchedulerManager().scheduleTask(() -> applySkin0(floodgatePlayer, skinData, false),
                        TaskSchedule.tick(10 * 1000), TaskSchedule.stop());
            }
            return;
        }

        player.setSkin(new PlayerSkin(skinData.value(), skinData.signature()));
    }
}
package me.konicai.floodgate.pluginmessage;

import net.minestom.server.MinecraftServer;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import org.geysermc.floodgate.api.player.FloodgatePlayer;
import org.geysermc.floodgate.skin.SkinApplier;
import org.geysermc.floodgate.skin.SkinData;

public class MinestomSkinApplier implements SkinApplier {

    @Override
    public void applySkin(FloodgatePlayer floodgatePlayer, SkinData skinData) {
        Player player = MinecraftServer.getConnectionManager().getPlayer(floodgatePlayer.getCorrectUniqueId());

        if (player == null) {
            throw new IllegalStateException(); //fixme, try again later
        }

        player.setSkin(new PlayerSkin(skinData.getValue(), skinData.getSignature()));
    }

    @Override
    public boolean hasSkin(FloodgatePlayer floodgatePlayer) {
        Player player = MinecraftServer.getConnectionManager().getPlayer(floodgatePlayer.getCorrectUniqueId());

        if (player == null) {
            return false;
        }

        return player.getSkin() != null;
    }
}
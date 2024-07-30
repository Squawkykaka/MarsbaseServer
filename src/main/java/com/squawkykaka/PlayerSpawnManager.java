package com.squawkykaka;

import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.player.PlayerSpawnEvent;

import java.util.function.Consumer;

public class PlayerSpawnManager {
    static Consumer<PlayerSpawnEvent> getPlayerSpawnEventConsumer() {
        return event -> {
            final Player player = event.getPlayer();
            player.setGameMode(GameMode.CREATIVE);
            PlayerSkin skinFromUsername = PlayerSkin.fromUsername(player.getUsername());
            player.setSkin(skinFromUsername);
            player.setFlying(true);
            player.setPermissionLevel(4);
        };
    }
}

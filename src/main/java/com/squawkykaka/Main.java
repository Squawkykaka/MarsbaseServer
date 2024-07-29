package com.squawkykaka;

import com.squawkykaka.commands.TeleportCommand;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.*;
import net.minestom.server.instance.*;
import net.minestom.server.instance.block.Block;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();

        MinecraftServer.getCommandManager().register(new TeleportCommand());

        // Create the instance
        InstanceManager instanceManager = MinecraftServer.getInstanceManager();
        InstanceContainer instance = instanceManager.createInstanceContainer();
        instance.setChunkSupplier(LightingChunk::new);

        instance.setGenerator(new TestGenerator());

        // Add an event callback to specify the spawning instance (and the spawn position)
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
            event.setSpawningInstance(instance);
            player.setRespawnPoint(new Pos(0, 42, 0));
        });

        globalEventHandler.addListener(PlayerSpawnEvent.class, event -> {
            final Player player = event.getPlayer();
            player.setAllowFlying(true);
            player.setItemInMainHand(ItemStack.of(Material.TNT));
            player.setGameMode(GameMode.CREATIVE);
            PlayerSkin skinFromUsername = PlayerSkin.fromUsername(player.getUsername());
            player.setSkin(skinFromUsername);
        });

        globalEventHandler.addListener(PlayerBlockInteractEvent.class, event -> {
            final Player player = event.getPlayer();
            if (event.getBlock() == Block.TNT) {
                event.setCancelled(true);
                player.setItemInMainHand(ItemStack.of(Material.ACACIA_BOAT));
            }
        });

        // Start the server on port 25565
        minecraftServer.start("0.0.0.0", 25565);
    }

    public static class TestGenerator implements Generator {

        @Override
        public void generate(@NotNull GenerationUnit unit) {
            Random random = new Random();
            Point start = unit.absoluteStart();

            unit.modifier().fillHeight(0, 40, Block.STONE);
        }
    }
}
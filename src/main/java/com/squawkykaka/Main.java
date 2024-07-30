package com.squawkykaka;

import com.squawkykaka.Generation.MarsGenerator;
import com.squawkykaka.commands.GamemodeCommand;
import com.squawkykaka.commands.SkinCommand;
import com.squawkykaka.commands.TeleportCommand;
import de.articdive.jnoise.core.api.modifiers.NoiseModifier;
import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.minestom.server.MinecraftServer;
import net.minestom.server.command.CommandManager;
import net.minestom.server.coordinate.Point;
import net.minestom.server.entity.GameMode;
import net.minestom.server.entity.Player;
import net.minestom.server.entity.PlayerSkin;
import net.minestom.server.event.GlobalEventHandler;
import net.minestom.server.event.player.*;
import net.minestom.server.event.trait.ItemEvent;
import net.minestom.server.extras.MojangAuth;
import net.minestom.server.instance.*;
import net.minestom.server.instance.block.Block;
import net.minestom.server.coordinate.Pos;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;
import net.minestom.server.item.ItemStack;
import net.minestom.server.item.Material;
import org.jetbrains.annotations.NotNull;

import java.util.Random;
import java.util.function.Consumer;
import java.util.logging.Logger;

import static com.squawkykaka.PlayerSpawnManager.getPlayerSpawnEventConsumer;

public class Main {
    public static final ComponentLogger LOGGER = ComponentLogger.logger(MinecraftServer.class);
    public static InstanceManager instanceManager = MinecraftServer.getInstanceManager();

    public static void main(String[] args) {
        MinecraftServer minecraftServer = MinecraftServer.init();

        CommandManager commandManager = MinecraftServer.getCommandManager();
        commandManager.register(new TeleportCommand());
        commandManager.register(new GamemodeCommand());
        commandManager.register(new SkinCommand());

        InstanceContainer instance = instanceManager.createInstanceContainer();

        // Create the instance
//        InstanceContainer lobby = instanceManager.createInstanceContainer();
//        lobby.setChunkSupplier(LightingChunk::new);
//        lobby.setGenerator(unit -> {
//            unit.modifier().fillHeight(0, 319, Block.AIR);
//        });
        instance.setChunkSupplier(LightingChunk::new);

        instance.setGenerator(new MarsGenerator());

        // Add an event callback to specify the spawning instance (and the spawn position)
        GlobalEventHandler globalEventHandler = MinecraftServer.getGlobalEventHandler();
        globalEventHandler.addListener(AsyncPlayerConfigurationEvent.class, event -> {
            final Player player = event.getPlayer();
//            event.setSpawningInstance(lobby);
            event.setSpawningInstance(instance);
            player.setRespawnPoint(new Pos(0, 70, 0));
        });

        globalEventHandler.addListener(PlayerSpawnEvent.class, getPlayerSpawnEventConsumer());

        // Start the server on port 25565
//        MojangAuth.init();
        minecraftServer.start("0.0.0.0", 25565);
        LOGGER.info((Component) Main.instanceManager.getInstances());
    }
}
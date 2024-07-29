package com.squawkykaka.Generation;

import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.generators.noisegen.worley.WorleyNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.InstanceContainer;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;

public class MarsGenerator implements Generator {
    public static final ComponentLogger LOGGER = ComponentLogger.logger(MinecraftServer.class);

    public void generate(GenerationUnit unit) {
        JNoise worldNoise = JNoise.newBuilder()
                .fastSimplex(FastSimplexNoiseGenerator.newBuilder().build())
                .scale(0.010)
                .build();
        JNoise textureNoise = JNoise.newBuilder()
                .fastSimplex(FastSimplexNoiseGenerator.newBuilder().build())
                .scale(0.1)
                .build();
        JNoise worleyNoise = JNoise.newBuilder()
                .worley(WorleyNoiseGenerator.newBuilder().build())
                .scale(0.015)
                .build();

        Point start = unit.absoluteStart();


        for (int x = 0; x < unit.size().x(); x++) {
            for (int z = 0; z < unit.size().z(); z++) {
                Point bottom = start.add(x, 0, z);
                double worldHeight = worldNoise.evaluateNoise(bottom.x(), bottom.z()) * 16;
                double worleyheight = worleyNoise.evaluateNoise(bottom.x(), bottom.z()) * 32;
                double textureHeight = textureNoise.evaluateNoise(bottom.x(), bottom.z());

                double height = worleyheight + worldHeight;

                Block blockType;
                if (textureHeight > 0.7) {
                    blockType = Block.RED_SAND;
                } else if (textureHeight > 0 && height < 0.7*16) {
                    blockType= Block.ORANGE_TERRACOTTA;
                } else if (height > 0.1*16 && textureHeight < 0.4) {
                    blockType = Block.YELLOW_TERRACOTTA;
                } else {
                    blockType = Block.RED_SANDSTONE;
                }
                unit.modifier().fill(bottom, bottom.add(1, 0, 1).withY(height), blockType);
                unit.modifier().fill(bottom, bottom.add(1, 0, 1).withY(height-5), Block.STONE);

                if (x == 1 && z == 0) {
                    unit.modifier().fill(bottom, bottom.add(1, 0, 1).withY(128), Block.AMETHYST_BLOCK);
                }

            }
        }
    }
}

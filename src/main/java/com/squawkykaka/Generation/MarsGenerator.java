package com.squawkykaka.Generation;

import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import net.minestom.server.MinecraftServer;
import net.minestom.server.coordinate.Point;
import net.minestom.server.instance.block.Block;
import net.minestom.server.instance.generator.GenerationUnit;
import net.minestom.server.instance.generator.Generator;

public class MarsGenerator implements Generator {
    public static final ComponentLogger LOGGER = ComponentLogger.logger(MinecraftServer.class);

    public void generate(GenerationUnit unit) {
        JNoise noise = JNoise.newBuilder()
                .fastSimplex(FastSimplexNoiseGenerator.newBuilder().build())
                .scale(0.010)
                .build();
        JNoise textureNoise = JNoise.newBuilder()
                .fastSimplex(FastSimplexNoiseGenerator.newBuilder().build())
                .scale(0.1)
                .build();

        Point start = unit.absoluteStart();


        for (int x = 0; x < unit.size().x(); x++) {
            for (int z = 0; z < unit.size().z(); z++) {
                Point bottom = start.add(x, 0, z);
                double height = noise.evaluateNoise(bottom.x(), bottom.z()) * 16;
                double textureHeight = textureNoise.evaluateNoise(bottom.x(), bottom.z());

                Block blockType;
                if (textureHeight > 0) {
                    blockType = Block.RED_SAND;
                } else {
                    blockType = Block.RED_SANDSTONE;
                }
                unit.modifier().fill(bottom, bottom.add(1, 1, 1).withY(height), blockType);

            }
        }


    }
}

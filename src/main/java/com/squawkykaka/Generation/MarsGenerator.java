package com.squawkykaka.Generation;

import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.generators.noisegen.perlin.PerlinNoiseGenerator;
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
        Point start = unit.absoluteStart();

        for (int x = 0; x < unit.size().x(); x++) {
            for (int z = 0; z < unit.size().z(); z++) {
                Point bottom = start.add(x, 0, z);
                double height = GeneratorNoise.getWorldNoiseAtPosition(bottom.x(), bottom.z()) * 16 ;
                double textureHeight = GeneratorNoise.getTextureNoiseAtPosition(bottom.x(), bottom.z());

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
//                }
            }
        }
    }
}

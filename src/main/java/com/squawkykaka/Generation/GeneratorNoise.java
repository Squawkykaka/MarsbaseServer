package com.squawkykaka.Generation;

import de.articdive.jnoise.core.api.modifiers.NoiseModifier;
import de.articdive.jnoise.core.util.MathUtil;
import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.generators.noisegen.perlin.PerlinNoiseGenerator;
import de.articdive.jnoise.generators.noisegen.worley.WorleyNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;
import net.minestom.server.utils.MathUtils;

import java.util.Random;

public class GeneratorNoise {
    public static double getTextureNoiseAtPosition(double x, double y) {
        JNoise simplexNoise = JNoise.newBuilder()
                .fastSimplex(FastSimplexNoiseGenerator.newBuilder().build())
                .scale(0.1)
                .build();

        return simplexNoise.evaluateNoise(x, y);
    }

    public static double getWorldNoiseAtPosition(double x, double y) {
        JNoise simplexNoise = JNoise.newBuilder()
                .fastSimplex(FastSimplexNoiseGenerator.newBuilder().build())
                .scale(0.01)
                .build();

        var simplexOutput = simplexNoise.evaluateNoise(x, y);

        // make the flat areas
        if (simplexOutput < 0.2 && simplexOutput > 0.1) {
            return simplexOutput*(simplexOutput*2);
        } else if (simplexOutput < 0.1) {
            return simplexOutput*2;
        }

        // The code to make the small mountains.
        return simplexOutput*24;
    }
}
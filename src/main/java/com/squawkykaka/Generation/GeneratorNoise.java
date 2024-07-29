package com.squawkykaka.Generation;

import com.squawkykaka.utils.MathUtils;
import de.articdive.jnoise.core.util.MathUtil;
import de.articdive.jnoise.generators.noisegen.opensimplex.FastSimplexNoiseGenerator;
import de.articdive.jnoise.generators.noisegen.perlin.PerlinNoiseGenerator;
import de.articdive.jnoise.generators.noisegen.worley.WorleyNoiseGenerator;
import de.articdive.jnoise.pipeline.JNoise;

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

        JNoise worleyNoise = JNoise.newBuilder()
                .worley(WorleyNoiseGenerator.newBuilder().build())
                .scale(0.05)
                .build();

        return simplexNoise.evaluateNoise(x, y) + worleyNoise.evaluateNoise(x, y) * 2;
    }
}
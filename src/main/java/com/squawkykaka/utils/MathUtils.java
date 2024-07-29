package com.squawkykaka.utils;

public class MathUtils {
    /**
     * Calculates the inverse lerp (inverse linear interpolation).
     *
     * @param min   The start of the range.
     * @param max   The end of the range.
     * @param value The value within the range.
     * @return The normalized value between 0 and 1.
     */
    public static double inverseLerp(double min, double max, double value) {
        if (min == max) {
            return 0.0; // Avoid division by zero
        }
        double t = (value - min) / (max - min);
        return Math.max(0.0, Math.min(1.0, t)); // Clamping the result between 0 and 1
    }
}

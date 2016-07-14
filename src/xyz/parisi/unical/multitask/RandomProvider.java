package xyz.parisi.unical.multitask;

import java.util.Random;

abstract public class RandomProvider {
    static private Random rand = new Random();

    public static double getNextExponential() {
        return Math.log(1 - rand.nextDouble()) / (-1);
    }

    public static boolean getNextBoolean() {
        return rand.nextBoolean();
    }

    public static int nextInt(int bound) {
        return rand.nextInt(bound);
    }
}

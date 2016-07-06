package xyz.parisi.test.multitask.arrowsgame;

import java.util.Random;

/**
 * Created by daniele on 7/6/16.sa
 */
enum Direction {
    LEFT,
    RIGHT;
    static Direction getRandom() {
        return (new Random().nextBoolean()) ? LEFT : RIGHT;
    }
}

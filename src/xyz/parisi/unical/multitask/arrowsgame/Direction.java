package xyz.parisi.unical.multitask.arrowsgame;

import xyz.parisi.unical.multitask.RandomProvider;


enum Direction {
    LEFT,
    RIGHT;
    static Direction getRandom() {
        return (RandomProvider.getNextBoolean() ? LEFT : RIGHT);
    }
}

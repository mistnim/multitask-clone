package xyz.parisi.test.multitask.balancegame;

import javafx.scene.shape.Circle;

/**
 * Created by Daniele Parisi (daniele@parisi.xyz) on 5/28/16.
 */

class Ball extends Circle {
    private double xSpeed = 0;
    private double xPos = 0;
    private final double G = 0.003;

    Ball() {
        super(0, 0, 5);
    }

    boolean update(double delta, double angle) {
        angle = Math.toRadians(angle);
        xSpeed = xSpeed + (Math.sin(angle) * Math.cos(angle) * G * delta);
        xPos += xSpeed;
        setTranslateX(xPos + Math.cos(Math.PI / 2 - angle) * 10);
        setTranslateY(Math.tan(angle) * xPos - Math.sin(Math.PI / 2 - angle) * 10);
        if (xPos > Math.cos(angle) * 50 || xPos < -Math.cos(angle) * 50) {
            return false;
        }
        return true;
    }
}

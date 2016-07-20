package xyz.parisi.unical.multitask.balancegame;

import javafx.scene.shape.Circle;

class Ball extends Circle {
    private double xSpeed = 0;
    private double xPos = 0;
    private static final double G = 0.003;

    Ball() {
        super(0, 0, 5);
        setTranslateY(-10);
    }

    boolean update(double delta, double angle) {
        angle = Math.toRadians(angle);
        xSpeed = xSpeed + (Math.sin(angle) * Math.cos(angle) * G * delta);
        xPos += xSpeed;
        setTranslateX(xPos + Math.cos(Math.PI / 2 - angle) * 10);
        setTranslateY(Math.tan(angle) * xPos - Math.sin(Math.PI / 2 - angle) * 10);
        return !(xPos > Math.cos(angle) * 50 || xPos < -Math.cos(angle) * 50);
    }
}

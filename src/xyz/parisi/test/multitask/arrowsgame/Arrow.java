package xyz.parisi.test.multitask.arrowsgame;


import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import java.util.Random;

public class Arrow extends Polygon {
    final double WIDTH;
    final double HEIGHT;
    final double BAR_WIDTH;
    Direction direction;
    final int level;
    static final double DISTANCE = 70;
    static final double SPEED = 0.05;
    boolean dead = false;
    Bar bar;

    Arrow(Bar bar) {
        this.bar = bar;
        BAR_WIDTH = bar.getWIDTH();
        HEIGHT = bar.getHEIGHT() / 10;
        WIDTH = HEIGHT * 1.5;
        getPoints().addAll(0., -HEIGHT / 2, WIDTH, 0., 0., HEIGHT / 2);
        setFill(Color.BLACK);
        direction = Direction.getRandom();
        if (direction == Direction.LEFT) {
            setRotate(180);
            setLayoutX(DISTANCE - WIDTH);
        } else {
            setLayoutX(-DISTANCE);
        }
        level = new Random().nextInt(5);
        setLayoutY((bar.getHEIGHT() / 5) * (level - 2));
    }

    boolean update(double delta) {
        if (direction == Direction.LEFT) {
            setLayoutX(getLayoutX() - SPEED * delta);
            if (getLayoutX() < -DISTANCE)
                return false;
        } else {
            setLayoutX(getLayoutX() + SPEED * delta);
            if (getLayoutX() > DISTANCE - WIDTH)
                return false;
        }
        return true;
    }

    boolean detectCollision() {
        if (bar.getLevel() == level)
            if (getLayoutX() > (- BAR_WIDTH / 2 - WIDTH) && getLayoutX() < (BAR_WIDTH / 2))
                return true;
        return false;
    }
}

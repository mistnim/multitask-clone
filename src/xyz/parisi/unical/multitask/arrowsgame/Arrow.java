package xyz.parisi.unical.multitask.arrowsgame;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import xyz.parisi.unical.multitask.RandomProvider;

import javafx.util.Duration;

public class Arrow extends Polygon {
    private double WIDTH;
    private double HEIGHT;
    private double BAR_WIDTH;
    private Direction direction;
    private int level;
    private static final double DISTANCE = 170;
    private static final double SPEED = 0.04;
    private boolean dead = false;
    private Bar bar;
    private Timeline fading = new Timeline();

    Arrow(Bar bar) {
        this.bar = bar;
        initialize();
        paint();
        setFadingAnimation();
    }

    private void setFadingAnimation() {
        fading.getKeyFrames().add(new KeyFrame(Duration.millis(700), new KeyValue(fillProperty(), Color.TRANSPARENT)));
        fading.setOnFinished(event -> dead = true);
    }

    private void initialize() {
        direction = Direction.getRandom();
        level = RandomProvider.nextInt(5);
    }

    private void paint() {
        BAR_WIDTH = bar.getWIDTH();
        HEIGHT = bar.getHEIGHT() / 10;
        WIDTH = HEIGHT * 1.2;
        getPoints().addAll(0., -HEIGHT / 2, WIDTH, 0., 0., HEIGHT / 2);
        setFill(Color.BLACK);
        if (direction == Direction.LEFT) {
            setRotate(180);
            setTranslateX(DISTANCE - WIDTH);
        } else {
            setTranslateX(-DISTANCE);
        }
        setLayoutY((bar.getHEIGHT() / 5) * (level - 2));
    }

    boolean update(double delta) {
        if (dead)
            return false;
        if (direction == Direction.LEFT) {
            setTranslateX(getTranslateX() - SPEED * delta);
            if (getTranslateX() < -40) {
                fading.play();
            }
        } else {
            setTranslateX(getTranslateX() + SPEED * delta);
            if (getTranslateX() > 40 - WIDTH) {
                fading.play();
            }
        }
        return true;
    }

    boolean detectCollision() {
        if (bar.getLevel() == level)
            if (getTranslateX() > (-BAR_WIDTH / 2 - WIDTH) && getTranslateX() < (BAR_WIDTH / 2))
                return true;
        return false;
    }
}

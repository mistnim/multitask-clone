package xyz.parisi.unical.multitask.targetgame;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;

/**
 * Created by daniele on 7/18/16.sa
 */
class Ring extends Pane {
    private final Rotate rotation = new Rotate(0, 0, 0);
    private static final double ROTATION_SPEED = 2.8;
    private final double innerRadius = 30;
    private final double circleWidth = 6;

    double getInnerRadius() {
        return innerRadius;
    }

    double getCircleWidth() {
        return circleWidth;
    }

    Ring() {
        Circle circle = new Circle(innerRadius);
        circle.setFill(Color.TRANSPARENT);
        circle.setStroke(Color.DARKORANGE);
        circle.setStrokeWidth(circleWidth);
        Polygon mouth = new Polygon(0, 0, 40, -40, -40, -40);
        mouth.setFill(TargetGame.BG_COLOR);
        getChildren().addAll(circle, mouth);
        getTransforms().addAll(rotation);
    }

    double getAngle() {
        double mod = rotation.getAngle() % 360;
        if (mod < 0)
            mod = 360 - Math.abs(mod);
        return mod;
    }

    void moveCounterClockWise() {
        rotation.setAngle(rotation.getAngle() - ROTATION_SPEED);
    }

    void moveClockWise() {
        rotation.setAngle(rotation.getAngle() + ROTATION_SPEED);
    }
}

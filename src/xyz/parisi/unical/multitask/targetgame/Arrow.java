package xyz.parisi.unical.multitask.targetgame;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import xyz.parisi.unical.multitask.RandomProvider;

class Arrow extends Polygon {
    private final static Color COLOR = Color.GRAY;
    private final static double WIDTH = 12;
    private final static double LENGTH = 14;
    private final Translate translate = new Translate(0, -100);
    private final Rotate rotation = new Rotate(0, 0, 0);
    private Status status = Status.BORN;
    private final double acceleration = 0.003;
    private double speed = 0;
    private final Ring ring;

    Arrow(Ring ring) {
        super(-WIDTH / 2, 0, WIDTH / 2, 0, 0, LENGTH);
        this.ring = ring;
        setFill(Color.TRANSPARENT);
        rotation.setAngle(RandomProvider.nextInt(360));
        getTransforms().addAll(rotation, translate);
        fadeIn();
    }

    private void fadeIn() {
        Timeline f = new Timeline(new KeyFrame(Duration.millis(700), new KeyValue(fillProperty(), COLOR)),
                new KeyFrame(Duration.millis(2000), new KeyValue(fillProperty(), COLOR)));
        f.play();
        f.setOnFinished(event -> status = Status.MOVING);
    }

    private void fadeOut() {
        Timeline fadeOut = new Timeline(new KeyFrame(Duration.millis(700), new KeyValue(fillProperty(), Color.TRANSPARENT)));
        fadeOut.play();
        fadeOut.setOnFinished(event -> status = Status.DEAD);
    }

    boolean update(double timeDelta) {
        if (status == Status.DEAD)
            return false;
        if (status == Status.MOVING) {
            speed += acceleration;
            translate.setY(translate.getY() + speed * timeDelta);
            if (translate.getY() > (-LENGTH/2)) {
                status = Status.STOPPED;
                fadeOut();
            }
        }
        return true;
    }

    boolean detectCollision() {
        return inDangerZone() && outsideOpening();
    }

    private boolean outsideOpening() {
        double angle = rotation.getAngle();
        double ringAngle = ring.getAngle();

        double distance = ringAngle - angle;
        if (Math.abs(distance) > 180)
            angle = angle + Math.signum(distance) * 360;

        double halfCollisionAngle = collisionAngle() / 2;
        return !((angle - halfCollisionAngle) > (ringAngle - 45) && (angle + halfCollisionAngle) < ringAngle + 45);
    }

    private double collisionAngle() {
        return Math.toDegrees(WIDTH / ring.getInnerRadius());
    }

    private boolean inDangerZone() {
        double y = translate.getY();
        double innerRadius = ring.getInnerRadius();
        double outerRadius = ring.getCircleWidth() + innerRadius;
        return y > -(outerRadius + LENGTH) && y < -(innerRadius);
    }

    private enum Status {
        BORN,
        MOVING,
        STOPPED,
        DEAD
    }

}

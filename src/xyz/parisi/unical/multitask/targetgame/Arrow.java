package xyz.parisi.unical.multitask.targetgame;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import xyz.parisi.unical.multitask.RandomProvider;

/**
 * Created by daniele on 7/18/16.sa
 */
class Arrow extends Polygon {
    final static Color COLOR = Color.GRAY;
    final static double WIDTH = 12;
    final static double LENGTH = 14;
    Translate translate = new Translate(0, -100);
    Rotate angle = new Rotate(0, 0, 0);
    boolean moving = false;
    private double acceleration = 0.3;

    Arrow() {
        super(-WIDTH / 2, 0, WIDTH / 2, 0, 0, LENGTH);
        setFill(Color.TRANSPARENT);
        angle.setAngle(RandomProvider.nextInt(360));
        getTransforms().addAll(angle, translate);
        fadeIn();
    }

    private void fadeIn() {
        Timeline f = new Timeline(new KeyFrame(Duration.millis(700), new KeyValue(fillProperty(), COLOR)),
                new KeyFrame(Duration.millis(2000), new KeyValue(fillProperty(), COLOR)));
        f.play();
        f.setOnFinished(event -> translate.setY(-50));
    }

}

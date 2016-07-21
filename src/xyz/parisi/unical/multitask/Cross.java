package xyz.parisi.unical.multitask;

import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Created by daniele on 7/20/16.sa
 */
public class Cross extends Pane {
    public Cross() {
        double width = 10;
        double lenght = 100;
        Rectangle a = new Rectangle(-lenght/2, -width/2, lenght, width);
        Rectangle b = new Rectangle(-lenght/2, -width/2, lenght, width);
        a.setRotate(45);
        b.setRotate(- 45);
        Color color = new Color(1,0,0, 0.7);
        a.setFill(color);
        b.setFill(color);
        getChildren().addAll(a, b);
    }
}

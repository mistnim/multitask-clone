package xyz.parisi.unical.multitask;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by daniele on 7/20/16.sa
 */
public class Cross extends Pane {
    public Cross() {
        double width = 10;
        double length = 100;
        Rectangle a = new Rectangle(-length/2, -width/2, length, width);
        Rectangle b = new Rectangle(-length/2, -width/2, length, width);
        a.setRotate(45);
        b.setRotate(- 45);
        Color color = new Color(1,0,0, 0.7);
        a.setFill(color);
        b.setFill(color);
        getChildren().addAll(a, b);
    }
}

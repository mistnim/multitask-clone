package xyz.parisi.unical.multitask.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;

/**
 * Created by daniele on 7/20/16.sa
 */
public class MenuBox extends VBox {
    MenuBox(MenuItem... items) {
        setAlignment(Pos.CENTER);
        getChildren().add(createSeparator());

        for (MenuItem item : items) {
            getChildren().addAll(item, createSeparator());
        }
    }

    private Line createSeparator() {
        Line sep = new Line();
        sep.setEndX(200);
        sep.setStroke(Color.DARKGRAY);
        return sep;
    }
}

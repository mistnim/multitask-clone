package xyz.parisi.test.testgame;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * Created by Daniele Parisi (daniele@parisi.xyz) on 6/15/16.
 */

public class ArrowsGame extends Pane implements MiniGame, Window {
    SimpleDoubleProperty myWidth = new SimpleDoubleProperty();
    SimpleDoubleProperty myHeight = new SimpleDoubleProperty();

    Rectangle bg = new Rectangle();
    Pane objects = new Pane();

    public SimpleDoubleProperty myWidthProperty() {
        return myWidth;
    }

    public SimpleDoubleProperty myHeightProperty() {
        return myHeight;
    }

    ArrowsGame(double w, double h) {
        myHeight.set(h);
        myWidth.set(w);
        bg.heightProperty().bind(myHeight);
        bg.widthProperty().bind(myWidth);
        bg.setFill(Color.AZURE);
        bg.setStroke(Color.GRAY);
        getChildren().addAll(bg, objects);
    }

    public boolean update(double delta, boolean keyOne, boolean keyTwo) {
        return false;
    }
}

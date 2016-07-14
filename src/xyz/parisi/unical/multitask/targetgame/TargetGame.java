package xyz.parisi.unical.multitask.targetgame;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xyz.parisi.unical.multitask.Keyboard;
import xyz.parisi.unical.multitask.MiniGame;
import xyz.parisi.unical.multitask.Window;

public class TargetGame extends Pane implements MiniGame, Window {
    private SimpleDoubleProperty myWidth = new SimpleDoubleProperty();
    private SimpleDoubleProperty myHeight = new SimpleDoubleProperty();

    TargetGame(double w, double h) {
        myHeight.set(h);
        myWidth.set(w);
        Rectangle bg = new Rectangle();
        bg.heightProperty().bind(myHeight);
        bg.widthProperty().bind(myWidth);
        bg.setFill(Color.LIGHTGREEN);
        bg.setStroke(Color.GRAY);
    }

    @Override
    public boolean update(double timeDelta, long currentTime, Keyboard keyboard) {
        return false;
    }

    @Override
    public SimpleDoubleProperty myWidthProperty() {
        return myWidth;
    }

    @Override
    public SimpleDoubleProperty myHeightProperty() {
        return myHeight;
    }
}

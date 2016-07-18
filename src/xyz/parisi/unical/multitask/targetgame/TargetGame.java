package xyz.parisi.unical.multitask.targetgame;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xyz.parisi.unical.multitask.Keyboard;
import xyz.parisi.unical.multitask.MiniGame;
import xyz.parisi.unical.multitask.Window;

public class TargetGame extends Pane implements MiniGame, Window {
    private SimpleDoubleProperty myWidth = new SimpleDoubleProperty();
    private SimpleDoubleProperty myHeight = new SimpleDoubleProperty();
    static final Color BG_COLOR = Color.LIGHTYELLOW;
    private Ring ring = new Ring();

    @Override
    public String getInstructionText() {
        return null;
    }

    public TargetGame(double w, double h) {
        myHeight.set(h);
        myWidth.set(w);
        Rectangle bg = new Rectangle();
        bg.heightProperty().bind(myHeight);
        bg.widthProperty().bind(myWidth);
        bg.setFill(BG_COLOR);
        bg.setStroke(Color.BLACK);
        Pane objects = new Pane();
        objects.layoutXProperty().bind(myWidth.divide(2));
        objects.layoutYProperty().bind(myHeight.divide(2));
        objects.getChildren().addAll(ring, new Arrow());
        getChildren().addAll(bg, objects);
    }

    @Override
    public boolean update(double timeDelta, long currentTime, Keyboard keyboard) {
        if (keyboard.isPressed(KeyCode.W))
            ring.moveCounterClockWise();
        if (keyboard.isPressed(KeyCode.R))
            ring.moveClockWise();
        return true;
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

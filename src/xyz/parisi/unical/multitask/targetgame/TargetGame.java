package xyz.parisi.unical.multitask.targetgame;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xyz.parisi.unical.multitask.Keyboard;
import xyz.parisi.unical.multitask.MiniGame;
import xyz.parisi.unical.multitask.Window;

import java.util.Iterator;

public class TargetGame extends Pane implements MiniGame, Window {
    private final SimpleDoubleProperty myWidth = new SimpleDoubleProperty();
    private final SimpleDoubleProperty myHeight = new SimpleDoubleProperty();
    static final Color BG_COLOR = Color.LIGHTYELLOW;
    private final Ring ring = new Ring();
    private final Pane arrows = new Pane();

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
        objects.getChildren().addAll(ring, arrows);
        arrows.getChildren().addAll(new Arrow(ring));
        getChildren().addAll(bg, objects);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public boolean update(double timeDelta, long currentTime, Keyboard keyboard) {
        if (keyboard.isPressed(KeyCode.W))
            ring.moveCounterClockWise();
        if (keyboard.isPressed(KeyCode.R))
            ring.moveClockWise();

        Iterator<Node> it = arrows.getChildren().iterator();
        while (it.hasNext()) {
            Arrow a = (Arrow) it.next();
            if (!a.update(timeDelta)) {
                it.remove();
                continue;
            }
            if (a.detectCollision()) {
                System.out.println("as d");
                return false;
            }
        }

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

package xyz.parisi.unical.multitask.targetgame;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xyz.parisi.unical.multitask.*;

import java.util.Iterator;

public class TargetGame extends Pane implements MiniGame, Window {
    private final SimpleDoubleProperty myWidth = new SimpleDoubleProperty();
    private final SimpleDoubleProperty myHeight = new SimpleDoubleProperty();
    static final Color BG_COLOR = Color.LIGHTYELLOW;
    private final Ring ring = new Ring();
    private final Pane arrows = new Pane();
    private boolean isFirstUpdate = true;
    private long nextArrowTime;
    private Pane objects = new Pane();

    @Override
    public String getInstructionText() {
        return null;
    }

    @Override
    public void showGameOver() {
        objects.getChildren().addAll(new Cross());
    }

    public TargetGame(double w, double h) {
        myHeight.set(h);
        myWidth.set(w);
        Rectangle bg = new Rectangle();
        bg.heightProperty().bind(myHeight);
        bg.widthProperty().bind(myWidth);
        bg.setFill(BG_COLOR);
        bg.setStroke(Color.BLACK);
        objects.layoutXProperty().bind(myWidth.divide(2));
        objects.layoutYProperty().bind(myHeight.divide(2));
        objects.getChildren().addAll(ring, arrows);
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
            if (a.detectCollision())
                return false;
            if (!a.update(timeDelta)) {
                it.remove();
            }
        }

        if (isFirstUpdate) {
            nextArrowTime = getNextArrowTime(currentTime);
            isFirstUpdate = false;
        } else {
            if (currentTime > nextArrowTime) {
                if (arrows.getChildren().size() < 4) {
                    nextArrowTime = getNextArrowTime(currentTime);
                    arrows.getChildren().addAll(new Arrow(ring));
                }
            }
        }

        return true;
    }

    private long getNextArrowTime(long currentTime) {
        return currentTime + 1500_000_000L + (long) (RandomProvider.getNextExponential() * 3_500_000_000L);
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

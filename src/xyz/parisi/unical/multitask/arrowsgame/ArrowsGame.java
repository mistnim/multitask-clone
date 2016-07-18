package xyz.parisi.unical.multitask.arrowsgame;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xyz.parisi.unical.multitask.Keyboard;
import xyz.parisi.unical.multitask.MiniGame;
import xyz.parisi.unical.multitask.RandomProvider;
import xyz.parisi.unical.multitask.Window;

import java.util.Iterator;

public class ArrowsGame extends Pane implements MiniGame, Window {
    private SimpleDoubleProperty myWidth = new SimpleDoubleProperty();
    private SimpleDoubleProperty myHeight = new SimpleDoubleProperty();
    private Bar bar = new Bar();
    private Pane arrows = new Pane();
    private long lastTimeKeyUp = 0;
    private long lastTimeKeyDown = 0;
    private Rectangle bg;
    private boolean alternate = true;
    private boolean isFirstUpdate = true;
    private long nextArrowTime;

    public SimpleDoubleProperty myWidthProperty() {
        return myWidth;
    }

    public SimpleDoubleProperty myHeightProperty() {
        return myHeight;
    }

    @Override
    public String getInstructionText() {
        return "Move up and down to avoid the arrows!\nUse the UP and DOWN arrow keys";

    }

    public ArrowsGame(double w, double h) {
        myHeight.set(h);
        myWidth.set(w);
        bg = new Rectangle();
        bg.heightProperty().bind(myHeight);
        bg.widthProperty().bind(myWidth);
        bg.setFill(Color.AZURE);
        bg.setStroke(Color.BLACK);
        Pane objects = new Pane();
        objects.layoutXProperty().bind(myWidth.divide(2));
        objects.layoutYProperty().bind(myHeight.divide(2));

        arrows.getChildren().addAll(new Arrow(bar));
        objects.getChildren().addAll(bar, arrows);
        getChildren().addAll(bg, objects);
    }

    private void goUp() {
        bar.goUp();
    }

    private void goDown() {
        bar.goDown();
    }

    public boolean update(double timeDelta, long currentTime, Keyboard keyboard) {
        // /* workaround javafx graphics bug
        if (alternate)
            bg.setTranslateY(1);
        else
            bg.setTranslateY(0);
        alternate = !alternate;
        // */

        boolean keyUp = keyboard.isPressed(KeyCode.E);
        boolean keyDown = keyboard.isPressed(KeyCode.D);

        final long intervalBetweenPresses = 170_000_000;
        if (keyUp && (currentTime - lastTimeKeyUp) > intervalBetweenPresses) {
            goUp();
            lastTimeKeyUp = currentTime;
        }
        if (keyDown && currentTime - lastTimeKeyDown > intervalBetweenPresses) {
            goDown();
            lastTimeKeyDown = currentTime;
        }

        if (isFirstUpdate) {
            nextArrowTime = currentTime + (long) (RandomProvider.getNextExponential() * 1_000_000);
            isFirstUpdate = false;
        } else {
            if (currentTime > nextArrowTime) {
                if (arrows.getChildren().size() < 4) {
                    nextArrowTime = currentTime + (long) (RandomProvider.getNextExponential() * 3_000_000_000L);
                    arrows.getChildren().addAll(new Arrow(bar));
                }
            }
        }

        Iterator<Node> it = arrows.getChildren().iterator();
        while (it.hasNext()) {
            Arrow a = (Arrow) it.next();
            if (!a.update(timeDelta)) {
                it.remove();
                continue;
            }
            if (a.detectCollision())
                return false;
        }
        return true;
    }
}

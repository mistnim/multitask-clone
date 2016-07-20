package xyz.parisi.unical.multitask.arrowsgame;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xyz.parisi.unical.multitask.*;

import java.util.Iterator;

public class ArrowsGame extends Pane implements MiniGame, Window {
    private final SimpleDoubleProperty myWidth = new SimpleDoubleProperty();
    private final SimpleDoubleProperty myHeight = new SimpleDoubleProperty();
    private final Bar bar = new Bar();
    private final Pane arrows = new Pane();
    private long lastTimeKeyUp = 0;
    private long lastTimeKeyDown = 0;
    private final Rectangle bg;
    private boolean alternate = true;
    private boolean isFirstUpdate = true;
    private long nextArrowTime;
    private Pane objects = new Pane();

    public SimpleDoubleProperty myWidthProperty() {
        return myWidth;
    }

    public SimpleDoubleProperty myHeightProperty() {
        return myHeight;
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

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
        objects.layoutXProperty().bind(myWidth.divide(2));
        objects.layoutYProperty().bind(myHeight.divide(2));

        arrows.getChildren().addAll(new Arrow(bar));
        objects.getChildren().addAll(bar, arrows);
        getChildren().addAll(bg, objects);
    }

    @Override
    public void showGameOver() {
        objects.getChildren().addAll(new Cross());
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

        final long intervalBetweenPresses = 190_000_000;
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
                if (arrows.getChildren().size() < 5) {
                    nextArrowTime = currentTime + (long) (RandomProvider.getNextExponential() * 2_000_000_000L);
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

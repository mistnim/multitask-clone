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
import java.util.Random;

public class ArrowsGame extends Pane implements MiniGame, Window {
    private SimpleDoubleProperty myWidth = new SimpleDoubleProperty();
    private SimpleDoubleProperty myHeight = new SimpleDoubleProperty();
    private Random rand = new Random();
    private Bar bar = new Bar();
    private Pane objects = new Pane();
    private Pane arrows = new Pane();
    private long lastPressTimeUp = 0;
    private long lastPressTimeDown = 0;

    private boolean firstUpdate = true;
    private long nextArrowTime;
    public SimpleDoubleProperty myWidthProperty() {
        return myWidth;
    }

    public SimpleDoubleProperty myHeightProperty() {
        return myHeight;
    }

    public ArrowsGame(double w, double h) {
        myHeight.set(h);
        myWidth.set(w);
        Rectangle bg = new Rectangle();
        bg.heightProperty().bind(myHeight);
        bg.widthProperty().bind(myWidth);
        bg.setFill(Color.AZURE);
        bg.setStroke(Color.GRAY);
        objects.layoutXProperty().bind(myWidth.divide(2));
        objects.layoutYProperty().bind(myHeight.divide(2));

        arrows.getChildren().addAll(new Arrow(bar));
        objects.getChildren().addAll(bar, arrows);
        getChildren().addAll(bg, objects);
    }

    public void goUp() {
        bar.goUp();
    }

    public void goDown() {
        bar.goDown();
    }

    public boolean update(double timeDelta, long currentTime, Keyboard keyboard) {
        boolean keyUp = keyboard.isPressed(KeyCode.E);
        boolean keyDown = keyboard.isPressed(KeyCode.D);

        long pressSpeed = 200_000_000;
        if (keyUp && currentTime - lastPressTimeUp > pressSpeed) {
            goUp();
            lastPressTimeUp = currentTime;
        }
        if (keyDown && currentTime - lastPressTimeDown > pressSpeed) {
            goDown();
            lastPressTimeDown = currentTime;
        }

        if (firstUpdate) {
            nextArrowTime = currentTime + (long) (RandomProvider.getNextExponential() * 1_000_000);
            firstUpdate = false;
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

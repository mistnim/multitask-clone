package xyz.parisi.test.multitask.arrowsgame;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import xyz.parisi.test.multitask.MiniGame;
import xyz.parisi.test.multitask.Window;

import java.util.Iterator;
import java.util.Random;

public class ArrowsGame extends Pane implements MiniGame, Window {
    private SimpleDoubleProperty myWidth = new SimpleDoubleProperty();
    private SimpleDoubleProperty myHeight = new SimpleDoubleProperty();
    private Random rand = new Random();
    private Rectangle bg = new Rectangle();
    private Bar bar = new Bar();
    private Pane objects = new Pane();
    private Pane arrows = new Pane();

    public SimpleDoubleProperty myWidthProperty() {
        return myWidth;
    }

    public SimpleDoubleProperty myHeightProperty() {
        return myHeight;
    }

    public ArrowsGame(double w, double h) {
        myHeight.set(h);
        myWidth.set(w);
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

    public boolean update(double delta, boolean keyOne, boolean keyTwo, long time) {
        if (rand.nextInt(100) == 50) {
            arrows.getChildren().addAll(new Arrow(bar));
        }

        Iterator<Node> it = arrows.getChildren().iterator();
        while (it.hasNext()) {
            Arrow a = (Arrow) it.next();
            if (!a.update(delta)) {
                it.remove();
                continue;
            }
            if (a.detectCollision())
               return false;
        }
        return true;
    }
}

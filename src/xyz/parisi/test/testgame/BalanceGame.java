package xyz.parisi.test.testgame;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * Created by Daniele Parisi (daniele@parisi.xyz) on 6/1/16.
 */

class BalanceGame extends Pane implements MiniGame, Window {
    SimpleDoubleProperty myWidth = new SimpleDoubleProperty();
    SimpleDoubleProperty myHeight = new SimpleDoubleProperty();

    Ball ball = new Ball();
    Rectangle bar = new Rectangle(-50, -5, 100, 10);
    Rectangle bg = new Rectangle();
    Pane objects = new Pane();

    BalanceGame(double w, double h) {
        myWidth.set(w);
        myHeight.set(h);
        bg.heightProperty().bind(myHeight);
        bg.widthProperty().bind(myWidth);
        bg.setFill(Color.PINK);
        bg.setStroke(Color.GRAY);

        bar.setFill(Color.BLACK);
        bar.setRotate(0.2);
        ball.setFill(Color.RED);
        objects.getChildren().addAll(bar, ball);
        objects.layoutXProperty().bind(myWidth.divide(2));
        objects.layoutYProperty().bind(myHeight.divide(2));
        getChildren().addAll(bg, objects);
    }

    @Override
    public SimpleDoubleProperty myWidthProperty() {
        return myWidth;
    }

    @Override
    public SimpleDoubleProperty myHeightProperty() {
        return myHeight;
    }

    public boolean update(double delta, boolean isPressedS, boolean isPressedF) {
        if (isPressedF)
            bar.setRotate(bar.getRotate() + 0.7);
        if (isPressedS)
            bar.setRotate(bar.getRotate() - 0.7);
        return ball.update(delta, bar.getRotate());
    }

    void playIntro() {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500),
                new KeyValue(myWidth, 300)));
        timeline.play();
        timeline.getOnFinished();
    }
}

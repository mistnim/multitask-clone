package xyz.parisi.unical.multitask;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.util.Duration;

public interface Window {
    SimpleDoubleProperty myWidthProperty();
    SimpleDoubleProperty myHeightProperty();
    DoubleProperty layoutXProperty();
    DoubleProperty layoutYProperty();

    default Timeline animate(double layoutX, double layoutY, double width, double height) {
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(700),
                new KeyValue(layoutXProperty(), layoutX),
                new KeyValue(layoutYProperty(), layoutY),
                new KeyValue(myHeightProperty(), height),
                new KeyValue(myWidthProperty(), width)));
        timeline.play();
        return timeline;
    }
}

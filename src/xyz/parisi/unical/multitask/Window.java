package xyz.parisi.unical.multitask;

import javafx.animation.Interpolator;
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
        final Interpolator interpolator = Interpolator.SPLINE(0.7,0, 0.3, 1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(1500),
                new KeyValue(layoutXProperty(), layoutX, interpolator),
                new KeyValue(layoutYProperty(), layoutY, interpolator),
                new KeyValue(myHeightProperty(), height, interpolator),
                new KeyValue(myWidthProperty(), width, interpolator)));
        timeline.play();
        return timeline;
    }
}

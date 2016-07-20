package xyz.parisi.unical.multitask;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;

/**
 * Created by daniele on 7/15/16.sa
 */
class Message extends StackPane {
    private final int WIDTH = 270;
    private final int HEIGHT = 140;
    private final Circle clip = new Circle(WIDTH/2, HEIGHT/2, 0);
    private final Interpolator interpolator = Interpolator.SPLINE(0.7,0, 0.3, 1);

    Message(String text) {
        setClip(clip);
        setLayoutX(-WIDTH/2);
        setLayoutY(-HEIGHT/2);
        Rectangle bg = new Rectangle(WIDTH, HEIGHT);
        bg.setFill(new Color(1,1,1,0.95));
        Text message= new Text(text);
        message.setFont(new Font(13));
        message.setFill(Color.BLACK);
        getChildren().addAll(bg, message);
        fadeIn();
    }

    private void fadeIn() {
        Timeline fadeIn = new Timeline();
        fadeIn.getKeyFrames().add(new KeyFrame(Duration.millis(300), new KeyValue(clip.radiusProperty(), 200, interpolator)));
        fadeIn.play();

    }

    Timeline fadeOut() {
        Timeline fadeOut = new Timeline();
        fadeOut.getKeyFrames().add(new KeyFrame(Duration.millis(300), new KeyValue(clip.radiusProperty(), 0, interpolator)));
        fadeOut.play();
        return fadeOut;
    }
}

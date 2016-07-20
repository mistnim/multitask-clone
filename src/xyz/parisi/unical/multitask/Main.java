package xyz.parisi.unical.multitask;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Main extends Application {
    private boolean playing = true;
    private final Pane appRoot = new Pane();
    private final double WIDTH = 800;
    private final double HEIGHT = 600;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(appRoot);

        Rectangle bg = new Rectangle(WIDTH, HEIGHT);
        bg.setFill(Color.BLACK);

        StandardGame standardGame = new StandardGame(scene, WIDTH, HEIGHT);

        appRoot.getChildren().addAll(bg, standardGame);

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                standardGame.update(now);
            }
        };
        at.start();

        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setMaxHeight(HEIGHT);
        primaryStage.setMaxWidth(WIDTH);
        primaryStage.setTitle("float");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

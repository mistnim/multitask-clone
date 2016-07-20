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
import xyz.parisi.unical.multitask.menu.Menu;
import xyz.parisi.unical.multitask.menu.MenuBox;
import xyz.parisi.unical.multitask.menu.MenuItem;
import xyz.parisi.unical.multitask.menu.Title;

public class Main extends Application {
    private boolean playing = true;
    private final Pane appRoot = new Pane();
    private final double WIDTH = 800;
    private final double HEIGHT = 600;
    private Scene scene;
    private Status status = Status.MENU;
    private StandardGame standardGame;

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(appRoot);

        Rectangle bg = new Rectangle(WIDTH, HEIGHT);
        bg.setFill(Color.BLACK);


        Menu menu = new Menu(WIDTH, HEIGHT);
        menu.setOnPlaySelected(event -> {
            appRoot.getChildren().remove(menu);
            standardGame = new StandardGame(scene, WIDTH, HEIGHT);
            appRoot.getChildren().addAll(standardGame);
            status = Status.PLAY;
        });

        appRoot.getChildren().addAll(bg, menu);

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (status == Status.PLAY)
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

    enum Status {
        MENU,
        PLAY
    }
}

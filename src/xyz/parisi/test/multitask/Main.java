package xyz.parisi.test.multitask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import xyz.parisi.test.multitask.arrowsgame.ArrowsGame;
import xyz.parisi.test.multitask.balancegame.BalanceGame;

import java.util.HashMap;

public class Main extends Application {
    private long oldTime;
    private boolean firstFrame = true;
    private boolean paused = true;
    private boolean gameOver = false;
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();
    private Pane appRoot = new Pane();

    private final double WIDTH = 800;
    private final double HEIGHT = 600;


    @Override
    public void start(Stage primaryStage) throws Exception {
        Screen screen = new Screen(WIDTH, HEIGHT, appRoot);

        BalanceGame balanceGame = new BalanceGame(WIDTH, HEIGHT);
        screen.set_first(balanceGame);
        ArrowsGame arrowsGame = new ArrowsGame(WIDTH, HEIGHT);
        screen.set_second(arrowsGame);

        Scene scene = new Scene(appRoot);

        scene.setOnKeyPressed(event -> {
            KeyCode k = event.getCode();
            if (k == KeyCode.E)
                arrowsGame.goUp();
            if (k == KeyCode.D)
                arrowsGame.goDown();
            keys.put(k, true);
        });

        scene.setOnKeyReleased(event -> keys.put(event.getCode(), false));
        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (firstFrame) {
                    oldTime = now;
                    firstFrame = false;
                    return;
                }
                double delta = (now - oldTime) / 1_000_000;
                if (!gameOver) {
                    if (!balanceGame.update(delta, isPressed(KeyCode.S), isPressed(KeyCode.F), now))
                        gameOver = true;
                    if (!arrowsGame.update(delta, false, false, now))
                        gameOver = true;
                }
                oldTime = now;
            }
        };
        at.start();

        primaryStage.setMinHeight(HEIGHT);
        primaryStage.setMinWidth(WIDTH);
        primaryStage.setTitle("float");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
}

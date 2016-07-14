package xyz.parisi.unical.multitask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import xyz.parisi.unical.multitask.arrowsgame.ArrowsGame;
import xyz.parisi.unical.multitask.balancegame.BalanceGame;

public class Main extends Application {
    private long oldTime;
    private boolean isFirstFrame = true;
    private boolean playing = true;
    private boolean gameOver = false;
    private Pane appRoot = new Pane();
    private final double WIDTH = 800;
    private final double HEIGHT = 600;
    private long levelStartTime;
    private Scene scene;
    private Keyboard keyboard = new Keyboard();

    private int level = 0;

    MiniGame[] miniGames = new MiniGame[4];

    private void setPlayKeyboardEvents() {
        scene.setOnKeyPressed(event -> keyboard.press(event.getCode()));
        scene.setOnKeyReleased(event -> keyboard.release(event.getCode()));
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Screen screen = new Screen(WIDTH, HEIGHT, appRoot);

        miniGames[0] = new BalanceGame(WIDTH, HEIGHT);
        screen.set_first((Window) miniGames[0]);

        scene = new Scene(appRoot);
        setPlayKeyboardEvents();

        Text score = new Text();
        score.setTranslateY(200);
        appRoot.getChildren().addAll(score);

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (isFirstFrame) {
                    oldTime = now;
                    isFirstFrame = false;
                    levelStartTime = now;
                    return;
                }

                score.setText(Long.toString(now - levelStartTime));

                if (level == 0 && now - levelStartTime > 1_000_000_000L) {
                    level = 1;
                    miniGames[1] = new ArrowsGame(WIDTH, HEIGHT);
                    screen.set_second((Window) miniGames[1]);
                    setPlayKeyboardEvents();
                }

                double delta = (now - oldTime) / 1_000_000;
                if (playing) {
                    if (!miniGames[0].update(delta, 0, keyboard))
                        playing = false;
                    if (level > 0)
                        if (!miniGames[1].update(delta, now, keyboard))
                            playing = false;
                }
                oldTime = now;
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

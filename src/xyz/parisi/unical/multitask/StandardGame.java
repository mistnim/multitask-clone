package xyz.parisi.unical.multitask;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.Duration;
import xyz.parisi.unical.multitask.arrowsgame.ArrowsGame;
import xyz.parisi.unical.multitask.balancegame.BalanceGame;
import xyz.parisi.unical.multitask.targetgame.TargetGame;


class StandardGame extends Pane {
    private long levelStartTime;
    private final Keyboard keyboard = new Keyboard();
    private long oldTime;
    private boolean isFirstFrame = true;
    private int level = 0;
    private MiniGame[] miniGames;
    private final Scene scene;
    private final double WIDTH;
    private final double HEIGHT;
    private GameScreen gameScreen;
    private long previouslyElapsedTime = 0;
    private long currentlyElapsedTime;
    private boolean playing = false;
    private final Text score = new Text();

    private long getScore() {
        return (previouslyElapsedTime + currentlyElapsedTime) / 1_000_000_000L;
    }

    private void setPlayKeyboardEvents() {
        scene.setOnKeyPressed(event -> keyboard.press(event.getCode()));
        scene.setOnKeyReleased(event -> keyboard.release(event.getCode()));
    }

    StandardGame(Scene scene, double WIDTH, double HEIGHT) {
        this.scene = scene;
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        initialize();
    }

    private void initialize() {
        this.setOpacity(0);
        Pane gamePane = new Pane();
        gameScreen = new GameScreen(WIDTH, HEIGHT - 30, gamePane);
        gamePane.setTranslateY(30);
        score.setTranslateY(20);
        score.setTranslateX(5);
        score.setFill(Color.WHITE);
        score.setFont(new Font(15));
        getChildren().addAll(score, gamePane);
        miniGames = new MiniGame[]{new BalanceGame(WIDTH, HEIGHT), new ArrowsGame(WIDTH, HEIGHT), new TargetGame(WIDTH, HEIGHT)};
        gameScreen.setFirst((Window) miniGames[0]);
        setPlayKeyboardEvents();
        fadeIn(event -> displayMessage(miniGames[0].getInstructionText()));
    }

    void fadeIn(EventHandler<ActionEvent> actionEvent) {
        FadeTransition ft = new FadeTransition(Duration.millis(700), this);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setOnFinished(actionEvent);
        ft.play();
    }

    void update(long now) {
        score.setText(Long.toString(getScore()));
        if (playing) {
            updateGames(now);
        }
    }

    private void updateGames(long now) {
        if (isFirstFrame) {
            oldTime = now;
            isFirstFrame = false;
            levelStartTime = now;
            return;
        }

        currentlyElapsedTime = now - levelStartTime;

        // launches new miniGames when it is their moment
        if (level < 2 && currentlyElapsedTime > 5_000_000_000L) {
            level += 1;
            isFirstFrame = true;
            previouslyElapsedTime += currentlyElapsedTime;
            currentlyElapsedTime = 0;
            playing = false;
            final String instructionMessage = miniGames[level].getInstructionText();
            gameScreen.setNewWindow((Window) miniGames[level]).setOnFinished(event -> displayMessage(instructionMessage));
        }

        double delta = (now - oldTime) / 1_000_000;

        for (int i = 0; i < level + 1; ++i)
            if (!miniGames[i].update(delta, now, keyboard)) {
                miniGames[i].showGameOver();
                playing = false;
            }
        oldTime = now;
    }

    private void displayMessage(String text) {
        Message message = new Message(text);
        message.setTranslateX(WIDTH / 2);
        message.setTranslateY(HEIGHT / 2 - 100);
        getChildren().addAll(message);
        scene.setOnKeyPressed(event -> {
            playing = true;
            setPlayKeyboardEvents();
            message.fadeOut().setOnFinished(event1 -> getChildren().remove(message));
        });
    }
}

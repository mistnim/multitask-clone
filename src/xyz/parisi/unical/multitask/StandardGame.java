package xyz.parisi.unical.multitask;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import xyz.parisi.unical.multitask.arrowsgame.ArrowsGame;
import xyz.parisi.unical.multitask.balancegame.BalanceGame;
import xyz.parisi.unical.multitask.targetgame.TargetGame;


class StandardGame extends Pane {
    private long levelStartTime;
    private Keyboard keyboard = new Keyboard();
    private long oldTime;
    private boolean isFirstFrame = true;
    private int level = 0;
    private MiniGame[] miniGames;
    private Scene scene;
    private final double WIDTH;
    private final double HEIGHT;
    private GameScreen gameScreen;
    private long previouslyElapsedTime = 0;
    private long currentlevelElepasedTime;
    private boolean playing = true;
    private Text score = new Text();

    private long getScore() {
        return (previouslyElapsedTime + currentlevelElepasedTime) / 1_000_000_000L;
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
        Pane gamePane = new Pane();
        gameScreen = new GameScreen(WIDTH, HEIGHT - 30, gamePane);
        gamePane.setTranslateY(30);
        score.setTranslateY(20);
        score.setTranslateX(5);
        score.setFill(Color.WHITE);
        score.setFont(new Font(15));
        getChildren().addAll(score, gamePane);
        miniGames = new MiniGame[]{new TargetGame(WIDTH, HEIGHT), new ArrowsGame(WIDTH, HEIGHT), new BalanceGame(WIDTH, HEIGHT)};
        gameScreen.setFirst((Window) miniGames[0]);
        setPlayKeyboardEvents();
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

        currentlevelElepasedTime = now - levelStartTime;

        // launches new miniGames when it is their moment
        for (int i = 0; i < 2; ++i) {
            if (level == i && currentlevelElepasedTime > 5_000_000_000L * (i + 1)) {
                level += 1;
                isFirstFrame = true;
                previouslyElapsedTime += currentlevelElepasedTime;
                currentlevelElepasedTime = 0;
                playing = false;
                final String instructionMessage = miniGames[level].getInstructionText();
                gameScreen.setNewWindow((Window) miniGames[level]).setOnFinished(event -> displayMessage(instructionMessage));
            }
        }

        double delta = (now - oldTime) / 1_000_000;

        for (int i = 0; i < level + 1; ++i)
            miniGames[i].update(delta, now, keyboard);
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
            message.fadeOut().setOnFinished(event1 -> getChildren().removeAll(message));
        });
    }
}

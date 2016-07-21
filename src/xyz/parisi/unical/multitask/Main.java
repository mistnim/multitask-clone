package xyz.parisi.unical.multitask;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import xyz.parisi.unical.multitask.menu.Menu;
import xyz.parisi.unical.multitask.scores.BestScoresList;
import xyz.parisi.unical.multitask.scores.DisplayBestScores;
import xyz.parisi.unical.multitask.scores.SaveScore;
import xyz.parisi.unical.multitask.scores.Score;

public class Main extends Application {
    private final Pane appRoot = new Pane();
    private final double WIDTH = 800;
    private final double HEIGHT = 600;
    private Scene scene;
    private Status status = Status.PLAY_OFF;
    private StandardGame standardGame;

    @Override
    public void start(Stage primaryStage) throws Exception {
        scene = new Scene(appRoot);

        Rectangle bg = new Rectangle(WIDTH, HEIGHT);
        bg.setFill(Color.BLACK);

        Menu menu = new Menu(WIDTH, HEIGHT);

        menu.setOnScoresSelected(event -> {
            appRoot.getChildren().addAll(new DisplayBestScores(WIDTH, HEIGHT));
        });

        menu.setOnPlaySelected(event -> {
            appRoot.getChildren().remove(menu);
            standardGame = new StandardGame(scene, WIDTH, HEIGHT, gameOverEvent -> {
                status = Status.PLAY_OFF;
                appRoot.getChildren().removeAll(standardGame, menu);
                int score = (int) standardGame.getScore();
                BestScoresList bestScoresList;
                try {
                    bestScoresList = BestScoresList.load();
                } catch (Exception e) {
                    System.out.println("Couldn't load: " + e.getMessage());
                    bestScoresList = new BestScoresList();
                }
                if (bestScoresList.canAdd(new Score("", score))) {
                    SaveScore saveScore = new SaveScore(WIDTH, HEIGHT, score, bestScoresList);
                    saveScore.setOnExit(event1 -> {
                        appRoot.getChildren().removeAll(saveScore);
                        appRoot.getChildren().addAll(menu);
                        menu.fadeIn();
                    });
                    appRoot.getChildren().addAll(saveScore);
                } else {
                    appRoot.getChildren().addAll(menu);
                    menu.fadeIn();
                }
            });
            appRoot.getChildren().addAll(standardGame);
            status = Status.PLAY_ON;
        });

        appRoot.getChildren().addAll(bg, menu);

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (status == Status.PLAY_ON)
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
        PLAY_OFF,
        PLAY_ON
    }
}

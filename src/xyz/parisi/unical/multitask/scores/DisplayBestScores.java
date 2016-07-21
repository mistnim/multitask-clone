package xyz.parisi.unical.multitask.scores;

import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import xyz.parisi.unical.multitask.menu.MenuBox;
import xyz.parisi.unical.multitask.menu.MenuItem;
import xyz.parisi.unical.multitask.menu.Title;

public class DisplayBestScores extends StackPane {
    private double WIDTH;

    public DisplayBestScores(double width, double height) {
        this.WIDTH = width;
        BestScoresList bestScoresList;
        try {
            bestScoresList = BestScoresList.load();
        } catch (Exception e) {
            System.out.println("Couldn't load: " + e.getMessage());
            bestScoresList = new BestScoresList();
        }

        setAlignment(Pos.CENTER);
        Rectangle bg = new Rectangle(width, height);
        bg.setFill(Color.BLACK);

        Title title = new Title("s c o r e s");
        title.setTranslateY(-200);

        BestScoresBox bestScoresBox = new BestScoresBox(bestScoresList);

        MenuItem back = new MenuItem("<");
        MenuBox menuBox = new MenuBox(back);
        menuBox.setTranslateY(200);

        back.setOnMouseClicked(event -> fadeOut());

        bestScoresBox.setTranslateY(0);
        getChildren().addAll(bg, title, bestScoresBox, menuBox);

        fadeIn();

    }

    private void fadeIn() {
        setTranslateX(-WIDTH);
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), this);
        tt.setToX(0);
        tt.setInterpolator(Interpolator.EASE_BOTH);
        tt.play();
    }

    private void fadeOut() {
        TranslateTransition tt = new TranslateTransition(Duration.millis(500), this);
        tt.setToX(-WIDTH);
        tt.setInterpolator(Interpolator.EASE_BOTH);
        tt.play();
    }
}

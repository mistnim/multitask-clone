package xyz.parisi.unical.multitask.scores;

import javafx.animation.FadeTransition;
import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import xyz.parisi.unical.multitask.menu.Title;

public class DisplayBestScores extends StackPane {
    public DisplayBestScores(double width, double height) {
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

        bestScoresBox.setTranslateY(-100);
        getChildren().addAll(bg, title, bestScoresBox);
        FadeTransition ft = new FadeTransition(Duration.millis(1300), this);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }
}

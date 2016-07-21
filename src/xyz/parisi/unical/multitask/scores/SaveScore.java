package xyz.parisi.unical.multitask.scores;

import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Duration;
import xyz.parisi.unical.multitask.menu.MenuBox;
import xyz.parisi.unical.multitask.menu.MenuItem;
import xyz.parisi.unical.multitask.menu.Title;

public class SaveScore extends StackPane {
    private EventHandler<ActionEvent> onExit;
    private final double WIDTH;
    private final double HEIGHT;
    private final BestScoresList bestScoresList;

    public void setOnExit(EventHandler<ActionEvent> onExit) {
        this.onExit = onExit;
    }

    public SaveScore(double width, double height, int score, BestScoresList bestScoresList) {
        this.WIDTH = width;
        this.HEIGHT = height;
        this.bestScoresList = bestScoresList;

        setAlignment(Pos.CENTER);
        Rectangle bg = new Rectangle(width, height);
        bg.setFill(Color.BLACK);

        Title title = new Title("s a v e");
        title.setTranslateY(-200);

        Text info = new Text("You made it to the Top 10!\nEnter your name to save the score.");
        info.setFill(Color.WHITE);
        info.setTranslateY(-120);

        TextField name = new TextField();
        name.setMaxWidth(300);
        name.setTranslateY(-70);

        MenuItem save = new MenuItem("Save");
        MenuItem cancel = new MenuItem("Cancel");
        cancel.setOnMouseClicked(event -> fadeOut());

        name.setOnAction(event -> {
            save(name.getText(), score);
            fadeOut();
        });

        save.setOnMouseClicked(event -> {
            save(name.getText(), score);
            fadeOut();
        });

        MenuBox menuBox = new MenuBox(save, cancel);
        getChildren().addAll(bg, title, menuBox, info, name);

        setOpacity(0);
        FadeTransition ft = new FadeTransition(Duration.millis(1000), this);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    private void save(String name, int score) {
        bestScoresList.add(new Score(name, score));
        try {
            ResourceManager.save(bestScoresList, "multitask.save");
        } catch (Exception e) {
            System.out.println("Couldn't save: " + e.getMessage());
        }
    }

    private void fadeOut() {
        TranslateTransition tt = new TranslateTransition(Duration.millis(700), this);
        tt.setToX(-WIDTH);
        tt.setOnFinished(onExit);
        tt.setInterpolator(Interpolator.EASE_BOTH);
        tt.play();
    }

}

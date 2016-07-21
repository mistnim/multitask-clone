package xyz.parisi.unical.multitask.menu;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Menu extends StackPane {
    private MenuItem play = new MenuItem("Play");
    private MenuItem scores = new MenuItem("Scores");

    public Menu(double width, double height) {
        setAlignment(Pos.CENTER);
        Rectangle bg = new Rectangle(width, height);
        bg.setFill(Color.BLACK);

        Title title = new Title("m u l t i t a s k");
        title.setTranslateY(-200);

        MenuItem exit = new MenuItem("Exit");
        exit.setOnMouseClicked(event -> System.exit(0));

        MenuBox menuBox = new MenuBox(play, scores, exit);

        menuBox.setTranslateY(0);
        getChildren().addAll(bg, title, menuBox);
    }

    public void fadeIn() {
        FadeTransition ft = new FadeTransition(Duration.millis(1300), this);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
    }

    public void setOnScoresSelected(EventHandler<MouseEvent> event) {
        scores.setOnMouseClicked(event);
    }

    public void setOnPlaySelected(EventHandler<ActionEvent> event) {
        play.setOnMouseClicked(event1 -> fadeOut().setOnFinished(event));
    }

    private FadeTransition fadeOut() {
        FadeTransition ft = new FadeTransition(Duration.millis(1300), this);
        ft.setFromValue(1);
        ft.setToValue(0);
        ft.play();
        return ft;
    }
}

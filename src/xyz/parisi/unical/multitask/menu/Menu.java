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


/**
 * Created by daniele on 7/20/16.sa
 */
public class Menu extends StackPane {
    MenuItem play = new MenuItem("Play");

    public Menu(double width, double height) {
        setAlignment(Pos.CENTER);
        Rectangle bg = new Rectangle(width, height);
        bg.setFill(Color.BLACK);

        Title title = new Title("m u l t i t a s k");
        title.setTranslateY(-200);

        MenuItem exit = new MenuItem("Exit");
        exit.setOnMouseClicked(event -> System.exit(0));

        MenuBox menuBox = new MenuBox(play, exit);

        menuBox.setTranslateY(0);
        getChildren().addAll(bg, title, menuBox);
    }

    public void fadeIn() {
        FadeTransition ft = new FadeTransition(Duration.millis(1300), this);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.play();
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

package xyz.parisi.unical.multitask.scores;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

/**
 * Created by daniele on 7/21/16.sa
 */
public class BestScoresBox extends VBox {
    BestScoresBox(BestScoresList bestScoresList) {
        setAlignment(Pos.CENTER);
        getChildren().add(createSeparator());

        for (Score item : bestScoresList) {
            getChildren().addAll(createScoreBox(item), createSeparator());
        }
    }

    private StackPane createScoreBox(Score score) {
        Rectangle bg = new Rectangle(300, 25);
        bg.setOpacity(0.4);
        Text text = new Text(score.getName() + " . . . " + score.getScore());
        text.setFill(Color.WHITE);
        text.setFont(Font.font("Sans", FontWeight.SEMI_BOLD, 18));
        StackPane sp = new StackPane();
        sp.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(bg, text);
        return sp;
    }

    private Line createSeparator() {
        Line sep = new Line();
        sep.setEndX(300);
        sep.setStroke(Color.DARKGRAY);
        return sep;
    }
}

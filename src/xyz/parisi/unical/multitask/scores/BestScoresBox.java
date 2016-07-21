package xyz.parisi.unical.multitask.scores;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.Collections;

class BestScoresBox extends VBox {
    BestScoresBox(BestScoresList bestScoresList) {
        setAlignment(Pos.CENTER);

        Collections.reverse(bestScoresList);
        for(int i = 0; i < bestScoresList.size(); ++i)
            getChildren().addAll(createScoreBox(bestScoresList.get(i), i + 1));
    }

    private StackPane createScoreBox(Score score, int index) {
        Rectangle bg = new Rectangle(300, 30);
        bg.setOpacity(0.4);
        String indexString = padLeft(Integer.toString(index), 2);
        String scoreString = padLeft(Long.toString(score.getScore()), 3).replace(' ', '.');
        String nameString = padRight(score.getName().replace(' ', '£'), 20).replace(' ', '.').replace('£', ' ');
        Text text = new Text(indexString + ") " + nameString + scoreString);
        text.setFill(Color.LIGHTGRAY);
        text.setFont(Font.font("Monospace", FontWeight.SEMI_BOLD, 18));
        StackPane sp = new StackPane();
        sp.setAlignment(Pos.CENTER);
        sp.getChildren().addAll(bg, text);
        return sp;
    }

    private static String padLeft(String s, int n) {
        return String.format("%1$" + n + "s", s);
    }

    private static String padRight(String s, int n) {
        return String.format("%1$-" + n + "s", s);
    }
}

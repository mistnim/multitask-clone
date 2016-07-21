package xyz.parisi.unical.multitask.menu;

import javafx.geometry.Pos;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class Title extends StackPane {
    public Title(String name) {

        LinearGradient gradient = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.LIGHTBLUE),
                new Stop(0.5, Color.PINK),
                new Stop(1, Color.LIGHTYELLOW));

        LinearGradient gradientBox = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE,
                new Stop(0, Color.LIGHTYELLOW),
                new Stop(0.5, Color.LIGHTBLUE),
                new Stop(1, Color.PINK));

        Rectangle bg = new Rectangle(390, 60);


        bg.setStroke(gradientBox);
        bg.setStrokeWidth(2);
        bg.setFill(null);

        Text text = new Text(name);
        text.setFill(gradient);
        text.setFont(Font.font("Sans", FontWeight.SEMI_BOLD, 50));

        setAlignment(Pos.CENTER);
        getChildren().addAll(bg, text);
    }
}

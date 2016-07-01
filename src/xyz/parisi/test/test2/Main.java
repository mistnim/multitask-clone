package xyz.parisi.test.test2;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.util.LinkedList;
import java.util.List;

public class Main extends Application {
    // List<Point2D> drawing = new LinkedList<>();
    List<Line> drawing = new LinkedList<>();
    Point2D p = null;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane root = new Pane();
        root.setPrefSize(600, 400);
        Scene scene = new Scene(root);
        Line l = new Line(0, 0, 600, 400);
        l.setStroke(Color.SIENNA);

        root.getChildren().addAll(l);


        root.setOnMouseMoved(event -> {
            if (p == null) {
                p = new Point2D(event.getX(), event.getY());
                return;
            }
            root.getChildren().add(new Line(p.getX(), p.getY(), event.getX(), event.getY()));

            p = new Point2D(event.getX(), event.getY());
        });

        AnimationTimer at = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (root.getChildren().size() > 0)
                    root.getChildren().remove(0);
            }
        };
        at.start();

        primaryStage.setMinHeight(400);
        primaryStage.setMinWidth(600);
        primaryStage.setTitle("float");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

package xyz.parisi.unical.multitask.arrowsgame;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

class Bar extends Pane {
    private int level = 3;
    private final double HEIGHT = 130;
    private final double WIDTH = 10;
    private final Rectangle[] boxes = new Rectangle[5];
    private final Color FILL = Color.BLUE;

    Bar() {
        for (int i = 0; i < 5; i++) {
            boxes[i] = new Rectangle(-WIDTH / 2, -HEIGHT / 2 + (HEIGHT / 5) * i, WIDTH, HEIGHT / 5);
            boxes[i].setFill(Color.TRANSPARENT);
            boxes[i].setStroke(Color.BLACK);
            getChildren().addAll(boxes[i]);
        }
        boxes[level].setFill(FILL);
    }

    int getLevel() {
        return level;
    }

    void goUp() {
        if (level > 0) {
            level -= 1;
            boxes[level + 1].setFill(Color.TRANSPARENT);
            boxes[level].setFill(FILL);
        }
    }

    void goDown() {
        if (level < 4)
            level += 1;
        boxes[level - 1].setFill(Color.TRANSPARENT);
        boxes[level].setFill(FILL);
    }

    double getHEIGHT() {
        return HEIGHT;
    }

    double getWIDTH() {
        return WIDTH;
    }

}

package xyz.parisi.unical.multitask;

import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.scene.layout.Pane;

class GameScreen {
    private final double width;
    private final double height;
    private final Pane root;
    private int windowsOnScreen = 0;

    private final Window[] windows = new Window[4];

    GameScreen(double width, double height, Pane root) {
        this.width = width;
        this.height = height;
        this.root = root;
    }

    void setFirst(Window w) {
        windows[0] = w;
        root.getChildren().addAll((Pane) w);
        w.myHeightProperty().set(height);
        w.myWidthProperty().set(width);
        windowsOnScreen = 1;
    }

    Timeline setNewWindow(Window w) {
        windowsOnScreen +=1;
        switch (windowsOnScreen) {
            case 2:
                return setSecond(w);
            case 3:
                return setThird(w);
            default:
                return null;
        }
    }

    private Timeline setSecond(Window w) {
        windows[1] = w;
        root.getChildren().addAll((Pane) w);
        w.myHeightProperty().set(height);
        w.myWidthProperty().set(width / 2);
        w.layoutXProperty().set(width);
        w.animate(width / 2, 0, width / 2, height);
        return windows[0].animate(0, 0, width / 2, height);
    }

    private Timeline setThird(Window w) {
        windows[2] = w;
        root.getChildren().addAll((Pane) w);
        w.myHeightProperty().set(height / 2);
        w.myWidthProperty().set(width / 2);
        w.layoutXProperty().set(width / 2);
        w.layoutYProperty().set(height);
        w.animate(width / 2, height / 2, width / 2, height / 2);
        return windows[1].animate(width/2, 0, width / 2, height / 2);
    }
}

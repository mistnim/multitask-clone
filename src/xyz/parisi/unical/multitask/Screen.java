package xyz.parisi.unical.multitask;

import javafx.scene.layout.Pane;

class Screen {
    private double width;
    private double height;
    private Pane appRoot;

    private Window[] windows = new Window[4];

    Screen(double width, double height, Pane appRoot) {
        this.width = width;
        this.height = height;
        this.appRoot = appRoot;
    }

    void set_first(Window w) {
        windows[0] = w;
        appRoot.getChildren().addAll((Pane) w);
        w.myHeightProperty().set(height);
        w.myWidthProperty().set(width);
    }

    void set_second(Window w) {
        windows[1] = w;
        appRoot.getChildren().addAll((Pane) w);
        w.myHeightProperty().set(height);
        w.myWidthProperty().set(width / 2);
        w.layoutXProperty().set(width);
        w.animate(width / 2, 0, width / 2, height);
        windows[0].animate(0, 0, width / 2, height);
    }

    void set_third(Window w) {
        windows[2] = w;
        appRoot.getChildren().addAll((Pane) w);
        w.myHeightProperty().set(height / 2);
        w.myWidthProperty().set(width / 2);
        w.layoutXProperty().set(width / 2);
        w.layoutYProperty().set(height);
        w.animate(width / 2, height / 2, width / 2, height / 2);
        windows[1].animate(0, 0, width / 2, height / 2);
    }
}

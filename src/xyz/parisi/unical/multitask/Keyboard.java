package xyz.parisi.unical.multitask;

import javafx.scene.input.KeyCode;

import java.util.HashMap;

/**
 * Created by daniele on 7/13/16.sa
 */
public class Keyboard {
    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    void press(KeyCode c) {
        keys.put(c, true);
    }

    void release(KeyCode c) {
        keys.put(c, false);
    }

    public boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
}

package xyz.parisi.unical.multitask;

public interface MiniGame {
    boolean update(double timeDelta, long currentTime, Keyboard keyboard);

    String getInstructionText();
}

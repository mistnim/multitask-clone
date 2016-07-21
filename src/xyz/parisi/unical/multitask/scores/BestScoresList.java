package xyz.parisi.unical.multitask.scores;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;

public class BestScoresList extends LinkedList<Score> implements Serializable {
    private static final long serialVersionUID = 1L;

    public BestScoresList() {
    }

    private boolean isFull() {
        return size() == 10;
    }

    private boolean isOverCapacity() {
        return size() > 10;
    }
    public boolean canAdd(Score score) {
        return !isFull() || getFirst().compareTo(score) < 0;
    }

    @Override
    public boolean add(Score score) {
        if (canAdd(score)) {
            super.add(score);
            Collections.sort(this);
            if (isOverCapacity())
                remove(getFirst());
            return true;
        }
        return false;
    }

    public static BestScoresList load() throws Exception {
        return (BestScoresList) ResourceManager.load("multitask.save");
    }

}

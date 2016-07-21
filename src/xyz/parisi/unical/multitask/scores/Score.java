package xyz.parisi.unical.multitask.scores;

import java.io.Serializable;

public class Score implements Comparable<Score>, Serializable {
    private String name;
    private Integer score;

    @Override
    public int compareTo(Score o) {
        return getScore().compareTo(o.getScore());
    }

    Integer getScore() {
        return score;
    }

    String getName() {
        return name;
    }


    public Score(String name, int score) {
        this.name = name;
        this.score = score;
    }
}

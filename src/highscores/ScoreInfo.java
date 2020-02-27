package highscores;

import java.io.Serializable;

/**
 * The type Score info.
 */
public class ScoreInfo implements Comparable<ScoreInfo>, Serializable {
    private String name;
    private int score;

    /**
     * Instantiates a new Score info.
     *
     * @param name  the name
     * @param score the score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Compare to int.
     *
     * @param o the o
     * @return the int
     */
    public int compareTo(ScoreInfo o) {
        return Integer.compare(o.score, this.score);
    }
}

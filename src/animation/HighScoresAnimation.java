package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import highscores.HighScoresTable;
import highscores.ScoreInfo;

import java.awt.Color;
import java.io.File;
import java.util.List;

/**
 * The type High scores animation.
 */
public class HighScoresAnimation implements Animation {
    /**
     * The Scores.
     */
    private HighScoresTable scores;
    private boolean stop;
    private KeyboardSensor key;

    /**
     * Instantiates a new High scores animation.
     *
     * @param scores the scores
     * @param key    the key
     */
    public HighScoresAnimation(HighScoresTable scores, KeyboardSensor key) {
        this.scores = scores;
        this.key = key;
        stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.stop = false;
        int i = 0;
        if (key.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
        List<ScoreInfo> list = this.scores.getHighScores();
        d.setColor(Color.yellow);
        d.drawText(20, 30, "High Scores ", 32);
        d.setColor(Color.green);
        d.drawText(40, 70, "Player Name", 24);
        d.drawText(300, 70, "Score", 24);
        d.drawLine(40, 90, 760, 90);
        d.setColor(Color.ORANGE);
        for (ScoreInfo s : list) {
            d.drawText(40, 120 + (i * 25), s.getName(), 24);
            d.drawText(300, 120 + (i * 25), "" + s.getScore(), 24);
            i++;
        }
    }

    @Override
    public boolean shouldStop() {
        return stop;
    }

    /**
     * Sets scores.
     *
     * @param file the file
     */
    public void setScores(File file) {
        try {
            this.scores.load(file);
        } catch (Exception ex) {
            System.out.println("Problem with highscores loading");
        }
    }
}

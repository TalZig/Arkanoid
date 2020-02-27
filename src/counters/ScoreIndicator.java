package counters;
import biuoop.DrawSurface;
import collidablesprites.Sprite;
import gameclasses.GameLevel;

import java.awt.Color;

/**
 * The type Score indicator.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;

    /**
     * Instantiates a new Score indicator.
     *
     * @param sco the score
     */
    public ScoreIndicator(Counter sco) {
        this.score = sco;
    }

    @Override
    public void drawOn(DrawSurface d) {
        double width, height, x, y;
        String string;
        string = "" + this.score.getValue();
        x = 350;
        y = 20;
        width = 30;
        height = 20;
        d.setColor(Color.BLACK);
        d.drawText((int) (x + (width / 2)), (int) (y + (height / 2) - 5), "Score: " + string, 20);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }
}

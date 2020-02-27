package animation;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import collidablesprites.SpriteCollection;

import java.awt.Color;

/**
 * The type Count down animation.
 */
public class CountDownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private int current;
    private SpriteCollection gameScreen;
    private boolean stop;

    /**
     * Instantiates a new Countdown animation.
     *
     * @param numOfSeconds the num of seconds
     * @param countFrom    the count from
     * @param gameScreen   the game screen
     */
    public CountDownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.current = countFrom;
        stop = false;
    }

    /**
     * Do one frame.
     *
     * @param d the d
     */
    public void doOneFrame(DrawSurface d) {
        Sleeper sleeper = new Sleeper();
        //long startTime = (long) this.numOfSeconds * 1000;
        if (this.current < this.countFrom) {
                sleeper.sleepFor((long) ((this.numOfSeconds / this.countFrom) * 1000));
        }
        gameScreen.drawAllOn(d);
        if (this.current != 0) {
            d.setColor(Color.RED);
            d.drawText(d.getWidth() / 2, d.getHeight() / 5, Integer.toString(this.current), 32);
        }
        this.current--;
        if (current < 0) {
            this.stop = true;
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

package listeners;

import collidablesprites.Ball;
import collidablesprites.Block;
import counters.Counter;

/**
 * The type Score tracking listener.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Instantiates a new Score tracking listener.
     *
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Hit event.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCounter() <= 0) {
            currentScore.increase(10);
        } else {
            currentScore.increase(5);
        }
    }
}
package listeners;

import collidablesprites.Ball;
import collidablesprites.Block;
import counters.Counter;
import gameclasses.GameLevel;

/**
 * The type Ball remover.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter counter;

    /**
     * Instantiates a new Ball remover.
     *
     * @param g the gameLevel
     * @param c the counter
     */
    public BallRemover(GameLevel g, Counter c) {
        this.gameLevel = g;
        this.counter = c;
    }
    /**
     * Hit event.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
// This method is called whenever the beingHit object is hit.
    // The hitter parameter is the Ball that's doing the hitting.
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(gameLevel);
        this.counter.decrease(1);
    }
}

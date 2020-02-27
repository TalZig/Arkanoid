package listeners;

import collidablesprites.Ball;
import collidablesprites.Block;
import counters.Counter;
import gameclasses.GameLevel;

/**
 * The type Block remover.
 */
// a BlockRemove is in charge of removing blocks from the gameLevel, as well as keeping count
// of the number of blocks that remain.
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Instantiates a new Block remover.
     *
     * @param g the gameLevel
     * @param c the counter
     */
    public BlockRemover(GameLevel g, Counter c) {
        this.gameLevel = g;
        this.remainingBlocks = c;
    }

    /**
     * Hit event.
     *
     * @param beingHit the being hit
     * @param hitter   the hitter
     */
// Blocks that are hit and reach 0 hit-points should be removed
    // from the gameLevel. Remember to remove this listener from the block
    // that is being removed from the gameLevel.
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getCounter() + 1 == 1) {
            beingHit.removeFromGame(this.gameLevel);
            beingHit.removeHitListener(this);
            this.remainingBlocks.decrease(1);
        } else {
            beingHit.setCounter(beingHit.getCounter() - 1);
        }
    }
}
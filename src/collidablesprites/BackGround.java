package collidablesprites;

import biuoop.DrawSurface;
import gameclasses.GameLevel;

/**
 * The type Back ground.
 */
public class BackGround implements Sprite {
    private SpriteCollection sprites;

    /**
     * Instantiates a new Back ground.
     */
    public BackGround() {
        this.sprites = new SpriteCollection();
    }

    @Override
    public void drawOn(DrawSurface d) {
        sprites.drawAllOn(d);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        sprites.addToGame(g);
        }

    /**
     * Add to back ground.
     *
     * @param s the s
     */
    public void addToBackGround(Sprite s) {
        sprites.addSprite(s);
    }
    }

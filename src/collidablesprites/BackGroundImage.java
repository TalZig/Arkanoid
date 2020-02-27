package collidablesprites;

import biuoop.DrawSurface;
import gameclasses.GameLevel;

import java.awt.Image;

/**
 * The type Back ground image.
 */
public class BackGroundImage implements Sprite {
    private Image image;

    /**
     * Instantiates a new Back ground image.
     *
     * @param img the img
     */
    public BackGroundImage(Image img) {
        this.image = img;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawImage(0, 0, this.image);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);

    }
}

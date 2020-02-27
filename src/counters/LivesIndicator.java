package counters;

import biuoop.DrawSurface;
import collidablesprites.Sprite;
import gameclasses.GameLevel;

import java.awt.Color;

/**
 * The type Lives indicator.
 */
public class LivesIndicator implements Sprite {
    private Counter live;

    /**
     * Instantiates a new Lives indicator.
     *
     * @param kills the kills
     */
    public LivesIndicator(Counter kills) {
        this.live = kills;
    }
    @Override
    public void drawOn(DrawSurface d) {
        double width, height, x, y;
        String string;
        string = "lives: " + this.live.getValue();
        x = 50;
        y = 20;
        width = 30;
        height = 20;
        d.setColor(Color.BLACK);
        d.drawText((int) (x + (width / 2)), (int) (y + (height / 2) - 5), string, 20);

    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

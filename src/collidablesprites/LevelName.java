package collidablesprites;

import biuoop.DrawSurface;
import gameclasses.GameLevel;

import java.awt.Color;

/**
 * The type Level name.
 */
public class LevelName implements Sprite {
    private String name;

    /**
     * Instantiates a new Level name.
     *
     * @param st the st
     */
    public LevelName(String st) {
        this.name = st;
    }
    @Override
    public void drawOn(DrawSurface d) {
        int x = 510;
        int y = 25;
        d.setColor(Color.BLACK);
        d.drawText((int) x, (int) y, "Level Name: " + name, 17);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

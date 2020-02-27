package collidablesprites;

import biuoop.DrawSurface;
import gameclasses.GameLevel;
import geometricshapes.Point;

import java.awt.Color;

/**
 * The type Line.
 */
public class Line implements Sprite {
    private Point p1;
    private Point p2;
    private Color color;

    /**
     * Instantiates a new Line.
     *
     * @param p1 the p 1
     * @param p2 the p 2
     * @param c  the color
     */
    public Line(Point p1, Point p2, Color c) {
        this.p1 = p1;
        this.p2 = p2;
        this.color = c;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        d.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(), (int) p2.getY());
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

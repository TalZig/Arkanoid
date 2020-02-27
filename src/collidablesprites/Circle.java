package collidablesprites;

import biuoop.DrawSurface;
import gameclasses.GameLevel;
import geometricshapes.Point;

import java.awt.Color;

/**
 * The type Circle.
 */
public class Circle implements Sprite {
    private Point center;
    private int radius;
    private Color color;

    /**
     * Instantiates a new Circle.
     *
     * @param p      the p
     * @param radius the radius
     * @param color  the color
     */
    public Circle(Point p, int radius, Color color) {
        this.center = p;
        this.radius = radius;
        this.color = color;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawCircle((int) center.getX(), (int) center.getY(), radius);
    }

    @Override
    public void timePassed() {

    }

    @Override
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}

package collidablesprites;

import biuoop.DrawSurface;
import geometricshapes.Point;

/**
 * The type Ball without black.
 */
public class BallWithoutBlack extends Ball {
    /**
     * Instantiates a new Ball.
     *
     * @param center the center of the circle
     * @param r      the radius of this circle.
     * @param color  the color of the circle.
     */
    public BallWithoutBlack(Point center, int r, java.awt.Color color) {
        super(center, r, color);
    }
    /**
     * Draw on.
     *
     * @param surface the surface
     */
// draw the ball on the given DrawSurface
    public void drawOn(DrawSurface surface) {
        surface.setColor(getColor());
        surface.fillCircle(this.getX(), this.getY(), super.getSize());
    }
}

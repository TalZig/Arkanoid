package collidablesprites;

import biuoop.DrawSurface;
import geometricshapes.Point;

import java.awt.Color;

/**
 * The type Block without black.
 */
public class BlockWithoutBlack extends Block {
    /**
     * Instantiates a new Block without black.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param count     the count
     * @param color     the color
     */
    public BlockWithoutBlack(Point upperLeft, double width, double height, int count, Color color) {
        super(upperLeft, width, height, count, color);
    }

    /**
     * The Dr.
     */
    /**
     * Draw the blocks on the surface.
     *
     * @param surface the surface
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(super.getColor());
        surface.fillRectangle((int) super.getBlock().getUpperLeft().getX(),
                (int) super.getBlock().getUpperLeft().getY(),
                (int) super.getBlock().getWidth(), (int) super.getBlock().getHeight());
    }
}

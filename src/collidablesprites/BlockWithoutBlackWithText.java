package collidablesprites;

import biuoop.DrawSurface;
import counters.Counter;
import geometricshapes.Point;

import java.awt.Color;

/**
 * The type Block without black with text.
 */
public class BlockWithoutBlackWithText extends BlockWithoutBlack implements Sprite {
    private Counter score;
    private boolean win;

    /**
     * Instantiates a new Block without black with text.
     *
     * @param upperLeft the upper left
     * @param width     the width
     * @param height    the height
     * @param count     the count
     * @param color     the color
     * @param win       the win
     * @param score     the score
     */
    public BlockWithoutBlackWithText(Point upperLeft, double width, double height, int count, Color color,
                                     boolean win, Counter score) {
        super(upperLeft, width, height, count, color);
        this.win = win;
        this.score = score;
    }

    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(super.getColor());
        surface.fillRectangle((int) super.getBlock().getUpperLeft().getX(),
                (int) super.getBlock().getUpperLeft().getY(),
                (int) super.getBlock().getWidth(), (int) super.getBlock().getHeight());
        int x = 0;
        int y = 0;
        double width = 300;
        double height = 600;
        if (!this.win) {
            String string;
            string = "Game Over. \n your score is: " + score.getValue();
            surface.setColor(Color.WHITE);
            surface.drawText((int) (x + (width / 2)), (int) (y + (height / 2)), string, 40);
        } else {
            String string;
            string = "You Win! \n your score is: " + score.getValue();
            surface.setColor(Color.WHITE);
            surface.drawText((int) (x + (width / 2)), (int) (y + (height / 2)), string, 40);
        }
    }
}

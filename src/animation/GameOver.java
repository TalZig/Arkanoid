package animation;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collidablesprites.BackGround;
import collidablesprites.BlockWithoutBlack;
import counters.Counter;
import geometricshapes.Point;

import java.awt.Color;

/**
 * The type End screen.
 */
public class GameOver implements Animation {
    private Counter score;
    private KeyboardSensor keyBoard;
    private boolean stop;

    /**
     * Instantiates a new End screen.
     *
     * @param score the score
     * @param key   the key
     */
    public GameOver(Counter score, KeyboardSensor key) {
        this.score = score;
        stop = false;
        keyBoard = key;
    }

    /**
     * Gets background.
     *
     * @return the back ground
     */
    public BackGround showEndScreen() {
        Point temp1 = new Point(0, 0);
        Point temp2;
        BackGround lvlBackGround = new BackGround();
        lvlBackGround.addToBackGround(new BlockWithoutBlack(temp1, 820, 600, 0,
                Color.lightGray));
        return lvlBackGround;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        BackGround ground = showEndScreen();
        ground.drawOn(d);
        d.setColor(Color.red);
            d.drawText(10, d.getHeight() / 2, "Game Over. \n Your score is: " + score.getValue(), 32);
        //if (this.keyBoard.isPressed(KeyboardSensor.SPACE_KEY)) {
            //this.stop = true;
       // }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}

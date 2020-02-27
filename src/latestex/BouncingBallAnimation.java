package latestex;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collidablesprites.Ball;
import geometricshapes.Point;
import geometricshapes.Velocity;

/**
 * The type Bouncing ball animation.
 */
public class BouncingBallAnimation {
    static final int WIDTH = 400;
    static final int HEIGHT = 400;
    static final int LEFT_LIM = 0;
    static final int UP_LIM = 0;
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Point center = new Point(0, 30);
        Ball ball = new Ball(center, 30, java.awt.Color.BLACK, UP_LIM, HEIGHT, LEFT_LIM, WIDTH);
        Velocity v = Velocity.fromAngleAndSpeed(90, 10);
        ball.setVelocity(v);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}

package latestex;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import collidablesprites.Ball;
import geometricshapes.Velocity;
import geometricshapes.Point;

import java.awt.Color;

/**
 * The type Multiple bouncing balls animation.
 */
public class MultipleBouncingBallsAnimation {
    static final int WIDTH = 1000;
    static final int HEIGHT = 1000;
    static final int LEFT_LIM = 0;
    static final int UP_LIM = 0;
    static final int MAX_RAD = 50;

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Color color;
        double x = 0, y = 0;
        GUI gui = new GUI("title", WIDTH, HEIGHT);
        Sleeper sleeper = new Sleeper();
        Velocity v;
        Ball[] balls = new Ball[args.length];
        int[] sizeArr = new int[args.length];
        for (int i = 0; i < balls.length; i++) {
            sizeArr[i] = Integer.parseInt(args[i]);
            x = (Math.random() * WIDTH);
            y = (Math.random() * HEIGHT);
            while ((x - sizeArr[i]) <= 0 || (y - sizeArr[i]) <= 0 || x + sizeArr[i] > WIDTH
                    || y + sizeArr[i] > HEIGHT) {
                x = (Math.random() * WIDTH);
                y = (Math.random() * HEIGHT);
            }
            //if size of ball big than MAX_RAD the speed is permanent
            if (sizeArr[i] >= MAX_RAD) {
                v = Velocity.fromAngleAndSpeed(Math.random() * 360, 2);
            } else {
                v = Velocity.fromAngleAndSpeed((sizeArr[i] * 360), (70 - sizeArr[i]) / 10.0);
            }
            color = Ball.randColor();
            Point p1 = new Point(x, y);
            balls[i] = new Ball(p1, sizeArr[i], color, UP_LIM, HEIGHT, LEFT_LIM, WIDTH);
            balls[i].setVelocity(v);
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < balls.length; i++) {
                //balls[i].isBallCrossTheBorder(0, 1000, 0, 1000);
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(20);
        }
    }
}
